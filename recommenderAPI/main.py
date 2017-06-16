#!/usr/bin/env python
from __future__ import division

from flask import Flask, g
from flask_cors import CORS, cross_origin
import numpy as np
import pandas as pd
from flask import jsonify
from scipy import spatial


app = Flask(__name__)
CORS(app)


global similarityMatrix
global initialised

@app.route("/")
def index():
    global initialised
    initialised = 0
    if initialised is not 1:
        init()
        initialised = 1
    return "Hi! My name is <b>SexySmart</b>! Sup? <br><br> Try me out!<br/>" \
           " e.g. http://0.0.0.0:5000/get/BookID/similarity(0-22) <br/> " \
           "http://0.0.0.0:5000/get/304133/6"


@app.route("/init")
def init():

    global similarityMatrix
    # read matrix from file
    df = pd.read_csv('../matrixOut.txt', header=None)
    print df
    df2 = df.set_index(0)
    transaction_df = df2.transpose()
    # take data matrix from dataframe
    transaction_matrix = transaction_df.as_matrix()

    # get number of rows and columns
    rows, columns = transaction_matrix.shape
    print rows,columns

    # init new matrix
    frequent_items_matrix = np.zeros((len(df2),len(df2)))
    # compare every product with every other
    for this_column in range(0, columns-1):
        for next_column in range(this_column + 1, columns):
            # multiply product pair vectors
            product_vector = 1 - spatial.distance.cosine(transaction_matrix[:,this_column], transaction_matrix[:,next_column])
            # check the number of pair occurrences in baskets
            count_matches = "{:.2f}".format(product_vector) #sum((product_vector)>0)
            # save values to new matrix
            frequent_items_matrix[this_column,next_column] = count_matches

    # and finally combine product names with data
    similarityMatrix = pd.DataFrame(frequent_items_matrix, columns = transaction_df.columns.values, index = transaction_df.columns.values)
    print similarityMatrix
    return "OK"


@app.route('/get/<int:identifier>/<int:treshold>')
def get(identifier, treshold):
    global similarityMatrix
    return jsonify(similarityMatrix[identifier][similarityMatrix[identifier] > treshold/10].to_dict())
    #return similarityMatrix[identifier][similarityMatrix[identifier] > treshold].to_json()


if __name__ == "__main__":
    app.run(host = '0.0.0.0')
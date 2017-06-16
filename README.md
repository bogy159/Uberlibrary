### Uberlibrary 

This is the root folder of the project **Uberlibrary**

## backend
Start **Recommender API Service** before starting backend
##
The **backend** is implemented in *Java* and has following jobs:
 - getting data from *Wienbibliothek* and make it available for *webui*
 - saving the *reviews*
 - make the *reviews* available for the *webui*

Preconditions:
- install [mongodb](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)
    - run the *backend* from *Intellij* by simply starting the *SpringBoot Application* or by maven command `mvn clean compile exec:java -Dexec.mainClass="at.tuwien.innovation.group7.Application"`

## webui
The **webui** is implemented in *JavaScript* and uses the framework *AngularJs*
- install [node](https://nodejs.org/en/download/) on your machine
- *sudo npm install -g angular-cli*
- *sudo npm install -g bower*
- go into the folder and run `npm install` and `bower install`
- run the *webui* with `ng serve -host 0.0.0.0`


### Recommender API Service
Recommender API

1. install python scientific packeges https://ipython.org/install.html 
+ install python flask microframework http://flask.pocoo.org/
+ install `flask_cors`

2. start the Recommender API with "./main.py" BEFORE starting the backend (backend needs it)

3. you can start making calls from frontend or from backend

### API
call: http://127.0.0.1:5000/get/RECORD_ID/BOOK_SIMILARITY
RECORD_ID: the library book identifier
BOOK_SIMILARITY (from 1 to 9)

Response:
{
  "OTHER_RECORD_ID": similarity
}



## EXAMPLES

call:http://127.0.0.1:5000/get/304133/2
response:
{
  "304134": 0.81000000000000005,
  "304139": 0.34999999999999998,
  "304140": 0.25,
  "304141": 0.40000000000000002,
  "304142": 0.47999999999999998,
  "304143": 0.40999999999999998,
  "304144": 0.37,
  "304145": 0.44,
  "304146": 0.44,
  "304148": 0.34000000000000002,
  "304149": 0.23000000000000001,
  "304153": 0.22,
  "304154": 0.57999999999999996,
  "304156": 0.23999999999999999,
  "304157": 0.45000000000000001,
  "304158": 0.38,
  "304159": 0.52000000000000002,
  "304160": 0.39000000000000001,
  "304161": 0.46999999999999997,
  "304162": 0.34000000000000002,
  "304163": 0.40000000000000002,
  "304164": 0.39000000000000001,
  "304165": 0.39000000000000001,
  "304166": 0.40999999999999998,
  "304167": 0.32000000000000001,
  "304168": 0.38,
  "304169": 0.40999999999999998,
  "304170": 0.40999999999999998,
  "304171": 0.34999999999999998,
  "304172": 0.37,
  "304173": 0.44,
  "304174": 0.35999999999999999,
  "304175": 0.39000000000000001,
  "304176": 0.38,
  "304177": 0.32000000000000001,
  "304178": 0.31,
  "304179": 0.41999999999999998,
  "304200": 0.34000000000000002,
  "304201": 0.23999999999999999,
  "304204": 0.31,
  "304206": 0.48999999999999999,
  "304207": 0.34999999999999998
}


call: http://127.0.0.1:5000/get/304133/4
response:
{
  "304134": 0.81000000000000005,
  "304142": 0.47999999999999998,
  "304143": 0.40999999999999998,
  "304145": 0.44,
  "304146": 0.44,
  "304154": 0.57999999999999996,
  "304157": 0.45000000000000001,
  "304159": 0.52000000000000002,
  "304161": 0.46999999999999997,
  "304166": 0.40999999999999998,
  "304169": 0.40999999999999998,
  "304170": 0.40999999999999998,
  "304173": 0.44,
  "304179": 0.41999999999999998,
  "304206": 0.48999999999999999
}



call: http://127.0.0.1:5000/get/304133/6
response:
{
  "304134": 0.81000000000000005
}





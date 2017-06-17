package at.tuwien.innovation.group7.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Review {

    private String reviewID;
    private Date datestamp;
    private String user;
    private String sentimentTitle;
    private String sentimentBody;
    private Integer rating;         // optional
    private enum ThreeWayClazz {
        NEGATIVE,
        POSITIVE,
        NEUTRAL,
    }
    private ThreeWayClazz clazz;    // optional


    @JsonCreator
    public Review(
            @JsonProperty("reviewID") String reviewID,
            @JsonProperty("datestamp") Date datestamp,
            @JsonProperty("user") String user,
            @JsonProperty("sentimentTitle") String sentimentTitle,
            @JsonProperty("sentimentBody") String sentimentBody,
            @JsonProperty("rating") Integer rating,
            @JsonProperty ("clazz") ThreeWayClazz clazz
    ) {
        this.reviewID = reviewID;
        this.datestamp = datestamp;
        this.user = user;
        this.sentimentTitle = sentimentBody;
        this.sentimentBody = sentimentBody;
        this.rating = rating;
        this.clazz = clazz;
    }


    public String getReviewId() { return reviewID; }

    public Date getDatestamp() { return datestamp; }

    public String getUser() {
        return user;
    }

    public String getSentimentTitle() {
        return sentimentTitle;
    }

    public String getSentimentBody() { return sentimentBody; }

    public Integer geRating() { return rating; }

    public ThreeWayClazz getClazz() { return clazz; }

    public void setReviewID(String reviewID) { this.reviewID = reviewID; }

    public void setDatestamp(Date datestamp) { this.datestamp = datestamp; }

    public void setUser(String user) { this.user = user; }

    public void setSentiment(String sentimentTitle) { this.sentimentTitle = sentimentTitle; }

    public void setSentimentBody(String sentimentBody) { this.sentimentBody = sentimentBody; }

    public void setRating(Integer rating) { this.rating = rating; }

    public void setClazz(ThreeWayClazz clazz) { this.clazz = clazz; }


}
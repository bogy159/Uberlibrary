package at.tuwien.innovation.group7.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {

    private final String id;
    private final Date datestamp;
    private final String user;
    private final String title;
    private final String body;
    private final Integer rating;


    @JsonCreator
    public Review(
            @JsonProperty("id") String id,
            @JsonProperty("datestamp") long datestamp,
            @JsonProperty("user") String user,
            @JsonProperty("title") String title,
            @JsonProperty("body") String body,
            @JsonProperty("rating") Integer rating
    ) {
        this.id = id;
        this.datestamp = new Date(datestamp);
        this.user = user;
        this.title = title;
        this.body = body;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public Date getDatestamp() {
        return datestamp;
    }

    public String getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Integer getRating() {
        return rating;
    }
}
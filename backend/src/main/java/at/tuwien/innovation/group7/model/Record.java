package at.tuwien.innovation.group7.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Record {

    private final Header header;
    private final Metadata metadata;
    private final List<Review> reviews;

    @JsonCreator
    public Record(
            @JsonProperty("header") Header header,
            @JsonProperty("metadata") Metadata metadata,
            @JsonProperty("reviews") List<Review> reviews
    ) {
        this.header = header;
        this.metadata = metadata;
        this.reviews = reviews;
    }

    public Header getHeader() {
        return header;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}


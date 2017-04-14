package at.tuwien.innovation.group7.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class Header {

    private final String identifier;
    private final Timestamp datestamp;
    private final List<String> specs;

    @JsonCreator
    public Header(
            @JsonProperty("identifier") String identifier,
            @JsonProperty("datestamp") Timestamp datestamp,
            @JsonProperty("specs") List<String> specs
    ) {
        this.identifier = identifier;
        this.datestamp = datestamp;
        this.specs = specs;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Timestamp getDatestamp() {
        return datestamp;
    }

    public List<String> getSpecs() {
        return specs;
    }
}

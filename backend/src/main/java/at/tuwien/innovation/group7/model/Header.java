package at.tuwien.innovation.group7.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Header {

    private final String identifier;
    private final Date datestamp;
    private final List<String> specs;

    @JsonCreator
    public Header(
            @JsonProperty("identifier") String identifier,
            @JsonProperty("datestamp") Date datestamp,
            @JsonProperty("specs") List<String> specs
    ) {
        this.identifier = identifier;
        this.datestamp = datestamp;
        this.specs = specs;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Date getDatestamp() {
        return datestamp;
    }

    public List<String> getSpecs() {
        return specs;
    }
}

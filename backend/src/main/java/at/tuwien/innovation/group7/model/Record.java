package at.tuwien.innovation.group7.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Record {

    private final String identifier;
    private final Date datestamp;
    private final List<String> specs;
    private final List<Review> reviews;
    private final String title;
    private final String subject;
    private final String creator;
    private final String publisher;
    private final String date;
    private final String language;
    private final String rights;
    private final List<String> coverage;
    private final List<String> description;
    private final List<String> type;
    private final List<String> format;
    private final List<String> identifierList;

    @JsonCreator
    public Record(
            @JsonProperty("identifier") String identifier,
            @JsonProperty("datestamp") Date datestamp,
            @JsonProperty("specs") List<String> specs,
            @JsonProperty("title") String title,
            @JsonProperty("subject") String subject,
            @JsonProperty("creator") String creator,
            @JsonProperty("publisher") String publisher,
            @JsonProperty("date") String date,
            @JsonProperty("language") String language,
            @JsonProperty("rights") String rights,
            @JsonProperty("coverage") List<String> coverage,
            @JsonProperty("description") List<String> description,
            @JsonProperty("type") List<String> type,
            @JsonProperty("format") List<String> format,
            @JsonProperty("identifierList") List<String> identifierList,
            @JsonProperty("reviews") List<Review> reviews
    ) {
        this.identifier = identifier;
        this.datestamp = datestamp;
        this.specs = specs;
        this.title = title;
        this.subject = subject;
        this.creator = creator;
        this.publisher = publisher;
        this.date = date;
        this.language = language;
        this.rights = rights;
        this.coverage = coverage;
        this.description = description;
        this.type = type;
        this.format = format;
        this.identifierList = identifierList;
        this.reviews = reviews;
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

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getCreator() {
        return creator;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDate() {
        return date;
    }

    public String getLanguage() {
        return language;
    }

    public String getRights() {
        return rights;
    }

    public List<String> getCoverage() {
        return coverage;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getType() {
        return type;
    }

    public List<String> getFormat() {
        return format;
    }

    public List<String> getIdentifierList() {
        return identifierList;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}


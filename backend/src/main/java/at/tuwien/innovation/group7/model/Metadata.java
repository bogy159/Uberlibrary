package at.tuwien.innovation.group7.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Metadata {

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
    private final List<String> identifier;

    @JsonCreator
    public Metadata(
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
            @JsonProperty("identifier") List<String> identifier
    ) {
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
        this.identifier = identifier;
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

    public List<String> getIdentifier() {
        return identifier;
    }
}

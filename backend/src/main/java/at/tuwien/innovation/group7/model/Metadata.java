package at.tuwien.innovation.group7.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Metadata {

    private final String title;
    private final String subject;
    private final String publisher;
    private final Date date;
    private final String language;
    private final String coverage;
    private final List<String> description;
    private final List<String> type;
    private final List<String> identifier;

    @JsonCreator
    public Metadata(
            @JsonProperty("title") String title,
            @JsonProperty("subject") String subject,
            @JsonProperty("publisher") String publisher,
            @JsonProperty("date") Date date,
            @JsonProperty("language") String language,
            @JsonProperty("coverage") String coverage,
            @JsonProperty("description") List<String> description,
            @JsonProperty("type") List<String> type,
            @JsonProperty("identifier") List<String> identifier
    ) {
        this.title = title;
        this.subject = subject;
        this.publisher = publisher;
        this.date = date;
        this.language = language;
        this.coverage = coverage;
        this.description = description;
        this.type = type;
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getPublisher() {
        return publisher;
    }

    public Date getDate() {
        return date;
    }

    public String getLanguage() {
        return language;
    }

    public String getCoverage() {
        return coverage;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getType() {
        return type;
    }

    public List<String> getIdentifier() {
        return identifier;
    }
}

package at.tuwien.innovation.group7.repository;

import at.tuwien.innovation.group7.model.Review;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class MongoRepository {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MongoRepository.class);

    private final DB mongoDB;
    private final ObjectMapper objectMapper;

    @Autowired
    public MongoRepository(DB mongoDB, ObjectMapper objectMapper) {
        this.mongoDB = mongoDB;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        LOG.info("Initialization of mongoDB");
        for(;;) {
            try {
                getReviewsCollection().createIndex(new BasicDBObject("identifier", 1),
                        new BasicDBObject("name", "identifier_idx")
                                .append("unique", true));

                LOG.info("Initialization successfully completed!");

                return;
            } catch (Exception e) {
                LOG.warn("Cannot access reviews Mongo database, automatically retrying in 5 secs...", e);
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException ie) {
                    Thread.interrupted();
                    return;
                }
            }
        }
    }

    private DBCollection getReviewsCollection() {
        return mongoDB.getCollection("reviews");
    }

    public void saveReview(String identifier, Review review) {
        try {
            getReviewsCollection().update(new BasicDBObject().append("identifier", identifier),
                    new BasicDBObject("$set", objectMapper.writeValueAsString(review)), true, false, WriteConcern.ACKNOWLEDGED);

            LOG.debug("Object was successfully saved!");
        } catch (Exception e) {
            LOG.warn("Saving object into mongoDB failed!", e);
        }
    }
}

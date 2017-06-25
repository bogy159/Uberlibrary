package at.tuwien.innovation.group7.repository;

import at.tuwien.innovation.group7.model.Review;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoRepository {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MongoRepository.class);

    private final MongoDatabase mongoDB;
    private final ObjectMapper objectMapper;

    @Autowired
    public MongoRepository(MongoDatabase mongoDB, ObjectMapper objectMapper) {
        this.mongoDB = mongoDB;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        LOG.info("Initialization of mongoDB");
        for(;;) {
            try {
                getReviewsCollection().createIndex(new BasicDBObject("identifier", 1), new IndexOptions().unique(true));

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

    private MongoCollection<Document> getReviewsCollection() {
        return mongoDB.getCollection("reviews");
    }

    public void saveReview(String identifier, Review review) {
        try {
            getReviewsCollection()
                    .updateOne(
                            new BasicDBObject().append("identifier", identifier),
                            new BasicDBObject("$addToSet", new BasicDBObject("reviews", objectMapper.writeValueAsString(review)))   ,
                            new UpdateOptions().upsert(true)
                    );

            LOG.info("Object was successfully saved!");
        } catch (Exception e) {
            LOG.error("Saving object into mongoDB failed!", e);
        }
    }

    public List<Review> getReviews(String identifier) throws IOException {
        FindIterable<Document> result = getReviewsCollection().find(new BasicDBObject("identifier", identifier));
        List<Review> reviews = new ArrayList<>();

        for (Document doc : result) {
            List<String> r = (List<String>)doc.get("reviews");
            for (String review : r) {
                reviews.add(objectMapper.readValue(review, Review.class));
            }
        }

        LOG.info(reviews.toString());
        return reviews;
    }
}

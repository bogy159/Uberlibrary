package at.tuwien.innovation.group7.controller;

import at.tuwien.innovation.group7.model.Review;
import at.tuwien.innovation.group7.repository.MongoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin
public class ReviewController {

    private final static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(ReviewController.class);

    private final MongoRepository mongoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReviewController(MongoRepository mongoRepository, ObjectMapper objectMapper) {
        this.mongoRepository = mongoRepository;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public void addReview(
            @PathVariable String id,
            @RequestBody Object review
    ) {

        LOG.info("Saving review {} for {}", review.toString(), id);
        mongoRepository.saveReview(id, objectMapper.convertValue(review, Review.class));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<Review> getReviews(@PathVariable String id) throws IOException {
        return mongoRepository.getReviews(id);
    }
}

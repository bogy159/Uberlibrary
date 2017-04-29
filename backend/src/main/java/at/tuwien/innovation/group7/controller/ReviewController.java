package at.tuwien.innovation.group7.controller;

import at.tuwien.innovation.group7.model.Review;
import at.tuwien.innovation.group7.repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin
public class ReviewController {

    private final MongoRepository mongoRepository;

    @Autowired
    public ReviewController(MongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.PUT, produces = "application/json")
    public void addReview(
            @PathVariable String id,
            @RequestBody Review review
    ) {
        mongoRepository.saveReview(id, review);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<Review> getReviews(@PathVariable String id) throws IOException {
        return mongoRepository.getReviews(id);
    }
}

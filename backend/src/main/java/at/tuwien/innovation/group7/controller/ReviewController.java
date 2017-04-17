package at.tuwien.innovation.group7.controller;

import at.tuwien.innovation.group7.model.Review;
import at.tuwien.innovation.group7.repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final MongoRepository mongoRepository;

    @Autowired
    public ReviewController(MongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.PUT, produces = "application/json")
    public void addReview(@RequestBody Review review) {

    }
}

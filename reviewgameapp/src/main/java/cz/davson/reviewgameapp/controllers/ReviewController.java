package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.Review;
import cz.davson.reviewgameapp.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
@CrossOrigin()

public class ReviewController {

    private final ReviewService service;

    @GetMapping(path = "/all")
    public List<Review> getUsers() {
        return  service.findAllReviews();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Review review) {
        service.save(review);
        return  ResponseEntity.ok("Review was successfully created!");
    }

    @PostMapping("/{reviewID}/update")
    public ResponseEntity<?> updateUser(@RequestBody Review review, @PathVariable("reviewID") long reviewID) {
        Review reviewFromDB = service.findById(reviewID).orElseThrow(() -> new IllegalArgumentException("Review not found for id :: " + reviewID));
        Objects.requireNonNull(reviewFromDB).setUser(review.getUser());
        Objects.requireNonNull(reviewFromDB).setGame(review.getGame());
        Objects.requireNonNull(reviewFromDB).setReviewComment(review.getReviewComment());
        Objects.requireNonNull(reviewFromDB).setScore(review.getScore());
        service.save(reviewFromDB);
        return ResponseEntity.ok("Review is updated!");
    }

    @DeleteMapping("/{reviewID}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable("reviewID") long gameID) {
        service.deleteById(gameID);
        return ResponseEntity.ok("Review is successfully deleted!");
    }
}

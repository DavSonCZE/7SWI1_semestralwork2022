package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.Review;
import cz.davson.reviewgameapp.entities.User;
import cz.davson.reviewgameapp.repositories.ReviewRepository;
import cz.davson.reviewgameapp.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
@CrossOrigin
public class ReviewController {
    private final ReviewService service;
    @Autowired
    private ReviewRepository reviewRepository;
    @GetMapping("/all")
    public List<Review> getReviews() {
        return service.findAllReviews();
    }
    @PostMapping("/create")
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        if(!reviewRepository.findByUserAndGame(review.getUser(),review.getGame()).isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body("You already reviewed this game. Edit or delete your review before adding new one.");
        }
        else {
            service.save(review);
            return ResponseEntity.ok("Review was successfully created!");
        }
    }

    @GetMapping("/game/{gameId}")
    public List<Review> getReviewsByGame(@PathVariable("gameId") long gameId) {return service.findByGameId(gameId);}
    @PutMapping("/{reviewId}/update")
    public ResponseEntity<?> updateReview(@RequestBody Review review, @PathVariable("reviewId") long reviewId) {
        Review reviewFromDb = service.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("Review not found for this id :: " + reviewId));
        Objects.requireNonNull(reviewFromDb).setUser(review.getUser());
        Objects.requireNonNull(reviewFromDb).setGame(review.getGame());
        Objects.requireNonNull(reviewFromDb).setScore(review.getScore());
        Objects.requireNonNull(reviewFromDb).setReviewComment(review.getReviewComment());
        service.save(reviewFromDb);
        return ResponseEntity.ok("Review was successfully updated!");
    }
    @DeleteMapping("/{reviewId}/delete")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") long reviewId) {
        service.deleteById(reviewId);
        return ResponseEntity.ok("Review was successfully deleted!");
    }

    @GetMapping("/{reviewId}")
    public Review getGameGenreById(@PathVariable("reviewId") long reviewId) {
        return service.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("Genre not found for this id :: " + reviewId));
    }

}

package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.Review;
import cz.davson.reviewgameapp.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ReviewController {

    private final ReviewRepository repository;

    @Autowired
    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/reviews")
    public List<Review> findAll() {
        return repository.findAll();
    }

    @GetMapping("/review/{id}")
    Optional<Review> findGame(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/review/{id}")
    ResponseEntity<String> createOrUpdate(@PathVariable Long id, @RequestBody Review newReview) {
        Review review = repository.findById(id)
                .map(x -> {
                    x.setUser(newReview.getUser());
                    x.setGame(newReview.getGame());
                    x.setScore(newReview.getScore());
                    x.setReviewComment(newReview.getReviewComment());
                    return repository.save(x);
                })
                .orElseGet(() -> repository.save(newReview));
        return ResponseEntity.ok("Review is valid");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Map> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Map> ret = new HashMap<>();
        ret.put("errors", errors);
        return ret;
    }
}

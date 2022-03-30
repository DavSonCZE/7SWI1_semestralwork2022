package cz.davson.reviewgameapp.repositories;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository  extends JpaRepository<Review, Long> {

    void deleteById(long reviewId);
}

package cz.davson.reviewgameapp.repositories;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository  extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    void deleteById(long reviewId);
    List<Review> findByMovie(Game game);
}

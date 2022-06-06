package cz.davson.reviewgameapp.repositories;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.entities.Review;
import cz.davson.reviewgameapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository  extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    void deleteById(long reviewId);
    List<Review> findByGame(Game game);
    List<Review> findByUser(User user);
    List<Review> findByUserAndGame(User user, Game game);
}

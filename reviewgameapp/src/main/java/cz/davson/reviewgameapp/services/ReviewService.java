package cz.davson.reviewgameapp.services;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.entities.Review;
import cz.davson.reviewgameapp.entities.User;
import cz.davson.reviewgameapp.repositories.GameRepository;
import cz.davson.reviewgameapp.repositories.ReviewRepository;
import cz.davson.reviewgameapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }
    public void save(Review review){
        reviewRepository.save(review);
    }
    public void deleteById(long gameId) {
        reviewRepository.deleteById(gameId);
    }
    public Optional<Review> findById(long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> findByGameId(long id) { return reviewRepository.findByGame(getGame(id));}

    public User getUser(long userID) {
        return  findAllUsers().stream()
                .filter((o) -> o.getId() == userID)
                .findFirst()
                .orElseThrow(() ->  new IllegalArgumentException("Unknown user id: "+ userID));
    }

    public Game getGame(long gameID) {
        return findAllGames().stream()
                .filter((o) -> o.getId() == gameID)
                .findFirst()
                .orElseThrow(() ->  new IllegalArgumentException("Unknown game id: "+ gameID));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }
}

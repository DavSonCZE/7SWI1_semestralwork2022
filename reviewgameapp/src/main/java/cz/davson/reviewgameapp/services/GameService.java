package cz.davson.reviewgameapp.services;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.entities.GameGenre;
import cz.davson.reviewgameapp.entities.Review;
import cz.davson.reviewgameapp.repositories.GameGenreRepository;
import cz.davson.reviewgameapp.repositories.GameRepository;
import cz.davson.reviewgameapp.repositories.ReviewRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameGenreRepository gameGenreRepository;
    private final ReviewRepository reviewRepository;

    public GameService(GameRepository gameRepository, GameGenreRepository gameGenreRepository, ReviewRepository reviewRepository) {
        this.gameRepository = gameRepository;
        this.gameGenreRepository = gameGenreRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }
    public void save(Game game){
        gameRepository.save(game);
    }

    public void deleteById(long gameId) {
        gameRepository.deleteById(gameId);
    }

    public Boolean gameExistsByName(String name) {
        return gameRepository.existsByName(name);
    }

    public Game getGameByName(String name) {
        return gameRepository.getGameByName(name);
    }

    public Optional<Game> findById(long id) {
        return gameRepository.findById(id);
    }

    public List<Review> findByGame(Game game) {
        return reviewRepository.findByGame(game);
    }

    public GameGenre getGenre(long gameGenreId) {
        return findAllGameGenres().stream()
                .filter((q) -> q.getId() == gameGenreId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown genre id " + gameGenreId));
    }

    public List<GameGenre> findAllGameGenres() {
        return gameGenreRepository.findAll();
    }



}

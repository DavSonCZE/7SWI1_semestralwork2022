package cz.davson.reviewgameapp.services;

import cz.davson.reviewgameapp.entities.GameGenre;
import cz.davson.reviewgameapp.repositories.GameGenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameGenreService {
    GameGenreRepository gameGenreRepository;

    @Autowired
    public GameGenreService(GameGenreRepository gameGenreRepository) {
        this.gameGenreRepository = gameGenreRepository;
    }

    public List<GameGenre> findAllGenres() {
        return gameGenreRepository.findAll();
    }

    public void save(GameGenre gameGenre) {
        gameGenreRepository.save(gameGenre);
    }

    public void deleteById(long gameGenreId) {
        gameGenreRepository.deleteById(gameGenreId);
    }

    public Optional<GameGenre> findById(long id) {
        return gameGenreRepository.findById(id);
    }

    public Boolean gameGenreExistsByName(String name) {
        return gameGenreRepository.existsByName(name);
    }

    public GameGenre getGameGenreByName(String name) {
        return gameGenreRepository.getGameGenreByName(name);
    }
}

package cz.davson.reviewgameapp.services;

import cz.davson.reviewgameapp.entities.GameGenre;
import cz.davson.reviewgameapp.repositories.GameGenreRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameGenreService {

    private final GameGenreRepository gameGenreRepository;
    public List<GameGenre> findAllGenres() {
        return gameGenreRepository.findAll();
    }
    public void save(GameGenre genre){
        gameGenreRepository.save(genre);
    }
    public void deleteById(long genreId) {
        gameGenreRepository.deleteById(genreId);
    }

    public Optional<GameGenre> findById(long id) {
        return gameGenreRepository.findById(id);
    }

    public Boolean genreExistsByName(String name) {
        return gameGenreRepository.existsByName(name);
    }

    public GameGenre getGenreByName(String name) {
        return gameGenreRepository.getGenreByName(name);
    }
}

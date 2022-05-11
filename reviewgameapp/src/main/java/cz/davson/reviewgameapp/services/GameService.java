package cz.davson.reviewgameapp.services;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.repositories.GameRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public void save(Game game){
        gameRepository.save(game);
    }

    public Optional<Game> findById(long id) {
        return gameRepository.findById(id);
    }

    public void deleteById(long gameId) {
        gameRepository.deleteById(gameId);
    }

}

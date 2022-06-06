package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.entities.Review;
import cz.davson.reviewgameapp.repositories.GameRepository;
import cz.davson.reviewgameapp.repositories.ReviewRepository;
import cz.davson.reviewgameapp.services.GameService;
import cz.davson.reviewgameapp.services.ReviewService;
import cz.davson.reviewgameapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/game")
@CrossOrigin()
public class GameController {
    private final GameService service;
    @Autowired
    private GameRepository gameRepository;
    @GetMapping("/all")
    public List<Game> getGames() {
        return service.findAllGames();
    }
    @PostMapping("/create")
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        if(gameRepository.existsByName(game.getName())){
            return ResponseEntity
                    .badRequest()
                    .body("Game with this name exist in DB!");
        }
        else {
            service.save(game);
            return ResponseEntity.ok("Game was successfully created!");
        }
    }
    @PutMapping("/{gameId}/update")
    public ResponseEntity<?> updateUser(@RequestBody Game game, @PathVariable("gameId") long gameId) {
        Game userFromDb = service.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Game not found for this id :: " + gameId));
        Objects.requireNonNull(userFromDb).setName(game.getName());
        Objects.requireNonNull(userFromDb).setDeveloper(game.getDeveloper());
        Objects.requireNonNull(userFromDb).setReleaseYear(game.getReleaseYear());
        Objects.requireNonNull(userFromDb).setPlatform(game.getPlatform());
        Objects.requireNonNull(userFromDb).setPrice(game.getPrice());
        Objects.requireNonNull(userFromDb).setGenres(game.getGenres());
        service.save(userFromDb);
        return ResponseEntity.ok("Game was successfully updated!");
    }

    @DeleteMapping("/{gameId}/delete")
    public ResponseEntity<?> deleteGame(@PathVariable("gameId") long gameId) {
        service.deleteById(gameId);
        return ResponseEntity.ok("Game was successfully deleted!");
    }

    @GetMapping("/{gameId}")
    public Game getGameGenreById(@PathVariable("gameId") long gameId) {
        return service.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Genre not found for this id :: " + gameId));
    }

}

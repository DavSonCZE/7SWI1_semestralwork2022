package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.entities.User;
import cz.davson.reviewgameapp.repositories.GameRepository;
import cz.davson.reviewgameapp.services.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/game")
@CrossOrigin()

public class GameController {
    private final GameService service;

    @GetMapping(path = "/all")
    public List<Game> getGames(){
        return service.findAllGames();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Game game) {
        service.save(game);
        return ResponseEntity.ok("Game is successfully created!");
    }

    @PostMapping("/{gameID}/update")
    public ResponseEntity<?> updateUser(@RequestBody Game game,@PathVariable("gameID") long gameID) {
        Game gameFromDB = service.findById(gameID).orElseThrow( () -> new IllegalArgumentException("Game is not found for id: " + gameID));
        Objects.requireNonNull(gameFromDB).setGameName(game.getGameName());
        Objects.requireNonNull(gameFromDB).setReleaseYear(game.getReleaseYear());
        Objects.requireNonNull(gameFromDB).setDeveloper(game.getDeveloper());
        Objects.requireNonNull(gameFromDB).setPlatform(game.getPlatform());
        Objects.requireNonNull(gameFromDB).setPrice(game.getPrice());
        Objects.requireNonNull(gameFromDB).setGenres(game.getGenres());
        service.save(gameFromDB);
        return ResponseEntity.ok("Game is updated!");
    }

    @DeleteMapping("/{gameID}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable("gameID") long gameID) {
        service.deleteById(gameID);
        return ResponseEntity.ok("Game is successfully deleted!");
    }



}

package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GameController {

    private final GameRepository repository;

    @Autowired
    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/games")
    public List<Game> findAll() {
        return repository.findAll();
    }

    @GetMapping("/game/{id}")
    Optional<Game> findGame(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/game/{id}")
    ResponseEntity<String> createOrUpdate(@PathVariable Long id, @RequestBody Game newGame) {
        Game game = repository.findById(id)
                .map(x -> {
                    x.setGameIcon(newGame.getGameIcon());
                    x.setGameName(newGame.getGameName());
                    x.setGameGenres(newGame.getGameGenres());
                    x.setReleaseDate(newGame.getReleaseDate());
                    x.setDeveloper(newGame.getDeveloper());
                    x.setPublisher(newGame.getPublisher());
                    x.setPlatform(newGame.getPlatform());
                    x.setPrice(newGame.getPrice());
                    return repository.save(x);
                })
                .orElseGet(() -> repository.save(newGame));
        return ResponseEntity.ok("Game is valid");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Map> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Map> ret = new HashMap<>();
        ret.put("errors", errors);
        return ret;
    }
}

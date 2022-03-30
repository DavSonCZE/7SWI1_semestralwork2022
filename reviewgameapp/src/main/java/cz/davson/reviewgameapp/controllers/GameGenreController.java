package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.Game;
import cz.davson.reviewgameapp.entities.GameGenre;
import cz.davson.reviewgameapp.repositories.GameGenreRepository;
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

@RestController
public class GameGenreController {
    private final GameGenreRepository repository;

    @Autowired
    public GameGenreController(GameGenreRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/gamegenre")
    public List<GameGenre> findAll() {
        return repository.findAll();
    }

    @GetMapping("/gamegenre/{id}")
    Optional<GameGenre> findGame(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/gamegenre/{id}")
    ResponseEntity<String> createOrUpdate(@PathVariable Long id, @RequestBody GameGenre newGameGenre) {
        GameGenre gameGenre= repository.findById(id)
                .map(x -> {
                    x.setName(newGameGenre.getName());
                    return repository.save(x);
                })
                .orElseGet(() -> repository.save(newGameGenre));
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

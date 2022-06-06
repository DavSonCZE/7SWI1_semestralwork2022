package cz.davson.reviewgameapp.controllers;


import cz.davson.reviewgameapp.entities.GameGenre;
import cz.davson.reviewgameapp.services.GameGenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/gamegenre")
@CrossOrigin
public class GameGenreController {
    private final GameGenreService service;

    @GetMapping("/all")
    public List<GameGenre> getGameGenres() {
        return service.findAllGenres();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGameGenre(@RequestBody GameGenre gameGenre) {
        if (service.gameGenreExistsByName(gameGenre.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: GameGenre is already in database!");
        } else {
            service.save(gameGenre);
            return ResponseEntity.ok("Genre was successfully created!");
        }
    }

    @PutMapping("/{gamegenreId}/update")
    public ResponseEntity<?> updateGameGenre(@RequestBody GameGenre gameGenre, @PathVariable("gamegenreId") long gameGenreId) {
        if (service.gameGenreExistsByName(gameGenre.getName())
                && gameGenre.getId() != service.getGameGenreByName(gameGenre.getName()).getId()) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: GameGenre is already in database!");
        } else {
            GameGenre genreFromDb = service.findById(gameGenreId).orElseThrow(() -> new IllegalArgumentException("Genre not found for this id :: " + gameGenreId));
            Objects.requireNonNull(genreFromDb).setName(gameGenre.getName());
            service.save(genreFromDb);
            return ResponseEntity.ok("Genre was successfully updated!");
        }
    }

    @DeleteMapping("/{gamegenreId}/delete")
    public ResponseEntity<?> deleteGameGenre(@PathVariable("gamegenreId") long gameGenreId) {
        service.deleteById(gameGenreId);
        return ResponseEntity.ok("Genre was successfully deleted!");
    }

    @GetMapping("/{gamegenreId}")
    public GameGenre getGameGenreById(@PathVariable("gamegenreId") long gameGenreId) {
        return service.findById(gameGenreId).orElseThrow(() -> new IllegalArgumentException("Genre not found for this id :: " + gameGenreId));
    }

}

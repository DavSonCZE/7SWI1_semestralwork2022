package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.GameGenre;
import cz.davson.reviewgameapp.services.GameGenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/gamegenre/")
@CrossOrigin()

public class GameGenreController {
    private final GameGenreService service;

    @GetMapping(path = "/all")
    public List<GameGenre> getGenres() {
        return  service.findAllGenres();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody GameGenre gameGenre) {
        if(service.genreExistsByName(gameGenre.getName())){
            return ResponseEntity
                    .badRequest()
                    .body("Warning: This gameGenre is already in DB!");
        }
        else
        {
            service.save(gameGenre);
            return ResponseEntity.ok("GameGenre is created!");
        }
    }

    @PostMapping("/{gameGenreID}/update")
    public ResponseEntity<?> updateGameGenre(@RequestBody GameGenre gameGenre, @PathVariable("gameGenreID") long gameGenreID){
        if(service.genreExistsByName(gameGenre.getName())){
            return ResponseEntity
                    .badRequest()
                    .body("Warning: GameGenre is already iN DB!");
        }
        else
        {
            GameGenre gameGenreFromDB = service.findById(gameGenreID).orElseThrow(() -> new IllegalArgumentException("User is not found for id: " + gameGenreID));
            Objects.requireNonNull(gameGenreFromDB).setName(gameGenre.getName());
            service.save(gameGenreFromDB);
            return  ResponseEntity.ok( "GameGenre is successfully updated!");
        }
    }

    @DeleteMapping("/{gameGenreID}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable("gameGenreID") long gameGenreID) {
        service.deleteById(gameGenreID);
        return ResponseEntity.ok("GameGenre is successfully deleted!");
    }

    @GetMapping("/{gameGenreID}")
    public GameGenre getGameGenreByID(@PathVariable("gameGenreID") long gameGenreID) {
        return  service.findById(gameGenreID).orElseThrow(() -> new IllegalArgumentException("Game not found with this ID: " + gameGenreID));
    }
}

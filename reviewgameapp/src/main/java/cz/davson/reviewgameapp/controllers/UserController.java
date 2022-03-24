package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.User;
import cz.davson.reviewgameapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(
        name = "",
//        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/users/{id}")
    Optional<User> findUser(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<String> createOrUpdate(  @PathVariable Long id, @RequestBody User newUser) {
        User user = repository.findById(id)
                .map(x -> {
                    x.setFirstName(newUser.getFirstName());
                    x.setSurName(newUser.getSurName());
                    x.setUserName(newUser.getUserName());
                    x.setEmail(newUser.getEmail());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    return repository.save(newUser);
                });
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

package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.User;
import cz.davson.reviewgameapp.repositories.UserRepository;
import cz.davson.reviewgameapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin()
public class UserController {

    private final UserService service;
    @GetMapping(path = "/all")
    public List<User> getUsers() {
        return  service.findAllUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        user.setPassword(user.getPassword());
        service.save(user);
        return  ResponseEntity.ok("User was successfully created!");
    }

    @PostMapping("/{userID}/update")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable("userID") long userID) {
        User userFromDb = service.findById(userID).orElseThrow(() -> new IllegalArgumentException("User is not found for id: " + userID));
        Objects.requireNonNull(userFromDb).setUsername(user.getUsername());
        Objects.requireNonNull(userFromDb).setEmail(user.getEmail());
        Objects.requireNonNull(userFromDb).setPassword(user.getPassword());
        service.save(userFromDb);
        return ResponseEntity.ok("User is updated!");
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable("userID") long userID) {
        service.deleteById(userID);
        return ResponseEntity.ok("User is successfully deleted!");
    }

}

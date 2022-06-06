package cz.davson.reviewgameapp.controllers;

import cz.davson.reviewgameapp.entities.User;
import cz.davson.reviewgameapp.repositories.UserRepository;
import cz.davson.reviewgameapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private final UserService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getUsers() {
        return service.findAllUsers();
    }

    @GetMapping("/login")
    public User logInUser(@RequestParam String email, @RequestParam String password) {
        User user = service.findByEmail(email);

        if(user.matchPassword(password)) {
            return user;
        } else {
            return null;
        }



        /*if(userRepository.existsByEmail(user.getEmail()) && userRepository.matchPassword(user.getPassword())) {
            return service.findByEmail(user.getEmail());
        } else return null;*/
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if(userRepository.existsByUsername(user.getUsername()) && userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body("Username or email is used in DB!");
        }
        else {
            user.setPassword(user.getPassword());
            service.save(user);
            return ResponseEntity.ok("User was successfully created!");
        }

    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<?> updateUser(@PathVariable("userId") long userId, @RequestBody User user) {
        User userFromDb = service.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found for this id :: " + userId));
        Objects.requireNonNull(userFromDb).setUsername(user.getUsername());
        Objects.requireNonNull(userFromDb).setEmail(user.getEmail());
        Objects.requireNonNull(userFromDb).setPassword(user.getPassword());
        service.save(userFromDb);
        return ResponseEntity.ok("User was successfully updated!");
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") long userId) {
        service.deleteById(userId);
        return ResponseEntity.ok("User was successfully deleted!");
    }

    @GetMapping("/{userId}")
    public User getGameGenreById(@PathVariable("userId") long userId) {
        return service.findById(userId).orElseThrow(() -> new IllegalArgumentException("Genre not found for this id :: " + userId));
    }

}

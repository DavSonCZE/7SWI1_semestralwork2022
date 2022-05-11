package cz.davson.reviewgameapp.services;

import cz.davson.reviewgameapp.entities.User;
import cz.davson.reviewgameapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public void save(User user){
        userRepository.save(user);
    }
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
    public void deleteById(long userId) {
        userRepository.deleteById(userId);
    }
}

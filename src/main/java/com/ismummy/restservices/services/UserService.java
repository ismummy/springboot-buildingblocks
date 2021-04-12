package com.ismummy.restservices.services;

import com.ismummy.restservices.entities.User;
import com.ismummy.restservices.exceptions.UserExistsException;
import com.ismummy.restservices.exceptions.UserNotFoundException;
import com.ismummy.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //Autowire the UserRepository
    @Autowired
    private UserRepository userRepository;

    //get All User
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //create user
    public User createUser(User user) throws UserExistsException {
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null) {
            throw new UserExistsException("User with the same username already exist");
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found in the repository!");
        }
        return user;
    }

    public User updateUserById(Long id, User user) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found in the repository, please provide correct user Id!");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in the repository, please provide correct user Id!");
        }
        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

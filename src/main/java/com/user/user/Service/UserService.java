package com.user.user.Service;

import com.user.user.Entity.User;
import com.user.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User>getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else {
            throw new RuntimeException
                    ("User with such ID not found" + id);
        }
    }

    public User updateUser(Long id, User updatedUser){
        return userRepository.findById(id).map(user -> {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException(
                "User not found with ID" + id));
    }

}

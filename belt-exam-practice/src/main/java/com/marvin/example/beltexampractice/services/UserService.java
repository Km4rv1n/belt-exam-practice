package com.marvin.example.beltexampractice.services;

import com.marvin.example.beltexampractice.models.LoginUser;
import com.marvin.example.beltexampractice.models.User;
import com.marvin.example.beltexampractice.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAndRegister(User user, BindingResult result) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "This email is already in use.");
        }

        if (!user.getPassword().equals(user.getConfirm())) {
            result.rejectValue("confirm", "error.user", "Passwords do not match.");
        }

        if (!result.hasErrors()) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
        }
    }
    public User login(LoginUser loginUser, BindingResult result) {
        User user = userRepository.findUserByEmail(loginUser.getEmail()).orElse(null);
        if (user == null || !BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
            result.rejectValue("email", "error.loginUser", "Invalid email or password.");
            return null;
        }
        return user;
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}

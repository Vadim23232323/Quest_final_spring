package com.example.quest_spring.service;

import com.example.quest_spring.entity.User;
import com.example.quest_spring.repositoty.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    UserRepo userRepo ;
    private BCryptPasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
    public void save(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepo.save(user);
        log.info("The user " + user.getLogin() + " has been successfully registered ");
    }
    public List<User> getAll(){
        return userRepo.findAll();
    }

    public boolean isLoginTaken(String login) {
        return userRepo.findByLogin(login).isPresent();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public void updateProfile(User user,  String newName, String newSurname, String newEmail, String newPassword) {
        user.setName(newName);
        user.setSurname(newSurname);
        user.setEmail(newEmail);

        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(passwordEncoder().encode(newPassword));
            log.info("User: {} changed the password.", user.getLogin());
        }

        userRepo.save(user);
        log.info("User: {} updated their details.", user.getLogin());
    }
}

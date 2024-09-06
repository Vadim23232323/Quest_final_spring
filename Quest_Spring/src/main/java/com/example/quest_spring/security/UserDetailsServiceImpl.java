package com.example.quest_spring.security;

import com.example.quest_spring.entity.User;
import com.example.quest_spring.repositoty.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepo userRepo ;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with login " + username + " not found.")) ;
        log.info("User: " + username + " successfully logged in");
        return UserDetailsImpl.build(user) ;
    }
}

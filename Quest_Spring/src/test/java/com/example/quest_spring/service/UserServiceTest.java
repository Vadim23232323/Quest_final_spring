package com.example.quest_spring.service;

import com.example.quest_spring.entity.User;
import com.example.quest_spring.repositoty.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setPassword("password");
        when(userRepo.save(user)).thenReturn(user);

        userService.save(user);

        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void testUpdateProfile() {
        User user = new User();
        when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));

        userService.updateProfile(user, "New Name", "New Surname", "newemail@example.com", "newpassword");

        assertEquals("New Name", user.getName());
        assertEquals("New Surname", user.getSurname());
        assertEquals("newemail@example.com", user.getEmail());
        assertNotNull(user.getPassword());
        verify(userRepo, times(1)).save(user);
    }
}

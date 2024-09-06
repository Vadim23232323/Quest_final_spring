package com.example.quest_spring.controller;

import com.example.quest_spring.entity.User;
import com.example.quest_spring.service.UserService;
import com.example.quest_spring.util.WebPaths;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testMainPage() throws Exception {
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name(WebPaths.MAIN))
                .andExpect(model().attribute("user", users));
    }
}

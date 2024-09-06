package com.example.quest_spring.controller;
import com.example.quest_spring.entity.Quest;
import com.example.quest_spring.repositoty.QuestRepository;
import com.example.quest_spring.util.WebPaths;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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
public class QuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestRepository questRepository;

    @Test
    @WithMockUser(username = "vadim232323", roles = {"USER"})
    public void testListQuests() throws Exception {
        List<Quest> quests = Arrays.asList(new Quest(), new Quest());
        when(questRepository.findAll()).thenReturn(quests);

        mockMvc.perform(get(WebPaths.LIST_QUEST))
                .andExpect(status().isOk())
                .andExpect(view().name(WebPaths.WP_LIST_QUEST))
                .andExpect(model().attribute("quests", quests));
    }
}


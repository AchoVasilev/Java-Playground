package com.example.PathFinder.web.rest;

import com.example.PathFinder.services.comment.ICommentService;
import com.example.PathFinder.viewModels.comment.CommentDisplayView;
import com.example.PathFinder.viewModels.comment.CommentDto;
import com.example.PathFinder.viewModels.comment.CommentMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {
    private static final Long routeId = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICommentService commentService;

    @Test
    public void getTwoCommentsShouldReturnTwoCommentsAsJsonWithStatusOk() throws Exception{
        when(this.commentService.getAllRouteComments(routeId))
                .thenReturn(List.of(
                        new CommentDisplayView(1L, "Gosho Peshev", "some random msg"),
                        new CommentDisplayView(2L, "Gosho Peshev", "another random msg")
                ));

        this.mockMvc.perform(get("/api/" + routeId + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].authorName", is("Gosho Peshev")))
                .andExpect(jsonPath("$.[0].message", is("some random msg")))
                .andExpect(jsonPath("$.[0].id", is(1L)))
                .andExpect(jsonPath("$.[1].authorName", is("Gosho Peshev")))
                .andExpect(jsonPath("$.[1].message", is("another random msg")));
    }

    @Test
    @WithMockUser(username = "testUsername")
    public void createCommentShouldReturnTheSuccessfullyCreatedComment() throws Exception{
        when(this.commentService.createComment(any()))
                .thenAnswer(interaction -> {
                    CommentDto commentDto = interaction.getArgument(0);
                    return new CommentDisplayView(1L, commentDto.getUsername(), commentDto.getMessage());
                });

        var messageDto = new CommentMessageDto("some random message");
        var objMapper = new ObjectMapper();

        this.mockMvc.perform(get("/api/" + routeId + "/comments")
                .content(objMapper.writeValueAsString(messageDto))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.message", is("some random message")));
    }
}

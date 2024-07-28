package com.atipera.githubApi.controller;


import com.atipera.githubApi.domain.GithubRepository;
import com.atipera.githubApi.domain.GithubUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GithubControllerTest {
@Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldThrowExceptionIfLoginIsNull() throws Exception {
        GithubRepository repo1 = new GithubRepository("Repo1");
        GithubRepository repo2 = new GithubRepository("Repo2");
        GithubUser user = new GithubUser("JoannaWalach1", "", Arrays.asList(repo1, repo2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/github/users/{userLogin}/repos", ""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.detail").value("Repository name must not be null or empty"))
                .andReturn();
    }
    @Test
    void shouldPrintRepositoryNames() throws Exception {
        GithubRepository repo1 = new GithubRepository("Repo1");
        GithubRepository repo2 = new GithubRepository("Repo2");
        GithubUser user = new GithubUser("JoannaWalach1", "JoannaWalach1", Arrays.asList(repo1, repo2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/github/users/{userLogin}/repos", "JoannaWalach1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").value("algoritms"))
                .andReturn();
    }

    @Test
    void shouldPrintBranchesNames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/github/repos/{userLogin}/{nameOfRepo}/branches", "JoannaWalach1", "School-App"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").value("Branch: main, Last Commit SHA: 79d48f428bf8ae8dea8e726f7947613da43510d6"))
                .andReturn();
    }

    @Test
    void shouldThrowExceptionIfLoginOrRepoIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/github/repos/{userLogin}/{nameOfRepo}/branches", "JoannaWalach1", null))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.detail").value("Repository name must not be null or empty"))
                .andReturn();
    }
}
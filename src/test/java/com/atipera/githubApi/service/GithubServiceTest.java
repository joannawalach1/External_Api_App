package com.atipera.githubApi.service;

import com.atipera.githubApi.domain.GithubRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class GithubServiceTest {

        @Mock
        private WebClient.Builder webClientBuilder;

        @Mock
        private WebClient webClient;

        @InjectMocks
        private GithubService githubService;
        private GithubRepository githubRepository1;
        private GithubRepository githubRepository2;
        private String userLogin;
        private List<String> mockRepositories;

        @BeforeEach
        void setUp() {
            githubRepository1 = new GithubRepository();
            githubRepository1.setName("Repo1");
            githubRepository2 = new GithubRepository();
            githubRepository2.setName("Repo1");
            mockRepositories = Arrays.asList("Repo1", "Repo2");

        }

//        @Test
//        void printRepositoryNamesByUser() {
//
//
//            when(githubService.repositoryList(userLogin)).thenReturn(mockRepositories);
//            List<String> repositories = githubService.printRepositoryNamesByUser("JoannaWalach1");
//            assertNotNull(repositories);
//            assertEquals(2, repositories.size());
//            verify(githubService, times(1)).repositoryList(userLogin);
//            verify(githubService, times(1)).printRepositoryNamesByUser(userLogin);
//
//
//        }

        @Test
        void printBranchesNamesByUserAndRepo() {
        }

        @Test
        void repositoryList() {
        }

        @Test
        void branchesListByRepoAndUserWithLastSha() {
        }
    }
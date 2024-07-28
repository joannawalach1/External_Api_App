package com.atipera.githubApi.service;

import com.atipera.githubApi.domain.GithubRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class GithubServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;
    @Mock
    private WebClient.ResponseSpec responseSpecMock;
 @InjectMocks
    private GithubService githubService;
    private GithubRepository githubRepository1;
    private GithubRepository githubRepository2;
    private String userLogin;
    private String nameOfRepo;
    private List<String> mockRepositories;

    @BeforeEach
    void setUp() {
        githubRepository1 = new GithubRepository();
        githubRepository1.setName("Repo1");
        githubRepository2 = new GithubRepository();
        githubRepository2.setName("Repo1");
        mockRepositories = Arrays.asList("Repo1", "Repo2");

    }
//TODO not working
    @Test
    void printRepositoryNamesByUser() {
        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri("/users/{userLogin}/repos", userLogin)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(GithubRepository.class)).thenReturn((Flux<GithubRepository>) mockRepositories);

        List<String> repositoryNames = githubService.printRepositoryNamesByUser(userLogin);

        StepVerifier.create(Flux.fromIterable(repositoryNames))
                .expectNext("Repo1")
                .expectNext("Repo2")
                .verifyComplete();
    }

//TODO not working
    @Test
    void printBranchesNamesByUserAndRepo() {
        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri("/repos/{userLogin}/{nameOfRepo}/branches", userLogin, nameOfRepo)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(GithubRepository.class)).thenReturn((Flux<GithubRepository>) mockRepositories);

        List<String> branchesNames = githubService.printRepositoryNamesByUser(userLogin);

        StepVerifier.create(Flux.fromIterable(branchesNames))
                .expectNext("main")
                .verifyComplete();
    }
}
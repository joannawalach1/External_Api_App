package com.atipera.githubApi.service;

import com.atipera.githubApi.domain.BranchResponse;
import com.atipera.githubApi.domain.GithubBranch;
import com.atipera.githubApi.domain.GithubRepository;
import com.atipera.githubApi.domain.RepositoryResponse;
import com.atipera.githubApi.exception.MissingUserException;
import com.atipera.githubApi.exception.NoRepositoriesFound;
import com.atipera.githubApi.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {
    private static final Logger log = LoggerFactory.getLogger(GithubService.class);
    private final String personalAccessToken = "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";

    private final WebClient webClient;

    @Autowired
    public GithubService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<RepositoryResponse> printRepositoryNamesByUser(String userLogin) throws NotFoundException {
        String url = "/users/{user}/repos";
        List<RepositoryResponse> repositoryResponses = new ArrayList<>();
        if (userLogin == null || userLogin.trim().isEmpty()) {
            throw new MissingUserException("User login must be provided");
        }
        try {
            List<GithubRepository> githubRepositories = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path(url).build(userLogin))
                    .header(HttpHeaders.AUTHORIZATION, "token " + personalAccessToken)
                    .retrieve()
                    .bodyToFlux(GithubRepository.class)
                    .collectList()
                    .block();

            if (githubRepositories.isEmpty()) {
                throw new NotFoundException("User '" + userLogin + "' does not exist");
            }
            if (githubRepositories == null) {
                throw new NoRepositoriesFound("User '" + userLogin + "' has no repositories");
            }
            for (GithubRepository repo : githubRepositories) {
                if (!repo.isFork()) {
                    String repoName = repo.getName();
                    List<BranchResponse> branches = printBranchesNamesByUserAndRepo(userLogin, repoName);
                    RepositoryResponse repoResponse = new RepositoryResponse();
                    repoResponse.setRepositoryName(repoName);
                    repoResponse.setOwnerLogin(userLogin);
                    repoResponse.setBranches(branches);
                    repositoryResponses.add(repoResponse);
                }
            }
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("User '" + userLogin + "' does not exist");
            }
            throw e;
        }
        return repositoryResponses;
    }

    public List<BranchResponse> printBranchesNamesByUserAndRepo(String userLogin, String nameOfRepo) throws NotFoundException {
        String url = "/repos/{user}/{repo}/branches";
        try {
            List<GithubBranch> githubBranches = webClient.get()
                            .uri(uriBuilder -> uriBuilder.path(url).build(userLogin, nameOfRepo))
                            .header(HttpHeaders.AUTHORIZATION, "token " + personalAccessToken)
                            .retrieve()
                            .bodyToFlux(GithubBranch.class)
                            .collectList()
                            .block();
            return githubBranches.stream()
                    .map(branch -> {
                        BranchResponse branchResponse = new BranchResponse();
                        branchResponse.setBranchName(branch.getName());
                        branchResponse.setLastCommitSha(branch.getCommit().getSha());
                        return branchResponse;
                    })
                    .collect(Collectors.toList());
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("User '" + userLogin + "' or repository '" + nameOfRepo + "' does not exist");
            }
            throw e;
        }
    }
}

package com.atipera.githubApi.service;

import com.atipera.githubApi.domain.GithubBranch;
import com.atipera.githubApi.domain.GithubRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {
    private static final Logger log = LoggerFactory.getLogger(GithubService.class);

    private final WebClient webClient;

    @Autowired
    public GithubService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<String> printRepositoryNamesByUser(String userLogin) {
        List<String> repositoryNames = repositoryList(userLogin);
        repositoryNames.forEach(name -> log.info("Repository Name: {}", name));
        return repositoryNames;
    }

    public List<String> printBranchesNamesByUserAndRepo(String userLogin, String nameOfRepo) {
        List<String> branchesNames = branchesListByRepoAndUserWithLastSha(userLogin, nameOfRepo);
        branchesNames.forEach(name -> log.info("Branch Name: {}", name));
        return branchesNames;
    }


    public List<String> repositoryList(String userLogin) {
        String url = "/users/{user}/repos";
        try {
            List<GithubRepository> githubRepositories =
                    webClient
                            .get()
                            .uri(uriBuilder -> uriBuilder.path(url).build(userLogin))
                            .retrieve()
                            .bodyToFlux(GithubRepository.class)
                            .doOnNext(repo -> log.info("Fetched repository: {}", repo))
                            .collectList()
                            .block();
            if (githubRepositories != null) {
                log.info("Repositories fetched: {}", githubRepositories);
            } else if (githubRepositories == null || githubRepositories.isEmpty()) {
                log.warn("User" + userLogin + "doesn't have any repositories");
                return Collections.emptyList();
            }

            return githubRepositories.stream()
                    .map(GithubRepository::getName)
                    .collect(Collectors.toList());
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info("Użytkownik " + userLogin + "doesn't exist");
            } else {
                log.info("Error" + e.getStatusCode() + e.getResponseBodyAsString());
            }
            return Collections.emptyList();
        } catch (Exception e) {
            log.info("The other error:" + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<String> branchesListByRepoAndUserWithLastSha(String userLogin, String nameOfRepo) {
        String url = "/repos/{user}/{repo}/branches";
        try {
            List<GithubBranch> githubBranches = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder.path(url).build(userLogin, nameOfRepo))
                    .retrieve()
                    .bodyToFlux(GithubBranch.class)
                    .doOnNext(branch -> log.info("Fetched branch: {} for repo: {}, user: {}, last commit SHA: {}",
                            branch.getName(), nameOfRepo, userLogin, branch.getCommit() != null ? branch.getCommit().getSha() : "null"))
                    .collectList()
                    .block();

            if (githubBranches == null || githubBranches.isEmpty()) {
                log.warn("Repository {} - {} doesn't have any branches", nameOfRepo, userLogin);
                return Collections.emptyList();
            }

            log.info("Branches for repository {} - {} fetched: {}", nameOfRepo, userLogin, githubBranches);
            return githubBranches.stream()
                    .map(branch -> "Branch: " + branch.getName() + ", Last Commit SHA: " + (branch.getCommit() != null ? branch.getCommit().getSha() : "null"))
                    .collect(Collectors.toList());
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info("User {} or repository {} doesn't exist", userLogin, nameOfRepo);
            } else {
                log.error("Error fetching branches: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            }
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Other error occurred: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}

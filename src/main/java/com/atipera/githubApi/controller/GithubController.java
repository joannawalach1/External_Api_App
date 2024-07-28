package com.atipera.githubApi.controller;

import com.atipera.githubApi.exception.GithubUserNotFound;
import com.atipera.githubApi.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/github")
@Validated
public class GithubController {

    private final GithubService githubService;
@Autowired
    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

@GetMapping("users/{userLogin}/repos")
    public List<String> printRepositoryNames(@PathVariable String userLogin) {
    return githubService.printRepositoryNamesByUser(userLogin);
}

    @GetMapping("repos/{userLogin}/{nameOfRepo}/branches")
    public List<String> printBranchesNames(@PathVariable String userLogin, @PathVariable String nameOfRepo) throws GithubUserNotFound {
        return githubService.printBranchesNamesByUserAndRepo(userLogin, nameOfRepo);
    }
}

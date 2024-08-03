package com.atipera.githubApi.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RepositoryResponse {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchResponse> branches;

    @Getter
    @Setter
    public static class ErrorResponse {
        private int status;
        private String message;
    }
}

package com.atipera.githubApi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchResponse {
    private String lastCommitSha;
    private String branchName;


}

package com.atipera.githubApi.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class GithubUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String login;
    @OneToMany(mappedBy = "GithubUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GithubRepository> repositories = new ArrayList<>();

    public <T> GithubUser(String username, String login, List<T> repositories) {
    }
}

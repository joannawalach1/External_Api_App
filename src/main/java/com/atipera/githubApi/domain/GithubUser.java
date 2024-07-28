package com.atipera.githubApi.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "Username is mandatory")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String username;
    @NotBlank(message = "Login is mandatory")
    private String login;
    @OneToMany(mappedBy = "GithubUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GithubRepository> repositories = new ArrayList<>();

    public <T> GithubUser(String username, String login, List<T> repositories) {
    }
}

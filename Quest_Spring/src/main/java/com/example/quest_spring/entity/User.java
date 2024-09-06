package com.example.quest_spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements AbstractEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The 'name' field must not be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "The 'surname' field must not be empty")
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotEmpty(message = "The 'login' field must not be empty")
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @NotEmpty(message = "The 'email' field must not be empty")
    @Column(name = "email", nullable = false)
    private String email;

    @NotEmpty(message = "The 'password' field must not be empty")
    @Column(name = "password", nullable = false)
    @ToString.Exclude
    private String password;

    private ERole role=ERole.USER_ROLE;
}

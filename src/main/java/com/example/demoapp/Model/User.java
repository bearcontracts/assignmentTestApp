package com.example.demoapp.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true, nullable = false, name = "user_name")
    @NotBlank(message="Username must not be blank")
    @Size(min=3, message="Username must be at least 3 characters long")
    private String username;

    @Column(unique = true, nullable = false, name = "password")
    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    private String password;

    private Integer balance;

    private String token;
    @Column(name = "deposit_id")
    private String depositId;

    @ManyToMany
    @JoinTable(name = "user_game",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "game_id"))
    private Set<Game> games = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Deposit> deposits = new ArrayList<>();
}




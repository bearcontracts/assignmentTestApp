package com.example.demoapp.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    private String username;

    @Column(unique = true, nullable = false, name = "password")
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




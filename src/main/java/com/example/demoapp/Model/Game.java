package com.example.demoapp.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String title;

    private int price;

    @ManyToMany(mappedBy = "games", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Set<User> persons = new HashSet<>();
}

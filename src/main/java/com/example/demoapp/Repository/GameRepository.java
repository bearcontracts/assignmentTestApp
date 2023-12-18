package com.example.demoapp.Repository;

import com.example.demoapp.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Boolean existsGameByName(String name);

    Optional<Game> findByGameId(Long id);
}

package com.example.demoapp.Repository;

import com.example.demoapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
       Optional<User> findByUsername(String username);

       Optional<User> findByUsernameAndPassword(String username, String password);

       Boolean existsByUsername(String username);

       Optional<User> findUserByToken(String token);

       Optional<User> findByDepositId(String depositId);
}

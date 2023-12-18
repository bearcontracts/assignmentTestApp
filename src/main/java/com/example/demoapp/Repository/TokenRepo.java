package com.example.demoapp.Repository;


import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo {

    String generateTokenForUser(String username);
    boolean isTokenValid(String token);
}

package com.example.demoapp.Model;

import com.example.demoapp.Repository.TokenRepo;
import com.example.demoapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import java.util.Optional;
import java.util.UUID;

@Configuration
public class TokenInit implements TokenRepo {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String generateTokenForUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        String token = UUID.randomUUID().toString();
        if(user.isPresent()){
            User user1 = user.get();
            user1.setToken(token);
            userRepository.save(user1);
        }
        return token;
    }

    @Override
    public boolean isTokenValid(String token) {
        Optional<User> checkToken = userRepository.findUserByToken(token);
        return checkToken.isPresent();
    }
}

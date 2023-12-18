package com.example.demoapp.Service;

import com.example.demoapp.DTO.Request.GameCreationRequest;
import com.example.demoapp.DTO.Request.GamePurchaseRequest;
import com.example.demoapp.Model.Game;
import com.example.demoapp.Model.User;
import com.example.demoapp.Repository.GameRepository;
import com.example.demoapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;
    public Boolean registerGame(GameCreationRequest request){
        return !gameRepository.existsGameByName(request.getName()) && saveGame(request);
    }
    private Boolean saveGame(GameCreationRequest request){
        try {
            Game game = new Game();
            game.setName(request.getName());
            game.setPrice(request.getPrice());
            game.setTitle(request.getTitle());

            gameRepository.save(game);

            return Boolean.TRUE;
        } catch (Exception e){
            return Boolean.FALSE;
        }
    }

    public User buyGames(GamePurchaseRequest request){
        Long game1 = request.getGameId();
        Optional<Game> findGame = gameRepository.findByGameId(game1);
        Optional<User> findUser = userRepository.findByUsername(request.getUsername());
        if(findGame.isPresent() && findUser.isPresent()){
            Game game = findGame.get();
            User user = findUser.get();

            int gamePrice = game.getPrice();
            int userBal = user.getBalance();

            if(userBal >= gamePrice){
                if(user.getGames() == null){
                    user.setGames(new HashSet<>());
                }
                user.getGames().add(game);

                int newBalance = user.getBalance() - game.getPrice();
                user.setBalance(newBalance);

                userRepository.save(user);
                return user;
            } else {
                return null;
            }
        } else return null;

    }
}

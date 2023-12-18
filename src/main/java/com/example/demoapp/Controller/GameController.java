package com.example.demoapp.Controller;

import com.example.demoapp.DTO.Request.GameCreationRequest;
import com.example.demoapp.DTO.Request.GamePurchaseRequest;
import com.example.demoapp.DTO.Request.UserRequest;
import com.example.demoapp.DTO.Response.GameCreationResponse;
import com.example.demoapp.DTO.Response.GamePurchaseResponse;
import com.example.demoapp.Model.TokenInit;
import com.example.demoapp.Model.User;
import com.example.demoapp.Repository.UserRepository;
import com.example.demoapp.Service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
@Slf4j
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private TokenInit tokenInit;

    @PostMapping("/game/create")
    public ResponseEntity<?> createGame(@RequestHeader("token") String token, @RequestBody GameCreationRequest request){
        if (!tokenInit.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        GameCreationResponse gg = new GameCreationResponse();
        boolean isCreated = gameService.registerGame(request);

        if(isCreated){
            gg.setGameId(UUID.randomUUID().toString());
            return ResponseEntity.status(HttpStatus.OK).body(gg);
        }
        else return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate game");
    }

    @PostMapping("/game/buy")
    public ResponseEntity<?> buyGame(@RequestHeader("token") String token, @RequestBody GamePurchaseRequest request){
        if (!tokenInit.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        GamePurchaseResponse gg = new GamePurchaseResponse();
        User gameUser = gameService.buyGames(request);
        if(gameUser != null){
            gg.setBalance(gameUser.getBalance());
            gg.setGameId(request.getGameId());
            return ResponseEntity.status(HttpStatus.OK).body(gg);
        }
        else return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate game");
    }
}

package com.example.demoapp.Controller;

import com.example.demoapp.DTO.Request.UserRequest;
import com.example.demoapp.DTO.Response.AuthenticationResponse;
import com.example.demoapp.Model.TokenInit;
import com.example.demoapp.Model.User;
import com.example.demoapp.Repository.UserRepository;
import com.example.demoapp.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenInit tokenInit;

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody UserRequest request){

        boolean isRegistered = userService.registerPerson(request);

        if(isRegistered){
            return ResponseEntity.status(HttpStatus.OK).body("Registration successful");
        }
        else return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate user");
    }

    @PostMapping("/token")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest request){
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            Optional<User> users = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
            if(users.isPresent()){
                User user = users.get();

                authenticationResponse.setToken((tokenInit.generateTokenForUser(request.getUsername())));
                authenticationResponse.setBalance(user.getBalance());
                return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
    }

}

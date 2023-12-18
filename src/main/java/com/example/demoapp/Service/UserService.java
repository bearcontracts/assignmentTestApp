package com.example.demoapp.Service;

import com.example.demoapp.DTO.Request.UserRequest;
import com.example.demoapp.Model.User;
import com.example.demoapp.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Boolean registerPerson(UserRequest request){
        return !userRepository.existsByUsername(request.getUsername()) && savePerson(request);
    }
    private Boolean savePerson(UserRequest request){
        try {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());

            userRepository.save(user);
            return Boolean.TRUE;
        } catch (Exception e){
            return Boolean.FALSE;
        }
    }
}

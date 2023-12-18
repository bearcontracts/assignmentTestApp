package com.example.demoapp.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepositRequest {

    private String username;

    private Integer amount;

}

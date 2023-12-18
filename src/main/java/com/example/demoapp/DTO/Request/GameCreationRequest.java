package com.example.demoapp.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameCreationRequest {

    private String name;

    private String title;

    private Integer price;
}

package com.petapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull
    private String password;
    @NotNull
    private String username;
}
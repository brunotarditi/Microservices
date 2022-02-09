package com.example.authservice.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginUserDto {
    private String userName;
    private String password;
}

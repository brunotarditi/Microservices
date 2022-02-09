package com.example.authservice.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NewUserDto {
    private String userName;
    private String password;
    private String role;
}

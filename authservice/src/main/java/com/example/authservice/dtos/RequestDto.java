package com.example.authservice.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RequestDto {
    private String uri;
    private String method;
}

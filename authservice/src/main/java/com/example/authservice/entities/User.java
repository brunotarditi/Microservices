package com.example.authservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document(collection = "Users")
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "university_sequence";
    @Id
    private long id;
    private String userName;
    private String password;
    private String role;
}

package com.example.universityservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Universities")
public class University {
    @Transient
    public static final String SEQUENCE_NAME = "university_sequence";
    @Id
    private long id;
    private String name;
    private String address;
}

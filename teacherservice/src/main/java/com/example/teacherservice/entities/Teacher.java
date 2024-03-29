package com.example.teacherservice.entities;

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
@Document(collection = "Teachers")
public class Teacher {
    @Transient
    public static final String SEQUENCE_NAME = "teachers_sequence";
    @Id
    private long id;
    private String name;
    private int age;
    private long universityId;
}

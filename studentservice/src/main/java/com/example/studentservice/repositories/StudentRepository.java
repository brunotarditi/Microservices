package com.example.studentservice.repositories;

import com.example.studentservice.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {
    List<Student> findByUniversityId(long universityId);
}

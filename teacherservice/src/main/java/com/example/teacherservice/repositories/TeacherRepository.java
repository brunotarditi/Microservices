package com.example.teacherservice.repositories;

import com.example.teacherservice.entities.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, Long> {
    List<Teacher> findByUniversityId(long universityId);
}

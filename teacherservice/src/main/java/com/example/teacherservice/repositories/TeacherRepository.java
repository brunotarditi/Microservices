package com.example.teacherservice.repositories;

import com.example.teacherservice.entities.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends  CrudRepository<Teacher, Long> {
    List<Teacher> findByUniversityId(long universityId);
}

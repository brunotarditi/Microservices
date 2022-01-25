package com.example.teacherservice.services;

import com.example.teacherservice.entities.Teacher;
import com.example.teacherservice.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findByAll() throws Exception {
        try {
            return (List<Teacher>) this.teacherRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Teacher> findById(Long id) throws Exception {
        try{
            return this.teacherRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Teacher save(Teacher teacher) throws Exception {
        try {
            return this.teacherRepository.save(teacher);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Teacher> findByUniversityId(long universityId) throws Exception {
        try {
            return (List<Teacher>) this.teacherRepository.findByUniversityId(universityId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}

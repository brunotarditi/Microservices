package com.example.studentservice.services;

import com.example.studentservice.entities.Student;
import com.example.studentservice.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findByAll() throws Exception {
        try {
            return (List<Student>) this.studentRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Student> findById(Long id) throws Exception {
        try{
            return this.studentRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Student save(Student student) throws Exception {
        try {
            return this.studentRepository.save(student);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Student> findByUniversityId(long universityId) throws Exception {
        try {
            return (List<Student>) this.studentRepository.findByUniversityId(universityId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}

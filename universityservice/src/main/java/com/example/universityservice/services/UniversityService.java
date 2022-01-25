package com.example.universityservice.services;

import com.example.universityservice.entities.University;
import com.example.universityservice.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> findByAll() throws Exception {
        try {
            return (List<University>) this.universityRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<University> findById(Long id) throws Exception {
        try{
            return this.universityRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public University save(University university) throws Exception {
        try {
            return this.universityRepository.save(university);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}

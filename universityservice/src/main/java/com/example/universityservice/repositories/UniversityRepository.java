package com.example.universityservice.repositories;

import com.example.universityservice.entities.University;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UniversityRepository extends MongoRepository<University, Long> {
}

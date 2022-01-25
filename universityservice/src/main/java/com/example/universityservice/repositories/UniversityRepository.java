package com.example.universityservice.repositories;

import com.example.universityservice.entities.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UniversityRepository extends  CrudRepository<University, Long> {
}

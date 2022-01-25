package com.example.universityservice.controllers;

import com.example.universityservice.entities.University;
import com.example.universityservice.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<University>> getAll() throws Exception {
            List<University> universities = this.universityService.findByAll();
            if (universities.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(universities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getOne(@PathVariable Long id) throws Exception {
        University university = this.universityService.findById(id).orElseGet(null);
            if (university == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(university);

    }

    @PostMapping()
    public ResponseEntity<University> save(@RequestBody University university) throws Exception {
        return ResponseEntity.ok(this.universityService.save(university));
    }

}

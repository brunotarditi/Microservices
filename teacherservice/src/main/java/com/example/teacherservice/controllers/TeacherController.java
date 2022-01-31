package com.example.teacherservice.controllers;

import com.example.teacherservice.entities.Teacher;
import com.example.teacherservice.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAll() throws Exception {
            List<Teacher> teachers = this.teacherService.findByAll();
            if (teachers.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getOne(@PathVariable Long id) throws Exception {
        Teacher teacher = this.teacherService.findById(id).orElse(null);
            if (teacher == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(teacher);

    }

    @PostMapping()
    public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) throws Exception {
        return ResponseEntity.ok(this.teacherService.save(teacher));
    }

    @GetMapping("/byUniversity/{universityId}")
    public ResponseEntity<List<Teacher>> getByUserId(@PathVariable long universityId) throws Exception {
        List<Teacher> teachers = this.teacherService.findByUniversityId(universityId);
        return ResponseEntity.ok(teachers);
    }
}

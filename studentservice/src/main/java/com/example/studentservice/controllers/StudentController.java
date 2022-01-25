package com.example.studentservice.controllers;

import com.example.studentservice.entities.Student;
import com.example.studentservice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() throws Exception {
            List<Student> students = this.studentService.findByAll();
            if (students.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getOne(@PathVariable Long id) throws Exception {
        Student student = this.studentService.findById(id).orElseGet(null);
            if (student == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(student);

    }

    @PostMapping()
    public ResponseEntity<Student> save(@RequestBody Student student) throws Exception {
        return ResponseEntity.ok(this.studentService.save(student));
    }

    @GetMapping("/byUniversity/{universityId}")
    public ResponseEntity<List<Student>> getByUserId(@PathVariable long universityId) throws Exception {
        List<Student> students = this.studentService.findByUniversityId(universityId);
        return ResponseEntity.ok(students);
    }
}

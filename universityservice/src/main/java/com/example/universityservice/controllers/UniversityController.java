package com.example.universityservice.controllers;

import com.example.universityservice.entities.University;
import com.example.universityservice.models.Student;
import com.example.universityservice.models.Teacher;
import com.example.universityservice.services.UniversityService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
        University university = this.universityService.findById(id).orElse(null);
            if (university == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(university);
    }

    @PostMapping()
    public ResponseEntity<University> save(@RequestBody University university) throws Exception {
        return ResponseEntity.ok(this.universityService.save(university));
    }

    @CircuitBreaker(name = "teachersCB", fallbackMethod = "fallbackGetTeachers")
    @GetMapping("/teachers/{universityId}")
    public ResponseEntity<List<Teacher>> getTeachers(@PathVariable long universityId) throws Exception {
        University university = this.universityService.findById(universityId).orElse(null);
        if (university == null)
            return ResponseEntity.notFound().build();
        List<Teacher> teachers = this.universityService.getTeachers(universityId);
        return ResponseEntity.ok(teachers);
    }
    @CircuitBreaker(name = "teachersCB", fallbackMethod = "fallbackSaveTeacher")
    @PostMapping("/saveTeacher/{universityId}")
    public ResponseEntity<Teacher> saveTeacher(@PathVariable long universityId, @RequestBody Teacher teacher) throws Exception {
        if (this.universityService.findById(universityId).isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.universityService.saveTeacher(universityId, teacher));
    }

    @CircuitBreaker(name = "studentsCB", fallbackMethod = "fallbackGetStudents")
    @GetMapping("/students/{universityId}")
    public ResponseEntity<List<Student>> getStudents(@PathVariable long universityId) throws Exception {
        University university = this.universityService.findById(universityId).orElse(null);
        if (university == null)
            return ResponseEntity.notFound().build();
        List<Student> movies = this.universityService.getStudents(universityId);
        return ResponseEntity.ok(movies);
    }

    @CircuitBreaker(name = "studentsCB", fallbackMethod = "fallbackSaveStudent")
    @PostMapping("/saveStudent/{universityId}")
    public ResponseEntity<Student> saveMovie(@PathVariable long universityId, @RequestBody Student student) throws Exception {
        if (this.universityService.findById(universityId).isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.universityService.saveStudent(universityId, student));
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetAll")
    @GetMapping("/getAll/{universityId}")
    public ResponseEntity<Map<String, Object>> getAllWithTeachersAndStudents(@PathVariable long universityId) {
        Map<String, Object> results = this.universityService.getUsersWithTeachersAndStudents(universityId);
        return ResponseEntity.ok(results);
    }

    private ResponseEntity<List<Teacher>> fallbackGetTeachers(@PathVariable long universityId, RuntimeException e){
        return new ResponseEntity("La universidad " + universityId + " tiene a los profesores de vacaciones.", HttpStatus.OK);
    }

    private ResponseEntity<Teacher> fallbackSaveTeacher(@PathVariable long universityId, @RequestBody Teacher teacher, RuntimeException e){
        return new ResponseEntity("La universidad " + universityId + " no puede contratar a otro profesor.", HttpStatus.OK);
    }

    private ResponseEntity<List<Student>> fallbackGetStudents(@PathVariable long universityId, RuntimeException e){
        return new ResponseEntity("La universidad " + universityId + " tiene a los alumnos de vacaciones.", HttpStatus.OK);
    }

    private ResponseEntity<Student> fallbackSaveStudent(@PathVariable long universityId, @RequestBody Student student, RuntimeException e){
        return new ResponseEntity("La universidad " + universityId + " no tiene más lugar para un alumno.", HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> fallbackGetAll(@PathVariable long universityId, RuntimeException e){
        return new ResponseEntity("La universidad " + universityId + " no tiene ni profesores y alumnos por vacaciones.", HttpStatus.OK);
    }
}

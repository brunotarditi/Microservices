package com.example.universityservice.feignclients;

import com.example.universityservice.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "studentservice")
public interface StudentFeignClient {
    @PostMapping("/students")
    Student save(@RequestBody Student student);

    @GetMapping("/students/byUniversity/{universityId}")
    List<Student> getStudents(@PathVariable long universityId);
}

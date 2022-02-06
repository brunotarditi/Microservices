package com.example.universityservice.feignclients;

import com.example.universityservice.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "studentservice")
@RequestMapping("/teachers")
public interface StudentFeignClient {
    @PostMapping()
    Student save(@RequestBody Student student);

    @GetMapping("/byUniversity/{universityId}")
    List<Student> getStudents(@PathVariable long universityId);
}

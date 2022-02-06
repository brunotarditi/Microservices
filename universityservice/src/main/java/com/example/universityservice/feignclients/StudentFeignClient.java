package com.example.universityservice.feignclients;

import com.example.universityservice.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "studentservice")
public interface StudentFeignClient {
    @PostMapping()
    Student save(@RequestBody Student student);

    @GetMapping("/byUniversity/{universityId}")
    List<Student> getStudents(@PathVariable long universityId);
}

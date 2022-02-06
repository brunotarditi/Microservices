package com.example.universityservice.feignclients;

import com.example.universityservice.models.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "teacherservice")
public interface TeacherFeignClient {
    @PostMapping("/teachers")
    Teacher save(@RequestBody Teacher teacher);

    @GetMapping("/teachers/byUniversity/{universityId}")
    List<Teacher> getTeachers(@PathVariable long universityId);
}

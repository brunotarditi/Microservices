package com.example.universityservice.feignclients;

import com.example.universityservice.models.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "teacherservice")
public interface TeacherFeignClient {
    @PostMapping()
    Teacher save(@RequestBody Teacher teacher);

    @GetMapping("/byUniversity/{universityId}")
    List<Teacher> getTeachers(@PathVariable long universityId);
}

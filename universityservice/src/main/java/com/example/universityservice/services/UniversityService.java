package com.example.universityservice.services;

import com.example.universityservice.entities.University;
import com.example.universityservice.feignclients.StudentFeignClient;
import com.example.universityservice.feignclients.TeacherFeignClient;
import com.example.universityservice.models.Student;
import com.example.universityservice.models.Teacher;
import com.example.universityservice.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;
    private final RestTemplate restTemplate;
    private final TeacherFeignClient teacherFeignClient;
    private final StudentFeignClient studentFeignClient;

    @Autowired
    public UniversityService(UniversityRepository universityRepository, RestTemplate restTemplate, TeacherFeignClient teacherFeignClient, StudentFeignClient studentFeignClient) {
        this.universityRepository = universityRepository;
        this.restTemplate = restTemplate;
        this.teacherFeignClient = teacherFeignClient;
        this.studentFeignClient = studentFeignClient;
    }



    public List<University> findByAll() throws Exception {
        try {
            return (List<University>) this.universityRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<University> findById(Long id) throws Exception {
        try{
            return this.universityRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public University save(University university) throws Exception {
        try {
            return this.universityRepository.save(university);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Teacher> getTeachers(long byUniversity){
        return restTemplate.getForObject("http://localhost:8002/teachers/byUniversity/" + byUniversity, List.class);

    }

    public List<Student> getStudents(long byUniversity){
        return restTemplate.getForObject("http://localhost:8003/students/byUniversity/" + byUniversity, List.class);
    }

    public Teacher saveTeacher(long universityId, Teacher teacher){
        teacher.setUniversityId(universityId);
        return this.teacherFeignClient.save(teacher);
    }

    public Student saveStudent(long universityId, Student student){
        student.setUniversityId(universityId);
        return this.studentFeignClient.save(student);
    }

    public Map<String, Object> getUsersWithTeachersAndStudents(long universityId){
        Map<String, Object> results = new HashMap<>();
        University university = this.universityRepository.findById(universityId).orElse(null);
        if (university == null) {
            results.put("Mensaje", "No existe la universidad.");
            return results;
        }
        results.put("University", university);
        List<Teacher> teachers = teacherFeignClient.getTeachers(universityId);
        if (teachers.isEmpty())
            results.put("Teachers", "No existen teachers");
        else
            results.put("Teachers", teachers);

        List<Student> students = studentFeignClient.getStudents(universityId);
        if (students.isEmpty())
            results.put("Students", "No existen students");
        else
            results.put("Students", students);
        return results;
    }

}

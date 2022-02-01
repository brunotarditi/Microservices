package com.example.universityservice.services;

import com.example.universityservice.entities.DbSequence;
import com.example.universityservice.entities.University;
import com.example.universityservice.feignclients.StudentFeignClient;
import com.example.universityservice.feignclients.TeacherFeignClient;
import com.example.universityservice.models.Student;
import com.example.universityservice.models.Teacher;
import com.example.universityservice.repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.*;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;
    private final RestTemplate restTemplate;
    private final TeacherFeignClient teacherFeignClient;
    private final StudentFeignClient studentFeignClient;
    private final MongoOperations mongoOperations;
    @Autowired
    public UniversityService(UniversityRepository universityRepository, RestTemplate restTemplate, TeacherFeignClient teacherFeignClient, StudentFeignClient studentFeignClient, MongoOperations mongoOperations) {
        this.universityRepository = universityRepository;
        this.restTemplate = restTemplate;
        this.teacherFeignClient = teacherFeignClient;
        this.studentFeignClient = studentFeignClient;
        this.mongoOperations = mongoOperations;
    }

    public List<University> findByAll() throws Exception {
        try {
            return this.universityRepository.findAll();
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
            university.setId(generateSequence());
            return this.universityRepository.save(university);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Teacher> getTeachers(long byUniversity){
        return restTemplate.getForObject("http://teacherservice/teachers/byUniversity/" + byUniversity, List.class);

    }

    public List<Student> getStudents(long byUniversity){
        return restTemplate.getForObject("http://studentservice/students/byUniversity/" + byUniversity, List.class);
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

    private long generateSequence(){
        DbSequence sequence = mongoOperations.findAndModify(query(where("_id").is(University.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(sequence) ? sequence.getSeq() : 1;
    }

}

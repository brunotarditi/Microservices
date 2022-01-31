package com.example.studentservice.services;

import com.example.studentservice.entities.DbSequence;
import com.example.studentservice.entities.Student;
import com.example.studentservice.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final MongoOperations mongoOperations;
    @Autowired
    public StudentService(StudentRepository studentRepository, MongoOperations mongoOperations) {
        this.studentRepository = studentRepository;
        this.mongoOperations = mongoOperations;
    }

    public List<Student> findByAll() throws Exception {
        try {
            return this.studentRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Student> findById(Long id) throws Exception {
        try{
            return this.studentRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Student save(Student student) throws Exception {
        try {
            student.setId(generateSequence());
            return this.studentRepository.save(student);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Student> findByUniversityId(long universityId) throws Exception {
        try {
            return this.studentRepository.findByUniversityId(universityId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private long generateSequence(){
        DbSequence sequence = mongoOperations.findAndModify(query(where("_id").is(Student.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(sequence) ? sequence.getSeq() : 1;
    }

}

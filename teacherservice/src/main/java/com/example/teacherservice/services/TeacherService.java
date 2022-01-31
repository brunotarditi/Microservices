package com.example.teacherservice.services;

import com.example.teacherservice.entities.DbSequence;
import com.example.teacherservice.entities.Teacher;
import com.example.teacherservice.repositories.TeacherRepository;
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
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final MongoOperations mongoOperations;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository, MongoOperations mongoOperations) {
        this.teacherRepository = teacherRepository;
        this.mongoOperations = mongoOperations;
    }

    public List<Teacher> findByAll() throws Exception {
        try {
            return this.teacherRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Teacher> findById(Long id) throws Exception {
        try{
            return this.teacherRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Teacher save(Teacher teacher) throws Exception {
        try {
            teacher.setId(generateSequence());
            return this.teacherRepository.save(teacher);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Teacher> findByUniversityId(long universityId) throws Exception {
        try {
            return this.teacherRepository.findByUniversityId(universityId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private long generateSequence(){
        DbSequence sequence = mongoOperations.findAndModify(query(where("_id").is(Teacher.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(sequence) ? sequence.getSeq() : 1;
    }

}

package com.example.authservice.services;

import com.example.authservice.dtos.LoginUserDto;
import com.example.authservice.dtos.RequestDto;
import com.example.authservice.dtos.TokenDto;
import com.example.authservice.dtos.NewUserDto;
import com.example.authservice.entities.DbSequence;
import com.example.authservice.entities.User;
import com.example.authservice.repositories.UserRepository;
import com.example.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final MongoOperations mongoOperations;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, MongoOperations mongoOperations) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.mongoOperations = mongoOperations;
    }

    public User save(NewUserDto newUserDto){
        Optional<User> userOptional = userRepository.findByUserName(newUserDto.getUserName());
        if (userOptional.isPresent())
            return null;
        String password = passwordEncoder.encode(newUserDto.getPassword());
        User user = User.builder()
                .id(generateSequence())
                .userName(newUserDto.getUserName())
                .password(password)
                .role(newUserDto.getRole())
                .build();
        return userRepository.save(user);
    }

    public TokenDto login (LoginUserDto loginUserDto){
        Optional<User> userOptional = userRepository.findByUserName(loginUserDto.getUserName());
        if (!userOptional.isPresent())
            return null;
        if (passwordEncoder.matches(loginUserDto.getPassword(), userOptional.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(userOptional.get()));
        else
            return null;
    }

    public TokenDto validate(String token, RequestDto dto){
        if (!jwtProvider.validate(token, dto))
            return null;
        String userName = jwtProvider.getUserNameFromToken(token);
        if (!userRepository.findByUserName(userName).isPresent())
            return null;
        return new TokenDto(token);
    }

    private long generateSequence(){
        DbSequence sequence = mongoOperations.findAndModify(query(where("_id").is(User.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(sequence) ? sequence.getSeq() : 1;
    }
}

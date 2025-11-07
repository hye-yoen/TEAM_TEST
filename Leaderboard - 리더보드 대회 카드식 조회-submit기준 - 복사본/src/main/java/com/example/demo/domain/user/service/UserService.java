package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.UserDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public Long compRegisstrarion1(UserDto dto) throws Exception{
        //dto -> entity
        User user = User.builder()
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .build();
        userRepository.save(user);
        return user.getUserId();
    }






}




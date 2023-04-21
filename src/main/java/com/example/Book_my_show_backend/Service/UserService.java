package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Models.UserEntity;
import com.example.Book_my_show_backend.Repository.UserRepository;
import com.example.Book_my_show_backend.RequestDtos.UserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto){

        UserEntity userEntity = UserEntity.builder().name(userRequestDto.getName())
                .mobile(userRequestDto.getMobile()).build();

        try{
            userRepository.save(userEntity);
        }catch(Exception e){
            return "User could not be Added";
        }
        return "User Added Successfully";

    }

    public List<UserEntity> getUser(String name){

        List<UserEntity> usersList = userRepository.findByName(name);

        return usersList;

    }

    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }

}

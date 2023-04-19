package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Models.UserEntity;
import com.example.Book_my_show_backend.Repository.UserRepository;
import com.example.Book_my_show_backend.RequestDtos.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}

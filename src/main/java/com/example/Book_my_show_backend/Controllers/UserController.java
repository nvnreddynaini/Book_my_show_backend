package com.example.Book_my_show_backend.Controllers;

import com.example.Book_my_show_backend.Models.UserEntity;
import com.example.Book_my_show_backend.RequestDtos.UserRequestDto;
import com.example.Book_my_show_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody UserRequestDto userRequestDto){

        return userService.createUser(userRequestDto);

    }

    //Find User By Name
    @GetMapping("/get/{name}")
    public List<UserEntity> getUser(@PathVariable String name){
        return userService.getUser(name);
    }


    //Find all Users
    @GetMapping("/findall")
    public List<UserEntity> findAllUsers(){
        return userService.findAllUsers();
    }

}

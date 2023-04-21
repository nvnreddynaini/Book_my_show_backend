package com.example.Book_my_show_backend.Controllers;

import com.example.Book_my_show_backend.Models.TheaterEntity;
import com.example.Book_my_show_backend.RequestDtos.TheaterRequestDto;
import com.example.Book_my_show_backend.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto){

        return theaterService.createTheater(theaterRequestDto);

    }

    //Get theaters by theater id
//    @GetMapping("/get/{id}")
//    public TheaterEntity getTheater(@PathVariable int id){
//
//        return theaterService.getTheater(id);
//
//    }


    //Get all theaters
//    @GetMapping("/getall")
//    public List<TheaterEntity> getAllTheaters(){
//
//        return theaterService.getAllTheaters();
//
//    }

}

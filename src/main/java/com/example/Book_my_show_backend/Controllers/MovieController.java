package com.example.Book_my_show_backend.Controllers;

import com.example.Book_my_show_backend.Models.MovieEntity;
import com.example.Book_my_show_backend.RequestDtos.MovieRequestDto;
import com.example.Book_my_show_backend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieRequestDto movieRequestDto){

        return movieService.addMovie(movieRequestDto);

    }

    //Get Movie By Name
    @GetMapping("/get/{movieName}")
    public MovieEntity getMovie(@PathVariable String movieName){

        return movieService.getMovie(movieName);

    }



}

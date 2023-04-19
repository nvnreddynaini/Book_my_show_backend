package com.example.Book_my_show_backend.RequestDtos;

import lombok.Data;

import java.util.Date;

@Data
public class MovieRequestDto {

    private String movieName;

    private int duration;

    private Date releaseDate;

}

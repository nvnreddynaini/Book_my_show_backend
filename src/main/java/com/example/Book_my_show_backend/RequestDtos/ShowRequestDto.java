package com.example.Book_my_show_backend.RequestDtos;

import com.example.Book_my_show_backend.Models.MovieEntity;
import com.example.Book_my_show_backend.Models.TheaterEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDto {

    private LocalDate showDate;

    private LocalTime showTime;

    private double multiplier;

    private String movieName;

    private int theaterId;

}

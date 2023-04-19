package com.example.Book_my_show_backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theater")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String address;

    //List of Shows
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<ShowEntity> listOfShows;


    //List of TheaterSeats
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeatEntity> theaterSeatEntityList;

}

package com.example.Book_my_show_backend.Models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String alloted_seats;

    private int amount;

    private Date booked_at;

    //User
    @ManyToOne
    @JoinColumn
    private UserEntity user;

    //Show
    @ManyToOne
    @JoinColumn
    private ShowEntity show;


    //List of show seats
    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> bookedSeats;


}

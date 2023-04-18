package com.example.Book_my_show_backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Shows")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    //Movies
    @ManyToOne
    @JoinColumn
    private MovieEntity movie;

    //Theater
    @ManyToOne
    @JoinColumn
    private TheaterEntity theater;

    //List of show Seats
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> listOfSeats;


    //List of Tickets
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    private List<TicketEntity> listOfTickets;



}

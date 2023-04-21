package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Models.ShowEntity;
import com.example.Book_my_show_backend.Models.ShowSeatEntity;
import com.example.Book_my_show_backend.Models.TicketEntity;
import com.example.Book_my_show_backend.Models.UserEntity;
import com.example.Book_my_show_backend.Repository.ShowRepository;
import com.example.Book_my_show_backend.Repository.TicketRepository;
import com.example.Book_my_show_backend.Repository.UserRepository;
import com.example.Book_my_show_backend.RequestDtos.BookTicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception{

        List<String> requestedSeats =  bookTicketRequestDto.getRequestSeats();

        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();

        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        //Get Show seats from showEntity
        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();

        //validate allocation of seats requested by user

        List<ShowSeatEntity> bookedSeats = new ArrayList<>();

        for(ShowSeatEntity showSeat : showSeats){

            String seatNo = showSeat.getSeatNo();

            if(showSeat.isBooked()==false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }

        }

        //Failure
        if(bookedSeats.size() != requestedSeats.size()){
            throw new Exception("Requested seats are not available");
        }

        //Now bookedSeats actually contains the booked seats.
        //Success
        TicketEntity ticketEntity = new TicketEntity();

        double totalAmount = 0;
        double multiplier = showEntity.getMultiplier();
        int rate = 0;
        String allotedSeats = "";

        //Calculating the amount and setting booked status
        for(ShowSeatEntity bookedSeat : bookedSeats){

            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);

            String seatNo = bookedSeat.getSeatNo();
            allotedSeats = allotedSeats + seatNo + " ";
            if(seatNo.charAt(0)=='1'){
                rate = 100;
            }else{
                rate = 200;
            }

            totalAmount += (multiplier * rate);

        }

        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int)totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAlloted_seats(allotedSeats);

        //Bi-directional mapping part is pending

        ticketRepository.save(ticketEntity);

        return "Successfully created a ticket";
    }

}

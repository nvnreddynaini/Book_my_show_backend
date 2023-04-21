package com.example.Book_my_show_backend.RequestDtos;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequestDto {

    private List<String> requestSeats;

    private int showId;

    private int userId;

}

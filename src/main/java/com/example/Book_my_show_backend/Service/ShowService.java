package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Models.*;
import com.example.Book_my_show_backend.Repository.*;
import com.example.Book_my_show_backend.RequestDtos.ShowRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowRequestDto showRequestDto){

        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDto.getShowDate()).
                showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();

        //we need to get the movie repo
        MovieEntity movieEntity = movieRepository.findBymovieName(showRequestDto.getMovieName());

        //we need to get the theater repo
        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        showEntity.setMovie(movieEntity);
        showEntity.setTheater(theaterEntity);

        //Because of Bi-directional Mapping
        //movieEntity.getShowEntityList().add(showEntity);
        List<ShowEntity> currShowList = movieEntity.getShowEntityList();
        currShowList.add(showEntity);
        movieEntity.setShowEntityList(currShowList);
        //movieRepository.save(movieEntity);

        //Because of Bi-directional Mapping
        //theaterEntity.getListOfShows().add(showEntity);
        List<ShowEntity> currListShows = theaterEntity.getListOfShows();
        currListShows.add(showEntity);
        theaterEntity.setListOfShows(currListShows);
        //theaterRepository.save(theaterEntity);


        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());

        showEntity.setListOfSeats(seatEntityList);

        //After Getting the list
        //For each Show Seat : we need to set the show
        for(ShowSeatEntity showSeat : seatEntityList){
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        //showRepository.save(showEntity); //This doesn't need to be called because parent save is called.

        theaterRepository.save(theaterEntity);

        return "Show Added Successfully";

    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){

        List<ShowSeatEntity> seats = new ArrayList<>();

        for(TheaterSeatEntity theaterSeat : theaterSeatEntityList){

            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatType(theaterSeat.getSeatType())
                    .seatNo(theaterSeat.getSeatNo()).build();

            seats.add(showSeat);

        }

        showSeatsRepository.saveAll(seats);

        return seats;

    }

}

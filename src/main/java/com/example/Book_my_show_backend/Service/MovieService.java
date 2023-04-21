package com.example.Book_my_show_backend.Service;

import com.example.Book_my_show_backend.Models.MovieEntity;
import com.example.Book_my_show_backend.Repository.MovieRepository;
import com.example.Book_my_show_backend.RequestDtos.MovieRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


    public String addMovie(MovieRequestDto movieRequestDto){

        MovieEntity movie = MovieEntity.builder().movieName(movieRequestDto.getMovieName())
                .duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();

        try{
            movieRepository.save(movie);
        }catch (Exception e){
            return "Movie could not be Added";
        }
        return "Movie Added Successfully";
    }

    public MovieEntity getMovie(String movieName){

        return movieRepository.findBymovieName(movieName);

    }

}

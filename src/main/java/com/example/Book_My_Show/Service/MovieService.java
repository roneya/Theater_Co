package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Controller.MovieController;
import com.example.Book_My_Show.Convertors.MovieConvertors;
import com.example.Book_My_Show.Entity.MovieEntity;
import com.example.Book_My_Show.Entity.TicketEntity;
import com.example.Book_My_Show.EntryDto.MovieEntryDto;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TicketRepository ticketRepository;
    public String addMovie(MovieEntryDto movieEntryDto) throws Exception{
        MovieEntity movieEntity = MovieConvertors.convertEntryDtoToEntity(movieEntryDto);
        movieRepository.save(movieEntity);
        return "Movie Added Successfully";
    }
    public MovieEntity getMovie(String name){
        return movieRepository.getMovie(name);
    }
    public List<String> getMovieRating(double rate){
    return movieRepository.getMovieRating(rate);
    }

    public int getMovieRevenue(String name){
        List<TicketEntity> ticketEntityList = ticketRepository.findAll();
        int ans =0;
        for(TicketEntity ticketEntity: ticketEntityList){
            if(ticketEntity.getMovieName().equals(name)){
                ans+=ticketEntity.getTotalAmount();
            }
        }
        return ans;
    }

    public List<String> getAll( ){ //movie names

        return movieRepository.getAll();
    }

}

package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.Entity.MovieEntity;
import com.example.Book_My_Show.EntryDto.MovieEntryDto;
import com.example.Book_My_Show.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto)
    {
            try {
                String result = movieService.addMovie(movieEntryDto);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }catch (Exception e){
                String response = "Movie not added";
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
    }
    @GetMapping("get")
    public MovieEntity getMovie(@RequestParam String name){

        return movieService.getMovie(name);
    }

    @GetMapping("getMovieRating")
    public List<String> getMovieRating (@RequestParam double rate){
        return movieService.getMovieRating(rate);
    }

    @GetMapping("earning")
    public int getMovieRevenue( @RequestParam String name){

        return movieService.getMovieRevenue(name);
    }
    @GetMapping("getAll")
    public List<String> getAll( ){

        return movieService.getAll();
    }



}

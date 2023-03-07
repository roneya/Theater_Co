package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDto.TheaterEntryDto;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("theaters")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @Autowired
    private TheaterRepository theaterRepository;

    @PostMapping("add")
    public ResponseEntity addTheater(@RequestBody TheaterEntryDto theaterEntryDto){

        try {
            String result = theaterService.addTheater(theaterEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e)
        {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("getall")  //list of theaters name and location
    public List<String>getAll(){
        return theaterService.getAll();
    }
    @GetMapping("shows")
    public List<String> shows(@RequestParam String name ){
    return theaterService.shows(name);
    }

}

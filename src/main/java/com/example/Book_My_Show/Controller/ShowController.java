package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDto.ShowEntryDto;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shows")
public class ShowController {

    @Autowired
    ShowService showService;
    @Autowired
    private ShowRepository showRepository;

    @PostMapping("add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){
    String result = showService.addShow(showEntryDto);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
}

    @GetMapping("showsOfMovie")
    public List<String> showsOfMovie(@RequestParam String name){
    return showService.showsOfMovie(name);
    }
}

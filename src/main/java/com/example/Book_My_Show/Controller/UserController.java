package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDto.UserEntryDto;
import com.example.Book_My_Show.Repository.UserRepository;
import com.example.Book_My_Show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto) {
        try {
            String response = userService.addUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            String result = "User could not be created";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }


    }
    @DeleteMapping("delete") //delete wrt mobile
    public String deleteUser(@RequestParam String mob){
        return userService.deleteUser(mob);
    }

    @GetMapping("getAll")
    public List<String> getAll() {
        return userService.getAll();
    }



}
package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDto.TicketEntryDto;
import com.example.Book_My_Show.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tickets")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("book")
    public String bookTicket(@RequestBody TicketEntryDto ticketEntryDto) throws  Exception{
        try{
            String result = ticketService.addTicket(ticketEntryDto);
            return result;
        }catch (Exception e){
            return "Failure";
        }
    }

}

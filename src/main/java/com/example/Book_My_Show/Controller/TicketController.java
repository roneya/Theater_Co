package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.Entity.TicketEntity;
import com.example.Book_My_Show.EntryDto.TicketEntryDto;
import com.example.Book_My_Show.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tickets")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("book") //book ticket
    public String bookTicket(@RequestBody TicketEntryDto ticketEntryDto) throws  Exception{
        try{
            String result = ticketService.addTicket(ticketEntryDto);
            return result;
        }catch (Exception e){
            return "Sorry, already booked";
        }
    }
    @PutMapping("cancel") //cancel the ticket from id
    public String cancelTicket(int id ){
        return ticketService.cancelTicket(id);
    }


}

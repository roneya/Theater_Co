package com.example.Book_My_Show.EntryDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class TicketEntryDto {
    private int showId;
    private List<String> requestedSeats = new ArrayList<>();
    private int userId;
}

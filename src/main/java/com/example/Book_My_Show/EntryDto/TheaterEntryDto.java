package com.example.Book_My_Show.EntryDto;

import lombok.Data;

@Data
public class TheaterEntryDto {

    private String name;
    private String location;
    private int classicSeatsCount;
    private int premiumSeatsCount;

}

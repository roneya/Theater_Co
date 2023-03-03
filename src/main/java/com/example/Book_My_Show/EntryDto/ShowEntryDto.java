package com.example.Book_My_Show.EntryDto;

import com.example.Book_My_Show.Enums.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {
    private LocalDate localDate; //show data
    private LocalTime localTime; //show time
    private ShowType showType; //2d 3d
    private int movieId;

    private int theaterId;
    private int classicSeatPrice;
    private int premiumSeatPrice;

}

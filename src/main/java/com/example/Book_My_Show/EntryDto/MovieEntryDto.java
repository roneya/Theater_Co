package com.example.Book_My_Show.EntryDto;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {

    private String movieName;
    private int duration;
    private double ratings;
    private Language language;
    private Genre genre;
}

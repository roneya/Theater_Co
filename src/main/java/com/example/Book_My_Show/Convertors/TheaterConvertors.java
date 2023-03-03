package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entity.TheaterEntity;
import com.example.Book_My_Show.EntryDto.TheaterEntryDto;

public class TheaterConvertors {
    public static TheaterEntity convertDtoToEntity(TheaterEntryDto theaterEntryDto){
    return TheaterEntity.builder().location(theaterEntryDto.getLocation()).name(theaterEntryDto.getName()).
            classicSeatsCount(theaterEntryDto.getClassicSeatsCount()).premiumSeatsCount(theaterEntryDto.getPremiumSeatsCount()).build();
    }
}

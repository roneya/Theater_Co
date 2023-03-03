package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entity.ShowEntity;
import com.example.Book_My_Show.EntryDto.ShowEntryDto;

public class ShowConvertors {
    public static ShowEntity convertEntryToEntity(ShowEntryDto showEntryDto){
        ShowEntity showEntity = ShowEntity.builder().showDate(showEntryDto.getLocalDate()).showTime(showEntryDto.getLocalTime()).showType(showEntryDto.getShowType()).build();
        return showEntity;
    }
}

package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entity.MovieEntity;
import com.example.Book_My_Show.EntryDto.MovieEntryDto;

public class MovieConvertors {
   public static MovieEntity convertEntryDtoToEntity(MovieEntryDto movieEntryDto){
       MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDto.getMovieName()).
               duration(movieEntryDto.getDuration()).genre(movieEntryDto.getGenre()).
               language(movieEntryDto.getLanguage()).ratings(movieEntryDto.getRatings()).build();
       return  movieEntity;
   }
}

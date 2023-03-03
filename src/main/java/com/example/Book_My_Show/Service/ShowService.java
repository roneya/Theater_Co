package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertors.ShowConvertors;
import com.example.Book_My_Show.Entity.*;
import com.example.Book_My_Show.EntryDto.ShowEntryDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDto showEntryDto){
        ShowEntity showEntity = ShowConvertors.convertEntryToEntity(showEntryDto);

        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheaterId();

        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        TheaterEntity theaterEntity = theaterRepository.findById(theaterId).get();

        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theaterEntity);

        List<ShowSeatEntity> showSeatEntityList = createShowSeatEntity(showEntryDto,showEntity);
        showEntity.setListOfShowSeats(showSeatEntityList);

        showEntity = showRepository.save(showEntity);

        List<ShowEntity> showEntityList = movieEntity.getShowEntityList();
        showEntityList.add(showEntity);



        movieEntity.getShowEntityList().add(showEntity);
        theaterEntity.getShowEntityList().add(showEntity);

        movieRepository.save(movieEntity); //movie
        theaterRepository.save(theaterEntity);

        return "The show has been added successfully";




    }
    private List<ShowSeatEntity> createShowSeatEntity(ShowEntryDto showEntryDto, ShowEntity showEntity){
        TheaterEntity theaterEntity = showEntity.getTheaterEntity();
        List<TheaterSeatEntity> theaterSeatEntityList = theaterEntity.getTheaterSeatEntityList();
        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

        for (TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList){
            ShowSeatEntity showSeatEntity = new ShowSeatEntity();

            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC))
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            else
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());

            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity);

            showSeatEntityList.add(showSeatEntity);
        }
            return showSeatEntityList;

    }

}

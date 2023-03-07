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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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

    public List<String> showsOfMovie(@RequestParam String name){   //giving -> show time and theater for given movie
        return showRepository.showsOfMovie(name);
    }

    public List<String> showAfter(LocalTime time){
       List<ShowEntity> showEntityList = showRepository.findAll();
       List<String> showEntityList1 = new ArrayList<>();


            for(ShowEntity showEntity: showEntityList){
                if(showEntity.getShowTime().isAfter(time)){
                    MovieEntity movieEntity = movieRepository.findById(showEntity.getMovieEntity().getId()).get();
                    TheaterEntity theaterEntity = theaterRepository.findById(showEntity.getTheaterEntity().getId()).get();
                    showEntityList1.add(movieEntity.getMovieName()+" "+theaterEntity.getName()+" "+theaterEntity.getLocation());
                }
            }



     return showEntityList1;


    }


}

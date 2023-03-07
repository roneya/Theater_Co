package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertors.TheaterConvertors;
import com.example.Book_My_Show.Entity.TheaterEntity;
import com.example.Book_My_Show.Entity.TheaterSeatEntity;
import com.example.Book_My_Show.EntryDto.TheaterEntryDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Repository.TheaterRepository;
import com.example.Book_My_Show.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;
    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{

        if(theaterEntryDto.getName()==null || theaterEntryDto.getLocation()==null){
            throw new Exception("Invalid Name/Location");
        }

        TheaterEntity theaterEntity = TheaterConvertors.convertDtoToEntity(theaterEntryDto);
        List<TheaterSeatEntity> theaterSeatEntityList = createTheaterSeats(theaterEntryDto, theaterEntity);

        theaterEntity.setTheaterSeatEntityList(theaterSeatEntityList);
        theaterRepository.save(theaterEntity);
        return "Theater added successfully";
    }
    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto, TheaterEntity theaterEntity ){
        int noClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int noPremiumSeats = theaterEntryDto.getPremiumSeatsCount();
        List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();

        for(int c =1;c<=noClassicSeats;c++){
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
                    seatType(SeatType.CLASSIC).seatNo(c+"C").theaterEntity(theaterEntity).build();
            theaterSeatEntityList.add(theaterSeatEntity);

        }
        for(int c =1;c<=noPremiumSeats;c++){
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
                    seatType(SeatType.PREMIUM).seatNo(c+"P").theaterEntity(theaterEntity).build();
            theaterSeatEntityList.add(theaterSeatEntity);

        }

        return theaterSeatEntityList;

    }

    public List<String> getAll(){
        return theaterRepository.getAllTheaters();
    }
    public List<String> shows(@RequestParam String name ){
    return theaterRepository.shows(name);
    }
}

package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertors.TicketConvertors;
import com.example.Book_My_Show.Entity.ShowEntity;
import com.example.Book_My_Show.Entity.ShowSeatEntity;
import com.example.Book_My_Show.Entity.TicketEntity;
import com.example.Book_My_Show.Entity.UserEntity;
import com.example.Book_My_Show.EntryDto.TicketEntryDto;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;
    @Autowired
    private UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception{ //book tickets
        TicketEntity ticketEntity = TicketConvertors.convertDtoToDto(ticketEntryDto);

        boolean isValidRequest = checkValidityOfRequestedSeats(ticketEntryDto);

        if(!isValidRequest){
            throw new Exception("Already booked not available");
        }
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatEntity> seatEntityList = showEntity.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int totalAmount = 0;

        for (ShowSeatEntity showSeatEntity: seatEntityList){
            if (requestedSeats.contains(showSeatEntity.getSeatNo())){
                totalAmount+=showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }
        ticketEntity.setTotalAmount(totalAmount);

        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());

        String allotedSeats = getAllSeatsFromShowSeats(requestedSeats);
        ticketEntity.setBookedSeats(allotedSeats);

        UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();

        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        ticketEntity = ticketRepository.save(ticketEntity);

        List<TicketEntity> ticketEntityList = showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfBookedTickets(ticketEntityList);

        showRepository.save(showEntity);

        List<TicketEntity> ticketEntityList1 = userEntity.getBookedTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setBookedTickets(ticketEntityList1);

        userRepository.save(userEntity);

        String body = "Confirmed seats are "+allotedSeats;
        return "Ticket has successfully been added";
    }

    private String getAllSeatsFromShowSeats(List<String> requestedSeats){
        String ans = "";

        for(String seat: requestedSeats){

            ans+=seat+" ";
        }
        return ans;
    }


    private boolean checkValidityOfRequestedSeats (TicketEntryDto ticketEntryDto){
        int showId = ticketEntryDto.getShowId(); //show id
        List<String> requestSeats = ticketEntryDto.getRequestedSeats();
        ShowEntity showEntity = showRepository.findById(showId).get();
        List<ShowSeatEntity> listOfSeats = showEntity.getListOfShowSeats();

        for(ShowSeatEntity showSeatEntity: listOfSeats){

            String seatNo = showSeatEntity.getSeatNo();

            if(requestSeats.contains(seatNo)){
                if(showSeatEntity.isBooked()){
                    return false; //Already booked
                }
            }

        }
        return true;
    }

    public String cancelTicket(int id ){
    TicketEntity ticketEntity = ticketRepository.findById(id).get();
    String str = ticketEntity.getBookedSeats();
    String s [] = str.split(" ");
        Set<String> hs = new HashSet<>();
        for(String q: s) hs.add(q); //adding all seat numbers
    ticketEntity.setTotalAmount(ticketEntity.getTotalAmount()*-1); //multiplying the amount with -1 to show it refund amount to my company
        ticketRepository.save(ticketEntity); //saving ticket
    ShowEntity showEntity = ticketEntity.getShowEntity();
        List<ShowSeatEntity> listOfSeats = showEntity.getListOfShowSeats();
        for( ShowSeatEntity showSeatEntity : listOfSeats){
            if( hs.contains(showSeatEntity.getSeatNo()) && showSeatEntity.getShowEntity().getId() == showEntity.getId() ){
                showSeatEntity.setBooked(false); //seat is not booked now
                showSeatEntity.setBookedAt(null); //created set to null
                hs.remove(showSeatEntity.getSeatNo()); //remove the seat from set as its job is done
            }
        }
        showRepository.save(showEntity); //saving the show as seat is child inside show
        return "Done, your refund will proceed within 7 working days";

    }
}

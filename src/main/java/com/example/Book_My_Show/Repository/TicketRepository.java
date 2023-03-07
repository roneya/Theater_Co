package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {

    //@Query(value = "select * from tickets", nativeQuery = true)
    //TicketEntity cancelTicket(int userId, int showId);
}

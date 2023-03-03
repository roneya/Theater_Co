package com.example.Book_My_Show.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Entity
@Table(name="tickets")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private int totalAmount;

    private String ticketId = UUID.randomUUID().toString();

    private String theaterName;

    private String bookedSeats;




    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;



    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}

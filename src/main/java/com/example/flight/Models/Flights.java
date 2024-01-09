package com.example.flight.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure")
    private Airports departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival")
    private Airports arrivalAirport;

    @Column
    private LocalDateTime departureTime;

    @Column
    private LocalDateTime arrivalTime;

    @Column
    private Long price;

}

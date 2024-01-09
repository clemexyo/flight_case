package com.example.flight.Requests;

import com.example.flight.Models.Airports;
import lombok.Getter;

import java.time.LocalDateTime;

public class CreateFlightRequest {


    private Airports departureAirport;
    private Airports arrivalAirport;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;
    private Long price;

    public CreateFlightRequest(Airports departureAirport, Airports arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime, Long price) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }
    public Airports getDepartureAirport() { return this.departureAirport; }
    public Airports getArrivalAirport() { return this.arrivalAirport; }
    public LocalDateTime getDepartureTime() { return this.departureTime; }
    public LocalDateTime getArrivalTime() { return this.arrivalTime; }
    public Long getPrice() { return this.price; }
}

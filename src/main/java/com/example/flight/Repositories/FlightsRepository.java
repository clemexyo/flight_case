package com.example.flight.Repositories;

import com.example.flight.Models.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, Long> {
    List<Flights> findByDepartureAirportCityAndArrivalAirportCityAndDepartureTimeAndArrivalTime(
            String departureCity, String arrivalCity, LocalDateTime departureTime, LocalDateTime arrivalTime);

    List<Flights> findByDepartureAirportCityAndArrivalAirportCityAndDepartureTime(
            String departureCity, String arrivalCity, LocalDateTime departureTime);
}

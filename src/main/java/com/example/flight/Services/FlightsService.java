package com.example.flight.Services;

import com.example.flight.Models.Airports;
import com.example.flight.Models.Flights;
import com.example.flight.Repositories.FlightsRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightsService {
    @Autowired
    private FlightsRepository flightsRepository;

    public String Create(Airports departureAirport, LocalDateTime departureTime,
    Airports arrivalAirport, LocalDateTime arrivalTime, Long price){
        Flights flight = new Flights(departureAirport, arrivalAirport, departureTime, arrivalTime, price);
        flightsRepository.save(flight);
        return flight.toString();
    }
    public List<String> GetAll(){
        List<Flights> flightsObjectList = flightsRepository.findAll();
        if(flightsObjectList.isEmpty()){
            throw new NotFoundException();
        }
        List<String> flightsStringList = new ArrayList<String>();
        for (Flights currentPlayer : flightsObjectList) {
            flightsStringList.add(currentPlayer.toString());
        }
        return flightsStringList;
    }

    public String Get(Long id) {
        Optional<Flights> flight = flightsRepository.findById(id);
        if(flight.isEmpty()){
            throw new NotFoundException();
        }
        return flight.get().toString();
    }

    public String Update(Flights updatedFlight, Long id) {
        Optional<Flights> flight = flightsRepository.findById(id);
        if(flight.isEmpty()){
            throw new NotFoundException();
        }
        BeanUtils.copyProperties(updatedFlight, flight.get(), "id");
        flightsRepository.save(flight.get());
        return flight.get().toString();
    }

    public String Delete(Long id) {
        if(!flightsRepository.existsById(id)){
            throw new NotFoundException();
        }
        flightsRepository.deleteById(id);

        return "Deleted flight\n";
    }
}

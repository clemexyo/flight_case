package com.example.flight.Services;

import com.example.flight.Models.Airports;
import com.example.flight.Repositories.AirportsRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirportsService {
    @Autowired
    private AirportsRepository airportsRepository;

    public String Create(String city, String name){
        Airports newAirport = new Airports(city, name);
        airportsRepository.save(newAirport);
        return newAirport.toString();
    }

    public List<String> GetAll(){
        List<Airports> airportsObjectList = airportsRepository.findAll();
        if(airportsObjectList.isEmpty()){
            throw new NotFoundException();
        }
        List<String> airportsStringList = new ArrayList<String>();
        for (Airports currentPlayer : airportsObjectList) {
            airportsStringList.add(currentPlayer.toString());
        }
        return airportsStringList;
    }
    public String Get(Long id){
        Optional<Airports> airport = airportsRepository.findById(id);
        if(airport.isEmpty()){
            throw new NotFoundException();
        }
        return airport.get().toString();
    }
    public Airports GetAirport(Long id){
        Optional<Airports> airport = airportsRepository.findById(id);
        if(airport.isEmpty()){
            throw new NotFoundException();
        }
        return airport.get();
    }
    public String Update(Airports newAirport, Long id){
        Optional<Airports> airport = airportsRepository.findById(id);
        if(airport.isEmpty()){
            throw new NotFoundException();
        }
        Airports oldAirport = airport.get();

        BeanUtils.copyProperties(newAirport, oldAirport, "id");
        airportsRepository.save(oldAirport);
        return oldAirport.toString();
    }
    public String Delete(Long id){
        if(!airportsRepository.existsById(id)){
            throw new NotFoundException();
        }
        airportsRepository.deleteById(id);

        return "Deleted airport";
    }
}

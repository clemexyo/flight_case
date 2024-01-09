package com.example.flight.Controllers;

import com.example.flight.Requests.CreateAirportRequest;
import com.example.flight.Services.AirportsService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/airports")
public class AirportsController {
    @Autowired
    private AirportsService airportsService;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody CreateAirportRequest request){
        if(request == null || request.getAirportName() == null || request.getAirportCity() == null){
            throw new BadRequestException();
        }
        String city = request.getAirportCity();
        String name = request.getAirportName();
        String createdAirport = airportsService.Create(city, name);
        return new ResponseEntity<>(createdAirport, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<String>> GetAll(){
        List<String> airportsList = airportsService.GetAll();
        return new ResponseEntity<>(airportsList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> Get(@PathVariable Long id){
        String airport = airportsService.Get(id);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }
}

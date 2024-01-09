package com.example.flight.Controllers;

import com.example.flight.Models.Flights;
import com.example.flight.Requests.CreateFlightRequest;
import com.example.flight.Services.FlightsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/flights")
public class FlightsController {
    @Autowired
    private FlightsService flightsService;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody CreateFlightRequest request){
        if(request == null ||
        request.getArrivalTime() == null ||
        request.getArrivalAirport() == null ||
        request.getDepartureTime() == null ||
        request.getDepartureAirport() == null ||
        request.getPrice() == null) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        String createdFlight = flightsService.Create(request.getArrivalAirport(), request.getArrivalTime(),
                request.getDepartureAirport(), request.getDepartureTime(), request.getPrice());
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<String>> GetAll(){
        List<String> airportsList = flightsService.GetAll();
        return new ResponseEntity<>(airportsList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> Get(@PathVariable Long id){
        String airport = flightsService.Get(id);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> Update(@PathVariable Long id, @RequestBody CreateFlightRequest request){
        if(request == null ||
                request.getArrivalTime() == null ||
                request.getArrivalAirport() == null ||
                request.getDepartureTime() == null ||
                request.getDepartureAirport() == null ||
                request.getPrice() == null) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        Flights updatedFlight = new Flights(request.getDepartureAirport(), request.getArrivalAirport(), request.getDepartureTime(), request.getArrivalTime(), request.getPrice());
        String updated = flightsService.Update(updatedFlight, id);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable Long id){
        String deleted = flightsService.Delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/flight-search")
    public ResponseEntity<List<String>> SearchFlight(
            @RequestParam("departureCity") String departureCity,
            @RequestParam("arrivalCity") String arrivalCity,
            @RequestParam("departureDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam(value = "returnDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDateTime
    ){
        List<String> flights;

        if (returnDateTime != null) {
            // Two-way flight
            flights = flightsService.TwoWayFlight(departureCity, arrivalCity, departureDateTime, returnDateTime);
                    //flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeAndReturnDateTime(
                    //departureCity, arrivalCity, departureDateTime, returnDateTime);
        } else {
            // One-way flight
            flights = flightsService.OneWayFlight(departureCity, arrivalCity, departureDateTime);
        }
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}

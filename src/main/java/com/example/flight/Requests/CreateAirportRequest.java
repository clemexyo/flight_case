package com.example.flight.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class CreateAirportRequest {
    @JsonProperty("city")
    private String city;
    @JsonProperty("name")
    private String name;

    public String getAirportName() { return this.name; }
    public String getAirportCity() { return this.city; }
}

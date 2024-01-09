package com.example.flight.Models;
import jakarta.persistence.*;

@Entity
@Table(name = "airports")
public class Airports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String city;

    @Column
    private String name;

    public Airports(String city, String name) {
        this.city = city;
        this.name = name;
    }

    @Override
    public String toString(){
        return "Airport name: " + this.name + ", Airport city: " + this.city + ".\n";
    }
}

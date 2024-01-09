package com.example.flight.Repositories;

import com.example.flight.Models.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportsRepository extends JpaRepository<Airports, Long> {
}

package com.flightticketsystem.runtime.repository;

import com.flightticketsystem.runtime.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    public ArrayList<Flight> findFlightsByDepartureCityOrArrivalCityOrEstimatedTakeOffTime(String departureCity, String arrivalCity, Date takeOffTime);

}

package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.repository.FlightRepository;
import com.flightticketsystem.runtime.service.FlightService;
import com.flightticketsystem.runtime.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public ArrayList<Flight> searchFlights(Flight flight) {
        return flightRepository.findFlightsByDepartureCityOrArrivalCityOrEstimatedTakeOffTime(flight.getDepartureCity(),flight.getArrivalCity(),flight.getEstimatedTakeOffTime());
    }
}

package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.repository.FlightRepository;
import com.flightticketsystem.runtime.service.FlightService;
import com.flightticketsystem.runtime.service.TicketService;
import com.flightticketsystem.runtime.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Resource
    private TicketService ticketService;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public ArrayList<Flight> searchFlights(Flight flight) {
        return flightRepository.findFlightsByDepartureCityOrArrivalCityOrEstimatedTakeOffTime(flight.getDepartureCity(),flight.getArrivalCity(),flight.getEstimatedTakeOffTime());
    }

    @Override
    public boolean addFlight(Flight flight, String seatCharts) {
        Flight flight1 = flightRepository.save(flight);
        if(flight1.getFlightId() != 0) {
            boolean addTicketResult = ticketService.addTickets(flight1.getFlightId(), seatCharts);
            return true;
        }
        return false;
    }
}

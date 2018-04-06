package com.flightticketsystem.runtime.service;

import com.flightticketsystem.runtime.domain.Flight;

import java.util.ArrayList;
import java.util.List;

public interface FlightService {

    List<Flight> searchFlights(Flight flight);

    boolean addFlight(Flight flight, String seatCharts);

    List<String> searchUnavailableSeatCharts(int flightId);
}

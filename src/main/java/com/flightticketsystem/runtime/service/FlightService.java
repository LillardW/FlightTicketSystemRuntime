package com.flightticketsystem.runtime.service;

import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.domain.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface FlightService {

    List<Flight> searchFlights(Flight flight);

    boolean addFlight(Flight flight, String seatCharts);

    List<Map<String, Object>> searchSeatCharts(int flightId);
}

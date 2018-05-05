package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.repository.DynamicQueryDao;
import com.flightticketsystem.runtime.repository.FlightRepository;
import com.flightticketsystem.runtime.service.FlightService;
import com.flightticketsystem.runtime.service.PlaceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FlightServiceImpl implements FlightService {
    private final static Logger logger = Logger.getLogger(FlightServiceImpl.class);

    @Resource
    private PlaceService placeService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private DynamicQueryDao dynamicQueryDao;

    @Override
    public List<Flight> searchFlights(Flight flight) {
        return dynamicQueryDao.findFlightsByDepartureCityOrArrivalCityOrEstimatedTakeOffTime(flight.getDepartureCity(), flight.getArrivalCity(), flight.getEstimatedTakeOffTime());
    }

    @Override
    public boolean addFlight(Flight addFlight, String seatCharts) {
        try {
            Flight flight = flightRepository.save(addFlight);
            placeService.addPlaces(flight.getFlightId(), seatCharts);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> searchSeatCharts(int flightId) {
        List<Map<String, Object>> seatCharts = dynamicQueryDao.searchSeatCharts(flightId);
        return seatCharts;
    }
}

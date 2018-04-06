package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.constant.PlaceStatus;
import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.domain.Place;
import com.flightticketsystem.runtime.repository.FlightRepository;
import com.flightticketsystem.runtime.repository.PlaceRepository;
import com.flightticketsystem.runtime.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public boolean addPlaces(int flightId, String seatCharts) {
        String[] seats = seatCharts.split("/");
        int row = 1;
        char column = 1;
        String flightPlace;
        Flight flight = flightRepository.findByFlightId(flightId);
        Place place;

        for (int i = 1; i <= seatCharts.replaceAll("/", "").length(); i++) {
            flightPlace = Integer.toString(row) + "_" +Integer.toString(column);
            ++column;
            place = new Place(flightId,flightPlace,PlaceStatus.AVAILABLE.getPlaceStatus());
            placeRepository.save(place);
            if (i % 6 == 0) {
                ++row;
                column = 1;
            }
            if (i == seatCharts.replaceAll("/", "").length()) {
                return true;
            }
        }
        return false;
    }
}

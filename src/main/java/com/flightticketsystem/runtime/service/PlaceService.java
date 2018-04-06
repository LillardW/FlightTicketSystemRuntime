package com.flightticketsystem.runtime.service;

import javax.annotation.Resource;

@Resource
public interface PlaceService {
    boolean addPlaces(int flightId, String seatCharts);
}

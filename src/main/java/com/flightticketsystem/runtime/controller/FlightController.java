package com.flightticketsystem.runtime.controller;


import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/flight")
public class FlightController {

    @Resource
    private FlightService flightService;

    @RequestMapping("/search")
    public Flight searchFlights(@RequestBody Flight flight) {
        return flightService.searchFlights(flight);
    }
}

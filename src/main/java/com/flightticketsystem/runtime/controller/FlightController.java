package com.flightticketsystem.runtime.controller;


import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.domain.Place;
import com.flightticketsystem.runtime.service.FlightService;
import com.flightticketsystem.runtime.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/flight")
public class FlightController {

    @Resource
    private FlightService flightService;

    @RequestMapping(value = "/addFlight")
    public String addFlight(@RequestParam("flightNo") String flightNo, @RequestParam("departureCity") String departureCity, @RequestParam("arrivalCity") String arrivalCity, @RequestParam("estimatedTakeOffTime") Date estimatedTakeOffTime, @RequestParam("estimatedArrivalTime") Date estimatedArrivalTime, @RequestParam("seatCharts") String seatCharts) {
        Flight flight = new Flight(flightNo,departureCity,arrivalCity,estimatedTakeOffTime,estimatedArrivalTime);
        boolean result = flightService.addFlight(flight, seatCharts);
        if(result) {
            return "redirect:/index";
        }
        return "redirect:addFlightPage";
    }

    @RequestMapping(value = "/searchSeatCharts", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> searchSeatCharts(@RequestParam("flightId") int flightId) { ;
        return flightService.searchSeatCharts(flightId);
    }
}

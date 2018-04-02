package com.flightticketsystem.runtime.controller;


import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/flight")
public class FlightController {

    @Resource
    private FlightService flightService;

    @RequestMapping("/addFlight")
    public String addFlight(@RequestParam("flightNo") String flightNo, @RequestParam("departureCity") String departureCity, @RequestParam("arrivalCity") String arrivalCity, @RequestParam("estimatedTakeOffTime") Date estimatedTakeOffTime, @RequestParam("estimatedArrivalTime") Date estimatedArrivalTime, @RequestParam("seatCharts") String seatCharts) {
        Flight flight = new Flight(flightNo,departureCity,arrivalCity,estimatedTakeOffTime,estimatedArrivalTime);
        boolean result = flightService.addFlight(flight, seatCharts);
        if(result) {
            return "redirect:/index";
        }
        return "redirect:addFlightPage";
    }
}

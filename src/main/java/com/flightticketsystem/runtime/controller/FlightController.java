package com.flightticketsystem.runtime.controller;


import com.flightticketsystem.runtime.constant.Constant;
import com.flightticketsystem.runtime.domain.*;
import com.flightticketsystem.runtime.service.FlightService;
import com.flightticketsystem.runtime.service.PlaceService;
import com.flightticketsystem.runtime.service.TicketService;
import com.flightticketsystem.runtime.service.UserService;
import com.flightticketsystem.runtime.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/flight")
public class FlightController {

    private final static Logger logger = Logger.getLogger(FlightController.class);

    @Resource
    private FlightService flightService;

    @Resource
    private TicketService ticketService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/addFlight")
    @ResponseBody
    public boolean addFlight(@RequestBody FlightModel flightModel) {
        Flight flight = new Flight(flightModel.getFlightNo(),flightModel.getDepartureCity(),flightModel.getArrivalCity(),flightModel.getEstimatedTakeOffTime(),flightModel.getEstimatedArrivalTime());
        return flightService.addFlight(flight, flightModel.getSeatCharts());
    }

    @RequestMapping(value = "/searchSeatCharts", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> searchSeatCharts(@RequestParam("flightId") int flightId) {
        return flightService.searchSeatCharts(flightId);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    @ResponseBody
    protected String checkout(@RequestBody List<Map<String, Object>> insertTicketModels, HttpServletRequest request, HttpSession session) {
        long userId = -1;

        if(session.getAttribute("currentUser") != null) {
            userId = ((UserModel) session.getAttribute("currentUser")).getUserId();
        }

        if (userId == -1) {
            return "fail";
        }
        boolean result = ticketService.addTicket(insertTicketModels, userId);
        if (result == true) {
            return "success";
        } else {
            return "fail";
        }
    }


    @RequestMapping(value = "/searchTicketsOfCurrentUser", method = RequestMethod.GET)
    @ResponseBody
    public List<Ticket> searchTicketsOfCurrentUser(@RequestParam("userId") long userId) {
        return ticketService.searchTicketsOfCurrentUser(userId);
    }

    @RequestMapping(value = "/reverseTicket", method = RequestMethod.GET)
    @ResponseBody
    public String reverseTicket(@RequestParam("ticketId") long ticketId) {
        return ticketService.reverseTicket(ticketId);
    }

    @RequestMapping(value = "/searchTicketsOfUser", method = RequestMethod.GET)
    @ResponseBody
    public List<TicketSearchModel> searchTicketsOfUserByUserName(@RequestParam("userName") String userName) {
        return ticketService.searchTicketsOfUserByUserName(userName);
    }
}

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
    public String addFlight(@RequestParam("flightNo") String flightNo, @RequestParam("departureCity") String departureCity, @RequestParam("arrivalCity") String arrivalCity, @RequestParam("estimatedTakeOffTime") Date estimatedTakeOffTime, @RequestParam("estimatedArrivalTime") Date estimatedArrivalTime, @RequestParam("seatCharts") String seatCharts) {
        Flight flight = new Flight(flightNo, departureCity, arrivalCity, estimatedTakeOffTime, estimatedArrivalTime);
        boolean result = flightService.addFlight(flight, seatCharts);
        if (result) {
            return "redirect:/index";
        }
        return "redirect:addFlightPage";
    }

    @RequestMapping(value = "/searchSeatCharts", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> searchSeatCharts(@RequestParam("flightId") int flightId) {
        return flightService.searchSeatCharts(flightId);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    @ResponseBody
    public String checkout(@RequestBody List<Map<String, Object>> insertTicketModels, HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        long userId = 0;

        if(session.getAttribute("currentUser") != null) {
            User user = userService.convert((UserModel) session.getAttribute("currentUser"));
            userId = user.getUserId();
        }

        if (userId == 0) {
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
}

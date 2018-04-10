package com.flightticketsystem.runtime.controller;

import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.domain.UserModel;
import com.flightticketsystem.runtime.service.FlightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PageController {
    private final static Logger logger = Logger.getLogger(PageController.class);

    @Autowired
    private FlightService flightService;

    @RequestMapping("/index")
    public String homePage(Model model, HttpSession session) {
        model.addAttribute("flight", new Flight());
        session.setAttribute("LOGIN_SESSION_KEY_USERNAME", null);
        return "index";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @RequestMapping("/login")
    public String loginPage(Model model, HttpSession session) {
        model.addAttribute("user", new User());
        if(session.getAttribute("LOGIN_SESSION_KEY_USERNAME") != null && !session.getAttribute("LOGIN_SESSION_KEY_USERNAME").equals("")) {
            return "index";
        }
        return "login";
    }

    @RequestMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "register";
    }

    @RequestMapping("/services")
    public String servicePage() {
        return "services";
    }

    @RequestMapping("/typo")
    public String typoPage() {
        return "typo";
    }

    @RequestMapping("/flightSearchResult")
    public String flightSearchResultPage(HttpServletRequest res, Model model) {
        Flight flight = new Flight();
        flight.setDepartureCity(res.getParameter("departureCity"));
        flight.setArrivalCity(res.getParameter("arrivalCity"));
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            if (!res.getParameter("estimatedTakeOffTime").equals("") && res.getParameter("estimatedTakeOffTime") != null) {
                Date takeOffTime = format.parse(res.getParameter("estimatedTakeOffTime"));
                flight.setEstimatedTakeOffTime(takeOffTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Flight> flightSearchResults = flightService.searchFlights(flight);
        model.addAttribute("flightSearchResults", flightSearchResults);
        return "flightSearchResult";
    }

    @RequestMapping("/bookTicketPage")
    public String bookTicketPage(@RequestParam int id) {
        return "bookTicket";
    }

    @RequestMapping("/updateUserProfilePage")
    public String updateUserProfilePage(HttpSession session) {
        if(session.getAttribute("currentUser") != null) {
            return "updateUserProfile";
        }
        return "redirect:index";
    }

    @RequestMapping("/addFlightPage")
    public String addFlightPage(Model model) {
        model.addAttribute("flight",new Flight());
        return "addFlightPage";
    }

    @RequestMapping("/checkout")
    public String checkoutPage(@RequestParam String selectedSeats, @RequestParam int flightId) {
        //TODO
        logger.warn("selectedSeats: " + selectedSeats);
        return "checkout";
    }
}

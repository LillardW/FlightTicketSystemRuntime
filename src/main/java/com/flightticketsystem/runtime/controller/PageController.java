package com.flightticketsystem.runtime.controller;

import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.domain.InsertTicketModel;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.domain.UserModel;
import com.flightticketsystem.runtime.service.FlightService;
import com.flightticketsystem.runtime.service.TicketService;
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

    @Autowired
    private TicketService ticketService;

    @RequestMapping("/")
    public String indexPage(Model model, HttpSession session) {
        model.addAttribute("flight", new Flight());
        session.setAttribute("LOGIN_SESSION_KEY_USERNAME", null);
        return "index";
    }

    @RequestMapping("/index")
    public String homePage(Model model, HttpSession session) {
        model.addAttribute("flight", new Flight());
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
    public String flightSearchResultPage(HttpServletRequest res, Model model, HttpSession session) {
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
    public String bookTicketPage(@RequestParam int id, HttpSession session) {
        if(session.getAttribute("currentUser") != null) {
            return "bookTicket";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/updateUserProfilePage")
    public String updateUserProfilePage(HttpSession session) {
        if(session.getAttribute("currentUser") != null) {
            return "updateUserProfile";
        }
        return "redirect:index";
    }

    @RequestMapping("/addFlightPage")
    public String addFlightPage(Model model, HttpSession session) {
        String page = "";
        if(session.getAttribute("Authority") != null) {
            if ((int) session.getAttribute("Authority") == 1) {
                model.addAttribute("flight", new Flight());
                page = "addFlightPage";
            } else {
                page = "index";
            }
            return page;
        } else {
            return "index";
        }

    }

    @RequestMapping("/checkout")
    public String checkoutPage(@RequestParam String selectedSeats, @RequestParam int flightId, Model model, HttpSession session) {
        //TODO
        List<InsertTicketModel> insertTicketModels = ticketService.getInsertTicketModels(selectedSeats, flightId);
        model.addAttribute("insertTicketModels", insertTicketModels);
        session.setAttribute("insertTicketModels", insertTicketModels);
        logger.warn("selectedSeats: " + selectedSeats);
        return "checkout";
    }
}

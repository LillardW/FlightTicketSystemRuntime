package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.constant.PlaceStatus;
import com.flightticketsystem.runtime.constant.TicketPrice;
import com.flightticketsystem.runtime.constant.TicketStatus;
import com.flightticketsystem.runtime.domain.*;
import com.flightticketsystem.runtime.repository.DynamicQueryDao;
import com.flightticketsystem.runtime.repository.FlightRepository;
import com.flightticketsystem.runtime.repository.PlaceRepository;
import com.flightticketsystem.runtime.repository.TicketRepository;
import com.flightticketsystem.runtime.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private DynamicQueryDao dynamicQueryDao;

    public boolean addTicket(List<Map<String, Object>> insertTicketModels, long userId) {
        for(Map<String, Object> map : insertTicketModels) {
            Ticket ticket = convertToTicket(map, userId);
            Place place = placeRepository.findOne(ticket.getPlaceId());
            place.setPlaceStatus(PlaceStatus.SOLD.getPlaceStatus());
            placeRepository.save(place);
            ticketRepository.save(ticket);
        }
        return true;
    }

//    public boolean addTicket(int flightId, String seatCharts) {
//        String[] seats = seatCharts.split("/");
//        int row = 1;
//        char column = 'a';
//        String flightPlace;
//        Ticket ticket;
//        int ticketPrice = 0;
//        Flight flight = flightRepository.findByFlightId(flightId);
//
//        for (int i = 1; i <= seatCharts.replaceAll("/", "").length(); i++) {
//            flightPlace = Integer.toString(row) + column;
//            ++column;
//            if (seatCharts.charAt(i-1) == 'f') {
//                ticketPrice = TicketPrice.f.getTicketPrice();
//            } else if (seatCharts.charAt(i-1) == 'n'){
//                ticketPrice = TicketPrice.n.getTicketPrice();
//            } else if (seatCharts.charAt(i-1) == '_') {
//
//            }
//            String ticketNo = new SimpleDateFormat("yyyyMMdd").format(flight.getEstimatedTakeOffTime()).toString() + flight.getFlightNo() + flightPlace;
//            //TODO
//            //ticket = new Ticket(ticketNo, TicketStatus.NOT_SOLD.getTicketStatus(), (double) ticketPrice, new Person("temp","temp"), flight, flightPlace);
//            ticket = new Ticket();
//            ticketRepository.save(ticket);
//            if (i % 6 == 0) {
//                ++row;
//                column = 'a';
//            }
//            if (i == seatCharts.replaceAll("/", "").length()) {
//                return true;
//            }
//        }
//        return false;
//    }


//    public boolean addTicket(ArrayList<Ticket> tickets) {
//        return false;
//    }

    public List<InsertTicketModel> getInsertTicketModels(String selectedSeatsString, int flightId) {
        List<InsertTicketModel> insertTicketModels = new ArrayList<>();
        String[] selectedSeats = selectedSeatsString.split(",");
        InsertTicketModel insertTicketModel;
        for(String selectSeat: selectedSeats) {
            insertTicketModel = new InsertTicketModel();
            insertTicketModel.setFlightId(flightId);
            insertTicketModel.setFlightNo(flightRepository.findByFlightId(flightId).getFlightNo());
            insertTicketModel.setPlaceNo(selectSeat);
            insertTicketModel.setPlaceLevel(placeRepository.findPlaceIdByFlightIdAndPlaceNo(flightId, selectSeat).getPlaceLevel());
            insertTicketModel.setPlaceId(placeRepository.findPlaceIdByFlightIdAndPlaceNo(flightId, selectSeat).getPlaceId());
            insertTicketModels.add(insertTicketModel);
        }
        return insertTicketModels;
    }

    public Ticket convertToTicket(Map<String, Object> map, long userId) {
        Flight flight = flightRepository.findByFlightNo(String.valueOf(map.get("flightNo")));
        Place place = placeRepository.findPlaceIdByFlightIdAndPlaceNo(flight.getFlightId(),String.valueOf(map.get("placeNo")));
        String ticketNo = new SimpleDateFormat("yyyyMMdd").format(flight.getEstimatedTakeOffTime()).toString() + flight.getFlightNo() + place.getPlaceNo();
        int ticketPrice = 0;
        if(place.getPlaceLevel().equals("f")) {
            ticketPrice = TicketPrice.f.getTicketPrice();
        } else if (place.getPlaceLevel().equals("e")) {
            ticketPrice = TicketPrice.e.getTicketPrice();
        }
        Person person = new Person(String.valueOf(map.get("personId")),String.valueOf(map.get("personName")));
        Ticket ticket = new Ticket(ticketNo,TicketStatus.PAID.getTicketStatus(),ticketPrice,person,flight,place.getPlaceId(), userId);
        return ticket;
    }

    @Override
    public List<Ticket> searchTicketsOfCurrentUser(long userId) {
        List<Ticket> tickets = ticketRepository.findTicketsByUserId(userId);
        return tickets;
    }

    public String reverseTicket(long ticketId) {
        try {
            Ticket ticket = ticketRepository.findOne(ticketId);
            Place place = placeRepository.findOne(ticket.getPlaceId());
            ticket.setTicketStatus(TicketStatus.REVERSED.getTicketStatus());
            place.setPlaceStatus(PlaceStatus.AVAILABLE.getPlaceStatus());
            ticketRepository.save(ticket);
            placeRepository.save(place);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    public List<Ticket> searchTickets(TicketSearchModel ticketSearchModel) {
        return dynamicQueryDao.searchTickets(ticketSearchModel);
    }
}

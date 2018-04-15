package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.constant.TicketPrice;
import com.flightticketsystem.runtime.constant.TicketStatus;
import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.domain.InsertTicketModel;
import com.flightticketsystem.runtime.domain.Person;
import com.flightticketsystem.runtime.domain.Ticket;
import com.flightticketsystem.runtime.repository.FlightRepository;
import com.flightticketsystem.runtime.repository.PlaceRepository;
import com.flightticketsystem.runtime.repository.TicketRepository;
import com.flightticketsystem.runtime.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public boolean addTicket(int flightId, String seatCharts) {
        String[] seats = seatCharts.split("/");
        int row = 1;
        char column = 'a';
        String flightPlace;
        Ticket ticket;
        int ticketPrice = 0;
        Flight flight = flightRepository.findByFlightId(flightId);

        for (int i = 1; i <= seatCharts.replaceAll("/", "").length(); i++) {
            flightPlace = Integer.toString(row) + column;
            ++column;
            if (seatCharts.charAt(i-1) == 'f') {
                ticketPrice = TicketPrice.f.getTicketPrice();
            } else if (seatCharts.charAt(i-1) == 'n'){
                ticketPrice = TicketPrice.n.getTicketPrice();
            } else if (seatCharts.charAt(i-1) == '_') {

            }
            String ticketNo = new SimpleDateFormat("yyyyMMdd").format(flight.getEstimatedTakeOffTime()).toString() + flight.getFlightNo() + flightPlace;
            //TODO
            //ticket = new Ticket(ticketNo, TicketStatus.NOT_SOLD.getTicketStatus(), (double) ticketPrice, new Person("temp","temp"), flight, flightPlace);
            ticket = new Ticket();
            ticketRepository.save(ticket);
            if (i % 6 == 0) {
                ++row;
                column = 'a';
            }
            if (i == seatCharts.replaceAll("/", "").length()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addTicket(ArrayList<Ticket> tickets) {
        return false;
    }

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
}

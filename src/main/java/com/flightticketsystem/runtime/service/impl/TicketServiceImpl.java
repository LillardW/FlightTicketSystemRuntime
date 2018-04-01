package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.constant.TicketPrice;
import com.flightticketsystem.runtime.constant.TicketStatus;
import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.domain.Ticket;
import com.flightticketsystem.runtime.repository.FlightRepository;
import com.flightticketsystem.runtime.repository.TicketRepository;
import com.flightticketsystem.runtime.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public boolean addTickets(int flightId, String seatCharts) {
        String[] seats = seatCharts.split("/");
        int row = 1;
        int column = 1;
        String flightPlace;
        Ticket ticket;
        int ticketPrice = 0;
        Flight flight = flightRepository.findOne(new Long(flightId));

        for (int i = 1; i <= seatCharts.replaceAll("/", "").length(); i++) {
            flightPlace = "r" + Integer.toString(row) + "c" + Integer.toString(column) + seatCharts.charAt(i);
            if (i % 6 == 0) {
                ++row;
                column = 1;
            }
            if (seatCharts.charAt(i) == 'f') {
                ticketPrice = TicketPrice.f.getTicketPrice().intValue();
            } else {
                ticketPrice = TicketPrice.n.getTicketPrice().intValue();
            }
            String ticketNo = new SimpleDateFormat("yyyymmdd").format(flight.getEstimatedTakeOffTime()).toString() + flight.getFlightNo() + flightPlace;
            ticket = new Ticket(ticketNo, TicketStatus.NOT_SOLD.getTicketStatus(), (double) ticketPrice, null, flight, flightPlace);
            ticketRepository.save(ticket);
            if (i == seatCharts.replaceAll("/", "").length()) {
                return true;
            }
        }
        return false;
    }
}

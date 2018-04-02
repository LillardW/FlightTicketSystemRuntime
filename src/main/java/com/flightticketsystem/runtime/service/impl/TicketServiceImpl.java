package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.constant.TicketPrice;
import com.flightticketsystem.runtime.constant.TicketStatus;
import com.flightticketsystem.runtime.domain.Flight;
import com.flightticketsystem.runtime.domain.Person;
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
            } else {
                ticketPrice = TicketPrice.n.getTicketPrice();
            }
            String ticketNo = new SimpleDateFormat("yyyyMMdd").format(flight.getEstimatedTakeOffTime()).toString() + flight.getFlightNo() + flightPlace;
            ticket = new Ticket(ticketNo, TicketStatus.NOT_SOLD.getTicketStatus(), (double) ticketPrice, new Person("temp","temp"), flight, flightPlace);
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
}

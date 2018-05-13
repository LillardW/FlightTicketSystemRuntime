package com.flightticketsystem.runtime.service;

import com.flightticketsystem.runtime.domain.InsertTicketModel;
import com.flightticketsystem.runtime.domain.Ticket;
import com.flightticketsystem.runtime.domain.TicketSearchModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TicketService {

    //boolean addTicket(ArrayList<Ticket> tickets);

    boolean addTicket(List<Map<String, Object>> insertTicketModels, long userId);

    List<InsertTicketModel> getInsertTicketModels(String selectedSeats, int flightId);

    List<Ticket> searchTicketsOfCurrentUser(long userId);

    String reverseTicket(long ticketId);

    List<Ticket> searchTickets(TicketSearchModel ticketSearchModel);
}

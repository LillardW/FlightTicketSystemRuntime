package com.flightticketsystem.runtime.service;

import com.flightticketsystem.runtime.domain.InsertTicketModel;
import com.flightticketsystem.runtime.domain.Ticket;

import java.util.ArrayList;
import java.util.List;

public interface TicketService {

    boolean addTicket(ArrayList<Ticket> tickets);

    List<InsertTicketModel> getInsertTicketModels(String selectedSeats, int flightId);
}

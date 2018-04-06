package com.flightticketsystem.runtime.service;

import com.flightticketsystem.runtime.domain.Ticket;

import java.util.ArrayList;

public interface TicketService {

    public boolean addTicket(ArrayList<Ticket> tickets);
}

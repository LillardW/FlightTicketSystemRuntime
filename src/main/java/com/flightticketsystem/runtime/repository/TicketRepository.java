package com.flightticketsystem.runtime.repository;

import com.flightticketsystem.runtime.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

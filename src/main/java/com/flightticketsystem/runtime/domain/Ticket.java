package com.flightticketsystem.runtime.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TICKET_SYSTEM_TICKET")
public class Ticket implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID")
    private Long ticketId;

    @Column(name = "TICKET_NO", nullable = false)
    private String ticketNo;

    @Column(name = "TICKET_STATUS", nullable = false)
    private Integer ticketStatus;

    @Column(name = "TICKET_PRICE", nullable = false)
    private Integer ticketPrice;

    @Column(name = "PERSON_ID", nullable = false)
    private Person passenger;

    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    @ManyToOne(targetEntity = Flight.class, optional = false)
    @Fetch(value = FetchMode.SELECT)
    private Flight flight;

    @JoinColumn(name = "PLACE_ID", table = "TICKET_SYSTEM_TICKET_PLACE", nullable = false)
    private long placeId;

    @JoinColumn(name = "USER_ID", table = "TICKET_SYSTEM_USER")
    private long userId;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Integer getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public Person getPassenger() {
        return passenger;
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Ticket(String ticketNo, Integer ticketStatus, Integer ticketPrice, Person passenger, Flight flight, long placeId, long userId) {
        this.ticketNo = ticketNo;
        this.ticketStatus = ticketStatus;
        this.ticketPrice = ticketPrice;
        this.passenger = passenger;
        this.flight = flight;
        this.placeId = placeId;
        this.userId = userId;
    }

    public Ticket() {

    }
}

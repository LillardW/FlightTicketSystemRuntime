package com.flightticketsystem.runtime.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TICKET_SYSTEM_TICKET")
public class Ticket implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "TICKET_ID")
    private Long ticketId;

    @Column(name = "TICKET_NO")
    private String ticketNo;

    @Column(name = "TICKET_STATUS")
    private Integer ticketStatus;

    @Column(name = "TICKET_PRICE")
    private Double ticketPrice;

    @Embedded
    private Person passenger;

    @Column(name = "TICKET_DEPARTMENT")
    private String ticketDepartment;

    @Column(name = "TICKET_TERMINAL")
    private String ticketTerminal;

    @Column(name = "TICKET_PLACE")
    private String ticketPlace;

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

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketDepartment() {
        return ticketDepartment;
    }

    public void setTicketDepartment(String ticketDepartment) {
        this.ticketDepartment = ticketDepartment;
    }

    public String getTicketTerminal() {
        return ticketTerminal;
    }

    public void setTicketTerminal(String ticketTerminal) {
        this.ticketTerminal = ticketTerminal;
    }

    public String getTicketPlace() {
        return ticketPlace;
    }

    public void setTicketPlace(String ticketPlace) {
        this.ticketPlace = ticketPlace;
    }

    public Person getPassenger() {
        return passenger;
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    public Ticket(String ticketNo, Integer ticketStatus, Double ticketPrice, Person passenger, String ticketDepartment, String ticketTerminal, String ticketPlace) {
        this.ticketNo = ticketNo;
        this.ticketStatus = ticketStatus;
        this.ticketPrice = ticketPrice;
        this.passenger = passenger;
        this.ticketDepartment = ticketDepartment;
        this.ticketTerminal = ticketTerminal;
        this.ticketPlace = ticketPlace;
    }
}

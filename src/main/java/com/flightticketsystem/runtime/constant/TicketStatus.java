package com.flightticketsystem.runtime.constant;

public enum  TicketStatus {
    NOT_SOLD(1), PAID(2);
    private Integer ticketStatus;

    public Integer getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    TicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}

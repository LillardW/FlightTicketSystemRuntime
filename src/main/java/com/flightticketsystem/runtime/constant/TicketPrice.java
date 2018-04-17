package com.flightticketsystem.runtime.constant;

public enum TicketPrice {
    f(100),e(40);

    private Integer ticketPrice;

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    TicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

package com.flightticketsystem.runtime.domain;

import java.util.Date;

public class TicketSearchModel {
    private long userId;

    private String userName;

    private long ticketId;

    private String ticketNo;

    private String flightNo;

    private String placeNo;

    private Date estimatedTakeOffTime;

    private Date estimatedArrivalTime;

    private String departureCity;

    private String arrivalCity;

    private String personId;

    private String personName;

    private String ticketStatus;

    private int ticketStatusId;

    private int ticketPrice;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public Date getEstimatedTakeOffTime() {
        return estimatedTakeOffTime;
    }

    public void setEstimatedTakeOffTime(Date estimatedTakeOffTime) {
        this.estimatedTakeOffTime = estimatedTakeOffTime;
    }

    public Date getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(Date estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public int getTicketStatusId() {
        return ticketStatusId;
    }

    public void setTicketStatusId(int ticketStatusId) {
        this.ticketStatusId = ticketStatusId;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public TicketSearchModel(long userId, String userName, long ticketId, String ticketNo, String flightNo, String placeNo, Date estimatedTakeOffTime, Date estimatedArrivalTime, String departureCity, String arrivalCity, String personId, String personName, String ticketStatus, int ticketStatusId, int ticketPrice) {
        this.userId = userId;
        this.userName = userName;
        this.ticketId = ticketId;
        this.ticketNo = ticketNo;
        this.flightNo = flightNo;
        this.placeNo = placeNo;
        this.estimatedTakeOffTime = estimatedTakeOffTime;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.personId = personId;
        this.personName = personName;
        this.ticketStatus = ticketStatus;
        this.ticketStatusId = ticketStatusId;
        this.ticketPrice = ticketPrice;
    }

    public TicketSearchModel() {
    }
}

package com.flightticketsystem.runtime.domain;

import java.util.Date;

public class TicketSearchModel {
    private long userId;

    private String userName;

    private String flightNo;

    private Date estimatedTakeOffDate;

    private Date estimatedArrivalDate;

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

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Date getEstimatedTakeOffDate() {
        return estimatedTakeOffDate;
    }

    public void setEstimatedTakeOffDate(Date estimatedTakeOffDate) {
        this.estimatedTakeOffDate = estimatedTakeOffDate;
    }

    public Date getEstimatedArrivalDate() {
        return estimatedArrivalDate;
    }

    public void setEstimatedArrivalDate(Date estimatedArrivalDate) {
        this.estimatedArrivalDate = estimatedArrivalDate;
    }

    public TicketSearchModel() {
    }

    public TicketSearchModel(long userId, String userName, String flightNo, Date estimatedTakeOffDate, Date estimatedArrivalDate) {
        this.userId = userId;
        this.userName = userName;
        this.flightNo = flightNo;
        this.estimatedTakeOffDate = estimatedTakeOffDate;
        this.estimatedArrivalDate = estimatedArrivalDate;
    }
}

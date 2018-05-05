package com.flightticketsystem.runtime.domain;

import java.util.Date;

public class FlightModel {
    private String flightNo;
    private String departureCity;
    private String arrivalCity;
    private Date estimatedTakeOffTime;
    private Date estimatedArrivalTime;
    private String seatCharts;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
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

    public String getSeatCharts() {
        return seatCharts;
    }

    public void setSeatCharts(String seatCharts) {
        this.seatCharts = seatCharts;
    }

    public FlightModel() {
    }

    public FlightModel(String flightNo, String departureCity, String arrivalCity, Date estimatedTakeOffTime, Date estimatedArrivalTime, String seatCharts) {
        this.flightNo = flightNo;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.estimatedTakeOffTime = estimatedTakeOffTime;
        this.estimatedArrivalTime = estimatedArrivalTime;
        this.seatCharts = seatCharts;
    }
}

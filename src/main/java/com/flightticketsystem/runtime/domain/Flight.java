package com.flightticketsystem.runtime.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TICKET_SYSTEM_FLIGHT")
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLIGHT_ID")
    private int flightId;

    @Column(name = "FLIGHT_NO", nullable = false)
    private String flightNo;

    @Column(name = "DEPARTURE_CITY", nullable = false)
    private String departureCity;

    @Column(name = "ARRIVAL_CITY", nullable = false)
    private String arrivalCity;

    @Transient
    private Date flightTime;

    @Column(name = "ESTIMATED_TAKE_OFF_TIME", nullable = false)
    private Date estimatedTakeOffTime;

    @Column(name = "ESTIMATED_ARRIVAL_TIME", nullable = false)
    private Date estimatedArrivalTime;

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

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

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
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
}

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
    private Integer flightId;

    @Column(name = "FLIGHT_NO")
    private String flightNo;

    @JoinColumn(name = "CITY_ID")
    private City departureCity;

    @JoinColumn(name = "CITY_ID")
    private City arrivalCity;

    @Column(name = "FLIGHT_TIME")
    private Date flightTime;

    @Column(name = "ESTIMATED_FLIGHT_TIME")
    private Date estimatedFlightTime;

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
    }

    public Date getEstimatedFlightTime() {
        return estimatedFlightTime;
    }

    public void setEstimatedFlightTime(Date estimatedFlightTime) {
        this.estimatedFlightTime = estimatedFlightTime;
    }
}

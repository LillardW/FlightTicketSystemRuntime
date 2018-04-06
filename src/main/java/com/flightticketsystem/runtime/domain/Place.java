package com.flightticketsystem.runtime.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TICKET_SYSTEM_TICKET_PLACE")
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACE_ID")
    private Long placeId;

    @JoinColumn(table = "TICKET_SYSTEM_FLIGHT", name = "FLIGHT_ID")
    private int flightId;

    @Column(name = "PLACE_NO")
    private String placeNo;

    @Column(name = "PLACE_STATUS")
    private int placeStatus;

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public int getPlaceStatus() {
        return placeStatus;
    }

    public void setPlaceStatus(int placeStatus) {
        this.placeStatus = placeStatus;
    }

    public Place(int flightId, String placeNo, int placeStatus) {
        this.flightId = flightId;
        this.placeNo = placeNo;
        this.placeStatus = placeStatus;
    }
}

package com.flightticketsystem.runtime.domain;

public class InsertTicketModel {

    private String personId;

    private String personName;

    private int flightId;

    private String flightNo;

    private long placeId;

    private String placeNo;

    private String placeLevel;

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

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public String getPlaceLevel() {
        return placeLevel;
    }

    public void setPlaceLevel(String placeLevel) {
        this.placeLevel = placeLevel;
    }

    public InsertTicketModel() {

    }

    public InsertTicketModel(String personId, String personName, int flightId, String flightNo, long placeId, String placeNo, String placeLevel) {
        this.personId = personId;
        this.personName = personName;
        this.flightId = flightId;
        this.flightNo = flightNo;
        this.placeId = placeId;
        this.placeNo = placeNo;
        this.placeLevel = placeLevel;
    }
}

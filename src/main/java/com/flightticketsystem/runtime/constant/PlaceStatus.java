package com.flightticketsystem.runtime.constant;

public enum PlaceStatus {
    AVAILABLE(1),SOLD(2);

    private int placeStatus;

    public int getPlaceStatus() {
        return placeStatus;
    }

    public void setPlaceStatus(int placeStatus) {
        this.placeStatus = placeStatus;
    }

    PlaceStatus(int placeStatus) {
        this.placeStatus = placeStatus;
    }
}

package com.flightticketsystem.runtime.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TICKET_SYSTEM_CITY")
public class City implements Serializable {

    @Column(name = "CITY_ID")
    private String cityId;

    @Column(name = "CITY_NAME")
    private String cityName;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

package com.flightticketsystem.runtime.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TICKET_SYSTEM_CITY")
public class City implements Serializable {

    @Id
    @Column(name = "CITY_ID")
    private Integer cityId;

    @Column(name = "CITY_NAME")
    private String cityName;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

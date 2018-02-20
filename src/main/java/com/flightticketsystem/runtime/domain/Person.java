package com.flightticketsystem.runtime.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Person implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "PERSON_ID")
    private Integer personId;

    @Column(name = "PERSON_NAME")
    private String personName;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

}

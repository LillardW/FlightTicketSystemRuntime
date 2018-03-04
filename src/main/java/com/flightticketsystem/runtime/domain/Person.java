package com.flightticketsystem.runtime.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Person implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "PERSON_ID", nullable = false)
    private String personId;

    @Column(name = "PERSON_NAME", nullable = false)
    private String personName;

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

}

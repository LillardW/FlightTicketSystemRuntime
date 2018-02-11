package com.flightticketsystem.runtime.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Customer implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "CUSTOMER_NAME", nullable = false, unique = true)
    private String customerName;

    @Column(name = "CUSTOMER_PASSWORD", nullable = false)
    private String passWord;

    @Column(name = "CUSTOMER_EMAIL", nullable = false)
    private String email;

    @Column(name = "CUSTOMER_MOBILE", nullable = false)
    private Integer mobile;

    @Column(name = "REGISTRATION_TIME", nullable = false)
    private Date regTime;



}

package com.flightticketsystem.runtime.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TICKET_SYSTEM_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Embedded
    private Person userOwner;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Person getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(Person userOwner) {
        this.userOwner = userOwner;
    }

    public User(Long userId, String userName, String password, String userEmail, Person userOwner) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userEmail = userEmail;
        this.userOwner = userOwner;
    }
}

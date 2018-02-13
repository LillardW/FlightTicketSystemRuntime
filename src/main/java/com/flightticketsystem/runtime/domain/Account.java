package com.flightticketsystem.runtime.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TICKET_SYSTEM_ACCOUNT")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @Column(name = "ACCOUNT_PASSWORD")
    private String password;

    @Column(name = "ACCOUNT_EMAIL")
    private String accountEmail;

    @Embedded
    private Person accountOwner;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public Person getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(Person accountOwner) {
        this.accountOwner = accountOwner;
    }

    public Account(Long accountId, String accountName, String password, String accountEmail, Person accountOwner) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.password = password;
        this.accountEmail = accountEmail;
        this.accountOwner = accountOwner;
    }
}

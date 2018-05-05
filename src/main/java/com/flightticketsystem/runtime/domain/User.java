package com.flightticketsystem.runtime.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TICKET_SYSTEM_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Embedded
    private Person accountOwner;

    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    @Column(name = "LAST_MODIFY_TIME", nullable = false)
    private Date lastModifyTime;

    @Column(name = "AUTHORITY", nullable = false)
    private int authority = 0;

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

    public Person getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(Person accountOwner) {
        this.accountOwner = accountOwner;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public User(String userName, String password, String userEmail, Person accountOwner, Date createTime, Date lastModifyTime, int authority) {
        this.userName = userName;
        this.password = password;
        this.userEmail = userEmail;
        this.accountOwner = accountOwner;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
        this.authority = authority;
    }

    public User() {

    }
}

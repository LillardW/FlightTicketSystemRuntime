package com.flightticketsystem.runtime.service;

import com.flightticketsystem.runtime.constant.ExceptionMsg;
import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SpringBootApplication
public interface BaseService {

    public Response result(ExceptionMsg msg);

    public Response result();

    public HttpServletRequest getRequest();

    public HttpSession getSession();

    public User getUser();

    public Long getUserId();

    public String getUserName();

    public String getUserIp();

    public String getPwd(String password);

    public String cookieSign(String value);
}

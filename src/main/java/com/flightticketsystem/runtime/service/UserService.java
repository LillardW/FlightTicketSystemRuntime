package com.flightticketsystem.runtime.service;

import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.ResponseData;
import com.flightticketsystem.runtime.domain.User;

import javax.servlet.http.HttpServletResponse;


public interface UserService extends BaseService {

    public ResponseData login(User user, HttpServletResponse response);

    public Response register(User user);
}

package com.flightticketsystem.runtime.service;

import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.ResponseData;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.domain.UserModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    public ResponseData login(User user, HttpServletResponse response);

    public Response register(User user);

    public Response updateUserProfile(User user);

    public User convert(UserModel userModel);
}

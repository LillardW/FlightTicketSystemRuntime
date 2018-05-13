package com.flightticketsystem.runtime.service;

import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.ResponseData;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.domain.UserModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    public ResponseData login(User user, HttpServletResponse response);

    public Response register(User user);

    public boolean updateUserProfile(UserModel userModel);

    public User convert(UserModel userModel);

    public UserModel convertToModel(User user);

    public User findUserByUserId(long userId);

    public User findUserByUserName(User user);

    public List<UserModel> searchAllUsers();
}

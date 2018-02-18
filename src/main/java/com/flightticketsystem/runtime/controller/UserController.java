package com.flightticketsystem.runtime.controller;

import com.flightticketsystem.runtime.aop.LoggerManage;
import com.flightticketsystem.runtime.constant.Constant;
import com.flightticketsystem.runtime.constant.ExceptionMsg;
import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.ResponseData;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.repository.UserRepository;
import com.flightticketsystem.runtime.service.UserService;
import com.flightticketsystem.runtime.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerManage(description = "登录")
    public ResponseData login(User user, HttpServletResponse response) {
        return userService.login(user, response);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(User user) {
        return userService.register(user);
    }
}

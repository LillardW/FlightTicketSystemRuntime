package com.flightticketsystem.runtime.controller;

import com.flightticketsystem.runtime.aop.LoggerManage;
import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.ResponseData;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestBody User user, HttpServletResponse response) {
        return userService.login(user, response);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody User user) {
        return userService.register(user);
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public Response updateProfile(@RequestBody User user) {
        return userService.updateUserProfile(user);
    }
}

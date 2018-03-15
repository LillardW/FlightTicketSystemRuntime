package com.flightticketsystem.runtime.controller;

import com.flightticketsystem.runtime.aop.LoggerManage;
import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.ResponseData;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.domain.UserModel;
import com.flightticketsystem.runtime.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpServletResponse response, Model model) {
        model.addAttribute("login",userService.login(user, response));
        return "redirect:/index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(UserModel userModel) {
        User user = userService.convert(userModel);
        userService.register(user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public Response updateProfile(@RequestBody User user) {
        return userService.updateUserProfile(user);
    }
}

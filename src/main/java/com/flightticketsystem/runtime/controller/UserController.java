package com.flightticketsystem.runtime.controller;

import com.flightticketsystem.runtime.constant.UserAuthority;
import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.ResponseData;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.domain.UserModel;
import com.flightticketsystem.runtime.repository.UserRepository;
import com.flightticketsystem.runtime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody User user, HttpServletResponse response, Model model, HttpSession session) {
        ResponseData responseData = userService.login(user, response);
        if(responseData.getRspCode().equals("000000")) {
            String result = "";
            session.setAttribute("LOGIN_SESSION_KEY_USERNAME", user.getUserName());
            session.setAttribute("currentUser",userService.convertToModel(userService.findUserByUserName(user)));
            if(user.getAuthority() == UserAuthority.NORMAL.getUserAuthority()) {
                result = "success";
            } else if (user.getAuthority() == UserAuthority.ADMIN.getUserAuthority()) {
                result =  "success";
            }
            return result;
        }else {
            return "failed";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestBody UserModel userModel) {
        User user = userService.convert(userModel);
        return userService.register(user);
    }

    @RequestMapping(value = "/updateUserProfile", method = RequestMethod.POST)
    public String updateProfile(UserModel userModel, HttpSession session) {
        boolean result = userService.updateUserProfile(userModel);
        if(result) {
            User user = userService.findUserByUserId(userModel.getUserId());
            session.setAttribute("currentUser", userService.convertToModel(user));
            return "redirect:/updateUserProfilePage";
        }
        return "redirect:/updateUserProfilePage";
    }
}

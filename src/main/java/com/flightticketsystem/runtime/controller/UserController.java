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
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> login(@RequestBody User user, HttpServletResponse response, Model model, HttpSession session) {
        ResponseData responseData = userService.login(user, response);
        User completeUser = userRepository.findByUserName(user.getUserName());
        Map<String, Object> responseResult = new HashMap<>();
        if(responseData.getRspCode().equals("000000")) {
            session.setAttribute("LOGIN_SESSION_KEY_USERNAME", user.getUserName());
            session.setAttribute("currentUser",userService.convertToModel(userService.findUserByUserName(user)));
            session.setAttribute("Authority",completeUser.getAuthority());
            responseResult.put("Result","success");
        }else {
            responseResult.put("Result","failed");
        }
        return responseResult;
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
        }
        return "redirect:/updateUserProfilePage";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("LOGIN_SESSION_KEY_USERID");
        session.removeAttribute("LOGIN_SESSION_KEY_USERNAME");
        session.removeAttribute("currentUser");
        session.removeAttribute("Authority");
        return "redirect:/index";
    }
}

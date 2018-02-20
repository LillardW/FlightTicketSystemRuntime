package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.constant.Constant;
import com.flightticketsystem.runtime.constant.ExceptionMsg;
import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.ResponseData;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.repository.UserRepository;
import com.flightticketsystem.runtime.service.BaseService;
import com.flightticketsystem.runtime.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BaseService baseService;

    public ResponseData login(User user, HttpServletResponse response) {
        try {
            User loginUser = userRepository.findByUserNameOrUserEmail(user.getUserName(), user.getUserEmail());
            if (loginUser == null) {
                return new ResponseData(ExceptionMsg.LoginNameNotExists);
            } else if (!loginUser.getPassword().equals(baseService.getPwd(user.getPassword()))) {
                return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
            }
            Cookie cookie = new Cookie(Constant.LOGIN_SESSION_KEY, baseService.cookieSign(loginUser.getUserId().toString()));
            cookie.setMaxAge(Constant.COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);
            baseService.getSession().setAttribute(Constant.LOGIN_SESSION_KEY, loginUser);
            String preUrl = "/";
            if (null != baseService.getSession().getAttribute(Constant.LAST_REFERER)) {
                preUrl = String.valueOf(baseService.getSession().getAttribute(Constant.LAST_REFERER));
                if (preUrl.indexOf("/collect?") < 0 && preUrl.indexOf("/lookAround/standard/") < 0
                        && preUrl.indexOf("/lookAround/simple/") < 0) {
                    preUrl = "/";
                }
            }
            if (preUrl.indexOf("/lookAround/standard/") >= 0) {
                preUrl = "/lookAround/standard/ALL";
            }
            if (preUrl.indexOf("/lookAround/simple/") >= 0) {
                preUrl = "/lookAround/simple/ALL";
            }
            return new ResponseData(ExceptionMsg.SUCCESS, preUrl);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("login failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    public Response register(User user) {
        try {
            User existingEmail = userRepository.findByUserEmail(user.getUserEmail());
            User existingAccountOwner = userRepository.findUserByAccountOwner_PersonId(user.getAccountOwner().getPersonId());
            if (existingEmail != null) {
                return baseService.result(ExceptionMsg.EmailUsed);
            } else if (existingAccountOwner != null) {
                return baseService.result(ExceptionMsg.AccountOwnerExists);
            } else {
                user.setPassword(baseService.getPwd(user.getPassword()));
                user.setCreateTime(new Date());
                user.setLastModifyTime(new Date());
                userRepository.save(user);
            }
        } catch (Exception e) {
            logger.error("Registration Error: " + e);
        }
        return baseService.result();
    }


}

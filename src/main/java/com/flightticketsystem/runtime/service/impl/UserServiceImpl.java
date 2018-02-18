package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.constant.Constant;
import com.flightticketsystem.runtime.constant.ExceptionMsg;
import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.ResponseData;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.repository.UserRepository;
import com.flightticketsystem.runtime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    public ResponseData login(User user, HttpServletResponse response) {
        try {
            User loginUser = userRepository.findByUserNameOrUserEmail(user.getUserName(), user.getUserEmail());
            if (loginUser == null) {
                return new ResponseData(ExceptionMsg.LoginNameNotExists);
            } else if (!loginUser.getPassword().equals(getPwd(user.getPassword()))) {
                return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
            }
            Cookie cookie = new Cookie(Constant.LOGIN_SESSION_KEY, cookieSign(loginUser.getUserId().toString()));
            cookie.setMaxAge(Constant.COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);
            getSession().setAttribute(Constant.LOGIN_SESSION_KEY, loginUser);
            String preUrl = "/";
            if (null != getSession().getAttribute(Constant.LAST_REFERER)) {
                preUrl = String.valueOf(getSession().getAttribute(Constant.LAST_REFERER));
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
                return result(ExceptionMsg.EmailUsed);
            } else if (existingAccountOwner != null) {
                return result(ExceptionMsg.AccountOwnerExists);
            } else {
                user.setPassword(getPwd(user.getPassword()));
                user.setCreateTime(new Date());
                user.setLastModifyTime(new Date());
                userRepository.save(user);
            }
        } catch (Exception e) {
            logger.error("Registration Error: " + e);
        }
        return result();
    }
}

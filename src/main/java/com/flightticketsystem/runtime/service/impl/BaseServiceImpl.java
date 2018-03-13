package com.flightticketsystem.runtime.service.impl;

import com.flightticketsystem.runtime.constant.Constant;
import com.flightticketsystem.runtime.constant.ExceptionMsg;
import com.flightticketsystem.runtime.domain.Response;
import com.flightticketsystem.runtime.domain.User;
import com.flightticketsystem.runtime.service.BaseService;
import com.flightticketsystem.runtime.utils.Des3EncryptionUtil;
import com.flightticketsystem.runtime.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class BaseServiceImpl implements BaseService {

    public Logger logger = Logger.getLogger(this.getClass());

    public Response result(ExceptionMsg msg) {
        return new Response(msg);
    }

    public Response result() {
        return new Response();
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }

    public User getUser() {
        return (User) getSession().getAttribute(Constant.LOGIN_SESSION_KEY_USERID);
    }

    public Long getUserId() {
        Long id = 0l;
        User user = getUser();
        if (user != null) {
            id = user.getUserId();
        }
        return id;
    }

    public String getUserName() {
        String userName = "SYSTEM";
        User user = getUser();
        if (user != null) {
            userName = user.getUserName();
        }
        return userName;
    }

    public String getUserIp() {
        String value = getRequest().getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return getRequest().getRemoteAddr();
        }
    }

    public String getPwd(String password) {
        try {
            String pwd = MD5Util.encrypt(password + Constant.PASSWORD_KEY);
            return pwd;
        } catch (Exception e) {
            logger.error("密码加密异常：", e);
        }
        return null;
    }

    public String cookieSign(String value) {
        try {
            value = value + Constant.PASSWORD_KEY;
            String sign = Des3EncryptionUtil.encode(Constant.DES3_KEY, value);
            return sign;
        } catch (Exception e) {
            logger.error("cookie签名异常：", e);
        }
        return null;
    }
}

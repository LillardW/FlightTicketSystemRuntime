package com.flightticketsystem.runtime.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

    public static String BASE_PATH;

    public static String LOGIN_SESSION_KEY_USERID = "FLIGHT_TICKET_SYSTEM_USERID";

    public static String LOGIN_SESSION_KEY_USERNAME = "FLIGHT_TICKET_SYSTEM_USERNAME";


    public static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";

    public static String DES3_KEY = "9964DYByKL967c3308imytCB";

    public static String default_logo = "img/logo.jpg";

    public static String userAgent = "Mozilla";

    public static String default_Profile = BASE_PATH + "/img/logo.jpg";

    public static String LAST_REFERER = "LAST_REFERER";

    public static int COOKIE_TIMEOUT = 24 * 60 * 60;


    @Autowired(required = true)
    public void setBasePath(@Value("${favorites.base.path}") String basePath) {
        Constant.BASE_PATH = basePath;
    }


}

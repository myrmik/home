package com.asevastyanov.torrentEater.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends BaseController {
    public final String LOGIN_VIEW = "login";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return LOGIN_VIEW;
    }
}

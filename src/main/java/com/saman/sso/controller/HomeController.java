package com.saman.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController implements BaseController {

    @Override
    @GetMapping(value = "/")
    public String view() {
        return "register_client.html";
    }
}

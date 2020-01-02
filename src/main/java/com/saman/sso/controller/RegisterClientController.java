package com.saman.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterClientController implements BaseController {

    @Override
    @GetMapping(value = "client/view.page")
    public String view(){
        return "client_management.html";
    }
}

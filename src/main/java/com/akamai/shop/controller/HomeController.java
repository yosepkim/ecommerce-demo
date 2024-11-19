package com.akamai.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = { "/", "/products/**", "/order-confirmation"})
    public String homePage() {
        return "index";
    }
}

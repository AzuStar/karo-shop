package com.karo.shop.controllers;

import com.karo.shop.session.SessionData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("sess")
public class CartController {

    @GetMapping("/cart")
    public String hook(@ModelAttribute("sess") SessionData session, ModelMap model) {
        return "cart";
    }

    @ModelAttribute("sess")
    public SessionData sess() {
        return new SessionData();
    }

}
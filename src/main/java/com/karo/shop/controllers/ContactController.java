package com.karo.shop.controllers;

import com.karo.shop.session.SessionData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes("sess")
public class ContactController {

    @GetMapping("/contact")
    public ModelAndView hook(ModelMap model) {
        return new ModelAndView("contact");
    }

}

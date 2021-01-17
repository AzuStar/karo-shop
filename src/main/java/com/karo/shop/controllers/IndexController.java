package com.karo.shop.controllers;

import java.util.ArrayList;

import com.karo.shop.data.ShopItem;
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
public class IndexController {

    @GetMapping("/")
    public ModelAndView hook(@ModelAttribute("sess") SessionData session, ModelMap model) {
        model.addAttribute("items", ShopItem.ExistingItems.values());
        model.addAttribute("sesstoken", session.userToken);

        // model.addAttribute("useremail", );
        return new ModelAndView("index", model);
    }

    @ModelAttribute("sess")
    public SessionData sess() {
        return new SessionData();
    }

}

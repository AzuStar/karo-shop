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

@Controller
@RequestMapping("/")
@SessionAttributes("sess")
public class IndexController {

    @GetMapping("/")
    public String hook(@ModelAttribute("sess") SessionData session, ModelMap model) {
        model.addAttribute("items", ShopItem.ExistingItems.values());
        return "index";
    }

    @ModelAttribute("sess")
    public SessionData sess() {
        return new SessionData();
    }

}

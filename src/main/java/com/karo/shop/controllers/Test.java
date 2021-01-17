package com.karo.shop.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.karo.shop.DBSocket;
import com.karo.shop.AppDatabase;
import com.karo.shop.data.ShopItem;
import com.karo.shop.session.SessionData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes("cart")
public class Test {

    // @GetMapping("/test")
    // public String pageHook(@ModelAttribute("cart") CartSession session, Model
    // model) {
    // model.addAttribute("incart", session.getCartItems().keySet());
    // return "test";
    // }

    @RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
    public String requestHook(@RequestParam(defaultValue = "false", required = false) boolean reset,
            @ModelAttribute("sess") SessionData session, Model model) {

        
        model.addAttribute("incart", ShopItem.ExistingItems.values().toArray());
        // if (!reset)
        // session.getCartItems().put(new ShopItem("ItemXXX", "Really test item", 20),
        // 1);
        // else
        // session.getCartItems().clear();
        return "test";
    }

    @ModelAttribute("sess")
    public SessionData cart() {
        return new SessionData();
    }

}
package com.karo.shop.controllers;

import com.karo.shop.data.ShopItem;
import com.karo.shop.session.SessionData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("sess")
public class ShopItemController {

    @GetMapping("/item")
    public String hook(@RequestParam int id, @ModelAttribute("sess") SessionData session, ModelMap model) {
        model.addAttribute("item", ShopItem.ExistingItems.get(id));
        return "item";
    }

    @ModelAttribute("sess")
    public SessionData sess() {
        return new SessionData();
    }

}

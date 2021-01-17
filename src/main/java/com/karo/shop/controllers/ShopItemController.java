package com.karo.shop.controllers;

import com.karo.shop.AppDatabase;
import com.karo.shop.data.ShopItem;
import com.karo.shop.session.SessionData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes("sess")
public class ShopItemController {

    @GetMapping("/item")
    public ModelAndView hookItem(@RequestParam int id, ModelMap model) {
        model.addAttribute("item", ShopItem.ExistingItems.get(id));
        return new ModelAndView("item", model);
    }

    // @PostMapping("/star-additem")
    // public ModelAndView hookAddItem(@RequestParam int id, @ModelAttribute("sess") SessionData session, ModelMap model) {
    //     AppDatabase.mainDB.execQuery("");
    //     return new ModelAndView("redirect:/", model);
    // }

    @ModelAttribute("sess")
    public SessionData sess() {
        return new SessionData();
    }

}

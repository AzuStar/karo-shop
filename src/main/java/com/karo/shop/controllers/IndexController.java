package com.karo.shop.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.karo.shop.AppDatabase;
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
    public ModelAndView hook(@ModelAttribute("sess") SessionData session, ModelMap model) throws SQLException {
        model.addAttribute("items", ShopItem.ExistingItems.values());

        if (!session.userToken.isEmpty()) {
            ResultSet result = AppDatabase.mainDB.execQuery(
                    "select sum(itemquantity) from cartitem where cartitem.cartid=(select currentcart from users where usertoken=\""
                            + session.userToken + "\")");
            model.addAttribute("cartquantity", result.getInt(1));
            result = AppDatabase.mainDB.execQuery(
                    "select sum(shopitem.price*cartitem.itemquantity) from cartitem join shopitem on cartitem.itemid=shopitem.itemid where cartitem.cartid=(select currentcart from users where usertoken=\""
                            + session.userToken + "\");");
            model.addAttribute("cartprice", result.getDouble(1));
        }
        return new ModelAndView("index", model);
    }

    @ModelAttribute("sess")
    public SessionData sess() {
        return new SessionData();
    }

}

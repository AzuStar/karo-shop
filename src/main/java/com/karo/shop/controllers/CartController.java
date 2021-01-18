package com.karo.shop.controllers;

import java.sql.ResultSet;

import com.karo.shop.AppDatabase;
import com.karo.shop.DBSocket;
import com.karo.shop.data.Cart;
import com.karo.shop.data.CartItem;
import com.karo.shop.data.ShopItem;
import com.karo.shop.session.SessionData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes("sess")
public class CartController {

    @GetMapping("/cart")
    public ModelAndView hookCart(@ModelAttribute("sess") SessionData session, ModelMap model) throws Exception {
        if (session.userToken.isEmpty())
            return new ModelAndView("redirect:/login");


        Cart cart = new Cart();
        ResultSet rs;
        rs = AppDatabase.mainDB.execQuery(
                "select sum(shopitem.price*cartitem.itemquantity) from cartitem join shopitem on cartitem.itemid=shopitem.itemid where cartitem.cartid=(select currentcart from users where usertoken=\""
                        + session.userToken + "\");");
        cart.price = rs.getDouble(1);
        rs = AppDatabase.mainDB.execQuery(
                "select * from cartitem join shopitem on shopitem.itemid=cartitem.itemid where cartitem.cartid=(select currentcart from users where usertoken=\""
                        + session.userToken + "\")");

        while (rs.next()) {
            ShopItem iitem = new ShopItem(rs.getInt("itemid"), rs.getString("name"),
                    rs.getString("description"), rs.getDouble("price"),
                    rs.getString("imglink"));
            int quantity = rs.getInt("itemquantity");
            cart.items.add(new CartItem(iitem, quantity * iitem.price, quantity));
        }
        model.addAttribute("cart", cart);
        return new ModelAndView("cart", model);
    }

    @GetMapping("/star-additem")
    public ModelAndView hookAddItem(@RequestParam(required = true) int id, @ModelAttribute("sess") SessionData session,
            ModelMap model) throws Exception {
        if (session.userToken.isEmpty())
            return new ModelAndView("redirect:/login");

        AppDatabase.mainDB.execQuery("insert into cartitem values((select currentcart from users where usertoken=\""
                + session.userToken + "\"), " + id + ", 1);");
        return new ModelAndView("redirect:/");
    }

    @ModelAttribute("sess")
    public SessionData sess() {
        return new SessionData();
    }

}

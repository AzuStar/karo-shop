package com.karo.shop.controllers;

import java.sql.ResultSet;

import com.karo.shop.AppDatabase;
import com.karo.shop.data.User;
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
import org.springframework.web.servlet.View;

@Controller
@RequestMapping("/")
@SessionAttributes("sess")
public class LoginController {

    @GetMapping("/login")
    public ModelAndView hookLoginPage(@RequestParam(defaultValue = "false") boolean wrong,
            @ModelAttribute("sess") SessionData session, ModelMap model) {

        if (!session.userToken.isEmpty())
            return new ModelAndView("redirect:/");
        model.addAttribute("wrong", wrong);
        return new ModelAndView("login");
    }

    @PostMapping("/star-login")
    public ModelAndView hookStarLoginPage(@RequestParam String email, @RequestParam String passwd,
            @ModelAttribute("sess") SessionData session, ModelMap model) throws Exception {
        ResultSet result = AppDatabase.mainDB.execQuery(
                "select * from users where users.email=\"" + email + "\" and users.passwd=\"" + passwd + "\"");

        if (result == null) {
            model.addAttribute("wrong", true);
            return new ModelAndView("redirect:/login", model);
        }
        session.setUserSession(result.getString("usertoken"),
                new User(result.getString("email"), result.getString("phone")));
        return new ModelAndView("redirect:/", model);
    }

    @ModelAttribute("sess")
    public SessionData sess() {
        return new SessionData();
    }

}

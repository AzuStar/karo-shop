package com.karo.shop.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.karo.shop.AppDatabase;
import com.karo.shop.data.User;
import com.karo.shop.session.SessionData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
@SessionAttributes("sess")
public class RegistrationController {

    @GetMapping("/registration")
    public String hook(ModelMap model) {
        return "reg";
    }

    @PostMapping("/star-registration")
    public ModelAndView hookTarget(@RequestParam String email, @RequestParam String passwd, @RequestParam String phone,
            ModelMap model) throws Exception {
        AppDatabase.mainDB.execQuery("INSERT INTO cart(userid) VALUES(\"" + email + "\");");
        ResultSet res = AppDatabase.mainDB.execQuery("SELECT last_insert_rowid();");
        try {
            res.next();
            AppDatabase.mainDB.execQuery("INSERT INTO users VALUES (\"" + email + "\", \"" + passwd + "\", \"" + phone
                    + "\", \"" + res.getInt(1) + "\", \"" + hash(email) + "\");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/login");
    }

    private int hash(String s) {
        return (s.hashCode() << 3) ^ 5;
    }

}

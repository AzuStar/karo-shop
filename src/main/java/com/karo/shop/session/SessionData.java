package com.karo.shop.session;

import javax.websocket.Session;

import com.karo.shop.data.User;

public class SessionData {

    public String userToken = "";
    public User user;
    
    public SessionData(){
    }

    public void setUserSession(String userToken, User user){
        this.userToken = userToken;
        this.user = user;
    }


}

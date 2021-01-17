package com.karo.shop.session;

import javax.websocket.Session;

public class SessionData {

    public String userToken = "";

    public SessionData(){
    }

    public void setUserSession(String userToken){
        this.userToken = userToken;
    }


}

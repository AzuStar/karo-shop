package com.karo.shop.session;

import javax.websocket.Session;

public class SessionData {

    // public CartSession Cart;
    public UserSession currentUser;

    public SessionData(){
        // Cart = new CartSession();
        currentUser = null;
    }

    public void setUserSession(String userToken){

    }


}

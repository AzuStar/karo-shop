package com.karo.shop.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.karo.shop.data.ShopItem;


public class CartSession {

    public ArrayList<CartItem> cart = new ArrayList<>();

    public List<CartItem> getCart() {
        return cart;
    }

    public CartSession() {
    }

    class CartItem {
        public ShopItem item;
        private int quantity;
        private double totalPrice;

        public CartItem(ShopItem item){
            this.item = item;
            setQuantity(1);
        }

        public void setQuantity(int quant){
            quantity = quant;
            totalPrice = quantity * item.price;
        }

        public int getQuantity(){
            return quantity;
        }

        public double getTotalPrice(){
            return totalPrice;
        }

    }
}

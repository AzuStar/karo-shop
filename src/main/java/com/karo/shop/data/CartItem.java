package com.karo.shop.data;

import java.util.ArrayList;

public class CartItem {

    public ShopItem meta;
    public double total;
    public int quantity;

    public CartItem(ShopItem meta, double total, int quantity) {
        this.meta = meta;
        this.total = total;
        this.quantity = quantity;
    }

}

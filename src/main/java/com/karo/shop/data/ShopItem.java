package com.karo.shop.data;

import java.util.HashMap;

public class ShopItem {

    public static final HashMap<Integer, ShopItem> ExistingItems = new HashMap<>();

    public final int id;
    public final String name;
    public final String description;
    public final String imgLink;
    public final double price;

    public ShopItem(int id, String name, String description, double price, String imgLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgLink = imgLink;
        ExistingItems.put(this.id, this);
    }
}

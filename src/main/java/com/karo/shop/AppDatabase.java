package com.karo.shop;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.karo.shop.data.ShopItem;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppDatabase implements ApplicationListener<ContextRefreshedEvent> {

    public static final String databaseName = "Shop.db";
    public static DBSocket mainDB = new DBSocket(databaseName);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ResultSet items = AppDatabase.mainDB.execQuery("select * from shopitem;");
        try {
            while (items.next()) {
                ShopItem.ExistingItems.put(items.getInt(1), new ShopItem(items.getInt(1), items.getString(2),
                        items.getString(3), items.getDouble(4), items.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

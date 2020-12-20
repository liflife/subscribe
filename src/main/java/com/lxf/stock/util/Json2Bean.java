package com.lxf.stock.util;

import com.alibaba.fastjson.JSONObject;
import com.lxf.stock.bean.Stock;
import com.lxf.stock.bean.User;

import java.util.List;

public class Json2Bean {
    public static List<User> json2User(String userJson) {
        List<User> users = JSONObject.parseArray(userJson, User.class);
        return users;
    }
    public static Stock json2Stock(String stockStr) {
        String[] split = stockStr.split(",");
        Stock stock = new Stock();
        stock.setGuPiaoName(split[0]);
        stock.setKaiPianJia(split[1]);
        stock.setShouPianJia4Yesterday(split[2]);
        stock.setCurrentPrice(split[3]);
        stock.setMaxPrice(split[4]);
        stock.setMinxPrice(split[5]);
        return stock;
    }
}

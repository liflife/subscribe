package com.lxf.stock.bean;

import java.util.List;

public class User {
    private String userName;
    private String uid;
    private List<StockRule> stockRules;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<StockRule> getStockRules() {
        return stockRules;
    }

    public void setStockRules(List<StockRule> stockRules) {
        this.stockRules = stockRules;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

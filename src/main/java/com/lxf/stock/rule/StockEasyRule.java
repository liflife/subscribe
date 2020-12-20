package com.lxf.stock.rule;

import com.lxf.stock.bean.Stock;
import com.lxf.stock.bean.User;
import org.jeasy.rules.annotation.*;

import java.util.List;

@Rule(name = "StockEasyRule", description = "if it rains then take an umbrella")
public class StockEasyRule {
    @Condition
    public boolean when(@Fact("stocks") List<Stock> stocks,@Fact("user") User user) {
        return true;
    }

    @Action(order = 1)
    public void then() {
        System.out.println("It rains, take an umbrella!");
    }
}

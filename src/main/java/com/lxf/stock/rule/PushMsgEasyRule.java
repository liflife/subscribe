package com.lxf.stock.rule;

import com.alibaba.fastjson.JSON;
import com.lxf.stock.SendServer;
import com.lxf.stock.bean.Stock;
import com.lxf.stock.bean.StockRule;
import com.lxf.stock.bean.User;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

import java.util.List;
import java.util.stream.Collectors;

@Rule(name = "PushMsgEasyRule", description = "if it rains then take an umbrella")
public class PushMsgEasyRule {
    private SendServer sendServer;
    public PushMsgEasyRule(SendServer sendServer){
        this.sendServer = sendServer;
    }

    @Condition
    public boolean when(@Fact("stocks") List<Stock> stocks, @Fact("user") User user, Facts facts) {
        List<Stock> collect = stocks.stream().collect(Collectors.toList());
        List<String> collect1 = user.getStockRules().stream().map(StockRule::getStockCode).collect(Collectors.toList());
        facts.put("ownStocks",stocks);
        System.out.println("stocks");
        return true;
    }

    @Action(order = 1)
    public void then(@Fact("ownStocks") List<Stock> ownStocks) {
        String text = "股价触发了你设置的规则，赶紧看看吧！";
        String desp = ownStocks.stream().map(stock -> {
            return stock.toMKString();
        }).collect(Collectors.joining("\n"));
        desp ="## 股票列表 \n"+desp;
        sendServer.pushMsg(text,desp);
    }
}

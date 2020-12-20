package com.lxf.stock.rule;

import com.lxf.stock.StockMain;
import com.lxf.stock.service.MessagePushService;
import com.lxf.stock.bean.SendMessage;
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
    private MessagePushService messagePushServic;
    public PushMsgEasyRule(MessagePushService messagePushServic){
        this.messagePushServic = messagePushServic;
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
        SendMessage sendMessage = new SendMessage();
        sendMessage.setTitle(StockMain.title);
        sendMessage.setContent(ownStocks);
        messagePushServic.pushMsg(sendMessage);
    }
}

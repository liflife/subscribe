package com.lxf.stock.rule;

import com.lxf.stock.StockMain;
import com.lxf.stock.service.MessagePushService;
import com.lxf.stock.bean.SendMessage;
import com.lxf.stock.bean.Stock;
import com.lxf.stock.bean.StockRule;
import com.lxf.stock.bean.User;
import com.zjiecode.wxpusher.client.bean.WxUser;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Rule(name = "PushMsgEasyRule", description = "if it rains then take an umbrella")
public class PushMsgEasyRule {
    private MessagePushService messagePushServic;
    public PushMsgEasyRule(MessagePushService messagePushServic){
        this.messagePushServic = messagePushServic;
    }

    @Condition
    public boolean when(@Fact("stocks") List<Stock> stocks, @Fact("user") User user, Facts facts) {
        List<Stock> allStockCode = stocks.stream().collect(Collectors.toList());
        List<StockRule> stockRules = user.getStockRules();
        Map<String, Stock> allStockCodeMap = allStockCode.stream().collect(Collectors.toMap(Stock::getGuPiaoCode, Function.identity(), (key1, key2) -> key2));
        List<Stock> ownStocks = stockRules.stream().map(stockRule -> {
            String stockCode = stockRule.getStockCode();
            return allStockCodeMap.get(stockCode);
        }).filter(Objects::nonNull).collect(Collectors.toList());
        facts.put("ownStocks",ownStocks);

        if(ownStocks.size()!=0){
            return true;
        }
        System.out.println("没有配置需要监控的股票，不发消息");
        return false;
    }

    @Action(order = 1)
    public void then(@Fact("ownStocks") List<Stock> ownStocks,@Fact("user") User user) {
        if(ownStocks.size()<=0){
            return ;
        }
        String uid = user.getUid();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setTitle(StockMain.title);
        sendMessage.setContent(ownStocks);
        sendMessage.setUid(uid);
        messagePushServic.pushMsg(sendMessage);
    }
}

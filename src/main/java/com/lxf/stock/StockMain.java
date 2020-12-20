package com.lxf.stock;

import com.lxf.stock.bean.Stock;
import com.lxf.stock.bean.StockRule;
import com.lxf.stock.bean.User;
import com.lxf.stock.rule.PushMsgEasyRule;
import com.lxf.stock.rule.StockEasyRule;
import com.lxf.stock.util.Json2Bean;
import com.lxf.stock.util.LoadFileResource;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StockMain {
   private static String pushToken="SCU137214Tbc71f20d0f354c996a40ea85df880dde5fdead997df28";

    public static void main(String[] args) {
        StockService stockService = new StockService();
        SendServer sendServer = new SendServer(pushToken);

        // define rules
        Rules rules = new Rules();
        rules.register(new PushMsgEasyRule(sendServer));
        // fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();


        String s = LoadFileResource.loadConfigJsonFromFile();
        List<User> users = Json2Bean.json2User(s);
        List<String> stockCodes = users.stream().map(User::getStockRules).flatMap(List::stream).map(StockRule::getStockCode).collect(Collectors.toList());
        List<Stock> stocks = stockCodes.stream().map(stockCode -> {
            String atockStr = stockService.queryStock(stockCode);
            if(atockStr==null){
                return null;
            }
            Stock stock = Json2Bean.json2Stock(atockStr);
            stock.setGuPiaoCode(stockCode);
            return stock;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        for (User user : users) {
            Facts facts = new Facts();
            facts.put("stocks", stocks);
            facts.put("user", user);
            rulesEngine.fire(rules, facts);
        }

    }
}

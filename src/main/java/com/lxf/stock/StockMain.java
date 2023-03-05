package com.lxf.stock;

import com.lxf.stock.bean.SendMessage;
import com.lxf.stock.bean.Stock;
import com.lxf.stock.bean.StockRule;
import com.lxf.stock.bean.User;
import com.lxf.stock.rule.PushMsgEasyRule;
import com.lxf.stock.service.*;
import com.lxf.stock.util.Json2Bean;
import com.lxf.stock.util.LoadFileResource;
import com.unfbx.chatgpt.OpenAiClient;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StockMain {
    private static Logger logger = LoggerFactory.getLogger(StockMain.class);
    public static String title = "股价触发了你设置的规则，赶紧看看吧！";
    public static String titleChatGpt = "ChatGpt";

    public static void main(String[] args) {
        if (args.length < 3) {
            logger.info("任务启动失败");
            logger.warn("pushToken参数缺失，请检查是否在Github Secrets中配置pushToken参数");
            return;
        }
        String serviceToken = args[0];
        String pushaddToken = args[1];
        String wxpusherToken = args[2];
        String openAiKeys = args[3];
        sendOpenAi(serviceToken,pushaddToken,wxpusherToken,openAiKeys);
    }

    public static void sendOpenAi(String serviceToken,String pushaddToken,String wxpusherToken,String openAiKeys){
        MessagePushService messagePushService = new WxpusherSendService(wxpusherToken);

        UserService userService = new UserService(wxpusherToken);
        String s = LoadFileResource.loadConfigJsonFromFile();
        List<User> users = Json2Bean.json2User(s);
        OpenAiClient openAiClient = new OpenAiClient(openAiKeys);
        ChatGptService chatGptService = new ChatGptService(openAiClient);
        List<String> list = chatGptService.queryChatGpt("三体人是什么？");
        for (User user : users) {
            // define rules
            String uid = user.getUid();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setTitle(StockMain.titleChatGpt);
            sendMessage.setContent(list);
            sendMessage.setUid(uid);
            messagePushService.pushMsg(sendMessage);
        }
    }

    public static void sendStock(String serviceToken,String pushaddToken,String wxpusherToken){
        boolean isFlag =true;
        DateTime now = DateTime.now(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Shanghai")));
        int dayOfWeek = now.getDayOfWeek();
        int hourOfDay = now.getHourOfDay();
        if(1 <= dayOfWeek && dayOfWeek<=5){
            if(9 <=hourOfDay && hourOfDay <=15){
                isFlag=false;
            }
        }
        if(isFlag){
            System.out.println(now.toString("时间不符合，不调用：")+now.toString("yyyy-MM-dd HH:mm:ss"));
            return;
        }

//        String wxpusherToken = "";
        StockService stockService = new StockService();
        MessagePushService messagePushService = new WxpusherSendService(wxpusherToken);
        UserService userService = new UserService(wxpusherToken);

        // define rules
        Rules rules = new Rules();
        rules.register(new PushMsgEasyRule(messagePushService));
        // fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();

        String s = LoadFileResource.loadConfigJsonFromFile();
        List<User> users = Json2Bean.json2User(s);
        userService.queryUserUid(users);
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

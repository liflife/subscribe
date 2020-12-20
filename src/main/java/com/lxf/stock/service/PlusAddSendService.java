package com.lxf.stock.service;

import com.alibaba.fastjson.JSONObject;
import com.lxf.stock.api.Apis;
import com.lxf.stock.bean.SendMessage;
import com.lxf.stock.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PlusAddSendService implements MessagePushService{
    private static Logger logger = LoggerFactory.getLogger(PlusAddSendService.class);
    private String token;

    public PlusAddSendService(String token) {
        this.token = token;
    }

    @Override
    public boolean pushMsg(SendMessage sendMessage) {
        String url = Apis.PushUrl.PlushAddPush;
        try {
            JSONObject jsonObjectPara = new JSONObject();
            jsonObjectPara.put("token",token);
            jsonObjectPara.put("title",sendMessage.getTitle());
            jsonObjectPara.put("content",new HtmlMessageFormat().format(sendMessage.getContent()));
            jsonObjectPara.put("template",sendMessage.getTemplate()!=null?sendMessage.getTemplate():"html");
            jsonObjectPara.put("topic","Stock");
            JSONObject jsonObject = HttpUtil.doPost(url, jsonObjectPara.toJSONString());
            if (jsonObject != null && 200==jsonObject.getInteger("code").intValue()) {
                logger.info("任务状态推送成功");
                return true;
            } else {
                logger.info("任务状态推送失败:",jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

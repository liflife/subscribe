package com.lxf.stock.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxf.stock.api.Apis;
import com.lxf.stock.bean.SendMessage;
import com.lxf.stock.util.HttpUtil;
import com.zjiecode.wxpusher.client.WxPusher;
import com.zjiecode.wxpusher.client.bean.Message;
import com.zjiecode.wxpusher.client.bean.MessageResult;
import com.zjiecode.wxpusher.client.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WxpusherSendService  implements MessagePushService  {
    private static Logger logger = LoggerFactory.getLogger(WxpusherSendService.class);
    private String token;

    public WxpusherSendService(String token) {
        this.token = token;
    }

    @Override
    public boolean pushMsg(SendMessage sendMessage) {
        try {
        Message message = new Message();
        message.setAppToken(token);
        message.setContentType(Message.CONTENT_TYPE_MD);
        message.setContent(new MarkDownMessageFormat().format(sendMessage.getContent()));
        message.setUid(sendMessage.getUid());
        message.setUrl(Apis.PushUrl.wxpuserPush);
        Result<List<MessageResult>> result = WxPusher.send(message);
        Integer code = result.getCode();
        if (1000==code.intValue()) {
            logger.info("任务状态推送成功");
            return true;
        } else {
            logger.info("任务状态推送失败:{}", JSON.toJSONString(result));
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

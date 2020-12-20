package com.lxf.stock;

import com.alibaba.fastjson.JSONObject;
import com.lxf.stock.api.Apis;
import com.lxf.stock.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.util.Arrays;


public class SendServer {
    private static Logger logger = LoggerFactory.getLogger(SendServer.class);
    private String pushToken;

    public SendServer(String pushToken) {
        this.pushToken = pushToken;
    }

    public boolean pushMsg(String text, String desp) {
        String url = Apis.PushUrl.ServerPush + pushToken + ".send";
        String pushBody = "text=" + text + "&desp=" + desp;
        try {
            JSONObject jsonObject = HttpUtil.doPost(url, pushBody);
            if (jsonObject != null && "success".equals(jsonObject.getString("errmsg"))) {
                logger.info("任务状态推送成功");
                return true;
            } else {
                logger.info(Arrays.toString(SSLContext.getDefault().getSupportedSSLParameters().getProtocols()));
                logger.info(Arrays.toString(SSLContext.getDefault().createSSLEngine().getEnabledProtocols()));
                logger.info("任务状态推送失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

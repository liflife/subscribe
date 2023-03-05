package com.lxf.stock.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxf.stock.bean.ChatGpt;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class SubscribeService {
    static String url="http://119.3.208.215:8080";
   static OkHttpClient  client = new OkHttpClient().newBuilder().build();

    public List<ChatGpt> queryChatGptData() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid","1");
        jsonObject.put("status","2");
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonObject.toJSONString());
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url+"/chatGpt/queryChatGptByStatus?uid=1&status=1")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        if(response.code()==200){
            String string = response.body().string();
            System.out.println(string);
            List<ChatGpt> chatGpts = JSONArray.parseArray(string, ChatGpt.class);
            return chatGpts;
        }
        return null;
    }


    public String updateChatGpt(ChatGpt chatGpt) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid",chatGpt.getUid());
        jsonObject.put("uuid",chatGpt.getUuid());
        jsonObject.put("choices",chatGpt.getChoices());

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,jsonObject.toJSONString());
        Request request = new Request.Builder()
                .url(url+"/chatGpt/updateChatGpt")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        if(response.code()==200){
            String string = response.body().string();
            System.out.println(string);
            return string;
        }
        return response.body().string();
    }

    public static void main(String[] args) throws IOException {
        List<ChatGpt> chatGpts = new SubscribeService().queryChatGptData();
        System.out.println(chatGpts);
    }
}

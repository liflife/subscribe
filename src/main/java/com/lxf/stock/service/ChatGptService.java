package com.lxf.stock.service;

import cn.hutool.core.collection.CollectionUtil;
import com.lxf.stock.bean.ChatGpt;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChatGptService {
    private static Logger logger = LoggerFactory.getLogger(ChatGptService.class);
    SubscribeService subscribeService = new SubscribeService();
    //配置api keys
    OpenAiClient openAiClient;


    public ChatGptService(OpenAiClient openAiClient){
        this.openAiClient = openAiClient;
    }
    public  List<String> queryChatGpt(String question){
        //配置api keys
        CompletionResponse completions = openAiClient.completions(question);
        Choice[] choices = completions.getChoices();
        List<String> collect = Arrays.stream(choices).map(Choice::getText).collect(Collectors.toList());
        logger.info("ChatGpt:question={}\r\n answer={} ",question,collect);
        return collect;

    }


    public void runChatGptJob() {
        try {
            List<ChatGpt> chatGpts = subscribeService.queryChatGptData();
            logger.info("查询到{}数据了",chatGpts.size());
            if(CollectionUtil.isNotEmpty(chatGpts)){
                for (ChatGpt chatGpt : chatGpts) {
                    String prompt = chatGpt.getPrompt();
                    List<String> list = queryChatGpt(prompt);
                    String choices = list.stream().collect(Collectors.joining("\r\n"));
                    chatGpt.setChoices(choices);
                    subscribeService.updateChatGpt(chatGpt);
                }
            }
        } catch (Exception e) {
            logger.error("runChatGptJob:exp=",e);
        }
    }

}

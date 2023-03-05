package com.lxf.stock.service;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChatGptService {
    private static Logger logger = LoggerFactory.getLogger(ChatGptService.class);

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



}

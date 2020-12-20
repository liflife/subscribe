package com.lxf.stock.bean;

import java.util.List;

public class SendMessage {
    private String token;
    private String title;
    private List<Stock> content;
    private String template;
    private String topic;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Stock> getContent() {
        return content;
    }

    public void setContent(List<Stock> content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

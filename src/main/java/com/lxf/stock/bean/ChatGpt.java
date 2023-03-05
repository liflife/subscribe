package com.lxf.stock.bean;

import lombok.Data;

@Data
public class ChatGpt {
    private Long id;
    private String uid;
    private String uuid;
    private String prompt;
    private String model;
    private String choices;
    private Integer status;
    private Long createTime;
    private Long updateTime;
}

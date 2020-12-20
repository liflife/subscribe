package com.lxf.stock.service;

import com.lxf.stock.bean.SendMessage;

public interface MessagePushService {
    boolean pushMsg(SendMessage sendMessage);
}

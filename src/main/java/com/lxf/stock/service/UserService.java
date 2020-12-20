package com.lxf.stock.service;

import com.alibaba.fastjson.JSONObject;
import com.lxf.stock.bean.User;
import com.zjiecode.wxpusher.client.WxPusher;
import com.zjiecode.wxpusher.client.bean.Page;
import com.zjiecode.wxpusher.client.bean.Result;
import com.zjiecode.wxpusher.client.bean.WxUser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {
    private String token;
    public UserService(String token){
        this.token=token;
    }
    public void queryUserUid(List<User> users){
        //分页查询全部用户
        Result<Page<WxUser>> wxUsers = WxPusher.queryWxUser(token, 1, 50);
        Map<String, String> userMap = wxUsers.getData().getRecords().stream().collect(Collectors.toMap(WxUser::getNickName, WxUser::getUid, (key1, key2) -> key2));
        for (User user : users) {
            user.setUid(userMap.get(user.getUserName()));
        }
    }
}

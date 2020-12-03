package com.example.projecttraining.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactManager {
    //尚未同意的好友请求列表
    public static Map<String,List<String> > newFriends=new HashMap<>();
    //已发送请求尚未同意的好友请求列表
    public static Map<String,List<String>> notAcceptFriends=new HashMap<>();

}

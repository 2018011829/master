package com.hyphenate.easeui.utils;

import android.content.Context;

import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.easeui.GlideRoundImage;

/**
 * 一个和ParentUtil相似的类，在EaseUI中，无法访问ParentUtil，所以创建此类，保存一些状态
 * @author 雨
 */

public class EaseParentUtil {
    //服务器地址;
    public static final String SERVICE_ADDRESS="http://192.168.137.1:8080/tiantian/";
    //服务器端用户头像地址
    public static final String SETVER_AVATAR = "http://192.168.137.1:8080/tiantian/avatar/";
    //当前登录用户的头像地址
    public static String currentUserAvatar="";
    public static String currentUserNickname="";
    //聊天好友的头像地址
    public static String toChatUserAvator="";
    //聊天好友的备注
    public static String toChatUserRemark="";
    //聊天好友的昵称，供展示个人信息时使用，平时不用
    public static String tochatUserNickname="";
    public static boolean isContactAddedOrDeleted=false;

    //得到一个Glide的RequestOptions，用来加载圆角8dp的图像
    public static RequestOptions getRoundImageTransform(Context context) {
        RequestOptions roundImageTransform=new RequestOptions().transform((new GlideRoundImage(context,8)));
        return roundImageTransform;
    }

}

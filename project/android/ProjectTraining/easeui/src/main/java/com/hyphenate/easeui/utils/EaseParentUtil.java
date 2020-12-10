package com.hyphenate.easeui.utils;

import android.content.Context;

import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.easeui.GlideRoundImage;

/**
 * 一个和ParentUtil相似的类，在EaseUI中，无法访问ParentUtil，所以创建此类，保存一些状态
 * @author 雨
 */

public class EaseParentUtil {
    //当前登录用户的头像地址
    public static String currentUserAvatar="";
    public static String currentUserNickname="";
    //聊天好友的头像地址
    public static String toChatUserAvator="";
    public static String toChatUserNickname="";
    public static boolean isContactAddedOrDeleted=false;

    //得到一个Glide的RequestOptions，用来加载圆角8dp的图像
    public static RequestOptions getRoundImageTransform(Context context) {
        RequestOptions roundImageTransform=new RequestOptions().transform((new GlideRoundImage(context,8)));
        return roundImageTransform;
    }

}

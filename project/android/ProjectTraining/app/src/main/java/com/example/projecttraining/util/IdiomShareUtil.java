package com.example.projecttraining.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

public class IdiomShareUtil {

    /**
     * 检测手机是否安装某个应用
     *
     * @param context
     * @param appPackageName 应用包名
     * @return true-安装，false-未安装
     */
    private static boolean isAppInstall(Context context, String appPackageName) {
        // 获取packagemanager
        PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (appPackageName.equals(pn)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 分享到微信、微博、qq
     * @param context
     * @param uri
     * @param appPackageName
     */
    public static void shareIdiom(Context context, Uri uri,String appPackageName){
        // 如果已经安装，则可以直接分享；
        // 如果没有安装，则跳转至下载界面
        if(isAppInstall(context, appPackageName)){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setPackage(appPackageName);
            intent.setType("image/jpeg");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"请先安装软件！",Toast.LENGTH_SHORT).show();
            launchAppDetail(context,appPackageName,"");
        }
    }

    /**
     * 分享前必须执行本代码，主要用于兼容SDK18以上的系统
     */
    public static void checkFileUriExposure() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,
     *                  否则调转到目标市场的应用详情界面
     */
    private static void launchAppDetail(Context mContext, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent =new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.projecttraining.idiom.api;

import android.util.Log;

import com.example.projecttraining.idiom.entity.IdiomResult;
import com.example.projecttraining.idiom.entity.Result;
import com.example.projecttraining.util.IdiomJsonUtil;

import java.net.URLEncoder;
import java.util.List;

/**
 * 2020-11-25
 *
 * @author lrf
 */
public class SearchIdiom {
    public static final String APPKEY = "52836ab53d4cf3e9";
    public static final String URL = "https://api.jisuapi.com/chengyu/search";

    public static List<Result> GetIdiom(String keyword) throws Exception {
        String result = null;
        String url = URL + "?appkey=" + APPKEY + "&keyword=" + URLEncoder.encode(keyword, "utf-8");

//        try {
        result = HttpUtil.sendGet(url, "utf-8");
        Log.e("lrf", result);
        IdiomResult idiomResult = IdiomJsonUtil.convertToObj(result);
        List<Result> resultList = idiomResult.getResult();


//            JSONObject json = JSONObject.fromObject(result);
//            if (json.getInt("status") != 0) {
//                System.out.println(json.getString("msg"));
//            } else {
//                JSONArray resultarr = json.optJSONArray("result");
//                for (int i = 0; i < resultarr.size(); i++) {
//                    JSONObject obj = (JSONObject) resultarr.opt(i);
//                    String name = obj.getString("name");
//                    System.out.println(name);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return resultList;
    }
}


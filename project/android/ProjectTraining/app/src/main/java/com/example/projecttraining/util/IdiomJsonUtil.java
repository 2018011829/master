package com.example.projecttraining.util;

import com.example.projecttraining.idiom.entity.IdiomInfo;
import com.example.projecttraining.idiom.entity.IdiomInfoResult;
import com.example.projecttraining.idiom.entity.IdiomResult;
import com.example.projecttraining.idiom.entity.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 2020-11-25
 * 2020-11-30
 * 2020-12-2
 * @author lrf
 */
public class IdiomJsonUtil {

    //定义Gson对象属性
    private static Gson gson = new GsonBuilder()//创建GsonBuilder对象
            .setPrettyPrinting()//格式化输出
            .serializeNulls()//允许输出Null值属性
            .create();//创建Gson对象

    /**
     * 将json串解析为IdiomInfoJiSu
     * @param json
     * @return
     */
    public static IdiomInfo convertToIdiomInfoJiSu(String json) {
        IdiomInfo idiomInfo = new IdiomInfo();
        IdiomInfoResult idiomInfoResult = new IdiomInfoResult();
        try {
            // 创建外层JSONObject对象
            JSONObject jObj = new JSONObject(json);
            // 获取外层JSONObject中的元素
            int status = jObj.getInt("status");
            String msg = jObj.getString("msg");

            idiomInfo.setStatus(status);
            idiomInfo.setMsg(msg);

            // 创建内层JSONObject对象
            JSONObject result = jObj.getJSONObject("result");

            // 获取内层JSONObject中的元素
            String name = result.getString("name");
            String pronounce = result.getString("pronounce");
            String content = result.getString("content");
            String comefrom = result.getString("comefrom");
            String tongyiJson = result.getString("antonym");
            String fanyiJson = result.getString("thesaurus");
            String example = result.getString("example");

            // 借助Gson，对List<String>解析
            Type type = new TypeToken<List<String>>(){}.getType();
            List<String> antonym = gson.fromJson(tongyiJson,type);
            List<String> thesaurus = gson.fromJson(fanyiJson,type);

            idiomInfoResult.setName(name);
            idiomInfoResult.setPronounce(pronounce);
            idiomInfoResult.setContent(content);
            idiomInfoResult.setComefrom(comefrom);
            idiomInfoResult.setAntonym(antonym);
            idiomInfoResult.setThesaurus(thesaurus);
            idiomInfoResult.setExample(example);

            idiomInfo.setIdiomInfoResult(idiomInfoResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idiomInfo;
    }

    /**
     * 将json串转换成IdiomResult
     *
     * @param json
     * @return
     */
    public static IdiomResult convertToIdiomResult(String json) {
        IdiomResult idiomResult = new IdiomResult();
        try {
            // 创建外层JSONObject对象
            JSONObject jObj = new JSONObject(json);
            // 获取外层JSONObject中的元素
            int status = jObj.getInt("status");
            String msg = jObj.getString("msg");
            // 将获取到的status和msg赋值给IdiomResult对象
            idiomResult.setStatus(status);
            idiomResult.setMsg(msg);
            // 创建内层数组JSONArray对象
            JSONArray jsonArray = jObj.getJSONArray("result");
            // 创建Result集合
            List<Result> results = new ArrayList<>();
            // 获取JSONArray中的数据
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // 获取当前JSONObject中的元素
                Result result = new Result();
                result.setName(jsonObject.getString("name"));
                results.add(result);
            }
            idiomResult.setResult(results);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idiomResult;
    }


}

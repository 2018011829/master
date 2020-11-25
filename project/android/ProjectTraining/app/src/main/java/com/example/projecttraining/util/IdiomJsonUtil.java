package com.example.projecttraining.util;

import com.example.projecttraining.idiom.entity.IdiomResult;
import com.example.projecttraining.idiom.entity.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 2020-11-25
 * @author lrf
 */
public class IdiomJsonUtil {

    /**
     * 将json串转换成对象
     * @param json
     * @return
     */
    public static IdiomResult convertToObj(String json){
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
            for(int i = 0; i < jsonArray.length(); ++i){
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

package com.shenzhen.baselib.utils;



import com.alibaba.fastjson.JSON;

import java.util.List;

public class JsonUtil {

    public static <T> T json2Object(String json, Class<T> clazz)
    {
        return JSON.parseObject(json, clazz);
    }
    public static <T> List<T> jsonToListObject(String json, Class<T> clazz)
    {
        return JSON.parseArray(json, clazz);
    }

    public static String object2Json(Object obj)
    {
        return JSON.toJSONString(obj);
    }
}

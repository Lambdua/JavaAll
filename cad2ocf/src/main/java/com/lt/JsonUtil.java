package com.lt;

import com.alibaba.fastjson.JSONObject;

/**
 * @author liangtao
 * @Date 2020/7/21
 **/
public class JsonUtil {
    public static String makeJson(CADProperties cadProperties){
        String cadJson = JSONObject.toJSONString(cadProperties);
        SocketProperties socketProperties = new SocketProperties();
        socketProperties.setInput(cadJson);
        socketProperties.setStatus(0);
        System.out.println(socketProperties.toString());
        return JSONObject.toJSONString(socketProperties);
    }
}

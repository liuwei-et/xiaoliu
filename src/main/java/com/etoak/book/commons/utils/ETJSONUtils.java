package com.etoak.book.commons.utils;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by 老周 on 2020/1/11.
 */
public class ETJSONUtils {
    public static void writeObject(HttpServletResponse response, Object obj) {
        response.setContentType("text/plain;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.toJSONString(obj));
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void writeArray(HttpServletResponse response,Object obj) {
        response.setContentType("text/plain;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(JSONArray.toJSONString(obj));
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

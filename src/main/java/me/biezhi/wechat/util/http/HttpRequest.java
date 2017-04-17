package me.biezhi.wechat.util.http;

import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSONObject;

public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            
     * @return URL 所代表远程资源的响应结果
     */
    public static JSONObject sendGet(String url, Map<String, String> params) {
        GetMethod method = null;
        HttpClient client = new HttpClient();
        JSONObject json = new JSONObject();
        StringBuffer sb = new StringBuffer();
        int i = 0;
        if(params!=null){
        for (String key : params.keySet()) {
            if (i != 0) {
                sb.append("&");
            }
            sb.append(key).append("=").append(params.get(key));
            i++;
        }
        }
        method = new GetMethod(url + "?" + sb.toString());
        method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        method.addRequestHeader("User-Agent", "Mozilla/4.0");
        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {//响应成功
                json = JSONObject.parseObject(method.getResponseBodyAsString());
            } else {
            	System.out.println(method.getStatusCode());
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return json;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            
     * @return 所代表远程资源的响应结果
     */
    public static JSONObject sendPost(String url, Map<String, String> params) {
        PostMethod method = null;
        HttpClient client = new HttpClient();
        JSONObject json = new JSONObject();
        method = new PostMethod(url);
        if(params!=null){
        for (String key : params.keySet()) {
            method.addParameter(key, params.get(key));
        }
        }
        method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        method.addRequestHeader("User-Agent", "Mozilla/4.0");
        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {//响应成功
                System.out.println(method.getResponseBodyAsString());
                json = JSONObject.parseObject(method.getResponseBodyAsString());
            } else {
            }
        } catch (Exception e) {
        }
        return json;
    }
}

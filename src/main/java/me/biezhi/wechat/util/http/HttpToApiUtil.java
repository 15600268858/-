package me.biezhi.wechat.util.http;

import java.util.List;
import java.util.Map;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 
 * 
 * 描述：http接口调用工具类
 *
 * @author ChenMing
 *
 * @version 1.0
 *
 * @since 2016-7-6
 */
public class HttpToApiUtil {

	
	/**
	 * 用来请求http接口的方法 返回json
	 * 
	 * @param type HttpRequestType get and post
	 *
	 * @param params 参数 hashMap key value对应
	 * @param url 请求地址
	 * @return JSONObject fastJson
	 */
	public static JSONObject httpRequestJson(HttpRequestType type,Map<String, String> params, String url){
		JSONObject resultJSON = null;
		try{
			if(type.equals(HttpRequestType.GET)){
				
				resultJSON = HttpRequest.sendGet(url, params);
			}else{
				
				resultJSON = HttpRequest.sendPost(url, params);
			}
		}catch (Exception e){
		}
		
		return resultJSON;
	}
	
	/**
	 * 用来请求http接口的方法 返回单个实体对象 注:本方法不返回接口状态 错误信息等 没有数据返回null
	 * 
	 * @param type HttpRequestType get and post
	 *
	 * @param params 参数 hashMap key value对应
	 * @param url 请求地址
	 * @param c Class类型(你需要返回的实体类型) 例:order.class
	 * @param methodName 返回json数据中 参数列表的名称(例:{"status":"1","data":{"id":"1","name":"张三"}}  传入data)
	 * 
	 * @return object(传入什么类型 返回什么类型)
	 */
	public static <T> T httpRequestObj(HttpRequestType type,Map<String, String> params, String url,Class<T> c,String methodName) throws InstantiationException,IllegalAccessException{
		T t = null;
		JSONObject resultJSON = null;
		try{
			if(type.equals(HttpRequestType.GET)){
				
				resultJSON = HttpRequest.sendGet(url, params);
			}else{
				
				resultJSON = HttpRequest.sendPost(url, params);
			}
			Object j = resultJSON.get(methodName);
			if(j!=null){
				
				String fromJson = j.toString();
				if(fromJson.startsWith("{")&&fromJson.endsWith("}")){
					if(fromJson.length()>2){//防止空参
						//t = c.newInstance();
						t = JSON.parseObject(fromJson, c);
					}
					
				}else{
					
				}
				
			}
			
		}catch (Exception e){
		}
		
		return t;
	}
	
	/**
	 * 用来请求http接口的方法 返回集合对象List<?> 注:本方法不返回接口状态 错误信息等 没有数据返回list.size==0
	 * 
	 * @param type HttpRequestType get and post
	 *
	 * @param params 参数 hashMap key value对应
	 * @param url 请求地址
	 * @param c Class类型(你需要返回的实体类型) 例:order.class
	 * @param methodName 返回json数据中 参数列表的名称(例:{"status":"1","data":{"id":"1","name":"张三"}}  传入data)
	 * @return List<object>(传入什么类型 返回什么类型)
	 */
	public static <T> List<T> httpRequestArray(HttpRequestType type,Map<String, String> params, String url,Class<T> c,String methodName) throws InstantiationException,IllegalAccessException{
		List<T> t = null;
		JSONObject resultJSON = null;
		try{
			if(type.equals(HttpRequestType.GET)){
				
				resultJSON = HttpRequest.sendGet(url, params);
			}else{
				
				resultJSON = HttpRequest.sendPost(url, params);
			}
			Object j = resultJSON.get(methodName);
			if(j!=null){
				String fromJson = j.toString();
				if(fromJson.startsWith("[")&&fromJson.endsWith("]")){
					
					t = JSON.parseArray(fromJson, c);
				}else {
				}
				
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return t;
	}
	


	
}

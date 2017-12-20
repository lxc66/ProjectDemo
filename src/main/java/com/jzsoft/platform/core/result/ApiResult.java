package com.jzsoft.platform.core.result;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jzsoft.platform.util.JsonUtil;

public class ApiResult extends Result {

	private Object data;
	
	public ApiResult() {
		super();
	}
	
	public ApiResult(boolean success) {
		super(success);
	}

	public ApiResult(boolean success, String message) {
		super(success, message);
	}
	
	public ApiResult(boolean success, int errcode, String message) {
		super(success, errcode, message);
	}

	public ApiResult(Object data) {
		super();
		this.data = data;
	}

	public ApiResult(Object data, String message) {
		super(message);
		this.data = data;
	}
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static ApiResult parse(String json){
		return JsonUtil.jsonToBean(json, ApiResult.class);
	}
	
	@SuppressWarnings("unchecked")
	private <X> X dataToBean(Object data, Class<?> clazz) {
		if(data==null || clazz==null){
			return null;
		}
		if(data instanceof JSONObject){
			return JsonUtil.jsonToBean(data, clazz);
		}
		if(clazz.isInstance(data)){
			return (X)data;
		}
		return null;
	}
	
	public <X> X dataToBean(Class<?> clazz) {
		return dataToBean(data, clazz);
	}
	
	@SuppressWarnings("unchecked")
	public <X> List<X> dataToBeans(Class<?> clazz) {
		if(data==null || clazz==null){
			return null;
		}
		List<X> dataList = new ArrayList<>();
		if(data instanceof JSONArray){
			JSONArray datas = (JSONArray)data;
			for(int i=0; i<datas.size();i++){
				Object object = datas.get(i);
				X x = this.dataToBean(object, clazz);
				dataList.add(x);
			}
			return dataList;
		}
		if(List.class.isInstance(data)){
			return (List<X>)data;
		}
		return null;
	}
}

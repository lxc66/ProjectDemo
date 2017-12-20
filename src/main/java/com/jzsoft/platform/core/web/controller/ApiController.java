package com.jzsoft.platform.core.web.controller;

import com.jzsoft.platform.core.result.ApiResult;

public class ApiController extends SpringController {

	public void printSuccess(){
		ApiResult result = new ApiResult();
		this.renderJson(result.toJson());
	}
	
	public void printSuccess(Object data){
		ApiResult result = new ApiResult(data);
		this.renderJson(result.toJson());
	}
	
	public void printSuccess(Object data,String message){
		ApiResult result = new ApiResult(data,message);
		this.renderJson(result.toJson());
	}
	
	public void printFailure(String errmsg){
		ApiResult result = new ApiResult(false, errmsg);
		this.renderJson(result.toJson());
	}
	
	public void printFailure(int errcode,String errmsg){
		ApiResult result = new ApiResult(false, errcode, errmsg);
		this.renderJson(result.toJson());
	}
}

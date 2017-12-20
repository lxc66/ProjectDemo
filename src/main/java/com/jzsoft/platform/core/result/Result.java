package com.jzsoft.platform.core.result;

import com.alibaba.fastjson.JSONObject;

public class Result {
	private boolean success = true;
	private int code = ERROR_CODE_NONE;
	private String message;

	private static final int ERROR_CODE_NONE = 0;
	private static final int ERROR_CODE_COMMON = 500;
	
	public Result() {
	}

	public Result(boolean success) {
		this.setSuccess(success);
	}

	public Result(String message) {
		this.message = message;
	}

	public Result(boolean success, String message) {
		this.setSuccess(success);
		this.message = message;
	}
	
	public Result(boolean success, int errcode, String message) {
		this.setSuccess(success);
		this.code = errcode;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
		if(success){
			this.code = ERROR_CODE_NONE;
		}else if(code==ERROR_CODE_NONE){
			this.code = ERROR_CODE_COMMON;
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setErrorMessage(String message) {
		this.success = false;
		this.code = ERROR_CODE_COMMON;
		this.message = message;
	}

	public void setErrorMessage(int errcode, String message) {
		this.success = false;
		this.code = errcode;
		this.message = message;
	}
	
	public String toJson(){
		return JSONObject.toJSONString(this);
	}
}

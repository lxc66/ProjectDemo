package com.jzsoft.platform.util.web;

import java.net.HttpURLConnection;

public class HttpResult {
	private Integer status;
	private String result;
	
	public HttpResult(Integer status) {
		super();
		this.status = status;
	}

	public HttpResult(Integer status, String result) {
		super();
		this.status = status;
		this.result = result;
	}

	public Integer getStatus() {
		return status;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public boolean isSuccess(){
		return HttpURLConnection.HTTP_OK==status;
	}

	@Override
	public String toString() {
		return "[HttpResult] --status:"+status+" --result:"+result;
	}
}

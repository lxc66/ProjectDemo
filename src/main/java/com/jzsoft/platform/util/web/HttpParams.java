package com.jzsoft.platform.util.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpParams {
	private Map<String,String> params = new HashMap<String, String>();
	private Map<String,File> files =new HashMap<String, File>();
	
	public Map<String, String> getParams() {
		return params;
	}

	public Map<String, File> getFiles() {
		return files;
	}

	public void addParameter(String name,String value){
		getParams().put(name, value);
	}
	
	public void addFile(String name,File file){
		getFiles().put(name, file);
	}
	
	public boolean isExistsParams(){
		return params.size()>0;
	}
	
	public boolean isExistsFile(){
		return files.size()>0;
	}
    
	public String buildParamsToString(){
		if(this.params.size()==0){
			return "";
		}
		String paramsStr = "";
		for(Entry<String, String> entry : params.entrySet()){
			if(!"".equals(paramsStr))paramsStr+="&";
			paramsStr+=entry.getKey()+"="+entry.getValue();
		}
		return paramsStr;
	}
	
    public static HttpParams buildParams(String data){
    	HttpParams params = new HttpParams();
    	if(data!=null){
        	String[] datas = data.split("&");
        	for(String data_:datas){
        		if(data_.trim().equals("") || data_.indexOf("=")==-1){
        			continue;
        		}
        		String[] param = data_.split("=");
        		params.addParameter(param[0], param[1]);
        	}
        }
    	return params;
    }
}

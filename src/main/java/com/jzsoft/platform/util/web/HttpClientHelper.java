package com.jzsoft.platform.util.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jzsoft.platform.util.CloseUtil;

@SuppressWarnings("unused")
public class HttpClientHelper {
	private final static Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);
	public static final String UTF_8 = "UTF-8";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
	private static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
    private static final String CONTENT_TYPE_APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_APPLICATION_OCTET_STREAM = "application/octet-stream";
    private static final String CONTENT_TYPE_MULTIPART_FORM_DATA = "multipart/form-data";
    
    public static HttpResult post(String actionUrl,String params){
        return post(actionUrl, HttpParams.buildParams(params));
    }
    
    public static HttpResult post(String actionUrl,HttpParams params){
    	return doPost(actionUrl, params);
    }
    
    public static HttpResult get(String actionUrl){
    	return doGet(actionUrl);
    }
    
    
    public static HttpResult get(String actionUrl,HttpParams params){
    	if(params!=null){
    		String paramStr = params.buildParamsToString();
    		if(actionUrl.indexOf("?")>0){
    			actionUrl+="&"+paramStr;
    		}else{
    			actionUrl+="?"+paramStr;
    		}
    	}
    	return doGet(actionUrl);
    }
    
    
    
    private static HttpResult doPost(String url, HttpParams params) {
    	logger.info("url:" + url);
    	HttpPost httpPost = buildHttpPost(url, params);
        return doPost(httpPost);
    }
    
    private static HttpResult doGet(String url) {
    	logger.info("url:" + url);
    	CloseableHttpResponse response =null;
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	try {
    		HttpGet httpGet = new HttpGet(url);
    		response = httpClient.execute(httpGet);
    		int statusCode = response.getStatusLine().getStatusCode();
    		logger.info("statusCode:" + statusCode);
    		HttpEntity resEntity = response.getEntity();
    		HttpResult httpResult = new HttpResult(statusCode);
    		if (resEntity != null) {
    			String result = EntityUtils.toString(resEntity,Consts.UTF_8);
    			EntityUtils.consume(resEntity);
    			httpResult.setResult(result);
            }
    		return httpResult;
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		throw new RuntimeException(e);
    	}finally {
    		CloseUtil.close(response);
    		CloseUtil.close(httpClient);
    	}
    }
    
    private static HttpResult doPost(HttpPost httpPost) {
    	CloseableHttpResponse response =null;
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	try {
    		response = httpClient.execute(httpPost);
    		int statusCode = response.getStatusLine().getStatusCode();
    		logger.info("statusCode:" + statusCode);
    		HttpEntity resEntity = response.getEntity();
    		HttpResult httpResult = new HttpResult(statusCode);
    		if (resEntity != null) {
    			String result = EntityUtils.toString(resEntity,Consts.UTF_8);
    			EntityUtils.consume(resEntity);
    			httpResult.setResult(result);
            }
    		return httpResult;
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		throw new RuntimeException(e);
    	}finally {
    		CloseUtil.close(response);
    		CloseUtil.close(httpClient);
    	}
    }
    
    private static HttpPost buildHttpPost(String url, HttpParams params) {
        HttpPost httpPost = new HttpPost(url);
        if(params==null){
        	return httpPost;
        }
        if(!params.isExistsFile()){
        	EntityBuilder entityBuilder = EntityBuilder.create().setContentEncoding(UTF_8);
//        	entityBuilder.setContentType(ContentType.create(CONTENT_TYPE_APPLICATION_FORM_URLENCODED, Consts.UTF_8));
    		Set<Entry<String, String>> paramsEntrySet = params.getParams().entrySet();
    		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			for(Entry<String, String> entry : paramsEntrySet){
				parameters.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
			}
			entityBuilder.setParameters(parameters);
			httpPost.setEntity(entityBuilder.build());
//    		System.out.println("ContentType:"+httpPost.getEntity().getContentType());
    		logger.info("ContentType:"+httpPost.getEntity().getContentType());
        }else{
        	try {
        		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        		entityBuilder.setCharset(Consts.UTF_8);//设置请求的编码格式
        		Set<Entry<String, String>> paramsEntrySet = params.getParams().entrySet();
        		if(params.isExistsParams()){
        			for(Entry<String, String> entry : paramsEntrySet){
//        				StringBody value = new StringBody(entry.getValue(), ContentType.create(CONTENT_TYPE_TEXT_PLAIN, Consts.UTF_8));
//        				entityBuilder.addPart(entry.getKey(),value);
        				entityBuilder.addTextBody(entry.getKey(),entry.getValue(), ContentType.create(CONTENT_TYPE_TEXT_PLAIN, Consts.UTF_8));
        			}
        		}
        		Set<Entry<String, File>> fileEntrySet = params.getFiles().entrySet();
        		for(Entry<String, File> entry : fileEntrySet){
        			File file = entry.getValue();
        			FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY, file.getName());
        			entityBuilder.addPart(entry.getKey(), fileBody);
        		}
        		httpPost.setEntity(entityBuilder.build());
//        		System.out.println("ContentType:"+httpPost.getEntity().getContentType());
        		logger.info("ContentType:"+httpPost.getEntity().getContentType());
        	} catch (Exception e) {
        		e.printStackTrace();
        		logger.error(e.getMessage(), e);
        	}
        }
        
        return httpPost;
    }
    
	public static HttpResult postBody(String urlPath, String body) {
		try {
			URL url = new URL(urlPath);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			// 设置doOutput属性为true表示将使用此urlConnection写入数据
			urlConnection.setDoOutput(true);
			// 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
			urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			// 得到请求的输出流对象
			OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
			// 把数据写入请求的Body
			out.write(body);
			out.flush();
			out.close();

			// 从服务器读取响应
			InputStream inputStream = urlConnection.getInputStream();
			String encoding = urlConnection.getContentEncoding();
			String result = IOUtils.toString(inputStream, encoding);
			HttpResult httpResult = new HttpResult(urlConnection.getResponseCode());
			httpResult.setResult(result);
			return httpResult;
		} catch (IOException e) {
    		logger.error(e.getMessage(), e);
    		throw new RuntimeException(e);
		}
	}
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
    	String url="http://192.168.30.86:8700/platform/dm/testVideoConvertCallBack";
    	String datas="status=success&id=20140606093118332487845497781993&path=20140626173223065%2F20140626173223065.mp4";
//        post(url,datas);
//    	HttpParams params = HttpParams.buildParams(datas);
    	HttpParams params = new HttpParams();
		params.addParameter("names[0]", "Tulips");
		params.addParameter("names[1]", "pic");
		//---成功
    	url="http://localhost:8080/dm/httpForm/test1";
      params.addFile("upFiles[0]", new File("d:\\t1.jpg"));
      params.addFile("upFiles[1]", new File("d:\\t2.jpg"));
		
		//---成功
//    	url="http://192.168.30.86:8700/platform/dm/httpForm/test2";
//    	params.addParameter("fileNames[0]", "图片1");
//		params.addParameter("fileNames[1]", "图片2");
    	//---成功
//    	url="http://192.168.30.86:8700/platform/dm/httpForm/test3";
//        params.addParameter("user.name", "张三");
//        params.addFile("user.file", new File("E:\\Tulips.jpg"));
		//---成功
//    	url="http://192.168.30.86:8700/platform/dm/httpForm/test4";
//        params.addParameter("users[0].name", "张三");
//        params.addParameter("users[1].name", "李四");
//        params.addFile("users[0].file", new File("E:\\Tulips.jpg"));
//        params.addFile("users[1].file", new File("E:\\pic.png"));
//        params.addParameter("users[0].dept.name", "大食堂");
//        params.addFile("users[0].dept.file", new File("E:\\系统架构图.png"));
//        params.addParameter("users[0].addresses[0].name", "玄镜司");
//        params.addParameter("users[0].addresses[1].name", "大理寺");
//        params.addParameter("users[1].addresses[0].name", "皇宫");
//        params.addParameter("users[1].addresses[1].name", "地牢");
//        params.addFile("users[0].addresses[0].file", new File("E:\\testImg\\Desert.jpg"));
//        params.addFile("users[0].addresses[1].file", new File("E:\\testImg\\Hydrangeas.jpg"));
//        params.addFile("users[1].addresses[0].file", new File("E:\\testImg\\Jellyfish.jpg"));
//        params.addFile("users[1].addresses[1].file", new File("E:\\testImg\\Koala.jpg"));
		
        HttpResult result = post(url,params);
        System.out.println(result);
        System.out.println(result.isSuccess());
        
    }
    
}

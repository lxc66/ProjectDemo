package com.jzsoft.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzsoft.demo.model.TBAddress;
import com.jzsoft.demo.model.TBUser;
import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.core.web.controller.SpringController;

@Controller
@RequestMapping("/dm/httpForm")
public class HttpFormController extends SpringController {
	final static Logger logger = LoggerFactory.getLogger(HttpFormController.class);
	@RequestMapping("/test1")
	@ResponseBody
	public String test1(String status,@FormModel("upFiles") List<MultipartFile>upFiles,@FormModel("names") List<String> names) {
		System.out.println("------httpClient提交数据--1");
		System.out.println("status:"+status);
		System.out.println("names:"+names.size());
		if(upFiles.size()>0){
			for(int i=0 ; i<upFiles.size(); i++){
				System.out.println("upFiles["+i+"]:"+upFiles.get(i));
			}
		}
		return "success";
	}
	@RequestMapping("/test2")
	@ResponseBody
	public String test2(String status,@FormModel("fileNames") List<String> fileNames,@FormModel("names") List<String> names) {
		System.out.println("------httpClient提交数据--2");
		System.out.println("status:"+status);
		System.out.println("names:"+names.size());
		System.out.println("fileNames:"+fileNames.size());
		for(String fileName:fileNames){
			System.out.println("-- "+fileName);
		}
		return "success";
	}
	
	@RequestMapping("/test3")
	@ResponseBody
	public String test3(String status,@FormModel("user")TBUser user,@FormModel("names") List<String> names) {
		System.out.println("------httpClient提交数据--3");
		System.out.println("status:"+status);
		System.out.println("user.file:"+user.getFile());
		System.out.println("names:"+names.size());
		return "success";
	}
	
	@RequestMapping("/test4")
	@ResponseBody
	public String test4(String status,@FormModel("users")List<TBUser> users,@FormModel("names") List<String> names) {
		System.out.println("------httpClient提交数据--4");
		System.out.println("status:"+status);
		System.out.println("names:"+names.size());
		if(users.size()>0){
			for(int i=0 ; i<users.size(); i++){
				TBUser user = users.get(i);
				System.out.println("user["+i+"].name:"+user.getName());
				System.out.println("user["+i+"].file:"+user.getFileName());
				if(user.getDept()!=null){
					System.out.println("user["+i+"].dept.name:"+user.getDept().getName());
					System.out.println("user["+i+"].dept.file:"+user.getDept().getFileName());
				}
				List<TBAddress> addresses = user.getAddresses();
				if(addresses.size()>0){
					for(int j=0 ; j<addresses.size() ; j++){
						System.out.println("user["+i+"].addresses["+j+"].name:"+addresses.get(j).getName());
						System.out.println("user["+i+"].addresses["+j+"].file:"+addresses.get(j).getFileName());
					}
				}
			}
		}
		return "success";
	}
}

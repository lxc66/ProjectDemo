package com.jzsoft.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class TBDept {

	private String id;

	private String name;

	private List<TBUser> users = new ArrayList<TBUser>();

	public TBDept() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TBUser> getUsers() {
		return users;
	}

	public void setUsers(List<TBUser> users) {
		this.users = users;
	}
//--测试
	private MultipartFile file;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getFileName(){
		return null==file?null:file.getOriginalFilename();
	}
}

package com.jzsoft.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class TBAddress {

	private String id;

	private String name;

	public TBAddress() {

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

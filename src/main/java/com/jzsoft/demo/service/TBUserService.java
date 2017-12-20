package com.jzsoft.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.demo.dao.TBUserDAO;
import com.jzsoft.demo.model.TBUser;
import com.jzsoft.platform.component.attachment.util.AttachmentUtil;
import com.jzsoft.platform.core.dao.Page;

@Service
@Transactional
public class TBUserService {

	@Autowired
	private TBUserDAO userDAO;

	public TBUser get(String id){
		return userDAO.get(id);
	}
	
	public void save(TBUser user) {
		userDAO.save(user);
		AttachmentUtil.bindAttachment(user);
//		List<File> tempFiles = AttachmentUtil.getTempFiles();
//		System.out.println(tempFiles.size());
//		System.out.println(tempFiles.get(0).exists());
//		throw new RuntimeException("error");
	}
	
	public void update(TBUser user) {
		userDAO.update(user);
		AttachmentUtil.bindAttachment(user);
//		throw new RuntimeException("error");
	}

	public List<TBUser> getAll() {
		return userDAO.getAll();
	}
	
	public void delete(String id){
		userDAO.delete(id);
		AttachmentUtil.unbindAttachment(id);
	}

	public Page<TBUser> getPageWithDemo(Page<TBUser> page) {
		if(page==null){
			page = new Page<TBUser>();
		}
		List<TBUser> list = new ArrayList<TBUser>(0);
		for(int i = 0 ; i<1000 ; i++){
			TBUser user = new TBUser();
			user.setId(UUID.randomUUID().toString());
			user.setName("无名"+i);
			user.setEmail("wuming"+i+"@163.com");
			list.add(user);
		}
		return userDAO.getPageWithDemo(list, page);
	}

	public List<TBUser> getListSimple() {
		List<TBUser> list = new ArrayList<TBUser>(0);
		for (int i = 0; i < 100; i++) {
			TBUser user = new TBUser();
			user.setId(UUID.randomUUID().toString());
			user.setName("无名" + i);
			user.setEmail("wuming" + i + "@163.com");
			list.add(user);
		}
		return list;
	}
}

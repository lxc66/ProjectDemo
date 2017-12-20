package com.jzsoft.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.demo.dao.auto.TBDeptDAO;
import com.jzsoft.demo.model.TBDept;

@Service
@Transactional
public class TBDeptService {

	@Autowired
	private TBDeptDAO deptDAO;

	public TBDept get(String id){
		return deptDAO.get(id);
	}
	
	
	public void save(TBDept dept) {
		deptDAO.save(dept);
	}


	public List<TBDept> getAll() {
		return deptDAO.getAll();
	}

}

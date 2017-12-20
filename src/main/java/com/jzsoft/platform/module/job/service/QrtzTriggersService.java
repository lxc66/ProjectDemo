package com.jzsoft.platform.module.job.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.module.job.dao.QrtzTriggersDAO;
import com.jzsoft.platform.module.job.model.QrtzTriggers;


/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class QrtzTriggersService {
	
	
	public List<QrtzTriggers> getAll() {
		return qrtzTriggersDAO.getAll();
	}
	
	public Map<String, QrtzTriggers> getAllTriggerNameMap() {
		Map<String, QrtzTriggers> triggerNameMap = new HashMap<String, QrtzTriggers>();
		List<QrtzTriggers> qrtzTriggers = qrtzTriggersDAO.getAll();
		for(QrtzTriggers trigger : qrtzTriggers){
			triggerNameMap.put(trigger.getTriggerName(), trigger);
		}
		return triggerNameMap;
	}
	
	
	@Autowired
	private QrtzTriggersDAO qrtzTriggersDAO;
}



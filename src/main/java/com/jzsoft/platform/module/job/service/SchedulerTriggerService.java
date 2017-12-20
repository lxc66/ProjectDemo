package com.jzsoft.platform.module.job.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.module.job.dao.SchedulerTriggerDAO;
import com.jzsoft.platform.module.job.helper.SchedulerHelper;
import com.jzsoft.platform.module.job.model.QrtzTriggers;
import com.jzsoft.platform.module.job.model.SchedulerTrigger;


/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class SchedulerTriggerService {
	
	public void save(SchedulerTrigger model) {
		schedulerTriggerDAO.save(model);
		SchedulerHelper.addOrModifyTrigger(get(model.getId()));
	}
	
	public void update(SchedulerTrigger model) {
		schedulerTriggerDAO.update(model);
		SchedulerHelper.addOrModifyTrigger(get(model.getId()));
	}
	
	public SchedulerTrigger get(String id){
		return schedulerTriggerDAO.get(id);
	}
	
	public List<SchedulerTrigger> getAll() {
		return schedulerTriggerDAO.getAll();
	}
	
	public Page<SchedulerTrigger> getPage(SchedulerTrigger model, Page<SchedulerTrigger> page,String sord,String sidx) {
		Page<SchedulerTrigger> pageObj = schedulerTriggerDAO.getPage(page);
		Map<String, QrtzTriggers> qrtzTriggersNameMap = qrtzTriggersService.getAllTriggerNameMap();
		List<SchedulerTrigger> list = pageObj.getList();
		for(SchedulerTrigger schedulerTrigger : list){
			if(qrtzTriggersNameMap.containsKey(schedulerTrigger.getTriggerName())){
				schedulerTrigger.setQrtzTriggers(qrtzTriggersNameMap.get(schedulerTrigger.getTriggerName()));
			}
		}
		return pageObj;
	}
	
	public void delete(String id) {
		SchedulerTrigger trigger = this.get(id);
		schedulerTriggerDAO.delete(id);
		SchedulerHelper.removeTrigger(trigger);
	}
	public void deleteWithJobId(String jobId) {
		schedulerTriggerDAO.deleteWithJobId(jobId);
	}
	
	@Autowired
	private SchedulerTriggerDAO schedulerTriggerDAO;
	@Autowired
	private QrtzTriggersService qrtzTriggersService;
}



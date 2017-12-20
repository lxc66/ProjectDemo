package com.jzsoft.platform.module.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.module.job.dao.SchedulerJobDAO;
import com.jzsoft.platform.module.job.helper.SchedulerHelper;
import com.jzsoft.platform.module.job.model.SchedulerJob;


/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class SchedulerJobService {

	
	public void save(SchedulerJob model) {
		schedulerJobDAO.save(model);
		SchedulerHelper.addJob(model);
	}
	
	public void update(SchedulerJob model) {
		schedulerJobDAO.update(model);
		SchedulerHelper.addJob(model);
	}
	
	public SchedulerJob get(String id){
		return schedulerJobDAO.get(id);
	}
	
	public SchedulerJob getWithGroupAndName(String jobGroup , String jobName){
		return schedulerJobDAO.getWithGroupAndName(jobGroup,jobName);
	}
	
	public List<SchedulerJob> getAll() {
		return schedulerJobDAO.getAll();
	}
	
	
	public Page<SchedulerJob> getPage(SchedulerJob model, Page<SchedulerJob> page,String sord,String sidx) {
		return schedulerJobDAO.getPage(page);
	}
	
	
	public void delete(String id) {
		SchedulerJob job = this.get(id);
		schedulerTriggerService.deleteWithJobId(id);
		schedulerJobDAO.delete(id);
		SchedulerHelper.removeJob(job);
	}
	
	
	/**
	 * 分组与名称的组合是否被占用
	 */
	public boolean isExistsGroupAndName(String jobGroup , String jobName,final String excludeId) {
		SchedulerJob model = schedulerJobDAO.getWithGroupAndNameExcludeId(jobGroup,jobName, excludeId);
		if (model != null) {
			return true;
		} 
		return false;
	}
	
	
	@Autowired
	private SchedulerJobDAO schedulerJobDAO;
	@Autowired
	private SchedulerTriggerService schedulerTriggerService;
}



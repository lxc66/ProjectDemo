package com.jzsoft.platform.module.job.dao;

import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.job.model.SchedulerTrigger;

@Repository
public class SchedulerTriggerDAO extends BaseDAO<SchedulerTrigger, String>{
	public void deleteWithJobId(String jobId) {
		this.delete("deleteWithJobId", jobId);
	}
}

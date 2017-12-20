package com.jzsoft.platform.module.job.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.job.model.SchedulerJob;

@Repository
public class SchedulerJobDAO extends BaseDAO<SchedulerJob, String>{
	
	public SchedulerJob getWithGroupAndName(String jobGroup , String jobName) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("jobGroup", jobGroup);
		params.put("jobName", jobName);
		return this.get("getWithGroupAndName", params);
	}
	
	public SchedulerJob getWithGroupAndNameExcludeId(String jobGroup , String jobName , String excludeId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("jobGroup", jobGroup);
		params.put("jobName", jobName);
		if(StringUtils.isNotBlank(excludeId)){
			params.put("excludeId", excludeId);
		}
		return this.get("getWithGroupAndNameExcludeId", params);
	}
	
	
}

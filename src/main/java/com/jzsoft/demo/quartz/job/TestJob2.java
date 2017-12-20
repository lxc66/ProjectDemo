package com.jzsoft.demo.quartz.job;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jzsoft.platform.util.DateUtil;


public class TestJob2 extends QuartzJobBean {
	protected Log logger = LogFactory.getLog(this.getClass());
	@Override
	protected void executeInternal(JobExecutionContext jec)
			throws JobExecutionException {
//		logger.info("清除系统日志["+DateUtil.getCurrDateSecondString()+"]..................................................................");
		System.out.println(TestJob2.class+":"+DateUtil.getCurrDateMillisecondString());
	}
}



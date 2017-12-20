package com.jzsoft.platform.module.job.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.jzsoft.platform.component.attachment.util.AttachmentUtil;
import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.core.web.context.ServletContextMonitor;
import com.jzsoft.platform.module.job.model.SchedulerJob;
import com.jzsoft.platform.module.job.model.SchedulerTrigger;
import com.jzsoft.platform.util.DateUtil;

@Component
public class SchedulerHelper implements ServletContextMonitor {
	private final static Logger logger = LoggerFactory.getLogger(AttachmentUtil.class);
	private static final String IDENTITY_JOB_PREFIX="";  
    private static final String IDENTITY_TRIGGER_PREFIX="";  
    
	private static SchedulerFactoryBean schedulerFactoryBean;
	private static Scheduler scheduler;

	public static boolean addJob(SchedulerJob job) {
		try {
			JobDetail jobDetail = generateJobDetail(job);
			getScheduler().addJob(jobDetail, true);
			return true;
		} catch (SchedulerException e) {
			logger.error("scheduler.scheduleJob", e);
			return false;
		}
	}
	
	public static boolean addOrModifyTrigger(SchedulerTrigger st) {
		TriggerKey triggerKey = getTriggerKey(st);
		try {
			CronTrigger trigger = (CronTrigger) getScheduler().getTrigger(triggerKey);
			if(null==trigger){
				return addTrigger(st);
			}else{
				return modifyTrigger(st,triggerKey,trigger);
			}
		} catch (SchedulerException e) {
			logger.error("scheduler.scheduleJob", e);
			return false;
		}
	}
	
	public static boolean addTrigger(SchedulerTrigger st) {

		try {
			JobKey jobKey = getJobKey(st.getJob());
			JobDetail jobDetail = getScheduler().getJobDetail(jobKey);
			if(null==jobDetail){
				addJob(st.getJob());
			}
			Trigger trigger = generateTriggerBuilder(st).build();
			getScheduler().scheduleJob(trigger);
			return true;
		} catch (SchedulerException e) {
			logger.error("scheduler.scheduleJob", e);
			return false;
		}
	}
	
	public static boolean modifyTrigger(SchedulerTrigger st) {
		TriggerKey triggerKey = getTriggerKey(st);
		try {
			CronTrigger trigger = (CronTrigger) getScheduler().getTrigger(triggerKey);
			return modifyTrigger(st,triggerKey,trigger);
		} catch (SchedulerException e) {
			logger.error("scheduler.scheduleJob", e);
			return false;
		}
	}

	private static boolean modifyTrigger(SchedulerTrigger st, TriggerKey triggerKey, CronTrigger trigger) {
		try {
			if(null!=trigger){
				trigger = generateTriggerBuilder(trigger.getTriggerBuilder(),st).build();
				//按新的trigger重新设置job执行
				getScheduler().rescheduleJob(triggerKey, trigger);
			}
			return true;
		} catch (SchedulerException e) {
			logger.error("scheduler.scheduleJob", e);
			return false;
		}
	}

	/**
	* 清除
	*/
	public static void clearAllScheduler() {
		try {
			getScheduler().clear();
		} catch (SchedulerException e) {
			logger.error("clearAllScheduler", e);
		}
	}

	/**
	* 删除任务
	*/
	public static boolean removeJob(SchedulerJob job) {
		try {
			getScheduler().deleteJob(getJobKey(job));
			return true;
		} catch (SchedulerException e) {
			logger.error("removeJob", e);
			return false;
		}
	}
	/**
	 * 删除触发器
	 */
	public static boolean removeTrigger(SchedulerTrigger st) {
		try {
			getScheduler().unscheduleJob(getTriggerKey(st));
			return true;
		} catch (SchedulerException e) {
			logger.error("removeTrigger", e);
			return false;
		}
	}

	/**
	* 暂停任务
	*/
	public static boolean pauseJob(SchedulerJob job) {
		try {
			getScheduler().pauseJob(getJobKey(job));
			return true;
		} catch (SchedulerException e) {
			logger.error("pauseJob", e);
			return false;
		}
	}
	
	/**
	 * 暂停触发器
	 */
	public static boolean pauseTrigger(SchedulerTrigger st) {
		try {
			getScheduler().pauseTrigger(getTriggerKey(st));
			return true;
		} catch (SchedulerException e) {
			logger.error("pauseTrigger", e);
			return false;
		}
	}
	
	/**
	 * 恢复任务
	 */
	public static boolean resumeJob(SchedulerJob job) {
		try {
			getScheduler().resumeJob(getJobKey(job));
			return true;
		} catch (SchedulerException e) {
			logger.error("resumeJob", e);
			return false;
		}
	}

	/**
	 * 恢复触发器
	 */
	public static boolean resumeTrigger(SchedulerTrigger st) {
		try {
			getScheduler().resumeTrigger(getTriggerKey(st));
			return true;
		} catch (SchedulerException e) {
			logger.error("resumeTrigger", e);
			return false;
		}
	}
	
	/**
	 * 触发任务
	 */
	public static boolean triggerJob(SchedulerJob job) {
		try {
			getScheduler().triggerJob(getJobKey(job));
			return true;
		} catch (SchedulerException e) {
			logger.error("triggerJob", e);
			return false;
		}
	}
	
	
	/**
	* 马上只执行一次任务
	*/
	public static boolean executeOneceJob(String jobName, String jobGroup) {
		try {
			Calendar end = Calendar.getInstance();
			TriggerBuilder<SimpleTrigger> simpleTriggerBuilder = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobName, jobGroup)).forJob(getJobKey(jobName, jobGroup)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2));
			end.add(Calendar.SECOND, 2);
			simpleTriggerBuilder.startAt(end.getTime());
			end.add(Calendar.SECOND, 5);
			simpleTriggerBuilder.endAt(end.getTime());
//			SchedulerJob job = jobService.getJobById(jobId);
			SchedulerJob job=null;

			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("jobEntity", job);
			simpleTriggerBuilder.usingJobData(jobDataMap);
			Trigger trigger = simpleTriggerBuilder.build();

			getScheduler().scheduleJob(trigger);
			return true;
		} catch (SchedulerException e) {
			logger.error("executeOneceJob", e);
			return false;
		}
	}

	/**
	* 启动一些scheduler里没有的active的jobDetail
	*/
	public static void createActiveJobFromDB() throws SchedulerException {
//		List<SchedulerJob> jobs = jobService.getActiveJob();
		List<SchedulerJob> jobs = new ArrayList<SchedulerJob>();
		for (SchedulerJob job : jobs) {
//			if (getScheduler().getJobDetail(getJobKey(job)) == null)
//				addJob(job);
		}
	}

	/**
	* 获得任务的jobKey
	*/
	public static JobKey getJobKey(String jobName, String jobGroup) {
		return new JobKey(IDENTITY_JOB_PREFIX + jobName, IDENTITY_JOB_PREFIX + jobGroup);
	}

	/**
	* 获得任务的jobKey
	*/

	public static JobKey getJobKey(SchedulerJob job) {
		return new JobKey(IDENTITY_JOB_PREFIX + job.getJobName(), IDENTITY_JOB_PREFIX + job.getJobGroup());
	}

	/**
	* 获得trigger的triggerkey
	*/
	public static TriggerKey getTriggerKey(SchedulerTrigger st) {
		return new TriggerKey(IDENTITY_TRIGGER_PREFIX + st.getTriggerName(), IDENTITY_TRIGGER_PREFIX + st.getTriggerGroup());
	}

	/**
	* 获得trigger的triggerkey
	*/
	public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
		return new TriggerKey(IDENTITY_TRIGGER_PREFIX + jobName, IDENTITY_TRIGGER_PREFIX + jobGroup);
	}

	public static JobDetail generateJobDetail(SchedulerJob job) {
		JobDataMap jobDataMap = new JobDataMap();
//		jobDataMap.put("jobEntity", job);
		try {
			@SuppressWarnings("unchecked")
			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(job.getJobClass());
			return JobBuilder.newJob(clazz).withIdentity(getJobKey(job)).usingJobData(jobDataMap).requestRecovery(true).storeDurably(true).build();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("FastDFS connection fail:"+e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	* 根据jobEntity获得trigger
	*/

	public static TriggerBuilder<CronTrigger> generateTriggerBuilder(SchedulerTrigger st) {
		TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(st)).forJob(getJobKey(st.getJob())).withSchedule(CronScheduleBuilder.cronSchedule(st.getCronExpression()).withMisfireHandlingInstructionDoNothing());
		if (StringUtils.isNotBlank(st.getStartTime()))
			triggerBuilder.startAt(DateUtil.getDateMin(st.getStartTime()));
		else
			triggerBuilder.startNow();

		if (StringUtils.isNotBlank(st.getEndTime()))
			triggerBuilder.endAt(DateUtil.getDateMin(st.getEndTime()));
		return triggerBuilder;
	}
	
	public static TriggerBuilder<CronTrigger> generateTriggerBuilder(TriggerBuilder<CronTrigger> triggerBuilder, SchedulerTrigger st) {
		if(triggerBuilder==null)return null;
		triggerBuilder.withIdentity(getTriggerKey(st)).forJob(getJobKey(st.getJob())).withSchedule(CronScheduleBuilder.cronSchedule(st.getCronExpression()).withMisfireHandlingInstructionDoNothing());
		if (StringUtils.isNotBlank(st.getStartTime()))
			triggerBuilder.startAt(DateUtil.getDateMin(st.getStartTime()));
		else
			triggerBuilder.startNow();
		
		if (StringUtils.isNotBlank(st.getEndTime()))
			triggerBuilder.endAt(DateUtil.getDateMin(st.getEndTime()));
		
		return triggerBuilder;
	}

	
	public static Scheduler getScheduler() {
		if(scheduler==null){
			throw new RuntimeException("SchedulerFactoryBean does not exist, please check the configuration file");
		}
		return scheduler;
	}

	public void init(ServletContext context) {
		Map<String, SchedulerFactoryBean> beans = SpringContextHolder.getBeansOfType(SchedulerFactoryBean.class);
		if (beans.size() > 0) {
			schedulerFactoryBean = SpringContextHolder.getBean(SchedulerFactoryBean.class);
			scheduler = schedulerFactoryBean.getScheduler();
		}
	}

	public void destroyed(ServletContext context) {

	}
}

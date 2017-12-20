package com.jzsoft.platform.module.job.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.component.jqGrid.controller.JqGridController;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.module.job.helper.SchedulerHelper;
import com.jzsoft.platform.module.job.model.SchedulerJob;
import com.jzsoft.platform.module.job.service.SchedulerJobService;

@Controller
@RequestMapping("/sys/schedulerJob")
public class SchedulerJobController extends JqGridController<SchedulerJob> {

	@Autowired
	private SchedulerJobService schedulerJobService;

	@RequestMapping("/index")
	public String index() {
		return "/platform/module/job/job-index";
	}

	@RequestMapping("/list")
	public String list() {
		return "/platform/module/job/schedulerJob-list";
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public String listData(@FormModel("page") Page<SchedulerJob> page,@FormModel("model") SchedulerJob model,String sord,String sidx){
		return this.refreshDataTable(schedulerJobService.getPage(model, page, sord, sidx));
	}
	
	@RequestMapping("/input")
	public String input(String id) {
		SchedulerJob model = null;
		if (StringUtils.isNotBlank(id)) {
			model = schedulerJobService.get(id);
		}else{
			model = new SchedulerJob();
		}
		setAttribute("model", model);
		return "/platform/module/job/schedulerJob-input";
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") SchedulerJob model) {
		if (StringUtils.isBlank(model.getId())) {
			schedulerJobService.save(model);
		} else {
			schedulerJobService.update(model);
		}
		return model.getId();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		schedulerJobService.delete(id);
	}
	
	/**
	 * Ajax字典编码检查
	 * @return 0 通过验证 其它返回结果代表没有通过验证，并且结果为错误信息
	 */
	@RequestMapping("/ajaxCheckGroupAndName")
	@ResponseBody
	public String ajaxCheckGroupAndName(String jobGroup ,String jobName , String excludeId) {
		if (this.schedulerJobService.isExistsGroupAndName(jobGroup, jobName, excludeId)) {
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping("/triggerJob")
	@ResponseBody
	public void triggerJob(String id) {
		SchedulerHelper.triggerJob(schedulerJobService.get(id));
	}
}

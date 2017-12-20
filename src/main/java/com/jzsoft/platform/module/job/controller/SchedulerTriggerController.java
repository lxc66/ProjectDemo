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
import com.jzsoft.platform.module.job.model.SchedulerTrigger;
import com.jzsoft.platform.module.job.service.SchedulerJobService;
import com.jzsoft.platform.module.job.service.SchedulerTriggerService;

@Controller
@RequestMapping("/sys/schedulerTrigger")
public class SchedulerTriggerController extends JqGridController<SchedulerTrigger> {

	@Autowired
	private SchedulerTriggerService schedulerTriggerService;
	@Autowired
	private SchedulerJobService schedulerJobService;


	@RequestMapping("/list")
	public String list() {
//		setAttribute("schedulerJobs", schedulerJobService.getAll());
		return "/platform/module/job/schedulerTrigger-list";
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public String listData(@FormModel("page") Page<SchedulerTrigger> page,@FormModel("model") SchedulerTrigger model,String sord,String sidx){
		return this.refreshDataTable(schedulerTriggerService.getPage(model, page, sord, sidx));
	}
	
	@RequestMapping("/input")
	public String input(String id) {
		SchedulerTrigger model = null;
		if (StringUtils.isNotBlank(id)) {
			model = schedulerTriggerService.get(id);
		}else{
			model = new SchedulerTrigger();
		}
		setAttribute("model", model);
		setAttribute("schedulerJobs", schedulerJobService.getAll());
		return "/platform/module/job/schedulerTrigger-input";
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") SchedulerTrigger model) {
		if (StringUtils.isBlank(model.getId())) {
			schedulerTriggerService.save(model);
		} else {
			schedulerTriggerService.update(model);
		}
		return model.getId();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		schedulerTriggerService.delete(id);
	}
	
	@RequestMapping("/pause")
	@ResponseBody
	public void pause(String id) {
		SchedulerHelper.pauseTrigger(schedulerTriggerService.get(id));
	}
	
	@RequestMapping("/resume")
	@ResponseBody
	public void resume(String id) {
		SchedulerHelper.resumeTrigger(schedulerTriggerService.get(id));
	}
	
}

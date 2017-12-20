package com.jzsoft.platform.module.user.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.component.jqGrid.controller.JqGridController;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.core.helper.UserHelper;
import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.module.dictionary.service.DictionaryValueService;
import com.jzsoft.platform.module.user.model.SysUser;
import com.jzsoft.platform.module.user.service.SysUserService;
import com.jzsoft.platform.module.userauth.service.UserPrivilegeService;
import com.jzsoft.platform.module.userauth.service.UserRoleService;

@Controller
@RequestMapping("/sys/user")
public class SysUserController extends JqGridController<SysUser> {

	@RequestMapping("/index")
	public String index() {
		setAttribute("userTypeList", dictionaryValueService.getListWithDictCode(SysUser.USER_TYPE_DICT_CODE));
		return "/platform/module/user/user-index";
	}

	@RequestMapping("/list")
	public String list(String userType) {
		setAttribute("userType", userType);
		return "/platform/module/user/user-list";
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public String listData(@FormModel("page") Page<SysUser> page,@FormModel("model") SysUser model,String sord,String sidx){
//		String positionId=this.getRequest().getParameter("positionId");
//		String departmentId=this.getRequest().getParameter("departmentId");
//		String sord=this.getRequest().getParameter("sord");
//		String sidx=this.getRequest().getParameter("sidx");
		return this.refreshDataTable(userService.getPage(model, page, sord, sidx));
	}
	
	@RequestMapping("/input")
	public String input(String id,String userType) {
		SysUser model = null;
		if (StringUtils.isNotBlank(id)) {
			model = userService.get(id);
		}else{
			model = new SysUser();
			model.setEnableFlag(SysUser.ENABLE);
			model.setType(userType);
		}
		setAttribute("model", model);
		setAttribute("enableFlagMap", SysUser.getEnableFlagMap());
		return "/platform/module/user/user-input";
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") SysUser model) {
		if (StringUtils.isBlank(model.getId())) {
			userService.save(model);
		} else {
			userService.update(model);
		}
		return model.getId();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		userService.delete(id);
	}
	
	@RequestMapping("/detail")
	public String detail(String id) {
		SysUser model = userService.get(id);
		setAttribute("model", model);
		setAttribute("roles", userRoleService.getRolesWithUserId(id));
//		setAttribute("privileges", userPrivilegeService.getPrivilegesWithUserId(id));
		return "/platform/module/user/user-detail";
	}

	
	/**
	 * Ajax字典编码检查
	 * @return 0 通过验证 其它返回结果代表没有通过验证，并且结果为错误信息
	 */
	@RequestMapping("/ajaxCheckLoginName")
	@ResponseBody
	public String ajaxCheckLoginName(String loginName , String excludeLoginName) {
		if (this.userService.isExistsLoginName(loginName, excludeLoginName)) {
			return "false";
		}else{
			return "true";
		}
	}

	@RequestMapping("/forbidden")
	@ResponseBody
	public void forbidden(String id) {
		userService.forbidden(id);
	}

	@RequestMapping("/enabled")
	@ResponseBody
	public void enabled(String id) {
		userService.enabled(id);
	}

	@RequestMapping("/toResetPassword")
	public String toResetPassword(String id) {
		SysUser model = userService.get(id);
		setAttribute("model", model);
		return "/platform/module/user/user-resetPassword";
	}

	@RequestMapping("/resetPassword")
	@ResponseBody
	public void resetPassword(@FormModel("model") SysUser model){
		userService.resetPassword(model);
	}
	
	@RequestMapping("/toChangePassword")
	public String toChangePassword() {
		SysUser model = UserHelper.getCurrUser();
		setAttribute("model", model);
		return "/platform/module/user/user-changePassword";
	}
	

	@RequestMapping("/changePassword")
	@ResponseBody
	public void changePassword(@FormModel("model") SysUser model){
		userService.resetPassword(model);
	}

	/**
	 * 验证原始密码
	 */
	@RequestMapping("/checkOriginalPassword")
	@ResponseBody
	public String checkOriginalPassword(String userId , String password) {
		if (userService.checkOriginalPassword(userId, password)) {
			return "true";
		}else{
			return "false";
		}
	}
	
	@Autowired
	private SysUserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserPrivilegeService userPrivilegeService;
	@Autowired
	private DictionaryValueService dictionaryValueService;
}

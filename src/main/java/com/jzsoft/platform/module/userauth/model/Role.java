package com.jzsoft.platform.module.userauth.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.module.userauth.service.RolePrivilegeService;



public class Role implements java.io.Serializable {
	private static final long serialVersionUID = -4505135692261510731L;
	private String id;
	private String name;
	private String code;
	private String type;
	private Integer num;
	private String systemFlag;
	private String enableFlag;
	private String ft;
	private String lt;
	private String fu;
	private String lu;

  	/** 启用禁用常量 */
  	public static final String DISABLED="0";
  	public static final String ENABLE="1";
  	
  	/** 系统标志常量 */
  	public static final String POSITION_SYSTEMFLAG_YES="1";
  	public static final String POSITION_SYSTEMFLAG_NO="0";
  	
  	public static final String ROLE_TYPE_DICT_CODE = "roleType";
  	
	//--自定义
    private List<String> privilegeIdList = new ArrayList<String>(0);
  	
 	/**
 	 * @Description : TODO(启用/禁用Map)
 	 */
 	public static final Map<String, String> getEnableFlagMap() {
 		Map<String, String> map = new LinkedHashMap<String, String>();
 		map.put(ENABLE, "启用");
 		map.put(DISABLED, "禁用");
 		return map;
 	}
	
	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getSystemFlag() {
		return systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getFt() {
		return ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}

	public String getLt() {
		return lt;
	}

	public void setLt(String lt) {
		this.lt = lt;
	}

	public String getFu() {
		return fu;
	}

	public void setFu(String fu) {
		this.fu = fu;
	}

	public String getLu() {
		return lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}
	
	public List<String> getPrivilegeIdList() {
		return privilegeIdList;
	}

	public void setPrivilegeIdList(List<String> privilegeIdList) {
		this.privilegeIdList = privilegeIdList;
	}

	public String getRoleTypeView(){
 		if(StringUtils.isEmpty(type)){
 			return "";
 		}
 		return type;
 	}
	
	public String getEnableFlagView(){
		if(StringUtils.isEmpty(enableFlag)){
			return "";
		}
		return getEnableFlagMap().get(enableFlag);
	}
	
	public boolean isEnable(){
		return ENABLE.equals(enableFlag);
	}
	
	public boolean isSystemInternal(){
		return POSITION_SYSTEMFLAG_YES.equals(systemFlag);
	}
	
	public String getPrivilegeIds(){
		if(StringUtils.isBlank(id))return "";
		if(this.privilegeIdList.size()==0){
			List<String> privilegeIds = SpringContextHolder.getBean(RolePrivilegeService.class).getPrivilegeIdsWithRoleId(id);
			this.privilegeIdList.addAll(privilegeIds);
		}
		if(privilegeIdList.size()==0)return "";
		String ids="";
		for(String privilegeId:privilegeIdList){
			ids+=privilegeId+",";
		}
		return ids.substring(0, ids.length()-1);
	}
}
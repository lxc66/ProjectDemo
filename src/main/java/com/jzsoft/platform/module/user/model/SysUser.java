package com.jzsoft.platform.module.user.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.module.userauth.service.UserPrivilegeService;
import com.jzsoft.platform.module.userauth.service.UserRoleService;


public class SysUser implements java.io.Serializable {
	private static final long serialVersionUID = -4364358205406448381L;
	private String id;
	private String name;//用户昵称
	private String displayName;//用户ID
	private String pinyin;
	private String loginName;
	private String password;
	private String type;
	private String enableFlag;
	private Integer num;
	private String email;
	private String mobile;
	private String phone;
	private String headPortraitSmallId;
	private String headPortraitBigId;
	private String headPortraitSmallUrl;
	private String headPortraitBigUrl;
	private String ft;
	private String lt;
	private String fu;
	private String lu;
	
	private String userprofile;
	private String platformTitle;
	private String autograph;
	

	/** 用户类型字典 */
	public static final String USER_TYPE_DICT_CODE = "userType";

	/** 启用禁用常量 */
	public static final String DISABLED = "0";
	public static final String ENABLE = "1";

	/** 默认密码 */
	public static final String DEFAULT_PASSWORD = "000000";
	
	/** 头像附件编码 */
	public static final String HEADPORTRAIT_ATTACHMENT_CODE = "bd_userHeadPortrait";
	//--自定义
	private String enableFlagView;
    private List<String> roleIdList = new ArrayList<String>(0);
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
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(String id) {
		this.id = id;
	}

	// Property accessors
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeadPortraitSmallId() {
		return headPortraitSmallId;
	}

	public void setHeadPortraitSmallId(String headPortraitSmallId) {
		this.headPortraitSmallId = headPortraitSmallId;
	}

	public String getHeadPortraitBigId() {
		return headPortraitBigId;
	}

	public void setHeadPortraitBigId(String headPortraitBigId) {
		this.headPortraitBigId = headPortraitBigId;
	}

	public String getHeadPortraitSmallUrl() {
		return headPortraitSmallUrl;
	}

	public void setHeadPortraitSmallUrl(String headPortraitSmallUrl) {
		this.headPortraitSmallUrl = headPortraitSmallUrl;
	}

	public String getHeadPortraitBigUrl() {
		return headPortraitBigUrl;
	}

	public void setHeadPortraitBigUrl(String headPortraitBigUrl) {
		this.headPortraitBigUrl = headPortraitBigUrl;
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

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public List<String> getPrivilegeIdList() {
		return privilegeIdList;
	}

	public void setPrivilegeIdList(List<String> privilegeIdList) {
		this.privilegeIdList = privilegeIdList;
	}

    public String getCredentialsSalt() {
//      return username + salt;
      return "";
  }
	
	public boolean isEnable() {
		return enableFlag.equals(ENABLE);
	}

	public String getEnableFlagView() {
		if (StringUtils.isEmpty(enableFlag)) {
			return "";
		}
		return getEnableFlagMap().get(enableFlag);
	}
	
	public String getRoleIds(){
		if(StringUtils.isBlank(id))return "";
		if(roleIdList.size()==0){
			List<String> idList = SpringContextHolder.getBean(UserRoleService.class).getRoleIdsWithUserId(id);
			roleIdList.addAll(idList);
		}
		if(roleIdList.size()==0)return "";
		String ids="";
		for(String id:roleIdList){
			ids+=id+",";
		}
		return ids.substring(0, ids.length()-1);
	}
	
	public String getPrivilegeIds(){
		if(StringUtils.isBlank(id))return "";
		if(this.privilegeIdList.size()==0){
			List<String> privilegeIds = SpringContextHolder.getBean(UserPrivilegeService.class).getPrivilegeIdsWithUserId(id);
			this.privilegeIdList.addAll(privilegeIds);
		}
		if(privilegeIdList.size()==0)return "";
		String ids="";
		for(String privilegeId:privilegeIdList){
			ids+=privilegeId+",";
		}
		return ids.substring(0, ids.length()-1);
	}

	public String getUserprofile() {
		return userprofile;
	}

	public void setUserprofile(String userprofile) {
		this.userprofile = userprofile;
	}

	public String getPlatformTitle() {
		return platformTitle;
	}

	public void setPlatformTitle(String platformTitle) {
		this.platformTitle = platformTitle;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	
	
	
}
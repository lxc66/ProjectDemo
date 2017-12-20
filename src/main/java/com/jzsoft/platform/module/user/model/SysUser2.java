package com.jzsoft.platform.module.user.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.module.userauth.service.UserPrivilegeService;
import com.jzsoft.platform.module.userauth.service.UserRoleService;


public class SysUser2 implements java.io.Serializable {
	private static final long serialVersionUID = -4364358205406448381L;
	
    private long ID;
    private String Username;
    private String Password;
    private String Userprofile;
    private String Usertype;
    private String Personal;
    private String Autograph;
    private String Tel;
    private String Email;
    private Float Follow;
    private Float Fans;
    private Float Account;
    private String bankcard;
    private String type;
    private String Attachment;
    private double price;
    private String CodeId;
    private String Ccodeid;
    
//	private String name;
//	private String displayName;
//	private String pinyin;
//	private String loginName;
//	private String password;
//	private String type;
//	private String enableFlag;
//	private Integer num;
//	private String email;
//	private String mobile;
//	private String phone;
//	private String headPortraitSmallId;
//	private String headPortraitBigId;
//	private String headPortraitSmallUrl;
//	private String headPortraitBigUrl;
//	private String ft;
//	private String lt;
//	private String fu;
//	private String lu;

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
//	private String enableFlagView;
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
	public SysUser2() {
	}

	
	
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUserprofile() {
		return Userprofile;
	}

	public void setUserprofile(String userprofile) {
		Userprofile = userprofile;
	}

	public String getUsertype() {
		return Usertype;
	}

	public void setUsertype(String usertype) {
		Usertype = usertype;
	}

	public String getPersonal() {
		return Personal;
	}

	public void setPersonal(String personal) {
		Personal = personal;
	}

	public String getAutograph() {
		return Autograph;
	}

	public void setAutograph(String autograph) {
		Autograph = autograph;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Float getFollow() {
		return Follow;
	}

	public void setFollow(Float follow) {
		Follow = follow;
	}

	public Float getFans() {
		return Fans;
	}

	public void setFans(Float fans) {
		Fans = fans;
	}

	public Float getAccount() {
		return Account;
	}

	public void setAccount(Float account) {
		Account = account;
	}

	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAttachment() {
		return Attachment;
	}

	public void setAttachment(String attachment) {
		Attachment = attachment;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCodeId() {
		return CodeId;
	}

	public void setCodeId(String codeId) {
		CodeId = codeId;
	}

	public String getCcodeid() {
		return Ccodeid;
	}

	public void setCcodeid(String ccodeid) {
		Ccodeid = ccodeid;
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

//	public String getRoleIds(){
//		if(StringUtils.isBlank(ID))return "";
//		if(roleIdList.size()==0){
//			List<String> idList = SpringContextHolder.getBean(UserRoleService.class).getRoleIdsWithUserId(id);
//			roleIdList.addAll(idList);
//		}
//		if(roleIdList.size()==0)return "";
//		String ids="";
//		for(String id:roleIdList){
//			ids+=id+",";
//		}
//		return ids.substring(0, ids.length()-1);
//	}
//	
//	public String getPrivilegeIds(){
//		if(StringUtils.isBlank(id))return "";
//		if(this.privilegeIdList.size()==0){
//			List<String> privilegeIds = SpringContextHolder.getBean(UserPrivilegeService.class).getPrivilegeIdsWithUserId(id);
//			this.privilegeIdList.addAll(privilegeIds);
//		}
//		if(privilegeIdList.size()==0)return "";
//		String ids="";
//		for(String privilegeId:privilegeIdList){
//			ids+=privilegeId+",";
//		}
//		return ids.substring(0, ids.length()-1);
//	}
}
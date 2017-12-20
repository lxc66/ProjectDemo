package com.jzsoft.platform.module.config.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jzsoft.platform.module.module.model.Module;

public class Config implements Serializable {
	private static final long serialVersionUID = -7835729620066032593L;
	private String id;
	private String moduleId;
	private Module module;
	private String kind;
	private String mode;
	private String name;
	private String code;
	private String visibleFlag;
	private String ft;
	private String lt;
	private String fu;
	private String lu;
	private String description;
	
	public static final String KIND_GLOBAL = "1";
	public static final String KIND_MODULE = "2";
	public static final String KIND_USER = "3";
	
	public static final String MODE_RADIO = "1";
	public static final String MODE_CHECKBOX = "2";
	public static final String MODE_INPUT = "3";
	public static final String MODE_FILE = "4";
	
	public static final String VISIBLE_FLAG_VISIBLE = "1";
	public static final String VISIBLE_FLAG_INVISIBLE = "0";

	public static final Map<String, String> getKindMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(KIND_GLOBAL, "全局");
		map.put(KIND_MODULE, "模块");
		map.put(KIND_USER, "用户");
		return map;
	}
	
	public static final Map<String, String> getModeMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(MODE_RADIO, "单选");
		map.put(MODE_CHECKBOX, "多选");
		map.put(MODE_INPUT, "用户输入");
		return map;
	}
	
	public static final Map<String, String> getVisibleFlagMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(VISIBLE_FLAG_VISIBLE, "可见");
		map.put(VISIBLE_FLAG_INVISIBLE, "不可见");
		return map;
	}
	
	//自定义属性
	private List<ConfigItem> configItemList;
	private String kindView;
	private String modeView;
	private String visibleFlagView;
	public Config() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Config(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public String getVisibleFlag() {
		return visibleFlag;
	}

	public void setVisibleFlag(String visibleFlag) {
		this.visibleFlag = visibleFlag;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ConfigItem> getConfigItemList() {
		return configItemList;
	}

	public void setConfigItemList(List<ConfigItem> configItemList) {
		this.configItemList = configItemList;
	}

	public String getKindView() {
		return getKindMap().get(kind);
	}

	public String getModeView() {
		return getModeMap().get(mode);
	}

	public String getVisibleFlagView() {
		return getVisibleFlagMap().get(visibleFlag);
	}
	
	public boolean isKindGlobal(){
		return KIND_GLOBAL.equals(kind);
	}
	
	public boolean isKindModule(){
		return KIND_MODULE.equals(kind);
	}
	
	public boolean isKindUser(){
		return KIND_USER.equals(kind);
	}
	
	public boolean isModeRadio(){
		return MODE_RADIO.equals(mode);
	}
	
	public boolean isModeCheckbox(){
		return MODE_CHECKBOX.equals(mode);
	}
	
	public boolean isModeInput(){
		return MODE_INPUT.equals(mode);
	}
}

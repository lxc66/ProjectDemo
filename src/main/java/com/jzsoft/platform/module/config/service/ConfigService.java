package com.jzsoft.platform.module.config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.module.config.dao.ConfigDAO;
import com.jzsoft.platform.module.config.model.Config;


/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
@Transactional
public class ConfigService {

	public Config get(String id){
		return configDAO.get(id);
	}
	
	public Config getWithCode(String code){
		return configDAO.getWithCode(code);
	}
	
	public Page<Config> getPage(Config model, Page<Config> page, String sord,String sidx) {
		page.addParams("name", model.getName());
		page.addParams("code", model.getCode());
		page.addParams("sord", sord);
		page.addParams("sidx", sidx);
		return configDAO.getPage(page);
	}

	public void save(Config config) {
		if (!Config.KIND_MODULE.equals(config.getKind())) {
			config.setModuleId(null);
		}
		if (Config.KIND_USER.equals(config.getKind())) {
			config.setVisibleFlag(Config.VISIBLE_FLAG_VISIBLE);
		}
		configDAO.save(config);
		configItemService.save(config.getId(), config.getConfigItemList());
	}

	public void update(Config config) {
		if (!Config.KIND_MODULE.equals(config.getKind())) {
			config.setModuleId(null);
		}
		configDAO.update(config);
		configItemService.update(config.getId(), config.getConfigItemList());
	}

	public void delete(String id) {
		configItemService.deleteWithConfigId(id);
		configResultService.deleteWithConfigId(id);
		configDAO.delete(id);
	}
	
	/**
	 * 编码是否存在
	 */
	public boolean isExistsCode(String code,final String excludeCode) {
		Config model = configDAO.getWithCodeAndExclude(code, excludeCode);
		if (model != null) {
			return true;
		} 
		return false;
	}
	
	public List<Config> getListWithGlobal(){
		return configDAO.getListWithKind(Config.KIND_GLOBAL);
	}
	
	public List<Config> getListWithModule(){
		return configDAO.getListWithKind(Config.KIND_MODULE);
	}
	
	public List<Config> getListWithUser(){
		return configDAO.getListWithKind(Config.KIND_USER);
	}
	
	@Autowired
	private ConfigDAO configDAO;
	@Autowired
	private ConfigItemService configItemService;
	@Autowired
	private ConfigResultService configResultService;
}



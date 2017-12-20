package com.jzsoft.platform.module.config.external;

import java.util.List;

import com.jzsoft.platform.module.config.model.Config;

public interface IConfigService {
	
	/**
	 * 通过配置的code得到配置对象
	 * @param code 配置编码
	 * @return 配置对象， 如果没有此配置返回null
	 */
	public Config getConfigWithCode(String code);
	
	/**
	 * 根据全局配置编码得到全局配置的配置结果
	 * @param code 配置编码
	 * @return 如果没有配置则返回默认值，否则返回配置值;
	 */
	public List<String> getGlobalConfigResultWithCode(String code);
	
	/**
	 * 根据模块配置编码得到模块配置的配置结果
	 * @param code 配置编码
	 * @return 如果没有配置则返回默认值，否则返回配置值;
	 */
	public List<String> getModuleConfigResultWithCode(String code);
	
	/**
	 * 根据用户配置编码得到用户配置的配置结果
	 * @param code 配置编码
	 * @param userId 用户ID
	 * @return 如果没有配置则返回默认值，否则返回配置值;
	 */
	public List<String> getUserConfigResultWithCode(String code, String userId);
	
	/**
	 * 更新全局配置的结果
	 * @param code 配置编码
	 * @param result 配置结果,多个结果之间以英文','分隔
	 */
	public void updateGlobalConfigResult(String code, String result);
	
	/**
	 * 更新模块配置的结果
	 * @param code 配置编码
	 * @param result 配置结果,多个结果之间以英文','分隔
	 */
	public void updateModuleConfigResult(String code, String result);
	
	/**
	 * 更新用户配置的结果
	 * @param code 配置编码
	 * @param result 配置结果,多个结果之间以英文','分隔
	 * @param userId 用户ID
	 */
	public void updateUserConfigResult(String code, String result,String userId);

}

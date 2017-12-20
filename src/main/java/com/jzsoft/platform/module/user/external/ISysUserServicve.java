package com.jzsoft.platform.module.user.external;

import com.jzsoft.platform.module.user.model.SysUser;

/**
 * @author wdw
 * 系统用户对外提供同步接口
 * 
 * <p><b> 注意：</b></p>
 * <p>1、系统用户（SysUser）与权限模块是紧偶合的</p>
 * <p>2、若业务系统依赖权限模块，可以分以下情况使用系统用户（SysUser）</p>
 * <ul>
 *  <li>(1)直接使用系统用户（SysUser）实体做为业务系统的用户信息实体 </li>
 *  <li>(2)自定义用户实体，但必须使用此接口同步系统用户实体</li>
 * </ul>
 */
public interface ISysUserServicve {
	void save(SysUser model);
	
	void update(SysUser model);
	
	void delete(String id);
	
	void enabled(String id);
	
	void forbidden(String id);
	
	void resetPassword(String id,String password);
}

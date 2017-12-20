package com.jzsoft.platform.core.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jzsoft.platform.module.user.service.SysUserService;

public class MyCasRealm extends CasRealm {
	@Autowired
    private SysUserService userService;
	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(UserRolePermissionHelper.getRoles(username));
        authorizationInfo.setStringPermissions(UserRolePermissionHelper.getPermissions(username));
        return authorizationInfo;
    }
}
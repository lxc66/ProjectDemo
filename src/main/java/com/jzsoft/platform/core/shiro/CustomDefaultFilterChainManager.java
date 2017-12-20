package com.jzsoft.platform.core.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

import org.apache.shiro.config.Ini;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.SimpleNamedFilterList;
import org.springframework.util.CollectionUtils;

import com.jzsoft.platform.core.spring.HandlerSessionInterceptor;

public class CustomDefaultFilterChainManager extends DefaultFilterChainManager {
	private Map<String, String> filterChainDefinitionMap = null;
	private String loginUrl;
	private String successUrl;
	private String unauthorizedUrl;
	private static String PERMS_FILTER = DefaultFilter.perms.name();

	public CustomDefaultFilterChainManager() {
		setFilters(new LinkedHashMap<String, Filter>());
		setFilterChains(new LinkedHashMap<String, NamedFilterList>());
		addDefaultFilters(true);
	}

	public Map<String, String> getFilterChainDefinitionMap() {
		return filterChainDefinitionMap;
	}

	public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
		this.filterChainDefinitionMap = filterChainDefinitionMap;
		HandlerSessionInterceptor.setUrlFilterMap(filterChainDefinitionMap);
	}

	public void setCustomFilters(Map<String, Filter> customFilters) {
		if (!CollectionUtils.isEmpty(customFilters)) {
			for (Map.Entry<String, Filter> entry : customFilters.entrySet()) {
				String name = entry.getKey();
				Filter filter = entry.getValue();
				applyGlobalPropertiesIfNecessary(filter);
				if (filter instanceof Nameable) {
					((Nameable) filter).setName(name);
				}
				addFilter(name, filter, false);
			}
		}
	}

	public void setDefaultFilterChainDefinitions(String definitions) {
		Ini ini = new Ini();
		ini.load(definitions);
		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		setFilterChainDefinitionMap(section);
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}

	@PostConstruct
	public void init() {
		//apply global settings if necessary:
		Map<String, Filter> filters = getFilters();
		if (!CollectionUtils.isEmpty(filters)) {
			for (Map.Entry<String, Filter> entry : filters.entrySet()) {
				String name = entry.getKey();
				Filter filter = entry.getValue();
				applyGlobalPropertiesIfNecessary(filter);
				if (filter instanceof Nameable) {
					((Nameable) filter).setName(name);
				}
			}
		}

		//---此处可以将数据库中的URL权限添加到拦截器链中--start
		Map<String, String> permissionResourceMap = UserRolePermissionHelper.permissionResourceMap;
		for (Map.Entry<String, String> entry : permissionResourceMap.entrySet()) {
			String permission = entry.getKey();
			String url = entry.getValue();
//			System.out.println(permission+"-----"+url);
			addFirstToChain(url, PERMS_FILTER, permission);
		}//--end

		//---init defaultFilterChainDefinitions  build up the chains:
		Map<String, String> chains = getFilterChainDefinitionMap();
		if (!CollectionUtils.isEmpty(chains)) {
			for (Map.Entry<String, String> entry : chains.entrySet()) {
				String url = entry.getKey();
				String chainDefinition = entry.getValue();
				createChain(url, chainDefinition);
			}
		}
	}

	protected void initFilter(Filter filter) {
		//ignore 
		
	}

	public void addFirstToChain(String chainName, String filterName, String chainSpecificFilterConfig) {
		if (!StringUtils.hasText(chainName)) {
			throw new IllegalArgumentException("chainName cannot be null or empty.");
		}
		Filter filter = getFilter(filterName);
		if (filter == null) {
			throw new IllegalArgumentException("There is no filter with name '" + filterName + "' to apply to chain [" + chainName + "] in the pool of available Filters.  Ensure a " + "filter with that name/path has first been registered with the addFilter method(s).");
		}

		applyChainConfig(chainName, filter, chainSpecificFilterConfig);

		SimpleNamedFilterList chain = (SimpleNamedFilterList) ensureChain(chainName);
		chain.add(0, filter);
	}

	public FilterChain proxy(FilterChain original, List<String> chainNames) {
		NamedFilterList configured = new SimpleNamedFilterList(chainNames.toString());
		for (String chainName : chainNames) {
			configured.addAll(getChain(chainName));
		}
		return configured.proxy(original);
	}

	private void applyGlobalPropertiesIfNecessary(Filter filter) {
		applyLoginUrlIfNecessary(filter);
		applySuccessUrlIfNecessary(filter);
		applyUnauthorizedUrlIfNecessary(filter);
	}

	private void applyLoginUrlIfNecessary(Filter filter) {
		String loginUrl = getLoginUrl();
		if (StringUtils.hasText(loginUrl) && (filter instanceof AccessControlFilter)) {
			AccessControlFilter acFilter = (AccessControlFilter) filter;
			//only apply the login url if they haven't explicitly configured one already:
			String existingLoginUrl = acFilter.getLoginUrl();
			if (AccessControlFilter.DEFAULT_LOGIN_URL.equals(existingLoginUrl)) {
				acFilter.setLoginUrl(loginUrl);
			}
		}
	}

	private void applySuccessUrlIfNecessary(Filter filter) {
		String successUrl = getSuccessUrl();
		if (StringUtils.hasText(successUrl) && (filter instanceof AuthenticationFilter)) {
			AuthenticationFilter authcFilter = (AuthenticationFilter) filter;
			//only apply the successUrl if they haven't explicitly configured one already:
			String existingSuccessUrl = authcFilter.getSuccessUrl();
			if (AuthenticationFilter.DEFAULT_SUCCESS_URL.equals(existingSuccessUrl)) {
				authcFilter.setSuccessUrl(successUrl);
			}
		}
	}

	private void applyUnauthorizedUrlIfNecessary(Filter filter) {
		String unauthorizedUrl = getUnauthorizedUrl();
		if (StringUtils.hasText(unauthorizedUrl) && (filter instanceof AuthorizationFilter)) {
			AuthorizationFilter authzFilter = (AuthorizationFilter) filter;
			//only apply the unauthorizedUrl if they haven't explicitly configured one already:
			String existingUnauthorizedUrl = authzFilter.getUnauthorizedUrl();
			if (existingUnauthorizedUrl == null) {
				authzFilter.setUnauthorizedUrl(unauthorizedUrl);
			}
		}
	}
}
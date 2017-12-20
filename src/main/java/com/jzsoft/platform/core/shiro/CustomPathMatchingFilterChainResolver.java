package com.jzsoft.platform.core.shiro;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {
	private CustomDefaultFilterChainManager customDefaultFilterChainManager;

	public void setCustomDefaultFilterChainManager(CustomDefaultFilterChainManager customDefaultFilterChainManager) {
		this.customDefaultFilterChainManager = customDefaultFilterChainManager;
		setFilterChainManager(customDefaultFilterChainManager);
	}

	public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
		FilterChainManager filterChainManager = getFilterChainManager();
		if (!filterChainManager.hasChains()) {
			return null;
		}

		String requestURI = getPathWithinApplication(request);

		//the 'chain names' in this implementation are actually path patterns defined by the user.  We just use them
		//as the chain name for the FilterChainManager's requirements
		for (String pathPattern : filterChainManager.getChainNames()) {

			// If the path does match, then pass on to the subclass implementation for specific checks:
			if (pathMatches(pathPattern, requestURI)) {
				return filterChainManager.proxy(originalChain, pathPattern);
			}
		}
		return null;
		//		FilterChainManager filterChainManager = getFilterChainManager();
		//		if (!filterChainManager.hasChains()) {
		//			return null;
		//		}
		//		String requestURI = getPathWithinApplication(request);
		//		List<String> chainNames = new ArrayList<String>();
		//		for (String pathPattern : filterChainManager.getChainNames()) {
		//			if (pathMatches(pathPattern, requestURI)) {
		//				chainNames.add(pathPattern);
		//			}
		//		}
		//		if (chainNames.size() == 0) {
		//			return null;
		//		}
		//		System.out.println(requestURI+"------"+chainNames.toString());
		//		return customDefaultFilterChainManager.proxy(originalChain, chainNames);
	}
}
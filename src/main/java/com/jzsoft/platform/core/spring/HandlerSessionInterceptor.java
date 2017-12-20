package com.jzsoft.platform.core.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jzsoft.platform.core.helper.UserHelper;

public class HandlerSessionInterceptor extends HandlerInterceptorAdapter {
	private static List<String> anonymousUrls = new ArrayList<>();
	
	private PatternMatcher pathMatcher = new AntPathMatcher();
	private static String ANON_FILTER = DefaultFilter.anon.name();
	public static void setUrlFilterMap(Map<String, String> filterMap){
		for(Entry<String, String> filter:  filterMap.entrySet()){
			if(ANON_FILTER.equals(filter.getValue())){
				anonymousUrls.add(filter.getKey());
			}
		}
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		if (sessionTimeout(request)){
			response.setHeader("sessionstatus", "timeout");// 在响应头设置session状态
			return false;
		}
		return true;
	}
	
	private boolean sessionTimeout(HttpServletRequest request){
		if (UserHelper.getCurrUser() != null)return false;
		// 如果是ajax请求响应头会有，x-requested-with；
		if (request.getHeader("x-requested-with") == null || !request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			return false;
		}
		if(anonymousUrl(request)){
			return false;
		}
		return true;
	}

	private boolean anonymousUrl(HttpServletRequest request){
		String requestURI = WebUtils.getPathWithinApplication(request);
		for (String pathPattern : anonymousUrls) {
			if(pathMatcher.matches(pathPattern, requestURI)){
				return true;
			}
		}
		return false;
	}
}

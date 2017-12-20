package com.jzsoft.platform.core.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.RedirectView;

public class RedirectPostView extends RedirectView {
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		String targetUrl = this.getUrl();
		Map<String, ?> paramMap = model;
		response.setContentType( "text/html;charset=utf-8");   
	    try {
			PrintWriter out = response.getWriter();  
			out.println("<form name='postSubmit' method='post' action='"+targetUrl+"' >");  
			for (String key : paramMap.keySet()) {
				out.println("<input type='hidden' name='"+key+"' value='" + paramMap.get(key)+ "'>");
			}
			out.println("</form>");   
			out.println("<script>");   
			out.println("  document.postSubmit.submit()");   
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}   
	}
	
	public RedirectPostView(String url) {
		super(url);
	}

}

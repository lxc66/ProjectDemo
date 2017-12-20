package com.jzsoft.platform.core.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.google.common.collect.Maps;
import com.jzsoft.platform.core.spring.SpringMVCUtil;
import com.jzsoft.platform.core.validator.BeanValidators;
import com.jzsoft.platform.util.UUID;

/**
 * 所有Controller的基类
 * 
 */
public class SpringController {
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected Validator validator;
	
	public SpringController() {

	}

	/**
	 * 在request中添加pageId参数
	 * 
	 * @return
	 */
	@ModelAttribute("pageId")
	public String populatePageId() {
		return "page" + UUID.getUUID();
	}

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}
	
	/**
	 * 服务端参数有效性验证,返回json
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidatorForJson(Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			StringBuilder sb = new StringBuilder();
			for (String message : list){
				sb.append(message).append("<br/>");
			}
			this.getResponse().setStatus(400);
			this.getResponse().setHeader("requeststatus", "ConstraintViolationException");
			this.print(sb.toString());
			return false;
		}
		return true;
	}
	
	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public ModelAndView bindException(Exception ex,HttpServletRequest request) {  
		
		// 编译错误信息
		StringBuilder sb = new StringBuilder("错误信息：\n");
		if (ex != null) {
			if (ex instanceof BindException) {
				for (ObjectError e : ((BindException)ex).getGlobalErrors()){
					sb.append("☆" + e.getDefaultMessage() + "(" + e.getObjectName() + ")\n");
				}
				for (FieldError e : ((BindException)ex).getFieldErrors()){
					sb.append("☆" + e.getDefaultMessage() + "(" + e.getField() + ")\n");
				}
				logger.error(ex.getMessage());
			}else if (ex instanceof ConstraintViolationException) {
				for (ConstraintViolation<?> v : ((ConstraintViolationException)ex).getConstraintViolations()) {
					sb.append("☆" + v.getMessage() + "(" + v.getPropertyPath() + ")\n");
				}
			} else {
				//sb.append(Exceptions.getStackTraceAsString(ex));
				sb.append("☆" + ex.getMessage());
			}
		} else {
			sb.append("未知错误.\n\n");
		}
		
		Map<String,Object> m=Maps.newHashMap();
//		m.put("msg", StringUtils.toHtml(sb.toString()));
		this.getResponse().setStatus(400);
        return new ModelAndView("/platform/common/jsp/400", m);
    }
	
	/**
	 * 获取request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return SpringMVCUtil.getRequest();
	}

	/**
	 * 获取response对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return SpringMVCUtil.getResponse();
	}

	public void print(String result) {
		render(result);
	}

	public void render(String result) {
		SpringMVCUtil.render(result, "text/plain");
	}

	public void renderText(String result) {
		SpringMVCUtil.render(result, "text/plain");
	}

	public void renderJson(String result) {
		SpringMVCUtil.render(result, "application/json");
	}

	public void renderXml(String result) {
		SpringMVCUtil.render(result, "text/xml");
	}

	public void renderHtml(String result) {
		SpringMVCUtil.render(result, "text/html");
	}

	public void render(String result, String contentType) {
		SpringMVCUtil.render(result, contentType);
	}

	public String redirectTo(String url) {
		return "redirect:" + url;
	}

	public String forwardTo(String url) {
		return "forward:" + url;
	}

	/**
	 * 在request中添加值
	 * 
	 * @param key
	 * @param value
	 */
	public void setAttribute(String key, Object value) {
		getRequest().setAttribute(key, value);
	}
	
	public static final View UNRESOLVED_VIEW = new View() {
		@Override
		public String getContentType() {
			return null;
		}
		@Override
		public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) {
		}
	};
}

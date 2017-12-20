package com.jzsoft.platform.core.web.argument;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.util.WebUtils;

import com.jzsoft.platform.core.web.argument.util.MapWapper;

public class FormModelBinder {
	/**
	 * 提取索引的模式 如[0].
	 */
	private final Pattern INDEX_PATTERN = Pattern.compile("\\[(\\d+)\\]\\.?");
	private int autoGrowCollectionLimit = Integer.MAX_VALUE;
	private Map<String, Boolean> hasProcessedPrefixMap = new HashMap<String, Boolean>();
	
	private WebDataBinder binder;
	private NativeWebRequest request;
	private MethodParameter parameter;
	private WebDataBinderFactory binderFactory;
	private ServletRequest servletRequest;
	
	
	
	public FormModelBinder(WebDataBinder binder, NativeWebRequest request, MethodParameter parameter, WebDataBinderFactory binderFactory, ServletRequest servletRequest) {
		super();
		this.binder = binder;
		this.request = request;
		this.parameter = parameter;
		this.binderFactory = binderFactory;
		this.servletRequest = servletRequest;
	}

	public void doBind() throws Exception{
		WebDataBinder simpleBinder = binderFactory.createBinder(request, null, null);
		Class<?> targetType = binder.getTarget().getClass();
		if (Collection.class.isAssignableFrom(targetType)) {// bind collection or array
			bindWithCollection(simpleBinder);
		} else if (MapWapper.class.isAssignableFrom(targetType)) {
			bindMapWapper(simpleBinder);
		} else {// bind model
			ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
			servletBinder.bind(servletRequest);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void bindWithCollection(WebDataBinder simpleBinder) throws Exception{
		Type type = parameter.getGenericParameterType();
		Class<?> componentType = Object.class;
		Collection<Object> target = (Collection<Object>) binder.getTarget();
		List<Object> targetList = new ArrayList<Object>(target);

		if (type instanceof ParameterizedType) {
			componentType = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
		}

		if (parameter.getParameterType().isArray()) {
			componentType = parameter.getParameterType().getComponentType();
		}
		
		if(MultipartFile.class.isAssignableFrom(componentType)){
			bindCollectionSimpleByMultipartFile(simpleBinder, componentType, targetList);
		}else{
			for (Object key : servletRequest.getParameterMap().keySet()) {
				String prefixName = getPrefixName((String) key);
				// 每个prefix 只处理一次
				if (hasProcessedPrefixMap.containsKey(prefixName)) {
					continue;
				} else {
					hasProcessedPrefixMap.put(prefixName, Boolean.TRUE);
				}
				if (isSimpleComponent(prefixName)) { // bind simple type
					Map<String, Object> paramValues = WebUtils.getParametersStartingWith(servletRequest, prefixName);
					bindCollectionSimple(simpleBinder, componentType, targetList, prefixName, paramValues.values());
				} else { // 处理如 votes[1].title=votes[1].title&votes[0].title=votes[0].title&votes[0].id=0&votes[1].id=1
					bindCollectionComplex(componentType, targetList, key, prefixName);
				}
			}
		}
		target.clear();
		target.addAll(targetList);
	}

	private void bindCollectionSimpleByMultipartFile(WebDataBinder simpleBinder, Class<?> componentType, List<Object> targetList) {
		MultipartRequest multipartRequest = WebUtils.getNativeRequest(servletRequest, MultipartRequest.class);
		if (multipartRequest == null) {
			return;
		}
		Map<String, List<MultipartFile>> multipartFiles = multipartRequest.getMultiFileMap();
		for (Map.Entry<String, List<MultipartFile>> entry : multipartFiles.entrySet()) {
			String key = entry.getKey();
			String prefixName = getPrefixName((String) key);
			// 每个prefix 只处理一次
			if (hasProcessedPrefixMap.containsKey(prefixName)) {
				continue;
			} else {
				hasProcessedPrefixMap.put(prefixName, Boolean.TRUE);
			}

			if (isSimpleComponent(prefixName)) { // bind simple type
				Collection<Object> values = new ArrayList<Object>(entry.getValue());
				bindCollectionSimple(simpleBinder, componentType, targetList, prefixName, values);
			}
		}
	}
	
	private void bindCollectionSimple(WebDataBinder simpleBinder, Class<?> componentType, List<Object> targetList, String prefixName, Collection<Object> values) {
		Matcher matcher = INDEX_PATTERN.matcher(prefixName);
		if (!matcher.matches()) { // 处理如 array=1&array=2的情况
			for (Object value : values) {
				targetList.add(simpleBinder.convertIfNecessary(value, componentType));
			}
		} else { // 处理如 array[0]=1&array[1]=2的情况
			int index = Integer.valueOf(matcher.group(1));
			
			if (targetList.size() <= index) {
				growCollectionIfNecessary(targetList, index);
			}
			//--将属性添加到集合中
			targetList.set(index, simpleBinder.convertIfNecessary(values, componentType));
		}
	}
	

	private void bindCollectionComplex(Class<?> componentType, List<Object> targetList, Object key, String prefixName) throws Exception, BindException {
		Object component = null;
		// 先查找老的 即已经在集合中的数据（而不是新添加一个）
		Matcher matcher = INDEX_PATTERN.matcher(prefixName);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("bind collection error, need integer index, key:" + key);
		}
		int index = Integer.valueOf(matcher.group(1));
		if (targetList.size() <= index) {
			growCollectionIfNecessary(targetList, index);
		}
		Iterator<Object> iterator = targetList.iterator();
		for (int i = 0; i < index; i++) {
			iterator.next();
		}
		component = iterator.next();

		if (component == null) {
			component = BeanUtils.instantiate(componentType);
		}

		WebDataBinder componentBinder = binderFactory.createBinder(request, component, null);
		component = componentBinder.getTarget();

		if (component != null) {
			ServletRequestParameterPropertyValues pvs = new ServletRequestParameterPropertyValues(servletRequest, prefixName, "");
			//--绑定之前将servletRequest中的MultipartFile添加到待绑定的属性中
			bindMultipart(servletRequest, pvs,componentBinder,prefixName);
			componentBinder.bind(pvs);
			FormModelMethodArgumentResolver.validateIfApplicable(componentBinder, parameter);
			if (componentBinder.getBindingResult().hasErrors()) {
				if (FormModelMethodArgumentResolver.isBindExceptionRequired(componentBinder, parameter)) {
					throw new BindException(componentBinder.getBindingResult());
				}
			}
			targetList.set(index, component);
		}
	}
	
	
	private void bindMultipart(ServletRequest servletRequest , MutablePropertyValues mpvs,WebDataBinder binder , String prefixName) {
		MultipartRequest multipartRequest = WebUtils.getNativeRequest(servletRequest, MultipartRequest.class);
		if (multipartRequest == null) {
			return;
		}
		Map<String, List<MultipartFile>> multipartFiles = multipartRequest.getMultiFileMap();
		for (Map.Entry<String, List<MultipartFile>> entry : multipartFiles.entrySet()) {
			String key = entry.getKey();
			if(!key.startsWith(prefixName)){
				continue;
			}
			//--将Key的前辍去掉，否则绑定时找不到Key对应的MultipartFile
			key=key.substring(prefixName.length(),key.length());
			List<MultipartFile> values = entry.getValue();
			if (values.size() == 1) {
				MultipartFile value = values.get(0);
				if (binder.isBindEmptyMultipartFiles() || !value.isEmpty()) {
					mpvs.add(key, value);
				}
			}
			else {
				mpvs.add(key, values);
			}
		}
	}
	

	@SuppressWarnings("unchecked")
	private void bindMapWapper(WebDataBinder simpleBinder) throws Exception, BindException {
		Type type = parameter.getGenericParameterType();
		Class<?> keyType = Object.class;
		Class<?> valueType = Object.class;

		if (type instanceof ParameterizedType) {
			keyType = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
			valueType = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[1];
		}

		MapWapper<Object,Object> mapWapper = ((MapWapper<Object,Object>) binder.getTarget());
		Map<Object,Object> target = mapWapper.getInnerMap();

		for (Object key : servletRequest.getParameterMap().keySet()) {
			String prefixName = getPrefixName((String) key);

			// 每个prefix 只处理一次
			if (hasProcessedPrefixMap.containsKey(prefixName)) {
				continue;
			} else {
				hasProcessedPrefixMap.put(prefixName, Boolean.TRUE);
			}

			Object keyValue = simpleBinder.convertIfNecessary(getMapKey(prefixName), keyType);

			if (isSimpleComponent(prefixName)) { // bind simple type
				Map<String, Object> paramValues = WebUtils.getParametersStartingWith(servletRequest, prefixName);
				for (Object value : paramValues.values()) {
					target.put(keyValue, simpleBinder.convertIfNecessary(value, valueType));
				}
			} else {
				Object component = target.get(keyValue);
				if (component == null) {
					component = BeanUtils.instantiate(valueType);
				}
				WebDataBinder componentBinder = binderFactory.createBinder(request, component, null);
				component = componentBinder.getTarget();

				if (component != null) {
					ServletRequestParameterPropertyValues pvs = new ServletRequestParameterPropertyValues(servletRequest, prefixName, "");
					componentBinder.bind(pvs);

					FormModelMethodArgumentResolver.validateComponent(componentBinder, parameter);

					target.put(keyValue, component);
				}
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void growCollectionIfNecessary(final Collection collection, final int index) {
		if (index >= collection.size() && index < this.autoGrowCollectionLimit) {
			for (int i = collection.size(); i <= index; i++) {
				collection.add(null);
			}
		}
	}

	private boolean isSimpleComponent(String prefixName) {
		return !prefixName.endsWith(".");
	}

	private String getPrefixName(String name) {

		int begin = 0;

		int end = name.indexOf("]") + 1;

		if (name.indexOf("].") >= 0) {
			end = end + 1;
		}

		return name.substring(begin, end);
	}
	

	private Object getMapKey(String prefixName) {
		String key = prefixName;
		if (key.startsWith("['")) {
			key = key.replaceAll("\\[\'", "").replaceAll("\'\\]", "");
		}
		if (key.startsWith("[\"")) {
			key = key.replaceAll("\\[\"", "").replaceAll("\"\\]", "");
		}
		if (key.endsWith(".")) {
			key = key.substring(0, key.length() - 1);
		}
		return key;
	}
	

	@SuppressWarnings("unused")
	private static boolean validateParameter(MethodParameter parameter) {
		Annotation[] annotations = parameter.getParameterAnnotations();
		for (Annotation annot : annotations) {
			if (annot.annotationType().getSimpleName().startsWith("Valid")) {
				return true;
			}
		}

		return false;
	}
}

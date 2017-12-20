package com.jzsoft.demo3.entity;
import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import com.jzsoft.platform.core.entity.BaseEntity;
/**
 * 示例功能2Entity
 *
 * @author 小强
 * @version 2017-02-07
 */
@Data
@ToString
@EqualsAndHashCode(callSuper=true)
public class DmText2 extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 名称
	 */
	@Length(max=200, message="名称长度必须介于 0 和 200 之间")
	@NotBlank(message="名称不能为空！")
	private String name;
	/**
	 * 分类
	 */
	@Length(max=200, message="分类长度必须介于 0 和 200 之间")
	@NotBlank(message="分类不能为空！")
	private String type;
	/**
	 * E-mail
	 */
	@Length(max=200, message="E-mail长度必须介于 0 和 200 之间")
	private String email;
	/**
	 * 描述
	 */
	@Length(max=500, message="描述长度必须介于 0 和 500 之间")
	private String comments;
	/**
	 * 序号
	 */
	private Integer num;

	public static final String TYPE_NORMAL = "1";
	public static final String TYPE_VIP = "2";
	public static final Map<String, String> TypeMap = new LinkedHashMap<>();
	
	static {
		TypeMap.put(TYPE_NORMAL, "普通");
		TypeMap.put(TYPE_VIP, "贵宾");
	}
	
	public Map<String, String> getTypeMap(){
		return TypeMap;
	}
	
	public String getTypeView(){
		return TypeMap.get(type);
	}
	
}
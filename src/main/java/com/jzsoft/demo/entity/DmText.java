package com.jzsoft.demo.entity;
import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 示例功能Entity
 *
 * @author 小哈
 * @version 2017-01-19
 */
@Data
@ToString
public class DmText implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * E-mail
	 */
	private String email;
	/**
	 * 描述
	 */
	private String comments;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 序号
	 */
	private Integer num;
	/**
	 * 删除标记（0：正常；1：删除）
	 */
	private Integer delFlag;
}
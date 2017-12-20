package com.jzsoft.demo2.entity;
import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import com.jzsoft.platform.core.entity.BaseEntity;
/**
 * 示例功能Entity
 *
 * @author 小哈
 * @version 2017-01-23
 */
public class DmText extends BaseEntity implements Serializable {
	
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
	 * E-mail
	 */
	@Length(max=200, message="E-mail长度必须介于 0 和 200 之间")
	@NotBlank(message="E-mail不能为空！")
	private String email;
	/**
	 * 描述
	 */
	@Length(max=500, message="描述长度必须介于 0 和 500 之间")
	private String comments;
	/**
	 * 备注信息
	 */
	@Length(max=255, message="备注信息长度必须介于 0 和 255 之间")
	private String remarks;
	/**
	 * 序号
	 */
	private Integer num;
	/**
	 * 删除标记（0：正常；1：删除）
	 */
	private Integer delFlag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}
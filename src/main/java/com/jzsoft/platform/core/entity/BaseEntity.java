package com.jzsoft.platform.core.entity;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class BaseEntity {
	public String getDbName() {
//		return JDBCConstants.JDBC_TYPE;
		return "mysql";
	}
}

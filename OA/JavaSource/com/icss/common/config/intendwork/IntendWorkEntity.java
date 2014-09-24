/*
 * 创建日期 2005-8-19
 */
package com.icss.common.config.intendwork;

/**
 * 待办工作配置条目
 * @author YANGYANG
 * 2005-8-19 11:15:53
 */
public class IntendWorkEntity {
	
	//子系统名称
	private String sysName;
	//子系统ID
	private String sysId;
	//实现类的名称
	private String className;
	
	
	
	/**
	 * @return
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @return
	 */
	public String getSysId() {
		return sysId;
	}

	/**
	 * @return
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * @param string
	 */
	public void setClassName(String string) {
		className = string;
	}

	/**
	 * @param string
	 */
	public void setSysId(String string) {
		sysId = string;
	}

	/**
	 * @param string
	 */
	public void setSysName(String string) {
		sysName = string;
	}

}

/*
 * �������� 2005-8-19
 */
package com.icss.common.config.intendwork;

/**
 * ���칤��������Ŀ
 * @author YANGYANG
 * 2005-8-19 11:15:53
 */
public class IntendWorkEntity {
	
	//��ϵͳ����
	private String sysName;
	//��ϵͳID
	private String sysId;
	//ʵ���������
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

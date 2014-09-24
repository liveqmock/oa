/*
 * 创建日期 2005-8-19
 */
package com.icss.common.intendwork;

/**
 * 待办工作异常
 * 
 * @author YANGYANG
 * 2005-8-19 12:12:10
 */
public class IntendWorkException extends Exception{
	
	public IntendWorkException() {
	}

	public IntendWorkException(String msg) {
		super(msg);
	}

	public IntendWorkException(Exception e) {
		super(e.getMessage());
	}
	
}


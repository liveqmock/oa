/*
 * Created on 2004-4-14
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.config;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IntendWorkConfig {
	/**
	 * 待办工作状态
	 */
	public static final String WORKFLAG_ALL = "0"; //全部
	public static final String WORKFLAG_HASDONE = "1"; //已完成
	public static final String WORKFLAG_NOTDO = "2"; //未完成

	/**
	 * 待办工作提醒时间
	 */
	public static final String AWOKEATONCE = "0";//立即提醒 
	/**
	 * 从日程安排加入到待办工作中
	 */
	public static final String SCHEDULE_NOTIN_INTEND = "0";//还没有加入到待办中
	public static final String SCHEDULE_IN_INTEND = "1";//已经加入到待办中
	
	/**
	 * 点击TOP中滚动弹出待办
	 */
	public static final String COMM_LOCAL = "1";   //普通级别在本页刷新
	public static final String IMPORT_LOCAL ="2";  //重要级别在本页刷新
	public static final String COMM_NEW = "11";    //普通级别在新窗口打开
	public static final String IMPORT_NEW = "12";  //重要级别在新窗口打开
}

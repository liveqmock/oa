/*
 * 创建日期 2005-4-8
 */
package com.icss.common.log;

/**
 * @author YANGYAMG
 */
public interface LogFactory {
	/**
	 * 实例化日志
	 * @param classe	要记录日志的类
	 * @param head		消息头
	 * @return
	 */
	public Log newInstance(Class classe , String log_head);	//edit by yangyang 20050701
	/**
	 * 实例化日志
	 * @param classe	要记录日志的类
	 * @return
	 */
	public Log newInstance(Class classe);
	public Log newInstance(Log log);
}

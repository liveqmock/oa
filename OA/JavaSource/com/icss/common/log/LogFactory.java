/*
 * �������� 2005-4-8
 */
package com.icss.common.log;

/**
 * @author YANGYAMG
 */
public interface LogFactory {
	/**
	 * ʵ������־
	 * @param classe	Ҫ��¼��־����
	 * @param head		��Ϣͷ
	 * @return
	 */
	public Log newInstance(Class classe , String log_head);	//edit by yangyang 20050701
	/**
	 * ʵ������־
	 * @param classe	Ҫ��¼��־����
	 * @return
	 */
	public Log newInstance(Class classe);
	public Log newInstance(Log log);
}

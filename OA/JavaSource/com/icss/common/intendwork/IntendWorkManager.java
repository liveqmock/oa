/*
 * �������� 2005-8-19
 */
package com.icss.common.intendwork;

import java.util.List;

/**
 * ���칤���������ӿ�
 * 
 * ʵ��������ӿڵ����У�
 * com.icss.common.intendwork.examine.ExamineIntendWorkManager	�����˴��칤��ʵ���ࣩ
 * 
 * @author YANGYANG
 * 2005-8-19 11:16:54
 */
public interface IntendWorkManager {
	
	/**
	 * ���������Ķ����ȡ���칤������ļ���
	 * �������а�����
	 * ��ϵͳID
	 * ��ǰ�û�����Ϣ
	 * 
	 * @param 		�����Ķ���
	 * @return		����IntendWorkVO����ļ���
	 */
	public List getIntendWorks(IntendWorkContext context) throws IntendWorkException;
	
}

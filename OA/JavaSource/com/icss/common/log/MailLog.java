/*
 * �������� 2005-7-14
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.common.log;

import com.icss.common.log.filelog.FileLogFactory;

/**
 * @author lizb
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class MailLog {
	
	
	private static final int ONOFF = 1;
	
	public static void write(String str) {
		
		if (ONOFF == 1) {
			
			LogFactory factory = new FileLogFactory("MailPathLog");
			Log log = factory.newInstance(Log.class, "");
			log.info(str);
		}
	}
	
}

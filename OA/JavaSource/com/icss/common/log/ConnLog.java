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
public class ConnLog {
	private static final int ONOFF = 1;
	private static final String LOG_START = " [������] <";
	private static final String LOG_END = " [�ر�����] <";
	public static void open(String str) {
		if (ONOFF == 1) {
			LogFactory factory = new FileLogFactory("FileConn");
			Log log = factory.newInstance(Log.class, "");
			log.debug(LOG_START + str + ">");
		}
	}
	public static void close(String str) {
		if (ONOFF == 1) {
			LogFactory factory = new FileLogFactory("FileConn");
			Log log = factory.newInstance(Log.class, "");
			log.debug(LOG_END + str + ">");
		}
	}

}

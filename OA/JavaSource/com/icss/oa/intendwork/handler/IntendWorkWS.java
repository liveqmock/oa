package com.icss.oa.intendwork.handler;

import java.sql.Connection;
import java.sql.SQLException;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.util.Globals;


public class IntendWorkWS {
	
	/**
	 * ͨ��web�������Ӵ��칤����ȡ�����ݿ����ӣ�ί�и�IntendWork��ִ��
	 * @param topic ���칤������
	 * @param source ���칤����Դ
	 * @param url ���칤�����ӵ�ַ
	 * @param navigate ���칤��������ַ
	 * @param personid ���칤���û�
	 */
	public String addWSIntendWork( String topic, String source, String url, String navigate, String personid, String code, String id) {

		Connection conn = null;
		String str = "";
		try {
			//ȡ�����ݿ�����
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("IntendWorkWS.addWSIntendWork");
			
			//���ô��칤���߼��࣬�����Ӵ��칤���Ĳ���ί�и��߼���
			IntendWork intendWork = new IntendWork(conn);
			intendWork.addWork( topic, source, url, navigate, personid, code, id);
			
			str = "success";
			
		} catch (DBConnectionLocatorException e) {
			
			str = "faild";
			e.printStackTrace();
			throw new RuntimeException("ͨ��web�������Ӵ��칤�����ݿ����Ӵ���!");
						
		} catch (Exception e) {
			
			str = "faild";
			throw new RuntimeException("ͨ��web�������Ӵ��칤������");
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("IntendWorkWS.addWSIntendWork");
				} catch (SQLException e1) {
					throw new RuntimeException("ͨ��web�������Ӵ��칤������ر����ݿ����Ӵ���");
				}
			}
		}
		return str;
		
	}
	
	
	
}




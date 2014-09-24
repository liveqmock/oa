package com.icss.oa.intendwork.handler;

import java.sql.Connection;
import java.sql.SQLException;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.util.Globals;


public class IntendWorkWS {
	
	/**
	 * 通过web服务增加待办工作，取得数据库连接，委托给IntendWork类执行
	 * @param topic 待办工作标题
	 * @param source 待办工作来源
	 * @param url 待办工作连接地址
	 * @param navigate 待办工作导航地址
	 * @param personid 待办工作用户
	 */
	public String addWSIntendWork( String topic, String source, String url, String navigate, String personid, String code, String id) {

		Connection conn = null;
		String str = "";
		try {
			//取得数据库连接
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("IntendWorkWS.addWSIntendWork");
			
			//调用待办工作逻辑类，将增加待办工作的操作委托给逻辑类
			IntendWork intendWork = new IntendWork(conn);
			intendWork.addWork( topic, source, url, navigate, personid, code, id);
			
			str = "success";
			
		} catch (DBConnectionLocatorException e) {
			
			str = "faild";
			e.printStackTrace();
			throw new RuntimeException("通过web服务增加待办工作数据库连接错误!");
						
		} catch (Exception e) {
			
			str = "faild";
			throw new RuntimeException("通过web服务增加待办工作错误");
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("IntendWorkWS.addWSIntendWork");
				} catch (SQLException e1) {
					throw new RuntimeException("通过web服务增加待办工作错误关闭数据库连接错误");
				}
			}
		}
		return str;
		
	}
	
	
	
}




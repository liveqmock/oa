/*
 * Created on 2004-4-15
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.intendwork.admin;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.intendwork.handler.IntendWork;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteWorkServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		try {
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}

			String[] workid = request.getParameterValues("workid");  

			IntendWork intendWork = new IntendWork(conn);

			for (int i = 0; i < workid.length; i++) {
				intendWork.deleteWork(new Integer(workid[i]));
			}

			this.forward(
				request,
				response,
				"/servlet/AllIntendWorkServlet");

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
//	public static String remogetDo(String uri,String userName) throws HttpException, IOException{
//		HttpClient client = new HttpClient();
//		String urll = 
//				"http://10.99.20.10:9080/ids/service?idsServiceType=remoteapi&method=reminderDelete" +
//				"&appName=dataexchange&coAppName="+Base64Util.getBase64("")+"&" +
//				"uri=" + Base64Util.getBase64(uri) +
//				"&userName=" + Base64Util.getBase64(userName);
//		urll = urll.replaceAll("\r", "").replaceAll("\n", "");
//		HttpMethod method = new GetMethod(urll);
//		client.executeMethod(method);
//		String xml = new String(method.getResponseBody(),"UTF-8");
//		return xml;
//	}
}

/*
 * Created on 2004-7-19
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.util.CommUtil;
import com.icss.oa.util.PropertyManager;
/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeloldFileServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		
		String stopDate = request.getParameter("stopDate");
		
		Connection conn = null;
		HttpSession session = request.getSession();
		
		dirmanage redHandler = null;
		String domain = PropertyManager.getProperty("archivesdomain");
		String ip = PropertyManager.getProperty("archivesip");
		
		String doResult = "";
		
		try {
			
			long stopTime = CommUtil.getLongByTime(stopDate);
			
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("DeloldFileServlet");
			AddressHelp helper = new AddressHelp(conn);
			List personList =
				helper.getperson(
					(List) session.getAttribute("delperson"),
					request,
					"delperson");
			StringBuffer sendto = new StringBuffer();
			Iterator personIterator = personList.iterator();
			//对每个所选用户操作
			while (personIterator.hasNext()) {
				SelectOrgpersonVO selectOrgpersonVO =
					(SelectOrgpersonVO) personIterator.next();
				//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
				String userid = ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
				
				redHandler = new dirmanage(ip, userid, domain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
				
				MessageHandler mHandler = new MessageHandler();
				//系统文件夹
				String[][] systemStr= redHandler.fileheadList("");
				for(int i=0;i<systemStr[0].length;i++){
			
					String mailHead = systemStr[1][i];
					Date reDate = mHandler.getReceiveDate(mailHead);
					long retime = reDate.getTime();
					if(retime<stopTime+86400000){
						String mailpath = systemStr[2][i];
						redHandler.deletedir(mailpath,1);
					}
				}
				
				//得到用户自定义的文件夹
				String[] uFolder = redHandler.dirList("");
				for(int j=0;j<uFolder.length;j++){
					String folder = uFolder[j];
					String[][] userStr = redHandler.fileheadList(folder+"/");
					
					for(int i=0;i<userStr[0].length;i++){
			
						String mailHead = userStr[1][i];
						Date reDate = mHandler.getReceiveDate(mailHead);
						long retime = reDate.getTime();
						if(retime<stopTime+86400000){
							String mailpath = folder+"/"+userStr[2][i];
							redHandler.deletedir(mailpath,1);
						}
					}
				}
				
			}//while
			doResult = "删除成功！";
			
		} catch (ServiceLocatorException e) {
			doResult = "删除失败！";
			e.printStackTrace();
		} catch (HandlerException e) {
			doResult = "删除失败！";
			e.printStackTrace();
		} catch (Exception e) {
			doResult = "删除失败！";
			e.printStackTrace();
		}finally{
			if(redHandler!=null)
				try {
					redHandler.disconnect();
				} catch (LdapException e1) {
					e1.printStackTrace();
				}
			if (conn != null)
			try {
				conn.close();
				ConnLog.close("DeloldFileServlet");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		request.setAttribute("sendresult",doResult);

		this.forward(request, response, "/filetransfer/sendResult.jsp");
		
	}

}

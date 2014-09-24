/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.dao.FiletransferSetDAO;
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.filetransfer.vo.FiletransferSetVO;
import com.icss.oa.util.CommUtil;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 * 
 * 删除所有用户一段时间内的邮件（所有文件夹下）
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ManagerDelServlet extends ServletBase{

	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		String startDate = request.getParameter("startTime");
		String endDate = request.getParameter("endTime");
		System.out.println("startDate "+startDate);
		System.out.println("endDate "+endDate);
		
		Connection conn = null;
		Context ctx = null;
		dirmanage mailhandler = null;
		
		List personlist = new ArrayList();
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ManagerDelServlet");
			long starttime = CommUtil.getLongByTime(startDate);
			long endtime = CommUtil.getLongByTime(endDate)+86400000;
			
			//得到所有用户
			FiletransferSetDAO dao = new FiletransferSetDAO();
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);
			personlist = factory.find(new FiletransferSetVO());
			
			MessageHandler mhandler = new MessageHandler();
			String domain = PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip");
			
			Iterator pItr = personlist.iterator();
			while(pItr.hasNext()){  
				
				FiletransferSetVO vo = (FiletransferSetVO)pItr.next();
				String userid = vo.getFsUid();
			    /* for(int p=0; p<3;p++){*/
			    try{			    	
			    	mailhandler = MailhandlerFactory.getHandler(userid);
					//***********删除系统文件夹下的文件**************
					System.out.println("the path is:"+mailhandler.path);  
					String[][] str = mailhandler.fileheadList("");
					for(int i=0; i < str[0].length; i++){
						String mailHead = str[1][i];
						Date receDate = mhandler.getReceiveDate(mailHead);
						long recetime = receDate.getTime();
						
						Date date=new Date(starttime);
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						String formatTime = formatter.format(date);
						System.out.println("starttime "+formatTime);
						
						date=new Date(endtime);
						formatter = new SimpleDateFormat("yyyy-MM-dd");
						formatTime = formatter.format(date);
						System.out.println("endtime "+formatTime);
						
						date=new Date(receDate.getTime());
						formatter = new SimpleDateFormat("yyyy-MM-dd");
						formatTime = formatter.format(date);
						System.out.println("receDate "+formatTime);
						
						
						if(starttime<=recetime&&recetime<=endtime){
							String mailpath = str[2][i];
							mailhandler.deletedir(mailpath,1);
						}
					}
					//***********删除自定义文件夹下的文件***************
					//得到用户自定义的文件夹
					String[] uFolder = mailhandler.dirList("");
					//得到用户所有自定义文件夹中的邮件列表(一个文件夹是一个LIST，一起放在一个大LIST中)
					if(uFolder.length>0){
						for(int i=0;i<uFolder.length;i++){
							String[][] userStr = mailhandler.fileheadList(uFolder[i]);
							for(int j=0; j < str[0].length; j++){
								String mailHead = str[1][j];
								Date receDate = mhandler.getReceiveDate(mailHead);
								long recetime = receDate.getTime();
								if(starttime<=recetime&&recetime<=endtime){
									String mailpath = str[2][j];
									mailhandler.deletedir(mailpath,1);
								}
							}
						}
					}
					if(mailhandler!=null){
						mailhandler.disconnect();
					}
					System.out.println("successfully");
					//****************************************************		
			    }catch(Exception whileIE){
			    	System.out.println("the person is not exist!");
					if(mailhandler!=null){
						mailhandler.disconnect();
					}
			    }		
			}
			this.forward(request,response,"filetransfer/showDelTime.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (conn != null)
			try {
				conn.close();
				ConnLog.close("ManagerDelServlet");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    }
		
	}

}

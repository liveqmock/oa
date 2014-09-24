/*
 * Created on 2004-5-14
 */
package com.icss.oa.log.admin;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.log.handler.AttachFileBean;
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.log.handler.SendFileBean;
import com.icss.oa.log.vo.LogAccessoryVO;
import com.icss.oa.log.vo.LogMsgVO;
import com.icss.oa.util.CurrentUser;

public class AddLogMsgServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	SendFileBean sendFileBean = SendFileBean.getInstanceFromSession(session);
    	Connection conn = null;
        //获得信息内容
    	Integer sysid=request.getParameter("sysid")==null?new Integer(-1):new Integer(request.getParameter("sysid"));
        String logpheno = request.getParameter("logpheno");
        String logreason = request.getParameter("logreason");
        String loganalyse = request.getParameter("loganalyse");
        String logdesc = request.getParameter("logdesc");
        try {
        	
            //初始化用到的数据
        	
        	
            CurrentUser user = new CurrentUser();
            String currentUserId = user.getId();
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            LogHandler handler=new LogHandler(conn);
            LogMsgVO logmsgvo=new LogMsgVO();
            
            Integer logmsgid;
            //写入信息表
            Long CurrentTime = new Long(System.currentTimeMillis());
            logmsgvo.setSysId(sysid);
            logmsgvo.setLogPheno(logpheno);
            logmsgvo.setLogReason(logreason);
            logmsgvo.setLogAnalyse(loganalyse);
            logmsgvo.setLogDesc(logdesc);
            logmsgvo.setLogPerson(currentUserId);
            
            logmsgvo.setLogTime(CurrentTime);
            logmsgid=handler.addLogMsg(logmsgvo);
//            Statement stmt=null;
            
//            System.out.println("+++++++++++AddLogMsgServlet+++++++++++++++sendFileBean.filenumber()="+sendFileBean.filenumber());
            //写入附件表
            for(int index = 0; index < sendFileBean.filenumber(); index ++){
            	AttachFileBean atachFileBean = sendFileBean.getAttachFile(index);
            	 
            	String	path=atachFileBean.getUploadUrl();
            	
            	/*System.out.println("+++++++++++AddLogMsgServlet+++++++++++++++index="+index+"+path="+path);
            	System.out.println("+++++++++++AddLogMsgServlet+++++++++++++++downloadpath="+atachFileBean.getDownloadUrl());
                */
            	InputStream inputStream = new FileInputStream(path);
            	/*if(inputStream==null){
            		System.out.println("*************inputStream*****is null***************"+inputStream.available());
            	}else{
            		System.out.println("*************inputStream*****is not null***************"+inputStream.available());
            	}*/
            	LogAccessoryVO logaccessoryvo=new LogAccessoryVO();
            	logaccessoryvo.setAccessoryName(atachFileBean.getFileOriginName());
            	logaccessoryvo.setLogId(logmsgid);
            	logaccessoryvo.setAccessoryBlob(inputStream);
            	logaccessoryvo.setAccessoryUploadusr(currentUserId);
            	logaccessoryvo.setAccessoryOrder(String.valueOf(index));
            	handler.addLogMsgAccessory(logaccessoryvo);
            	
            	/*try {

    				stmt = conn.createStatement();
    				String sql = " insert into log_accessory(logid,accessory_name,accessory_blob,accessory_uploadusr) values( ";
    				
    				sql+=logmsgid;
    				sql+=" ,'"+atachFileBean.getFileOriginName()+"'";
    				sql+=" ,"+inputStream;
    				sql+=" ,'"+currentUserId+"')";
    				System.out.println("+++++++++++sql+++++++++++++++addLogMsgAccessory sql"+sql);
    				stmt.executeUpdate(sql);

    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
            	
            	*/
            	/*System.out.println("+++++++++++AddLogMsgServlet+++++++++++++++addLogMsgAccessory ok");
                if(logaccessoryvo.getAccessoryBlob()==null){
                	System.out.println("+++++++++++logaccessoryvo.getAccessoryBlob()==null+++++++++++++++"+logaccessoryvo.getAccessoryBlob().available());
                }else{
                	System.out.println("+++++++++++logaccessoryvo.getAccessoryBlob()is not=null+++++++++++++++"+logaccessoryvo.getAccessoryBlob().available());
                }*/
            	inputStream.close();
               
            }
            this.forward(request, response, "/servlet/ShowLogListServlet?parentId="+sysid);
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (sendFileBean!=null){
                	SendFileBean.removeSession(session);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

    }
}

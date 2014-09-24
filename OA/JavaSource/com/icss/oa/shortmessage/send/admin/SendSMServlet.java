/*
 * Created on 2004-5-27
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.send.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.shortmessage.allrecord.handler.AllDuanxinHistoryHandler;
import com.icss.oa.shortmessage.allrecord.vo.DuanxinHistoryVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SendSMServlet extends ServletBase{
	protected void performTask(HttpServletRequest request,HttpServletResponse response) 
    throws ServletException, IOException{
		Connection conn=null;
		String depid=request.getParameter("dep");
		String content=request.getParameter("message");
		
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SendSMServlet");
			Long date=new Long(System.currentTimeMillis());
			AllDuanxinHistoryHandler handler=new AllDuanxinHistoryHandler(conn);
			String senderid=handler.getUserId();
			String sendercnname=handler.getUserName();
			HttpSession session=request.getSession();
            //List sessionlist=(List)session.getAttribute(sessionname);
			AddressHelp helper = new AddressHelp(conn);
			List sessionlist =
				helper.getperson(
					(List) session.getAttribute("sendtoperson"),
					request,
					"sendtoperson");
			Iterator iter=sessionlist.iterator();
			while(iter.hasNext()){
				SelectOrgpersonVO selectpersonVO=(SelectOrgpersonVO)iter.next();
				DuanxinHistoryVO recordvo=new DuanxinHistoryVO();
				recordvo.setReceiverId(selectpersonVO.getUseruuid());
				recordvo.setDepId(depid);
				recordvo.setSenderId(senderid);
				recordvo.setSenderCnname(sendercnname);
				recordvo.setShContent(content);
				recordvo.setShDate(date);
				handler.addSMRecord(recordvo);
			}
			
			this.forward(request, response, "/shortmessage/send/sendsuccess.jsp");
			
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SendSMServlet");
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
	
	}
	
}

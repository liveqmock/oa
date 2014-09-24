/*
 * Created on 2004-4-27
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.icss.oa.message.handler.MsgHandler;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AddMsgManagerServlet extends ServletBase {
	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

		Connection conn=null;

		String orgid=request.getParameter("orgid");
		HttpSession session=request.getSession();
		List list0=(List) session.getAttribute("selectPersonSession");
		
		try{
			conn=this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddMsgManagerServlet");
			AddressHelp addresshelp=new AddressHelp(conn);
			List list=null;
			List resultList=new ArrayList();
			list=addresshelp.getperson(list0,request,"selectPersonSession");
			MsgHandler handler=new MsgHandler(conn);
			try{
				Iterator it=list.iterator();
				while(it.hasNext()){
					SelectOrgpersonVO vo=(SelectOrgpersonVO)it.next();
					resultList.add(handler.addMsgManager(orgid,vo.getUseruuid()));
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}

			request.setAttribute("resultList",resultList);

			this.forward(request, response,"/message/AddMsgManagerResult.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("AddMsgManagerServlet");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}


/*
 * Created on 2007-6-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.fo.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.fo.vo.FoVotepersonVO;
import com.icss.oa.log.handler.LogHandler;



/**
 *É¾³ý¼Æ»®
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FoCreateUserPasswordFromIDServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		
		
		//Integer planPlanid = Integer.valueOf(request.getParameter("planPlanid"));
		Integer planid = request.getParameter("planid")==null?(new Integer(-1)):(Integer.valueOf(request.getParameter("planid")));
		
		System.out.println("++++++FoCreateUserPasswordFromIDServlet++++++++++planid="+planid.toString());
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			
			FoHandler handler = new FoHandler(conn);
			List list=null;
			list=handler.getUserManagerList();
			String	password="";
			
			String	Id="";
			Iterator itr=list.iterator();
			while(itr.hasNext()){
				password="";
				FoVotepersonVO vo=(FoVotepersonVO) itr.next();
				Id=vo.getHeadshipLevelCode();
				if(Id==null||Id.length()<=0){
					password="111111";
				}else if(Id.length()<6){
					
					
					for(int i=1;i<=6-Id.length();i++){
						password=password+"1";
						//System.out.println("+++++++++++++++password"+i+"="+password);
						
					}
					//strbuf.append(Id);
					password=password+Id;
					//System.out.println("+++++++++++++++"+Id.length()+"password"+password+"id="+Id);
					
				}else{
					password=Id.substring(Id.length()-6,Id.length());
				}
				vo.setPassword(password);
				handler.updatePersonVO(vo);
			}
			
			
			// this.forward(request, response, "/servlet/FoUserManagerListServlet?planid="+planid);
			response.sendRedirect(request.getContextPath()+"/servlet/FoUserManagerListServlet?planid="+planid);

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}

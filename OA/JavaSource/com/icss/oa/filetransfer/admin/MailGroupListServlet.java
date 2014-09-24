package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressGroupVO;
import com.icss.oa.address.vo.AddressGroupinfoVO;
import com.icss.oa.address.vo.SysPersonVO;
import com.icss.oa.filetransfer.dao.SysPersonDAO;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.FiletransferTimeHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * Servlet implementation class for Servlet: MailGroupListServlet
 *
 */
 public class MailGroupListServlet extends com.icss.j2ee.servlet.ServletBase implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
		protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			Connection conn = null;
			Context ctx = null;

			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);

				//取得发件人信息
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();

				
//				--------------------------------------------------------------
//				取得分组信息  add by lxy
//				--------------------------------------------------------------
							
				Group ugroup = new Group(conn);
				List grouplist = ugroup.individualGroupList(user.getPersonUuid());
				List useringrouplist = new ArrayList();
				
				DAOFactory factory=new DAOFactory(conn);
				int num = grouplist.size();
				try {
					for(int i=0;i<num;i++){
						List allinone = new ArrayList();
						AddressGroupVO gvo = (AddressGroupVO)grouplist.get(i);
						List temp = ugroup.personInGroup(gvo.getId(), "2");
						for(int j=0;j<temp.size();j++){
							List tempuser = new ArrayList();
							AddressGroupinfoVO agvo = (AddressGroupinfoVO)temp.get(j);
							SysPersonDAO sdao = new SysPersonDAO(conn);
							sdao.setPersonuuid(agvo.getUserid());
							sdao.setDeltag("0");
							factory.setDAO(sdao);
							tempuser = factory.find(new SysPersonVO());
							if(tempuser.size()!=0){
								allinone.add((SysPersonVO)tempuser.get(0));
							}
						} 					 
						useringrouplist.add(allinone);
					}
					
				}catch (DAOException e) {
					e.printStackTrace();
				}

//				--------------------------------------------------------------
//				取得最近联系人信息  add by lxy
//				--------------------------------------------------------------
				
				FiletransferTimeHandler ftth = new FiletransferTimeHandler(conn);
				List presentcontact = ftth.selectByTime(user.getPersonUuid(),10);
			
				request.setAttribute("grouplist", grouplist);
				request.setAttribute("useringrouplist", useringrouplist);
				request.setAttribute("presentcontact", presentcontact);
				
				this.forward(request, response, "/mail/PersonList_Right.jsp");

			} catch (ServiceLocatorException e1) {
				e1.printStackTrace();
	 
			} catch (EntityException e) {
				e.printStackTrace();

			} finally {
				if (conn != null) {
					try {
						conn.close();
						ConnLog.close("FileTransferServlet");
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}

			}

		}  	  	    
}
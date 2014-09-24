package com.icss.oa.address.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.handler.EntrustHandler;
import com.icss.oa.address.vo.EntrustVO;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.commsite.handler.HandlerException;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
/**
 * 得到组织的uuid,将本组织下所有人的信息都放入session中
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SetEntrustServlet extends ServletBase {
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException {
			Connection conn = null;
			HttpSession session = null;
		try{	
			try {
				conn = getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("SetEntrustServlet");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}

			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			
			if ("add".equals(request.getParameter("flag"))){
				
				session = request.getSession();
				AddressHelp helper = new AddressHelp(conn);
				List personList =
					helper.getperson(
						(List)session.getAttribute("entrust"),
						request,
						"entrust");
				if (personList.size() > 1){
					this.forward(
						request,
						response,
						"/address/addsuccess.jsp?text=委托失败，您不能把工作委托给"+personList.size()+"个人");
				}else{
					addPerson2Entrust(user.getUserID(),user.getPersonUuid(),personList,conn,request,response);
	
					this.forward(
						request,
						response,
						"/address/addsuccess.jsp?text=委托成功");
				}
			}

			if ("cancel".equals(request.getParameter("flag"))){
				cancelPerson2Entrust(
					Integer.valueOf(request.getParameter("id")),
					user.getUserID(),
					user.getPersonUuid(),
					request.getParameter("substituteUid"),
					request.getParameter("substituteUuid"),
					Long.valueOf(request.getParameter("starttime")),
					conn);
//					System.out.println("starttime = "+request.getParameter("starttime"));
				this.forward(
					request,
					response,
					"/address/addsuccess.jsp?text=收回成功");
			}
			
//			根据uid得到用户邮件信息
			EntrustHandler handler = new EntrustHandler(conn);
			List list = handler.getByEntrustUid(user.getUserID());
			request.setAttribute("userid", user.getUserID());
			request.setAttribute("list", list);
			if(list.size()==0){
				this.forward(
					request,
					response,
					"/address/addentrust.jsp");
			}else{
				this.forward(
					request,
					response,
					"/address/cancelentrust.jsp");
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			handleError(ex);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("SetEntrustServlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//
		}
	}

	private void addPerson2Entrust(
		String entrustUid,
		String entrustUuid,
		List personList,
		Connection conn,
		HttpServletRequest request,
		HttpServletResponse response) {
		if (personList != null) {
			Iterator personIterator = personList.iterator();
			if (personIterator.hasNext()) {
				SelectOrgpersonVO selectOrgpersonVO =
					(SelectOrgpersonVO) personIterator.next();
				//判断是否把工作交给自己
				if (selectOrgpersonVO.getUserid().equals(entrustUid)){
					try {
						this.forward(
							request,
							response,
							"/address/addsuccess.jsp?text=委托失败，您不能把工作委托自己");
					} catch (ServletException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else{
					EntrustHandler handler = new EntrustHandler(conn);
					EntrustVO entrustvo = new EntrustVO();
					entrustvo.setEntrustUid(entrustUid);
					entrustvo.setEntrustUuid(entrustUuid);
					entrustvo.setSubstituteUid(selectOrgpersonVO.getUserid());
					entrustvo.setSubstituteUuid(selectOrgpersonVO.getUseruuid());
					entrustvo.setStarttime(new Long(System.currentTimeMillis()));
					entrustvo.setEndtime(new Long(0));
					try {
						handler.add(entrustvo);
					} catch (HandlerException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	private void cancelPerson2Entrust(
		Integer id,
		String entrustUid,
		String entrustUuid,
		String substituteUid,
		String substituteUuid,
		Long starttime,
		Connection conn){
			EntrustHandler handler = new EntrustHandler(conn);
			EntrustVO entrustvo = new EntrustVO();
			entrustvo.setId(id);
			entrustvo.setEntrustUid(entrustUid);
			entrustvo.setEntrustUuid(entrustUuid);
			entrustvo.setSubstituteUid(substituteUid);
			entrustvo.setSubstituteUuid(substituteUuid);
			entrustvo.setStarttime(starttime);
			entrustvo.setEndtime(new Long(System.currentTimeMillis()));
			System.out.println("starttime2 = "+ starttime);
			try {
				handler.alter(entrustvo);
			} catch (HandlerException e) {
				e.printStackTrace();
			}
	}
}

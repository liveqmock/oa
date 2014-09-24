package com.icss.oa.ftp;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eprobiti.trs.TRSException;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.orgtree.handler.HandlerException;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * Servlet implementation class for Servlet: FtpTrsServlet
 *
 */
 public class FtpTrsServlet extends ServletBase {
	 protected void performTask(
				HttpServletRequest request,
				HttpServletResponse response)
				throws ServletException, IOException {
		 
		 Connection conn = null;
		 try {
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			String keyword = request.getParameter("keyword");
			String firstrecord = request.getParameter("firstrecord");
			String lastrecord = request.getParameter("lastrecord");
			String type = request.getParameter("type");
			if(type == null || "".equals(type.trim())){
				type = "1";
			}
			if(lastrecord == null || "".equals(lastrecord.trim())){
				lastrecord = "0";
			}
			FtpRightHandler frhandler = new FtpRightHandler(conn);
			List readlist = frhandler.getAllRead(user.getPersonUuid());
			FtpTrs ft = new FtpTrs();
			List l = null;
			if("1".equals(type)){
				l = ft.SearchNext(readlist,keyword,Integer.parseInt(lastrecord));
			}else{
				l = ft.SearchPrevious(readlist,keyword,Integer.parseInt(firstrecord));
			}
			request.setAttribute("result",l.get(0) );
			request.setAttribute("total",l.get(3));
			request.setAttribute("lastrecord",l.get(1));
			request.setAttribute("firstrecord",l.get(2));
			request.setAttribute("keyword",keyword);
			this.forward(request, response, "/ftp/search.jsp");
		 }catch (EntityException e) {
			e.printStackTrace();
		 }catch (ServiceLocatorException e) {
			e.printStackTrace();
		 }catch (HandlerException e) {
			e.printStackTrace();
		 }catch (DAOException e) {
			e.printStackTrace();
		 }catch (TRSException e) {
			e.printStackTrace();
		 }finally{
			try {
				if(conn!=null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
		 
	 }
}
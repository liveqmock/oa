package com.icss.oa.useraddress.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.useraddress.handler.UserAddressHandler;

public class userAddressQueryServlet extends ServletBase {

	
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
			StringBuffer sysout=new StringBuffer();
			sysout.append("huang=").append(System.currentTimeMillis()).append("！！！！");
			
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);	
				ConnLog.open("userAddressQueryServlet");
				StringBuffer sql=new StringBuffer();
				boolean havesql=false;
				String ip = request.getParameter("ip");
				String username = request.getParameter("username");
				String dept = request.getParameter("dept");
				String opesys = request.getParameter("opesys");
				String roomnum = request.getParameter("roomnum");
				String word = request.getParameter("word");
				String machcircs = request.getParameter("machcircs");
				String region = request.getParameter("region");
				String machname = request.getParameter("machname");
				
				if(ip!=null&&!ip.equals("")){
								 sql.append("OD_IP LIKE '%"+ip+"%' ");
								 havesql=true;
							}	
				if(username!=null&&!username.equals("")){
					if(havesql) sql.append("AND ");
					 sql.append("OD_usrname LIKE '%"+username+"%' ");
					 havesql=true;
										}	
				if(dept!=null&&!dept.equals("")){
									if(havesql) sql.append("AND ");
									 sql.append("OD_dept LIKE '%"+dept+"%' ");
									 havesql=true;
														}	
				if(opesys!=null&&!opesys.equals("")){
									if(havesql) sql.append("AND ");
									 sql.append("OD_opesys LIKE '%"+opesys+"%' ");
									 havesql=true;
														}	
				if(roomnum!=null&&!roomnum.equals("")){
									if(havesql) sql.append("AND ");
									 sql.append("OD_roomnum LIKE '%"+roomnum+"%' ");
									 havesql=true;
														}	
				if(word!=null&&!word.equals("")){
									if(havesql) sql.append("AND ");
									 sql.append("OD_word LIKE '%"+word+"%' ");
									 havesql=true;
														}	
				if(machcircs!=null&&!machcircs.equals("")){
													if(havesql) sql.append("AND ");
													 sql.append("OD_MACHCIRCS LIKE '%"+machcircs+"%' ");
													 havesql=true;
																		}	
				if(region!=null&&!region.equals("")){
																	if(havesql) sql.append("AND ");
																	 sql.append("OD_REGION LIKE '%"+region+"%' ");
																	 havesql=true;
																						}	
				if(machname!=null&&!machname.equals("")){
																	if(havesql) sql.append("AND ");
																	 sql.append("OD_MACHNAME LIKE '%"+machname+"%' ");
																	 havesql=true;
																						}	
				
				
				
				UserAddressHandler handler = new UserAddressHandler(conn);
				sysout.append("huang=").append(System.currentTimeMillis()).append("！！！！");
				StringBuffer sbt=new StringBuffer();
				System.out.println("+++++++++++++++++sql"+sql.toString());
				List userinfoList=handler.getUserSearchList(sql.toString(),sbt);
				sysout.append(sbt.toString());
				
//				List userwordlist = userinfohandler.getUserRegionList();
				List deptlist=handler.getUserDeptList();
				request.setAttribute("userinfoList", userinfoList);
				List regionlist=new ArrayList();
				regionlist=handler.getUserRegionList();
				request.setAttribute("regionlist",regionlist);
//				request.setAttribute("userwordlist", userwordlist);
				request.setAttribute("deptlist",deptlist);
				this.forward(request,response,"/useraddress/showDetailList.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
				handleError(e);
				
			} catch (Throwable e) {
				e.printStackTrace();
				
			} finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("userAddressQueryServlet");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

	}

}

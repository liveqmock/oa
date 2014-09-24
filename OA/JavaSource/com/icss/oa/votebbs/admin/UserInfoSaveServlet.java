/*
 * 创建日期 2007-3-9
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.vo.BbsVoteuserVO;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class UserInfoSaveServlet extends ServletBase {

	/* （非 Javadoc）
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成方法存根
		Connection conn = null;
		try {
			conn = super.getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			BbsVoteuserVO userVO = new BbsVoteuserVO();
			Integer userId = new Integer(0);
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			String loginname = request.getParameter("username");
			String password = request.getParameter("userpwd");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex") == null ? "" : request.getParameter("sex");
			String dep = request.getParameter("dep") == null ? "" : request.getParameter("dep");
			String work = request.getParameter("work") == null ? "" : request.getParameter("work");
			String mainId = request.getParameter("mainId") == null ? "-1" : request.getParameter("mainId");
			
			userVO.setVuLoginname(loginname);
			userVO.setVuPassword(password);
			userVO.setVuName(name);
			userVO.setVuSex(sex);
			userVO.setVuPost(work);
			userVO.setVuMainid(mainId);
			userVO.setVuDep(dep);
			userVO.setVuIp(request.getRemoteAddr());
			
			List list = handler.getUserListByName(loginname,mainId);
//			System.err.println("1:"+list.size());
			String status = "0";
			if(list!=null&&list.size()>0){//已存在此用户
				status = "2";
			}
			else{
				userId = handler.addUser(userVO);//不存在此用户则新增
				status = "1";
			}
//			System.err.println("2:"+status);
			Cookie c= new Cookie("userId",userId.toString());				
			c.setMaxAge(60*60);
			response.addCookie(c);
			//String dist =request.getContextPath()+ "/servlet/ArticleShowServlet?mainId="+ mainId+ "&userId="+ value+ "&action=4";
			String dist = request.getContextPath()+ "/servlet/BbsLoginServlet?status="+status+"&mainId="+mainId;
			response.sendRedirect(dist);
			
		} catch (ServiceLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

}

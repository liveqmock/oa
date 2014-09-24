package com.icss.oa.user.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.handler.PhoneHandler;
import com.icss.oa.user.handler.hrUserInfoHandler;
import com.icss.oa.user.vo.UserInfoSearchVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class UserInfoServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		Context ctx = null;

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			PhoneHandler pHandler = new PhoneHandler(conn);
//			获取登陆人员信息
			ctx = Context.getInstance();
            UserInfo user = ctx.getCurrentLoginInfo();
			//处理人员查看权限
//			获取登陆人员的职级,从人事视图中获取,如果没有返回空
            String jobcode = pHandler.getPersonJobCode(user.getPersonUuid());
            //System.out.println("--------job code is "+jobcode);
            //获取登陆人员的职级查看权限
            String searchpriv = "";
            if(!"".equals(jobcode)){
            	searchpriv = pHandler.getSearchPrivJobCode(jobcode);
            }
            //System.out.println("--------search priv by job "+searchpriv);
            request.setAttribute("searchpriv", searchpriv);
            //获取登陆人员的特殊查看权限,如果没有返回空
            String specialpriv = pHandler.getSpecialSearchPriv(user.getPersonUuid());
            //System.out.println("--------specialpriv "+specialpriv);
            request.setAttribute("specialpriv", specialpriv);

			//ctx = Context.getInstance();
			//UserInfo user = ctx.getCurrentLoginInfo();
			//String uuid = ctx.getCurrentPersonUuid();
			String uuid = request.getParameter("personuuid");
			hrUserInfoHandler handler = new hrUserInfoHandler(conn);
			UserInfoSearchVO vo = handler.getUserInfo(uuid);
			
			request.setAttribute("userInfoSearchVO", vo);
			
			if(request.getParameter("image")!=null){
				this.forward(request, response, "/user/userimage.jsp");
			}else{
				this.forward(request, response, "/user/userInfo.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}

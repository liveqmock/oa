package com.icss.oa.counter.admin;

import java.util.Date;
import java.util.StringTokenizer;
import javax.servlet.http.*;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.counter.handler.VRHanlder;
import com.icss.oa.counter.vo.ViewRecordVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class CounterServlet extends ServletBase {
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		
		//System.out.println("#################");
		Context ctx = null;
		try {
			ctx = Context.getInstance();

			//UserInfo info = ctx.getCurrentLoginInfo();
			//System.out.println("#####"+info.getSysList());
			//UserInfo info1=(UserInfo)request.getSession().getAttribute(com.icss.j2ee.util.Globals.J2EE_USER_NAME);
			
			//System.out.println("#####"+info1.getSysList());
			
			String personuuid = ctx.getCurrentPersonUuid();
			String site = request.getParameter("site");
			String ipAddress = request.getRemoteAddr();
			String Agent = request.getHeader("User-agent");
			StringTokenizer st = new StringTokenizer(Agent, ";");
			st.nextToken();
			String ieVersion = st.nextToken();
			String osVersion = st.nextToken();
			
			Date currentTime = new Date();
			// 取得浏览时间
			long date = currentTime.getTime();
			
			//System.out.println("#################"+personuuid+";"+site+";"+ipAddress+";"+ieVersion+";"+osVersion);
			
			ViewRecordVO vo = new ViewRecordVO();
			vo.setUseruuid(personuuid);
			vo.setIp(ipAddress);
			vo.setAgent(ieVersion);
			vo.setOsVersion(osVersion);
			vo.setDate(date);
			vo.setSite(site);
			
			VRHanlder handler = new VRHanlder();
			handler.addMemoryList(vo);
			
		} catch (EntityException e) {
			e.printStackTrace();
		}
	}
}
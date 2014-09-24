package com.icss.oa.statsite.admin;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.*;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.oa.statsite.handler.*;
import com.icss.oa.statsite.vo.*;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.oa.util.*;

public class AddStatSite extends TagSupport {
	private String modulename;
	private String value;
	private String ip;

	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public int doStartTag() throws JspException {
		Connection conn = null;
		try {
			try {
				//System.out.println("Sunchuanting:     "+StatSiteControl.gejndi());
				conn = DBConnectionLocator.getInstance().getConnection(StatSiteControl.gejndi());
				ConnLog.open("AddStatSite");
			} catch (ServiceLocatorException e) {
				throw new RuntimeException("取得数据库连接错误");
			}

			//得到当前时间和人员
			Context ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			long longtime = System.currentTimeMillis();
			Long Longtime = new Long(longtime);
			String userid = user.getCnName();

			//向数据库中插入记录
			StatSiteHandler handler = new StatSiteHandler(conn);
			ipManagerHandler handler1 = new ipManagerHandler(conn);
			StatSiteVO statsiteVO = new StatSiteVO();
			StatSiteDateVO statsitedateVO = new StatSiteDateVO();
			StatSiteMoldVO StatSiteMoldvo = new StatSiteMoldVO();

			statsiteVO.setModuleid(modulename);
			statsiteVO.setTime(Longtime);
			statsiteVO.setUserid(userid);
			statsiteVO.setIp(ip);
			try {
				statsiteVO.setAddress(handler1.IsAdress(ip));
			} catch (Throwable e1) {
				e1.printStackTrace();
			}

			long time1 = System.currentTimeMillis();
			java.util.Date date = new java.util.Date(time1);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String formatTime = formatter.format(date);

			handler.add(statsiteVO);
			handler.updateMoldNumber(modulename);
			handler.updateIpNumber(statsiteVO.getAddress());

		} catch (Exception e) {
			e.printStackTrace();
			throw new JspTagException("TagException:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("AddStatSite");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return SKIP_BODY;
	}
	public int doEndTag() {
		return EVAL_PAGE;
	}
}

/*
 * Created on 2004-5-20
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.partrecord.handler;

import java.sql.Connection;
import java.util.List;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.shortmessage.partrecord.dao.DuanxinShortaccessSysOrgSearchDAO;
import com.icss.oa.shortmessage.partrecord.vo.DuanxinShortaccessSysOrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DXPartRecordHandler {
	
	private Connection conn;
	
	public DXPartRecordHandler(Connection conn) {
	       this.conn = conn;
	}
	
	//通过personuuid获取此人员能够管理的短信发送部门的列表
	public List getPoweredOrgListByperId(String personId){
		
		List list=null;
		DAOFactory factory = new DAOFactory(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("DUANXIN_SHORTACCESS.SA_ID,DUANXIN_SHORTACCESS.PERSONID,DUANXIN_SHORTACCESS.DEPID,DUANXIN_SHORTACCESS.ACCESSDEPID,SYS_ORG.ORGUUID,SYS_ORG.CNNAME ");
		sql.append("FROM ");
		sql.append("DUANXIN_SHORTACCESS,SYS_ORG ");
		sql.append("WHERE ");
		sql.append("DUANXIN_SHORTACCESS.DEPID=SYS_ORG.ORGUUID ");
		sql.append("AND ");
		sql.append("DUANXIN_SHORTACCESS.PERSONID= '"+personId+"'");
		DuanxinShortaccessSysOrgSearchDAO dao=new DuanxinShortaccessSysOrgSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		try{
		 list=(List)factory.find(new DuanxinShortaccessSysOrgVO());
		 //System.out.println("#############################"+list);
		 
		}
		catch(Exception e){
			//System.out.println("#############################");
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public String getUserId() {

		Context ctx;
		UserInfo user = null;
		try {
			ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		if (user != null)
			return user.getPersonUuid();
		else
			return null;
	}
	/**
	 * get user name
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public String getUserName() {
		Context ctx;
		UserInfo user = null;
		try {
			ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();

		} catch (EntityException e) {
			e.printStackTrace();
		}
		if (user != null)
			return user.getCnName();
		else
			return null;
	}
}

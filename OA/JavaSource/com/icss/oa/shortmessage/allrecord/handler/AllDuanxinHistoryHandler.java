/*
 * Created on 2004-5-18
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.allrecord.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.shortmessage.allrecord.dao.DXHistoryOrgPersonSearchDAO;
import com.icss.oa.shortmessage.allrecord.dao.DuanxinHistoryDAO;
import com.icss.oa.shortmessage.allrecord.vo.DXHistoryOrgPersonVO;
import com.icss.oa.shortmessage.allrecord.vo.DuanxinHistoryVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AllDuanxinHistoryHandler {
	private Connection conn;
	
	public AllDuanxinHistoryHandler(Connection conn) {
	       this.conn = conn;
	}
	
//	get a list that contains the information of the sent shortmessage
	public List getByOrgId(String id) {
		DAOFactory factory = new DAOFactory(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("DUANXIN_HISTORY.SH_ID,DUANXIN_HISTORY.SENDER_ID,DUANXIN_HISTORY.SH_CONTENT,DUANXIN_HISTORY.SH_DATE,DUANXIN_HISTORY.DEP_ID,DUANXIN_HISTORY.RECEIVER_ID,DUANXIN_HISTORY.SENDER_CNNAME,SYS_ORG.ORGUUID,SYS_ORG.CNNAME,SYS_PERSON.PERSONUUID,SYS_PERSON.MOBILE ");
		sql.append("FROM ");
		sql.append("DUANXIN_HISTORY,SYS_ORG,SYS_PERSON ");
		sql.append("WHERE ");
		sql.append("DUANXIN_HISTORY.DEP_ID=SYS_ORG.ORGUUID AND ");
		sql.append("DUANXIN_HISTORY.RECEIVER_ID=SYS_PERSON.PERSONUUID ");
		sql.append("AND ");
		sql.append("DUANXIN_HISTORY.DEP_ID = '"+id+"' ");
		System.out.println("ccccccccccccccccccccc"+sql.toString());
		DXHistoryOrgPersonSearchDAO smdao=new DXHistoryOrgPersonSearchDAO();
		smdao.setSearchDaoSql(sql.toString());
		factory.setDAO(smdao);
		DXHistoryOrgPersonVO smVO=new DXHistoryOrgPersonVO();
		List list=new ArrayList();
		try {
			list=(List)factory.find(new DXHistoryOrgPersonVO());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} 
	
	
	/**
	 * 删除指定的行
     */
	public void deletesmRecord(Integer smId) throws AllDuanxinHistoryHandlerException {

		DuanxinHistoryDAO smDao = new DuanxinHistoryDAO();
		smDao.setConnection(conn);
		smDao.setShId(smId);

		try {
			smDao.delete();
		} catch (Exception e) {
			throw new AllDuanxinHistoryHandlerException(e);
		}
	}
	
	 //添加新的短信记录
	public void addSMRecord(DuanxinHistoryVO sendRecordVO)
	throws AllDuanxinHistoryHandlerException{

		DuanxinHistoryDAO SMrecorddao = new DuanxinHistoryDAO();
		SMrecorddao.setConnection(conn);
		SMrecorddao.setValueObject(sendRecordVO);
	 try {
		 SMrecorddao.create();
	 } catch (Exception e) {
		throw new AllDuanxinHistoryHandlerException(e);
	 }
  }
	
	 //添加一组新的短信记录
	//注意此方法只是针对list中的vo为DuanxinHistoryVO这种特定情况的
	public void addSMRecordByList(List list)
	throws AllDuanxinHistoryHandlerException{

		DuanxinHistoryDAO SMrecorddao = new DuanxinHistoryDAO();
		SMrecorddao.setConnection(conn);
		DuanxinHistoryVO vo=null;
		Iterator iter=list.iterator();
		while(iter.hasNext()){
			vo=(DuanxinHistoryVO)iter.next();
			SMrecorddao.setValueObject(vo);
		}
		
	 try {
		 SMrecorddao.create();
	 } catch (Exception e) {
		throw new AllDuanxinHistoryHandlerException(e);
	 }
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

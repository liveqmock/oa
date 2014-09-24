/*
 * Created on 2004-3-31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.journal.handler;

import java.sql.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.netoffice.journal.dao.OfficeJournalDAO;

import com.icss.oa.netoffice.journal.vo.OfficeJournalVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
//import com.icss.oa.netoffice.setNetoffice.handler.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class JournalHandler {
	
	private Connection conn;

	public JournalHandler(Connection conn) {
      this.conn = conn;
	}

	/**
	 * 得到所有的日记列表（不包含大字段）
	 * @return
	 * @throws RjHandlerException
	 */
	public List getJournalList() throws JournalHandlerException {
		List list = new ArrayList();
		//int k=0;
		DAOFactory factory = new DAOFactory(conn);
		OfficeJournalDAO journalDao2 = new OfficeJournalDAO();
		journalDao2.addOrderBy("rjDate",true);
		factory.setDAO(journalDao2);
		try {
			list = factory.find(new OfficeJournalVO());
		} catch (Exception e) {
			throw new JournalHandlerException(e);
		}
		return list;
	}
	/**
	 * 根据uuid得到所有的日记列表（不包含大字段）
	 * @return
	 * @throws RjHandlerException
	 */
	public List getJournalList(String uuid) throws JournalHandlerException {
		List list = new ArrayList();
		//int k=0;
		DAOFactory factory = new DAOFactory(conn);
		OfficeJournalDAO journalDao2 = new OfficeJournalDAO();
		journalDao2.setRjOwner(uuid);
		journalDao2.addOrderBy("rjDate",true);
		factory.setDAO(journalDao2);
		try {
			list = factory.find(new OfficeJournalVO());
		} catch (Exception e) {
			throw new JournalHandlerException(e);
		}
		return list;
	}
	
	/**
	 * 通过日记id得到日记（包含大字段）
	 * @return
	 * @throws RjHandlerException
	 */
	
	public OfficeJournalVO getById(Integer id) {
		DAOFactory factory = new DAOFactory(conn);
		OfficeJournalDAO jDao = new OfficeJournalDAO();
		//fileDao.setConnection(conn);
		jDao.setRjId(id);
		factory.setDAO(jDao);
		OfficeJournalVO jVO=null;
		try {
			jVO=(OfficeJournalVO)factory.findByPrimaryKey(new OfficeJournalVO());
			System.out.println(jVO.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jVO;
	}  
	
	/**
	 * 删除指定的日记
	 * @param tuuid
	 * @throws ServiceTypeMaintenanceHandlerException
	 */
	public void deleteJournal(Integer juuid) throws JournalHandlerException {

		OfficeJournalDAO journalDao = new OfficeJournalDAO();
		journalDao.setConnection(conn);
		journalDao.setRjId(juuid);

		try {
			journalDao.delete();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw new JournalHandlerException(e);
		}
	}
	
//	添加新的日记
	public void addJournal(OfficeJournalVO jVO)
	throws JournalHandlerException{

		OfficeJournalDAO jDao = new OfficeJournalDAO();
		jDao.setConnection(conn);
		jDao.setValueObject(jVO);
	try {
		jDao.create();
	} catch (Exception e) {
		throw new JournalHandlerException(e);
	}
}
	
	/**
	 * 根据指定的vo进行更新，其中新vo中的id由是当前行日记的id
    */
	public void updateJournal(OfficeJournalVO newJVO)
		throws JournalHandlerException {

		OfficeJournalDAO jDao = new OfficeJournalDAO();
		jDao.setConnection(conn);
		jDao.setValueObject(newJVO);
		try {
			jDao.update(true);
		} catch (Exception e) {
			System.err.println("==================================="+e.getMessage());
			throw new JournalHandlerException(e);
		}
	}
	
	/**
	 * 将字符串转换为long
	 * @param time
	 * @return long
	 */
	public static long getLongByTime(String time) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		java.util.Date date = formatter.parse(time);
		if (date == null)
			throw new Exception("the date/time string can not parse");
		return date.getTime();
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
	
	public List getListInTimeSegment(Long time1)throws JournalHandlerException{
		List mlist = new ArrayList();
		OfficeJournalDAO jdao=new OfficeJournalDAO();
		
		Context ctx;
		UserInfo user = null;
		try {
			ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		String loginname = user.getPersonUuid();
		jdao.setWhereClause("RJ_DATE = "+time1+" AND RJ_OWNER = '"+loginname+"'");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(jdao);
		try {
		 mlist = factory.find(new OfficeJournalVO());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new JournalHandlerException(e);
		}
		return mlist;
		
		
	
	}
	 
	/**
	 * 把long型时间转化为"yyyy.MM.dd ' 'HH:mm  "得形式
	 * @param time
	 * @return
	 */
	  public static String getTime(long time){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd ' 'HH:mm  ");
			return formatter.format(new Date(time));
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

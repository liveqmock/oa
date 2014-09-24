/*
 * Created on 2004-4-14
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.setnetoffice.handler;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.config.TypeConfig;
import com.icss.oa.netoffice.setnetoffice.dao.OfficeSetnetofficeDAO;
import com.icss.oa.netoffice.setnetoffice.vo.OfficeSetnetofficeVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SetNetofficeHandler {
	private Connection conn;
	
	public SetNetofficeHandler(Connection conn){
		
	  
		this.conn=conn;
	}
	
	/**
	 * 得到所有设置参数类型的列表（不包含大字段）
	 * @return
	 * @throws RjHandlerException
	 */
	public List getSetofficeList() throws SetNetofficeHandlerException {
		List list = new ArrayList();
		//int k=0;
		DAOFactory factory = new DAOFactory(conn);
		OfficeSetnetofficeDAO dao = new OfficeSetnetofficeDAO();
		
		dao.addOrderBy("setType",true);
		factory.setDAO(dao);
		try {
			list = factory.find(new OfficeSetnetofficeVO());
		} catch (Exception e) {
			throw new SetNetofficeHandlerException(e);
		}
		return list;
	}
	
	/**
	 * 得到所有设置参数类型的列表（不包含大字段）
	 * @return
	 * @throws RjHandlerException
	 */
	public List getSetofficeListByType(String type) throws SetNetofficeHandlerException {
		List list = new ArrayList();
		//int k=0;
		DAOFactory factory = new DAOFactory(conn);
		OfficeSetnetofficeDAO dao = new OfficeSetnetofficeDAO();
		
		dao.addOrderBy("setType",true);
		factory.setDAO(dao);
		try {
			list = factory.find(new OfficeSetnetofficeVO());
		} catch (Exception e) {
			throw new SetNetofficeHandlerException(e);
		}
		return list;
	}
	/**
  	 * 根据指定的vo进行更新，其中新vo中的id由是当前行memo的id
      */
  	public void setNetOffice(OfficeSetnetofficeVO scheduleVO)
  		throws SetNetofficeHandlerException {

  		OfficeSetnetofficeDAO setDao = new OfficeSetnetofficeDAO();
  		setDao.setConnection(conn);
  		setDao.setValueObject(scheduleVO);
  		try {
  			
  			setDao.update(true);
  		} catch (Exception e) {
  			System.err.println("==================================="+e.getMessage());
  			throw new SetNetofficeHandlerException(e);
  		}
  	}
  	
  	/**
  	 * 根据类型取出设置的月份
  	 * @author Administrator
  	 *
  	 * To change the template for this generated type comment go to
  	 * Window - Preferences - Java - Code Generation - Code and Comments
  	 */
  	public Integer getMonthsByType(int type)
		throws SetNetofficeHandlerException{
  		List list = new ArrayList();
  		Integer months=null;
		//int k=0;
		DAOFactory factory = new DAOFactory(conn);
		OfficeSetnetofficeDAO dao = new OfficeSetnetofficeDAO();
		dao.setSetType(new Integer(type));
		factory.setDAO(dao);
		try {
			list = factory.find(new OfficeSetnetofficeVO());
		    
		     Iterator it=null;
		     if(!list.isEmpty()){
		    	it=list.iterator();	
		    	OfficeSetnetofficeVO vo=null;
		    	if(it.hasNext()){
					vo=(OfficeSetnetofficeVO)it.next();
		    	}
		    		
		    	
		    	months=vo.getMonthsReserve();
		     }
		} catch (Exception e) {
			throw new SetNetofficeHandlerException(e);
		}
		return months;
  		
  		
  	}
  	
  	//取出setnetoffice中设置的schedule默认提醒的时间
  	public Integer getDefaultHours()
	throws SetNetofficeHandlerException{
		List list = new ArrayList();
		Integer hours=null;
	//int k=0;
	DAOFactory factory = new DAOFactory(conn);
	OfficeSetnetofficeDAO dao = new OfficeSetnetofficeDAO();
	dao.setSetType(new Integer(TypeConfig.SCHEDULE_TYPE));
	factory.setDAO(dao);
	try {
		list = factory.find(new OfficeSetnetofficeVO());
	    
	     Iterator it=null;
	     if(!list.isEmpty()){
	    	it=list.iterator();	
	    	OfficeSetnetofficeVO vo=null;
	    	if(it.hasNext())
	    		vo=(OfficeSetnetofficeVO)it.next();
	    	
	    	//months=vo.getMonthsReserve();
	    	hours=vo.getHoursRemind();
	     }
	} catch (Exception e) {
		throw new SetNetofficeHandlerException(e);
	}
	return hours;
		
		
	}
  	
  	public String getUseruuid() {

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

/*
 * Created on 2004-4-21
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.schedule.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.netoffice.schedule.dao.OfficeScheduleDAO;
import com.icss.oa.netoffice.schedule.vo.OfficeScheduleVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
    public class ScheduleHandler {
    	
    	private Connection conn;

    	public ScheduleHandler(Connection conn) {
          this.conn = conn;
    	}
    	
    	/**
    	 * 通过事件日期得到当天的日程列表
    	 * @return
    	 * @throws RjHandlerException
    	 */
    	
    	public List getScheduleListBydate(Long date,String personid) throws ScheduleHandlerException {
    		List list = new ArrayList();
    		//int k=0;
    		DAOFactory factory = new DAOFactory(conn);
    		OfficeScheduleDAO scheduleDao = new OfficeScheduleDAO();
    		scheduleDao.setPersonid(personid);
    		scheduleDao.setOsDate(date);
    		scheduleDao.addOrderBy("osBegin",true);
    		factory.setDAO(scheduleDao);
    		try {
    			list = factory.find(new OfficeScheduleVO());
    		} catch (Exception e) {
    			throw new ScheduleHandlerException(e);
    		}
    		
    		return list;
    	}
    	
        // 	添加新的日程安排事件
    	public void addSchedule(OfficeScheduleVO sVO)
    	throws ScheduleHandlerException{

    		OfficeScheduleDAO sDao = new OfficeScheduleDAO();
    		sDao.setConnection(conn);
    		sDao.setValueObject(sVO);
    	try {
    		sDao.create();
    	} catch (Exception e) {
    		throw new ScheduleHandlerException(e);
    	}
    }
    	
    	/**
    	 * 删除指定的日记
    	 * @param tuuid
    	 * @throws ServiceTypeMaintenanceHandlerException
    	 */
    	public void deleteSchedule(Integer sid) throws ScheduleHandlerException {

    		OfficeScheduleDAO scheduleDao = new OfficeScheduleDAO();
    		scheduleDao.setConnection(conn);
    		scheduleDao.setOsId(sid);

    		try {
    			scheduleDao.delete();
    		} catch (Exception e) {
    			//System.out.print(e.getMessage());
    			throw new ScheduleHandlerException(e);
    		}
    	}
    	
    	/**
    	 * 通过日程事件id得到事件
    	 
    	 */
    	
    	public OfficeScheduleVO getById(Integer id) {
    		DAOFactory factory = new DAOFactory(conn);
    		OfficeScheduleDAO sDao = new OfficeScheduleDAO();
    		sDao.setOsId(id);
    		factory.setDAO(sDao);
    		OfficeScheduleVO sVO=null;
    		try {
    			sVO=(OfficeScheduleVO)factory.findByPrimaryKey(new OfficeScheduleVO());
    			System.out.println(sVO.toString());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		return sVO;
    	} 
    	
    	
    	/**
    	 * 根据指定的vo进行更新，其中新vo中的id是当前行日程事件的id
        */
    	public void updateSchedule(OfficeScheduleVO newJVO)
    		throws ScheduleHandlerException {

    		OfficeScheduleDAO sDao = new OfficeScheduleDAO();
    		sDao.setConnection(conn);
    		sDao.setValueObject(newJVO);
    		try {
    			sDao.update(true);
    		} catch (Exception e) {
    			System.err.println("==================================="+e.getMessage());
    			throw new ScheduleHandlerException(e);
    		}
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
    			return user.getUserID();
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

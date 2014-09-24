/*
 * Created on 2004-12-31
 *
 * 
 */
                                                                                                                       
package com.icss.oa.workmeeting.handler;     

import java.sql.Connection;  
import java.util.ArrayList;
import java.util.Iterator;     
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;

import com.icss.oa.workmeeting.dao.*;  
import com.icss.oa.workmeeting.vo.*;

/**
 * @author sunchuanting
 *
 * 
 */

public class Workinghandler{
	
	private Connection conn;
	
	public Workinghandler(Connection connection) {
		this.conn = connection;
	}
	
	//取得全部的工作计划 
	public List getWorkPlanList() throws HandlerException {
		
		DAOFactory factory = new DAOFactory(conn);
		GongzuowjDAO dao = new GongzuowjDAO();
		factory.setDAO(dao);
		dao.setCflag(new Integer(0));
		dao.addOrderBy("seqno",true);
		try {
			List list = factory.find(new GongzuowjVO());
			return list;
		}
		catch (DAOException e) {
			System.out.println(e.toString());
			throw new HandlerException(e);
		}
	}
	
	//取得全部的工作计划反馈
	public List getWorkAllPlanRequestList() throws HandlerException {
		
		DAOFactory factory = new DAOFactory(conn);
		GongzuowjDAO dao = new GongzuowjDAO();
		factory.setDAO(dao);
		dao.setCflag(new Integer(1));
		dao.addOrderBy("seqno",true);
		try {
			List list = factory.find(new GongzuowjVO());
			return list;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
    //本局级单位下的工作计划反馈
	public List getWorkPlanRequestList(String orguuid) throws HandlerException {
		
		List list_return = new ArrayList();
		DAOFactory factory = new DAOFactory(conn);
		GongzuowjDAO dao = new GongzuowjDAO();
		factory.setDAO(dao);
		dao.setCflag(new Integer(1));
		dao.addOrderBy("seqno",true);
		try {
			List list = factory.find(new GongzuowjVO());
			Iterator it = null;
			if(list!=null) it = list.iterator();
			while(it.hasNext()){
				GongzuowjVO vo = (GongzuowjVO)it.next();
				String text_in_JU_uuid = this.getPersonJU_UUID(vo.getIssueName());
				if(text_in_JU_uuid.equals(orguuid)) list_return.add(vo);
			}
			
			return list_return;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
    //某工作计划的阅读情况
	public List getWorkPlanReadList(Integer wr_id) throws HandlerException {
		
		DAOFactory factory = new DAOFactory(conn);
		GzwjviewRecDAO dao = new GzwjviewRecDAO();
		factory.setDAO(dao);
		dao.setWrNo(wr_id);
		try {
			List list = factory.find(new GzwjviewRecVO());
			return list;
		}
		catch (DAOException e) {
			System.out.println(e.toString());
			throw new HandlerException(e);
		}
	}
	
	 //得到某人是否有权限阅读全部的人员列表
	public boolean getReadPower(String userid) throws HandlerException {
		
		DAOFactory factory = new DAOFactory(conn);
		ReadpowerDAO dao = new ReadpowerDAO();
		factory.setDAO(dao);
		boolean flag = false;
		
		try {
			List list = factory.find(new ReadpowerVO());
			Iterator it = null;
			if(list!=null) it = list.iterator();
			while(it.hasNext()){
				ReadpowerVO vo = (ReadpowerVO)it.next();
				if(vo.getUserid().equals(userid)) flag =true;
				}
			return flag;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
	
	
    //得到某工作计划的附件
	public Attachment1VO getWorkPlanAttach(Integer wr_id) throws HandlerException {
		
		Attachment1VO vo = null;
		
		DAOFactory factory = new DAOFactory(conn);
		Attachment1DAO dao = new Attachment1DAO();
		factory.setDAO(dao);
		dao.setGzwjid(wr_id);
		try {
			List list = factory.find(new Attachment1VO());
			
			Iterator it =null;
			if(list!=null) it = list.iterator();
			
			if(it!=null){
			    while(it.hasNext()){
			    	vo = (Attachment1VO)it.next();
			    	}	
			}
			
			return vo;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
	//根据登陆名字得到该人的局级单位的UUID
	public String getPersonJU_UUID(String userid) throws HandlerException
	{
		StringBuffer sql = new StringBuffer();
		List list = null;
		Iterator it = null;
		String  JU_UUID = null;
		Integer orglevel = new Integer(3);   
		
		sql.append("select ");
		sql.append(
		"SYS_PERSON.PERSONUUID,SYS_ORG.ORGUUID,SYS_ORG.CNNAME,SYS_ORG.ORGLEVEL ");
		sql.append("from ");
		sql.append("SYS_PERSON,SYS_ORG,SYS_ORGPERSON ");
		sql.append("where ");
		sql.append("SYS_PERSON.PERSONUUID = SYS_ORGPERSON.PERSONUUID and ");
		sql.append("SYS_ORG.ORGUUID= SYS_ORGPERSON.ORGUUID and ");
		sql.append("SYS_ORGPERSON.ISBELONG= '1' and ");
		sql.append("SYS_PERSON.USERID= '"+userid+"' ");
		
		DAOFactory factory = new DAOFactory(conn);  
		SysOrgSysOrgpersonSysPersonSearchDAO dao = new SysOrgSysOrgpersonSysPersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		
		SysOrgSysOrgpersonSysPersonVO vo = new SysOrgSysOrgpersonSysPersonVO();
		
		try
		{
			list = factory.find(new SysOrgSysOrgpersonSysPersonVO());
			if(list!=null){
				it = list.iterator();
				}
			if(it!=null){
			    while(it.hasNext()){
			        vo = (SysOrgSysOrgpersonSysPersonVO)it.next();
			        orglevel = vo.getOrglevel();
			    }	
			}
			
			if(orglevel.intValue()==3)
			{
				JU_UUID = vo.getOrguuid();
			}
			
			if(orglevel.intValue()==4)
			{
				JU_UUID = this.getParentUUID(vo.getOrguuid());
			}
			
			//关于这个问题要加以调整！
			if(orglevel.intValue()==5||orglevel.intValue()==0||orglevel.intValue()==1||orglevel.intValue()==2)
			{
				JU_UUID = null;
			}
			return JU_UUID;
			
		}
		catch (Exception e)
		{   
			e.printStackTrace();
			throw new HandlerException(e);
			
		}
        
	}
	
//	根据登陆名字得到该人的局级单位的中文名
	public String getPersonJU_Name(String userid) throws HandlerException
	{
		StringBuffer sql = new StringBuffer();
		List list = null;
		Iterator it = null;
		String  JU_UUID = null;
		Integer orglevel = new Integer(3);   
		
		sql.append("select ");
		sql.append(
		"SYS_PERSON.PERSONUUID,SYS_ORG.ORGUUID,SYS_ORG.CNNAME,SYS_ORG.ORGLEVEL ");
		sql.append("from ");
		sql.append("SYS_PERSON,SYS_ORG,SYS_ORGPERSON ");
		sql.append("where ");
		sql.append("SYS_PERSON.PERSONUUID = SYS_ORGPERSON.PERSONUUID and ");
		sql.append("SYS_ORG.ORGUUID= SYS_ORGPERSON.ORGUUID and ");
		sql.append("SYS_ORGPERSON.ISBELONG= '1' and ");
		sql.append("SYS_PERSON.USERID= '"+userid+"' ");
		
		DAOFactory factory = new DAOFactory(conn);  
		SysOrgSysOrgpersonSysPersonSearchDAO dao = new SysOrgSysOrgpersonSysPersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		
		SysOrgSysOrgpersonSysPersonVO vo = new SysOrgSysOrgpersonSysPersonVO();
		
		try
		{
			list = factory.find(new SysOrgSysOrgpersonSysPersonVO());
			if(list!=null){
				it = list.iterator();
				}
			if(it!=null){
			    while(it.hasNext()){
			        vo = (SysOrgSysOrgpersonSysPersonVO)it.next();
			        orglevel = vo.getOrglevel();
			    }	
			}
			
			if(orglevel.intValue()==3)
			{
				JU_UUID = vo.getCnname();
			}
			
			if(orglevel.intValue()==4)
			{
				JU_UUID = this.getOrg_Name(vo.getOrguuid());
			}
			
			//关于这个问题要加以调整！
			if(orglevel.intValue()==5||orglevel.intValue()==0||orglevel.intValue()==1||orglevel.intValue()==2)
			{
				JU_UUID = null;
			}
			return JU_UUID;
			
		}
		catch (Exception e)
		{   
			e.printStackTrace();
			throw new HandlerException(e);
			
		}
        
	}
	
	//根据组织的uuid得到父组织的UUID
	public String getParentUUID(String uuid) throws HandlerException {
		
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setOrguuid(uuid);
		
		String orguuid =null;
		
		try {
			List list = factory.find(new SysOrgVO());
			Iterator it = list.iterator();
			if(it!=null){
				while(it.hasNext()){
					SysOrgVO vo = (SysOrgVO)it.next();
					orguuid = vo.getParentorguuid();
					}
			}
			
			return orguuid;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
    //根据组织的uuid得到组织的名称
	public String getOrg_Name(String Orguuid) throws HandlerException {
		
		//Connection conn = null;
		
		try {
		
		//conn=DBConnectionLocator.getInstance().getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setOrguuid(Orguuid);
		
		String orgname =null;
		
			List list = factory.find(new SysOrgVO());
			Iterator it = list.iterator();
			if(it!=null){
				while(it.hasNext()){
					SysOrgVO vo = (SysOrgVO)it.next();
					orgname = vo.getCnname();
					}
			}
			
			return orgname;
			
	    } catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public Attachment1DAO newInfoMsg(Attachment1VO vo)throws DAOException{
		Attachment1DAO dao = new Attachment1DAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		dao.create();
		return dao;
	}


}

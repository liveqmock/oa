package com.icss.oa.statsite.handler;

import java.util.*;
import java.sql.*;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.dao.DAOException;  
import com.icss.oa.commsite.handler.HandlerException;
import com.icss.oa.statsite.vo.*;
import com.icss.oa.statsite.dao.*;

public class ipManagerHandler
{

	private Connection conn;

	public ipManagerHandler(Connection conn)
	{
		this.conn = conn;
	}
	
	public void add(StatSiteIpcontentVO vo) throws HandlerException {
		StatSiteIpcontentDAO dao = new StatSiteIpcontentDAO();
		dao.setConnection(conn);
		dao.setValueObject(vo);
		try {
			dao.create();
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
    }
	
	public List getIPList() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpcontentDAO dao = new StatSiteIpcontentDAO();
		dao.addOrderBy("ID",true);
		factory.setDAO(dao);
		try {
			List list = factory.find(new StatSiteIpcontentVO());
			return list;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
	public List getIPList1() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpcontentListDAO dao = new StatSiteIpcontentListDAO();
		dao.addOrderBy("ID",true);
		factory.setDAO(dao);
		try {
			List list = factory.find(new StatSiteIpcontentListVO());
			return list;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
	public List getIPList1(Integer id) throws HandlerException{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpcontentDAO dao = new StatSiteIpcontentDAO();
		dao.setId(id);
		factory.setDAO(dao);
		try {
			List list= factory.find(new StatSiteIpcontentVO());
			return list;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
	public List getIPList2(String address) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpcontentDAO dao = new StatSiteIpcontentDAO();
		dao.setIpcontent(address);
		factory.setDAO(dao);
		try {
			List list= factory.find(new StatSiteIpcontentVO());
			return list;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public void update(StatSiteIpcontentVO vo){
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpcontentDAO dao = new StatSiteIpcontentDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setId (vo.getId());
		try{
		    dao = (StatSiteIpcontentDAO)factory.findByPrimaryKey();
		    dao.setValueObject(vo);
		    dao.update();
		}
		catch(Exception e){
		}
	 }
	public void delete(Integer Id){
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpcontentDAO dao = new StatSiteIpcontentDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setId (Id);
		try{
			dao.delete();
		}
		catch(Exception e){
		}
	}
	public String ToStringByIP(String ipdot){
		StringTokenizer st_IP = new StringTokenizer(ipdot,".");
		StringBuffer sb_IP = new StringBuffer();
		while(st_IP.hasMoreTokens()){
			
			String sb1 =st_IP.nextToken();
			int sbLength = sb1.length();
			switch (sbLength){
			  case 1:
			  	sb_IP.append("00");
			  	sb_IP.append(sb1);
			      	break;
			  case 2:
			  	sb_IP.append("0");
			  	sb_IP.append(sb1);
		      		break;  
			  case 3:
	      	    sb_IP.append(sb1);
	      			break;  
			}
		}
		return sb_IP.toString();
	}
	
	public String IsAdress(String ip) throws Throwable{
		String Adress ="Î´ÖªµØÖ·";
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpcontentDAO dao = new StatSiteIpcontentDAO();
		factory.setDAO(dao);
		dao.setWhereClause(" STARTIP<="+this.ToStringByIP(ip)+" AND "+" ENDIP>="+this.ToStringByIP(ip));
		try {
			List list = factory.find(new StatSiteIpcontentVO());
			if(list!=null){
			Iterator it = list.iterator();
			
			while(it.hasNext()){
				
				StatSiteIpcontentVO vo = (StatSiteIpcontentVO)it.next();
				Adress = vo.getIpcontent();
			}
			}
			return Adress;			
		}
		catch (DAOException e) {
			throw new DAOException(e);
		}
		}
	
	public Integer IsAdress1(String ip) throws Throwable{
		Integer id = null ;
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpcontentDAO dao = new StatSiteIpcontentDAO();
		factory.setDAO(dao);
		dao.setWhereClause("STARTIP<="+ip+" AND "+"ENDIP>="+ip);
		try {
			List list = factory.find(new StatSiteIpcontentVO());
			Iterator it = list.iterator();
			if(it!=null){
				while(it.hasNext()){
				StatSiteIpcontentVO vo = (StatSiteIpcontentVO)it.next();
				id = vo.getId();}
			}
			return id;			
		}
		catch (DAOException e) {
			throw new DAOException(e); 
		}
		}
}
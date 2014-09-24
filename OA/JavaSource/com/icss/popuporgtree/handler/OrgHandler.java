/*
 * �������� 2004-4-7
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.popuporgtree.handler;

import java.sql.Connection;
import java.util.*;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.popuporgtree.dao.SysOrgDAO;
import com.icss.popuporgtree.vo.SysOrgTreeVO;
import com.icss.popuporgtree.vo.SysOrgVO;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class OrgHandler {
	private Connection conn;
	public OrgHandler(Connection conn) {
		this.conn = conn;
	}
	public List getOrgTreeList(String orgId) throws HandlerException{
		List orgList=this.getOrgList(orgId);
		List orgTreeList=null;
		if(orgList!=null){
			orgTreeList=new ArrayList();
			Iterator it=orgList.iterator();
			while(it.hasNext()){
				SysOrgTreeVO vo=new SysOrgTreeVO();
				SysOrgVO vo1=(SysOrgVO)it.next();
				vo.setVO(vo1);
				vo.setHasChild(this.hasChildOrg(vo1.getOrguuid()));
				orgTreeList.add(vo);
			}
		}
		return orgTreeList;
	}
	public List getOrgList(String orgId) throws HandlerException{
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setWhereClause("DELTAG='0' AND PARENTORGUUID='"+orgId+"'");
		List orgList=null;
		try{
			dao.addOrderBy("serialindex",true);
			orgList=factory.find(new SysOrgVO());
		}
		catch(DAOException e){
			throw new HandlerException(e);
		}
		return orgList;
	}
	public boolean hasChildOrg(String orgId){
		try{
			List list=this.getOrgList(orgId);
			if(list!=null&&list.size()>0){
				return true;
			}
			else return false;
		}
		catch(Exception e){
			return false;
		}
	}
	public SysOrgVO getTopOrg() throws HandlerException{
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setWhereClause("DELTAG='0' AND ORGLEVEL='0' ");
		List orgList=null;
		try{
			orgList=factory.find(new SysOrgVO());
			if(orgList!=null&&orgList.size()==1){
				return (SysOrgVO)orgList.get(0);
			}
			else{
				throw new DAOException();
			}
		}
		catch(DAOException e){
			throw new HandlerException(e);
		}
	}
}

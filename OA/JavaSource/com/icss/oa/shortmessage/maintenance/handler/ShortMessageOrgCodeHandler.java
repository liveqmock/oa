/*
 * Created on 2004-5-8
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
  package com.icss.oa.shortmessage.maintenance.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.shortmessage.maintenance.dao.DuanxinShortmappingDAO;
import com.icss.oa.shortmessage.maintenance.dao.DuanxinShortmappingSysOrgSearchDAO;
import com.icss.oa.shortmessage.maintenance.vo.DuanxinShortmappingSysOrgVO;
import com.icss.oa.shortmessage.maintenance.vo.DuanxinShortmappingVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
  public class ShortMessageOrgCodeHandler{
  	
  	private Connection conn;

	public ShortMessageOrgCodeHandler(Connection conn) {
      this.conn = conn;
	}
	
	/**
	 * �õ����е���֯���ƺ����Ӧ�Ķ��ź��б����������ֶΣ�
	 * @return
	 * 
	 */
	public List getOrgShortMessageCodeList() throws ShortMessageOrgCodeHandlerException {
		List list = new ArrayList();
		//int k=0;
		DAOFactory factory = new DAOFactory(conn);
		DuanxinShortmappingSysOrgSearchDAO orgSMCode = new DuanxinShortmappingSysOrgSearchDAO();
		orgSMCode.addOrderBy("depid",true);
		factory.setDAO(orgSMCode);
		try {
			list = factory.find(new DuanxinShortmappingSysOrgVO());
		} catch (Exception e) {
			throw new ShortMessageOrgCodeHandlerException(e);
		}
		return list;
	}
	
	/**
	 * �õ����е���֯id�����Ӧ�Ķ��ź��б����������ֶΣ�(��seachdao��ͬ)
	 * @return
	 * 
	 */
	public List getOrgIdSMCodeList() throws ShortMessageOrgCodeHandlerException {
		List list = new ArrayList();
		//int k=0;
		DAOFactory factory = new DAOFactory(conn);
		DuanxinShortmappingDAO orgSMCode = new DuanxinShortmappingDAO();
		factory.setDAO(orgSMCode);
		try {
			list = factory.find(new DuanxinShortmappingVO());
		} catch (Exception e) {
			throw new ShortMessageOrgCodeHandlerException(e);
		}
		return list;
	}
	
  //������µ���֯���ź�
	public void addSMCode(DuanxinShortmappingVO smVO)
	throws ShortMessageOrgCodeHandlerException{

		DuanxinShortmappingDAO orgSMdao = new DuanxinShortmappingDAO();
		orgSMdao.setConnection(conn);
		orgSMdao.setValueObject(smVO);
	try {
		orgSMdao.create();
	} catch (Exception e) {
		throw new ShortMessageOrgCodeHandlerException(e);
	}
}
	
	/**
	 * ɾ��ָ������
	 * 
	 */
	public void deleteSMcode(Integer smid) throws ShortMessageOrgCodeHandlerException {

		DuanxinShortmappingDAO orgSMdao = new DuanxinShortmappingDAO();
		orgSMdao.setConnection(conn);
		orgSMdao.setSmId(smid);

		try {
			orgSMdao.delete();
		} catch (Exception e) {
			throw new ShortMessageOrgCodeHandlerException(e);
		}
	}
	
	/**
	 * ����ָ����vo���и��£�������vo�е�id���ǵ�ǰ����֯��id
    */
	public void updateSMcode(DuanxinShortmappingVO smVO)
		throws ShortMessageOrgCodeHandlerException {

		DuanxinShortmappingDAO orgSMdao = new DuanxinShortmappingDAO();
		orgSMdao.setConnection(conn);
		orgSMdao.setValueObject(smVO);
		
		try {
			orgSMdao.update(true);
		} catch (Exception e) {
			//System.err.println("==================================="+e.getMessage());
			throw new ShortMessageOrgCodeHandlerException(e);
		}
	}
	
	
	
 
  }

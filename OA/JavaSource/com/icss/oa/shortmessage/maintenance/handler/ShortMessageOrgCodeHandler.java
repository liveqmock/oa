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
	 * 得到所有的组织名称和其对应的短信号列表（不包含大字段）
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
	 * 得到所有的组织id和其对应的短信号列表（不包含大字段）(与seachdao不同)
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
	
  //分配给新的组织短信号
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
	 * 删除指定的行
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
	 * 根据指定的vo进行更新，其中新vo中的id由是当前行组织的id
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

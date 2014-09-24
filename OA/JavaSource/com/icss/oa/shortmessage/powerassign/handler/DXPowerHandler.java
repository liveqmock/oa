/*
 * Created on 2004-5-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.shortmessage.powerassign.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.vo.SysOrgpersonVO;
import com.icss.oa.shortmessage.powerassign.dao.DXShortaccessOrgPersonSearchDAO;
import com.icss.oa.shortmessage.powerassign.dao.DuanxinShortaccessDAO;
import com.icss.oa.shortmessage.powerassign.dao.SysOrgpersonDAO;
import com.icss.oa.shortmessage.powerassign.vo.DXShortaccessOrgPersonVO;
import com.icss.oa.shortmessage.powerassign.vo.DuanxinShortaccessVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DXPowerHandler {

	private Connection conn;
	public static String toSerchdaoId = null;

	public DXPowerHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 得到所有的searchDao取得的列表（不包含大字段）
	 * @return
	 * 
	 */
	public List getSMPowerList() throws DXPowerHandlerException {
		List list = new ArrayList();
		//int k=0;
		DAOFactory factory = new DAOFactory(conn);
		DuanxinShortaccessDAO smDao = new DuanxinShortaccessDAO();
		factory.setDAO(smDao);
		try {
			list = factory.find(new DuanxinShortaccessVO());
		} catch (Exception e) {
			throw new DXPowerHandlerException(e);
		}
		return list;
	}

	//get a list that contains the information of the person powered to the org
	public List getByOrgId(String id) {
		DAOFactory factory = new DAOFactory(conn);
		toSerchdaoId = id;
		DXShortaccessOrgPersonSearchDAO smDao =
			new DXShortaccessOrgPersonSearchDAO();
		factory.setDAO(smDao);
		DXShortaccessOrgPersonVO smVO = new DXShortaccessOrgPersonVO();
		List list = new ArrayList();
		try {
			list = (List) factory.find(new DXShortaccessOrgPersonVO());

		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("1111111111"+e.getMessage());
		}
		return list;
	}

	//get a vo by shortmessageId 
	public DuanxinShortaccessVO getVObyId(Integer saId) {
		DuanxinShortaccessVO smvo = null;
		try {

			DAOFactory factory = new DAOFactory(conn);
			DuanxinShortaccessDAO smDao = new DuanxinShortaccessDAO();
			smDao.setSaId(saId);
			factory.setDAO(smDao);
			smvo =
				(DuanxinShortaccessVO) factory.findByPrimaryKey(
					new DuanxinShortaccessVO());

		} catch (Exception e) {
			System.err.print(e.getMessage());
			e.printStackTrace();
		}
		return smvo;
	}

	/**
	 * 删除指定的行
	 *
	 */
	public void deletesmPower(Integer smId) throws DXPowerHandlerException {

		DuanxinShortaccessDAO smDao = new DuanxinShortaccessDAO();
		smDao.setConnection(conn);
		smDao.setSaId(smId);

		try {
			smDao.delete();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw new DXPowerHandlerException(e);
		}
	}

	//分配给新的组织短信号
	public void addPowerPerson(DuanxinShortaccessVO powerVO)
		throws DXPowerHandlerException {
		DuanxinShortaccessDAO powerdao = new DuanxinShortaccessDAO();
		powerdao.setConnection(conn);
		powerdao.setValueObject(powerVO);
		try {
			powerdao.create();
		} catch (Exception e) {
			throw new DXPowerHandlerException(e);
		}
	}

	//根据personuuid查找该人员所属单位的uuid
	public String getOrguuidBypersonuuid(String id) {
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonDAO opDao = new SysOrgpersonDAO();
		opDao.setPersonuuid(id);
		factory.setDAO(opDao);
		SysOrgpersonVO opVO = new SysOrgpersonVO();
		List list = new ArrayList();
		String orguuid = null;
		try {
			list = (List) factory.find(new SysOrgpersonVO());
			if (list.size() != 0) {
				Iterator iter = list.iterator();
				while (iter.hasNext()) {
					opVO = (SysOrgpersonVO) iter.next();
					if (opVO.getIsbelong().equals("1")) {
						orguuid = opVO.getOrguuid();
						break;
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("1111111111" + e.getMessage());
		}
		return orguuid;
	}

	//	根据personuuid查找该人员有权限发送短信的部门
	//Todo

}

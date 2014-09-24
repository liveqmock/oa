/*
 * 创建日期 2008-2-26
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.zbs.duty.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.zbs.duty.dao.TbZbsWorkinfoDAO;
import com.icss.oa.zbs.duty.dao.TbZbsWorkinfomainDAO;
import com.icss.oa.zbs.duty.dao.TbZbsWorkinfotypeDAO;
import com.icss.oa.zbs.duty.vo.TbZbsWorkinfoVO;
import com.icss.oa.zbs.duty.vo.TbZbsWorkinfomainVO;
import com.icss.oa.zbs.duty.vo.TbZbsWorkinfotypeVO;

/**
 * @author 王苏
 * 
 * 更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class ZbsWorkInfoHandler {

	private Connection conn;

	public ZbsWorkInfoHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * @param string
	 * @return
	 */
	public List getMainDutyListByClause(String string) {
		TbZbsWorkinfomainDAO dao = new TbZbsWorkinfomainDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("witCreatetime", false);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new TbZbsWorkinfomainVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param string
	 * @return
	 */
	public List getMainDutyTypeListByClause(String string) {
		TbZbsWorkinfotypeDAO dao = new TbZbsWorkinfotypeDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("witId", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new TbZbsWorkinfotypeVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @return
	 */
	public Integer newDutyType(String name, String desc) throws DAOException {
		TbZbsWorkinfotypeDAO dao = new TbZbsWorkinfotypeDAO();
		dao.setWitName(name);
		dao.setWitMemo(desc);
		dao.setConnection(conn);
		dao.create();
		Integer id = dao.getWitId();
		return id;
	}

	/**
	 * @param vo
	 * @return
	 */
	public Integer newDutyMainInfo(TbZbsWorkinfomainVO vo) throws DAOException {
		// TODO 自动生成方法存根
		TbZbsWorkinfomainDAO dao = new TbZbsWorkinfomainDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		dao.create();
		Integer id = dao.getWimId();
		return id;
	}

	/**
	 * @param infovo
	 * @return
	 * @throws SQLException
	 */
	public Integer newDutyContentInfo(TbZbsWorkinfoVO infovo)
			throws DAOException, SQLException {
		// String temp = new String(infovo.getWitContent());
		// boolean o = conn.getAutoCommit();
		// conn.setAutoCommit(false);
		//		
		TbZbsWorkinfoDAO dao = new TbZbsWorkinfoDAO();
		dao.setValueObject(infovo);
		dao.setConnection(conn);
		dao.create();

		// dao.setWitContent(temp);
		// dao.update(true);
		//		
		// conn.commit();
		// conn.setAutoCommit(o);
		//		
		Integer id = dao.getWiId();
		return id;
	}

	/**
	 * @param string
	 * @return
	 */
	public boolean deleteDutyInfoByMainId(String string) {
		try {
			TbZbsWorkinfoDAO dao = new TbZbsWorkinfoDAO();
			dao.setWimId(new Integer(string));
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);
			dao.setConnection(conn);
			factory.batchDelete();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param string
	 * @return
	 */
	public boolean deleteDutyMainById(String string) {
		try {
			TbZbsWorkinfomainDAO dao = new TbZbsWorkinfomainDAO();
			dao.setWimId(new Integer(string));
			dao.setConnection(conn);
			dao.delete();
			return true;
		} catch (DAOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param witid
	 * @return
	 */
	public boolean deleteDutyTypeById(String witid) {
		try {
			TbZbsWorkinfotypeDAO dao = new TbZbsWorkinfotypeDAO();
			dao.setWitId(new Integer(witid));
			dao.setConnection(conn);
			dao.delete();
			return true;
		} catch (DAOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param modifydutyid
	 * @param name
	 * @param desc
	 * @return
	 */
	public Integer updateDutyType(String modifydutyid, String name, String desc)
			throws DAOException {
		TbZbsWorkinfotypeDAO dao = new TbZbsWorkinfotypeDAO();
		dao.setWitId(new Integer(modifydutyid));
		dao.setWitName(name);
		dao.setWitMemo(desc);
		dao.setConnection(conn);
		dao.update();
		Integer id = dao.getWitId();
		return id;
	}

	/**
	 * @param wimid
	 * @return
	 */
	public TbZbsWorkinfomainVO getMainDutyById(String wimid)
			throws DAOException {
		TbZbsWorkinfomainDAO dao = new TbZbsWorkinfomainDAO();
		TbZbsWorkinfomainVO vo = new TbZbsWorkinfomainVO();
		dao.setWimId(new Integer(wimid));
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		vo = (TbZbsWorkinfomainVO) factory
				.findByPrimaryKey(new TbZbsWorkinfomainVO());
		return vo;
	}

	/**
	 * @param string
	 * @return
	 */
	public List getDutyContentInfoByClause(String string) throws DAOException {
		TbZbsWorkinfoDAO dao = new TbZbsWorkinfoDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("wiId", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		list = (List) factory.find(new TbZbsWorkinfoVO());
		return list;
	}

	/**
	 * @param vo
	 * @return
	 */
	public Integer editDutyMainInfo(TbZbsWorkinfomainVO vo) throws DAOException {
		TbZbsWorkinfomainDAO dao = new TbZbsWorkinfomainDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		dao.update();
		Integer id = dao.getWimId();
		return id;
	}

	/**
	 * @param infovo
	 * @return
	 * @throws SQLException
	 */
	public Integer updateDutyContentInfo(TbZbsWorkinfoVO infovo)
			throws DAOException, SQLException {
		// boolean o = conn.getAutoCommit();
		// conn.setAutoCommit(false);

		TbZbsWorkinfoDAO dao = new TbZbsWorkinfoDAO();
		dao.setValueObject(infovo);
		dao.setConnection(conn);
		dao.update(true);
		//		
		// conn.commit();
		// conn.setAutoCommit(o);
		Integer id = dao.getWiId();
		return id;
	}

	/**
	 * @param string
	 * @return
	 */
	public TbZbsWorkinfoVO getDutyInfobyId(String string) throws DAOException {
		TbZbsWorkinfoDAO dao = new TbZbsWorkinfoDAO();
		TbZbsWorkinfoVO vo = new TbZbsWorkinfoVO();
		dao.setWiId(new Integer(string));
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		vo = (TbZbsWorkinfoVO) factory.findByPrimaryKey(new TbZbsWorkinfoVO());
		return vo;
	}

	public TbZbsWorkinfomainVO getLastDuty() throws DAOException {
		// TODO Auto-generated method stub
		TbZbsWorkinfomainDAO dao = new TbZbsWorkinfomainDAO();
		TbZbsWorkinfomainVO vo = new TbZbsWorkinfomainVO();
		dao.addOrderBy("witDate", false);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new TbZbsWorkinfomainVO());

		if (!list.isEmpty()) {
			vo = (TbZbsWorkinfomainVO) list.get(0);
		}
		return vo;

	}

	public List getLastInfo(Integer wimId) throws DAOException {
		// TODO Auto-generated method stub
		TbZbsWorkinfoDAO dao = new TbZbsWorkinfoDAO();
		dao.setWimId(wimId);
		dao.addOrderBy("witId", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new TbZbsWorkinfoVO());
		return list;

	}

}

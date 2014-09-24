/*
 * 创建日期 2010-12-13
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.zbs.yjsduty.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.zbs.yjsduty.dao.TbYjsWorkinfoDAO;
import com.icss.oa.zbs.yjsduty.dao.TbYjsWorkinfomainDAO;
import com.icss.oa.zbs.yjsduty.dao.TbYjsWorkinfotypeDAO;
import com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfoVO;
import com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfomainVO;
import com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfotypeVO;

/**
 * @author 王苏
 * 
 * 更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class YjsWorkInfoHandler {

	private Connection conn;

	public YjsWorkInfoHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * @param string
	 * @return
	 */
	public List getMainDutyListByClause(String string) {
		TbYjsWorkinfomainDAO dao = new TbYjsWorkinfomainDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("witCreatetime", false);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new TbYjsWorkinfomainVO());
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
		TbYjsWorkinfotypeDAO dao = new TbYjsWorkinfotypeDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("witId", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new TbYjsWorkinfotypeVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @return
	 */
	public Integer newDutyType(String name, String desc) throws DAOException {
		TbYjsWorkinfotypeDAO dao = new TbYjsWorkinfotypeDAO();
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
	public Integer newDutyMainInfo(TbYjsWorkinfomainVO vo) throws DAOException {
		// TODO 自动生成方法存根
		TbYjsWorkinfomainDAO dao = new TbYjsWorkinfomainDAO();
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
	public Integer newDutyContentInfo(TbYjsWorkinfoVO infovo)
			throws DAOException, SQLException {
		// String temp = new String(infovo.getWitContent());
		// boolean o = conn.getAutoCommit();
		// conn.setAutoCommit(false);
		//		
		TbYjsWorkinfoDAO dao = new TbYjsWorkinfoDAO();
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
			TbYjsWorkinfoDAO dao = new TbYjsWorkinfoDAO();
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
			TbYjsWorkinfomainDAO dao = new TbYjsWorkinfomainDAO();
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
			TbYjsWorkinfotypeDAO dao = new TbYjsWorkinfotypeDAO();
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
		TbYjsWorkinfotypeDAO dao = new TbYjsWorkinfotypeDAO();
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
	public TbYjsWorkinfomainVO getMainDutyById(String wimid)
			throws DAOException {
		TbYjsWorkinfomainDAO dao = new TbYjsWorkinfomainDAO();
		TbYjsWorkinfomainVO vo = new TbYjsWorkinfomainVO();
		dao.setWimId(new Integer(wimid));
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		vo = (TbYjsWorkinfomainVO) factory
				.findByPrimaryKey(new TbYjsWorkinfomainVO());
		return vo;
	}

	/**
	 * @param string
	 * @return
	 */
	public List getDutyContentInfoByClause(String string) throws DAOException {
		TbYjsWorkinfoDAO dao = new TbYjsWorkinfoDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("wiId", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		list = (List) factory.find(new TbYjsWorkinfoVO());
		return list;
	}

	/**
	 * @param vo
	 * @return
	 */
	public Integer editDutyMainInfo(TbYjsWorkinfomainVO vo) throws DAOException {
		TbYjsWorkinfomainDAO dao = new TbYjsWorkinfomainDAO();
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
	public Integer updateDutyContentInfo(TbYjsWorkinfoVO infovo)
			throws DAOException, SQLException {
		// boolean o = conn.getAutoCommit();
		// conn.setAutoCommit(false);

		TbYjsWorkinfoDAO dao = new TbYjsWorkinfoDAO();
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
	public TbYjsWorkinfoVO getDutyInfobyId(String string) throws DAOException {
		TbYjsWorkinfoDAO dao = new TbYjsWorkinfoDAO();
		TbYjsWorkinfoVO vo = new TbYjsWorkinfoVO();
		dao.setWiId(new Integer(string));
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		vo = (TbYjsWorkinfoVO) factory.findByPrimaryKey(new TbYjsWorkinfoVO());
		return vo;
	}

	public TbYjsWorkinfomainVO getLastDuty() throws DAOException {
		// TODO Auto-generated method stub
		TbYjsWorkinfomainDAO dao = new TbYjsWorkinfomainDAO();
		TbYjsWorkinfomainVO vo = new TbYjsWorkinfomainVO();
		dao.addOrderBy("witDate", false);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new TbYjsWorkinfomainVO());

		if (!list.isEmpty()) {
			vo = (TbYjsWorkinfomainVO) list.get(0);
		}
		return vo;

	}

	public List getLastInfo(Integer wimId) throws DAOException {
		// TODO Auto-generated method stub
		TbYjsWorkinfoDAO dao = new TbYjsWorkinfoDAO();
		dao.setWimId(wimId);
		dao.addOrderBy("witId", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new TbYjsWorkinfoVO());
		return list;

	}

}

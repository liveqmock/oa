/*
 * 创建日期 2010-12-13
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.zbs.xtsduty.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.zbs.xtsduty.dao.TbXtsWorkinfoDAO;
import com.icss.oa.zbs.xtsduty.dao.TbXtsWorkinfomainDAO;
import com.icss.oa.zbs.xtsduty.dao.TbXtsWorkinfotypeDAO;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfoVO;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfomainVO;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfotypeVO;

/**
 * @author 王苏
 * 
 * 更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class XtsWorkInfoHandler {

	private Connection conn;

	public XtsWorkInfoHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * @param string
	 * @return
	 */
	public List getMainDutyListByClause(String string) {
		TbXtsWorkinfomainDAO dao = new TbXtsWorkinfomainDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("witCreatetime", false);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new TbXtsWorkinfomainVO());
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
		TbXtsWorkinfotypeDAO dao = new TbXtsWorkinfotypeDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("witId", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new TbXtsWorkinfotypeVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @return
	 */
	public Integer newDutyType(String name, String desc) throws DAOException {
		TbXtsWorkinfotypeDAO dao = new TbXtsWorkinfotypeDAO();
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
	public Integer newDutyMainInfo(TbXtsWorkinfomainVO vo) throws DAOException {
		// TODO 自动生成方法存根
		TbXtsWorkinfomainDAO dao = new TbXtsWorkinfomainDAO();
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
	public Integer newDutyContentInfo(TbXtsWorkinfoVO infovo)
			throws DAOException, SQLException {
		// String temp = new String(infovo.getWitContent());
		// boolean o = conn.getAutoCommit();
		// conn.setAutoCommit(false);
		//		
		TbXtsWorkinfoDAO dao = new TbXtsWorkinfoDAO();
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
			TbXtsWorkinfoDAO dao = new TbXtsWorkinfoDAO();
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
			TbXtsWorkinfomainDAO dao = new TbXtsWorkinfomainDAO();
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
			TbXtsWorkinfotypeDAO dao = new TbXtsWorkinfotypeDAO();
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
		TbXtsWorkinfotypeDAO dao = new TbXtsWorkinfotypeDAO();
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
	public TbXtsWorkinfomainVO getMainDutyById(String wimid)
			throws DAOException {
		TbXtsWorkinfomainDAO dao = new TbXtsWorkinfomainDAO();
		TbXtsWorkinfomainVO vo = new TbXtsWorkinfomainVO();
		dao.setWimId(new Integer(wimid));
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		vo = (TbXtsWorkinfomainVO) factory
				.findByPrimaryKey(new TbXtsWorkinfomainVO());
		return vo;
	}

	/**
	 * @param string
	 * @return
	 */
	public List getDutyContentInfoByClause(String string) throws DAOException {
		TbXtsWorkinfoDAO dao = new TbXtsWorkinfoDAO();
		dao.setWhereClause(string);
		dao.addOrderBy("wiId", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		list = (List) factory.find(new TbXtsWorkinfoVO());
		return list;
	}

	/**
	 * @param vo
	 * @return
	 */
	public Integer editDutyMainInfo(TbXtsWorkinfomainVO vo) throws DAOException {
		TbXtsWorkinfomainDAO dao = new TbXtsWorkinfomainDAO();
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
	public Integer updateDutyContentInfo(TbXtsWorkinfoVO infovo)
			throws DAOException, SQLException {
		// boolean o = conn.getAutoCommit();
		// conn.setAutoCommit(false);

		TbXtsWorkinfoDAO dao = new TbXtsWorkinfoDAO();
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
	public TbXtsWorkinfoVO getDutyInfobyId(String string) throws DAOException {
		TbXtsWorkinfoDAO dao = new TbXtsWorkinfoDAO();
		TbXtsWorkinfoVO vo = new TbXtsWorkinfoVO();
		dao.setWiId(new Integer(string));
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		vo = (TbXtsWorkinfoVO) factory.findByPrimaryKey(new TbXtsWorkinfoVO());
		return vo;
	}

	public TbXtsWorkinfomainVO getLastDuty() throws DAOException {
		// TODO Auto-generated method stub
		TbXtsWorkinfomainDAO dao = new TbXtsWorkinfomainDAO();
		TbXtsWorkinfomainVO vo = new TbXtsWorkinfomainVO();
		dao.addOrderBy("witDate", false);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new TbXtsWorkinfomainVO());

		if (!list.isEmpty()) {
			vo = (TbXtsWorkinfomainVO) list.get(0);
		}
		return vo;

	}

	public List getLastInfo(Integer wimId) throws DAOException {
		// TODO Auto-generated method stub
		TbXtsWorkinfoDAO dao = new TbXtsWorkinfoDAO();
		dao.setWimId(wimId);
		dao.addOrderBy("witId", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = factory.find(new TbXtsWorkinfoVO());
		return list;

	}

}

package com.icss.oa.address.handler;

import java.sql.Connection;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.dao.EntrustDAO;
import com.icss.oa.address.vo.EntrustVO;
import com.icss.oa.commsite.handler.HandlerException;

public class EntrustHandler {

	private Connection conn;

	public EntrustHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 创建新test
	 * @param testVO 
	 * @throws HandlerException
	 */
	public void add(EntrustVO vo) throws HandlerException {
		EntrustDAO dao = new EntrustDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List get() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		EntrustDAO dao = new EntrustDAO();
		factory.setDAO(dao);

		try {
			List list = factory.find(new EntrustVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getByid(Integer id) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		EntrustDAO dao = new EntrustDAO();
		dao.setId(id);
		factory.setDAO(dao);

		try {
			List list = factory.find(new EntrustVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getByEntrustUid(String userid) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		EntrustDAO dao = new EntrustDAO();
		dao.setEntrustUid(userid);
		dao.setEndtime(new Long(0));
		factory.setDAO(dao);

		try {
			List list = factory.find(new EntrustVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * 修改test信息
	 * @param testVO 
	 */
	public void alter(EntrustVO vo) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		try {
			EntrustDAO dao = new EntrustDAO();
			dao.setValueObject(vo);
			dao.setConnection(conn);
			dao.update(true);

		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}
	/**
		   * 删除test
		   * @param  id
		   * @exception 
		   */
	public void delete(Integer id) throws HandlerException {
		try {
			DAOFactory factory = new DAOFactory(conn);
			EntrustDAO dao = new EntrustDAO();
			factory.setDAO(dao);
			dao.setConnection(conn);
			dao.setId(id);
			dao.delete();

		} catch (Exception e) {
			throw new HandlerException();
		}
	}

}
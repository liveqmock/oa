package com.icss.oa.commsite.handler;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.commsite.dao.CommsiteDAO;
import com.icss.oa.commsite.dao.CommsiteListDAO;
import com.icss.oa.commsite.vo.CommsiteListVO;
import com.icss.oa.commsite.vo.CommsiteVO;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CommsiteHandler {
	private Connection conn;

	public CommsiteHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * @param link0
	 * @return
	 */
	public String CheckLink(String link0) {
		if (link0.indexOf("//") != -1) {
			return link0;
		} else
			return "http://" + link0;
		
	}

	/**
	 * @return
	 * @throws HandlerException
	 */
	public List getList() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		CommsiteDAO dao = new CommsiteDAO();
		dao.addOrderBy("ocsIndex", true);
		factory.setDAO(dao);
		try {
			List list = factory.find(new CommsiteVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * @return
	 * @throws HandlerException
	 */
	public List getWebList() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		CommsiteListDAO dao = new CommsiteListDAO();
		dao.addOrderBy("ocsIndex", true);
		factory.setDAO(dao);
		try {
			List list = factory.find(new CommsiteListVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * @param vo
	 * @throws HandlerException
	 */
	public void add(CommsiteVO vo) throws HandlerException {
		CommsiteDAO dao = new CommsiteDAO();
		dao.setConnection(conn);
		dao.setValueObject(vo);
		try {
			dao.create();
			System.out.println("do this add");
		} catch (DAOException e) {
			System.out.println("error =" + e);
			throw new HandlerException(e);
		}
	}

	/**
	 * @param vo
	 * @param usedefaultlogo
	 */
	public void update(CommsiteVO vo, boolean usedefaultlogo) {
		DAOFactory factory = new DAOFactory(conn);
		CommsiteDAO dao = new CommsiteDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setOcsId(vo.getOcsId());
		try {
			dao = (CommsiteDAO) factory.findByPrimaryKey();
			if ((!usedefaultlogo) && vo.getOcsLogo() == null) {
				InputStream in = dao.getOcsLogo();
				dao.setValueObject(vo);
				dao.setOcsLogo(in);
			} else {
				dao.setValueObject(vo);
			}
			dao.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param ocsId
	 */
	public void delete(Integer ocsId) {
		DAOFactory factory = new DAOFactory(conn);
		CommsiteDAO dao = new CommsiteDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setOcsId(ocsId);
		try {
			dao.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param ocsId
	 * @return
	 */
	public InputStream getStream(Integer ocsId) {
		InputStream in = null;
		DAOFactory factory = new DAOFactory(conn);
		CommsiteDAO dao = new CommsiteDAO();
		factory.setDAO(dao);
		dao.setOcsId(ocsId);
		try {
			CommsiteVO vo = (CommsiteVO) factory
					.findByPrimaryKey(new CommsiteVO());
			in = vo.getOcsLogo();
			return in;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

/*
 * Created on 2004-2-27
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.handler;

import java.sql.Connection;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.bbs.dao.BbsOnlineDAO;
import com.icss.oa.bbs.dao.BbsUserinfoDAO;
import com.icss.oa.bbs.userinfo.control.UserInterface;
import com.icss.oa.bbs.vo.BbsOnlineVO;
import com.icss.oa.bbs.vo.BbsUserinfoVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserMsgHandler implements UserInterface {
	private Connection conn;
	public UserMsgHandler(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * get user id
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public String getUser() {
		UserInfo user = null;
		try {
			Context ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		if (user != null)
			return user.getUserID();
		else
			return null;
	}

	/**
	 * get user id
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public String getUserId() {
		UserInfo user = null;
		try {
			Context ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		if (user != null)
			return user.getPersonUuid();
		else
			return null;
	}
	/**
	 * get user name
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public String getUserName() {
		Context ctx;
		UserInfo user = null;
		try {
			ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();

		} catch (EntityException e) {
			e.printStackTrace();
		}
		if (user != null)
			return user.getCnName();
		else
			return null;
	}
	/**
	 * get userList by userId
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getUserListById(String userId) {
		BbsUserinfoDAO dao = new BbsUserinfoDAO();
		dao.setUserid(userId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = (List) factory.find(new BbsUserinfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * get user vo
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public BbsUserinfoVO getUserVO(String userId) {
		BbsUserinfoDAO dao = new BbsUserinfoDAO();
		dao.setUserid(userId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		BbsUserinfoVO vo = null;
		try {
			vo = (BbsUserinfoVO) factory.findByPrimaryKey(new BbsUserinfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	/**
	 * 
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void addUser(BbsUserinfoVO vo) {
		BbsUserinfoDAO dao = new BbsUserinfoDAO(conn);
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void updateUser(BbsUserinfoVO vo) {
		BbsUserinfoDAO dao = new BbsUserinfoDAO(conn);
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * update userinfo
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void updateUserInfo(BbsUserinfoVO vo) {
		DAOFactory factory = new DAOFactory(conn);
		BbsUserinfoDAO dao = new BbsUserinfoDAO();
		factory.setDAO(dao);
		dao.setUserid(vo.getUserid());
		try {
			dao = (BbsUserinfoDAO) factory.findByPrimaryKey();
			dao.setExp(vo.getExp());
			dao.setPubnum(vo.getPubnum());
			dao.setAccessnum(vo.getAccessnum());
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * update user base info
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void updateUserBaseInfo(BbsUserinfoVO vo) {
		DAOFactory factory = new DAOFactory(conn);
		BbsUserinfoDAO dao = new BbsUserinfoDAO();
		factory.setDAO(dao);
		dao.setUserid(vo.getUserid());
		try {
			dao = (BbsUserinfoDAO) factory.findByPrimaryKey();
			dao.setUsername(vo.getUsername());
			dao.setOicq(vo.getOicq());
			dao.setHomepage(vo.getHomepage());
			dao.setMail(vo.getMail());
			dao.setUserpic(vo.getUserpic());
			dao.setUnderwrite(vo.getUnderwrite());
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * update user base info
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void updateUserLevel(String userId, String updateFlag) {
		DAOFactory factory = new DAOFactory(conn);
		BbsUserinfoDAO dao = new BbsUserinfoDAO();
		factory.setDAO(dao);
		dao.setUserid(userId);
		String level1 = "初级站友";
		String level2 = "中级站友";
		String level3 = "高级站友";
		try {
			dao = (BbsUserinfoDAO) factory.findByPrimaryKey();
			if (updateFlag.equals("1")) {
				if (dao.getUserlevel().equals(level1))
					dao.setUserlevel(level2);
				else if (dao.getUserlevel().equals(level2))
					dao.setUserlevel(level3);
			} else if (updateFlag.equals("2")) {
				if (dao.getUserlevel().equals(level2))
					dao.setUserlevel(level1);
				else if (dao.getUserlevel().equals(level3))
					dao.setUserlevel(level2);
			}
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * get user online info
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public BbsOnlineVO getUserOnlineVO(String userId) {
		BbsOnlineDAO dao = new BbsOnlineDAO();
		dao.setUserid(userId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		BbsOnlineVO vo = null;
		try {
			vo = (BbsOnlineVO) factory.find(new BbsOnlineVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	/**
	 * create user online info
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void addUserOnlineInfo(BbsOnlineVO vo) {
		BbsOnlineDAO dao = new BbsOnlineDAO(conn);
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * update user online info
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void updateUserOnlineInfo(BbsOnlineVO vo) {
		DAOFactory factory = new DAOFactory(conn);
		BbsOnlineDAO dao = new BbsOnlineDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setUserid(vo.getUserid());
		try {
			dao = (BbsOnlineDAO) factory.find();
			dao.setBegintime(new Long(System.currentTimeMillis()));
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}

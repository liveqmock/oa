/*
 * 创建日期 2004-4-7
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.myphonebook.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.myphonebook.dao.SysOrgDAO;
import com.icss.oa.myphonebook.vo.SysOrgTreeVO;
import com.icss.oa.myphonebook.vo.SysOrgVO;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Organization;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class OrgHandler {
	private Connection conn;
	public OrgHandler(Connection conn) {
		this.conn = conn;
	}
	public List getOrgTreeList(String orgId) throws HandlerException {
		List orgList = this.getOrgList(orgId);
		List orgTreeList = null;
		if (orgList != null) {
			orgTreeList = new ArrayList();
			Iterator it = orgList.iterator();
			while (it.hasNext()) {
				SysOrgTreeVO vo = new SysOrgTreeVO();
				SysOrgVO vo1 = (SysOrgVO) it.next();
				vo.setVO(vo1);
				vo.setHasChild(this.hasChildOrg(vo1.getOrguuid()));
				orgTreeList.add(vo);
			}
		}
		return orgTreeList;
	}
	public List getOrgList(String orgId) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setWhereClause("DELTAG='0' AND PARENTORGUUID='" + orgId + "'");
		List orgList = null;
		try {
			dao.addOrderBy("serialindex", true);
			orgList = factory.find(new SysOrgVO());
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
		return orgList;
	}
	public boolean hasChildOrg(String orgId) {
		try {
			List list = this.getOrgList(orgId);
			if (list != null && list.size() > 0) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	public SysOrgVO getTopOrg() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setWhereClause("DELTAG='0' AND ORGLEVEL='0' ");
		List orgList = null;
		try {
			orgList = factory.find(new SysOrgVO());
			if (orgList != null && orgList.size() == 1) {
				return (SysOrgVO) orgList.get(0);
			} else {
				throw new DAOException();
			}
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	public SysOrgVO getOrgByCurrentUser(Organization org, int orglevel) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);

		try {
			if (org.getOrglevel().intValue() <= orglevel) {
				dao.setWhereClause("DELTAG='0' AND ORGUUID='" + org.getOrgUuid() + "' ");
			} else {
				dao.setWhereClause("DELTAG='0' AND ORGUUID='" + getParentOrgByLevel(org, orglevel).getOrgUuid() + "' ");
			}
		} catch (EntityException e1) {
			e1.printStackTrace();
		}

		List orgList = null;
		try {
			orgList = factory.find(new SysOrgVO());
			if (orgList != null && orgList.size() == 1) {
				return (SysOrgVO) orgList.get(0);
			} else {
				throw new DAOException();
			}
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public SysOrgVO getOrg(String orguuid) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO(conn);
		factory.setDAO(dao);
		dao.setOrguuid(orguuid);
		List orgList = null;
		try {
			orgList = factory.find(new SysOrgVO());
			if (orgList != null && orgList.size() == 1) {
				return (SysOrgVO) orgList.get(0);
			} else {
				throw new DAOException();
			}
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public static String realUrl(String url, String orgId, String orgName) {
		int index = url.indexOf("?");
		if (index == -1) {
			return url + "?orgid=" + orgId + "&amp;orgname=" + orgName;
		}
		StringBuffer realUrl = new StringBuffer();
		int si = 0;
		int ei = index;
		int i = 0;
		while (ei != -1) {
			++i;
			realUrl.append(url.substring(si, ei));
			if (i == 1) {
				realUrl.append("?");
			} else {
				realUrl.append("&amp;");
			}
			si = ei + 1;
			ei = url.indexOf("?", si);
		}
		realUrl.append(url.substring(si, url.length()));
		realUrl.append("&amp;orgid=" + orgId + "&amp;orgname=" + orgName);
		return realUrl.toString();
	}

	public Integer getContextUserOrgLevel(String personuuid) throws HandlerException {

		EntityManager entitymanger;
		Organization org = null;
		try {
			entitymanger = EntityManager.getInstance();
			org = entitymanger.findPrimaryOrganization(personuuid);
			return org.getOrglevel();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Organization getContextUserOrg(String personuuid) throws HandlerException {

		EntityManager entitymanger;
		Organization org = null;
		try {
			entitymanger = EntityManager.getInstance();
			org = entitymanger.findPrimaryOrganization(personuuid);
		} catch (EntityException e) {
			e.printStackTrace();
		}
		return org;
	}

	public Organization getParentOrgByLevel(Organization org, int orglevel) throws HandlerException {
		Organization parentorg = null;
		try {
			int parentorglevel = org.getOrglevel().intValue();
			while (parentorglevel > orglevel) {
				parentorg = (org.getParentOrg());
				parentorglevel = parentorg.getOrglevel().intValue();
			}
		} catch (EntityException e) {
			e.printStackTrace();
		}
		return parentorg;
	}

}

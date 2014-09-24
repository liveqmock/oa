/*
 * 创建日期 2004-4-7
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
*/
package com.icss.oa.phonebook.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.phonebook.dao.PhoneInfoDAO;
import com.icss.oa.phonebook.dao.SysOrgDAO;
import com.icss.oa.phonebook.vo.PhoneInfoVO;
import com.icss.oa.phonebook.vo.SysOrgTreeVO;
import com.icss.oa.phonebook.vo.SysOrgVO;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Organization;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class PersonSyncHandler {
	private Connection conn;
	public PersonSyncHandler(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * 人事系统同步人员信息时，同步的人员经过系统管理员审核后，需要同步到电话簿中
	 * @param String personuuid,String orguuid,String personname,String hrid
	 * @return boolean
	 * **/
	public boolean newPersonPhoneInfo(String orguuid,String personuuid,String personname,String hrid) throws HandlerException {
		
		PhoneInfoVO pVO = new PhoneInfoVO();
		//同步人员信息
	    pVO.setOrguuid(orguuid);
	    pVO.setFunction("1");//个人信息
	    pVO.setUseruuid(personuuid);
	    pVO.setUsername(personname);
	    pVO.setHrid(hrid);
	    pVO.setLastmaintantime(new Long(System.currentTimeMillis()));
	    pVO.setNoteorder(new Integer(0));
	    
	    //创建人员信息
	    PhoneInfoDAO dao2 = new PhoneInfoDAO();
		try {
			dao2.setValueObject(pVO);
			dao2.setConnection(conn);
			dao2.create();
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 人事系统同步人员信息时，同步的人员经过系统管理员审核后，需要同步到电话簿中，修改情况
	 * @param String personuuid,String orguuid,String personname,String hrid
	 * @return boolean
	 * **/
	public boolean updatePersonPhoneInfo(String orguuid,String personuuid,String personname,String hrid) throws HandlerException {
		
		PhoneInfoDAO dao = new PhoneInfoDAO();
		//同步人员信息
	    dao.setOrguuid(orguuid);
	    dao.setUsername(personname);
	    dao.setHrid(hrid);
	    dao.setLastmaintantime(new Long(System.currentTimeMillis()));
	    dao.setWhereClause(" useruuid='"+personuuid+"'");
	    
		try {
			dao.setConnection(conn);
			dao.update(false);
		} catch (DAOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

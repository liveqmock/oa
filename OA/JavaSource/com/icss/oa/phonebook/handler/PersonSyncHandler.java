/*
 * �������� 2004-4-7
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class PersonSyncHandler {
	private Connection conn;
	public PersonSyncHandler(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * ����ϵͳͬ����Ա��Ϣʱ��ͬ������Ա����ϵͳ����Ա��˺���Ҫͬ�����绰����
	 * @param String personuuid,String orguuid,String personname,String hrid
	 * @return boolean
	 * **/
	public boolean newPersonPhoneInfo(String orguuid,String personuuid,String personname,String hrid) throws HandlerException {
		
		PhoneInfoVO pVO = new PhoneInfoVO();
		//ͬ����Ա��Ϣ
	    pVO.setOrguuid(orguuid);
	    pVO.setFunction("1");//������Ϣ
	    pVO.setUseruuid(personuuid);
	    pVO.setUsername(personname);
	    pVO.setHrid(hrid);
	    pVO.setLastmaintantime(new Long(System.currentTimeMillis()));
	    pVO.setNoteorder(new Integer(0));
	    
	    //������Ա��Ϣ
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
	 * ����ϵͳͬ����Ա��Ϣʱ��ͬ������Ա����ϵͳ����Ա��˺���Ҫͬ�����绰���У��޸����
	 * @param String personuuid,String orguuid,String personname,String hrid
	 * @return boolean
	 * **/
	public boolean updatePersonPhoneInfo(String orguuid,String personuuid,String personname,String hrid) throws HandlerException {
		
		PhoneInfoDAO dao = new PhoneInfoDAO();
		//ͬ����Ա��Ϣ
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

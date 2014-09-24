/*
 * Created on 2004-4-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.oftenperson.handler;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.icss.common.config.sendtimes.SendTimesConfigManager;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.oftenperson.dao.FiletransferStatDAO;
import com.icss.oa.oftenperson.vo.FiletransferStatVO;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Person;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OftenHandler {
	private Connection conn;
	public OftenHandler(Connection connection) {
		this.conn = connection;
	}

	public OftenHandler() {
	}

	/**
	 *得到指定userid的前n个常用联系人
	 * @param userid
	 * @return
	 */
	public List getOftenPerson(String userid) throws DAOException, EntityException, IOException {
		
		SendTimesConfigManager sendTimesConfigManager = SendTimesConfigManager.getInstance();
		Integer sendtimes = sendTimesConfigManager.getSendTimes();
		System.out.println("wangjiang::getOftenPerson-->sendtimes = " + sendtimes);
		
		List result = new ArrayList();
		DAOFactory factory = new DAOFactory(conn);
		FiletransferStatDAO dao = new FiletransferStatDAO();
		dao.setSenderuserid(userid);
		dao.addOrderBy("times",false);
		
		factory.setDAO(dao);
		List temp = factory.find(new FiletransferStatVO());
		if (!temp.isEmpty()){
			EntityManager entityManager = EntityManager.getInstance();
			for(int i = 0;i<temp.size();i++){
				FiletransferStatVO vo = (FiletransferStatVO)temp.get(i);
				Person person = entityManager.findPersonByUid(vo.getReceiveruserid());
				result.add(person);
				if (i>=sendtimes.intValue()){//只将指定的前sendtimes用户例出
					break;				
				}
			}			
		}
		return result;
	}
	
	/**
	 *得到指定userid的所有常用联系人
	 * @param userid
	 * @return
	 */
	public List getAllOftenPerson(String userid) throws DAOException, EntityException, IOException {
		
		SendTimesConfigManager sendTimesConfigManager = SendTimesConfigManager.getInstance();
		Integer sendtimes = sendTimesConfigManager.getSendTimes();
		System.out.println("wangjiang::getOftenPerson-->sendtimes = " + sendtimes);
		
		List result = new ArrayList();
		DAOFactory factory = new DAOFactory(conn);
		FiletransferStatDAO dao = new FiletransferStatDAO();
		dao.setSenderuserid(userid);
		dao.addOrderBy("times",false);
		
		factory.setDAO(dao);
		List temp = factory.find(new FiletransferStatVO());
		if (!temp.isEmpty()){
			EntityManager entityManager = EntityManager.getInstance();
			for(int i = 0;i<temp.size();i++){
				FiletransferStatVO vo = (FiletransferStatVO)temp.get(i);
				Person person = entityManager.findPersonByUid(vo.getReceiveruserid());
				result.add(person);
			}			
		}
		return result;
	}
	
	/**
	 * 删除常用联系人
	 * @param userid
	 * @param groupid
	 */
	public void delOftenPerson(String userid,String personuserid) throws DAOException {
		FiletransferStatDAO dao = new FiletransferStatDAO();
		dao.setSenderuserid(userid);
		dao.setReceiveruserid(personuserid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		factory.batchDelete();
	}


}

/*
 * Created on 2005-1-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.util;

import java.sql.Connection;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.phonebook.dao.SysRONEPersonDAO;
import com.icss.oa.phonebook.vo.PhoneInfoVO;
import com.icss.oa.phonebook.vo.SysRONEPersonVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SynchRONEImp implements SynchPersonInterface {
	protected Connection conn;
	protected SysRONEPersonVO vo;
	protected String hometel;
	protected String officetel;
	protected String officefax;
	protected String mobile;
	protected String pager;
	public void addPersonInfo(PhoneInfoVO vo) throws Exception {
		updatePhoneInfo(vo);
		commitSynch(vo);
	}
	public void updatePersonInfo(PhoneInfoVO vo) throws Exception {
		updatePhoneInfo(vo);
		commitSynch(vo);
	}
	public void delPersonInfo(PhoneInfoVO vo) throws Exception {
//		deletePhoneInfo(vo);
//		commitSynch(vo);
	}
	
	protected void updatePhoneInfo(PhoneInfoVO vo){
		hometel = vo.getHomephone();
		officetel = vo.getOfficephone();
		officefax = vo.getFaxphone();
		mobile = vo.getMobilephone();
		pager = vo.getNetphone();
	}
	protected void deletePhoneInfo(PhoneInfoVO vo){
		hometel = "";
		officetel = "";
		officefax = "";
		mobile = "";
		pager = "";
	}
	protected void commitSynch(PhoneInfoVO vo){
		try {
			conn = com.icss.j2ee.services.DBConnectionLocator.getInstance().getConnection("jdbc/ROEEE");
			ConnLog.open("commitSynch");
			SysRONEPersonDAO dao = new SysRONEPersonDAO();
			dao.setPersonuuid(vo.getUseruuid());
			dao.setConnection(conn);
			dao.setHometel(hometel);
			dao.setOfficetel(officetel);
			dao.setOfficefax(officefax);
			dao.setMobile(mobile);
			dao.setPager(pager);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null){
					conn.close();
					ConnLog.close("commitSynch");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

}

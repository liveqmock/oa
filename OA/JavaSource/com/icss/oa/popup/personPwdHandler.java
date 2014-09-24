package com.icss.oa.popup;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.resourceone.common.login.dao.PersonAccountDAO;
import com.icss.resourceone.common.login.model.PersonAccountVO;

public class  personPwdHandler{
	
	public static String getPwdByUUID(String uuid){
		Connection conn =null;
		String  pass = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection("jdbc/ROEEE");
			PersonAccountDAO dao =new PersonAccountDAO();
			dao.setPersonuuid(uuid);
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);
			
			List list = factory.find(new PersonAccountVO());
			
			if(!list.isEmpty()){
				pass = ((PersonAccountVO) list.get(0)).getPassword();
			}
		} catch (DBConnectionLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return pass;
	}
}
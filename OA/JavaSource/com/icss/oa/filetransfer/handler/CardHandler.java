/*
 * Created on 2004-12-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.filetransfer.dao.FiletransferCardDAO;
import com.icss.oa.filetransfer.vo.FiletransferCardVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CardHandler {
	Connection conn;
	public CardHandler(Connection _conn){
		conn = _conn;
	}
	/**
	 * get cardlist
	 * @author firecoral
	 */
	public List getAllCard(){
		List list = new ArrayList();
		FiletransferCardDAO cdao = new FiletransferCardDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(cdao);
		try {
			list = factory.find(new FiletransferCardVO());
		} catch (DAOException e) {		
			e.printStackTrace();
		}
		return list;
	}

}

package com.icss.oa.filetransfer.handler;

import java.sql.Connection;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.filetransfer.dao.FiletransferWanttorecordDAO;

public class testHandler {
	public void wantto(Connection conn){
		System.out.println("newInteger");
		DAOFactory factory=new DAOFactory(conn);
		FiletransferWanttorecordDAO fdao = new FiletransferWanttorecordDAO(conn);
		factory.setDAO(fdao);
		fdao.setRecordid("kkkkkkk");
		try {
			fdao.create();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}

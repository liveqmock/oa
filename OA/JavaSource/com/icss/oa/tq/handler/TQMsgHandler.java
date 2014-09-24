package com.icss.oa.tq.handler;

import java.sql.Connection;

import com.icss.j2ee.dao.DAOException;
import com.icss.oa.tq.dao.TqMsgDAO;

public class TQMsgHandler {

	private Connection conn;

	public TQMsgHandler() {
	}

	public TQMsgHandler(Connection conn) {
		this.conn = conn;
	}
	
	public void saveMsg(String sender, String title, String digest, String body, String endday) throws DAOException{
		TqMsgDAO dao = new TqMsgDAO();
		dao.setSender(sender);
		dao.setTitle(title);
		dao.setDigest(digest);
		dao.setBody(body);
		dao.setEndday(endday);
		dao.setConnection(conn);
		dao.create();
	}
}
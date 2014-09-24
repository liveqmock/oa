/*
 * Created on 2004-12-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FiletransferCardrelationDAO extends DAO {

	String sendpersonuuid;

	String recepersonuuid;

	Integer cardid;

	String sendtime;

	String isnew;

	public String getSendpersonuuid() {
		return sendpersonuuid;
	}

	public void setSendpersonuuid(String _sendpersonuuid) {
		firePropertyChange("sendpersonuuid", sendpersonuuid, _sendpersonuuid);
		sendpersonuuid = _sendpersonuuid;
	}

	public String getRecepersonuuid() {
		return recepersonuuid;
	}

	public void setRecepersonuuid(String _recepersonuuid) {
		firePropertyChange("recepersonuuid", recepersonuuid, _recepersonuuid);
		recepersonuuid = _recepersonuuid;
	}

	public Integer getCardid() {
		return cardid;
	}

	public void setCardid(Integer _cardid) {
		firePropertyChange("cardid", cardid, _cardid);
		cardid = _cardid;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String _sendtime) {
		firePropertyChange("sendtime", sendtime, _sendtime);
		sendtime = _sendtime;
	}

	public String getIsnew() {
		return isnew;
	}

	public void setIsnew(String _isnew) {
		firePropertyChange("isnew", isnew, _isnew);
		isnew = _isnew;
	}

	protected void setupFields() throws DAOException {
		addField("sendpersonuuid", "SENDPERSONUUID");
		addField("recepersonuuid", "RECEPERSONUUID");
		addField("cardid", "CARDID");
		addField("sendtime", "SENDTIME");
		addField("isnew", "ISNEW");
		setTableName("FILETRANSFER_CARDRELATION");
		addKey("RECEPERSONUUID");
		addKey("SENDPERSONUUID");
		addKey("SENDTIME");
	}

	public FiletransferCardrelationDAO(Connection conn) {
		super(conn);
	}

	public FiletransferCardrelationDAO() {
		super();
	}
}

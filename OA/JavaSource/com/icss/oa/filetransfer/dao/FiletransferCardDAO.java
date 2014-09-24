/*
 * Created on 2004-12-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.dao;

import java.io.InputStream;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FiletransferCardDAO extends DAO {

	Integer cardid;

	String cardname;

	InputStream cardstream;

	String filename;

	String sortid;

	Long creattime;

	String deltag;

	String uploadperson;

	public Integer getCardid() {
		return cardid;
	}

	public void setCardid(Integer _cardid) {
		firePropertyChange("cardid", cardid, _cardid);
		cardid = _cardid;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String _cardname) {
		firePropertyChange("cardname", cardname, _cardname);
		cardname = _cardname;
	}

	public InputStream getCardstream() {
		return cardstream;
	}

	public void setCardstream(InputStream _cardstream) {
		firePropertyChange("cardstream", cardstream, _cardstream);
		cardstream = _cardstream;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String _filename) {
		firePropertyChange("filename", filename, _filename);
		filename = _filename;
	}

	public String getSortid() {
		return sortid;
	}

	public void setSortid(String _sortid) {
		firePropertyChange("sortid", sortid, _sortid);
		sortid = _sortid;
	}

	public Long getCreattime() {
		return creattime;
	}

	public void setCreattime(Long _creattime) {
		firePropertyChange("creattime", creattime, _creattime);
		creattime = _creattime;
	}

	public String getDeltag() {
		return deltag;
	}

	public void setDeltag(String _deltag) {
		firePropertyChange("deltag", deltag, _deltag);
		deltag = _deltag;
	}

	public String getUploadperson() {
		return uploadperson;
	}

	public void setUploadperson(String _uploadperson) {
		firePropertyChange("uploadperson", uploadperson, _uploadperson);
		uploadperson = _uploadperson;
	}

	protected void setupFields() throws DAOException {
		addField("cardid", "CARDID");
		addField("cardname", "CARDNAME");
		addField("cardstream", "CARDSTREAM");
		addField("filename", "FILENAME");
		addField("sortid", "SORTID");
		addField("creattime", "CREATTIME");
		addField("deltag", "DELTAG");
		addField("uploadperson", "UPLOADPERSON");
		setTableName("FILETRANSFER_CARD");
		addKey("CARDID");
	}

	public FiletransferCardDAO(Connection conn) {
		super(conn);
	}

	public FiletransferCardDAO() {
		super();
	}
}

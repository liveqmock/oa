/*
 * �������� 2004-3-31
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.commsite.dao;

import java.io.InputStream;
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class CommsiteDAO extends DAO {

	Integer ocsId;
	Integer ocsIndex;
	String ocsLink;
	String ocsDes;
	InputStream ocsLogo;
	String ocsName;
	public Integer getOcsId() {
		return ocsId;
	}
	public void setOcsId(Integer _ocsId) {
		firePropertyChange("ocsId", ocsId, _ocsId);
		ocsId = _ocsId;
	}
	public Integer getOcsIndex() {
		return ocsIndex;
	}
	public void setOcsIndex(Integer _ocsIndex) {
		firePropertyChange("ocsIndex", ocsIndex, _ocsIndex);
		ocsIndex = _ocsIndex;
	}
	public String getOcsLink() {
		return ocsLink;
	}
	public void setOcsLink(String _ocsLink) {
		firePropertyChange("ocsLink", ocsLink, _ocsLink);
		ocsLink = _ocsLink;
	}
	public String getOcsDes() {
		return ocsDes;
	}
	public void setOcsDes(String _ocsDes) {
		firePropertyChange("ocsDes", ocsDes, _ocsDes);
		ocsDes = _ocsDes;
	}
	public InputStream getOcsLogo() {
		return ocsLogo;
	}
	public void setOcsLogo(InputStream _ocsLogo) {
		firePropertyChange("ocsLogo", ocsLogo, _ocsLogo);
		ocsLogo = _ocsLogo;
	}
	public String getOcsName() {
		return ocsName;
	}
	public void setOcsName(String _ocsName) {
		firePropertyChange("ocsName", ocsName, _ocsName);
		ocsName = _ocsName;
	}
	protected void setupFields() throws DAOException {
		addField("ocsId", "OCS_ID");
		addField("ocsIndex", "OCS_INDEX");
		addField("ocsLink", "OCS_LINK");
		addField("ocsDes", "OCS_DES");
		addField("ocsLogo", "OCS_LOGO");
		addField("ocsName", "OCS_NAME");
		setTableName("OA_COMMSITE");
		addKey("OCS_ID");
		setAutoIncremented("OCS_ID");
	}
	public CommsiteDAO(Connection conn) {
		super(conn);
	}
	public CommsiteDAO() {
		super();
	} 
}

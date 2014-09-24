/*
 * Created on 2004-4-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test.com.icss.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestDAO extends DAO {

	private String name;
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		firePropertyChange("name", name, _name);
		name = _name;
	}
	protected void setupFields() throws DAOException {
		addField("name", "NAME");
		setTableName("TEST");
	}
	public TestDAO(Connection conn) {
		super(conn);
	}
	public TestDAO() {
		super();
	}
}

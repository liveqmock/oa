/*
 * Created on 2004-4-24
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test.com.icss.dao;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestVO extends ValueObject {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		name = _name;
	}
}
/*
 * Created on 2004-12-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.vo;

import com.icss.j2ee.vo.ValueObject;
/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PhonePrivVO extends ValueObject {
	Integer pp_id;
	String pp_type;
	String pp_scope;
	String pp_name;
	String pp_level;
	String pp_searchlevel;
	
	
	public Integer getPp_id() {
		return pp_id;
	}
	public void setPp_id(Integer _pp_id) {
		pp_id = _pp_id;
	}
    
	public String getPp_type() {
		return pp_type;
	}
	public void setPp_type(String _pp_type) {
		pp_type = _pp_type;
	}
	
	
	public String getPp_scope() {
		return pp_scope;
	}
	public void setPp_scope(String _pp_scope) {
		pp_scope = _pp_scope;
	}
	
	public String getPp_name() {
		return pp_name;
	}
	public void setPp_name(String _pp_name) {
		pp_name = _pp_name;
	}
	
	public String getPp_level() {
		return pp_level;
	}
	public void setPp_level(String _pp_level) {
		pp_level = _pp_level;
	}
	
	public String getPp_searchlevel() {
		return pp_searchlevel;
	}
	public void setPp_searchlevel(String _pp_searchlevel) {
		pp_searchlevel = _pp_searchlevel;
	}
}



/*
 * Created on 2004-6-2
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.phonebook.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SysOrgTreeVO extends ValueObject {
	private SysOrgVO vo;
	private boolean hasChild;
	public SysOrgVO getVO() {
		return vo;
	}
	public void setVO(SysOrgVO _vo) {
		vo = _vo;
	}
	public boolean getHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean _hasChild) {
		hasChild = _hasChild;
	}
}
/*
 * Created on 2004-6-2
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.bbs.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class BBSLeftTreeVO extends ValueObject {
	private BbsAreaVO vo;
	private boolean hasChild;
	public BbsAreaVO getVO() {
		return vo;
	}
	public void setVO(BbsAreaVO _vo) {
		vo = _vo;
	}
	public boolean getHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean _hasChild) {
		hasChild = _hasChild;
	}
}
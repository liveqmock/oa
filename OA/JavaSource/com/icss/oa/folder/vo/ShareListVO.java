/*
 * Created on 2004-4-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.folder.vo;
import java.util.List;

import com.icss.j2ee.vo.ValueObject;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShareListVO extends ValueObject {
	List list;
	String shareAccess;
	public List getList() {
		return list;
	}
	public void setList(List _list) {
		this.list = _list;
	}
	public String getShareAccess() {
		return shareAccess;
	}
	public void setShareAccess(String _shareAccess) {
		this.shareAccess = _shareAccess;
	}

}

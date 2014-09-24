/*
 * Created on 2004-5-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.addressbook.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressbookTreeVO extends ValueObject {
	
	private AddressbookFolderVO addressbookFolderVO;
	//private boolean isShare;
	private boolean hasChild;

	public AddressbookFolderVO getAddressbookFolderVO() {
		return addressbookFolderVO;
	}
	public void setAddressbookFolderVO(AddressbookFolderVO _addressbookFolderVO) {
		addressbookFolderVO = _addressbookFolderVO;
	}
	//public boolean getIsShare() {
	//	return isShare;
	//}
	//public void setIsShare(boolean _isShare) {
	//	isShare = _isShare;
	//}
	public boolean getHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean _hasChild) {
		hasChild = _hasChild;
	}

}

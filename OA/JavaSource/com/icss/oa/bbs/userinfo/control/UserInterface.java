/*
 * Created on 2004-2-27
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.userinfo.control;

import java.util.List;

import com.icss.oa.bbs.vo.BbsUserinfoVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface UserInterface {
	public List getUserListById(String userId);
	public String getUserId();
	public String getUserName();
	public void updateUserInfo(BbsUserinfoVO userVO);
	public void updateUserBaseInfo(BbsUserinfoVO userVO);
	public void updateUserLevel(String userId,String updateFlag);
}

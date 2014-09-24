/*
 * Created on 2004-4-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.user.control;
import java.sql.Connection;

import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.bbs.vo.*;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InitializeUser {
	private Connection conn;
	public InitializeUser(Connection conn) {
		this.conn = conn;
	}
	public BbsUserinfoVO initialize(String userId,String userName,String lastip) {
		UserMsgHandler userMsghandler = new UserMsgHandler(conn);
		BbsUserinfoVO uservo = new BbsUserinfoVO();
		uservo.setUserid(userId);
		uservo.setUsername(userName);
		uservo.setPubnum(new Integer(0));
		uservo.setAccessnum(new Integer(0));
		uservo.setUserlevel("³õ¼¶Õ¾ÓÑ");
		uservo.setRegdate(new Long(System.currentTimeMillis()));
		uservo.setExp(new Integer(0));
		uservo.setIsleader("0");
		uservo.setTruename(userName);
		uservo.setUserpic("001.gif");
		uservo.setLasttime(new Long(System.currentTimeMillis()));
		uservo.setLastip(lastip);
		userMsghandler.addUser(uservo);
		return uservo;
	}

}

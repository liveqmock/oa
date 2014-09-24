/*
 * Created on 2004-5-22
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.util;  

import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CurrentUser {
	private UserInfo user;
	public CurrentUser(){
		user = null;
		try {
			Context ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();
		} catch (EntityException e) {
			e.printStackTrace();
			user = null;
		}
	}
	public String getId(){
		return user==null?null:user.getPersonUuid();
	}
	public String getUserId(){
		return user==null?null:user.getUserID();
	}
	public String getName(){
		return user==null?null:user.getCnName();
	}
}

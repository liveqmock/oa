package com.icss.oa.tq.Webservice;

import java.net.MalformedURLException;

import junit.framework.TestCase;

public class TQUser extends TestCase {

	TQWebserviceClient tqweb = new TQWebserviceClient();

	/**
	 * 注册TQ用户
	 * @param username  用户的用户名
	 * @param userpassword 用户的密码
	 * @param sex 用户的性别,男1,女2,保密0
	 * @param nickname 用户的昵称
	 * @param email 用户的电子邮件
	 * @param city 用户所在的城市
	 * @param departmentId 用户所在部门（必须为数字）
	 * @param spreadparam 扩展参数,默认值为空
	 * @return String
	 * @throws Exception
	 * 
	 */
	public String oneUserRegister(String username, String userpassword,
			String sex, String nickname, String email, String city,
			String departmentId, String spreadparam) throws Exception {

		String rs = tqweb.register("", "10000", "123456", username,
				userpassword, sex, nickname, email, city, departmentId,
				spreadparam);
		return rs;

	}

	/**
	 * 修改TQ用户
	 * @param uin TQ号 
	 * @param username  用户的用户名
	 * @param userpassword 用户的密码
	 * @param nickname 用户的昵称
	 * @param departmentId 用户所在部门（必须为数字）
	 * @param spreadparam 扩展参数,默认值为空
	 * @return String
	 * @throws MalformedURLException
	 */

	public String updateUser(String uin, String username, String userpassword,
			String nickname, String departmentId, String spreadparam)
			throws MalformedURLException {
		String rs = tqweb.refresh("10000", "123456", uin, username,
				userpassword, nickname, departmentId, spreadparam);
		
		return rs;
	}

	/**
	 * 删除TQ用户 
	 * @param uin TQ号 
	 * @param username 用户的用户名
	 * @return
	 * @throws MalformedURLException
	 */
	public String deleteUser(String uin, String username)
			throws MalformedURLException {
		String rs = tqweb.delete("10000", "123456", uin, username);
		return rs;
	}

}

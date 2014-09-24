package com.icss.oa.tq.Webservice;

import java.net.MalformedURLException;

import junit.framework.TestCase;

public class TQUser extends TestCase {

	TQWebserviceClient tqweb = new TQWebserviceClient();

	/**
	 * ע��TQ�û�
	 * @param username  �û����û���
	 * @param userpassword �û�������
	 * @param sex �û����Ա�,��1,Ů2,����0
	 * @param nickname �û����ǳ�
	 * @param email �û��ĵ����ʼ�
	 * @param city �û����ڵĳ���
	 * @param departmentId �û����ڲ��ţ�����Ϊ���֣�
	 * @param spreadparam ��չ����,Ĭ��ֵΪ��
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
	 * �޸�TQ�û�
	 * @param uin TQ�� 
	 * @param username  �û����û���
	 * @param userpassword �û�������
	 * @param nickname �û����ǳ�
	 * @param departmentId �û����ڲ��ţ�����Ϊ���֣�
	 * @param spreadparam ��չ����,Ĭ��ֵΪ��
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
	 * ɾ��TQ�û� 
	 * @param uin TQ�� 
	 * @param username �û����û���
	 * @return
	 * @throws MalformedURLException
	 */
	public String deleteUser(String uin, String username)
			throws MalformedURLException {
		String rs = tqweb.delete("10000", "123456", uin, username);
		return rs;
	}

}

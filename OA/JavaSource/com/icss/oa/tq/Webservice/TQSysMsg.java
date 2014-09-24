package com.icss.oa.tq.Webservice;

import java.net.MalformedURLException;

public class TQSysMsg {

	TQWebserviceClient tqweb = new TQWebserviceClient();

	/**
	 * 
	 * @param title ��Ϣ����
	 * @param digest ��ϢժҪ
	 * @param body ��Ϣ��������
	 * @param life_time ��Ϣ��Ч��
	 * @param refresh_time ��Ϣˢ��ʱ��,��λ����
	 * @param smess_type ��Ϣ���� 0 ȫ��1 �Ŷ�2 ����3 ����
	 * @param create_auther ��Ϣ������
	 * @param smess_uin ��Ϣ���͸�ĳ��TQ����
	 * @param smess_start_uin
	 * @param smess_end_uin
	 * @param show_type ��Ϣչʾ���� 0 ֱ�ӵ������� 1 ����������� 2 �������ҳ��
	 * @param smess_url
	 * @return id
	 * @throws MalformedURLException
	 */
	public String creatMsg(String title, String digest, String body,
			String life_time, String refresh_time, String smess_type,
			String create_auther, String smess_uin, String smess_start_uin,
			String smess_end_uin, String show_type, String smess_url)
			throws MalformedURLException {
		String id = tqweb.createSysMsg("10000", "123456", title, digest, body,
				life_time, refresh_time, smess_type, create_auther, smess_uin,
				smess_start_uin, smess_end_uin, show_type, smess_url);
		return id;
	}

	/**
	 * 
	 * @param id
	 * @param title
	 * @param digest
	 * @param body
	 * @param life_time
	 * @param refresh_time
	 * @param smess_type
	 * @param create_auther
	 * @param smess_uin
	 * @param smess_start_uin
	 * @param smess_end_uin
	 * @param show_type
	 * @param smess_url
	 * @return
	 * @throws MalformedURLException
	 */

	public String updateMsg(String id, String title, String digest,
			String body, String life_time, String refresh_time,
			String smess_type, String create_auther, String smess_uin,
			String smess_start_uin, String smess_end_uin, String show_type,
			String smess_url) throws MalformedURLException {
		String rs = tqweb
				.refreshSysMsg("10000", "123456", id, title, digest, body,
						life_time, refresh_time, smess_type, create_auther,
						smess_uin, smess_start_uin, smess_end_uin, show_type,
						smess_url);
		return rs;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws MalformedURLException
	 */
	public String deleteMsg(String id) throws MalformedURLException {
		String rs = tqweb.deleteSysMsg("10000", "123456", id);
		return rs;
	}

	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws MalformedURLException
	 */
	public String getMsgByTime(String startTime, String endTime)
			throws MalformedURLException {
		String rs = tqweb.getSysMsgIdByTime("10000", "123456", startTime,
				endTime);
		return rs;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws MalformedURLException
	 */
	public String getMsgById(String id) throws MalformedURLException {
		String rs = tqweb.getSysMsgInfoById("10000", "123456", id);
		return rs;
	}
}

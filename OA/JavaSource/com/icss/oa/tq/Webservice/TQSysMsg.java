package com.icss.oa.tq.Webservice;

import java.net.MalformedURLException;

public class TQSysMsg {

	TQWebserviceClient tqweb = new TQWebserviceClient();

	/**
	 * 
	 * @param title 消息标题
	 * @param digest 消息摘要
	 * @param body 消息整体内容
	 * @param life_time 消息有效期
	 * @param refresh_time 消息刷新时间,单位分钟
	 * @param smess_type 消息类型 0 全部1 号段2 定制3 个人
	 * @param create_auther 消息创建者
	 * @param smess_uin 消息发送给某个TQ号码
	 * @param smess_start_uin
	 * @param smess_end_uin
	 * @param show_type 消息展示类型 0 直接弹出窗口 1 点击弹出窗口 2 点击弹出页面
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

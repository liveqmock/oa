package com.icss.oa.sms.admin;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.oa.sms.util.SMSDown;
import com.icss.oa.sms.util.SMSProperties;

public class SMSTaskServlet extends HttpServlet {

	private static final long serialVersionUID = -5133713870906571091L;

	// 读取短信时间间隔，单位：秒。
	private static String sendTimer = SMSProperties.readValue("TIMER");
	private static String sms = SMSProperties.readValue("SMSSERVER");

	public void init() throws ServletException {
		System.out.println("----------是否为SMS同步机"+sms);
		System.out.println("----------SMS同步间隔"+sendTimer);
		run();
	}

	public void run() {
		if("yes".equalsIgnoreCase(sms)){
		Timer timer = new Timer();
		TimerTask task = new SMSDown();
		// 间隔sendTimer秒执行一次任务
		timer.schedule(task, 1000, Integer.valueOf(sendTimer) * 1000);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		run();
	}

}

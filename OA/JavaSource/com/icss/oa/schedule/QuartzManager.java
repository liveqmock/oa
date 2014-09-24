/*
 * Created on 2004-7-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.schedule;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class QuartzManager {

	static Scheduler sched;
	static QuartzManager quartzManager;

	/**
	 * 
	 */
	public QuartzManager() {
	}

	/* 读取任务配置文件
	 * @see com.icss.oa.schedule.ScheduleManagerIfc#configuation(java.lang.String)
	 */
	public void configuation(String configFile) throws JDOMException, IOException {
		SAXBuilder saxBuilder = new SAXBuilder();
		//		System.out.println("configFile is :"+configFile+"   "+getClass());
		//		System.out.println(getClass().getResource(configFile).getPath());
		Document doc = saxBuilder.build(getClass().getResourceAsStream(configFile));
		//		System.out.println("success load xml");
		try {

			//添加任务
			List jobs = doc.getRootElement().getChildren();
			Element jobDetailElm;
			Element triggerElm;
			JobDetail jobDetail;
			CronTrigger trigger;
			for (int i = 0; i < jobs.size(); i++) {
				jobDetailElm = ((Element) jobs.get(i)).getChild("job-detail");
				triggerElm = ((Element) jobs.get(i)).getChild("cron-trigger");
				//jobDetail = new JobDetail(jobDetailElm.getChildText("name"), jobDetailElm.getChildText("group"), Class.forName(jobDetailElm.getChildText("job-class")));
				//jobDetail.getJobDataMap().put("type", "FULL");
				trigger = new CronTrigger(triggerElm.getChildText("name"), triggerElm.getChildText("group"), triggerElm.getChildText("cron-expression"));
				//sched.scheduleJob(jobDetail, trigger);
			}
			//		System.out.println("all job add success!");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static QuartzManager getInstance() {
		quartzManager = new QuartzManager();
		if (sched == null) {
			try {
				SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
				sched = schedFact.getScheduler();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		return quartzManager;
	}

	public static void start() throws SchedulerException {
		sched.start();
	}

	public static void shutDown(boolean arg0) throws SchedulerException {
		sched.shutdown(arg0);
	}

	public static void pause() throws SchedulerException {
		sched.pause();
	}

	public static boolean isShutdown() throws SchedulerException {
		return sched.isShutdown();
	}

	public static boolean isPaused() throws SchedulerException {
		return sched.isPaused();
	}
}

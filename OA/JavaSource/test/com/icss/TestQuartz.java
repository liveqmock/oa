/*
 * Created on 2004-7-8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test.com.icss;

import org.quartz.*;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestQuartz implements Job {

	public void execute(JobExecutionContext cntxt)
		throws JobExecutionException {
		System.out.println(
			"Generating report - "
				+ cntxt.getJobDetail().getJobDataMap().get("type"));
		//TODO Generate report
	}

	public static void main(String[] args) {
		try {
			SchedulerFactory schedFact =
				new org.quartz.impl.StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			sched.start();
			JobDetail jobDetail =
				new JobDetail(
					"Income Report",
					"Report Generation",
					TestQuartz.class);
			jobDetail.getJobDataMap().put("type", "FULL");
			CronTrigger trigger =
				new CronTrigger("Income Report", "Report Generation");
			trigger.setCronExpression("0/15 * * * * ?");
			sched.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/*
 * Created on 2004-7-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.schedule;

import javax.servlet.http.HttpServlet;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class QuartzManagerServlet
	extends HttpServlet {

		SchedulerFactory schedFact;
		static Scheduler sched;

	public void init(){
		//Connection conn = null;
		try {
			super.init();
			QuartzManager quartzManager = QuartzManager.getInstance();
			quartzManager.configuation(this.getInitParameter("configFile"));
			QuartzManager.start();
//			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
//			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
//			try {
//				if(conn!=null)
//					conn.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
		}
	}
	
	public void destroy(){
		try {
			QuartzManager.shutDown(true);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	

}

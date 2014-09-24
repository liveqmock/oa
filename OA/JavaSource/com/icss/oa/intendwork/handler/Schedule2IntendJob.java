/*
 * Created on 2004-4-8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.intendwork.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.IntendWorkConfig;
import com.icss.oa.netoffice.schedule.dao.OfficeScheduleDAO;
import com.icss.oa.netoffice.schedule.handler.ScheduleHandler;
import com.icss.oa.netoffice.schedule.handler.ScheduleHandlerException;
import com.icss.oa.netoffice.schedule.vo.OfficeScheduleVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * 待办工作
 */
public class Schedule2IntendJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("Schedule2IntendJob.execute");
			List list = null;
			//1.取得符合条件的list
			list = search(conn);
			//2.转换list
			if (list != null) {
				Iterator scheduleItr = list.iterator();
				//3.将list插入待办
				IntendWork addIntendHandler = new IntendWork(conn);
				ScheduleHandler sHandler = new ScheduleHandler(conn);
				OfficeScheduleVO officeScheVO = null;
				if (scheduleItr.hasNext()) {
					officeScheVO = (OfficeScheduleVO) scheduleItr.next();

					String topic = new String();
					String source = new String();
					String url = new String();
					String navigate = new String();
					String personid = new String();

					topic = officeScheVO.getOsTopic();
					source = "日程安排";
					url = "/oabase/servlet/ContentShowScheduleServlet?sid=" + officeScheVO.getOsId();
					navigate = "";
					personid = officeScheVO.getPersonid();

					//添加到待办
					addIntendHandler.addWork(topic, source, url, navigate, personid, IntendWork.getCodeValue("oa_schedule"), officeScheVO.getOsId().toString());

					officeScheVO.setIsreminded(IntendWorkConfig.SCHEDULE_IN_INTEND);
					try {
						sHandler.updateSchedule(officeScheVO);
					} catch (ScheduleHandlerException e) {
						e.printStackTrace();
					}

				} //end if
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					ConnLog.close("Schedule2IntendJob.execute");
				}
			} catch (SQLException e1) {
				throw new RuntimeException("数据库连接关闭错误");
			}
		}

	}

	/**
	 * 根据条件搜索日程安排
	 * @param searchSql 搜索条件
	 * @return
	 */
	public List search(Connection conn) {
		DAOFactory daoFactory = new DAOFactory(conn);
		OfficeScheduleDAO dao = new OfficeScheduleDAO();
		dao.setWhereClause(getSearchSql());
		//System.out.println("the sql: "+getSearchSql());
		daoFactory.setDAO(dao);
		List list = null;
		try {
			list = daoFactory.find(new OfficeScheduleVO());
		} catch (DAOException e) {
			throw new RuntimeException("根据条件搜索日程安排错误!" + e.getMessage());
		}
		return list;
	}
	/**
	 * 生成条件查询sql语句
	 * @return sql
	 */
	public String getSearchSql() {

		Context ctx = null;
		try {
			ctx = Context.getInstance();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		UserInfo user = ctx.getCurrentLoginInfo();

		StringBuffer sql = new StringBuffer();
		Long curtime = new Long(System.currentTimeMillis());
		sql.append("abs(" + curtime + "-(OS_DATE+OS_BEGIN-OS_ALERTBUFFER*1000*60*60))<1000*60*5 ");
		sql.append(" and ISREMINDED='0'");
		return sql.toString();
	}

}

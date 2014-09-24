/*
 * Created on 2004-8-3
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.oa.netoffice.onduty.vo.OfficeDutyPeriodVO;
import com.icss.oa.netoffice.onduty.vo.OfficeDutyVO;
import com.icss.oa.util.CommUtil;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowSetDutyServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	   throws ServletException, IOException {
		Connection conn = null;
		List periodList = new ArrayList();
		List periodStrList = new ArrayList();
		List periodDutyList = new ArrayList();
		
		String orgUUid = request.getParameter("orgUUid");
		String yearStr = request.getParameter("year_select");
		String monthStr = request.getParameter("month_select");
		String weekStr = request.getParameter("week_select");
		int year = new Integer(yearStr).intValue();
		int month = new Integer(monthStr).intValue();
		long week = new Integer(weekStr).longValue();
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowSetDutyServlet");
			DutyHandler dHandler = new DutyHandler(conn);
			if(week==6){
				//判断所先的年月是否有第六周，如果没有，则将所选周变为第五周
				week = dHandler.getWeekNum(year,month);
			}
			
			//得到开始时间
			Calendar cal = Calendar.getInstance();
			cal.set(year,month - 1,1);  //设置每月的开始日期
			long weeknum = new Integer(cal.get(Calendar.DAY_OF_WEEK)).longValue();   //取得每月的第一天是一个星期的第几天
			long monthStart = 0;
			
			if(month<10){
				monthStart = CommUtil.getLongByTime(yearStr+"-0"+monthStr+"-01");
			}else{
				monthStart = CommUtil.getLongByTime(yearStr+"-"+monthStr+"-01");
			}

			//得到所选星期的星期天的开始时间
			long weekStart = 0;
			if(weeknum==1){  //此月的第一天是星期天
				weekStart = monthStart - 6*86400000 + (week-1)*7*86400000;
			}else{
				weekStart = monthStart - (weeknum-2)*86400000 + (week-1)*7*86400000;
			}
			request.setAttribute("weekStart",new Long(weekStart));
			
			long curMonthStart = 0;
			if(month<10){
				curMonthStart = CommUtil.getLongByTime(year+"-0"+month+"-01");
			}else{
				curMonthStart = CommUtil.getLongByTime(year+"-"+month+"-01");
			}
			cal.setTime(new Date(curMonthStart));
			int sWeek1 = cal.get(Calendar.DAY_OF_WEEK);  //开始一天是星期几
				
			long nextMonthStart = 0;
			if(month<10){
				nextMonthStart = CommUtil.getLongByTime(year+"-0"+(month+1)+"-01");
			}else{
				nextMonthStart = CommUtil.getLongByTime(year+"-"+(month+1)+"-01");
			}
			cal.setTime(new Date(nextMonthStart-86400000));
			int sWeek2 = cal.get(Calendar.DAY_OF_WEEK);  //最后一天是星期几
				
			long weekth = new Integer(cal.get(Calendar.WEEK_OF_MONTH)).longValue();
			if(sWeek1==1){  //开始一天是星期天
				weekth++; 
			}
			if(sWeek2==1){  //结束一天是星期天
				weekth--;
			}
			request.setAttribute("weekth",new Long(weekth));
			request.setAttribute("year",yearStr);
			request.setAttribute("month",monthStr);
			request.setAttribute("week",(new Long(week)).toString());
			request.setAttribute("orgUUid",orgUUid);
			//得到值班表名
			String dutyName = dHandler.getDutyName(orgUUid,weekStart);
			if("".equals(dutyName)){
				dutyName = dHandler.GetOrgName(orgUUid)+"值班表";
			}
			request.setAttribute("dutyName",dutyName);
			
			//得到当前时间的年、月、星期
			long nowtime = System.currentTimeMillis();
			cal.setTime(new Date(nowtime));
			int curyear = cal.get(Calendar.YEAR);
			int curmonth = cal.get(Calendar.MONTH)+1;
			int curweek = cal.get(Calendar.WEEK_OF_MONTH);
			//判断是否选择的是当前周
			boolean yes = false;
			if((year>curyear)||(month>curmonth)||((year==curyear)&&(month==curmonth)&&(week>=curweek))){
			   yes = true;
			}
			if(yes==true){
				periodList = dHandler.getSetPeriodList(orgUUid);
			
				for(int i=0;i<periodList.size();i++){  //每次循环一个时间段
					OfficeDutyPeriodVO odpVO = (OfficeDutyPeriodVO)periodList.get(i);
					//得到时间段串
					StringBuffer periodBuf = new StringBuffer();
					//加上一个时间只是为得到一个完整的LONG时间
					String pStart = CommUtil.getTime(nextMonthStart+odpVO.getPeriodstart().longValue()).trim();
					periodBuf.append(pStart.substring(pStart.indexOf(" ")));
					periodBuf.append("~");
					String pEnd = CommUtil.getTime(nextMonthStart+odpVO.getPeriodend().longValue()).trim();
					String endStr = pEnd.substring(pEnd.indexOf(" "));
					if("00:00".equals(endStr.trim())){
						endStr = " 24:00";
					}
					periodBuf.append(endStr);

					periodStrList.add(periodBuf.toString());
					
					long firstStart = weekStart;
					Integer periodid = odpVO.getPeriodid();
					for(int j=0;j<7;j++){  //七天
						List onePeriodList = new ArrayList();
						onePeriodList = dHandler.getOnedaySetList(firstStart,periodid,orgUUid);  //一天可能有多个人值班
						periodDutyList.add(onePeriodList);
						firstStart = firstStart + 86400000;
					}
				}
			}else{
				periodList = dHandler.getPeriodList(orgUUid,weekStart);
			
				for(int i=0;i<periodList.size();i++){  //每次循环一个时间段
					OfficeDutyVO odpVO = (OfficeDutyVO)periodList.get(i);
					//得到时间段串
					StringBuffer periodBuf = new StringBuffer();
					//加上一个时间只是为得到一个完整的LONG时间
					String pStart = CommUtil.getTime(odpVO.getStarttime().longValue()).trim();
					periodBuf.append(pStart.substring(pStart.indexOf(" ")));
					periodBuf.append("~");
					String pEnd = CommUtil.getTime(odpVO.getEndtime().longValue()).trim();
					String endStr = pEnd.substring(pEnd.indexOf(" "));
					if("00:00".equals(endStr.trim())){
						endStr = " 24:00";
					}
					periodBuf.append(endStr);
					periodStrList.add(periodBuf.toString());
					
					long firstStart = weekStart;
					for(int j=0;j<7;j++){  //七天
						List onePeriodList = new ArrayList();
						onePeriodList = dHandler.getOnedayList(
							firstStart,
							odpVO.getStarttime().longValue(),
							odpVO.getEndtime().longValue(),
							orgUUid
							);  //一天可能有多个人值班
						periodDutyList.add(onePeriodList);
						firstStart = firstStart + 86400000;
					}
				}
			}  //if

			request.setAttribute("periodList",periodList);
			request.setAttribute("periodStrList",periodStrList);
			request.setAttribute("periodDutyList",periodDutyList);
			
			this.forward(request,response,"/netoffice/onduty/showSetDuty.jsp");
			
		} catch (ServiceLocatorException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("ShowSetDutyServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
	}

}

/*
 * Created on 2004-7-12
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
import com.icss.oa.util.CommUtil;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SetDutyServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		List periodList = new ArrayList();
		List periodStrList = new ArrayList();
		List periodDutyList = new ArrayList();
		
//		String orgid = request.getParameter("orgid");
		String orgUUid = "";
		String orgName = "";
		
        String startDate = (String)request.getParameter("startDate");
		
		try {
			Context ctx = null;
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("SetDutyServlet");
			DutyHandler dHandler = new DutyHandler(conn);
			String personUUid = user.getPersonUuid();
			//登录人所属能排值班表的组织
			orgUUid = dHandler.getOrgRight(personUUid);
			if("".equals(orgUUid)){
				orgUUid = user.getPrimaryOrguuid();
			}
//			if(orgid!=null&&!(orgUUid.equals(orgid))){  //登录用户不属于所选的组织
//				orgUUid = orgid;   //显示所选择的组织
//			}
			
			//组织中文名
			orgName = dHandler.GetOrgName(orgUUid);	
			
			//得到本组织下的本周有值班的人员的信息
			long nowtime = 0;
			if(startDate==null){
				nowtime = System.currentTimeMillis();
			}else{    //不是第一次进入排班界面
				nowtime = CommUtil.getLongByTime(startDate);
			}
			
			//得到当前时间所在星期的开始时间
			long weekStart = dHandler.getWeekStart(nowtime);
			
			//当前月的最后一天是本月的第几个星期
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(nowtime));
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+2;  //下月
			int week = cal.get(Calendar.WEEK_OF_MONTH);
			
			long curMonthStart = 0;
			if((month-1)<10){
				curMonthStart = CommUtil.getLongByTime(year+"-0"+(month-1)+"-01");
			}else{
				curMonthStart = CommUtil.getLongByTime(year+"-"+(month-1)+"-01");
			}
			cal.setTime(new Date(curMonthStart));
			int sWeek1 = cal.get(Calendar.DAY_OF_WEEK);  //开始一天是星期几
				
			long nextMonthStart = 0;
			if(month<10){
				nextMonthStart = CommUtil.getLongByTime(year+"-0"+(month)+"-01");
			}else{
				nextMonthStart = CommUtil.getLongByTime(year+"-"+(month)+"-01");
			}
			cal.setTime(new Date(nextMonthStart-86400000));
			int sWeek2 = cal.get(Calendar.DAY_OF_WEEK);  //最后一天是星期几
				
			long weekth = new Integer(cal.get(Calendar.WEEK_OF_MONTH)).longValue();
			if(sWeek1==1){  //开始一天是星期天
				weekth++;
				week++;
			}
			if(sWeek2==1){  //结束一天是星期天
				weekth--;
				week--;
			}
			
			request.setAttribute("weekth",new Long(weekth));
			
			request.setAttribute("year",String.valueOf(year));
			request.setAttribute("month",String.valueOf(month-1));
			request.setAttribute("week",String.valueOf(week));
			request.setAttribute("weekStart",new Long(weekStart));
			request.setAttribute("orgUUid",orgUUid);
			//得到值班表名
			String dutyName = dHandler.getDutyName(orgUUid,weekStart);
			if("".equals(dutyName)){
				dutyName = orgName+"值班表";
			}
			request.setAttribute("dutyName",dutyName);
			
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
			request.setAttribute("periodList",periodList);
			request.setAttribute("periodStrList",periodStrList);
			request.setAttribute("periodDutyList",periodDutyList);
			
			this.forward(request,response,"/netoffice/onduty/setDuty.jsp");
			
		} catch (ServiceLocatorException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null)
				try {
					conn.close();
					ConnLog.close("SetDutyServlet");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
	}

}

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
				//�ж����ȵ������Ƿ��е����ܣ����û�У�����ѡ�ܱ�Ϊ������
				week = dHandler.getWeekNum(year,month);
			}
			
			//�õ���ʼʱ��
			Calendar cal = Calendar.getInstance();
			cal.set(year,month - 1,1);  //����ÿ�µĿ�ʼ����
			long weeknum = new Integer(cal.get(Calendar.DAY_OF_WEEK)).longValue();   //ȡ��ÿ�µĵ�һ����һ�����ڵĵڼ���
			long monthStart = 0;
			
			if(month<10){
				monthStart = CommUtil.getLongByTime(yearStr+"-0"+monthStr+"-01");
			}else{
				monthStart = CommUtil.getLongByTime(yearStr+"-"+monthStr+"-01");
			}

			//�õ���ѡ���ڵ�������Ŀ�ʼʱ��
			long weekStart = 0;
			if(weeknum==1){  //���µĵ�һ����������
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
			int sWeek1 = cal.get(Calendar.DAY_OF_WEEK);  //��ʼһ�������ڼ�
				
			long nextMonthStart = 0;
			if(month<10){
				nextMonthStart = CommUtil.getLongByTime(year+"-0"+(month+1)+"-01");
			}else{
				nextMonthStart = CommUtil.getLongByTime(year+"-"+(month+1)+"-01");
			}
			cal.setTime(new Date(nextMonthStart-86400000));
			int sWeek2 = cal.get(Calendar.DAY_OF_WEEK);  //���һ�������ڼ�
				
			long weekth = new Integer(cal.get(Calendar.WEEK_OF_MONTH)).longValue();
			if(sWeek1==1){  //��ʼһ����������
				weekth++; 
			}
			if(sWeek2==1){  //����һ����������
				weekth--;
			}
			request.setAttribute("weekth",new Long(weekth));
			request.setAttribute("year",yearStr);
			request.setAttribute("month",monthStr);
			request.setAttribute("week",(new Long(week)).toString());
			request.setAttribute("orgUUid",orgUUid);
			//�õ�ֵ�����
			String dutyName = dHandler.getDutyName(orgUUid,weekStart);
			if("".equals(dutyName)){
				dutyName = dHandler.GetOrgName(orgUUid)+"ֵ���";
			}
			request.setAttribute("dutyName",dutyName);
			
			//�õ���ǰʱ����ꡢ�¡�����
			long nowtime = System.currentTimeMillis();
			cal.setTime(new Date(nowtime));
			int curyear = cal.get(Calendar.YEAR);
			int curmonth = cal.get(Calendar.MONTH)+1;
			int curweek = cal.get(Calendar.WEEK_OF_MONTH);
			//�ж��Ƿ�ѡ����ǵ�ǰ��
			boolean yes = false;
			if((year>curyear)||(month>curmonth)||((year==curyear)&&(month==curmonth)&&(week>=curweek))){
			   yes = true;
			}
			if(yes==true){
				periodList = dHandler.getSetPeriodList(orgUUid);
			
				for(int i=0;i<periodList.size();i++){  //ÿ��ѭ��һ��ʱ���
					OfficeDutyPeriodVO odpVO = (OfficeDutyPeriodVO)periodList.get(i);
					//�õ�ʱ��δ�
					StringBuffer periodBuf = new StringBuffer();
					//����һ��ʱ��ֻ��Ϊ�õ�һ��������LONGʱ��
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
					for(int j=0;j<7;j++){  //����
						List onePeriodList = new ArrayList();
						onePeriodList = dHandler.getOnedaySetList(firstStart,periodid,orgUUid);  //һ������ж����ֵ��
						periodDutyList.add(onePeriodList);
						firstStart = firstStart + 86400000;
					}
				}
			}else{
				periodList = dHandler.getPeriodList(orgUUid,weekStart);
			
				for(int i=0;i<periodList.size();i++){  //ÿ��ѭ��һ��ʱ���
					OfficeDutyVO odpVO = (OfficeDutyVO)periodList.get(i);
					//�õ�ʱ��δ�
					StringBuffer periodBuf = new StringBuffer();
					//����һ��ʱ��ֻ��Ϊ�õ�һ��������LONGʱ��
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
					for(int j=0;j<7;j++){  //����
						List onePeriodList = new ArrayList();
						onePeriodList = dHandler.getOnedayList(
							firstStart,
							odpVO.getStarttime().longValue(),
							odpVO.getEndtime().longValue(),
							orgUUid
							);  //һ������ж����ֵ��
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

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
			//��¼����������ֵ������֯
			orgUUid = dHandler.getOrgRight(personUUid);
			if("".equals(orgUUid)){
				orgUUid = user.getPrimaryOrguuid();
			}
//			if(orgid!=null&&!(orgUUid.equals(orgid))){  //��¼�û���������ѡ����֯
//				orgUUid = orgid;   //��ʾ��ѡ�����֯
//			}
			
			//��֯������
			orgName = dHandler.GetOrgName(orgUUid);	
			
			//�õ�����֯�µı�����ֵ�����Ա����Ϣ
			long nowtime = 0;
			if(startDate==null){
				nowtime = System.currentTimeMillis();
			}else{    //���ǵ�һ�ν����Ű����
				nowtime = CommUtil.getLongByTime(startDate);
			}
			
			//�õ���ǰʱ���������ڵĿ�ʼʱ��
			long weekStart = dHandler.getWeekStart(nowtime);
			
			//��ǰ�µ����һ���Ǳ��µĵڼ�������
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(nowtime));
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+2;  //����
			int week = cal.get(Calendar.WEEK_OF_MONTH);
			
			long curMonthStart = 0;
			if((month-1)<10){
				curMonthStart = CommUtil.getLongByTime(year+"-0"+(month-1)+"-01");
			}else{
				curMonthStart = CommUtil.getLongByTime(year+"-"+(month-1)+"-01");
			}
			cal.setTime(new Date(curMonthStart));
			int sWeek1 = cal.get(Calendar.DAY_OF_WEEK);  //��ʼһ�������ڼ�
				
			long nextMonthStart = 0;
			if(month<10){
				nextMonthStart = CommUtil.getLongByTime(year+"-0"+(month)+"-01");
			}else{
				nextMonthStart = CommUtil.getLongByTime(year+"-"+(month)+"-01");
			}
			cal.setTime(new Date(nextMonthStart-86400000));
			int sWeek2 = cal.get(Calendar.DAY_OF_WEEK);  //���һ�������ڼ�
				
			long weekth = new Integer(cal.get(Calendar.WEEK_OF_MONTH)).longValue();
			if(sWeek1==1){  //��ʼһ����������
				weekth++;
				week++;
			}
			if(sWeek2==1){  //����һ����������
				weekth--;
				week--;
			}
			
			request.setAttribute("weekth",new Long(weekth));
			
			request.setAttribute("year",String.valueOf(year));
			request.setAttribute("month",String.valueOf(month-1));
			request.setAttribute("week",String.valueOf(week));
			request.setAttribute("weekStart",new Long(weekStart));
			request.setAttribute("orgUUid",orgUUid);
			//�õ�ֵ�����
			String dutyName = dHandler.getDutyName(orgUUid,weekStart);
			if("".equals(dutyName)){
				dutyName = orgName+"ֵ���";
			}
			request.setAttribute("dutyName",dutyName);
			
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

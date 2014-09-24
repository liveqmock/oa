/*
 * Created on 2004-11-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.netoffice.onduty.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PrintDutyBean {
	
	private Integer year;
	private Integer month;
	private Integer week;
	private Long weekStart;
	private String orgUUid;
	private List periodList;
	private List periodStrList;
	private List periodDutyList;
	

	/**
	 * construct method
	 */
	public PrintDutyBean() {
		year = new Integer(0);
		month = new Integer(0);
		week = new Integer(0);
		weekStart = new Long(0);
		orgUUid = "";
		periodList = new ArrayList();
		periodStrList = new ArrayList();
		periodDutyList = new ArrayList();
	}
	
	public void setYear(int year){
		this.year = new Integer(year);
	}
	
	public void setMonth(int month){
		this.month = new Integer(month);
	}
	
	public void setWeek(int week){
		this.week = new Integer(week);
	}
	
	public void setWeekStart(long weekstart){
		this.weekStart = new Long(weekstart);
	}
	
	public void setOrgUUid(String orgid){
		this.orgUUid = orgid;
	}
	
	public int getYear(){
		return this.year.intValue();
	}
	
	public int getMonth(){
		return this.month.intValue();
	}
	
	public int getWeek(){
		return this.week.intValue();
	}
	
	public long getWeekStart(){
		return this.weekStart.longValue();
	}
	
	public String getOrgUUid(){
		return this.orgUUid;
	}
	
	public void setPeriodList(List list){
		this.periodList = list;
	}
	
	public void setperiodStrList(List list){
		this.periodStrList = list;
	}
	
	public void setperiodDutyList(List list){
		this.periodDutyList = list;
	}
	
	public List getPeriodList(){
		return this.periodList;
	}
	
	public List getperiodStrList(){
		return this.periodStrList;
	}
	
	public List getperiodDutyList(){
		return this.periodDutyList;
	}
	
	/**
	 * 从session中取得实例
	 * @param request
	 * @return PrintDutyBean
	 */
	public static PrintDutyBean getInstanceFromSession(HttpSession session) {
		PrintDutyBean printDutyBean = (PrintDutyBean) session.getAttribute("printduty");
		if (printDutyBean == null) {
			printDutyBean = new PrintDutyBean();
		}
		return printDutyBean;
	}
	/**
	 * 存入session
	 * @param session
	 * @param attachFile
	 */
	public static void saveToSession(HttpSession session,PrintDutyBean printDutyBean) {
		session.setAttribute("printduty", printDutyBean);
	}

	/**
	 * 删除session
	 * @param session
	 */
	public static void removeSession(HttpSession session) {
		session.removeAttribute("printduty");
	}
	
//	public void testbean(){
//		HttpServletRequest request = null;
//		HttpSession testsession = request.getSession();
//		PrintDutyBean.removeSession(testsession);
//		PrintDutyBean testBean = PrintDutyBean.getInstanceFromSession(testsession);
//		testBean.setOrgUUid();
//		testBean.setOrgname();
//		testBean.setPeriodList();
//		testBean.setperiodStrList();
//		testBean.setperiodDutyList();
//		testBean.saveToSession(testsession,testBean);
//	}

}

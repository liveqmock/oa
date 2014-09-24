/*
 * Created on 2004-7-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.config.PhoneBookConfig;
import com.icss.oa.netoffice.onduty.dao.OfficeDutyDAO;
import com.icss.oa.netoffice.onduty.dao.OfficeDutyPeriodDAO;
import com.icss.oa.netoffice.onduty.dao.OfficeDutyRightDAO;
import com.icss.oa.netoffice.onduty.dao.OfficeDutyRightSysPersonSearchDAO;
import com.icss.oa.netoffice.onduty.dao.SysOrgpersonSysPersonSearchDAO;
import com.icss.oa.netoffice.onduty.dao.SysPersonDAO;
import com.icss.oa.netoffice.onduty.vo.OfficeDutyPeriodVO;
import com.icss.oa.netoffice.onduty.vo.OfficeDutyRightSysPersonVO;
import com.icss.oa.netoffice.onduty.vo.OfficeDutyRightVO;
import com.icss.oa.netoffice.onduty.vo.OfficeDutyVO;
import com.icss.oa.netoffice.onduty.vo.SysOrgpersonSysPersonVO;
import com.icss.oa.netoffice.onduty.vo.SysPersonVO;
import com.icss.oa.phonebook.dao.PhoneInfoDAO;
import com.icss.oa.phonebook.dao.SysOrgDAO;
import com.icss.oa.phonebook.vo.PhoneInfoVO;
import com.icss.oa.phonebook.vo.SysOrgVO;
import com.icss.oa.util.CommUtil;
import com.icss.resourceone.sdk.framework.Person;
import com.icss.resourceone.sdk.right.AppRole;
import com.icss.resourceone.sdk.right.RightException;
import com.icss.resourceone.sdk.right.RightManager;
import com.icss.resourceone.sdk.right.Role;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DutyHandler {
	
	private Connection conn;
	public DutyHandler(Connection conn) {
		this.conn = conn;
	}
	
	public DutyHandler() {
	}
	
	/**
	 * ������ѡ�����е�ĳ�¿���ٸ���
	 * @author firecoral
	 * param  year:��ѡ���ꣻmonth:��ѡ���£�
	 */
	public long getWeekNum(int year,int month){
		long week = 0;
		try {
			Calendar calx = Calendar.getInstance();
			long curMonthStart = 0;
			if((month)<10){
				curMonthStart = CommUtil.getLongByTime(year+"-0"+(month)+"-01");
			}else{
				curMonthStart = CommUtil.getLongByTime(year+"-"+(month)+"-01");
			}
			calx.setTime(new Date(curMonthStart));
			int sWeek1 = calx.get(Calendar.DAY_OF_WEEK);  //��ʼһ�������ڼ�
			
			long nextMonthStart = 0;
			if(month<10){
				nextMonthStart = CommUtil.getLongByTime(year+"-0"+(month+1)+"-01");
			}else{
				nextMonthStart = CommUtil.getLongByTime(year+"-"+(month+1)+"-01");
			}
			calx.setTime(new Date(nextMonthStart-86400000));
			week = calx.get(Calendar.WEEK_OF_MONTH); //��ѡ�µ����һ�ܵ�����
			int sWeek2 = calx.get(Calendar.DAY_OF_WEEK);  //���һ�������ڼ�

			if(sWeek1==1){  //��ʼһ����������
				week++; 
			}
			if(sWeek2==1){  //����һ����������
				week--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return week;
	}
	
	/**
	 * ͨ�����ڵ������õ����ڼ�
	 * @author firecoral
	 *
	 */
	public String getWeekStr(int week) {
		String weekstr = "";
		if(week==1){
			weekstr = "������";
		}else if(week==2){
			weekstr = "����һ";
		}else if(week==3){
			weekstr = "���ڶ�";
		}else if(week==4){
			weekstr = "������";
		}else if(week==5){
			weekstr = "������";
		}else if(week==6){
			weekstr = "������";
		}else if(week==7){
			weekstr = "������";
		}
		return weekstr;
	}
	
	/**
	 * ����µ�ֵ����
	 * @author firecoral
	 *
	 */
	public void newDuty(OfficeDutyVO vo) {
		OfficeDutyDAO dao = new OfficeDutyDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �õ���ǰʱ���������ڵĿ�ʼʱ��(Ĭ������һ�ǵ�һ�죩
	 * @author firecoral
	 *
	 */
	public long getWeekStart(long nowtime) throws Exception {
		long starttime = 0;
		long curdate = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(nowtime));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1; 
        int day = cal.get(Calendar.DAY_OF_MONTH);
        curdate = CommUtil.getLongByTime(year+"-"+month+"-"+day);
        
		int sWeek = cal.get(Calendar.DAY_OF_WEEK);
		if(sWeek==1){  //sWeek equals 1 so it is sunday,and get the forweek
			starttime = curdate - 6*86400000;
		}else{
			starttime = curdate - (new Integer(sWeek-2)).longValue()*86400000;
		}
		return starttime;
	}
	
	/**
	 * �õ���ǰһ������֮�������֯������ֵ���˵�ֵ����Ϣ�ļ�¼�����ڲ鿴
	 * @author firecoral
	 *
	 */
	public List getOneWeekList(long weekStart,String orgUUid) throws Exception {
		List list = new ArrayList();
		
		List personList = new ArrayList();

		long weekEnd = weekStart+7*86400000;
		
		StringBuffer sql = new StringBuffer();
		OfficeDutyDAO dao = new OfficeDutyDAO();
		dao.setWhereClause("ORGUUID='"+orgUUid+"' AND "+weekStart+"<=STARTTIME AND STARTTIME<="+weekEnd);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			list = factory.find(new OfficeDutyVO());
			if(list.size()>0){
				Iterator personItr = list.iterator();
				int i = 0;
				while(personItr.hasNext()){
					OfficeDutyVO odVO = (OfficeDutyVO)personItr.next();
					if(i==0){
						personList.add(odVO);
					}else{  //��֤UUID���ظ�
						boolean pd = false;
						for(int j=0;j<personList.size();j++){
							OfficeDutyVO compareVO = (OfficeDutyVO)personList.get(j);
							if(odVO.getPersonuuid().equals(compareVO.getPersonuuid())){
								pd = true;
								break;
							}
						}
						if(pd==false){
							personList.add(odVO);
						}
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personList;
	}
	
	/**
	 * �õ�ĳһ����ĳһ���̶�ʱ������ֵ���ˣ�����list
	 * @author firecoral
	 *
	 */
	public List getOnedaySetList(long dayStart,Integer periodid,String orgUUid){
		List list  = new ArrayList();
		List periodtime = this.getPeriodStarttime(periodid);
		long dutystart = dayStart + ((Long)periodtime.get(0)).longValue();
		long dutyend = dayStart + ((Long)periodtime.get(1)).longValue();
		OfficeDutyDAO dao = new OfficeDutyDAO();
		DAOFactory factory = new DAOFactory(conn);
		String sql = "ORGUUID='"+orgUUid+"' AND "+dutystart+"=STARTTIME AND ENDTIME="+dutyend;
		dao.setWhereClause(sql);
		factory.setDAO(dao);
		
		try {
			list = factory.find(new OfficeDutyVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}  //һ��ʱ��ο����м�����ͬʱֵ��
		return list;
	}
	
	/**
	 * �õ�ĳһ����ĳһ���̶�ʱ������ֵ���ˣ�����list
	 * @author firecoral
	 *
	 */
	public List getOnedayList(long dayStart,long starttime,long endtime,String orgUUid) {
//		List periodtime = this.getPeriodStarttime(periodid);
//		long dutystart = dayStart + ((Long)periodtime.get(0)).longValue();
//		long dutyend = dayStart + ((Long)periodtime.get(1)).longValue();
		List list = new ArrayList();
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(starttime));
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			StringBuffer daybuf = new StringBuffer();
			daybuf.append(year);
			daybuf.append("-");
			if(month>=10){
				daybuf.append(month);
			}else{
				daybuf.append("0"+month);
			}
			daybuf.append("-");
			if(day>=10){
				daybuf.append("0"+day);
			}else{
				daybuf.append(day);
			}
			long sdate = CommUtil.getLongByTime(daybuf.toString());
		
			long dutystart = starttime-sdate+dayStart;
			long dutyend = endtime-sdate+dayStart;
			
			OfficeDutyDAO dao = new OfficeDutyDAO();
			DAOFactory factory = new DAOFactory(conn);
			String sql = "ORGUUID='"+orgUUid+"' AND "+dutystart+"=STARTTIME AND ENDTIME="+dutyend;
			dao.setWhereClause(sql);
			factory.setDAO(dao);
		
			list = factory.find(new OfficeDutyVO());  //һ��ʱ��ο����м�����ͬʱֵ��
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ��һ����֯��UUID�õ�����ֵ֯���ʱ���
	 * @author firecoral
	 *
	 */
	public List getSetPeriodList(String orgUUid){
		List list = new ArrayList();
		OfficeDutyPeriodDAO dao = new OfficeDutyPeriodDAO();
		dao.setOrguuid(orgUUid);
		dao.addOrderBy("periodstart",true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try{
			list  = factory.find(new OfficeDutyPeriodVO());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ��һ����֯��UUID��һ�ܿ�ʼ�õ�����ֵ֯���ʱ��Σ����ڲ鿴��
	 * @author firecoral
	 *
	 */
	public List getPeriodList(String orgUUid,long weekStart) {
		List list = new ArrayList();
		List periodlist = new ArrayList();
		StringBuffer sqlbf = new StringBuffer();
		OfficeDutyDAO dao = new OfficeDutyDAO();
		dao.setWhereClause(" ORGUUID='"+orgUUid+"'AND STARTTIME>="+weekStart+"AND STARTTIME<="+(weekStart+7*86400000));
		dao.addOrderBy("starttime",true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			list = factory.find(new OfficeDutyVO());
			if(list.size()>0){
				int x = 0;
				Iterator Itr = list.iterator();
				while(Itr.hasNext()){
					OfficeDutyVO odVO = (OfficeDutyVO)Itr.next();
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date(odVO.getStarttime().longValue()));
					int shour = cal.get(Calendar.HOUR_OF_DAY);
					int sminute = cal.get(Calendar.MINUTE);
					cal.setTime(new Date(odVO.getEndtime().longValue()));
					int ehour = cal.get(Calendar.HOUR_OF_DAY);
					int eminute = cal.get(Calendar.MINUTE);
//					if(!(shour==0&&sminute==0&&ehour==0&&eminute==0)
					if(odVO.getStarttime().longValue()!=odVO.getEndtime().longValue()){
						if(x>0){
							//���Ƿ������ͬ��ʱ���
							boolean tr = false;
							for(int i=0;i<periodlist.size();i++){
								OfficeDutyVO tempVO = (OfficeDutyVO)periodlist.get(i);
								cal.setTime(new Date(tempVO.getStarttime().longValue()));
								int tshour = cal.get(Calendar.HOUR_OF_DAY);
								int tsminute = cal.get(Calendar.MINUTE);
								cal.setTime(new Date(tempVO.getEndtime().longValue()));
								int tehour = cal.get(Calendar.HOUR_OF_DAY);
								int teminute = cal.get(Calendar.MINUTE);
								if(shour==tshour&&sminute==tsminute&&ehour==tehour&&eminute==teminute){
									tr = true;
									break;
								}
							}
							if(tr==false)  periodlist.add(odVO);
						}else{
							periodlist.add(odVO);
						}
					}
					x++;
				}
				//��ʱ��δ��絽������
				periodlist = this.getNewTimeList(periodlist);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return periodlist;
	}
	
	/**
	 * �õ�ֵ��������
	 * @author firecoral
	 *
	 */
	public String getUserName(String personUuid) {
		String personName = "";
		SysPersonDAO dao = new SysPersonDAO();
		DAOFactory factory =new DAOFactory(conn);
		dao.setPersonuuid(personUuid);
		factory.setDAO(dao);
		try {
			List list = factory.find(new SysPersonVO());
			if(list.size()>0){
				Iterator perItr = list.iterator();
				while(perItr.hasNext()){  //ֻ�õ�һ����¼
					SysPersonVO perVO = (SysPersonVO)perItr.next();
					personName = perVO.getCnname();
				}
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return personName;
	}
	
	/**
	 * ��LIST�еļ�¼��ʱ��Ӿɵ�������(�������ڡ�ʱ�䣩
	 * @author firecoral
	 *
	 */
	public List getNewDutyList(List list) {
		List newlist = new ArrayList();
		List templist = new ArrayList();
		for(int i=0;i<list.size();i++){
		    OfficeDutyVO ofVO = (OfficeDutyVO)list.get(i);
		    if(i==0){
				newlist.add(ofVO);
		    }else{
		    	boolean pd = false;
				int listlength = newlist.size();
		    	for(int j=0;j<listlength;j++){
		    		OfficeDutyVO tempVO = (OfficeDutyVO)newlist.get(j);
		    		if(ofVO.getStarttime().longValue()<tempVO.getStarttime().longValue()){
		    			for(int k=j;k<listlength;k++){
		    				newlist.set(k,ofVO);
		    				ofVO = tempVO;
		    				if(k<listlength-1){//�������һ��
								tempVO = (OfficeDutyVO)newlist.get(k+1);
		    				}else{
								newlist.add(ofVO);  //�������һ��
		    				}
		    			}
		    			pd = true; //�ҵ����Լ����
		    		}//if
		    	}
		    	if(pd==false){  //û�ҵ����Լ���ģ���������
		    		newlist.add(ofVO);
		    	}
		    }//if
		    
	    }//for

		return newlist;
	}
	
	/**
	 * ��LIST�еļ�¼��ʱ��Ӿɵ������У����������ڣ�
	 * @author firecoral
	 *
	 */
	public List getNewTimeList(List list) throws Exception {
		List newlist = new ArrayList();
		List templist = new ArrayList();
		for(int i=0;i<list.size();i++){
			OfficeDutyVO ofVO = (OfficeDutyVO)list.get(i);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(ofVO.getStarttime().longValue()));
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minute = cal.get(Calendar.MINUTE);

			if(i==0){
				newlist.add(ofVO);
			}else{
				boolean pd = false;
				int listlength = newlist.size();
				for(int j=0;j<listlength;j++){
					OfficeDutyVO tempVO = (OfficeDutyVO)newlist.get(j);
					cal.setTime(new Date(tempVO.getStarttime().longValue()));
					int thour = cal.get(Calendar.HOUR_OF_DAY);
					int tminute = cal.get(Calendar.MINUTE);
					if(hour<thour||(hour==thour&&minute<tminute)){
						for(int k=j;k<listlength;k++){
							newlist.set(k,ofVO);
							ofVO = tempVO;
							if(k<listlength-1){//�������һ��
								tempVO = (OfficeDutyVO)newlist.get(k+1);
							}else{
								newlist.add(ofVO);  //�������һ��
							}
						}
						pd = true; //�ҵ����Լ�С��
						break;
					}//if
				}
				if(pd==false){  //û�ҵ����Լ�С�ģ���������
					newlist.add(ofVO);
				}
			}//if
		    
		}//for

		return newlist;
	}
	
	/**
	 * ɾ��һ��ֵ���¼
	 * @author firecoral
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void delDuty(Integer dutyId) {
		OfficeDutyDAO dao = new OfficeDutyDAO();
		dao.setDutyid(dutyId);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����һ��ֵ���¼
	 * @author firecoral
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void UpdateDuty(Integer dutyId,String personUUid,Long stime,Long etime) {
		OfficeDutyDAO dao = new OfficeDutyDAO();
		dao.setDutyid(dutyId);
		dao.setPersonuuid(personUUid);
		dao.setStarttime(stime);
		dao.setEndtime(etime);
		dao.setConnection(conn);
		try {
			dao.update(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �õ���֯������
	 * @author firecoral
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public String GetOrgName(String OrgUUid) {
		String orgName = "";
		List orgList = new ArrayList();
		SysOrgDAO dao = new SysOrgDAO();
		dao.setOrguuid(OrgUUid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			orgList = factory.find(new SysOrgVO());
			SysOrgVO sysVO = (SysOrgVO)orgList.get(0);
			orgName = sysVO.getCnname();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgName;
	}
	
	/**
	 * �жϵ�ǰ��ѡ��֯�Ƿ��ǵ�¼�û�������֯������֯
	 * @author firecoral
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public boolean IsChildOrg(String ParentUUid,String ChildUUid) {
		boolean pd = false;
		List orgList = new ArrayList();
		SysOrgDAO dao = new SysOrgDAO();
		DAOFactory factory = new DAOFactory(conn);
		String sql = "ORGUUID='"+ChildUUid+"' AND "+"PARENTORGUUID='"+ParentUUid+"'";
		dao.setWhereClause(sql);
		factory.setDAO(dao);
		try {
			orgList = factory.find(new SysOrgVO());
			if(orgList.size()>0)
			    pd = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pd;
	}
	
	/**
	 * �õ�һ����֯�µ�������Ա
	 * @author firecoral
	 *
	 */
	public List GetAllUser(String OrgUUid) {
		List orgList = new ArrayList();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(
			"SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ORGUUID,SYS_ORGPERSON.ISBELONG,SYS_PERSON.USERID,SYS_PERSON.CNNAME ");
		sql.append("from ");
		sql.append("SYS_ORGPERSON,SYS_PERSON ");
		sql.append("where ");
		sql.append("SYS_ORGPERSON.PERSONUUID= SYS_PERSON.PERSONUUID and ");
		sql.append("SYS_ORGPERSON.ORGUUID= '"+ OrgUUid + "'" +"AND SYS_ORGPERSON.ISBELONG= 1");
		SysOrgpersonSysPersonSearchDAO dao = new SysOrgpersonSysPersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			orgList = factory.find(new SysOrgpersonSysPersonVO());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgList;
	}
	
	/**
	 * �õ�һ����֯�µ���������ֵ������Ա
	 * @author firecoral
	 *
	 */
	public List GetAllDutyUser(String OrgUUid) {
		List userlist = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("SYS_PERSON.USERID,SYS_PERSON.DELTAG,SYS_PERSON.CNNAME,OFFICE_DUTY_RIGHT.ORGUUID,OFFICE_DUTY_RIGHT.PERSONUUID ");
		sql.append("FROM ");
		sql.append("OFFICE_DUTY_RIGHT,SYS_PERSON ");
		sql.append(" WHERE OFFICE_DUTY_RIGHT.PERSONUUID=SYS_PERSON.PERSONUUID AND OFFICE_DUTY_RIGHT.ORGUUID='"+OrgUUid+"'");
		sql.append(" AND SYS_PERSON.DELTAG='0'");
		OfficeDutyRightSysPersonSearchDAO dao = new OfficeDutyRightSysPersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			userlist = factory.find(new OfficeDutyRightSysPersonVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userlist;
	}
	
	/**
	 * �õ�һ��ʱ��εĿ�ʼʱ��ͽ���ʱ��
	 * @author firecoral
	 *
	 */
	public List getPeriodStarttime(Integer periodid){
		Long starttime = null;
		Long endtime = null;
		List list = new ArrayList();
		List timelist = new ArrayList();
		OfficeDutyPeriodDAO dao = new OfficeDutyPeriodDAO();
		dao.setPeriodid(periodid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			list = factory.find(new OfficeDutyPeriodVO());
			if(list!=null){
				OfficeDutyPeriodVO odpVO = (OfficeDutyPeriodVO)list.get(0);
				starttime = odpVO.getPeriodstart();
				endtime = odpVO.getPeriodend();
				timelist.add(starttime);
				timelist.add(endtime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timelist;
	}
	
	/**
	 * ���һ��ʱ��μ�¼
	 * @author firecoral
	 *
	 */
	public void addPeriod(String orgUUid,long starttime,long endtime){
		OfficeDutyPeriodDAO dao = new OfficeDutyPeriodDAO();
		dao.setOrguuid(orgUUid);
		dao.setPeriodstart(new Long(starttime));
		dao.setPeriodend(new Long(endtime));
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ��һ��ʱ��μ�¼����ɾ���޸����������Ӧʱ��εļ�¼
	 * @author firecoral
	 *
	 */
	public void delPeriod(String orgUUid,long starttime,long endtime,long weekstart){
		
		Statement stm = null;
		try {
			stm = conn.createStatement();
			String sql = "DELETE OFFICE_DUTY_PERIOD WHERE ORGUUID='"+orgUUid+"' AND "+starttime+"=PERIODSTART AND PERIODEND="+endtime;
			stm.executeUpdate(sql);
			
			long totalstart = weekstart + starttime;
			long totalend = weekstart + endtime;
			String sql2 = "DELETE OFFICE_DUTY WHERE STARTTIME>="+totalstart+" AND MOD((STARTTIME-"+totalstart+"),86400000)=0 AND ENDTIME<="+(totalend+7*86400000)+" AND MOD((ENDTIME-"+totalend+"),86400000)=0";
			stm.executeUpdate(sql2);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(stm!=null){
				try {
					stm.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * �ж���ĳһ�������Ƿ��м�¼������У��������һ�ܼ�¼�е�ֵ�������������TRUE�����򷵻�FALSE
	 * @author firecoral
	 *
	 */
	public boolean isHaveRecord(String dutyName,String orgUUid,long startdate,long enddate){
		
		boolean ishave = false;
		
		OfficeDutyDAO dao = new OfficeDutyDAO();
		dao.setWhereClause("ORGUUID='"+orgUUid+"' AND "+startdate+"<=STARTTIME AND ENDTIME<="+enddate);
		
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao); 
		
		Statement stm = null;
		try {
			List list = factory.find(new OfficeDutyVO());
			if(list.size()>0){
				stm = conn.createStatement();
				String sql = "UPDATE OFFICE_DUTY SET DUTYNAME='"+dutyName+"' WHERE ORGUUID='"+orgUUid+"' AND "+startdate+"<=STARTTIME AND ENDTIME<="+enddate;
				stm.executeUpdate(sql);
				//stm.close();
				ishave = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(stm!=null){
				try {
					stm.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return ishave;
	}
	
	/**
	 * �õ�ĳһ�ܵ�ֵ�����
	 * @author firecoral
	 *
	 */
	public String getDutyName(String orgUUid,long wstart){
		long wend = wstart+7*86400000;
		String dutyName = "";
		OfficeDutyDAO dao = new OfficeDutyDAO();
		dao.setWhereClause("ORGUUID='"+orgUUid+"' AND "+wstart+"<=STARTTIME AND ENDTIME<="+wend);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List nlist = factory.find(new OfficeDutyVO());
			if(nlist.size()>0){
				OfficeDutyVO ofdVO = (OfficeDutyVO)nlist.get(0);
				dutyName = ofdVO.getDutyname();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dutyName;
	}
	
	/**
	 * �õ���¼�û����ܿ�����һ�������еĵ绰��¼
	 * @author firecoral
	 *
	 */
	public List GetPhone(String OrgUUid,String personUUid) {
		List phoneList = new ArrayList();
		StringBuffer sql = new StringBuffer();
//		sql.append(" ORGUUID='"+OrgUUid+"'");
		sql.append(" USERUUID='"+personUUid+"'");
		sql.append(" AND (ISPARTTIME='"+PhoneBookConfig.NOPARTTIME+"' OR ISPARTTIME IS NULL)");

		PhoneInfoDAO dao = new PhoneInfoDAO();
		dao.setWhereClause(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try{
			phoneList = factory.find(new PhoneInfoVO());
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return phoneList;
	}
	
	/**
	 * �õ���ǰ��֯��ֵ��绰
	 * @author firecoral
	 *
	 */
	public String GetDutyPhone(String OrgUUid) {
		List phoneList = new ArrayList();
        String phone = "";
		PhoneInfoDAO dao = new PhoneInfoDAO();
		dao.setWhereClause("ORGUUID='"+OrgUUid+"'"+" AND FUNCTION='ֵ��绰'");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try{
			phoneList = factory.find(new PhoneInfoVO());
			if(phoneList.size()>0){
				PhoneInfoVO VO = (PhoneInfoVO)phoneList.get(0);
				phone = VO.getOfficephone();
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return phone;
	}
	
	/**
	 * �ɵ�ǰ�û�����֯ID�õ������ھֵ�UUID
	 * @author firecoral
	 *
	 */
	public String getJuOrguuid(String orguuid){
		String juOrguuid = "";
		SysOrgDAO sdao = new SysOrgDAO();
		sdao.setOrguuid(orguuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(sdao);
		try {
			List list = factory.find(new SysOrgVO());
			if(list!=null&&list.size()>0){
				SysOrgVO svo = (SysOrgVO)list.get(0);
                if(svo.getOrglevel().intValue()!=3){
                	String pOrguuid = svo.getParentorguuid();
                	juOrguuid = this.getJuOrguuid(pOrguuid);
                }else{
                	juOrguuid = svo.getOrguuid();
                }
			}
		} catch (DAOException e) {			
			e.printStackTrace();
		}
		return juOrguuid;
	}
	
	/**
	 * ���һ����ֵ����Ȩ�޼�¼
	 * @author firecoral
	 *
	 */
	public void addDutyRight(String orguuid,String personuuid){
		OfficeDutyRightDAO dao = new OfficeDutyRightDAO();
		dao.setOrguuid(orguuid);
		dao.setPersonuuid(personuuid);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ��һ����ֵ����Ȩ�޼�¼
	 * @author firecoral
	 *
	 */
	public void delDutyRight(String orguuid,String personuuid){
		OfficeDutyRightDAO dao = new OfficeDutyRightDAO();
		dao.setOrguuid(orguuid);
		dao.setPersonuuid(personuuid);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����ֵ����Ȩ�޼�¼�ı��ܹ�PERSONUUID�õ�ORGUUID
	 * @author firecoral
	 *
	 */
	public String getOrgRight(String personuuid){
		String orgUUid = "";
		OfficeDutyRightDAO dao = new OfficeDutyRightDAO();
		dao.setPersonuuid(personuuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find(new OfficeDutyRightVO());
			if(list.size()>0){
				orgUUid = ((OfficeDutyRightVO)list.get(0)).getOrguuid();
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return orgUUid;
	}
	
	/**
	 * 	�ж��Ƿ�ΪӦ�ý�ɫ
	 * @author firecoral
	 *
	 */
	public boolean hasRole(String personuuid,String roleCode){
		try{
			boolean pd = false;
			Person p=new Person();
			p.setUuid(personuuid);
			RightManager r=RightManager.getInstance();
			List roleList=r.findAppRolesByPerson(p);
			for(int i=0;i<roleList.size();i++){
				AppRole ap = (AppRole)roleList.get(i);
				if(roleCode.equals(ap.getRolecode())){
					pd = true;
					break;
				}
			}
			return pd;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 	���һ��Ӧ�ý�ɫ
	 * @author firecoral
	 *
	 */	
	public void dispatchRole(String personuuid,String roleCode) throws RightException{
		Role role=new Role();
		role.setRolecode(roleCode);
		role=role.getInstanceByKey();
		Person p=new Person();
		try{
			p.setUuid(personuuid);
		} catch(Exception e){
			e.printStackTrace();
		}
		List personlist=new ArrayList();
		personlist.add(p);
		role.addPersons(personlist);
	}
	/**
	 * 	ɾ��һ��Ӧ�ý�ɫ
	 * @author firecoral
	 *
	 */	
	public void deleteRolePerson(String personuuid,String roleCode){
		try{
			Role role=new Role();
			role.setRolecode(roleCode);
			role=role.getInstanceByKey();
			Person p=new Person();
			p.setUuid(personuuid);
			List personlist=new ArrayList();
			personlist.add(p);
			role.removePersons(personlist);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}

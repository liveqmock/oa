/*
 * Created on 2004-8-12
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.meeting.admin;

import java.text.SimpleDateFormat;

import com.icss.oa.meeting.vo.MeetingPutVO;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Person;

/**
 * @author Administrator
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductMeetingString {
	
	private MeetingPutVO vo;
	private StringBuffer RetString = new  StringBuffer();
	EntityManager entityManager ;//= EntityManager.getInstance();
	
	
	public ProductMeetingString(MeetingPutVO vo,EntityManager entityManager){
		this.vo = vo;
		this.entityManager = entityManager;
		}
	
	public String getRetString() throws EntityException{
		
		RetString.append(vo.getMeetingName());  
		RetString.append(" ");
		RetString.append(vo.getMeetingRoom()); /* 
		RetString.append(" ");
		RetString.append(vo.getMeetingPutdep());  
		RetString.append(" ");
		Person person = entityManager.findPersonByUuid(vo.getMeetingPutperson());
		RetString.append(person.getFullName()); */
		RetString.append(" ");
		RetString.append(new SimpleDateFormat("MM-dd").format(new java.util.Date(vo.getStarttimeday().longValue())));
		RetString.append(" "+vo.getStartimehour()+"--");
		RetString.append(new SimpleDateFormat("MM-dd").format(new java.util.Date(vo.getEndtimeday().longValue())));
		RetString.append(" "+vo.getEndtimehour());
		
		return RetString.toString();
	}
	
	
	

}

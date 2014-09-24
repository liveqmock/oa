/*
 * Created on 2004-9-18
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.api;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MsgContent {
	private String phone;
	private Integer code;
	private String content;
	private Long date;
	private int mode;
	public MsgContent(String _phone,Integer _code,String _content,Long _date,int _mode){
		phone=_phone;
		code=_code;
		content=_content;
		date=_date;
		mode=_mode;
	}
	public String getPhone(){
		return phone;
	}
	public Integer getCode(){
		return code;
	}
	public String getContent(){
		return content;
	}
	public Long getDate(){
		return date;
	}
	public int getMode(){
		return mode;
	}
}

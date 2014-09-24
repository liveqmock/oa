/*
 * 创建日期 2005-8-19
 */
package com.icss.common.intendwork;

/**
 * 待办工作值对象
 * 
 * @author YANGYANG
 * 2005-8-19 11:24:06
 */
public class IntendWorkVO {
	
	public static final int CODE_RED = 0;
	public static final int CODE_YELLOW = 1;
	
	public static final String[] LEVELS = new String[] {
		"重要","一般",
	};
	
	//子系统ID
	private String sysid;
	//待办工作标题
	private String title;
	//链接指向的URL（也可以在URL中附带参数）
	private String url;
	//待办工作提示的时间
	private String time;
	//重要等级
	private int level;
	
	
	/**
	 * 获取显示信息
	 */
	public String toString(){
		String display = "<font color=red >["+LEVELS[this.getLevel()]+"][全社考核]</font>" + this.getTitle() + "("+this.getTime()+")";
		return display;
	}


	/**
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return
	 */
	public String getSysid() {
		return sysid;
	}

	/**
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param i
	 */
	public void setLevel(int i) {
		level = i;
	}

	/**
	 * @param string
	 */
	public void setSysid(String string) {
		sysid = string;
	}

	/**
	 * @param string
	 */
	public void setTime(String string) {
		time = string;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}




}

/*
 * �������� 2005-8-19
 */
package com.icss.common.intendwork;

/**
 * ���칤��ֵ����
 * 
 * @author YANGYANG
 * 2005-8-19 11:24:06
 */
public class IntendWorkVO {
	
	public static final int CODE_RED = 0;
	public static final int CODE_YELLOW = 1;
	
	public static final String[] LEVELS = new String[] {
		"��Ҫ","һ��",
	};
	
	//��ϵͳID
	private String sysid;
	//���칤������
	private String title;
	//����ָ���URL��Ҳ������URL�и���������
	private String url;
	//���칤����ʾ��ʱ��
	private String time;
	//��Ҫ�ȼ�
	private int level;
	
	
	/**
	 * ��ȡ��ʾ��Ϣ
	 */
	public String toString(){
		String display = "<font color=red >["+LEVELS[this.getLevel()]+"][ȫ�翼��]</font>" + this.getTitle() + "("+this.getTime()+")";
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

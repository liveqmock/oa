/*
 * Created on 2004-8-23
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.config;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhoneBookConfig {
	//是否是兼职
	public static String NOPARTTIME = "0";
    public static String ISPARTTIME = "1";
    //执行哪一种同步
    public static int ADDFLAG = 0,UPDATEFLAG = 1,DELETEFLAG = 2;
    //执行哪一个方法
    public static String[] METHOD_NAME = new String[]{"addPersonInfo","updatePersonInfo","delPersonInfo"};
}

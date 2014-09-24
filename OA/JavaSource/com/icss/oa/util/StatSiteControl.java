/*
 * Created on 2004-7-15
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.util;

/**
 * @author Administrator
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StatSiteControl {
	
	private static String starsitecontrol="1";
	private static String statsitejndi;
	private static String appname;
	
	public static void InitControl(String str){
		   starsitecontrol = str;
		}
	
	public static String geSwitch(){
		return starsitecontrol;
		}
	
	public static void Initjndi(String str){
		statsitejndi=str;
		}
	public static String gejndi(){
		return statsitejndi;
		}
	
	public static void setAppname(String appname1) {
		appname = appname1;
	}
	public static String getAppname() {
		return appname;
	}
}

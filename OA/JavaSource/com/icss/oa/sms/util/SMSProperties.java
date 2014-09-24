package com.icss.oa.sms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SMSProperties {
	public static String sendAccount = readValue("sendAccount");
	public static String senderNumber = readValue("senderNumber");
	public static String ip = readValue("ip");
	public static String port = readValue("port");
	public static String username = readValue("username");
	public static String password = readValue("password");
	public static String contentpath = readValue("contentpath");
	public static String flagpath = readValue("flagpath");
	public static String upcontent = readValue("upcontent");
	public static String upflag = readValue("upflag");

	public static String readValue(String key) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			Class theClass = SMSProperties.class;
			in = theClass.getResourceAsStream("/ftpConfig.properties");
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			System.out.println("############## È¡µÃ ftpConfig.properties ³ö´í");
			e.printStackTrace();
			return null;
		} finally {
			try {
				in.close();
				in = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
/*
 * Created on 2004-5-27
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.util;

//import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author yniu
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatPropertyManager {
	private static final String CONFIG = "/WEB-INF/statsite";
	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(CONFIG);
	/**
	 * 读取默认的config.properties文件
	 * @param key 
	 * @return value
	 */
	public static String getString(String key) {
		try {
			return StringUtility.toChinese(RESOURCE_BUNDLE.getString(key));
		} catch (MissingResourceException e) {
			return StringUtility.toChinese('!' + key + '!');
		}
	}
	/**
	 * 读取指定为rb.properties文件
	 * @param rb property 文件，文件名为 rb.properties
	 * @param key 
	 * @return value
	 */
	public static String getRB(String rb, String key) {
		return StringUtility.toChinese(ResourceBundle.getBundle(rb).getString(key));
	}
}

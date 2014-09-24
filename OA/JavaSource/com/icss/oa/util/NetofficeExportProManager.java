/*
 * Created on 2004-6-18
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class NetofficeExportProManager {
	
	private static final String CONFIG = "/WEB-INF/export";
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
}

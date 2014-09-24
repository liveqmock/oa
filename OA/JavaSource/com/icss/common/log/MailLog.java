/*
 * 创建日期 2005-7-14
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.common.log;

import com.icss.common.log.filelog.FileLogFactory;

/**
 * @author lizb
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class MailLog {
	
	
	private static final int ONOFF = 1;
	
	public static void write(String str) {
		
		if (ONOFF == 1) {
			
			LogFactory factory = new FileLogFactory("MailPathLog");
			Log log = factory.newInstance(Log.class, "");
			log.info(str);
		}
	}
	
}

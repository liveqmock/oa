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
public class ConnLog {
	private static final int ONOFF = 1;
	private static final String LOG_START = " [打开连接] <";
	private static final String LOG_END = " [关闭连接] <";
	public static void open(String str) {
		if (ONOFF == 1) {
			LogFactory factory = new FileLogFactory("FileConn");
			Log log = factory.newInstance(Log.class, "");
			log.debug(LOG_START + str + ">");
		}
	}
	public static void close(String str) {
		if (ONOFF == 1) {
			LogFactory factory = new FileLogFactory("FileConn");
			Log log = factory.newInstance(Log.class, "");
			log.debug(LOG_END + str + ">");
		}
	}

}

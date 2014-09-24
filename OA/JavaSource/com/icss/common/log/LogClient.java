/*
 * 创建日期 2005-4-8
 */
package com.icss.common.log;

import com.icss.common.log.filelog.FileLogFactory;
import com.icss.j2ee.util.Globals;

/**
 * @author YANGYAMG
 */
public class LogClient {

	public LogClient(){
		Globals.resourceOneHome = "C:\\ResourceOneHome";
		LogFactory factory = new FileLogFactory();
		Log log = factory.newInstance(this.getClass());

		log.warn("HAHAHA.............");
		log.debug("KAKAKA.............");
		
	}

	public static void main(String[] args) {
		LogClient c = new LogClient();
	}
}

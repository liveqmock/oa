/*
 * 创建日期 2005-4-8
 */
package com.icss.common.log.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.icss.common.log.Log;

/**
 * @author YANGYAMG
 */
public class LogImpl implements Log {

	public final Class classe;
	private SimpleDateFormat format;
	
	public LogImpl(Class classe) {
		this.classe = classe;
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public final void debug(Object object) {
		if (DEBUG) {
			log(DEBUG_LEVEL, object);
		}
	}

	public final void info(Object object) {
		if (INFO) {
			log(INFO_LEVEL, object);
		}
	}

	public final void warn(Object object) {
		if (WARNING) {
			log(WARNING_LEVEL, object);
		}
	}

	public final void message(Object object) {
		if (MESSAGE) {
			log(MESSAGE_LEVEL, object);
		}
	}

	public final void error(Object object, Throwable throwable) {
		log(ERROR_LEVEL, object);
		throwable.printStackTrace(System.out);
	}


	public void log(int level, Object object) {
		StringBuffer sb = new StringBuffer(30);
		sb.append(format.format(new Date()));
		sb.append(" ");
		sb.append(getObjectClass().getName());
		sb.append(" ");		
		sb.append(Log.LEVELS[level]);
		sb.append(" -   ");
		sb.append(object);		
		System.out.println(sb.toString());
	}

	public Class getObjectClass() {
		return classe;
	}

}

/*
 * 创建日期 2005-4-8
 */
package com.icss.common.log;

/**
 * @author YANGYAMG
 */
public interface Log {
	
	public static final boolean DEBUG = true;
	public static final boolean INFO = true;
	public static final boolean MESSAGE = true;
	public static final boolean WARNING = true;
	public static final int DEBUG_LEVEL = 0;
	public static final int INFO_LEVEL = 1;
	public static final int MESSAGE_LEVEL = 2;
	public static final int WARNING_LEVEL = 3;
	public static final int ERROR_LEVEL = 4;
	public static final String[] LEVELS = { "DEBUG", "INFO ", "MESSAGE", "WARN ", "ERROR" };
	public final String line = System.getProperty("line.separator"); 	//行分隔符

	public abstract void debug(Object object);
	public abstract void info(Object object);
	public abstract void warn(Object object);
	public abstract void message(Object object);
	public abstract void error(Object object, Throwable throwable);
	public abstract Class getObjectClass();
	
}

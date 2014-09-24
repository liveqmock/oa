/*
 * Created on 2005-4-9
 */
package com.icss.common.log.runnablelog;

import com.icss.common.log.Log;
import com.icss.common.log.LogFactory;
import com.icss.common.log.impl.LogImpl;


/**
 * @author YANGYANG
 * 2005-4-9 10:05:30
 */
public abstract class RunnableLogFactory implements LogFactory {
	
	private LogFactory factory;
	
	//ȡ�ÿ����첽ִ�е���־����
	public Log getRunnableLog(Log log) {
		return new RunnableLog(log);
	}
	
	public Log getDefaultLog(Class classe){
		return new LogImpl(classe);
	}
	
}



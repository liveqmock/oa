/*
 * Created on 2004-5-13
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.log.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LogSysTreeVO extends ValueObject {
	
	private LogSysVO logSysVO;
	//private boolean isShare;
	private boolean hasChild;
	public boolean isHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
	public LogSysVO getLogSysVO() {
		return logSysVO;
	}
	public void setLogSysVO(LogSysVO logSysVO) {
		this.logSysVO = logSysVO;
	}

	

}

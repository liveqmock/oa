/*
 * Created on 2005-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.util;

import com.icss.oa.phonebook.vo.PhoneInfoVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SynchPersonInterface{
	
	public abstract void addPersonInfo(PhoneInfoVO vo) throws Exception;
	
	public abstract void updatePersonInfo(PhoneInfoVO vo) throws Exception;
	
	public abstract void delPersonInfo(PhoneInfoVO vo) throws Exception;

}

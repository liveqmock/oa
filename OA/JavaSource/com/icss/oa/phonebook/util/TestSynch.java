/*
 * Created on 2005-1-13
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
public class TestSynch {
	public static void main(String[] args) {
		PhoneInfoVO vo  = new PhoneInfoVO();
		ExcuteSynch.getInstance().readConfig(vo,0);
	}
}

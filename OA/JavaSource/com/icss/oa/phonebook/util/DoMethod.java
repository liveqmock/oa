/*
 * Created on 2005-1-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.icss.oa.phonebook.vo.PhoneInfoVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DoMethod {
	
	public static void doMethod(PhoneInfoVO vo, String className,String methodName){
		
		try {
			Class clazz = Class.forName(className);
			SynchPersonInterface person = (SynchPersonInterface)clazz.newInstance();
			Method method = clazz.getMethod(methodName,new Class[]{PhoneInfoVO.class});
			Object[] args = new Object[]{vo};
			method.invoke(person,args);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
	

}

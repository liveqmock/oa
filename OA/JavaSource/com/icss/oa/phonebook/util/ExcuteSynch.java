/*
 * Created on 2005-1-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.util;

import java.util.Iterator;
import java.util.List;

import com.icss.oa.config.PhoneBookConfig;
import com.icss.oa.phonebook.vo.PhoneInfoVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExcuteSynch {
	
	public static ExcuteSynch synchRef;
	public synchronized static ExcuteSynch getInstance(){
		if(synchRef == null){
			synchRef = new ExcuteSynch();
		}
		return synchRef;
	}
	
	private ExcuteSynch(){		
	}
	
	public synchronized void readConfig(PhoneInfoVO vo,int methodtype){
		String methodName = "";
		for (int i = 0; i < PhoneBookConfig.METHOD_NAME.length; i++) {
			if(methodtype == i){
				methodName = PhoneBookConfig.METHOD_NAME[i];
			}
		}
		if(methodName.equals("")){
			throw new IllegalArgumentException("非法的参数类型");
		}
		//读配置文件
		List classlist;
		classlist = ReadConfigFile.getInstance().parseFile();
		//执行
		if(classlist!=null&classlist.size()>0){
			String className = "";
			Iterator itr = classlist.iterator();
			System.out.println("begin to call-back.......");
			while(itr.hasNext()){ //每次循环处理一个类
				className = (String)itr.next();
				System.out.println("begin to call-back class is:"+className+".......");
				DoMethod.doMethod(vo,className,methodName);
			}
			System.out.println("all call-back is end.......");
		}
	}

}

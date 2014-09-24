/*
 * Created on 2005-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.jdom.*;
import org.jdom.input.SAXBuilder;
/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReadConfigFile {
//	private static String xmlFile = "/WEB-INF/classes/SynchPersonInfoClass.xml";
	private static String xmlFile;
	private static final String CLASSNAME = "class-name";
	
	private static ReadConfigFile instance;
	public synchronized static ReadConfigFile getInstance(){
		if(instance == null){
			instance = new ReadConfigFile();
		}
		return instance;
		
	}
	
	private ReadConfigFile(){	
		xmlFile = "/WEB-INF/classes/SynchPersonInfoClass.xml";
	}
	
	public List parseFile(){
		System.out.println("begin to parse xml.......");
		List classlist = new ArrayList();
		File file = new File(xmlFile);
		InputStream fis = null;
//		try {
//			fis = new FileInputStream(file);
			fis = getClass().getClassLoader().getResourceAsStream(xmlFile);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
		Document saxDoc = null;
		SAXBuilder builder = new org.jdom.input.SAXBuilder();
		try {
			saxDoc = builder.build(fis);
			Element rootE = saxDoc.getRootElement(); //取得根元素
			if(rootE!=null){
				List maplist = rootE.getChildren(CLASSNAME);
				if(maplist!=null&&maplist.size()>0){
					Iterator itr = maplist.iterator();
					while(itr.hasNext()){
						Element onemap = (Element) itr.next();
						classlist.add(onemap.getText());
					}
				}
			}
			System.out.println("parse xml end.......");
		} catch (JDOMException e) {
			e.printStackTrace();
			return new ArrayList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classlist;
	}

}

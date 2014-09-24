package com.icss.oa.deletetempfile.handler;

/**
 * @author firecoral
 */
public class PathBean {
	
	static{
		pathBeanRef = new PathBean();
	}
	public static PathBean pathBeanRef;
	public /*synchronized(单重校验时使用)*/ static PathBean getInstance(){
		/*
		 //单重校验————效率较低 
		 if(pathBeanRef == null){
		 	pathBeanRef = new PathBean();
		 }
		 */
		/*
		//双重校验————有可能出现bug
		if(pathBeanRef == null){
			synchronized(pathBeanRef){
				if(pathBeanRef == null){
					pathBeanRef = new PathBean();
				}
			}
		}
		*/
		return pathBeanRef;
	}
	private String mailuploadpath = "";
	private String maildownloadpath = "";
	
	/**
	 * 私有构造方法
	 */
	private PathBean(){

	}
	
	public void setMailuploadpath(String string){
		mailuploadpath = string;
	}
	
	public String getMailuploadpath(){
		return mailuploadpath;
	}
	
	public void setMaildownloadpath(String string){
		maildownloadpath = string;
	}
	
	public String getMaildownloadpath(){
		return maildownloadpath;
	}

}

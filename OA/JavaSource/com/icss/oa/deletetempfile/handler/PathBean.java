package com.icss.oa.deletetempfile.handler;

/**
 * @author firecoral
 */
public class PathBean {
	
	static{
		pathBeanRef = new PathBean();
	}
	public static PathBean pathBeanRef;
	public /*synchronized(����У��ʱʹ��)*/ static PathBean getInstance(){
		/*
		 //����У�顪������Ч�ʽϵ� 
		 if(pathBeanRef == null){
		 	pathBeanRef = new PathBean();
		 }
		 */
		/*
		//˫��У�顪�������п��ܳ���bug
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
	 * ˽�й��췽��
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

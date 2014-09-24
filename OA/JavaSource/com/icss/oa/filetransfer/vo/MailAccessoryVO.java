/*
 * Created on 2004-11-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.vo;

import java.io.InputStream;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MailAccessoryVO {

	private InputStream in;
	private String filename;
	
	public void setIn(InputStream in){
		this.in = in;
		}
	
	public void setFilename(String filename){
		this.filename = filename;
	}
	
	public InputStream getIn(){
		return this.in;
	}
	
	public String getFilename(){
		return this.filename;
	}
}

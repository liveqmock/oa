/*
 * Created on 2004-11-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.deletetempfile.control;

import java.io.*;
import java.util.*;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.icss.oa.deletetempfile.handler.PathBean;

/**
 * @author firecoral
 *
 *  删除应用服务器上的暂存文件
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteTempFile implements Job{

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//删除Mail的暂存邮件（用于下载的）
		System.out.println("begin to delete mailtempfile........");
		PathBean pbean = PathBean.getInstance();
		String mailuppath = pbean.getMailuploadpath();
		if(!"".equals(mailuppath)){
            this.deleteFolder(mailuppath);
		}
		
		String maildownpath = pbean.getMaildownloadpath();
		if(!"".equals(maildownpath)){
            this.deleteFolder(maildownpath);
		}
	}
	
	//递归方式清空一个文件夹
	private void deleteFolder(String path){
		File f = new File(path);
		List filelist = new ArrayList();       
		String[] name = f.list();
		for (int i = 0; i < name.length; i++) {            
			File newfile = new File(f, name[i]);                      
			if (newfile.isDirectory()){
				deleteFolder(newfile.getPath());
				newfile.delete();
				System.out.println("成功删除一个文件夹！");
			}else{
				newfile.delete();
			}      
		}
	}

}

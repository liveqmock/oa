/*
 * 创建日期 2005-8-26
 */
package com.icss.oa.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.user.usermanage;

/**
 * @author YANGYANG
 * 2005-8-26 10:52:26
 */
public class MyTimer extends Timer {

	//12小时
	protected long interval = 1000 * 60 * 60 * 12 ;
	//	邮件服务器的IP地址
//    private String mailServerIp = "192.9.100.26";
    private String mailServerIp = "10.102.1.205";
    //  邮件服务器的域名
//    private String domain = "xinhua.com";
    private String domain = "oa.xinhua.org";
    
    //定时切换Map的备份类
    private Map map_back;

	/**
	 * 无参的构造函数
	 */
	public MyTimer() {
		//设置成daemon方式，以便整个程序结束时自动退出
		super(true);
	}

	/**
	 * 带参数的构造函数
	 *
	 * @param interval
	 */
	public MyTimer(long interval) {
		//设置成daemon方式，以便整个程序结束时自动退出
		super(true);
		this.interval = interval;
	}       


	public void start() {   
		// 调用execute()方法.
		
		//参数1	TimerTask定时任务
		//参数2	Timestamp启动时间
		//参数3	运行周期
		this.schedule(new TimerTask() {
			public void run() {
				execute();
			}
		}, new Timestamp(System.currentTimeMillis()), interval);
	}


	public void stop() {
		this.cancel();
	}

	/**
	 * 要定时执行的任务
	 */
	protected void execute() {
		// 调用garbage collector.
		//System.gc();
		//刷新缓存
//		System.out.println("dddddddddddddddddd  "+getCurrentTime());
	    
	    Map map = getTimeList();
		UserManagerCache.getInstance().setMap(map);
		
	}
	
	/**
	 * 返回当前：年月日 时分秒，例如：2004-07-15 15:03:25
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	
	public Map getTimeList(){
	    //初始化集合,包括全部的邮件用户ID
        usermanage user=new usermanage();
        String Array_user[] = null;
        String rootpath1 = null;
        
        try {
            user.connect(mailServerIp,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
            Array_user = user.ListAlluserByUnit(domain,"");
        } catch (LdapException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < Array_user.length; i++) {
	        try {
                //根据用户ID＆域名获取这个用户的根路径
                rootpath1=user.getUserRootPath(Array_user[i],domain);
		        //把用户ID以及根路径保存到映射中
		        map_back.put(Array_user[i],rootpath1);
            } catch (LdapException e1) {
                e1.printStackTrace();
            } 
	        
        }
        
        return map_back;
	    
	}
	
	
	

}

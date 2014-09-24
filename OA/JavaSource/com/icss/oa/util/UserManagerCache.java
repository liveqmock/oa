/*
 * Created on 2005-8-25
 */
package com.icss.oa.util;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Map;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.user.usermanage;

import test.MailPath.Mailhandler;
import test.MailPath.getConnetion;

import com.icss.common.log.MailLog;

/**
 * @author SUNCHUANTING
 * 2005-8-25 10:05:44
 */
public class UserManagerCache implements Runnable{
   
    
    private static UserManagerCache cacheRef;
//  数据映射
    //key = usrname		用户ID
    //value = rootpath	这个用户的根路径
    private Map map;
    
	private static Connection conn = null;
	
    
    public synchronized static UserManagerCache getInstance(){
        if(cacheRef==null){
            cacheRef = new UserManagerCache();
        }
        return cacheRef;
    }
    
    //邮件服务器的IP地址
    private String mailServerIp = PropertyManager.getProperty("archivesip");
    //邮件服务器的域名
    private String domain = PropertyManager.getProperty("archivesdomain");
    
    
    private UserManagerCache(){
        map = new Hashtable();
        //从配置文件获取域名
        domain = PropertyManager.getProperty("archivesdomain");
		
		conn = getConnetion.getDBConn();
    }
    
    private String []usernames;
    
    public void init(String []usrnames){
        for (int i = 0; i < usrnames.length; i++) {
            String usrname = usrnames[i];
            try {
                System.out.println(i+" ="+usrname);
				MailLog.write("开始初始化 " +usrname +"的 内存信息！");
                this.getUserRootPath(usrname);
                
            } catch (LdapException e) {
                e.printStackTrace();
                
            }
        }
    }
    
    
    /**
     * 获取指定的用户对应的根路径
     * @param usrname
     * @return
     * @throws LdapException
     */
    public String getUserRootPath(String usrname) throws LdapException{
        
        String rootpath = "";
        
        synchronized(map){
            if(map.containsKey(usrname)){
                //缓存中存在这个用户对应的根路径,缓存中获取路径值
                rootpath = (String)map.get(usrname);
				MailLog.write(usrname+"的路径在memory中找到是："+rootpath);
            }else{
    		 	usermanage user=new usermanage();
    		    try {
    		    	
					Mailhandler handler = null;
    		       
				   try{
					   handler = new Mailhandler(conn);
				   }catch(Exception e){
					   e.printStackTrace();
				   }finally{
					   conn = getConnetion.getDBConn();
					   handler = new Mailhandler(conn);
				   }
    		        
					String path = handler.QueryMailPath(usrname).getUser_path();
					
					MailLog.write(usrname+"  的在数据库中的路径为 ："+path);
					
					if(path!=null){
						rootpath = path;
                        
					}else{
						//取得到LDAP服务的链接
						System.out.println("usrname = "+usrname);
						MailLog.write(usrname+"  在数据库中没有路径的记录");
						user.connect(this.getMailServerIp(),389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
	                    
						//根据用户ID＆域名获取这个用户的根路径
						rootpath=user.getUserRootPath(usrname,this.getDomain());
						System.out.println("rootpath = "+rootpath);
						MailLog.write(usrname+" 在LDAP中的记录是 ："+rootpath+"  ;并插入的数据库记录中");
						handler.InsertMailPath(usrname,rootpath);
					}
						//把用户ID以及根路径保存到映射中
						map.put(usrname,rootpath);
    		        
                    
                } catch (LdapException e) {
                    e.printStackTrace();
                    throw e;
                } finally {
					
                    if(user!=null){
                        user.disConnect();
                    }
                    
//					getConnetion.CloseConnection();
                }

            }
            
        }
        return rootpath;
    }
    /**
     * @return Returns the domain.
     */
    public String getDomain() {
        return domain;
    }
    /**
     * @param domain The domain to set.
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }
    /**
     * @return Returns the mailServerIp.
     */
    public String getMailServerIp() {
        return mailServerIp;
    }
    /**
     * @param mailServerIp The mailServerIp to set.
     */
    public void setMailServerIp(String mailServerIp) {
        this.mailServerIp = mailServerIp;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        this.init(this.usernames);
        
    }
    /**
     * @param usernames The usernames to set.
     */
    public void setUsernames(String[] usernames) {
        this.usernames = usernames;
    }
	/**
	 * @return Returns the map.
	 */
	public Map getMap() {
	    return map;
	}

	/**
	 * @param map The map to set.
	 */
	public void setMap(Map map) {
	    synchronized (map) {
			System.out.println("ssssss map.clear()= ");
	    	this.map.clear();
		    this.map = map;
        }
	}
}




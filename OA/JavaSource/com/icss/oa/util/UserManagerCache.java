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
//  ����ӳ��
    //key = usrname		�û�ID
    //value = rootpath	����û��ĸ�·��
    private Map map;
    
	private static Connection conn = null;
	
    
    public synchronized static UserManagerCache getInstance(){
        if(cacheRef==null){
            cacheRef = new UserManagerCache();
        }
        return cacheRef;
    }
    
    //�ʼ���������IP��ַ
    private String mailServerIp = PropertyManager.getProperty("archivesip");
    //�ʼ�������������
    private String domain = PropertyManager.getProperty("archivesdomain");
    
    
    private UserManagerCache(){
        map = new Hashtable();
        //�������ļ���ȡ����
        domain = PropertyManager.getProperty("archivesdomain");
		
		conn = getConnetion.getDBConn();
    }
    
    private String []usernames;
    
    public void init(String []usrnames){
        for (int i = 0; i < usrnames.length; i++) {
            String usrname = usrnames[i];
            try {
                System.out.println(i+" ="+usrname);
				MailLog.write("��ʼ��ʼ�� " +usrname +"�� �ڴ���Ϣ��");
                this.getUserRootPath(usrname);
                
            } catch (LdapException e) {
                e.printStackTrace();
                
            }
        }
    }
    
    
    /**
     * ��ȡָ�����û���Ӧ�ĸ�·��
     * @param usrname
     * @return
     * @throws LdapException
     */
    public String getUserRootPath(String usrname) throws LdapException{
        
        String rootpath = "";
        
        synchronized(map){
            if(map.containsKey(usrname)){
                //�����д�������û���Ӧ�ĸ�·��,�����л�ȡ·��ֵ
                rootpath = (String)map.get(usrname);
				MailLog.write(usrname+"��·����memory���ҵ��ǣ�"+rootpath);
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
					
					MailLog.write(usrname+"  �������ݿ��е�·��Ϊ ��"+path);
					
					if(path!=null){
						rootpath = path;
                        
					}else{
						//ȡ�õ�LDAP���������
						System.out.println("usrname = "+usrname);
						MailLog.write(usrname+"  �����ݿ���û��·���ļ�¼");
						user.connect(this.getMailServerIp(),389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
	                    
						//�����û�ID��������ȡ����û��ĸ�·��
						rootpath=user.getUserRootPath(usrname,this.getDomain());
						System.out.println("rootpath = "+rootpath);
						MailLog.write(usrname+" ��LDAP�еļ�¼�� ��"+rootpath+"  ;����������ݿ��¼��");
						handler.InsertMailPath(usrname,rootpath);
					}
						//���û�ID�Լ���·�����浽ӳ����
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




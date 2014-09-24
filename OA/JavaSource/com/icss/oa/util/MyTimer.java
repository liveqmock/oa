/*
 * �������� 2005-8-26
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

	//12Сʱ
	protected long interval = 1000 * 60 * 60 * 12 ;
	//	�ʼ���������IP��ַ
//    private String mailServerIp = "192.9.100.26";
    private String mailServerIp = "10.102.1.205";
    //  �ʼ�������������
//    private String domain = "xinhua.com";
    private String domain = "oa.xinhua.org";
    
    //��ʱ�л�Map�ı�����
    private Map map_back;

	/**
	 * �޲εĹ��캯��
	 */
	public MyTimer() {
		//���ó�daemon��ʽ���Ա������������ʱ�Զ��˳�
		super(true);
	}

	/**
	 * �������Ĺ��캯��
	 *
	 * @param interval
	 */
	public MyTimer(long interval) {
		//���ó�daemon��ʽ���Ա������������ʱ�Զ��˳�
		super(true);
		this.interval = interval;
	}       


	public void start() {   
		// ����execute()����.
		
		//����1	TimerTask��ʱ����
		//����2	Timestamp����ʱ��
		//����3	��������
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
	 * Ҫ��ʱִ�е�����
	 */
	protected void execute() {
		// ����garbage collector.
		//System.gc();
		//ˢ�»���
//		System.out.println("dddddddddddddddddd  "+getCurrentTime());
	    
	    Map map = getTimeList();
		UserManagerCache.getInstance().setMap(map);
		
	}
	
	/**
	 * ���ص�ǰ�������� ʱ���룬���磺2004-07-15 15:03:25
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	
	public Map getTimeList(){
	    //��ʼ������,����ȫ�����ʼ��û�ID
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
                //�����û�ID��������ȡ����û��ĸ�·��
                rootpath1=user.getUserRootPath(Array_user[i],domain);
		        //���û�ID�Լ���·�����浽ӳ����
		        map_back.put(Array_user[i],rootpath1);
            } catch (LdapException e1) {
                e1.printStackTrace();
            } 
	        
        }
        
        return map_back;
	    
	}
	
	
	

}

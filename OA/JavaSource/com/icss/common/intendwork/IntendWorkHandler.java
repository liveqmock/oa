/*
 * �������� 2005-8-19
 */
package com.icss.common.intendwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.icss.common.config.intendwork.IntendWorkConfigManager;
import com.icss.common.config.intendwork.IntendWorkEntity;

/**
 * ���칤��������
 * 
 * @author YANGYANG
 * 2005-8-19 13:01:50
 */
public class IntendWorkHandler {
	
	public static IntendWorkHandler newInstance(){
		return new IntendWorkHandler();
	}
	
	//��½�û�personuuid
	private String personuuid;
	
	/**
	 * ȡ�ô��칤����Ϣ�ļ���
	 * 
	 * @return	����IntendWorkVO����ļ���
	 * @throws IOException
	 */
	public List getIntendWorks() throws IOException{
			
		List intendWorks = new ArrayList();

		try {
			//ȡ��IntendWorkConfig.xml�����ļ��е���Ŀ�ļ��ϣ�����IntendWorkEntity���͵Ķ���
			List entitys = IntendWorkConfigManager.getInstance().getIntendWorkEntitys();
			
			System.out.println("****** IntendWorkHandler.getIntendWorks() entitys.size() = " + entitys.size());
			
			//�������е�������Ϣ
			for (int i = 0; i < entitys.size(); i++) {
					
				//ȡ�õ�ǰһ��������Ϣ��Ŀ
				IntendWorkEntity entity = (IntendWorkEntity)entitys.get(i);
				//ȡ��������Ŀ�е������Ϣ
				String sysname = entity.getSysName();		//��ϵͳ����
				String sysid = entity.getSysId();			//��ϵͳID
				String classname = entity.getClassName();	//��ȡ���칤�����ϵ�ʵ���������
				
				
				//������칤�������Ķ���
				IntendWorkContext context = new IntendWorkContext();
				//�����������е����ԣ���ϵͳID
				context.setAttribute(IntendWorkContext.SYSID,sysid);
				//�����������е����ԣ�ƽ̨�û���UUID
				context.setAttribute(IntendWorkContext.PERSONUUID,personuuid);
				
				System.out.println("****** IntendWorkHandler.getIntendWorks() sysname = " + sysname);
				System.out.println("****** IntendWorkHandler.getIntendWorks() sysid = " + sysid);
				System.out.println("****** IntendWorkHandler.getIntendWorks() classname = " + classname);
				System.out.println("****** IntendWorkHandler.getIntendWorks() personuuid = " + personuuid);
				
				
				try {
					Class intendWorkClass = Class.forName(classname);
					
					if(IntendWorkManager.class.isAssignableFrom(intendWorkClass)){
						//���intendWorkClass��IntendWorkManager��ʵ����
						
						IntendWorkManager intendWorkManager = (IntendWorkManager)intendWorkClass.newInstance();
						
						//������ϵͳID��ȡIntendWorkVO����ļ���
						List currIntendWorks = intendWorkManager.getIntendWorks(context);
						
						//�ѵ�ǰ��һ����칤����Ϣ���뵽ȫ�����칤����Ϣ��
						intendWorks.addAll(currIntendWorks);

					}
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IntendWorkException e) {
					e.printStackTrace();
				}
				
				
				
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}	
			
		System.out.println("****** IntendWorkHandler.getIntendWorks() intendWorks.size() = " + intendWorks.size());	
			
		return intendWorks;	
			
	}
	
	
	/**
	 * @param string
	 */
	public void setPersonuuid(String string) {
		personuuid = string;
	}

}



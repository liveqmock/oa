/*
 * 创建日期 2005-8-19
 */
package com.icss.common.intendwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.icss.common.config.intendwork.IntendWorkConfigManager;
import com.icss.common.config.intendwork.IntendWorkEntity;

/**
 * 待办工作处理类
 * 
 * @author YANGYANG
 * 2005-8-19 13:01:50
 */
public class IntendWorkHandler {
	
	public static IntendWorkHandler newInstance(){
		return new IntendWorkHandler();
	}
	
	//登陆用户personuuid
	private String personuuid;
	
	/**
	 * 取得待办工作信息的集合
	 * 
	 * @return	返回IntendWorkVO对象的集合
	 * @throws IOException
	 */
	public List getIntendWorks() throws IOException{
			
		List intendWorks = new ArrayList();

		try {
			//取得IntendWorkConfig.xml配置文件中的条目的集合，返回IntendWorkEntity类型的对象
			List entitys = IntendWorkConfigManager.getInstance().getIntendWorkEntitys();
			
			System.out.println("****** IntendWorkHandler.getIntendWorks() entitys.size() = " + entitys.size());
			
			//遍历所有的配置信息
			for (int i = 0; i < entitys.size(); i++) {
					
				//取得当前一个配置信息条目
				IntendWorkEntity entity = (IntendWorkEntity)entitys.get(i);
				//取得配置条目中的相关信息
				String sysname = entity.getSysName();		//子系统名称
				String sysid = entity.getSysId();			//子系统ID
				String classname = entity.getClassName();	//获取待办工作集合的实现类的名称
				
				
				//构造待办工作上下文对象
				IntendWorkContext context = new IntendWorkContext();
				//设置上下文中的属性：子系统ID
				context.setAttribute(IntendWorkContext.SYSID,sysid);
				//设置上下文中的属性：平台用户的UUID
				context.setAttribute(IntendWorkContext.PERSONUUID,personuuid);
				
				System.out.println("****** IntendWorkHandler.getIntendWorks() sysname = " + sysname);
				System.out.println("****** IntendWorkHandler.getIntendWorks() sysid = " + sysid);
				System.out.println("****** IntendWorkHandler.getIntendWorks() classname = " + classname);
				System.out.println("****** IntendWorkHandler.getIntendWorks() personuuid = " + personuuid);
				
				
				try {
					Class intendWorkClass = Class.forName(classname);
					
					if(IntendWorkManager.class.isAssignableFrom(intendWorkClass)){
						//如果intendWorkClass是IntendWorkManager的实现类
						
						IntendWorkManager intendWorkManager = (IntendWorkManager)intendWorkClass.newInstance();
						
						//根据子系统ID获取IntendWorkVO对象的集合
						List currIntendWorks = intendWorkManager.getIntendWorks(context);
						
						//把当前的一组待办工作信息加入到全部待办工作信息中
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



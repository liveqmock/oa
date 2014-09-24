/*
 * 创建日期 2005-8-19
 */
package com.icss.common.intendwork;

import java.util.List;

/**
 * 待办工作管理器接口
 * 
 * 实现了这个接口的类有：
 * com.icss.common.intendwork.examine.ExamineIntendWorkManager	（考核待办工作实现类）
 * 
 * @author YANGYANG
 * 2005-8-19 11:16:54
 */
public interface IntendWorkManager {
	
	/**
	 * 根据上下文对象获取待办工作对象的集合
	 * 上下文中包括：
	 * 子系统ID
	 * 当前用户的信息
	 * 
	 * @param 		上下文对象
	 * @return		返回IntendWorkVO对象的集合
	 */
	public List getIntendWorks(IntendWorkContext context) throws IntendWorkException;
	
}

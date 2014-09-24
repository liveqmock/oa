/*
 * 创建日期 2005-8-19
 */
package com.icss.common.intendwork.examine;

import java.util.ArrayList;
import java.util.List;

import com.icss.common.intendwork.IntendWorkContext;
import com.icss.common.intendwork.IntendWorkException;
import com.icss.common.intendwork.IntendWorkManager;
import com.icss.common.intendwork.IntendWorkVO;
import com.icss.starflow.sdk.WmActivityDefinition;
import com.icss.starflow.sdk.WmException;
import com.icss.starflow.sdk.WmFactory;
import com.icss.starflow.sdk.WmProcessInstance;
import com.icss.starflow.sdk.WmWorkItem;
import com.icss.starflow.sdk.WmWorkItemHandler;
import com.icss.starflow.sdk.WorkItemConditionEx;
import com.icss.starflow.sdk.condition.WorkItemCondition;
import com.icss.starflow.sdk.extension.AppData;

/**
 * 考核系统获取待办工作项的实现类
 * 
 * @author YANGYANG
 * 2005-8-19 11:17:17
 */
public class ExamineIntendWorkManager implements IntendWorkManager {

	/**
	 * 根据子系统ID获取这个子系统的待办工作对象的集合
	 * @param sysid 子系统ID
	 * @return	返回IntendWorkVO对象的集合
	 */
	public List getIntendWorks(IntendWorkContext context) throws IntendWorkException {

		//构造IntendWorkVO对象的集合
		List intendWorks = new ArrayList();

		//从上下文中获取子系统ID
		String sysid = (String) context.getAttribute(IntendWorkContext.SYSID);
		//从上下文中获取平台登陆用户的personuuid
		String personuuid = (String) context.getAttribute(IntendWorkContext.PERSONUUID);

		System.out.println("###### ExamineIntendWorkManager.getIntendWorks() sysid = " + sysid);
		System.out.println("###### ExamineIntendWorkManager.getIntendWorks() personuuid = " + personuuid);

		try {
			//工作流查询条件
			WorkItemConditionEx condition = new WorkItemConditionEx();

			//设置查询条件
			condition.setPartyId(personuuid); //参与人ID——当前用户uuid（用于获取当前用户的待办工作）
			condition.setCurrentStates(WorkItemCondition.LIVE_STATES); //工作流状态（活动的工作流）
			condition.addOrderBy("fromDate", false); //排序字段		

			//取得工作流工厂实例
			WmFactory wmFactory = WmFactory.getInstance();

			//构造工作项处理类
			WmWorkItemHandler handler = WmWorkItemHandler.getInstance();

			//取得待办工作的集合，WmWorkItem对象的集合
			//每个WmWorkItem对象中封装了一个WePartyAssignmentVO对象
			List workItems = handler.getWorkItems(wmFactory, condition);

			System.out.println("###### ExamineIntendWorkManager.getIntendWorks() workItems.size() = " + workItems.size());

			for (int i = 0; i < workItems.size(); i++) {
				//取得一个待办工作项
				WmWorkItem workItem = (WmWorkItem) workItems.get(i);

				//1.工作项ID
				String workEffortId = workItem.getWorkEffortId();

				//2.从流程实例中取得工作流节点内部的显示信息
				WmProcessInstance procInst = workItem.getProcessInstance();
				String processName = "";
				if (procInst.getAppDataUuid() != null && !"".equals(procInst.getAppDataUuid())) {
					AppData appData = procInst.getAppData();
					processName = appData.getStringEx1();
				}
				if (processName == null || "".equals(processName)) {
					processName = procInst.getWorkEffortName();
				}

				//3.从节点定义中取得节点名称作为标题
				WmActivityDefinition activityDef = workItem.getActivityDefinition();
				//根据节点定义取得待办工作项的名称
				String objectName = (String) activityDef.getObjectName();

				//4.待办工作到达时间
				String fromData = workItem.getFromDate().toString();
				if (fromData.length() >= 19) {
					fromData = fromData.substring(0, 19);
				}

				//5.构造URL
				String backUrl = java.net.URLEncoder.encode("/examine/servlet/WorkItemsTodoServlet");
				String url = "/examine/servlet/WorkItemNormalServlet?workEffortId=" + workEffortId + "&readOnly=false&backUrl=" + backUrl + "&itemTitle=" + processName + "&userUuid=" + personuuid;

				//6.构造待办工作值对象
				IntendWorkVO intendWork = new IntendWorkVO();
				intendWork.setSysid(sysid); //子系统ID
				intendWork.setLevel(IntendWorkVO.CODE_RED); //重要级别
				intendWork.setTitle(objectName); //标题
				intendWork.setTime(fromData); //日期
				intendWork.setUrl(url); //url

				System.out.println("###### IntendWorkVO[" + i + "] ExamineIntendWorkManager.getIntendWorks() url = " + url);
				System.out.println("###### IntendWorkVO[" + i + "] ExamineIntendWorkManager.getIntendWorks() sysid = " + sysid);
				System.out.println("###### IntendWorkVO[" + i + "] ExamineIntendWorkManager.getIntendWorks() display = " + intendWork.toString());

				intendWorks.add(intendWork);

			}

		} catch (WmException e) {
			e.printStackTrace();
			throw new IntendWorkException(e);

		}

		System.out.println("###### ExamineIntendWorkManager.getIntendWorks() intendWorks.size() = " + intendWorks.size());

		return intendWorks;
	}

}

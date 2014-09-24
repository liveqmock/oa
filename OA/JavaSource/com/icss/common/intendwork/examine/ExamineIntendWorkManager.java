/*
 * �������� 2005-8-19
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
 * ����ϵͳ��ȡ���칤�����ʵ����
 * 
 * @author YANGYANG
 * 2005-8-19 11:17:17
 */
public class ExamineIntendWorkManager implements IntendWorkManager {

	/**
	 * ������ϵͳID��ȡ�����ϵͳ�Ĵ��칤������ļ���
	 * @param sysid ��ϵͳID
	 * @return	����IntendWorkVO����ļ���
	 */
	public List getIntendWorks(IntendWorkContext context) throws IntendWorkException {

		//����IntendWorkVO����ļ���
		List intendWorks = new ArrayList();

		//���������л�ȡ��ϵͳID
		String sysid = (String) context.getAttribute(IntendWorkContext.SYSID);
		//���������л�ȡƽ̨��½�û���personuuid
		String personuuid = (String) context.getAttribute(IntendWorkContext.PERSONUUID);

		System.out.println("###### ExamineIntendWorkManager.getIntendWorks() sysid = " + sysid);
		System.out.println("###### ExamineIntendWorkManager.getIntendWorks() personuuid = " + personuuid);

		try {
			//��������ѯ����
			WorkItemConditionEx condition = new WorkItemConditionEx();

			//���ò�ѯ����
			condition.setPartyId(personuuid); //������ID������ǰ�û�uuid�����ڻ�ȡ��ǰ�û��Ĵ��칤����
			condition.setCurrentStates(WorkItemCondition.LIVE_STATES); //������״̬����Ĺ�������
			condition.addOrderBy("fromDate", false); //�����ֶ�		

			//ȡ�ù���������ʵ��
			WmFactory wmFactory = WmFactory.getInstance();

			//���칤�������
			WmWorkItemHandler handler = WmWorkItemHandler.getInstance();

			//ȡ�ô��칤���ļ��ϣ�WmWorkItem����ļ���
			//ÿ��WmWorkItem�����з�װ��һ��WePartyAssignmentVO����
			List workItems = handler.getWorkItems(wmFactory, condition);

			System.out.println("###### ExamineIntendWorkManager.getIntendWorks() workItems.size() = " + workItems.size());

			for (int i = 0; i < workItems.size(); i++) {
				//ȡ��һ�����칤����
				WmWorkItem workItem = (WmWorkItem) workItems.get(i);

				//1.������ID
				String workEffortId = workItem.getWorkEffortId();

				//2.������ʵ����ȡ�ù������ڵ��ڲ�����ʾ��Ϣ
				WmProcessInstance procInst = workItem.getProcessInstance();
				String processName = "";
				if (procInst.getAppDataUuid() != null && !"".equals(procInst.getAppDataUuid())) {
					AppData appData = procInst.getAppData();
					processName = appData.getStringEx1();
				}
				if (processName == null || "".equals(processName)) {
					processName = procInst.getWorkEffortName();
				}

				//3.�ӽڵ㶨����ȡ�ýڵ�������Ϊ����
				WmActivityDefinition activityDef = workItem.getActivityDefinition();
				//���ݽڵ㶨��ȡ�ô��칤���������
				String objectName = (String) activityDef.getObjectName();

				//4.���칤������ʱ��
				String fromData = workItem.getFromDate().toString();
				if (fromData.length() >= 19) {
					fromData = fromData.substring(0, 19);
				}

				//5.����URL
				String backUrl = java.net.URLEncoder.encode("/examine/servlet/WorkItemsTodoServlet");
				String url = "/examine/servlet/WorkItemNormalServlet?workEffortId=" + workEffortId + "&readOnly=false&backUrl=" + backUrl + "&itemTitle=" + processName + "&userUuid=" + personuuid;

				//6.������칤��ֵ����
				IntendWorkVO intendWork = new IntendWorkVO();
				intendWork.setSysid(sysid); //��ϵͳID
				intendWork.setLevel(IntendWorkVO.CODE_RED); //��Ҫ����
				intendWork.setTitle(objectName); //����
				intendWork.setTime(fromData); //����
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

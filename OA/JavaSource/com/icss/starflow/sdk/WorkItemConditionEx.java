package com.icss.starflow.sdk;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.icss.j2ee.dao.DAO;
import com.icss.starflow.sdk.condition.WorkItemCondition;
import com.icss.starflow.sdk.extension.AppData;

/**
 * �������ѯ������չ��
 * @author YANGYAMG
 */
public class WorkItemConditionEx extends WorkItemCondition {
	
	//������״̬����
	//�״̬�Ĺ�����״̬���Ƽ���
	public static final List LIVE_STATES;
	//��ȥ״̬�Ĺ�����״̬���Ƽ���
	public static final List DEAD_STATES;
	
	static {
		
		LIVE_STATES = new ArrayList();
		DEAD_STATES = new ArrayList();
		
		LIVE_STATES.add("CAL_ACCEPTED");
		LIVE_STATES.add("CAL_SENT");
		LIVE_STATES.add("CAL_WAITING");
		DEAD_STATES.add("CAL_ABORTED");
		DEAD_STATES.add("CAL_COMPLETED");
		DEAD_STATES.add("CAL_DECLINED");
		DEAD_STATES.add("CAL_DELEGATED");
		DEAD_STATES.add("CAL_TERMINATED");
		DEAD_STATES.add("CAL_WITHDRAWN");
	}

	
	//��ѯ�����з�װ��WePartyAssignmentSearchDAO����
	//���еĲ�ѯ�������ᱣ�浽���������
	protected WePartyAssignmentSearchDAO partyAssignmentSearchDAO;

	/**
	 * ���췽��
	 */
	public WorkItemConditionEx() {
		partyAssignmentSearchDAO = new WePartyAssignmentSearchDAO();
	}
	
	/**
	 * ��ָ�����������ƺ�����ʽ�����������������ѯ����
	 */
	public void addOrderBy(String propertyName, boolean asc) {
		partyAssignmentSearchDAO.addOrderBy(propertyName, asc);
	}

	/**
	 * ȡ��dao�������б�����Ҫ��ѯ�Ĳ�ѯ����
	 */
	public DAO getDAO() {
		return partyAssignmentSearchDAO;
	}

	/**
	 * ����ע��
	 */
	public void setComments(String comments) {
		partyAssignmentSearchDAO.setComments(comments);
	}

	/**
	 * ���õ�ǰ��������״̬
	 */
	public void setCurrentStates(List currentStates) {
		partyAssignmentSearchDAO.setCurrentStates(currentStates);
	}

	/**
	 * ���ù������Ķ�������
	 */
	public void setCustomizedTypes(List customizedTypes) {
		partyAssignmentSearchDAO.setCustomizedTypes(customizedTypes);
	}
	
	/**
	 * ����EnumId
	 */
	protected void setExpectationEnumId(String expectationEnumId) {
		partyAssignmentSearchDAO.setExpectationEnumId(expectationEnumId);
	}
	
	/**
	 * ����FacilityId - �����豸ID
	 */
	protected void setFacilityId(String facilityId) {
		partyAssignmentSearchDAO.setFacilityId(facilityId);
	}
	
	/**
	 * ����ʱ���ѯ����
	 */
	public void setFromDate(Timestamp fromDate1, Timestamp fromDate2) {
		partyAssignmentSearchDAO.setFromDate1(fromDate1);
		partyAssignmentSearchDAO.setFromDate2(fromDate2);
	}
	
	/**
	 * �������״̬����ʱ��
	 */
	public void setLastStateUpdate(Timestamp lastStateUpdate) {
		partyAssignmentSearchDAO.setLastStateUpdate(lastStateUpdate);
	}
	
	/**
	 * ���ñ���ظ�
	 */
	protected void setMustRsvp(String mustRsvp) {
		partyAssignmentSearchDAO.setMustRsvp(mustRsvp);
	}
	
	/**
	 * ���ò�����ID
	 */
	public void setPartyId(String partyId) {
		partyAssignmentSearchDAO.setPartyId(partyId);
	}
	
	/**
	 * ���ò���������
	 */
	public void setPartyName(String partyName) {
		partyAssignmentSearchDAO.setPartyName(partyName);
	}

	/**
	 * ���ý�ɫ����ID
	 */
	public void setRoleTypeId(String roleTypeId) {
		partyAssignmentSearchDAO.setRoleTypeId(roleTypeId);
	}
	
	/**
	 * ������һ��ʱ���ѯ����
	 */
	public void setThruDate(Timestamp thruDate1, Timestamp thruDate2) {
		partyAssignmentSearchDAO.setThruDate1(thruDate1);
		partyAssignmentSearchDAO.setThruDate2(thruDate2);
	}

	/**
	 * ����ʱ������
	 */
	public void setTimeLimit(Double timeLimit) {
		partyAssignmentSearchDAO.setTimeLimit(timeLimit);
	}
	
	/**
	 * ���ù�����������ʵ��ID�����������̽ڵ�ʵ��ID��
	 */
	public void setWorkEffortId(String workEffortId) {
		partyAssignmentSearchDAO.setWorkEffortId(workEffortId);
	}
	
	/**
	 * ���ù������İ�ID
	 */
	public void setWorkflowPackageId(String workflowPackageId) {
		partyAssignmentSearchDAO.setWorkflowPackageId(workflowPackageId);
	}

	/**
	 * ���ù������İ��汾
	 */
	public void setWorkflowPackageVersion(String workflowPackageVersion) {
		partyAssignmentSearchDAO.setWorkflowPackageVersion(
			workflowPackageVersion);
	}
	
	/**
	 * ���ù������̶���ID
	 */
	public void setWorkflowProcessId(String workflowProcessId) {
		partyAssignmentSearchDAO.setWorkflowProcessId(workflowProcessId);
	}
	
	/**
	 * ���ù������̶���ID�ļ���
	 */
	public void setWorkflowProcessIds(List workflowProcessIds) {
		partyAssignmentSearchDAO.setWorkflowProcessIds(workflowProcessIds);
	}
	
	/**
	 * ������";"ƴ�ӵĹ�������ID���ϵ��ַ���
	 */
	public void setWorkflowProcessIds(String workflowProcessIds) {
		if (workflowProcessIds != null) {
			List uuids = new ArrayList();
			for (StringTokenizer st =
				new StringTokenizer(workflowProcessIds, ";");
				st.hasMoreTokens();
				uuids.add(st.nextToken()));
			setWorkflowProcessIds(uuids);
		}
	}

	/**
	 * ���ù������̶���汾
	 */
	public void setWorkflowProcessVersion(String workflowProcessVersion) {
		partyAssignmentSearchDAO.setWorkflowProcessVersion(
			workflowProcessVersion);
	}
	
	/**
	 * ���ù������̽ڵ㶨��ID
	 */
	public void setWorkflowActivityId(String workflowActivityId) {
		partyAssignmentSearchDAO.setWorkflowActivityId(workflowActivityId);
	}
	
	
	/**
	 * ����Ӧ�ó������ݵ�uuid�ļ���
	 */
	public void setAppDataUuids(List appDataUuids) {
		partyAssignmentSearchDAO.setAppDataUuids(appDataUuids);
	}
	
	/**
	 * ȡ��Ӧ�ó�������
	 */
	public void setAppData(AppData data) {
		partyAssignmentSearchDAO.setAppData(data);
	}


}

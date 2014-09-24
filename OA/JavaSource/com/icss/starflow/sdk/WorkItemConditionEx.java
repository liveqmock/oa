package com.icss.starflow.sdk;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.icss.j2ee.dao.DAO;
import com.icss.starflow.sdk.condition.WorkItemCondition;
import com.icss.starflow.sdk.extension.AppData;

/**
 * 工作项查询条件扩展类
 * @author YANGYAMG
 */
public class WorkItemConditionEx extends WorkItemCondition {
	
	//工作流状态常量
	//活动状态的工作流状态名称集合
	public static final List LIVE_STATES;
	//死去状态的工作流状态名称集合
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

	
	//查询条件中封装的WePartyAssignmentSearchDAO对象
	//所有的查询条件都会保存到这个对象中
	protected WePartyAssignmentSearchDAO partyAssignmentSearchDAO;

	/**
	 * 构造方法
	 */
	public WorkItemConditionEx() {
		partyAssignmentSearchDAO = new WePartyAssignmentSearchDAO();
	}
	
	/**
	 * 按指定的排序名称和排序方式（正序或倒序）添加排序查询条件
	 */
	public void addOrderBy(String propertyName, boolean asc) {
		partyAssignmentSearchDAO.addOrderBy(propertyName, asc);
	}

	/**
	 * 取得dao对象，其中保存了要查询的查询条件
	 */
	public DAO getDAO() {
		return partyAssignmentSearchDAO;
	}

	/**
	 * 设置注释
	 */
	public void setComments(String comments) {
		partyAssignmentSearchDAO.setComments(comments);
	}

	/**
	 * 设置当前工作流的状态
	 */
	public void setCurrentStates(List currentStates) {
		partyAssignmentSearchDAO.setCurrentStates(currentStates);
	}

	/**
	 * 设置工作流的定制类型
	 */
	public void setCustomizedTypes(List customizedTypes) {
		partyAssignmentSearchDAO.setCustomizedTypes(customizedTypes);
	}
	
	/**
	 * 设置EnumId
	 */
	protected void setExpectationEnumId(String expectationEnumId) {
		partyAssignmentSearchDAO.setExpectationEnumId(expectationEnumId);
	}
	
	/**
	 * 设置FacilityId - 工具设备ID
	 */
	protected void setFacilityId(String facilityId) {
		partyAssignmentSearchDAO.setFacilityId(facilityId);
	}
	
	/**
	 * 设置时间查询条件
	 */
	public void setFromDate(Timestamp fromDate1, Timestamp fromDate2) {
		partyAssignmentSearchDAO.setFromDate1(fromDate1);
		partyAssignmentSearchDAO.setFromDate2(fromDate2);
	}
	
	/**
	 * 设置最后状态更新时间
	 */
	public void setLastStateUpdate(Timestamp lastStateUpdate) {
		partyAssignmentSearchDAO.setLastStateUpdate(lastStateUpdate);
	}
	
	/**
	 * 设置必须回复
	 */
	protected void setMustRsvp(String mustRsvp) {
		partyAssignmentSearchDAO.setMustRsvp(mustRsvp);
	}
	
	/**
	 * 设置参与人ID
	 */
	public void setPartyId(String partyId) {
		partyAssignmentSearchDAO.setPartyId(partyId);
	}
	
	/**
	 * 设置参与人名称
	 */
	public void setPartyName(String partyName) {
		partyAssignmentSearchDAO.setPartyName(partyName);
	}

	/**
	 * 设置角色类型ID
	 */
	public void setRoleTypeId(String roleTypeId) {
		partyAssignmentSearchDAO.setRoleTypeId(roleTypeId);
	}
	
	/**
	 * 设置另一个时间查询条件
	 */
	public void setThruDate(Timestamp thruDate1, Timestamp thruDate2) {
		partyAssignmentSearchDAO.setThruDate1(thruDate1);
		partyAssignmentSearchDAO.setThruDate2(thruDate2);
	}

	/**
	 * 设置时间限制
	 */
	public void setTimeLimit(Double timeLimit) {
		partyAssignmentSearchDAO.setTimeLimit(timeLimit);
	}
	
	/**
	 * 设置工作流的流程实例ID还是设置流程节点实例ID？
	 */
	public void setWorkEffortId(String workEffortId) {
		partyAssignmentSearchDAO.setWorkEffortId(workEffortId);
	}
	
	/**
	 * 设置工作流的包ID
	 */
	public void setWorkflowPackageId(String workflowPackageId) {
		partyAssignmentSearchDAO.setWorkflowPackageId(workflowPackageId);
	}

	/**
	 * 设置工作流的包版本
	 */
	public void setWorkflowPackageVersion(String workflowPackageVersion) {
		partyAssignmentSearchDAO.setWorkflowPackageVersion(
			workflowPackageVersion);
	}
	
	/**
	 * 设置工作流程定义ID
	 */
	public void setWorkflowProcessId(String workflowProcessId) {
		partyAssignmentSearchDAO.setWorkflowProcessId(workflowProcessId);
	}
	
	/**
	 * 设置工作流程定义ID的集合
	 */
	public void setWorkflowProcessIds(List workflowProcessIds) {
		partyAssignmentSearchDAO.setWorkflowProcessIds(workflowProcessIds);
	}
	
	/**
	 * 设置由";"拼接的工作流程ID集合的字符串
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
	 * 设置工作流程定义版本
	 */
	public void setWorkflowProcessVersion(String workflowProcessVersion) {
		partyAssignmentSearchDAO.setWorkflowProcessVersion(
			workflowProcessVersion);
	}
	
	/**
	 * 设置工作流程节点定义ID
	 */
	public void setWorkflowActivityId(String workflowActivityId) {
		partyAssignmentSearchDAO.setWorkflowActivityId(workflowActivityId);
	}
	
	
	/**
	 * 设置应用程序数据的uuid的集合
	 */
	public void setAppDataUuids(List appDataUuids) {
		partyAssignmentSearchDAO.setAppDataUuids(appDataUuids);
	}
	
	/**
	 * 取得应用程序数据
	 */
	public void setAppData(AppData data) {
		partyAssignmentSearchDAO.setAppData(data);
	}


}

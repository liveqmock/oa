package com.icss.starflow.sdk;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.SearchDAO;
import com.icss.resourceone.service.util.UtilMisc;
import com.icss.starflow.sdk.extension.AppData;

public class WePartyAssignmentSearchDAO extends SearchDAO {

	private String workEffortId;
	private String workflowPackageId;
	private String workflowPackageVersion;
	private String workflowProcessId;
	private String workflowProcessVersion;
	private String workflowActivityId;
	private String partyId;
	private String partyName;
	private String roleTypeId;
	private Timestamp fromDate;
	private Timestamp thruDate;
	private String facilityId;
	private String currentState;
	private Timestamp lastStateUpdate;
	private String comments;
	private String mustRsvp;
	private String expectationEnumId;
	private Double timeLimit;
	private String customizedType;
	private String appDataUuid;
	private Timestamp fromDate1;
	private Timestamp fromDate2;
	private Timestamp thruDate1;
	private Timestamp thruDate2;
	private String workflowProcessIds;
	private String currentStates;
	private String customizedTypes;
	private String appDataUuids;
	private AppData appData;

	public String getWorkEffortId() {
		return workEffortId;
	}

	public void setWorkEffortId(String _workEffortId) {
		firePropertyChange("workEffortId", workEffortId, _workEffortId);
		workEffortId = _workEffortId;
	}

	public String getWorkflowPackageId() {
		return workflowPackageId;
	}

	public void setWorkflowPackageId(String _workflowPackageId) {
		firePropertyChange("workflowPackageId", workflowPackageId, _workflowPackageId);
		workflowPackageId = _workflowPackageId;
	}

	public String getWorkflowPackageVersion() {
		return workflowPackageVersion;
	}

	public void setWorkflowPackageVersion(String _workflowPackageVersion) {
		firePropertyChange("workflowPackageVersion", workflowPackageVersion, _workflowPackageVersion);
		workflowPackageVersion = _workflowPackageVersion;
	}

	public String getWorkflowProcessId() {
		return workflowProcessId;
	}

	public void setWorkflowProcessId(String _workflowProcessId) {
		firePropertyChange("workflowProcessId", workflowProcessId, _workflowProcessId);
		workflowProcessId = _workflowProcessId;
	}

	public String getWorkflowProcessVersion() {
		return workflowProcessVersion;
	}

	public void setWorkflowProcessVersion(String _workflowProcessVersion) {
		firePropertyChange("workflowProcessVersion", workflowProcessVersion, _workflowProcessVersion);
		workflowProcessVersion = _workflowProcessVersion;
	}

	public String getWorkflowActivityId() {
		return workflowActivityId;
	}

	public void setWorkflowActivityId(String _workflowActivityId) {
		firePropertyChange("workflowActivityId", workflowActivityId, _workflowActivityId);
		workflowActivityId = _workflowActivityId;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String _partyId) {
		firePropertyChange("partyId", partyId, _partyId);
		partyId = _partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String _partyName) {
		firePropertyChange("partyName", partyName, _partyName);
		partyName = _partyName;
	}

	public String getRoleTypeId() {
		return roleTypeId;
	}

	public void setRoleTypeId(String _roleTypeId) {
		firePropertyChange("roleTypeId", roleTypeId, _roleTypeId);
		roleTypeId = _roleTypeId;
	}

	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(Timestamp _fromDate) {
		firePropertyChange("fromDate", fromDate, _fromDate);
		fromDate = _fromDate;
	}

	public Timestamp getThruDate() {
		return thruDate;
	}

	public void setThruDate(Timestamp _thruDate) {
		firePropertyChange("thruDate", thruDate, _thruDate);
		thruDate = _thruDate;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String _facilityId) {
		firePropertyChange("facilityId", facilityId, _facilityId);
		facilityId = _facilityId;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String _currentState) {
		firePropertyChange("currentState", currentState, _currentState);
		currentState = _currentState;
	}

	public Timestamp getLastStateUpdate() {
		return lastStateUpdate;
	}

	public void setLastStateUpdate(Timestamp _lastStateUpdate) {
		firePropertyChange("lastStateUpdate", lastStateUpdate, _lastStateUpdate);
		lastStateUpdate = _lastStateUpdate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String _comments) {
		firePropertyChange("comments", comments, _comments);
		comments = _comments;
	}

	public String getMustRsvp() {
		return mustRsvp;
	}

	public void setMustRsvp(String _mustRsvp) {
		firePropertyChange("mustRsvp", mustRsvp, _mustRsvp);
		mustRsvp = _mustRsvp;
	}

	public String getExpectationEnumId() {
		return expectationEnumId;
	}

	public void setExpectationEnumId(String _expectationEnumId) {
		firePropertyChange("expectationEnumId", expectationEnumId, _expectationEnumId);
		expectationEnumId = _expectationEnumId;
	}

	public Double getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Double _timeLimit) {
		firePropertyChange("timeLimit", timeLimit, _timeLimit);
		timeLimit = _timeLimit;
	}

	public String getCustomizedType() {
		return customizedType;
	}

	public void setCustomizedType(String _customizedType) {
		firePropertyChange("customizedType", customizedType, _customizedType);
		customizedType = _customizedType;
	}

	public String getAppDataUuid() {
		return appDataUuid;
	}

	public void setAppDataUuid(String _appDataUuid) {
		firePropertyChange("appDataUuid", appDataUuid, _appDataUuid);
		appDataUuid = _appDataUuid;
	}

	public Timestamp getFromDate1() {
		return fromDate1;
	}

	public void setFromDate1(Timestamp _fromDate) {
		firePropertyChange("fromDate1", fromDate1, _fromDate);
		fromDate1 = _fromDate;
	}

	public Timestamp getFromDate2() {
		return fromDate2;
	}

	public void setFromDate2(Timestamp _fromDate) {
		firePropertyChange("fromDate2", fromDate2, _fromDate);
		fromDate2 = _fromDate;
	}

	public Timestamp getThruDate1() {
		return thruDate1;
	}

	public void setThruDate1(Timestamp _thruDate) {
		firePropertyChange("thruDate1", thruDate1, _thruDate);
		thruDate1 = _thruDate;
	}

	public Timestamp getThruDate2() {
		return thruDate2;
	}

	public void setThruDate2(Timestamp _thruDate) {
		firePropertyChange("thruDate2", thruDate2, _thruDate);
		thruDate2 = _thruDate;
	}

	public String getWorkflowProcessIds() {
		return workflowProcessIds;
	}

	public void setWorkflowProcessIds(List _workflowProcessIds) {
		String __workflowProcessIds = UtilMisc.getSqlInSentence4String(_workflowProcessIds);
		firePropertyChange("workflowProcessIds", workflowProcessIds, __workflowProcessIds);
		workflowProcessIds = __workflowProcessIds;
	}

	public String getCurrentStates() {
		return currentStates;
	}

	public void setCurrentStates(List _currentStates) {
		String __currentStates = UtilMisc.getSqlInSentence4String(_currentStates);
		firePropertyChange("currentStates", currentStates, __currentStates);
		currentStates = __currentStates;
	}

	public String getCustomizedTypes() {
		return customizedTypes;
	}

	public void setCustomizedTypes(List _customizedTypes) {
		String __customizedTypes = UtilMisc.getSqlInSentence4String(_customizedTypes);
		firePropertyChange("customizedTypes", customizedTypes, __customizedTypes);
		customizedTypes = __customizedTypes;
	}

	public String getAppDataUuids() {
		return appDataUuids;
	}

	public void setAppDataUuids(List _appDataUuids) {
		String __appDataUuids = UtilMisc.getSqlInSentence4String(_appDataUuids);
		firePropertyChange("appDataUuids", appDataUuids, __appDataUuids);
		appDataUuids = __appDataUuids;
	}

	public AppData getAppData() {
		return appData;
	}

	public void setAppData(AppData _appData) {
		firePropertyChange("appData", appData, _appData);
		appData = _appData;
	}

	protected void setupFields() throws DAOException {
		addField("workEffortId", "WE_PARTY_ASSIGNMENT.WORK_EFFORT_ID");
		addField("workflowPackageId", "WE_PARTY_ASSIGNMENT.WORKFLOW_PACKAGE_ID");
		addField("workflowPackageVersion", "WE_PARTY_ASSIGNMENT.WORKFLOW_PACKAGE_VERSION");
		addField("workflowProcessId", "WE_PARTY_ASSIGNMENT.WORKFLOW_PROCESS_ID");
		addField("workflowProcessVersion", "WE_PARTY_ASSIGNMENT.WORKFLOW_PROCESS_VERSION");
		addField("workflowActivityId", "WE_PARTY_ASSIGNMENT.WORKFLOW_ACTIVITY_ID");
		addField("partyId", "WE_PARTY_ASSIGNMENT.PARTY_ID");
		addField("partyName", "WE_PARTY_ASSIGNMENT.PARTY_NAME");
		addField("roleTypeId", "WE_PARTY_ASSIGNMENT.ROLE_TYPE_ID");
		addField("fromDate", "WE_PARTY_ASSIGNMENT.FROM_DATE");
		addField("thruDate", "WE_PARTY_ASSIGNMENT.THRU_DATE");
		addField("facilityId", "WE_PARTY_ASSIGNMENT.FACILITY_ID");
		addField("currentState", "WE_PARTY_ASSIGNMENT.CURRENT_STATE");
		addField("lastStateUpdate", "WE_PARTY_ASSIGNMENT.LAST_STATE_UPDATE");
		addField("comments", "WE_PARTY_ASSIGNMENT.COMMENTS");
		addField("mustRsvp", "WE_PARTY_ASSIGNMENT.MUST_RSVP");
		addField("expectationEnumId", "WE_PARTY_ASSIGNMENT.EXPECTATION_ENUM_ID");
		addField("timeLimit", "WE_PARTY_ASSIGNMENT.TIME_LIMIT");
		addField("customizedType", "WE_PARTY_ASSIGNMENT.CUSTOMIZED_TYPE");
		addField("appDataUuid", "WE_PARTY_ASSIGNMENT.APP_DATA_UUID");
	}

	protected String getSearchSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"WE_PARTY_ASSIGNMENT.WORK_EFFORT_ID,WE_PARTY_ASSIGNMENT.WORKFLOW_PACKAGE_ID,WE_PARTY_ASSIGNMENT.WORKFLOW_PACKAGE_VERSION,WE_PARTY_ASSIGNMENT.WORKFLOW_PROCESS_ID,WE_PARTY_ASSIGNMENT.WORKFLOW_PROCESS_VERSION,WE_PARTY_ASSIGNMENT.WORKFLOW_ACTIVITY_ID,WE_PARTY_ASSIGNMENT.PARTY_ID,WE_PARTY_ASSIGNMENT.PARTY_NAME,WE_PARTY_ASSIGNMENT.ROLE_TYPE_ID,WE_PARTY_ASSIGNMENT.FROM_DATE,WE_PARTY_ASSIGNMENT.THRU_DATE,WE_PARTY_ASSIGNMENT.FACILITY_ID,WE_PARTY_ASSIGNMENT.CURRENT_STATE,WE_PARTY_ASSIGNMENT.LAST_STATE_UPDATE,WE_PARTY_ASSIGNMENT.COMMENTS,WE_PARTY_ASSIGNMENT.MUST_RSVP,WE_PARTY_ASSIGNMENT.EXPECTATION_ENUM_ID,WE_PARTY_ASSIGNMENT.TIME_LIMIT,WE_PARTY_ASSIGNMENT.CUSTOMIZED_TYPE,WE_PARTY_ASSIGNMENT.APP_DATA_UUID ");
		sql.append("FROM ");
		sql.append("WE_PARTY_ASSIGNMENT ");
		sql.append("WHERE WE_PARTY_ASSIGNMENT.WORKFLOW_PACKAGE_ID = '").append(workflowPackageId).append("' ");
		sql.append("AND WE_PARTY_ASSIGNMENT.WORKFLOW_PACKAGE_VERSION = '").append(workflowPackageVersion).append("' ");
		if (workflowProcessId != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.WORKFLOW_PROCESS_ID = '").append(workflowProcessId).append("' ");
		if (workflowProcessVersion != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.WORKFLOW_PROCESS_VERSION = '").append(workflowProcessVersion).append("' ");
		if (workflowActivityId != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.WORKFLOW_ACTIVITY_ID = '").append(workflowActivityId).append("' ");
		if (workEffortId != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.WORK_EFFORT_ID = '").append(workEffortId).append("' ");
		if (partyId != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.PARTY_ID = '").append(partyId).append("' ");
		if (partyName != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.PARTY_NAME = '").append(partyName).append("' ");
		if (roleTypeId != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.ROLE_TYPE_ID = '").append(roleTypeId).append("' ");
		if (fromDate1 != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.FROM_DATE >= ").append(getOracleTimeFormate(fromDate1)).append(" ");
		if (fromDate2 != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.FROM_DATE < ").append(getOracleTimeFormate(fromDate2)).append(" ");
		if (thruDate1 != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.THRU_DATE >= ").append(getOracleTimeFormate(thruDate1)).append(" ");
		if (thruDate2 != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.THRU_DATE < ").append(getOracleTimeFormate(thruDate2)).append(" ");
		if (workflowProcessIds != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.WORKFLOW_PROCESS_ID IN (").append(workflowProcessIds).append(") ");
		if (facilityId != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.FACILITY_ID = '").append(facilityId).append("' ");
		if (currentStates != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.CURRENT_STATE IN (").append(currentStates).append(") ");
		if (lastStateUpdate != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.LAST_STATE_UPDATE = '").append(lastStateUpdate).append("' ");
		if (comments != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.COMMENTS = '").append(comments).append("' ");
		if (mustRsvp != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.MUST_RSVP = '").append(mustRsvp).append("' ");
		if (expectationEnumId != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.EXPECTATION_ENUM_ID = '").append(expectationEnumId).append("' ");
		if (timeLimit != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.TIME_LIMIT = '").append(timeLimit).append("' ");
		if (customizedTypes != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.CUSTOMIZED_TYPE IN (").append(customizedTypes).append(") ");
		if (appDataUuids != null)
			sql.append("AND WE_PARTY_ASSIGNMENT.APP_DATA_UUID IN (").append(appDataUuids).append(") ");
		if (appData != null) {
			String appDataSql = getAppDataSearchSQL();
			sql.append("AND WE_PARTY_ASSIGNMENT.APP_DATA_UUID IN (").append(appDataSql).append(") ");
		}
		
		//if (_orderByFields != null)
		//	sql.append("ORDER BY ").append(_orderByFields);
		
		return sql.toString();
	}

	private String getAppDataSearchSQL() {
		StringBuffer appDataSql = new StringBuffer();
		appDataSql.append("SELECT ");
		appDataSql.append("WE_APP_DATA.APP_DATA_UUID ");
		appDataSql.append("FROM ");
		appDataSql.append("WE_APP_DATA ");
		appDataSql.append("WHERE 1=1 ");
		if (appData.getStringEx1() != null)
			appDataSql.append("AND WE_APP_DATA.STRING_EX1 LIKE '%").append(appData.getStringEx1()).append("%' ");
		if (appData.getStringEx2() != null)
			appDataSql.append("AND WE_APP_DATA.STRING_EX2 LIKE '%").append(appData.getStringEx2()).append("%' ");
		if (appData.getStringEx3() != null)
			appDataSql.append("AND WE_APP_DATA.STRING_EX3 LIKE '%").append(appData.getStringEx3()).append("%' ");
		if (appData.getStringEx4() != null)
			appDataSql.append("AND WE_APP_DATA.STRING_EX4 LIKE '%").append(appData.getStringEx4()).append("%' ");
		if (appData.getTimestampEx1() != null)
			appDataSql.append("AND WE_APP_DATA.TIMESTAMP_EX1 = '").append(appData.getTimestampEx1()).append("' ");
		if (appData.getTimestampEx2() != null)
			appDataSql.append("AND WE_APP_DATA.TIMESTAMP_EX2 = '").append(appData.getTimestampEx2()).append("' ");
		if (appData.getIntegerEx1() != null)
			appDataSql.append("AND WE_APP_DATA.INTEGER_EX1 = ").append(appData.getIntegerEx1()).append(" ");
		if (appData.getLongEx1() != null)
			appDataSql.append("AND WE_APP_DATA.LONG_EX1 = ").append(appData.getLongEx1()).append(" ");
		return appDataSql.toString();
	}

	private String getOracleTimeFormate(Timestamp timestamp) {
		String str_temp = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str_temp = format.format(timestamp);
		str_temp = "to_date('" + str_temp + "','yyyy-mm-dd hh24:mi:ss')";
		return str_temp;
	}
	public WePartyAssignmentSearchDAO() {
	}
}

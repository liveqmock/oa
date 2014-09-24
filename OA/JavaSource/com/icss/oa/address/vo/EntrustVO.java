package com.icss.oa.address.vo;

import com.icss.j2ee.vo.ValueObject;

public class EntrustVO extends ValueObject {
	Integer id;
	String entrustUid;
	String substituteUid;
	Long starttime;
	Long endtime;
	String entrustUuid;
	String substituteUuid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer _id) {
		id = _id;
	}
	public String getEntrustUid() {
		return entrustUid;
	}
	public void setEntrustUid(String _entrustUid) {
		entrustUid = _entrustUid;
	}
	public String getSubstituteUid() {
		return substituteUid;
	}
	public void setSubstituteUid(String _substituteUid) {
		substituteUid = _substituteUid;
	}

	public Long getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Long _starttime)
	{
		starttime = _starttime;
	}

	public Long getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Long _endtime)
	{
		endtime = _endtime;
	}
	public String getEntrustUuid() {
		return entrustUuid;
	}
	public void setEntrustUuid(String _entrustUuid) {
		entrustUuid = _entrustUuid;
	}
	public String getSubstituteUuid() {
		return substituteUuid;
	}
	public void setSubstituteUuid(String _substituteUuid) {
		substituteUuid = _substituteUuid;
	}
}
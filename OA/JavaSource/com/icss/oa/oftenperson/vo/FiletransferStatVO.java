/*
 * �������� 2005-8-24
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.oftenperson.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class FiletransferStatVO extends ValueObject {
	private Integer statid;
	private String senderuserid;
	private String receiveruserid;
	private Integer times;
	public Integer getStatid() {
		return statid;
	}
	public void setStatid(Integer _statid) {
		statid = _statid;
	}
	public String getSenderuserid() {
		return senderuserid;
	}
	public void setSenderuserid(String _senderuserid) {
		senderuserid = _senderuserid;
	}
	public String getReceiveruserid() {
		return receiveruserid;
	}
	public void setReceiveruserid(String _receiveruserid) {
		receiveruserid = _receiveruserid;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer _times) {
		times = _times;
	}
}
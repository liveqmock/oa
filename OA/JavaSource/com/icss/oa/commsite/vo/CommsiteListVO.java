/*
 * �������� 2004-3-31
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.commsite.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class CommsiteListVO extends ValueObject {
	Integer ocsId;
	Integer ocsIndex;
	String ocsLink;
	String ocsDes;
	String ocsName;
	public Integer getOcsId() {
		return ocsId;
	}
	public void setOcsId(Integer _ocsId) {
		ocsId = _ocsId;
	}
	public Integer getOcsIndex() {
		return ocsIndex;
	}
	public void setOcsIndex(Integer _ocsIndex) {
		ocsIndex = _ocsIndex;
	}
	public String getOcsLink() {
		return ocsLink;
	}
	public void setOcsLink(String _ocsLink) {
		ocsLink = _ocsLink;
	}
	public String getOcsDes() {
		return ocsDes;
	}
	public void setOcsDes(String _ocsDes) {
		ocsDes = _ocsDes;
	}
	public String getOcsName() {
		return ocsName;
	}
	public void setOcsName(String _ocsName) {
		ocsName = _ocsName;
	}
}
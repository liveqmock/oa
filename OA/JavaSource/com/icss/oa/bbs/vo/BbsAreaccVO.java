/*
 * �������� 2008-3-7
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.bbs.vo;

/**
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */

import com.icss.j2ee.vo.ValueObject;

public class BbsAreaccVO extends ValueObject {

	private Integer baaId;
	private Integer areaid;
	private String userid;
	private String flag;

	public java.lang.Integer getBaaId(){
		return baaId;
	}
	public void setBaaId(java.lang.Integer _baaId){ 
		baaId = _baaId;
	}
	public java.lang.Integer getAreaid(){
		return areaid;
	}
	public void setAreaid(java.lang.Integer _areaid){ 
		areaid = _areaid;
	}
	public java.lang.String getUserid(){
		return userid;
	}
	public void setUserid(java.lang.String _userid){ 
		userid = _userid;
	}
	public java.lang.String getFlag(){
		return flag;
	}
	public void setFlag(java.lang.String _flag){ 
		flag = _flag;
	}
	
	public String toString(){
		String str="  [TYPE=<java.lang.Integer>	 FIELD=<baaId>	 VALUE=<"+baaId+">"
		+"  [TYPE=<java.lang.Integer>	 FIELD=<areaid>	 VALUE=<"+areaid+">"
		+"  [TYPE=<java.lang.String>	 FIELD=<userid>	 VALUE=<"+userid+">"
		+"  [TYPE=<java.lang.String>	 FIELD=<flag>	 VALUE=<"+flag+">"
		+"";
		return str;
	}
}
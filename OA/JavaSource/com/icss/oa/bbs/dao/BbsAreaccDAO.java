/*
 * �������� 2008-3-7
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.bbs.dao;

/**
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class BbsAreaccDAO extends DAO {

	private Integer baaId;
	private Integer areaid;
	private String userid;
	private String flag;

	public BbsAreaccDAO(Connection conn){
		super(conn);
	}

	public BbsAreaccDAO(){
		super();
	}

	public void setBaaId(java.lang.Integer _baaId){ 
		firePropertyChange("baaId",baaId,_baaId);
		baaId = _baaId;
	}

	public java.lang.Integer getBaaId(){
		return baaId;
	}


	public void setAreaid(java.lang.Integer _areaid){ 
		firePropertyChange("areaid",areaid,_areaid);
		areaid = _areaid;
	}

	public java.lang.Integer getAreaid(){
		return areaid;
	}


	public void setUserid(java.lang.String _userid){ 
		firePropertyChange("userid",userid,_userid);
		userid = _userid;
	}

	public java.lang.String getUserid(){
		return userid;
	}


	public void setFlag(java.lang.String _flag){ 
		firePropertyChange("flag",flag,_flag);
		flag = _flag;
	}

	public java.lang.String getFlag(){
		return flag;
	}


	protected void setupFields() throws DAOException {
		addField("baaId","BAA_ID");
		addField("areaid","AREAID");
		addField("userid","USERID");
		addField("flag","FLAG");
		setTableName("BBS_AREACC");
		addKey("BAA_ID");
		setAutoIncremented("BAA_ID");
	}

}
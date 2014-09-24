/*
 * �������� 2008-2-26
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.zbs.duty.dao;

/**
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class TbZbsWorkinfotypeDAO extends DAO {

    private Integer witId;
    private String witName;
    private String witMemo;
    private Integer witIndex;
    private String witType;

    public TbZbsWorkinfotypeDAO(Connection conn){
        super(conn);
    }

    public TbZbsWorkinfotypeDAO(){
        super();
    }

    public void setWitId(java.lang.Integer _witId){ 
        firePropertyChange("witId",witId,_witId);
        witId = _witId;
	}

    public java.lang.Integer getWitId(){
        return witId;
	}


    public void setWitName(java.lang.String _witName){ 
        firePropertyChange("witName",witName,_witName);
        witName = _witName;
	}

    public java.lang.String getWitName(){
        return witName;
	}


    public void setWitMemo(java.lang.String _witMemo){ 
        firePropertyChange("witMemo",witMemo,_witMemo);
        witMemo = _witMemo;
	}

    public java.lang.String getWitMemo(){
        return witMemo;
	}


    public void setWitIndex(java.lang.Integer _witIndex){ 
        firePropertyChange("witIndex",witIndex,_witIndex);
        witIndex = _witIndex;
	}

    public java.lang.Integer getWitIndex(){
        return witIndex;
	}


    public void setWitType(java.lang.String _witType){ 
        firePropertyChange("witType",witType,_witType);
        witType = _witType;
	}

    public java.lang.String getWitType(){
        return witType;
	}


    protected void setupFields() throws DAOException {
        addField("witId","WIT_ID");
        addField("witName","WIT_NAME");
        addField("witMemo","WIT_MEMO");
        addField("witIndex","WIT_INDEX");
        addField("witType","WIT_TYPE");
        setTableName("TB_ZBS_WORKINFOTYPE");
        addKey("WIT_ID");
		setAutoIncremented("WIT_ID");
    }

}
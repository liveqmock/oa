/*
 * Created on 2005-3-24
 */
package com.icss.starflow.sdk;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.resourceone.service.LocalDispatcher;
import com.icss.starflow.engine.impl.db.model.WePartyAssignmentVO;

public class WmWorkItemHandler {

	//����ת���������е��õ�
	private LocalDispatcher localDispatcher;

	private WmWorkItemHandler() {

	}

	public static WmWorkItemHandler getInstance() {
		WmWorkItemHandler handler = new WmWorkItemHandler();
		return handler;
	}

	/**
	 * ȡ�ù����б�
	 * @param wmFactory	��������������
	 * @param condition	��ѯ�����������û���UUID��������״̬���Ǵ��칤�������Ѱ칤����
	 * @return
	 * @throws WmException
	 */
	public List getWorkItems(WmFactory wmFactory, WorkItemConditionEx condition) throws WmException {

		//���칤������ļ���
		List partyAssignmentVOs;

		//�������ò�ѯ��������ID�����汾ID
		condition.setWorkflowPackageId(wmFactory.getPackageId());
		condition.setWorkflowPackageVersion(wmFactory.getPackageVersion());

		Connection conn = null;
		boolean beganTransaction = false;

		try {
			//��ʼ����
			conn = this.getConnection("jdbc/STARFLOW");

			DAOFactory fact = new DAOFactory(conn);
			//����DAO����
			//WePartyAssignmentSearchDAO���͵�DAO
			fact.setDAO(condition.getDAO());

			//��ѯָ�������Ĵ��칤���ļ���
			partyAssignmentVOs = fact.find(new WePartyAssignmentVO());

		} catch (DAOException e) {
			throw new WmException(e.getMessage(), e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		//����WmWorkItem������ļ���
		List workItems = new ArrayList();

		if (partyAssignmentVOs != null) {

			Iterator iter = partyAssignmentVOs.iterator();

			//�������е�WePartyAssignmentVO����ļ���
			while (iter.hasNext()) {

				WePartyAssignmentVO partyAssignmentVO = (WePartyAssignmentVO) iter.next();

				//��LocalDispatcher��WePartyAssignmentVO����һ��WmWorkItem����
				WmWorkItem workItem = new WmWorkItem(localDispatcher, partyAssignmentVO);

				//��WmWorkItem������뵽������
				workItems.add(workItem);

			}

		}

		return workItems;

	}

	protected Connection getConnection(String jndiName) {
		Connection conn = null;
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(jndiName);
			conn = ds.getConnection();

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}

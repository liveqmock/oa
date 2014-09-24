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

	//本地转发器，进行调用的
	private LocalDispatcher localDispatcher;

	private WmWorkItemHandler() {

	}

	public static WmWorkItemHandler getInstance() {
		WmWorkItemHandler handler = new WmWorkItemHandler();
		return handler;
	}

	/**
	 * 取得工作列表
	 * @param wmFactory	工作流工厂对象
	 * @param condition	查询条件，包括用户的UUID、工作流状态（是待办工作还是已办工作）
	 * @return
	 * @throws WmException
	 */
	public List getWorkItems(WmFactory wmFactory, WorkItemConditionEx condition) throws WmException {

		//待办工作对象的集合
		List partyAssignmentVOs;

		//继续设置查询条件，包ID、包版本ID
		condition.setWorkflowPackageId(wmFactory.getPackageId());
		condition.setWorkflowPackageVersion(wmFactory.getPackageVersion());

		Connection conn = null;
		boolean beganTransaction = false;

		try {
			//开始事务
			conn = this.getConnection("jdbc/STARFLOW");

			DAOFactory fact = new DAOFactory(conn);
			//设置DAO对象
			//WePartyAssignmentSearchDAO类型的DAO
			fact.setDAO(condition.getDAO());

			//查询指定条件的待办工作的集合
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

		//构造WmWorkItem工作项的集合
		List workItems = new ArrayList();

		if (partyAssignmentVOs != null) {

			Iterator iter = partyAssignmentVOs.iterator();

			//遍历所有的WePartyAssignmentVO对象的集合
			while (iter.hasNext()) {

				WePartyAssignmentVO partyAssignmentVO = (WePartyAssignmentVO) iter.next();

				//用LocalDispatcher和WePartyAssignmentVO构造一个WmWorkItem对象
				WmWorkItem workItem = new WmWorkItem(localDispatcher, partyAssignmentVO);

				//把WmWorkItem对象加入到集合中
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

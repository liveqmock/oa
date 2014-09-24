package com.icss.oa.counter.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.util.Globals;
import com.icss.oa.counter.dao.ViewRecordDAO;
import com.icss.oa.counter.vo.ViewRecordVO;

public class VRHanlder {

	private static ArrayList MemoryList = new ArrayList();
	private static int MemorySize = 0;
	private static long TimeSpan = 0;
	//private static Map count = new HashMap();

	private static Connection conn;

	public VRHanlder() {
	}

	public VRHanlder(Connection conn) {
		this.conn = conn;
	}

	public void addMemoryList(ViewRecordVO vo) {

		// ����ڴ��Ƿ��ʼ��
		if (MemorySize <= 0) {
			InitialDataMemory();
		}
		synchronized (VRHanlder.class) {
			// ���÷����Ƿ��ڷ��ʵ�����ʱ��֮��
			if (!checkTheViewSpan(vo)) {
				return;
			}
			MemoryList.add(vo);
			// �������Ϣ��ӵ�����ͳ����
			// ViewCount.setViewCount(viewRecord.getSiteId(), viewRecord
			// .getChannelId(), viewRecord.getInforId(), viewRecord
			// .getUrl());

			// **********************************************************************************************************
			// System.out.println("Have Record the View: (the MemorySize-"
			// + getMemorySize() + ", used MemorySize-" + getMemoryListSize()
			// + ", TimeSpan-" + getTimeSpan() + ")");
			// viewRecord.printViewRecord();
			// **********************************************************************************************************

			// �ڴ�ռ��Ѿ��ﵽ���ƵĴ�Сʱ��д���ݿ�
			if (MemoryList.size() >= MemorySize) {
				writeToDB();
			}
		}
	}

	/**
	 * ��ʼ�����ݴ洢�ڴ�ͷ������Ƽ�� Ĭ�ϵ����ݴ洢��С��100����¼��ʱ����Ϊ0������û�δ�趨��
	 */
	public static void InitialDataMemory() {
		MemorySize = 100;
		TimeSpan = 0;
	}

	/**
	 * ������ʱ����
	 */
	public static boolean checkTheViewSpan(ViewRecordVO vo) {
		if (getTheRecordTimeSpan(vo) >= TimeSpan) {
			return true;
		}
		return false;
	}

	/**
	 * �õ�����������¼�ļ��ʱ��
	 */

	private static long getTheRecordTimeSpan(ViewRecordVO vo) {
		for (int i = MemoryList.size() - 1; i >= 0; i--) {
			ViewRecordVO vo1 = (ViewRecordVO) MemoryList.get(i);
			if (vo.getIp() != null && vo.getSite() != null
					&& vo.getUseruuid() != null
					&& vo.getUseruuid().equals(vo1.getUseruuid())
					&& vo.getSite().equals(vo1.getSite())
					&& vo.getIp().equals(vo1.getIp())) {
				return vo.getDate() - vo1.getDate();
			}
		}
		return TimeSpan;
	}

	/**
	 * ���ڴ�������ӵ����ݿ�
	 */
	public void writeToDB() {
		Connection conn1 = null;
		try {
			conn1 = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			Iterator it = MemoryList.iterator();
			while (it.hasNext()) {
				ViewRecordVO vo = (ViewRecordVO) it.next();

				ViewRecordDAO dao = new ViewRecordDAO();
				dao.setValueObject(vo);
				dao.setConnection(conn1);
				dao.create();
			}
			MemoryList.clear();
			//count = new HashMap();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn1 != null) {
				try {
					conn1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
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

		// 检测内存是否初始化
		if (MemorySize <= 0) {
			InitialDataMemory();
		}
		synchronized (VRHanlder.class) {
			// 检测该访问是否在访问的限制时间之内
			if (!checkTheViewSpan(vo)) {
				return;
			}
			MemoryList.add(vo);
			// 将浏览信息添加到访问统计中
			// ViewCount.setViewCount(viewRecord.getSiteId(), viewRecord
			// .getChannelId(), viewRecord.getInforId(), viewRecord
			// .getUrl());

			// **********************************************************************************************************
			// System.out.println("Have Record the View: (the MemorySize-"
			// + getMemorySize() + ", used MemorySize-" + getMemoryListSize()
			// + ", TimeSpan-" + getTimeSpan() + ")");
			// viewRecord.printViewRecord();
			// **********************************************************************************************************

			// 内存空间已经达到限制的大小时，写数据库
			if (MemoryList.size() >= MemorySize) {
				writeToDB();
			}
		}
	}

	/**
	 * 初始化数据存储内存和访问限制间隔 默认的数据存储大小是100条记录，时间间隔为0（如果用户未设定）
	 */
	public static void InitialDataMemory() {
		MemorySize = 100;
		TimeSpan = 0;
	}

	/**
	 * 检测访问时间间隔
	 */
	public static boolean checkTheViewSpan(ViewRecordVO vo) {
		if (getTheRecordTimeSpan(vo) >= TimeSpan) {
			return true;
		}
		return false;
	}

	/**
	 * 得到访问与最后记录的间隔时间
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
	 * 将内存数据添加到数据库
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
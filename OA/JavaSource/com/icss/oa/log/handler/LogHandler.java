/*
 * Created on 2003-12-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.log.handler;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


import com.icss.oa.addressbook.dao.AddressbookFolderDAO;
import com.icss.oa.addressbook.dao.AddressbookManagerDAO;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.addressbook.vo.AddressbookTreeVO;
import com.icss.oa.log.dao.LogAccessoryDAO;
import com.icss.oa.log.dao.LogMsgDAO;
import com.icss.oa.log.dao.LogReplyDAO;
import com.icss.oa.log.dao.LogSysDAO;
import com.icss.oa.log.vo.LogAccessoryVO;
import com.icss.oa.log.vo.LogMsgVO;
import com.icss.oa.log.vo.LogReplyVO;
import com.icss.oa.log.vo.LogSysTreeVO;
import com.icss.oa.log.vo.LogSysVO;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LogHandler {
	private Connection conn;
	public String filepath = null;
	public LogHandler(Connection conn) {
		this.conn = conn;
	}
	/**�õ�ϵͳ�б�
	 * get subarea list
	 * @author firecoral
	 *
	 */
	public List getAllsysList() {
		LogSysDAO dao = new LogSysDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setWhereClause(" SYS_ID<>'-1'");
		dao.addOrderBy("sysOrder",true);
		factory.setDAO(dao);
		
		List list = null;
		try {
			list = factory.find(new LogSysVO());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**�õ�������־��Ϣ�б�
	 * get subarea list
	 * @author firecoral
	 *
	 */
	public List getAllLogList() {
		LogMsgDAO dao = new LogMsgDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.addOrderBy("logTime",false);
		factory.setDAO(dao);
		
		List list = null;
		try {
			list = factory.find(new LogMsgVO());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**���Ӱ����Ϣ
	 * 
	 * @param logsysvo
	 */
	public void addSys(LogSysVO vo) {
		LogSysDAO dao = new LogSysDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * ȡ�õ�һ��İ����Ϣ
	 * @return
	 */
	public List getTopSysList() {
		LogSysDAO dao = new LogSysDAO();
		dao.setLogSysId(new Integer("-1"));
		DAOFactory factory = new DAOFactory(conn);
		
		factory.setDAO(dao);
		
		List list = null;
		try {
			list = factory.find(new LogSysVO());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**�õ����ļ���
	 * @param folderId
	 * @return
	 */
	private List getFolderList(Integer parentId) {

		LogSysDAO dao = new LogSysDAO();
		dao.setLogSysId(parentId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new LogSysVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	/**�Ƿ������ļ���
	 * @param list
	 * @return
	 */

	public boolean hasChild(Integer folderId) {
		List folderList = getFolderList(folderId);
		if (folderList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * ���ɰ����Ϣ����vo
	 * @param list
	 * @return
	 */
	public List buildXloadTreeList(List list) {
		List treeList = new ArrayList();
		if (list != null) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				LogSysVO vo = (LogSysVO) it.next();
				LogSysTreeVO treeVO = new LogSysTreeVO();
				treeVO.setLogSysVO(vo);
				treeVO.setHasChild(hasChild(vo.getSysId()));
				//treeVO.setIsShare(isShare(vo.getFpId()));
				treeList.add(treeVO);
			}
		}
		return treeList;
	}
	/**
	 * �õ��Ӱ��
	 * @param folderId
	 * @return
	 */
	public List getSysList(Integer folderId) {
		LogSysDAO dao = new LogSysDAO();
		dao.setLogSysId(folderId);
		DAOFactory factory = new DAOFactory(conn);
		dao.addOrderBy("sysOrder",true);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new LogSysVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ɾ�������Ϣ
	 * @param integer
	 */
	public void delLogSys(Integer sysid) {
		LogSysDAO dao = new LogSysDAO();
		dao.setSysId(sysid);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ȡ��ĳ������־��Ϣ
	 * @return
	 */
	public List getLogList(Integer parentId) {
		LogMsgDAO dao = new LogMsgDAO();
		dao.setSysId(parentId);
		DAOFactory factory = new DAOFactory(conn);
		dao.addOrderBy("logTime",false);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new LogMsgVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ����־��Ϣ���������
	 * @param logmsgvo
	 * @return
	 */
	public Integer addLogMsg(LogMsgVO vo) {
		LogMsgDAO dao = new LogMsgDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao.getLogId();
	}
	/**
	 * ����־��Ϣ��������븽����Ϣ
	 * @param logaccessoryvo
	 */
	public void addLogMsgAccessory(LogAccessoryVO vo) {
		LogAccessoryDAO dao = new LogAccessoryDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			
			dao.create();
//			System.out.println("*************addLogMsgAccessory********************");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ȡ����־��ϸ��Ϣ
	 * @param logid
	 * @return
	 */
	public List getLogDetail(Integer logid) {
		LogMsgDAO dao = new LogMsgDAO();
		dao.setLogId(logid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new LogMsgVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ȡ����־�ظ���Ϣ
	 * @param logid
	 * @return
	 */
	public List getLogReply(Integer logid) {
		LogReplyDAO dao = new LogReplyDAO();
		dao.setLogId(logid);
		DAOFactory factory = new DAOFactory(conn);
//		dao.addOrderBy("replyTime",false);
		
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new LogReplyVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ���ӻظ���Ϣ
	 * @param replyvo
	 */
	public void addReply(LogReplyVO replyvo) {
		LogReplyDAO dao = new LogReplyDAO();
		dao.setValueObject(replyvo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ������־IDȡ�ø�����Ϣ
	 * @param logid
	 * @return
	 */
	public List getattachListfull(Integer logid) {
		LogAccessoryDAO dao = new LogAccessoryDAO();
		dao.setLogId(logid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new LogAccessoryVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ������־IDȡ�ø�����Ϣ
	 * @param logid
	 * @return
	 */
	public List getattachListsimple(Integer logid) {
		Connection conn = null;
		
		List returnlist=new ArrayList();
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " select log_id,accessory_id,accessory_name,accessory_order accessory_uploadusr from oabase.log_accessory WHERE log_id = '"
				+ logid+"'";
//		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee33333322222222223     "); 
			if (rs != null) {
//				System.out.println("eeee444444     "); 
				while (rs.next()) {
					LogAccessoryVO vo=new LogAccessoryVO();
					vo.setLogId(Integer.valueOf(rs.getString("log_id")));
					vo.setAccessoryId(Integer.valueOf(rs.getString("accessory_id")));
//					System.out.println("eeee555555     "); 
					vo.setAccessoryName(rs.getString("accessory_name"));
//					System.out.println("eeee5555550000000     "); 
//					vo.setAccessoryOrder(rs.getString("accessory_order"));
//					System.out.println("eeee5555551111111     "); 
					vo.setAccessoryUploadusr(rs.getString("accessory_uploadusr"));
//					System.out.println("eeee666666     "+vo.getAccessoryName()); 
					returnlist.add(vo);
//					System.out.println("eeee777     "); 
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return returnlist;

	}
	
	/**
	 * ���ݸ���IDȡ�ø�����Ϣ
	 * @param attachid
	 * @return
	 */
	public List getattachDetailList(Integer attachid) {
		LogAccessoryDAO dao = new LogAccessoryDAO();
		dao.setAccessoryId(attachid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new LogAccessoryVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 * @param path
	 */
	public void CreatDir(String filepath) {
		// this.filepath = toFilePathManager.getString("toFile_path");
		this.filepath = filepath;
		System.out.println("����Ŀ¼toFile_path is : " + filepath);
		File file = new File(filepath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	/**
	 * ������־IDɾ��������־��Ϣ
	 * @param logid
	 */
	public void deleteLogMsg(String logid) {
		LogMsgDAO dao = new LogMsgDAO();
		dao.setLogId(Integer.valueOf(logid));
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ���ݻظ�IDɾ���ظ���Ϣ
	 * @param logid
	 */
	public void deleteReply(String replyid) {
		LogReplyDAO dao = new LogReplyDAO();
		dao.setReplyId(Integer.valueOf(replyid));
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	



}

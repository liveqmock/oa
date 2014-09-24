/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.folder.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.folder.dao.*;
import com.icss.oa.folder.vo.*;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class FolderHandler {
	private Connection conn;
	public FolderHandler(Connection conn) {
		this.conn = conn;
	}
	/**
	 * get folder list
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	/*	private boolean hasChildFolder(Integer folderId){
			
		}
		public List getFolderListForTree(String userId){
			List list=getFolderList(userId); 
		}*/
	public List getSharePeopleList(String userId, Integer folderId)
		throws HandlerException {

		SharePersonSearchDAO dao = new SharePersonSearchDAO();
		dao.setConnection(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"FOLDER_SHAREACCESS.FSC_ACCESSRIGHT,SYS_PERSON.CNNAME,SYS_PERSON.PERSONUUID ");
		sql.append("FROM ");
		sql.append(
			"FOLDER_MANAGEMENT,FOLDER_SHARE,FOLDER_SHAREACCESS,SYS_PERSON ");
		sql.append(" WHERE ");
		sql.append(
			"FOLDER_SHARE.FM_ID=FOLDER_MANAGEMENT.FM_ID AND FOLDER_SHAREACCESS.FS_ID=FOLDER_SHARE.FS_ID ");
		sql.append("AND FOLDER_MANAGEMENT.FM_PERSONID='" + userId + "' ");
		sql.append("AND FOLDER_SHARE.FP_ID=" + folderId + " ");
		sql.append(
			"AND SYS_PERSON.PERSONUUID=FOLDER_SHAREACCESS.FSC_PERSONID ");
		sql.append("ORDER BY FOLDER_SHARE.FS_DATE DESC");

		dao.setSearchSQL(sql.toString());

		List list = null;
		try {
			list = factory.find(new SharePersonVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getFolderList(String userId) {
		ManagementPackageSearchDAO dao = new ManagementPackageSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"FOLDER_MANAGEMENT.FM_PERSONID,FOLDER_MANAGEMENT.FM_STORETYPE,FOLDER_MANAGEMENT.FM_STOREPATH,FOLDER_MANAGEMENT.FM_CREATEDATE,FOLDER_MANAGEMENT.FM_MODIFYDATE,FOLDER_PACKAGE.FP_ID,FOLDER_PACKAGE.FM_ID,FOLDER_PACKAGE.FOL_FP_ID,FOLDER_PACKAGE.FP_NAME,FOLDER_PACKAGE.FP_DESC,FOLDER_PACKAGE.FP_CREATEDATE,FOLDER_PACKAGE.FP_MODIFYDATE,FOLDER_PACKAGE.FP_SIZE,FOLDER_PACKAGE.FP_MIME_TYPE,FOLDER_PACKAGE.FP_ISFILE,FOLDER_PACKAGE.FP_LEVEL ");
		sql.append("FROM ");
		sql.append("FOLDER_MANAGEMENT,FOLDER_PACKAGE ");
		sql.append(
			" where FOLDER_MANAGEMENT.FM_ID=FOLDER_PACKAGE.FM_ID and FOLDER_PACKAGE.FP_ISFILE='1' and  FOLDER_MANAGEMENT.FM_PERSONID='"
				+ userId
				+ "' ");

		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new ManagementPackageVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * get path list
	 * @param parentId
	 * @return
	 */

	public StringBuffer getFolderPath(Integer fpId, StringBuffer path) {
		FolderPackageVO vo = getFolderVO(fpId);
		path.insert(0, "/");
		path.insert(1, vo.getFpName());
		if (!vo.getFolFpId().equals(new Integer(1)))
			getFolderPath(vo.getFolFpId(), path);
		return path;
	}
	/**
		 * get path list
		 * @param parentId
		 * @return
		 */
	public String getShareFolderPath(
		Integer fpId,
		Integer rootFpId,
		StringBuffer path) {
		FolderPackageVO vo = getFolderVO(fpId);
		path.insert(0, "/");
		path.insert(1, vo.getFpName());
		if (!vo.getFolFpId().equals(rootFpId))
			getShareFolderPath(vo.getFolFpId(), rootFpId, path);
		return path.toString();
	}

	/**
	 * get file and folder list
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */

	public List getFileList(Integer parentId) {
		FolderPackageDAO dao = new FolderPackageDAO();
		dao.setFolFpId(parentId);
		dao.addOrderBy("fpIsfile", false);
		dao.addOrderBy("fpModifydate", false);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		
		List list = null;
		try {
			list = factory.find(new FolderPackageVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 得到子文件夹
	 * @param parentId
	 * @return
	 */

	public List getFolderList(Integer parentId) {
		FolderPackageDAO dao = new FolderPackageDAO();
		dao.setFolFpId(parentId);
		dao.setFpIsfile("1");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new FolderPackageVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	* get file and folder list
	* 
	* @author Administrator
	* 
	* To change the template for this generated type comment go to
	* Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	*/

	public List getFolderList(Integer parentId, Integer fmId) {
		FolderPackageDAO dao = new FolderPackageDAO();
		dao.setFolFpId(parentId);
		dao.setFmId(fmId);
		dao.addOrderBy("fpIsfile", false);
		dao.addOrderBy("fpModifydate", false);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new FolderPackageVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * get file and folder list by parentId and userId
	 * @param fpId
	 * @return
	 */
	//	public List getFolderList(Integer parentId, String userId) {
	//		ManagementPackageSearchDAO dao = new ManagementPackageSearchDAO();
	//		StringBuffer sql = new StringBuffer();
	//		sql.append("SELECT ");
	//		sql.append(
	//			"FOLDER_MANAGEMENT.FM_PERSONID,FOLDER_MANAGEMENT.FM_STORETYPE,FOLDER_MANAGEMENT.FM_STOREPATH,FOLDER_MANAGEMENT.FM_CREATEDATE,FOLDER_MANAGEMENT.FM_MODIFYDATE,FOLDER_PACKAGE.FP_ID,FOLDER_PACKAGE.FM_ID,FOLDER_PACKAGE.FOL_FP_ID,FOLDER_PACKAGE.FP_NAME,FOLDER_PACKAGE.FP_DESC,FOLDER_PACKAGE.FP_CREATEDATE,FOLDER_PACKAGE.FP_MODIFYDATE,FOLDER_PACKAGE.FP_SIZE,FOLDER_PACKAGE.FP_MIME_TYPE,FOLDER_PACKAGE.FP_ISFILE,FOLDER_PACKAGE.FP_LEVEL ");
	//		sql.append("FROM ");
	//		sql.append("FOLDER_MANAGEMENT,FOLDER_PACKAGE ");
	//		sql.append(
	//			" where FOLDER_MANAGEMENT.FM_ID=FOLDER_PACKAGE.FM_ID  and  FOLDER_MANAGEMENT.FM_PERSONID='"
	//				+ userId
	//				+ "' and FOLDER_PACKAGE.FOL_FP_ID="
	//				+ parentId
	//				+ " ");
	//		dao.setSearchSQL(sql.toString());
	//		DAOFactory factory = new DAOFactory(conn);
	//		factory.setDAO(dao);
	//		List list = null;
	//		try {
	//			list = factory.find(new ManagementPackageVO());
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return list;
	//	}

	/**
	 * get folder list by id
	 * @param parentId
	 * @param parentLevel
	 * @return
	 */
	public List getFolderListById(Integer fpId) {
		List childList = getFolderList(fpId);
		List tempList = getFolderList(fpId);
		if (tempList != null) {
			for (int i = 0; i < tempList.size(); i++) {
				FolderPackageVO vo = (FolderPackageVO) childList.get(i);
				childList.addAll(getFolderListById(vo.getFpId()));
			}
		}
		return childList;
	}

	/**
	* add folder
	* 
	* @author Administrator
	* 
	* To change the template for this generated type comment go to
	* Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	*/

	public Integer addFolder(FolderPackageVO vo) {
		FolderPackageDAO dao = new FolderPackageDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
			//System.out.println("foldererror==================>>" + e.getMessage());
		}
		return dao.getFpId();
	}

	/**
	 * add file
	 * @param name
	 * @param parentId
	 * @return
	 */
	public void addFile(FolderDbfileVO vo) {
		FolderDbfileDAO dao = new FolderDbfileDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}

	/**
	* check same name
	* 
	* @author Administrator
	* 
	* To change the template for this generated type comment go to
	* Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	*/
	public boolean checkSameName(String name, Integer parentId, Integer fmId) {
		FolderPackageDAO dao = new FolderPackageDAO();
		dao.setFolFpId(parentId);
		dao.setFpName(name);
		if (parentId.intValue() == 1) {
			dao.setFmId(fmId);
		}
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new FolderPackageVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null) {
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	* check same sharename
	* 
	* @author Administrator
	* 
	* To change the template for this generated type comment go to
	* Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	*/
	public boolean checkSameShareName(String sharename) {

		FolderShareDAO dao = new FolderShareDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setFsName(sharename);
		List list = null;
		try {
			list = factory.find(new FolderShareVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null) {
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	* get parent vo
	* 
	* @author Administrator
	* 
	* To change the template for this generated type comment go to
	* Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	*/
	public FolderPackageVO getFolderVO(Integer fpId) {
		FolderPackageDAO dao = new FolderPackageDAO();
		dao.setFpId(fpId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		FolderPackageVO vo = null;
		try {
			vo =
				(FolderPackageVO) factory.findByPrimaryKey(
					new FolderPackageVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * get file list
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */

	public List getDbFileList(Integer fp_id) throws HandlerException {

		DbfilePackageSearchDAO dao = new DbfilePackageSearchDAO();

		DAOFactory factory = new DAOFactory(conn);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"FOLDER_DBFILE.FDBF_ID,FOLDER_DBFILE.FDBF_ACCESSORY,FOLDER_PACKAGE.FP_ID,FOLDER_PACKAGE.FM_ID,FOLDER_PACKAGE.FOL_FP_ID,FOLDER_PACKAGE.FP_NAME,FOLDER_PACKAGE.FP_DESC,FOLDER_PACKAGE.FP_CREATEDATE,FOLDER_PACKAGE.FP_MODIFYDATE,FOLDER_PACKAGE.FP_SIZE,FOLDER_PACKAGE.FP_MIME_TYPE,FOLDER_PACKAGE.FP_ISFILE,FOLDER_PACKAGE.FP_LEVEL ");
		sql.append("FROM ");
		sql.append("FOLDER_DBFILE,FOLDER_PACKAGE ");
		sql.append(
			" where FOLDER_DBFILE.FP_ID = FOLDER_PACKAGE.FP_ID and FOLDER_PACKAGE.fp_id = "
				+ fp_id);
		//		System.out.println("sql===========" + sql.toString());
		dao.setSearchSQL(sql.toString());

		factory.setDAO(dao);
		try {
			List list = factory.find(new DbfilePackageVO());
			return list;

		} catch (DAOException e) {
			throw new HandlerException(e);
		}

	}

	/**
	 * del single file
	 * 
	 * @author Administrator
	 * 
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void delFile(Integer folderId) {
		FolderPackageDAO dao = new FolderPackageDAO();
		dao.setFpId(folderId);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void updateFolder(Integer sourceId, Integer targetId) {
		DAOFactory factory = new DAOFactory(conn);
		FolderPackageDAO dao = new FolderPackageDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setFpId(sourceId);
		try {
			dao = (FolderPackageDAO) factory.findByPrimaryKey();
			dao.setFolFpId(targetId);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * get user list
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List getUserList(String userId) {
		FolderManagementDAO dao = new FolderManagementDAO();
		dao.setFmPersonid(userId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new FolderManagementVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * get share list
	 * @param userId
	 * @return
	 */
	public List getShareList(Integer fpId) {
		FolderShareDAO dao = new FolderShareDAO();
		dao.setFpId(fpId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new FolderShareVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * get share list
	 * @param vo
	 * @return
	 */
	public List getShareList(String userId) {

		ShareShareaccessSearchDAO dao = new ShareShareaccessSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"FOLDER_SHARE.FM_ID,FOLDER_SHARE.FP_ID,FOLDER_SHARE.FS_NAME,FOLDER_SHARE.FS_DATE,FOLDER_SHAREACCESS.FSC_ID,FOLDER_SHAREACCESS.FS_ID,FOLDER_SHAREACCESS.FSC_PERSONID,FOLDER_SHAREACCESS.FSC_ACCESSRIGHT ");
		sql.append("FROM ");
		sql.append("FOLDER_SHARE,FOLDER_SHAREACCESS ");
		sql.append(
			" where FOLDER_SHARE.FS_ID = FOLDER_SHAREACCESS.FS_ID and FOLDER_SHAREACCESS.FSC_PERSONID = '"
				+ userId
				+ "' ");
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new ShareShareaccessVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * create user
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public Integer addUser(FolderManagementVO vo) {
		FolderManagementDAO dao = new FolderManagementDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao.getFmId();

	}
	/**
	 * add share 
	 * @param fpId
	 * @return
	 */
	public Integer addShareFolder(FolderShareVO vo) {
		FolderShareDAO dao = new FolderShareDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao.getFsId();

	}

	/**
	 * add share 
	 * @param fpId
	 * @return
	 */
	public Integer getFolderIDByShareName(String str) {
		FolderShareDAO dao = new FolderShareDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setFsName(str);
		List list = null;
		Iterator it = null;
		Integer kk = null;
		try {
			list = factory.find(new FolderShareVO());
			if (list != null) {
				it = list.iterator();
			}

			while (it.hasNext()) {
				FolderShareVO vo = (FolderShareVO) it.next();
				kk = vo.getFpId();
			}
			return kk;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao.getFsId();

	}
	/**
	 * add share access
	 * @param fpId
	 * @return
	 */
	public void addShareAccess(FolderShareaccessVO vo) {
		FolderShareaccessDAO dao = new FolderShareaccessDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * update shareAccess 
	 * @param vo
	 * @return
	 */
	public Integer getShareAccessID(String userId, Integer folderID) {

		System.out.println(
			"parentId======================================================qqqqqqqqq");
		ShareShareaccessSearchDAO dao = new ShareShareaccessSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"FOLDER_SHARE.FM_ID,FOLDER_SHARE.FP_ID,FOLDER_SHARE.FS_NAME,FOLDER_SHARE.FS_DATE,FOLDER_SHAREACCESS.FSC_ID,FOLDER_SHAREACCESS.FS_ID,FOLDER_SHAREACCESS.FSC_PERSONID,FOLDER_SHAREACCESS.FSC_ACCESSRIGHT ");
		sql.append("FROM ");
		sql.append("FOLDER_SHARE,FOLDER_SHAREACCESS ");
		sql.append(
			" where FOLDER_SHARE.FS_ID = FOLDER_SHAREACCESS.FS_ID and FOLDER_SHAREACCESS.FSC_PERSONID = '"
				+ userId
				+ "' ");
		sql.append(" and FOLDER_SHARE.FP_ID = " + folderID);
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		Integer id = null;

		try {
			list = factory.find(new ShareShareaccessVO());
			System.out.println(
				"parentId================         " + list.size());
			//	System.out.println("parentId================writer   " +writer);
			Iterator it = list.iterator();

			while (it.hasNext()) {

				System.out.println(
					"parentId================wwwwwwwwwwwwwwwwwwwwwwwwww");
				ShareShareaccessVO vo = new ShareShareaccessVO();
				vo = (ShareShareaccessVO) it.next();
				id = vo.getFscId();

				/*	FolderShareaccessDAO dao1 = new FolderShareaccessDAO();
					dao1.setConnection(conn);
					dao.setFscId(vo.getFscId());
					dao1 = (FolderShareaccessDAO)factory.findByPrimaryKey();
					
					if (writer.equals("yes"))
						dao1.setFscAccessright("1");
					else
						dao1.setFscAccessright("0");
					//dao1.setFscAccessright(writer);
					System.out.println("parentId================writer   " +dao1.getFscAccessright());
					dao1.update(true);*/
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getAccessRight(Integer id) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		FolderShareaccessDAO dao = new FolderShareaccessDAO();
		factory.setDAO(dao);
		dao.setFscId(id);
		try {
			FolderShareaccessVO vo =
				(FolderShareaccessVO) factory.findByPrimaryKey(
					new FolderShareaccessVO());
			return vo.getFscAccessright();
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public void updateAccess(String writer, Integer id) {
		DAOFactory factory = new DAOFactory(conn);
		FolderShareaccessDAO dao = new FolderShareaccessDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setFscId(id);
		try {
			dao = (FolderShareaccessDAO) factory.findByPrimaryKey();
			if (writer.equals("yes"))
				dao.setFscAccessright("1");
			else
				dao.setFscAccessright("0");
			dao.update(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get folder vo
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public FolderPackageVO getBoardVO(Integer fpId) {
		FolderPackageDAO dao = new FolderPackageDAO();
		dao.setFpId(fpId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		FolderPackageVO vo = null;
		try {
			vo =
				(FolderPackageVO) factory.findByPrimaryKey(
					new FolderPackageVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	/**
	 * 
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public void updateFolder(FolderPackageVO vo) {

		DAOFactory factory = new DAOFactory(conn);
		FolderPackageDAO dao = new FolderPackageDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setFpId(vo.getFpId());
		try {
			dao = (FolderPackageDAO) factory.findByPrimaryKey();
			dao.setFpName(vo.getFpName());
			dao.setFpDesc(vo.getFpDesc());
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * string to list
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public List sringToList(String s) {
		ArrayList list = new ArrayList();
		int index0 = s.indexOf(",", 0);
		if (index0 == -1)
			return null;
		int index1 = 0;
		while (index1 < s.length()) {
			index1 = s.indexOf(",", index0 + 1);
			if (index1 == -1) {
				index1 = s.length();
			}
			list.add(s.substring(index0 + 1, index1));
			index0 = index1;
		}
		return list;
	}
	/**
		 * batch delete folder
		 * @author Administrator
		 *
		 * To change the template for this generated type comment go to
		 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
		 */
	public void delShareUser(Integer fsId) {
		FolderShareaccessDAO dao = new FolderShareaccessDAO();
		dao.setFsId(fsId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setConnection(conn);
		try {
			factory.batchDelete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到用户所有文件大小
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public Integer contSize(Integer fm_id) {
		String sql =
			" SELECT SUM( FOLDER_PACKAGE.FP_SIZE) AS FOLDER_SIZE FROM FOLDER_PACKAGE WHERE FM_ID="
				+ fm_id;
				
		Statement stmt = null;
		ResultSet rs = null;
		int folderSize = 0;
		try {
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			if (rs != null) {
				if (rs.next()) {
					folderSize = rs.getInt("FOLDER_SIZE");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return new Integer(folderSize);

	}

	public void deleteShare(Integer folderId, String personId) {
		DelShareSearchDAO dao0 = new DelShareSearchDAO();
		dao0.setConnection(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao0);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("FOLDER_SHAREACCESS.FSC_ID ");
		sql.append("FROM ");
		sql.append("FOLDER_SHARE,FOLDER_SHAREACCESS ");
		sql.append(" WHERE ");
		sql.append("FOLDER_SHAREACCESS.FS_ID=FOLDER_SHARE.FS_ID ");
		sql.append("AND FOLDER_SHARE.FP_ID=" + folderId + " ");
		sql.append("AND FOLDER_SHAREACCESS.FSC_PERSONID='" + personId + "' ");

		dao0.setSearchSQL(sql.toString());

		try {
			List list = factory.find();
			if (list != null && list.size() != 0) {
				dao0 = (DelShareSearchDAO) list.get(0);
			} else {
				dao0 = null;
			}
		} catch (Exception e) {
			dao0 = null;
		}

		if (dao0 != null) {
			FolderShareaccessDAO dao = new FolderShareaccessDAO();
			dao.setFscId(dao0.getFscId());
			factory.setDAO(dao);
			try {
				dao = (FolderShareaccessDAO) factory.findByPrimaryKey();
				dao.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查看文件夹或文件是否共享
	 * @param folderId
	 * @return 真或假
	 */

	public boolean isShare(Integer folderId) {
		String sql =
			" SELECT COUNT( FOLDER_PACKAGE.FP_ID) AS ISSHARE FROM FOLDER_PACKAGE,FOLDER_SHARE WHERE FOLDER_PACKAGE.FP_ID=FOLDER_SHARE.FP_ID AND FOLDER_PACKAGE.FP_ID="
				+ folderId;
				
		Statement stmt = null;		
		ResultSet rs = null;
		int share = 0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs != null) {
				if (rs.next()) {
					share = rs.getInt("ISSHARE");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (share > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 节点是否有子文件夹
	 * @param folderId
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
	/**生成xload tree的list
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */

	public List buildXloadTreeList(List list) {
		List treeList = new ArrayList();
		if (list != null) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				FolderPackageVO vo = (FolderPackageVO) it.next();
				FolderTreeVO treeVO = new FolderTreeVO();
				treeVO.setFolderPackageVO(vo);
				treeVO.setHasChild(hasChild(vo.getFpId()));
				treeVO.setIsShare(isShare(vo.getFpId()));
				treeList.add(treeVO);
			}
		}
		return treeList;
	}

	public List getFolderName(Integer FolderId) {
		List list = new ArrayList();
		DAOFactory factory = new DAOFactory(conn);
		FolderPackageDAO fDao = new FolderPackageDAO();
		fDao.setFpId(FolderId);
		factory.setDAO(fDao);
		FolderPackageVO jVO = null;
		try {
			list = factory.find(new FolderPackageVO());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void deleteShare(Integer folderId) {

		DAOFactory factory = new DAOFactory(conn);
		FolderShareDAO sDao = new FolderShareDAO();
		sDao.setConnection(conn);
		factory.setDAO(sDao);
		sDao.setFpId(folderId);

		try {
			factory.batchDelete();

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public boolean isShare1(Integer folderid) {

		List list = new ArrayList();
		DAOFactory factory = new DAOFactory(conn);
		FolderShareDAO jDao = new FolderShareDAO();

		jDao.setFpId(folderid);
		factory.setDAO(jDao);
		FolderShareVO jVO = null;

		try {
			list = factory.find(new FolderShareVO());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void updateShareName(Integer folderId, String shareName) {

		DAOFactory factory = new DAOFactory(conn);
		FolderShareDAO dao = new FolderShareDAO();
		FolderShareVO vo = null;
		factory.setDAO(dao);
		dao.setConnection(conn);
		List list = this.getShareList(folderId);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			vo = (FolderShareVO) it.next();
			try {
				dao.setFsId(vo.getFsId());
				dao = (FolderShareDAO) factory.findByPrimaryKey();
				dao.setFsName(shareName);
				dao.update(true);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}

	}
}

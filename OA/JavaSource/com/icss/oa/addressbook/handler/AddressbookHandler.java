/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.addressbook.handler;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.addressbook.dao.*;
import com.icss.oa.addressbook.vo.*;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class AddressbookHandler {
	private Connection conn;

	public String filepath = null;
	public AddressbookHandler(Connection conn) {
		this.conn = conn;
	}
	//列出左边所示的文件夹

	public List getFolderList(String userId) {
		AddressbookSearchDAO dao = new AddressbookSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"ADDRESSBOOK_MANAGER.ABM_ID,ADDRESSBOOK_MANAGER.ABM_OWNER,ADDRESSBOOK_MANAGER.ABM_CRETETIME,ADDRESSBOOK_MANAGER.ABM_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABM_ID,ADDRESSBOOK_FOLDER.ABF_NAME,ADDRESSBOOK_FOLDER.ABF_DESCRIPT,ADDRESSBOOK_FOLDER.ABF_CREATETIME ,ADDRESSBOOK_FOLDER.ABF_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_FLAG  ");
		sql.append("FROM ");
		sql.append("ADDRESSBOOK_MANAGER  ,ADDRESSBOOK_FOLDER   ");
		sql.append(" where ADDRESSBOOK_MANAGER.ABM_ID=ADDRESSBOOK_FOLDER.Add_ABM_ID  and  ADDRESSBOOK_MANAGER.ABM_OWNER='" + userId + "' ");
		System.out.println("**********sql********" + sql);
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookSearchVO());
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

	/**增加用户的通讯录
	 * @param vo
	 * @return
	 */
	public Integer addUser(AddressbookManagerVO vo) {
		AddressbookManagerDAO dao = new AddressbookManagerDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao.getAbmId();
	}
	/**
	 * @param 通过用户ID取得通讯录主信息
	 * @return
	 */
	public List getUserList(String userId) {
		AddressbookManagerDAO dao = new AddressbookManagerDAO();
		dao.setAbmOwner(userId);
		dao.addOrderBy("abcName", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookManagerVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**列出文件夹
	 * @param parentId
	 * @return
	 */
	public List getFileList(Integer parentId) {
		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setAddAbfId(parentId);
		dao.addOrderBy("AbfFlag", false);
		dao.addOrderBy("abfName", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookFolderVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**列出具体信息
		 * @param parentId
		 * @return
		 */
	public List getdetailFileList(Integer abfcid) {
		AddressbookContentDAO dao = new AddressbookContentDAO();
		dao.setAddAbfcId(abfcid);
		//dao.addOrderBy("AbfFlag", false);
		dao.addOrderBy("abcName", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookContentVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param parentId
	 * @param integer
	 * @return
	 */
	public List getFolderList(Integer parentId, Integer addabmid) {
		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setAddAbfId(parentId);
		dao.setAddAbmId(addabmid);
		dao.addOrderBy("abfflag", false);
		dao.addOrderBy("abfName", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		//System.out.println("::::::::::(haha)::::::::::::");
		try {
			list = factory.find(new AddressbookFolderVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**判断是否分组重名
	 * @param folderName
	 * @param parentId
	 * @param integer
	 * @return
	 */
	public boolean checkSameName(String folderName, Integer parentId, Integer abmId) {
		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setAddAbfId(parentId);
		dao.setAbfName(folderName);
		if (parentId.intValue() == 1) {
			dao.setAddAbmId(abmId);
		}
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookFolderVO());
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
	/**增加分组
	 * @param vo
	 */
	public Integer addFolder(AddressbookFolderVO vo) {
		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			System.out.println("foldererror==================>>" + e.getMessage());
		}
		return dao.getAbfId();

	}
	/**
	 * 
	 * @param 
	 * @return 返回对应abfid的信息集
	 */
	public AddressbookFolderVO getFolderVO(Integer abfId) {
		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setAbfId(abfId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		AddressbookFolderVO vo = null;
		try {
			vo = (AddressbookFolderVO) factory.findByPrimaryKey(new AddressbookFolderVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	/**返回当前路径
	 * @param parentId
	 * @param bpath
	 * @return
	 */
	public StringBuffer getFolderPath(Integer abfId, StringBuffer path) {
		AddressbookFolderVO vo = getFolderVO(abfId);
		path.insert(0, "/");
		path.insert(1, vo.getAbfName());
		if (!vo.getAddAbfId().equals(new Integer(1)))
			getFolderPath(vo.getAddAbfId(), path);
		return path;
	}
	/**是否有子文件夹
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
	/**是否有子文件夹
			 * @param list
			 * @return
			 */

	public boolean hasChildinfo(Integer folderId) {
		List folderList = getFolderinfoList(folderId);
		if (folderList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	/**得到子文件夹
	 * @param folderId
	 * @return
	 */
	private List getFolderList(Integer parentId) {

		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setAddAbfId(parentId);
		dao.setAbfFlag("1");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookFolderVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	/**得到子文件夹
		 * @param folderId
		 * @return
		 */
	private List getFolderinfoList(Integer parentId) {

		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setAddAbfId(parentId);
		//dao.setAbfFlag("1");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookFolderVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	/**
	 * 打印时返回根分组所有信息
	 * @param abmId
	 * @return
	 */

	public List getFoldersList(Integer abmId, Integer parentId) {
		AddressbookAllSearchDAO dao = new AddressbookAllSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"ADDRESSBOOK_MANAGER.ABM_ID,ADDRESSBOOK_MANAGER.ABM_OWNER,ADDRESSBOOK_MANAGER.ABM_CRETETIME,ADDRESSBOOK_MANAGER.ABM_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABM_ID,ADDRESSBOOK_FOLDER.ABF_NAME,ADDRESSBOOK_FOLDER.ABF_DESCRIPT,ADDRESSBOOK_FOLDER.ABF_CREATETIME ,ADDRESSBOOK_FOLDER.ABF_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_FLAG,ADDRESSBOOK_CONTENT.ABC_ID,ADDRESSBOOK_CONTENT.ADD_ABFC_ID,ADDRESSBOOK_CONTENT.ABC_NAME,ADDRESSBOOK_CONTENT.ABC_COMPANYADDRESS,ADDRESSBOOK_CONTENT.ABC_FAMILYADDRESS,ADDRESSBOOK_CONTENT.ABC_COMPANYTEL,ADDRESSBOOK_CONTENT.ABC_FAMILYTEL,ADDRESSBOOK_CONTENT.ABC_CELLPHONE,ADDRESSBOOK_CONTENT.ABC_EMAIL,ADDRESSBOOK_CONTENT.ABC_EMAILSEC,ADDRESSBOOK_CONTENT.ABC_COMPANY,ADDRESSBOOK_CONTENT.ABC_LEVER,ADDRESSBOOK_CONTENT.ABC_CREATETIME,ADDRESSBOOK_CONTENT.ABC_UPDATETIME,ADDRESSBOOK_CONTENT.ABC_MEMO  ");
		sql.append(" FROM PERSON_SYNC,RO_PERSON,RO_PERSONACCOUNT ");
		sql.append(" ");
		
		sql.append("ADDRESSBOOK_MANAGER  ,ADDRESSBOOK_FOLDER LEFT JOIN ADDRESSBOOK_CONTENT ON ADDRESSBOOK_FOLDER.ABF_ID=ADDRESSBOOK_CONTENT.ADD_ABFC_ID");
		sql.append(" where ADDRESSBOOK_MANAGER.ABM_ID=ADDRESSBOOK_FOLDER.ADD_ABM_ID    and ADDRESSBOOK_MANAGER.ABM_ID='" + abmId + "' " + "and ADDRESSBOOK_FOLDER.ADD_ABF_ID='" + parentId + "' ");
		sql.append("ORDER BY ADDRESSBOOK_FOLDER.ABF_NAME,ADDRESSBOOK_CONTENT.ABC_NAME");
		//System.out.println("**********sql2********"+sql);
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookAllSearchVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/**得到子分组所有信息
		 * @param folderId
		 * @return
		 */
	public List getchildList(Integer parentId) {

		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setAddAbfId(parentId);

		dao.addOrderBy("abfFlag", false);
		dao.addOrderBy("abfName", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookFolderVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	/**生成xload tree的list
	 * @param list
	 * @return
	 */
	public List buildXloadTreeList(List list) {
		List treeList = new ArrayList();
		if (list != null) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				AddressbookFolderVO vo = (AddressbookFolderVO) it.next();
				AddressbookTreeVO treeVO = new AddressbookTreeVO();
				treeVO.setAddressbookFolderVO(vo);
				treeVO.setHasChild(hasChild(vo.getAbfId()));
				//treeVO.setIsShare(isShare(vo.getFpId()));
				treeList.add(treeVO);
			}
		}
		return treeList;
	}
	/**返回首进入页面所有数据信息
	 * @param parentId
	 * @param integer
	 * @return
	 */
	public List getAddrbFolderList(Integer parentId, Integer abmId) {
		AddressbookAllSearchDAO dao = new AddressbookAllSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"ADDRESSBOOK_MANAGER.ABM_ID,ADDRESSBOOK_MANAGER.ABM_OWNER,ADDRESSBOOK_MANAGER.ABM_CRETETIME,ADDRESSBOOK_MANAGER.ABM_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABM_ID,ADDRESSBOOK_FOLDER.ABF_NAME,ADDRESSBOOK_FOLDER.ABF_DESCRIPT,ADDRESSBOOK_FOLDER.ABF_CREATETIME ,ADDRESSBOOK_FOLDER.ABF_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_FLAG,ADDRESSBOOK_CONTENT.ABC_ID,ADDRESSBOOK_CONTENT.ADD_ABFC_ID,ADDRESSBOOK_CONTENT.ABC_NAME,ADDRESSBOOK_CONTENT.ABC_COMPANYADDRESS,ADDRESSBOOK_CONTENT.ABC_FAMILYADDRESS,ADDRESSBOOK_CONTENT.ABC_COMPANYTEL,ADDRESSBOOK_CONTENT.ABC_FAMILYTEL,ADDRESSBOOK_CONTENT.ABC_CELLPHONE,ADDRESSBOOK_CONTENT.ABC_EMAIL,ADDRESSBOOK_CONTENT.ABC_EMAILSEC,ADDRESSBOOK_CONTENT.ABC_COMPANY,ADDRESSBOOK_CONTENT.ABC_LEVER,ADDRESSBOOK_CONTENT.ABC_CREATETIME,ADDRESSBOOK_CONTENT.ABC_UPDATETIME,ADDRESSBOOK_CONTENT.ABC_MEMO  ");
		sql.append("FROM ");
		sql.append("ADDRESSBOOK_MANAGER  ,ADDRESSBOOK_FOLDER LEFT JOIN ADDRESSBOOK_CONTENT ON ADDRESSBOOK_FOLDER.ABF_ID=ADDRESSBOOK_CONTENT.ADD_ABFC_ID");
		sql.append(" where ADDRESSBOOK_MANAGER.ABM_ID=ADDRESSBOOK_FOLDER.ADD_ABM_ID    and ADDRESSBOOK_MANAGER.ABM_ID='" + abmId + "' " + "and ADDRESSBOOK_FOLDER.ADD_ABF_ID='" + parentId + "' ");
		sql.append("ORDER BY ADDRESSBOOK_FOLDER.ABF_FLAG DESC,ADDRESSBOOK_FOLDER.ABF_NAME,ADDRESSBOOK_CONTENT.ABC_NAME");
		System.out.println("**********sql2********" + sql);
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookAllSearchVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**创建新的通讯录
	 * @param vo
	 */
	public Integer addAddressbook(AddressbookContentVO vo) {
		AddressbookContentDAO dao = new AddressbookContentDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println("addressbookrerror==================>>" + e.getMessage());
		}
		return dao.getAbcId();

	}
	/**修改分组信息
	 * @param vo
	 */
	public void updateAddrbFolder(AddressbookFolderVO vo, Integer abfid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setAbfId(abfid);
		try {
			dao = (AddressbookFolderDAO) factory.findByPrimaryKey();
			dao.setAbfName(vo.getAbfName());
			dao.setAbfDescript(vo.getAbfDescript());
			dao.setAbfUpdatetime(vo.getAbfUpdatetime());
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**修改通讯录
	 * @param vo
	 * @param abcId
	 */
	public void updateAddressbook(AddressbookContentVO vo, Integer abcId) {
		DAOFactory factory = new DAOFactory(conn);
		AddressbookContentDAO dao = new AddressbookContentDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setAbcId(abcId);
		try {
			dao = (AddressbookContentDAO) factory.findByPrimaryKey();
			dao.setAbcName(vo.getAbcName());
			dao.setAbcCompany(vo.getAbcCompany());
			dao.setAbcCompanyaddress(vo.getAbcCompanyaddress());
			dao.setAbcCompanytel(vo.getAbcCompanytel());
			dao.setAbcFamilyaddress(vo.getAbcFamilyaddress());
			dao.setAbcCompanytel(vo.getAbcCompanytel());
			dao.setAbcCellphone(vo.getAbcCellphone());
			dao.setAbcEmail(vo.getAbcEmail());
			dao.setAbcEmailsec(vo.getAbcEmailsec());
			dao.setAbcMemo(vo.getAbcMemo());
			dao.setAbcLever(vo.getAbcLever());
			dao.setAbcFamilytel(vo.getAbcFamilytel());

			dao.setAbcUpdatetime(vo.getAbcUpdatetime());
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**删除分组
	 * @param abfId
	 */
	public void delAddrbFolder(Integer abfId) {
		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setAbfId(abfId);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}
	/**删除通讯录
	 * @param abfcId
	 */
	public void delAddressbook(Integer abfcId) {
		AddressbookFolderDAO dao = new AddressbookFolderDAO();
		dao.setAbfId(abfcId);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}
	/**返回查询结果集
	 
	 * @param integer
	 * @param queryname
	 * @param querycompany
	 * @param querycompanyaddress
	 * @param queryfamilyaddress
	 * @param querycompanytel
	 * @param queryfamilytel
	 * @param querycellphone
	 * @param queryemail
	 * @param querymemo
	 * @return
	 */
	public List getUserSearchList(
		Integer abmId,
		String queryname,
		String querycompany,
		String querycompanyaddress,
		String queryfamilyaddress,
		String querycompanytel,
		String queryfamilytel,
		String querycellphone,
		String queryemail,
		String querymemo) {
		AddressbookAllSearchDAO dao = new AddressbookAllSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"ADDRESSBOOK_MANAGER.ABM_ID,ADDRESSBOOK_MANAGER.ABM_OWNER,ADDRESSBOOK_MANAGER.ABM_CRETETIME,ADDRESSBOOK_MANAGER.ABM_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABM_ID,ADDRESSBOOK_FOLDER.ABF_NAME,ADDRESSBOOK_FOLDER.ABF_DESCRIPT,ADDRESSBOOK_FOLDER.ABF_CREATETIME ,ADDRESSBOOK_FOLDER.ABF_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_FLAG,ADDRESSBOOK_CONTENT.ABC_ID,ADDRESSBOOK_CONTENT.ADD_ABFC_ID,ADDRESSBOOK_CONTENT.ABC_NAME,ADDRESSBOOK_CONTENT.ABC_COMPANYADDRESS,ADDRESSBOOK_CONTENT.ABC_FAMILYADDRESS,ADDRESSBOOK_CONTENT.ABC_COMPANYTEL,ADDRESSBOOK_CONTENT.ABC_FAMILYTEL,ADDRESSBOOK_CONTENT.ABC_CELLPHONE,ADDRESSBOOK_CONTENT.ABC_EMAIL,ADDRESSBOOK_CONTENT.ABC_EMAILSEC,ADDRESSBOOK_CONTENT.ABC_COMPANY,ADDRESSBOOK_CONTENT.ABC_LEVER,ADDRESSBOOK_CONTENT.ABC_CREATETIME,ADDRESSBOOK_CONTENT.ABC_UPDATETIME,ADDRESSBOOK_CONTENT.ABC_MEMO  ");
		sql.append("FROM ");
		sql.append("ADDRESSBOOK_MANAGER  ,ADDRESSBOOK_FOLDER LEFT JOIN ADDRESSBOOK_CONTENT ON ADDRESSBOOK_FOLDER.ABF_ID=ADDRESSBOOK_CONTENT.ADD_ABFC_ID");
		sql.append(" where ADDRESSBOOK_MANAGER.ABM_ID=ADDRESSBOOK_FOLDER.ADD_ABM_ID    and ADDRESSBOOK_MANAGER.ABM_ID='" + abmId + "' ");
		if (queryname != null && !queryname.equals("")) {
			sql.append("AND ADDRESSBOOK_CONTENT.ABC_NAME LIKE '%" + queryname + "%' ");
		}
		if (querycompany != null && !querycompany.equals("")) {
			sql.append("AND ADDRESSBOOK_CONTENT.ABC_COMPANY LIKE '%" + querycompany + "%' ");
		}
		if (querycompanyaddress != null && !querycompanyaddress.equals("")) {
			sql.append("AND ADDRESSBOOK_CONTENT.ABC_COMPANYADDRESS LIKE '%" + querycompanyaddress + "%' ");
		}
		if (queryfamilyaddress != null && !queryfamilyaddress.equals("")) {
			sql.append("AND ADDRESSBOOK_CONTENT.ABC_FAMILYADDRESS LIKE '%" + queryfamilyaddress + "%' ");
		}
		if (querycompanytel != null && !querycompanytel.equals("")) {
			sql.append("AND ADDRESSBOOK_CONTENT.ABC_COMPANYTEL LIKE '%" + querycompanytel + "%' ");
		}
		if (querycellphone != null && !querycellphone.equals("")) {
			sql.append("AND ADDRESSBOOK_CONTENT.ABC_CELLPHONE LIKE '%" + querycellphone + "%' ");
		}
		if (queryemail != null && !queryemail.equals("")) {
			sql.append("AND ADDRESSBOOK_CONTENT.ABC_EMAIL LIKE '%" + queryemail + "%' ");
		}
		if (querymemo != null && !querymemo.equals("")) {
			sql.append("AND ADDRESSBOOK_CONTENT.ABC_MEMO LIKE '%" + querymemo + "%' ");
		}
		if (queryfamilytel != null && !queryfamilytel.equals("")) {
			sql.append("AND ADDRESSBOOK_CONTENT.ABC_FAMILYTEL LIKE '%" + queryfamilytel + "%' ");
		}
		sql.append("AND ADDRESSBOOK_FOLDER.ABF_FLAG <>'1'  ");
		sql.append("ORDER BY ADDRESSBOOK_FOLDER.ABF_FLAG DESC,ADDRESSBOOK_CONTENT.ABC_NAME ");

		System.out.println("**********sql3********" + sql);
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookAllSearchVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**返回打印内容
	 * @param integer
	 * @return
	 */
	public List getAllAddressbookList(Integer abmId) {
		AddressbookAllSearchDAO dao = new AddressbookAllSearchDAO();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"ADDRESSBOOK_MANAGER.ABM_ID,ADDRESSBOOK_MANAGER.ABM_OWNER,ADDRESSBOOK_MANAGER.ABM_CRETETIME,ADDRESSBOOK_MANAGER.ABM_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABF_ID,ADDRESSBOOK_FOLDER.ADD_ABM_ID,ADDRESSBOOK_FOLDER.ABF_NAME,ADDRESSBOOK_FOLDER.ABF_DESCRIPT,ADDRESSBOOK_FOLDER.ABF_CREATETIME ,ADDRESSBOOK_FOLDER.ABF_UPDATETIME,ADDRESSBOOK_FOLDER.ABF_FLAG,ADDRESSBOOK_CONTENT.ABC_ID,ADDRESSBOOK_CONTENT.ADD_ABFC_ID,ADDRESSBOOK_CONTENT.ABC_NAME,ADDRESSBOOK_CONTENT.ABC_COMPANYADDRESS,ADDRESSBOOK_CONTENT.ABC_FAMILYADDRESS,ADDRESSBOOK_CONTENT.ABC_COMPANYTEL,ADDRESSBOOK_CONTENT.ABC_FAMILYTEL,ADDRESSBOOK_CONTENT.ABC_CELLPHONE,ADDRESSBOOK_CONTENT.ABC_EMAIL,ADDRESSBOOK_CONTENT.ABC_EMAILSEC,ADDRESSBOOK_CONTENT.ABC_COMPANY,ADDRESSBOOK_CONTENT.ABC_LEVER,ADDRESSBOOK_CONTENT.ABC_CREATETIME,ADDRESSBOOK_CONTENT.ABC_UPDATETIME,ADDRESSBOOK_CONTENT.ABC_MEMO  ");
		sql.append("FROM ");
		sql.append("ADDRESSBOOK_MANAGER  ,ADDRESSBOOK_FOLDER LEFT JOIN ADDRESSBOOK_CONTENT ON ADDRESSBOOK_FOLDER.ABF_ID=ADDRESSBOOK_CONTENT.ADD_ABFC_ID");
		sql.append(" where ADDRESSBOOK_MANAGER.ABM_ID=ADDRESSBOOK_FOLDER.ADD_ABM_ID    and ADDRESSBOOK_MANAGER.ABM_ID='" + abmId + "' ");

		//System.out.println("**********sql3********"+sql);
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new AddressbookAllSearchVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 创建目录
	 * @param filepath
	 */
	public void CreatDir(String filepath) {

		// this.filepath = toFilePathManager.getString("toFile_path");
		this.filepath = filepath;
		System.out.println("toFile_path is : " + filepath);
		File file = new File(filepath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	/**
	 * @param inabmvo
	 */
	public void inaccessory(AddressbookManagerVO inabmvo, Integer abmid) {
		DAOFactory factory = new DAOFactory(conn);
		AddressbookManagerDAO dao = new AddressbookManagerDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setAbmId(abmid);
		try {
			dao = (AddressbookManagerDAO) factory.findByPrimaryKey();
			dao.setAbmAccessory(inabmvo.getAbmAccessory());

			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 由得到一条个人的电话记录
	 * @param abcid
	 */
	public AddressbookContentVO getOneAddressBook(Integer abcid) {
		AddressbookContentVO avo = null;
		AddressbookContentDAO adao = new AddressbookContentDAO();
		adao.setAbcId(abcid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(adao);
		try {
			List list = factory.find(new AddressbookContentVO());
			avo = (AddressbookContentVO) list.get(0);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return avo;
	}

}

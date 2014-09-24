package com.icss.oa.filetransfer.handler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.MimeUtility;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.dao.AddressGroupDAO;
import com.icss.oa.address.dao.SysPersonDAO;
import com.icss.oa.address.vo.AddressGroupVO;
import com.icss.oa.commsite.handler.HandlerException;
import com.icss.oa.filetransfer.dao.FiletransferSetDAO;
import com.icss.oa.filetransfer.vo.DraftPersonVO;
import com.icss.oa.filetransfer.vo.FiletransferSetVO;
import com.icss.oa.hr.dao.HRSysPersonDAO;
import com.icss.oa.hr.vo.HRSysPersonVO;
import com.icss.oa.util.StringUtility;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Person;

public class FiletransferSetHandler {

	private Connection conn;

	public FiletransferSetHandler(Connection conn) {
		this.conn = conn;
	}

	public FiletransferSetHandler() {
	}

	/**
	 * 创建新test
	 * 
	 * @param testVO
	 * @throws HandlerException
	 */
	public void add(FiletransferSetVO vo) throws HandlerException {
		FiletransferSetDAO dao = new FiletransferSetDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List get() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		FiletransferSetDAO dao = new FiletransferSetDAO();
		factory.setDAO(dao);

		try {
			List list = factory.find(new FiletransferSetVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getByid(Integer id) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		FiletransferSetDAO dao = new FiletransferSetDAO();
		dao.setId(id);
		factory.setDAO(dao);

		try {
			List list = factory.find(new FiletransferSetVO());

			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	public List getByUid(String userid) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		FiletransferSetDAO dao = new FiletransferSetDAO();
		dao.setFsUid(userid);
		factory.setDAO(dao);

		try {
			List list = factory.find(new FiletransferSetVO());
			return list;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	/*
	 * 根据组织id号得到改组织下所有的邮箱配置信息
	 */
	public List getByOrgid(String orgid) throws HandlerException {
		EntityManager entitymanger;
		List list = new ArrayList();
		try {
			entitymanger = EntityManager.getInstance();
			List useridlist = entitymanger.findPersonsByOrgUuid(orgid);

			if (useridlist.size() > 0) {
				for (int i = 0; i < useridlist.size(); i++) {
					Person person = (Person) useridlist.get(i);
					list.addAll(getByUid(person.getLoginName()));
				}
			}
		} catch (EntityException e) {
			throw new RuntimeException("根据组织id号取邮箱配置出错 " + e);
		}
		Iterator result = list.iterator();

		while (result.hasNext()) {
			FiletransferSetVO filetransetvo = (FiletransferSetVO) result.next();
			System.out.println("filetransetvo = " + filetransetvo.getFsUid());
		}
		return list;
	}

	/**
	 * 修改test信息
	 * 
	 * @param testVO
	 */
	public void alter(FiletransferSetVO vo) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		try {
			FiletransferSetDAO dao = new FiletransferSetDAO();
			dao.setValueObject(vo);
			dao.setConnection(conn);
			dao.update(true);

		} catch (Exception e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * 删除test
	 * 
	 * @param id
	 * @exception
	 */
	public void delete(Integer id) throws HandlerException {
		try {
			DAOFactory factory = new DAOFactory(conn);
			FiletransferSetDAO dao = new FiletransferSetDAO();
			factory.setDAO(dao);
			dao.setConnection(conn);
			dao.setId(id);
			dao.delete();

		} catch (Exception e) {
			throw new HandlerException();
		}
	}

	/**
	 * 依UUID返回用户的邮箱USERID
	 * 
	 * @param
	 * @exception
	 */
	public String getUserid(String uuid) {
		List list = null;
		String userid = "";
		DAOFactory factory = new DAOFactory(conn);
		FiletransferSetDAO dao = new FiletransferSetDAO();
		dao.setFsUuid(uuid);
		dao.setFsDeltag("0");
		factory.setDAO(dao);
		try {
			list = factory.find(new FiletransferSetVO());
			FiletransferSetVO ftVO = (FiletransferSetVO) list.get(0);
			userid = ftVO.getFsUid();
		} catch (DAOException e) {
			//lizb 2009-11-26
			try {
				if (conn!=null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e1) {
			}
			//lizb 2009-11-26
			throw new RuntimeException("您的帐号没有关联邮箱，或者您没有邮箱！\n以下为调试信息，请联系管理员：uuid=" +uuid);
//			userid = null;
//			System.out.println("登录用户为非法邮箱用户！");
		}
		if (userid==null){
			//lizb 2009-11-26
			throw new RuntimeException("您的帐号没有关联邮箱，或者您没有邮箱！\n以下为调试信息，请联系管理员：uuid=" +uuid);
		}
		//System.out.println("----userid=" + userid);
		return userid;
	}

	public int getFolderSize(String uuid) {
		List list = null;
		int size = 0;
		DAOFactory factory = new DAOFactory(conn);
		FiletransferSetDAO dao = new FiletransferSetDAO();
		dao.setFsUuid(uuid);
		dao.setFsDeltag("0");
		factory.setDAO(dao);
		try {
			list = factory.find(new FiletransferSetVO());
			FiletransferSetVO ftVO = (FiletransferSetVO) list.get(0);
			size = ftVO.getFsSize();
		} catch (DAOException e) {
			size = 0;
			System.out.println("登录用户为非法邮箱用户！");
		}
		return size;
	}


	/**
	 * 依用户的邮箱USERID返回用户在数据库中的中文名
	 * 
	 * @param
	 * @exception
	 */
	public String getCName(String userid) {
		// EntityManager entitymanger;
		String uuid = "";
		// if(flag==1){ //为系统中的USERID
		// uuid = getSysUserid(userid);
		// }else if(flag==2){ //为邮箱USERID
		uuid = getAllUUid(userid);
		String CName = null;
		// }
		try {
			HRSysPersonVO vo = this.getVOByUUID(uuid);
			if (vo!=null){
				CName = vo.getCnname();
			}
			// 根据uuid得到CName
			// EntityManager entitymanger;

			// entitymanger = EntityManager.getInstance();
			// CName = entitymanger.findPersonByUuid(uuid).getFullName();
		} catch (DAOException e) {
			e.printStackTrace();
			CName = null;
		}
		if (CName == null) {
			CName = userid;
		}
		return CName;
	}

	// /**
	// * 依数据库中的USERID返回用户uuid
	// * @param
	// * @exception
	// */
	// public String getSysUserid(String userid){
	// List list = null;
	// String uuid = "";
	// DAOFactory factory = new DAOFactory(conn);
	// SysPersonDAO dao = new SysPersonDAO();
	// dao.setUserid(userid);
	// factory.setDAO(dao);
	// try {
	// list = factory.find(new SysPersonVO());
	// SysPersonVO sysVO = (SysPersonVO)list.get(0);
	// uuid = sysVO.getPersonuuid();
	// } catch (DAOException e) {
	// e.printStackTrace();
	// }
	// return uuid;
	// }

	/**
	 * 依用户邮箱通用USERID返回数据库中的USERID（包括已经被假删除的）
	 * 
	 * @param
	 * @exception
	 */
	public String getUid(String uid) throws IOException {

		List list = null;
		List list1 = null;

		String uuid = null;
		HRSysPersonVO hvo;
		String userid= null;
		try {
			uuid = this.getAllUUid(uid);
			hvo = this.getVOByUUID(uuid);
			userid = hvo.getUserid();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String userid = "";
		// String uuid = "";
		// DAOFactory factory = new DAOFactory(conn);
		// FiletransferSetDAO dao = new FiletransferSetDAO();
		// dao.setFsUid(uid);
		// factory.setDAO(dao);
		// try {
		// list = factory.find(new FiletransferSetVO());
		// FiletransferSetVO ftVO = (FiletransferSetVO) list.get(0);
		// uuid = ftVO.getFsUuid();
		// HRSysPersonDAO sdao = new HRSysPersonDAO();
		// sdao.setPersonuuid(uuid);
		// factory.setDAO(sdao);
		// list1 = factory.find(new HRSysPersonVO());
		// HRSysPersonVO hvo = (HRSysPersonVO) list1.get(0);
		// userid = hvo.getUserid();
		// // 根据useruuid得到userid
		// // EntityManager entitymanger = EntityManager.getInstance();
		// // userid = entitymanger.findPersonByUuid(uuid).getLoginName();
		// } catch (DAOException e) {
		// e.printStackTrace();
		// }
		return userid;
	}

	/**
	 * 依邮箱通用USERID返回用户的UUID（不包括已经被假删除的用户）
	 * 
	 * @param
	 * @exception
	 */
	public String getUUid(String uid) {
		List list = null;
		String uuid = "";
		DAOFactory factory = new DAOFactory(conn);
		FiletransferSetDAO dao = new FiletransferSetDAO();
		dao.setFsUid(uid);
		dao.setFsDeltag("0");
		factory.setDAO(dao);
		try {
			list = factory.find(new FiletransferSetVO());
			FiletransferSetVO ftVO = (FiletransferSetVO) list.get(0);
			uuid = ftVO.getFsUuid();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return uuid;
	}

	/**
	 * 依邮箱通用USERID返回用户的UUID（包括已经被假删除的用户也可查出）
	 * 
	 * @param
	 * @exception
	 */
	public String getAllUUid(String uid) {
		List list = null;
		String uuid = "";
		DAOFactory factory = new DAOFactory(conn);
		FiletransferSetDAO dao = new FiletransferSetDAO();
		dao.setFsUid(uid);
		factory.setDAO(dao);
		try {
			list = factory.find(new FiletransferSetVO());
			if (list!=null && !list.isEmpty()) {
				FiletransferSetVO ftVO = (FiletransferSetVO) list.get(0);
				uuid = ftVO.getFsUuid();
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return uuid;
	}

	/**
	 * 依MIMEMESSAGE的各种地址得到对应的用户的邮箱USERID
	 * 
	 * @param
	 * @exception
	 */
	public List getAddressList(Address[] address, SendFileBean sendFileBean,
			String bj) throws UnsupportedEncodingException {
		List list = new ArrayList();
		if (address != null) {
			String Address = "";
			String Name = "";
			StringBuffer addressBuf = new StringBuffer();
			for (int i = 0; i < address.length; i++) {
				Address = MimeUtility.decodeText(address[i].toString());
				Name = this
						.getCName(Address.substring(0, Address.indexOf("@")));
				if (i != 0)
					addressBuf.append(",");
				addressBuf.append(Address);
				list.add(Name);
				Name = "";
			}
			// 写入session,用于从草稿箱发送
			if ("to".equals(bj)) {
				sendFileBean.setSendto(addressBuf.toString());
				// System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwww
				// originUserAddress0000000000000000000 =
				// "+addressBuf.toString());
			} else if ("cc".equals(bj))
				sendFileBean.setSendcc(addressBuf.toString());
			else if ("bcc".equals(bj))
				sendFileBean.setSendbcc(addressBuf.toString());
		}
		return list;
	}

	public String getAddressToString(Address[] address)
			throws UnsupportedEncodingException {
		StringBuffer str = new StringBuffer();
		if (address != null) {
			String Address = "";
			String Name = "";
			StringBuffer addressBuf = new StringBuffer();
			for (int i = 0; i < address.length; i++) {
				Address = MimeUtility.decodeText(address[i].toString());
				Name = this
						.getCName(Address.substring(0, Address.indexOf("@")));
				if (i != 0)
					str.append(",");
				str.append(Name);
				Name = "";
			}

		}
		return str.toString();
	}

	// 取得草稿人员的list
	public List getDraftVOList(Address[] address)
			throws UnsupportedEncodingException {
		List list = new ArrayList();

		if (address != null) {
			String Address = "";
			String Name = "";
			StringBuffer addressBuf = new StringBuffer();
			for (int i = 0; i < address.length; i++) {
				Address = MimeUtility.decodeText(address[i].toString());
				DraftPersonVO vo = new DraftPersonVO();

				try {

					String usrid = Address.substring(0, Address.indexOf("@"));
					vo.setUserid(usrid);

					Name = this.getCName(usrid);
					vo.setCnname(Name);

					String uuid = this.getAllUUid(usrid);
					vo.setUuid(uuid);

				} catch (Exception e) {
					e.printStackTrace();
				}

				list.add(vo);

			}
		}
		return list;
	}

	/**
	 * 得到用户的个人分组ID
	 * 
	 * @param
	 * @exception
	 */
	public Integer getGroupid(String userid) {
		List list = null;
		Integer groupid = null;
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupDAO dao = new AddressGroupDAO();
		dao.setGroupuser(userid);
		factory.setDAO(dao);
		try {
			list = factory.find(new AddressGroupVO());
			AddressGroupVO agVO = (AddressGroupVO) list.get(0);
			groupid = agVO.getId();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupid;
	}

	/**
	 * 判断个人分组ID是否已经存在
	 * 
	 * @param
	 * @exception
	 */
	public boolean reGroupid(String userid) {
		List list = null;
		boolean pd = false;
		DAOFactory factory = new DAOFactory(conn);
		AddressGroupDAO dao = new AddressGroupDAO();
		dao.setGroupuser(userid);
		factory.setDAO(dao);
		try {
			list = factory.find(new AddressGroupVO());
			if (list.size() > 0) {
				pd = true;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pd;
	}

	/**
	 * 得到base64编码
	 * 
	 * @return
	 * @throws
	 */
	public String encodeBase64(String str) {
		String codeStr = "";
		System.out.println("20060321 BASE64 before =" + str);
		codeStr = (new sun.misc.BASE64Encoder()).encodeBuffer(str.getBytes());
		System.out.println("20060321 BASE64 after =" + codeStr);
		codeStr = StringUtility.replace(codeStr, "/", "-");
		codeStr = StringUtility.replace(codeStr, "+", "|");
		String subStr = codeStr.substring(codeStr.length() - 2, codeStr
				.length());
		if ("\r\n".equals(subStr)) {
			codeStr = codeStr.substring(0, codeStr.length() - 2);
		}
		System.out.println("20060321 BASE64 after2 =" + codeStr);
		return codeStr;
	}

	/**
	 * 从base64编码得到中文
	 * 
	 * @return
	 * @throws
	 */
	public String decodeBase64(String str) {
		String decodeStr = "";
		try {
			decodeStr = StringUtility.replace(str, "-", "/");
			decodeStr = StringUtility.replace(decodeStr, "|", "+");
			decodeStr = new String((new sun.misc.BASE64Decoder())
					.decodeBuffer(decodeStr));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return decodeStr;

	}

	public HRSysPersonVO getVOByUUID(String uuid) throws DAOException {
		List list1 = null;
		HRSysPersonVO hvo = null;
		DAOFactory factory = new DAOFactory(conn);
		HRSysPersonDAO sdao = new HRSysPersonDAO();
		sdao.setPersonuuid(uuid);
		factory.setDAO(sdao);
		list1 = factory.find(new HRSysPersonVO());
		if(list1!=null && !list1.isEmpty()){
			hvo = (HRSysPersonVO) list1.get(0);
		}
		return hvo;
	}
}
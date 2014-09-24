/*
 * Created on 2004-8-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.phonebook.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.config.PhoneBookConfig;
import com.icss.oa.filetransfer.dao.FiletransferSetDAO;
import com.icss.oa.folder.dao.FolderPackageDAO;
import com.icss.oa.phonebook.dao.PhoneCustomsqlDAO;
import com.icss.oa.phonebook.dao.PhoneInfoDAO;
import com.icss.oa.phonebook.dao.PhoneInfoPhoneJobnameSearchDAO;
import com.icss.oa.phonebook.dao.PhoneInfoSysPersonSearchDAO;
import com.icss.oa.phonebook.dao.PhoneJobDAO;
import com.icss.oa.phonebook.dao.PhoneJobnameDAO;
import com.icss.oa.phonebook.dao.RoPersonDAO;
import com.icss.oa.phonebook.dao.SysOrgDAO;
import com.icss.oa.phonebook.dao.SysOrgSearchDAO;
import com.icss.oa.phonebook.dao.SysOrgpersonSysPersonSearchDAO;
import com.icss.oa.phonebook.dao.SysPersonDAO;
import com.icss.oa.phonebook.dao.SysPersonDAO1;
import com.icss.oa.phonebook.dao.SysPersonDAO2;
import com.icss.oa.phonebook.dao.PhoneDAO;
import com.icss.oa.phonebook.dao.PhoneInfoHrPersonSearchDAO;
import com.icss.oa.phonebook.dao.PhonePrivPersonSearchDAO;
import com.icss.oa.phonebook.util.ExcuteSynch;
import com.icss.oa.phonebook.vo.PhoneCustomsqlVO;
import com.icss.oa.phonebook.vo.PhoneInfoPhoneJobnameVO;
import com.icss.oa.phonebook.vo.PhoneInfoSysPersonVO;
import com.icss.oa.phonebook.vo.PhoneInfoVO;
import com.icss.oa.phonebook.vo.PhoneJobVO;
import com.icss.oa.phonebook.vo.PhoneJobnameVO;
import com.icss.oa.phonebook.vo.RoPersonVO;
import com.icss.oa.phonebook.vo.SysOrgVO;
import com.icss.oa.phonebook.vo.PhoneInfoHrPersonSearchVO;
import com.icss.oa.phonebook.vo.SysOrgpersonSysPersonVO;
import com.icss.oa.phonebook.vo.SysPersonVO;
import com.icss.oa.phonebook.vo.SysPersonVO1;
import com.icss.oa.phonebook.vo.SysPersonVO2;
import com.icss.oa.phonebook.vo.PhoneSysNameVO;
import com.icss.oa.util.StatSiteControl;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.org.model.OrgVO;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Organization;
import com.icss.resourceone.sdk.framework.Person;
import com.icss.resourceone.sdk.right.AppRole;
import com.icss.resourceone.sdk.right.RightManager;
import com.icss.oa.phonebook.dao.PhonePrivDAO;
import com.icss.oa.phonebook.dao.PhonePrivPersonDAO;
import com.icss.oa.phonebook.vo.PhonePrivVO;
import com.icss.oa.phonebook.vo.PhonePrivPersonVO;
import com.icss.oa.phonebook.vo.PhonePrivPersonSearchVO;
import com.icss.oa.phonebook.vo.PhoneVO;

/**
 * @author firecoral
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhoneHandler {

	Connection conn = null;

	/**
	 * 
	 */
	public PhoneHandler(Connection connection) {
		this.conn = connection;
	}

	public PhoneHandler() {
	}

	/**
	 * 通过一个职位字符串得到最高的职级
	 * 
	 * @author firecoral
	 * 
	 */
	public int getNoteOrder(String jobs) {
		StringTokenizer joblist = new StringTokenizer(jobs, ",");
		int tempjobId = 0;
		int i = 0;
		while (joblist.hasMoreTokens()) {
			Integer nameID = new Integer(joblist.nextToken());
			PhoneJobnameVO pjVO = getOrgjobVO(nameID);
			Integer jobId = pjVO.getJobid();
			int onejobId = JobBean.getInstance().getJobLevel(jobId).intValue();
			if (i == 0)
				tempjobId = onejobId;
			else {

				// 得到一个最大的职级（职级ID最小）
				if (onejobId < tempjobId)
					tempjobId = onejobId;
			}
			i++;
		}

		return tempjobId;

	}

	/**
	 * 由当前用户的组织ID得到他所在局的UUID
	 * 
	 * @author firecoral
	 * 
	 */
	public String getJuOrguuid(String orguuid) {
		String juOrguuid = "";
		SysOrgDAO sdao = new SysOrgDAO();
		sdao.setOrguuid(orguuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(sdao);
		try {
			List list = factory.find(new SysOrgVO());
			if (list != null && list.size() > 0) {
				SysOrgVO svo = (SysOrgVO) list.get(0);
				if (svo.getOrglevel().intValue() != 3) {
					String pOrguuid = svo.getParentorguuid();
					juOrguuid = this.getJuOrguuid(pOrguuid);
				} else {
					juOrguuid = svo.getOrguuid();
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return juOrguuid;
	}

	/**
	 * 由当前用户的ID得到他所在局下属的所有处室
	 * 
	 */
	public List getChuOrguuid(String orguuid) {
		String juorguuid = getJuOrguuid(orguuid);
		SysOrgDAO dao = new SysOrgDAO();

		dao.setParentorguuid(juorguuid);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		List chuorgList = new ArrayList();

		try {
			chuorgList = factory.find(new SysOrgVO());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return chuorgList;
	}

	/**
	 * 查找某局级部门中的所有所有电话记录
	 * 
	 * @param userid
	 * @param orgUUid
	 * @return
	 */
	public List GetJuPhone(List chuorgList) {
		List sqlList = new ArrayList();
		PhoneInfoDAO pdao = new PhoneInfoDAO();
		DAOFactory factory = new DAOFactory(conn);
		String whereclause = "";
		Iterator it = chuorgList.iterator();
		while (it.hasNext()) {
			SysOrgVO sysorg = (SysOrgVO) it.next();
			whereclause += " ORGUUID='" + sysorg.getOrguuid() + "'";
			if (it.hasNext())
				whereclause += " OR";
		}
		pdao.setWhereClause(whereclause);
		factory.setDAO(pdao);
		try {
			sqlList = factory.find(new PhoneInfoVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return sqlList;
	}

	/**
	 * 得到一条记录对应的所在组织，如果当前组织是处室，则要加上局级名称
	 * 
	 * @author firecoral
	 * 
	 */
	public String getFullOrgName(String orguuid) {
		String fullOrgName = "";
		SysOrgDAO sdao = new SysOrgDAO();
		sdao.setOrguuid(orguuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(sdao);
		try {
			String juOrguuid = "";
			List list = factory.find(new SysOrgVO());
			if (list != null && list.size() > 0) {
				SysOrgVO svo = (SysOrgVO) list.get(0);
				if (svo.getOrglevel().intValue() != 3) {
					String pOrguuid = svo.getParentorguuid();
					juOrguuid = this.getJuOrguuid(pOrguuid);
					fullOrgName = this.GetOrgName(juOrguuid) + "->"
							+ this.GetOrgName(orguuid);
				} else {
					juOrguuid = svo.getOrguuid();
					fullOrgName = this.GetOrgName(juOrguuid);
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return fullOrgName;
	}

	/**
	 * 由personUUid得到姓名
	 * 
	 * @author firecoral
	 * 
	 */
	public String getUserName(String personUuid) {
		String personName = "";
		SysPersonDAO dao = new SysPersonDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setPersonuuid(personUuid);
		factory.setDAO(dao);
		try {
			List list = factory.find(new SysPersonVO());
			if (list.size() > 0) {
				Iterator perItr = list.iterator();
				while (perItr.hasNext()) { // 只得到一条记录
					SysPersonVO perVO = (SysPersonVO) perItr.next();
					personName = perVO.getCnname();
				}
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return personName;
	}

	/**
	 * 得到组织的名称
	 * 
	 * @author firecoral
	 */
	public String GetOrgName(String OrgUUid) {
		String orgName = "";
		List orgList = new ArrayList();
		SysOrgDAO dao = new SysOrgDAO();
		dao.setOrguuid(OrgUUid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			orgList = factory.find(new SysOrgVO());
			SysOrgVO sysVO = (SysOrgVO) orgList.get(0);
			orgName = sysVO.getCnname();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgName;
	}

	/**
	 * 得到所有的电话记录
	 * 
	 * @author firecoral
	 * 
	 */
	public List GetAllPhone(String OrgUUid) {
		List phoneList = new ArrayList();
		List childOrgList = new ArrayList();

		StringBuffer sql = new StringBuffer();
		PhoneInfoDAO dao = new PhoneInfoDAO();
		sql.append("ORGUUID='" + OrgUUid + "'");

		// 查看是否有子组织，如果有的话就加上其子组织的电话
		SysOrgDAO sdao = new SysOrgDAO();
		sdao.setParentorguuid(OrgUUid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(sdao);
		try {
			childOrgList = factory.find(new SysOrgVO());
			if (childOrgList.size() > 0) {
				for (int i = 0; i < childOrgList.size(); i++) {
					SysOrgVO sVO = (SysOrgVO) childOrgList.get(i);
					sql.append(" OR ORGUUID='" + sVO.getOrguuid() + "'");
				}
			}

			dao.setWhereClause(sql.toString());
			dao.addOrderBy("nameid", true);
			factory.setDAO(dao);

			phoneList = factory.find(new PhoneInfoVO());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return phoneList;
	}

	/**
	 * 得到当前组织的上一级组织的组织名称
	 * 
	 * @author firecoral
	 * 
	 */
	public String GetParentOrgName(String OrgUUid) {
		String porgName = "";
		SysOrgDAO sdao = new SysOrgDAO();
		sdao.setOrguuid(OrgUUid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(sdao);
		try {
			List list = factory.find(new SysOrgVO());
			SysOrgVO vo = (SysOrgVO) list.get(0);

			if (vo.getOrglevel().intValue() == 0) {
				porgName = "";
			} else {
				String porgUUid = vo.getParentorguuid();
				porgName = this.GetOrgName(porgUUid).concat("-");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return porgName;
	}

	/**
	 * 得到本部门(包括处室)在信息表中还没有出现记录的人
	 * 
	 * @author firecoral
	 * 
	 */
	public List getNoPhonePerson(String OrgUUid) {
		StringBuffer searchsql = new StringBuffer();
		searchsql.append("SELECT ");
		searchsql
				.append("SYS_ORGPERSON.PERSONUUID,SYS_ORGPERSON.ORGUUID,"
						+ "SYS_ORGPERSON.ISBELONG,SYS_PERSON.USERID,SYS_PERSON.DELTAG,"
						+ "SYS_PERSON.CNNAME,SYS_PERSON.JOB,SYS_PERSON.IDNUM,SYS_PERSON.SEX,SYS_PERSON.SEQUENCENO");
		searchsql.append(" FROM");
		searchsql.append(" SYS_ORGPERSON,SYS_PERSON");
		searchsql
				.append(" WHERE SYS_PERSON.PERSONUUID=SYS_ORGPERSON.PERSONUUID");
		// if(!isShe){
		// searchsql.append(" OR SYS_ORGPERSON.ORGUUID IN (SELECT ORGUUID FROM SYS_ORG WHERE PARENTORGUUID='"+OrgUUid+"')");
		// }
		searchsql
				.append(" AND SYS_ORGPERSON.ISBELONG='1' AND SYS_PERSON.DELTAG='0' AND SYS_ORGPERSON.ORGUUID='"
						+ OrgUUid + "'");
		searchsql.append(" AND SYS_ORGPERSON.PERSONUUID IN");
		searchsql
				.append(" (SELECT SYS_ORGPERSON.PERSONUUID FROM SYS_ORGPERSON WHERE SYS_ORGPERSON.ORGUUID='"
						+ OrgUUid + "'AND SYS_ORGPERSON.ISBELONG='1'");
		searchsql.append(" MINUS");
		searchsql
				.append(" SELECT PHONE_INFO.USERUUID FROM PHONE_INFO WHERE PHONE_INFO.ORGUUID='"
						+ OrgUUid + "')");
		SysOrgpersonSysPersonSearchDAO dao = new SysOrgpersonSysPersonSearchDAO();
		dao.setSearchSQL(searchsql.toString());
		// System.out.println(searchsql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List notPhonelist = new ArrayList();
		try {
			notPhonelist = factory.find(new SysOrgpersonSysPersonVO());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return notPhonelist;
	}

	/**
	 * 将查出无记录的人的基本信息加入信息表
	 * 
	 * @author firecoral
	 * 
	 */
	public void setNoPhoneNote(List notPhonelist) {
		Iterator it = notPhonelist.iterator();
		String orguuid = "";
		String personuuid = "";
		String cnname = "";
		while (it.hasNext()) {
			SysOrgpersonSysPersonVO vo = (SysOrgpersonSysPersonVO) it.next();
			orguuid = vo.getOrguuid();
			personuuid = vo.getPersonuuid();
			cnname = vo.getCnname();
			PhoneInfoDAO pdao = new PhoneInfoDAO();
			pdao.setOrguuid(orguuid);
			pdao.setUseruuid(personuuid);
			pdao.setUsername(cnname);
			pdao.setNoteorder(Integer.valueOf(999));
			pdao.setConnection(conn);
			try {
				pdao.create();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 得到登录用户所能看到的所有电话记录（或查看某一个部门的电话记录）
	 * 
	 * @author firecoral
	 * 
	 */
	public List GetOrgPhone(String OrgUUid) {
		List phoneList = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("PHONE_INFO.NOTEID,PHONE_INFO.ORGUUID,PHONE_INFO.USERUUID,PHONE_INFO.USERNAME,PHONE_INFO.OFFICEPHONE,PHONE_INFO.HOMEPHONE,PHONE_INFO.NETPHONE,PHONE_INFO.MOBILEPHONE,PHONE_INFO.FAXPHONE,PHONE_INFO.OFFICEADDRESS,PHONE_INFO.FUNCTION,PHONE_INFO.ISPERMISSION,PHONE_INFO.MAINTANPERSON,PHONE_INFO.LASTMAINTANTIME,PHONE_INFO.NOTEORDER,PHONE_INFO.NAMEIDS,PHONE_INFO.ISPARTTIME,SYS_PERSON.DELTAG,SYS_PERSON.SEQUENCENO");
		sql.append(" FROM");
		sql
				.append(" PHONE_INFO LEFT JOIN SYS_PERSON ON PHONE_INFO.USERUUID = SYS_PERSON.PERSONUUID");
		if (OrgUUid != null) { // 为选择某一个部门下的信息
			sql.append(" WHERE PHONE_INFO.ORGUUID='" + OrgUUid + "'");
			// sql.append(
			// " ORDER BY PHONE_INFO.NOTEORDER,SYS_PERSON.SEQUENCENO,PHONE_INFO.NOTEID");
			sql.append(" ORDER BY SYS_PERSON.USERID");
		} else {
			// sql.append(" ORDER BY PHONE_INFO.NOTEORDER,PHONE_INFO.NOTEID");
			sql.append(" ORDER BY SYS_PERSON.USERID");
		}
		System.out.print("bbbbbbbbbbb   " + sql.toString());
		PhoneInfoSysPersonSearchDAO dao = new PhoneInfoSysPersonSearchDAO();
		dao.setSearchSQL(sql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			phoneList = factory.find(new PhoneInfoSysPersonVO());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return phoneList;
	}

	/**
	 * 得到所查看组织的联系地址
	 * 
	 * @author firecoral
	 * 
	 */
	public String GetOrgContact(String OrgUUid) {
		String contact = "";
		List list = new ArrayList();
		SysOrgDAO dao = new SysOrgDAO();
		dao.setOrguuid(OrgUUid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			list = factory.find();
			if (list.size() > 0) {
				SysOrgDAO sVO = (SysOrgDAO) list.get(0);
				// contact = sVO.getContact();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contact;
	}

	/**
	 * 添加一个新的电话记录
	 * 
	 * @author firecoral
	 * 
	 */
	public Integer NewPhone(PhoneInfoVO vo) {
		PhoneInfoDAO dao2 = new PhoneInfoDAO();
		try {
			dao2.setValueObject(vo);
			dao2.setConnection(conn);
			dao2.create();
			// 为个人记录
			// zhanggk delete 不需要同步到其他地方，该功能只添加部门公用电话，个人电话在人事系统中修改
			// if (vo.getUseruuid() != null && !("".equals(vo.getUseruuid()))) {
			// //同步其他模块的数据
			// ExcuteSynch.getInstance().readConfig(
			// vo,
			// PhoneBookConfig.ADDFLAG);
			// }

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao2.getNoteid();
	}

	/**
	 * 删除一条电话记录
	 * 
	 * @author firecoral
	 * 
	 */
	public void DelPhone(PhoneInfoVO vo) {
		if (vo.getUseruuid() == null || "".equals(vo.getUseruuid())) {
			// 为值班电话
			PhoneInfoDAO dao = new PhoneInfoDAO();
			dao.setNoteid(vo.getNoteid());
			dao.setConnection(conn);
			try {
				dao.delete();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else { // 为个人电话
			// String personuuid = vo.getUseruuid();
			// PhoneInfoDAO dao1 = new PhoneInfoDAO();
			// dao1.setUseruuid(personuuid);
			// if("1".equals(vo.getIsparttime())){
			// //为兼职
			// dao1.setConnection(conn);
			// try {
			// dao1.delete();
			// } catch (DAOException e) {
			// e.printStackTrace();
			// }
			// }else{
			// //删除所有相关记录
			// DAOFactory factory1 = new DAOFactory(conn);
			// factory1.setDAO(dao1);
			// List list = new ArrayList();
			// try {
			// list = factory1.find(new PhoneInfoVO());
			// if(list!=null&&list.size()>0){
			// for(int i=0;i<list.size();i++){
			// PhoneInfoVO pvo = (PhoneInfoVO)list.get(i);
			// this.DelPhone(pvo.getNoteid());
			// }
			// }
			// } catch (DAOException e) {
			// e.printStackTrace();
			// }
			PhoneInfoDAO dao = new PhoneInfoDAO();
			dao.setNoteid(vo.getNoteid());
			dao.setConnection(conn);
			try {
				dao.delete();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			// 同步其他模块的数据
			ExcuteSynch.getInstance()
					.readConfig(vo, PhoneBookConfig.DELETEFLAG);
			// }
		} // else
	}

	/**
	 * 删除一条电话记录（只被上面的删除方法调用）
	 * 
	 * @author firecoral
	 * 
	 */
	public void DelPhone(Integer noteid) {
		PhoneInfoDAO dao = new PhoneInfoDAO();
		dao.setNoteid(noteid);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新一条电话记录
	 * 
	 * @author firecoral
	 * 
	 */
	public void UpdatePhone(PhoneInfoVO vo) {
		// 值班记录
		if (vo.getUseruuid() == null || "".equals(vo.getUseruuid())) {
			PhoneInfoDAO dao = new PhoneInfoDAO();
			dao.setValueObject(vo);
			dao.setConnection(conn);
			try {
				dao.update(true);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else { // 个人记录
			PhoneInfoDAO dao = new PhoneInfoDAO();
			dao.setValueObject(vo);
			dao.setConnection(conn);
			try {
				dao.update(true);
				// //为全职记录时才需要同步
				// if("0".equals(vo.getIsparttime())){
				// //修改相应的兼职记录
				// String personuuid = vo.getUseruuid();
				// PhoneInfoDAO dao1 = new PhoneInfoDAO();
				// dao1.setUseruuid(personuuid);
				// dao1.setIsparttime("1");
				// DAOFactory factory1 = new DAOFactory(conn);
				// factory1.setDAO(dao1);
				// List list = new ArrayList();
				// list = factory1.find(new PhoneInfoVO());
				// if(list!=null&&list.size()>0){
				// for(int i=0;i<list.size();i++){
				// PhoneInfoVO pvo = (PhoneInfoVO)list.get(i);
				// PhoneInfoVO newVO = new PhoneInfoVO();
				// Method[] ms = PhoneInfoVO.class.getDeclaredMethods();
				// Method mSet = null;
				// Method mGet = null;
				// String methodName = "";
				// String sGet = "";
				// for (int j = 0; j < ms.length; j++) {
				// mSet = ms[j];
				// //System.out.println(mSet);
				// methodName = mSet.getName();
				// if (methodName.startsWith("set")) {
				// sGet = "g" + methodName.substring(1, methodName.length());
				// //System.out.println(sGet);
				// mGet = PhoneInfoVO.class.getMethod(sGet,new Class[]{});
				// mSet.invoke(newVO,
				// new Object[]{mGet.invoke(pvo,new Object[]{})});
				// }
				// }
				// newVO.setNoteid(pvo.getNoteid());
				// PhoneInfoDAO newdao = new PhoneInfoDAO();
				// newdao.setValueObject(newVO);
				// newdao.setConnection(conn);
				// newdao.update(true);
				//							
				// }
				// }// if list
				// //-------修改结束--------------------------
				// }
				if ("0".equals(vo.getIsparttime()))
					// 同步其他模块的数据
					ExcuteSynch.getInstance().readConfig(vo,
							PhoneBookConfig.UPDATEFLAG);
			} catch (DAOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				// } catch (IllegalAccessException e) {
				// e.printStackTrace();
				// } catch (InvocationTargetException e) {
				// e.printStackTrace();
				// } catch (SecurityException e) {
				// e.printStackTrace();
				// } catch (NoSuchMethodException e) {
				// e.printStackTrace();
			}
		}
	}

	/**
	 * 更新一条电话记录（用于职位修改时，修改排序）
	 * 
	 * @author firecoral
	 * 
	 */
	public void UpdatePhone(Integer noteId, String jobId, Integer noteOrder) {
		PhoneInfoDAO dao = new PhoneInfoDAO();
		dao.setNoteid(noteId);
		dao.setConnection(conn);
		try {
			dao.setNameids(jobId);
			dao.setNoteorder(noteOrder);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过一个noteid得到一个电话记录
	 * 
	 * @author firecoral
	 * 
	 */
	public PhoneInfoVO GetOnePhone(Integer noteid) {
		List onePhonelist = new ArrayList();
		PhoneInfoDAO pdao = new PhoneInfoDAO();
		pdao.setNoteid(noteid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(pdao);
		PhoneInfoVO pVO = null;
		try {
			onePhonelist = factory.find(new PhoneInfoVO());
			if (onePhonelist.size() > 0) { // 只得到一条记录
				pVO = (PhoneInfoVO) onePhonelist.get(0);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return pVO;
	}

	/**
	 * 通过一个userid得到当前用户的常用SQL
	 * 
	 * @author firecoral
	 * 
	 */
	public List GetSqlList(String userid, String orgUUid) {
		List sqlList = new ArrayList();
		PhoneCustomsqlDAO pdao = new PhoneCustomsqlDAO();
		DAOFactory factory = new DAOFactory(conn);
		pdao.setWhereClause("USERID='" + userid + "' AND ORGUUid='" + orgUUid
				+ "'");
		factory.setDAO(pdao);
		try {
			sqlList = factory.find(new PhoneCustomsqlVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return sqlList;
	}

	/**
	 * 得到搜索结果
	 * 
	 * @author firecoral
	 * 
	 */
	public List GetSearchList(String personUUid, String sqlid, String username,
			String userJob, String functiontype, List personUUidList,
			String phonetype, String phonenum, String functionname,
			String sqltitle, String savetosql) {
		List searchList = new ArrayList();
		StringBuffer searchsql = new StringBuffer();
		if (sqlid == null) { // 没有选择习惯方式
			StringBuffer sql = new StringBuffer();
			searchsql.append(" SELECT ");
			searchsql
					.append(" DISTINCT PHONE_INFO.NOTEID,PHONE_INFO.ORGUUID,PHONE_INFO.USERUUID,PHONE_INFO.USERNAME,PHONE_INFO.OFFICEPHONE,PHONE_INFO.HOMEPHONE,PHONE_INFO.NETPHONE,PHONE_INFO.MOBILEPHONE,PHONE_INFO.FAXPHONE,PHONE_INFO.OFFICEADDRESS,PHONE_INFO.FUNCTION,PHONE_INFO.ISPERMISSION,PHONE_INFO.MAINTANPERSON,PHONE_INFO.LASTMAINTANTIME,PHONE_INFO.NAMEIDS,PHONE_INFO.ISPARTTIME,PHONE_INFO.NOTEORDER");
			searchsql.append(" FROM");
			searchsql.append(" PHONE_INFO,PHONE_JOBNAME");
			searchsql.append(" WHERE PHONE_INFO.ORGUUID IS NOT NULL AND");

			int thesize = personUUidList.size();
			if ("1".equals(functiontype)) {
				if (thesize > 0) {
					searchsql.append(" (");
					for (int i = 0; i < personUUidList.size(); i++) {
						if (i > 0) {
							searchsql.append(" OR");
						}
						searchsql.append(" PHONE_INFO.USERUUID='"
								+ personUUidList.get(i) + "'");
					}
					searchsql.append(")");
					searchsql.append(" AND");
				} else {
					if (!("".equals(username))) {
						StringTokenizer namelist = new StringTokenizer(
								username, ",");
						int y = 0;
						searchsql.append(" (");
						while (namelist.hasMoreTokens()) {
							if (y > 0) {
								searchsql.append(" OR");
							}
							searchsql.append(" PHONE_INFO.USERNAME LIKE '%"
									+ namelist.nextToken().trim() + "%'");
							y++;
						}
						searchsql.append(") AND");
					}
				}
			} else {
				if (!("".equals(username))) {
					StringTokenizer namelist = new StringTokenizer(username,
							",");
					int y = 0;
					searchsql.append(" (");
					while (namelist.hasMoreTokens()) {
						if (y > 0) {
							searchsql.append(" OR");
						}
						searchsql.append(" PHONE_INFO.USERNAME LIKE '%"
								+ namelist.nextToken().trim() + "%'");
						y++;
					}
					searchsql.append(") AND");
				}
			}

			if (!("-1".equals(userJob))) {
				searchsql
						.append(" PHONE_INFO.NAMEIDS LIKE concat(concat('%,',PHONE_JOBNAME.NAMEID),'%')");
				searchsql
						.append(" AND PHONE_JOBNAME.NAMEID IN (SELECT NAMEID FROM PHONE_JOBNAME WHERE PHONE_JOBNAME.JOBID="
								+ userJob + ")");
				// searchsql.append(" PHONE_INFO.JOBID LIKE '%,"+userJob+",%'");
				searchsql.append(" AND");
			}

			if (!("0".equals(phonetype))) { // 选择了电话类别
				if ("1".equals(phonetype)) {
					searchsql.append(" PHONE_INFO.OFFICEPHONE='" + phonenum
							+ "'");
				} else if ("2".equals(phonetype)) {
					searchsql
							.append(" PHONE_INFO.HOMEPHONE='" + phonenum + "'");
				} else if ("3".equals(phonetype)) {
					searchsql.append(" PHONE_INFO.NETPHONE='" + phonenum + "'");
				} else if ("4".equals(phonetype)) {
					searchsql.append(" PHONE_INFO.MOBILEPHONE='" + phonenum
							+ "'");
				} else {
					searchsql.append(" PHONE_INFO.FAXPHONE='" + phonenum + "'");
				}
				searchsql.append(" AND");
			} else if (!("".equals(phonenum))) { // 所有电话类别
				searchsql.append(" (PHONE_INFO.OFFICEPHONE='" + phonenum + "'");
				searchsql.append(" OR PHONE_INFO.HOMEPHONE='" + phonenum + "'");
				searchsql.append(" OR PHONE_INFO.NETPHONE='" + phonenum + "'");
				searchsql.append(" OR PHONE_INFO.MOBILEPHONE='" + phonenum
						+ "'");
				searchsql.append(" OR PHONE_INFO.FAXPHONE='" + phonenum + "')");
				searchsql.append(" AND");
			}

			// 电话职能
			if ("".equals(functionname)) {
				searchsql.append(" PHONE_INFO.FUNCTION IS NULL");
			} else {
				searchsql.append(" PHONE_INFO.FUNCTION='" + functionname + "'");
			}

			searchsql.append(" ORDER BY PHONE_INFO.NOTEORDER");

			if ("checked".equals(savetosql)) { // 选择了保存查询条件
				// 将SQL保存了习惯
				this.SaveToCustomSql(searchsql.toString(), sqltitle);
			}
		} else { // 选择了习惯方式
			searchsql.append(this.GetCustomSql(sqlid));
		}

		PhoneInfoPhoneJobnameSearchDAO dao = new PhoneInfoPhoneJobnameSearchDAO();
		System.out.println("the sql is:" + searchsql.toString());
		dao.setSearchSQL(searchsql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			searchList = factory.find(new PhoneInfoPhoneJobnameVO());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return searchList;
	}

	// add by zhouyi
	/**
	 * 用户查找值班电话
	 * 
	 * @author firecoral
	 * 
	 */
	public List MyGetSearchList(String username, String phonenum,
			String orgname, String deptname) {
		List searchList = new ArrayList();
		PhoneDAO pdao = new PhoneDAO();
		DAOFactory factory = new DAOFactory(conn);
		StringBuffer searchsql = new StringBuffer();
		searchsql.append("HR_PHONE_INFO.FUNCTION='2'");
		// 根据名字查找
		if (!("".equals(username))) {
			StringTokenizer namelist = new StringTokenizer(username, ",");
			int y = 0;
			searchsql.append(" AND (");
			while (namelist.hasMoreTokens()) {
				if (y > 0) {
					searchsql.append(" OR");
				}
				searchsql.append("  HR_PHONE_INFO.USERNAME LIKE '%"
						+ namelist.nextToken().trim() + "%'");
				y++;
			}
			searchsql.append(")");
		}

		// 根据部门查找
		if (!("".equals(orgname))) {
			searchsql.append(" AND HR_PHONE_INFO.ORGNAME LIKE '%" + orgname
					+ "%'");
		}

		if (!("".equals(deptname))) {
			searchsql.append(" AND HR_PHONE_INFO.DEPTNAME LIKE '%" + deptname
					+ "%'");
		}

		// 根据电话号查找
		if (!("".equals(phonenum))) {
			searchsql.append(" AND ( HR_PHONE_INFO.OFFICEPHONE LIKE '%"
					+ phonenum + "%' OR HR_PHONE_INFO.HOMEPHONE LIKE '%"
					+ phonenum + "%' OR HR_PHONE_INFO.NETPHONE LIKE '%"
					+ phonenum + "%' OR HR_PHONE_INFO.MOBILEPHONE LIKE '%"
					+ phonenum + "%' OR HR_PHONE_INFO.FAXPHONE LIKE '%"
					+ phonenum + "%' )");
		}
		System.out.println("sqlstrtt:" + searchsql.toString());
		pdao.setWhereClause(searchsql.toString());
		factory.setDAO(pdao);
		try {
			searchList = factory.find(new PhoneVO());
			// System.out.println("-------------searchlist size in handler "+searchList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return searchList;
	}

	//modified by liupei add order by sys_person.sequenceno
	public List HrPhoneInfoList(String username, String functionname,
			String phonenum, String orgname, String deptname, String detail,
			String hrid) {
		List searchList = new ArrayList();
		StringBuffer searchsql = new StringBuffer();
		searchsql.append("SELECT ");
		searchsql
				.append("HR_PHONE_INFO.PINOTEID,HR_PHONE_INFO.PIORGUUID,HR_PHONE_INFO.PIUSERUUID,HR_PHONE_INFO.PIUSERNAME,HR_PHONE_INFO.PIOFFICEPHONE,HR_PHONE_INFO.PIHOMEPHONE,HR_PHONE_INFO.PINETPHONE,HR_PHONE_INFO.PIMOBILEPHONE,HR_PHONE_INFO.PIFAXPHONE,HR_PHONE_INFO.PIOFFICEADDRESS,HR_PHONE_INFO.PIFUNCTION,HR_PHONE_INFO.PIISPERMISSION,HR_PHONE_INFO.PIMAINTANPERSON,HR_PHONE_INFO.PILASTMAINTANTIME,HR_PHONE_INFO.PINAMEIDS,HR_PHONE_INFO.PIISPARTTIME,HR_PHONE_INFO.PINOTEORDER,HR_PHONE_INFO.PIORGNAME,HR_PHONE_INFO.PIDEPTNAME,HRPERSON.OFFICEPHONE,"
						+ "HRPERSON.HOMEPHONE,HRPERSON.NETPHONE,HRPERSON.MOBILEPHONE,HRPERSON.FAXPHONE,HRPERSON.VPN,HRPERSON.ORGCODE,HRPERSON.ORGNAME,HRPERSON.DEPTCODE,HRPERSON.DEPTNAME,HRPERSON.JOBCODE,HRPERSON.JOB,HRPERSON.HOMEADDRESS,HRPERSON.EMAIL");
		searchsql
				.append(" FROM HR_PHONE_INFO LEFT JOIN HRPERSON ON HR_PHONE_INFO.PIHRID=HRPERSON.HRID LEFT JOIN SYS_PERSON ON SYS_PERSON.PERSONUUID=HR_PHONE_INFO.piuseruuid ");
		searchsql.append("WHERE HR_PHONE_INFO.PIORGUUID IS NOT NULL");
		// 根据电话分类查找
		if (!("".equals(functionname))) {
			searchsql.append(" AND HR_PHONE_INFO.PIFUNCTION='" + functionname
					+ "'");
		}
		if (!("".equals(hrid))) {
			searchsql.append(" AND HR_PHONE_INFO.PIHRID='" + hrid + "'");
		}

		// 根据人员名字查找
		if (!("".equals(username))) {
			StringTokenizer namelist = new StringTokenizer(username, " ");
			int y = 0;
			searchsql.append(" AND (");
			while (namelist.hasMoreTokens()) {
				if (y > 0) {
					searchsql.append(" OR");
				}
				String aname = namelist.nextToken().trim();
				searchsql.append(" HR_PHONE_INFO.PIUSERNAME LIKE '%" + aname
						+ "%' OR HRPERSON.USERNAME LIKE '%" + aname + "%'");
				y++;
			}
			searchsql.append(")");
		}

		// 根据所属部门
		// 如果是社领导，不判断所属部门信息，把所有社领导放在一起
//		if (!("".equals(orgname)) && !"社领导".equals(deptname)) {
			if (!("".equals(orgname)) && !"社领导".equals(deptname)) {

			if ("1".equals(detail)) {
				// 精确查找
				searchsql.append(" AND ( HR_PHONE_INFO.PIORGNAME = '" + orgname
						+ "' OR HRPERSON.ORGNAME = '" + orgname + "' )");
			} else {
				searchsql.append(" AND ( HR_PHONE_INFO.PIORGNAME LIKE '%"
						+ orgname + "%' OR HRPERSON.ORGNAME LIKE '%" + orgname
						+ "%' )");
			}
		}


		// 根据所属处室
		if (!("".equals(deptname))) {
			if ("1".equals(detail)) {
				// 精确查找
				searchsql.append(" AND ( HR_PHONE_INFO.PIDEPTNAME = '"
						+ deptname + "' OR HRPERSON.DEPTNAME = '" + deptname
						+ "' )");
			} else {
				searchsql.append(" AND ( HR_PHONE_INFO.PIDEPTNAME LIKE '%"
						+ deptname + "%' OR HRPERSON.DEPTNAME LIKE '%"
						+ deptname + "%' )");
			}
		}

		// 电话号
		if (!("".equals(phonenum))) {
			searchsql.append(" AND ( HR_PHONE_INFO.PIOFFICEPHONE LIKE '%"
					+ phonenum + "%' OR HR_PHONE_INFO.PIHOMEPHONE LIKE '%"
					+ phonenum + "%' OR HR_PHONE_INFO.PINETPHONE LIKE '%"
					+ phonenum + "%' OR HR_PHONE_INFO.PIMOBILEPHONE LIKE '%"
					+ phonenum + "%' OR HR_PHONE_INFO.PIFAXPHONE LIKE '%"
					+ phonenum + "%' " + " OR HRPERSON.OFFICEPHONE LIKE '%"
					+ phonenum + "%' OR HRPERSON.HOMEPHONE LIKE '%" + phonenum
					+ "%' OR HRPERSON.NETPHONE LIKE '%" + phonenum
					+ "%' OR HRPERSON.MOBILEPHONE LIKE '%" + phonenum
					+ "%' OR HRPERSON.FAXPHONE LIKE '%" + phonenum + "%')");
		}
		if ("1".equals(detail)) {
			// 精确查找,按人员级别排序
			searchsql
					.append("  AND SYS_PERSON.DELTAG='0' ORDER BY SYS_PERSON.SEQUENCENO,HRPERSON.DEPTCODE,HRPERSON.SEQUENCE,HR_PHONE_INFO.PINOTEORDER ");
		} else {
			// 按人员姓名拼音排序
			searchsql
					.append(" AND SYS_PERSON.DELTAG='0' ORDER BY HR_PHONE_INFO.PIUSERNAME ");
		}
		PhoneInfoHrPersonSearchDAO dao = new PhoneInfoHrPersonSearchDAO();
		dao.setSearchSQL(searchsql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			searchList = factory.find(new PhoneInfoHrPersonSearchVO());
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		// System.out.println("str777"+searchsql.toString());
		// System.out.println("str777size:"+searchList.size());
		return searchList;
	}

	/**
	 * 管理员查找电话
	 * 
	 * @author zhouyi
	 * 
	 */
	public List ManageSearchPhone(String noteids, String username,
			String orguuid, String officeaddress, String officephone,
			String homephone, String mobilephone, String netphone,
			String faxphone, List chuorgList) {
		List sqlList = new ArrayList();
		PhoneInfoDAO pdao = new PhoneInfoDAO();
		DAOFactory factory = new DAOFactory(conn);
		String whereclause = "";
		Iterator it = chuorgList.iterator();
		whereclause = whereclause + "FUNCTION='2' AND ";

		// 根据noteid查找电话记录
		if (!noteids.equals("")) {
			whereclause = whereclause + " PHONE_INFO.NOTEID=" + noteids;
		} else {
			// 如果指定了处级部门
			if (!orguuid.equals("") && !orguuid.equals("-1")) {
				whereclause = whereclause + "  ORGUUID='" + orguuid + "'";
			} else {
				whereclause = whereclause + " (";
				while (it.hasNext()) {
					SysOrgVO sysorg = (SysOrgVO) it.next();
					whereclause += "ORGUUID='" + sysorg.getOrguuid() + "'";
					if (it.hasNext())
						whereclause = whereclause + " OR ";
				}
				whereclause = whereclause + ")";
			}

			if (!username.equals("")) {
				whereclause = whereclause + " AND USERNAME LIKE '%" + username
						+ "%'";
			}

			if (!officeaddress.equals("")) {
				whereclause = whereclause + " AND OFFICEADDRESS LIKE '%"
						+ officeaddress + "%'";
			}
			if (!officephone.equals("")) {
				whereclause = whereclause + " AND OFFICEPHONE LIKE '%"
						+ officephone + "%'";
			}
			if (!homephone.equals("")) {
				whereclause = whereclause + " AND HOMEPHONE LIKE '%"
						+ homephone + "%'";
			}
			if (!mobilephone.equals("")) {
				whereclause = whereclause + " AND MOBILEPHONE LIKE '%"
						+ mobilephone + "%'";
			}
			if (!netphone.equals("")) {
				whereclause = whereclause + " AND NETPHONE LIKE '%" + netphone
						+ "%'";
			}
			if (!faxphone.equals("")) {
				whereclause = whereclause + " AND FAXPHONE LIKE '%" + faxphone
						+ "%'";
			}
		}
		// System.out.println("whereclause1:"+whereclause);
		pdao.setWhereClause(whereclause);
		factory.setDAO(pdao);
		try {
			sqlList = factory.find(new PhoneInfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlList;
	}

	/**
	 * 得到所有的职级code
	 * 
	 * @author zhouyi
	 * 
	 */
	public List getAllJob() {
		String jobcodestr = "";
		List joblist = new ArrayList();
		Statement stat = null;
		ResultSet rs = null;
		try {

			String idsql = "select distinct jobcode,job from hrperson where jobcode is not null and job is not null";
			stat = conn.createStatement();
			rs = stat.executeQuery(idsql);
			while (rs.next()) {
				String codename = rs.getString("jobcode") + ","
						+ rs.getString("job");
				joblist.add(codename);
			}
			// System.out.println("joblist.size():"+joblist.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return joblist;
	}

	/**
	 * 得到所有的职级code
	 * 
	 * @author zhouyi
	 * 
	 */
	public Map getAllJobMap() {
		Map privmap = new HashMap();
		Statement stat = null;
		ResultSet rs = null;
		try {
			String idsql = "select distinct jobcode,job from hrperson where jobcode is not null and job is not null";
			stat = conn.createStatement();
			rs = stat.executeQuery(idsql);
			while (rs.next()) {
				privmap.put(rs.getString("jobcode"), rs.getString("job"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return privmap;
	}

	/**
	 *新建电话薄权限
	 * 
	 * @author zhouyi
	 * 
	 */
	public Integer NewPhonePriv(PhonePrivVO vo) {
		PhonePrivDAO dao2 = new PhonePrivDAO();
		try {
			dao2.setValueObject(vo);
			dao2.setConnection(conn);
			dao2.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao2.getPp_id();
	}

	/**
	 *新建特殊电话薄权限
	 * 
	 * @author zhouyi
	 * 
	 */
	public Integer NewPhonePrivPerson(PhonePrivPersonVO vo) {
		PhonePrivPersonDAO dao2 = new PhonePrivPersonDAO();
		try {
			dao2.setValueObject(vo);
			dao2.setConnection(conn);
			dao2.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return dao2.getPpp_id();
	}

	/**
	 * 更新一条电话权限记录
	 * 
	 * @author zhouyi
	 * 
	 */
	public void UpdatePhonePriv(PhonePrivVO vo) {
		PhonePrivDAO dao = new PhonePrivDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新一条特殊电话权限记录
	 * 
	 * @author zhouyi
	 * 
	 */
	public void UpdatePhonePrivPerson(PhonePrivPersonVO vo) {
		PhonePrivPersonDAO dao = new PhonePrivPersonDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		try {
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过一个ppid得到一条电话权限记录
	 * 
	 * @author zhouyi
	 * 
	 */
	public PhonePrivVO GetOnePhonePriv(Integer ppid) {
		List onePhonelPrivList = new ArrayList();
		PhonePrivDAO pdao = new PhonePrivDAO();
		pdao.setPp_id(ppid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(pdao);
		PhonePrivVO pVO = null;
		try {
			onePhonelPrivList = factory.find(new PhonePrivVO());
			if (onePhonelPrivList.size() > 0) { // 只得到一条记录
				pVO = (PhonePrivVO) onePhonelPrivList.get(0);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return pVO;
	}

	/**
	 * 删除一条电话权限记录
	 * 
	 * @author zhouyi
	 * 
	 */
	public void DelPhonePriv(PhonePrivVO vo) {
		PhonePrivDAO dao = new PhonePrivDAO();
		dao.setPp_id(vo.getPp_id());
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过一个权限pp_id得到拥有此权限的特殊权限记录
	 * 
	 * @author zhouyi
	 * 
	 */
	public List GetPhonePrivPersonByPpid(String ppid) {
		List PhonePrivPersonList = new ArrayList();
		PhonePrivPersonDAO pdao = new PhonePrivPersonDAO();
		pdao.setPp_id(ppid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(pdao);
		try {
			PhonePrivPersonList = factory.find(new PhonePrivPersonVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return PhonePrivPersonList;
	}

	public List GetPhonePrivPersonByPpid(String ppid, String personuuid) {
		List PhonePrivPersonList = new ArrayList();
		PhonePrivPersonDAO pdao = new PhonePrivPersonDAO();
		pdao.setPp_id(ppid);
		pdao.setPersonuuid(personuuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(pdao);
		try {
			PhonePrivPersonList = factory.find(new PhonePrivPersonVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return PhonePrivPersonList;
	}

	/**
	 * 删除一条特殊电话权限记录
	 * 
	 * @author zhouyi
	 * 
	 */
	public void DelPhonePrivPerson(PhonePrivPersonVO vo) {
		PhonePrivPersonDAO dao = new PhonePrivPersonDAO();
		dao.setPpp_id(vo.getPpp_id());
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *查询电话权限
	 * 
	 * @author zhouyi
	 * 
	 */
	public List SearchPhonePriv() {
		List phoneprivList = new ArrayList();
		PhonePrivDAO pdao = new PhonePrivDAO();
		// pdao.setWhereClause("WH");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(pdao);
		pdao.setWhereClause("PP_ID IS NOT NULL");
		try {
			phoneprivList = factory.find(new PhonePrivVO());
			// System.out.println("nophoneprivListerror:");
		} catch (DAOException e) {
			// System.out.println("phoneprivListerror:");
			e.printStackTrace();
		}
		// System.out.println("phoneprivList.size:"+phoneprivList.size());
		return phoneprivList;
	}

	/**
	 * 连接两表查询
	 * 
	 * @author zhouyi
	 * 
	 */
	public List SearchPhonePrivPerson(String phoneprivname, String privtype,
			String scope, String useruuid, String username) {
		List searchList = new ArrayList();
		StringBuffer searchsql = new StringBuffer();
		searchsql.append(" SELECT ");
		searchsql
				.append(" PHONE_PRIVILEDGE.PP_ID,PHONE_PRIVILEDGE.PP_TYPE,PHONE_PRIVILEDGE.PP_SCOPE,PHONE_PRIVILEDGE.PP_NAME,PHONE_PRIVILEDGE.PP_LEVEL,PHONE_PRIVILEDGE.PP_SEARCHLEVEL"
						+ ",PHONE_PRIVPERSON.PP_ID,PHONE_PRIVPERSON.PERSONUUID,PHONE_PRIVPERSON.USERID,PHONE_PRIVPERSON.PERSONNAME");
		searchsql.append(" FROM ");
		searchsql
				.append(" PHONE_PRIVILEDGE LEFT JOIN PHONE_PRIVPERSON ON PHONE_PRIVILEDGE.PP_ID=PHONE_PRIVPERSON.PP_ID WHERE PHONE_PRIVILEDGE.PP_ID IS NOT NULL");

		if (!phoneprivname.equals(""))
			searchsql.append(" AND PHONE_PRIVILEDGE.PP_NAME LIKE '%"
					+ phoneprivname + "%'");

		if (!privtype.equals(""))
			searchsql.append(" AND PHONE_PRIVILEDGE.PP_TYPE = '" + privtype
					+ "'");

		if (!scope.equals("") && !scope.equals("0"))
			searchsql
					.append(" AND PHONE_PRIVILEDGE.PP_SCOPE = '" + scope + "'");

		if (!useruuid.equals(""))
			searchsql.append(" AND PHONE_PRIVPERSON.PERSONUUID = '" + useruuid
					+ "'");

		PhonePrivPersonSearchDAO dao = new PhonePrivPersonSearchDAO();

		dao.setSearchSQL(searchsql.toString());
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			searchList = factory.find(new PhonePrivPersonSearchVO());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("bbbbbsqlstr:" + searchsql.toString());
		return searchList;
	}

	/**
	 * 通过一个JOBID得到所有的职位的NAMEID
	 * 
	 * @author firecoral
	 * 
	 */
	public List getAllNameid(Integer jobId) {
		List nameList = new ArrayList();
		PhoneJobnameDAO jdao = new PhoneJobnameDAO();
		jdao.setJobid(jobId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(jdao);
		try {
			List jobList = factory.find(new PhoneJobnameVO());
			if (jobList != null && jobList.size() > 0) {
				Iterator it = jobList.iterator();
				PhoneJobnameVO vo = null;
				while (it.hasNext()) {
					vo = (PhoneJobnameVO) it.next();
					nameList.add(vo.getNameid());
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return nameList;
	}

	/**
	 * 保存一个常用SQL
	 * 
	 * @author firecoral
	 * 
	 */
	public void SaveToCustomSql(String sqlStr, String sqltitle) {
		Context ctx = null;
		try {
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();

			String userid = user.getUserID();
			String orgUUid = user.getPrimaryOrguuid();

			PhoneCustomsqlDAO pdao = new PhoneCustomsqlDAO();
			pdao.setUserid(userid);
			pdao.setOrguuid(orgUUid);
			pdao.setSqlTitle(sqltitle);
			pdao.setSqlContent(sqlStr);

			pdao.setConnection(conn);

			pdao.create();

		} catch (EntityException e1) {
			e1.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过一个SQLID得到一个常用SQL
	 * 
	 * @author firecoral
	 * 
	 */
	public String GetCustomSql(String sqlid) {
		String sqlStr = "";
		List sqlList = new ArrayList();
		PhoneCustomsqlDAO pdao = new PhoneCustomsqlDAO();
		DAOFactory factory = new DAOFactory(conn);
		pdao.setSqlId(new Integer(sqlid));
		factory.setDAO(pdao);
		try {
			sqlList = factory.find(new PhoneCustomsqlVO());
			PhoneCustomsqlVO pcsVO = (PhoneCustomsqlVO) sqlList.get(0);
			sqlStr = pcsVO.getSqlContent();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return sqlStr;
	}

	/**
	 * 通过一个SQLID得到一个常用SQL
	 * 
	 * @author firecoral
	 * 
	 */
	public void DelCustomSql(String sqlid) {
		PhoneCustomsqlDAO pdao = new PhoneCustomsqlDAO();
		pdao.setSqlId(new Integer(sqlid));
		pdao.setConnection(conn);
		try {
			pdao.delete();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到所有职级
	 * 
	 * @author firecoral
	 * 
	 */
	public List GetAllJobGeneral() {
		List jobList = new ArrayList();
		PhoneJobDAO jdao = new PhoneJobDAO();
		jdao.addOrderBy("jobLevel", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(jdao);
		try {
			jobList = factory.find(new PhoneJobVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return jobList;
	}

	/**
	 * 通过一个ORGUUID得到一个部门的所有职位
	 * 
	 * @author firecoral
	 * 
	 */
	public List GetAllJob(String orguuid) {
		List jobList = new ArrayList();
		PhoneJobnameDAO jdao = new PhoneJobnameDAO();
		jdao.setOrguuid(orguuid);
		jdao.addOrderBy("jobid", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(jdao);
		try {
			jobList = factory.find(new PhoneJobnameVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return jobList;
	}

	/**
	 * 通过一个JOBID得到一个职级VO
	 * 
	 * @author firecoral
	 * 
	 */
	public PhoneJobVO getVOByJobId(Integer jobId) {
		PhoneJobVO pVO = null;
		PhoneJobDAO jdao = new PhoneJobDAO();
		jdao.setJobId(jobId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(jdao);
		try {
			List jobList = factory.find(new PhoneJobVO());
			pVO = (PhoneJobVO) jobList.get(0);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return pVO;
	}

	/**
	 * 增加一个职位记录
	 * 
	 * @author firecoral
	 * 
	 */
	public void NewJob(Integer jobLevel, String jobName) {
		if (isSameJob(jobLevel)) { // 存在相同的JOBID,大于等于这个jobLevel的增1
			increaseByAuto(jobLevel);
		}
		PhoneJobDAO jdao = new PhoneJobDAO();
		jdao.setJobLevel(jobLevel);
		jdao.setJobName(jobName);
		jdao.setConnection(conn);
		try {
			jdao.create();
			// 更新HASHTABLE
			JobBean.getInstance().initHashTable(conn);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使所有的满足条件的JOBKEYID自增一
	 * 
	 * @author firecoral
	 * 
	 */
	public void increaseByAuto(Integer jobLevel) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "update phone_job set job_Level=job_Level+1 where job_Level>="
					+ jobLevel;
			stmt.executeUpdate(sql);

			// stmt.close();

		} catch (SQLException e1) {
			e1.printStackTrace();

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断是不是已经有相同的JOBID
	 * 
	 * @author firecoral
	 * 
	 */
	public boolean isSameJob(Integer jobLevel) {
		boolean isSame = false;
		PhoneJobDAO jdao = new PhoneJobDAO();
		jdao.setJobLevel(jobLevel);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(jdao);
		try {
			List list = factory.find(new PhoneJobVO());
			if (list != null && list.size() > 0) {
				isSame = true;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return isSame;
	}

	/**
	 * 更新一个职位记录
	 * 
	 * @author firecoral
	 * 
	 */
	public void UpdateJob(Integer jobId, Integer jobLevel, String jobName) {
		PhoneJobDAO jdao = new PhoneJobDAO();
		jdao.setJobId(jobId);
		jdao.setJobLevel(jobLevel);
		jdao.setJobName(jobName);
		jdao.setConnection(conn);
		try {
			jdao.update(true);
			// 更新HASHTABLE
			JobBean.getInstance().initHashTable(conn);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使原电话记录关联到新的JOBID
	 * 
	 * @author firecoral
	 * 
	 */
	public void UpdateNOSameKeyJob(Integer jobLevel, String jobName) {
		PhoneJobDAO pdao = new PhoneJobDAO();
		pdao.setJobId(jobLevel);
		pdao.setJobName(jobName);
		try {
			pdao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除一个职位记录
	 * 
	 * @author firecoral
	 * 
	 */
	public void DeleteJob(Integer jobId) {
		PhoneJobDAO jdao = new PhoneJobDAO();
		jdao.setJobId(jobId);
		jdao.setConnection(conn);
		try {
			jdao.delete();
			// 更新HASHTABLE
			JobBean.getInstance().initHashTable(conn);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新所有和这个职位关联的电话记录
	 * 
	 * @author firecoral
	 * 
	 */
	public void setNewNameidsForInfo(PhoneJobnameVO pVO, String doflag) {
		/*
		 * idStr 从电话职位表(Phone_jobName)中得到的职位id
		 */
		String idStr = ",".concat(pVO.getNameid().toString()).concat(",");
		PhoneInfoDAO pdao = new PhoneInfoDAO();
		pdao.setWhereClause("nameids like '%" + idStr + "%'");
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(pdao);
		try {
			/*
			 * infoNameids 得到电话簿中职位的Id noteId 得到电话簿Id
			 */
			List infolist = factory.find(new PhoneInfoVO());
			String infoNameids = "";
			Integer noteId = null;
			int index = 0;
			if (infolist != null && infolist.size() > 0) {
				Iterator Itr = infolist.iterator();
				String sql = "";
				Integer newOrder = null;
				while (Itr.hasNext()) {
					PhoneInfoVO pvo = (PhoneInfoVO) Itr.next();
					noteId = pvo.getNoteid();
					infoNameids = pvo.getNameids();
					// 解析NAMEIDS
					String replaceJobId = infoNameids;
					if ("delete".equals(doflag)) { // 删除时需要修改NAMEIDS
						if (infoNameids.equals(idStr)) {
							replaceJobId = "";
						} else {
							// 去掉一个逗号一个数字
							index = infoNameids.indexOf(idStr);
							if (index == 0) { // 形如：,2,3,4, idStr=",2,"
								replaceJobId = infoNameids.substring(index + 2);
							} else { // 除上面的情况
								replaceJobId = infoNameids.substring(0, index)
										.concat(
												infoNameids
														.substring(index + 2));
							}
						}
					}
					// 更新一条记录
					// PhoneInfoVO newPVO = new PhoneInfoVO();
					// newPVO.setOrguuid(pvo.getOrguuid());
					// newPVO.setUseruuid(pvo.getUseruuid());
					// newPVO.setUsername(pvo.getUsername());
					// newPVO.setNameid(pvo.getNameid());
					// newPVO.setOfficephone(pvo.getOfficephone());
					// newPVO.setHomephone(pvo.getHomephone());
					// newPVO.setNetphone(pvo.getNetphone());
					// newPVO.setMobilephone(pvo.getMobilephone());
					// newPVO.setFaxphone(pvo.getFaxphone());
					// newPVO.setOfficeaddress(pvo.getOfficeaddress());
					// newPVO.setFunction(pvo.getFunction());
					// newPVO.setIspermission(pvo.getIspermission());
					// newPVO.setMaintanperson(pvo.getMaintanperson());
					// newPVO.setLastmaintantime(pvo.getLastmaintantime());
					// newPVO.setJobid(replaceJobId);
					// newPVO.setIsparttime(pvo.getIsparttime());
					// 得到新的排序号,并作了重新排序
					newOrder = new Integer(this.getNoteOrder(replaceJobId));
					this.UpdatePhone(noteId, replaceJobId, newOrder);
				} // while
			} // if
		} catch (DAOException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * 由职位ID得到职位称
	 * 
	 * @author firecoral
	 * 
	 */
	public String GetJobName(Integer jobId) {
		String jobName = JobBean.getInstance().getJobName(jobId);
		return jobName;
	}

	/**
	 * 由职位ID得到职位级别
	 * 
	 * @author firecoral
	 * 
	 */
	public Integer GetJobLevel(Integer jobId) {
		Integer jobLevel = JobBean.getInstance().getJobLevel(jobId);
		return jobLevel;
	}

	/**
	 * 对电话记录表中的排序字段写入一个排序号
	 * 
	 * @author firecoral
	 * 
	 */
	public Integer setInfoNoteOrder(String jobId) {
		Integer newNoteOrder = null;
		PhoneInfoDAO pdao = new PhoneInfoDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(pdao);
		Statement stmt = null;
		try {
			List infolist = factory.find(new PhoneInfoVO());
			if (infolist.size() > 0 && "".equals(jobId)) { // (当新记录没有职务，则放在表最后)
				newNoteOrder = Integer.valueOf(infolist.size());
			} else if (infolist.size() > 0) { // 新记录中有职务
				Iterator infoItr = infolist.iterator();
				String infoJobId = "";
				Integer noteOrder = null;
				boolean isHave = false;
				while (infoItr.hasNext()) {
					PhoneInfoVO pVO = (PhoneInfoVO) infoItr.next();
					infoJobId = pVO.getNameids();
					noteOrder = pVO.getNoteorder();
					Integer noteId = pVO.getNoteid();
					// 解析JobId字符串,比较是否找到一个比新职级小的职级
					if (isSmall(infoJobId, jobId)) { // 找到一个比新职级小的职级
						newNoteOrder = noteOrder;
						// 后面的记录order加1
						String sql = "update phone_info set noteorder=noteorder+1 where noteid>="
								+ noteId;
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
						stmt.close();
						isHave = true;
					}
				}
				if (!isHave) { // 没有找到一个比新职级小的职级,则放在最后
					newNoteOrder = Integer.valueOf(infolist.size());
				}
			} else { // 为第一条记录
				newNoteOrder = Integer.valueOf(0);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return newNoteOrder;
	}

	/**
	 * 解析JobId字符串,比较是否找到一个比新职级小的职级
	 * 
	 * @author firecoral
	 * 
	 */
	public boolean isSmall(String jobId, String newJobId) {
		boolean issmall = false;
		StringTokenizer tempToken = new StringTokenizer(jobId, ",");
		int jobNum = tempToken.countTokens();
		StringTokenizer newToken = new StringTokenizer(newJobId, ",");
		int newjobNum = newToken.countTokens();
		if (jobNum == 0) { // 相比的记录没有职务
			issmall = true;
		} else { // 都有职务
			if (jobNum <= newjobNum) { // 比较职务个数
				issmall = getCompResult(newToken, tempToken, jobNum, "1");
			} else {
				issmall = getCompResult(newToken, tempToken, newjobNum, "0");
			}
		}
		return issmall;
	}

	/**
	 * 用一个递归来得到相比的结果
	 * 
	 * @author firecoral
	 * 
	 */
	public boolean getCompResult(StringTokenizer newToken,
			StringTokenizer tempToken, int forNum, String pd) {
		boolean issmall = false;
		int newId = JobBean.getInstance().getJobLevel(
				new Integer(newToken.nextToken())).intValue();
		int tempId = JobBean.getInstance().getJobLevel(
				new Integer(tempToken.nextToken())).intValue();
		for (int i = 0; i < forNum; i++) {
			if (newId > tempId) {
				issmall = true;
			} else if (newId < tempId) {
				issmall = false;
			} else { // 相等
				if (forNum - 1 == i) { // 比到最后一个
					if ("1".equals(pd)) { // 新的职务长
						return false;
					} else {
						return true;
					}
				} else {
					issmall = getCompResult(newToken, tempToken, forNum - 1, pd);
				}
			}
			break;
		}
		return issmall;
	}

	/**
	 * 查出所有本部门的职务记录
	 * 
	 * @author firecoral
	 * 
	 */
	public List getOrgJob(String orguuid) {
		PhoneJobnameDAO jdao = new PhoneJobnameDAO();
		jdao.setOrguuid(orguuid);
		jdao.addOrderBy("jobid", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(jdao);
		List list = new ArrayList();
		try {
			list = factory.find(new PhoneJobnameVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 判断是否为应用角色
	 * 
	 * @author firecoral
	 * 
	 */
	// public boolean hasRole(String personuuid, String roleCode) {
	// try {
	// boolean pd = false;
	// Person p = new Person();
	// p.setUuid(personuuid);
	// RightManager r = RightManager.getInstance();
	// List roleList = r.findAppRolesByPerson(p);
	// for (int i = 0; i < roleList.size(); i++) {
	// AppRole ap = (AppRole) roleList.get(i);
	// if (roleCode.equals(ap.getRolecode())) {
	// pd = true;
	// break;
	// }
	// }
	// return pd;
	// } catch (Exception e) {
	// return false;
	// }
	// }
	public boolean hasRole(String personuuid, String roleCode) {
		Statement stmt = null;
		ResultSet rs = null;
		try {

			boolean pd = false;
			stmt = conn.createStatement();
			String sql = "select rolecode from sys_approle_person where personuuid="
					+ "'" + personuuid + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String role = rs.getString("rolecode");
				if (role != null) {
					if (role.equals(roleCode)) {
						return true;
					}
				}

			}
			return pd;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

	}

	/**
	 * 增加一个部门的职务名称
	 * 
	 * @author firecoral
	 * 
	 */
	public void addOrgjob(String name, Integer jobid, String orguuid) {
		PhoneJobnameDAO dao = new PhoneJobnameDAO();
		dao.setName(name);
		dao.setJobid(jobid);
		dao.setOrguuid(orguuid);
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除一个部门的职务名称
	 * 
	 * @author firecoral
	 * 
	 */
	// public void deleteOrgjob(Integer nameId) {
	// String sql =
	// "SELECT count(*)FROM PHONE_INFO where NAMEIDS="
	// + ","
	// + nameId
	// + ",";
	// ResultSet rs = null;
	// try {
	// rs = conn.createStatement().executeQuery(sql);
	//
	// if (rs != null) {
	// canDelete(nameId);
	//
	// } else {
	// PhoneJobnameDAO dao = new PhoneJobnameDAO();
	// dao.setNameid(nameId);
	// dao.setConnection(conn);
	// try {
	// dao.delete();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	// } catch (SQLException e) {
	//
	// e.printStackTrace();
	// }
	//
	// }
	public void deleteOrgjob(Integer nameId) {

		PhoneJobnameDAO dao = new PhoneJobnameDAO();
		dao.setNameid(nameId);
		dao.setConnection(conn);
		try {
			dao.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 验证部门的职务是否可以删除
	 * 
	 * @return true/不可以删除 ，false/可以删除
	 */
	public boolean canDelete(Integer nameId) {

		Statement stmt = null;
		ResultSet rs = null;
		try {

			List list = new ArrayList();
			stmt = conn.createStatement();
			// String sql =
			// "SELECT count(*)FROM PHONE_INFO where NAMEIDS="
			// +"'"+ ","
			// + nameId
			// +","+ "'";
			String sql = "SELECT NAMEIDS FROM PHONE_INFO";
			// System.out.println("sql=" + sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String a = rs.getString("NAMEIDS");
				if (a != null) {

					a = a.substring(1, a.length() - 1);

					ArrayList temp = new ArrayList();
					temp = parseStringToArrayList(a, ",");
					if (temp.contains(nameId.toString())) {

						return true;

					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		return false;

	}

	/**
	 * 更新一个部门的职务名称
	 * 
	 * @author firecoral
	 * 
	 */
	public void updateOrgjob(Integer nameId, String name, Integer jobId) {
		PhoneJobnameDAO dao = new PhoneJobnameDAO();
		dao.setNameid(nameId);
		dao.setConnection(conn);
		try {
			dao.setJobid(jobId);
			dao.setName(name);
			dao.update(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过一个nameId得到一个相应的VO
	 * 
	 * @author firecoral
	 * 
	 */
	public PhoneJobnameVO getOrgjobVO(Integer nameId) {
		PhoneJobnameDAO dao = new PhoneJobnameDAO();
		dao.setNameid(nameId);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		PhoneJobnameVO pvo = null;
		try {
			List list = factory.find(new PhoneJobnameVO());
			pvo = (PhoneJobnameVO) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pvo;
	}

	/**
	 * 得到本部门所有职务的名称
	 * 
	 * @author firecoral
	 * 
	 */
	public List getOrgjobname(String orguuid) {
		PhoneJobnameDAO dao = new PhoneJobnameDAO();
		dao.setOrguuid(orguuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List jobnamelist = new ArrayList();
		try {
			List list = factory.find(new PhoneJobnameVO());
			if (list != null && list.size() > 0) {
				PhoneJobnameVO vo = null;
				Iterator Itr = list.iterator();
				while (Itr.hasNext()) {
					vo = (PhoneJobnameVO) Itr.next();
					jobnamelist.add(vo.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobnamelist;
	}

	// /**
	// * 添加一个新的电话权限记录
	// * @author firecoral
	// *
	// */
	// public void NewPermissionPhone(PhonePersmissionVO pvo) {
	// PhonePersmissionDAO pdao = new PhonePersmissionDAO();
	// pdao.setValueObject(pvo);
	// pdao.setConnection(conn);
	// try {
	// pdao.create();
	// } catch (DAOException e) {
	// e.printStackTrace();
	// }
	// }
	//	
	// /**
	// * 删除一个的电话权限记录
	// * @author firecoral
	// *
	// */
	// public void DelPermissionPhone(Integer permissionid) {
	// PhonePersmissionDAO pdao = new PhonePersmissionDAO();
	// pdao.setPermissionId(permissionid);
	// pdao.setConnection(conn);
	// try {
	// pdao.delete();
	// } catch (DAOException e) {
	// e.printStackTrace();
	// }
	// }

	// /**
	// * 通过一个noteid得到一个被限制权限的人名称
	// * @author firecoral
	// *
	// */
	// public List GetPermissionPersonName(Integer noteid) {
	// List namelist = new ArrayList();
	// PhonePersmissionDAO pdao = new PhonePersmissionDAO();
	// pdao.setNoteid(noteid);
	// DAOFactory factory =new DAOFactory(conn);
	// factory.setDAO(pdao);
	// try {
	// List permissionlist = factory.find(new PhonePersmissionVO());
	// if(permissionlist!=null&&permissionlist.size()>0){
	// Iterator perItr = permissionlist.iterator();
	// while(perItr.hasNext()){
	// PhonePersmissionVO pVO = (PhonePersmissionVO)perItr.next();
	// namelist.add(this.getUserName(pVO.getPersonuuid()));
	// }
	// }
	// } catch (DAOException e) {
	// e.printStackTrace();
	// }
	// return namelist;
	// }
	//	
	// /**
	// * 通过一个noteid得到一个被限制权限的人
	// * @author firecoral
	// *
	// */
	// public List GetPermissionPersonUUid(Integer noteid) {
	// List personUUidlist = new ArrayList();
	// PhonePersmissionDAO pdao = new PhonePersmissionDAO();
	// pdao.setNoteid(noteid);
	// DAOFactory factory =new DAOFactory(conn);
	// factory.setDAO(pdao);
	// try {
	// personUUidlist = factory.find(new PhonePersmissionVO());
	// } catch (DAOException e) {
	// e.printStackTrace();
	// }
	// return personUUidlist;
	// }
	//	
	// /**
	// * 通过一个noteid、和personUUid得到此人是否被限制
	// * @author firecoral
	// *
	// */
	// public boolean isPermission(String personUUid,Integer noteid) {
	// boolean pd = false;
	// PhonePersmissionDAO pdao = new PhonePersmissionDAO();
	// DAOFactory factory =new DAOFactory(conn);
	// pdao.setWhereClause("NOTEID="+noteid+"AND PERSONUUID='"+personUUid+"'");
	// factory.setDAO(pdao);
	// try {
	// List permissionlist = factory.find(new PhonePersmissionVO());
	// if(permissionlist.size()>0){
	// pd = true;
	// }
	// } catch (DAOException e) {
	// e.printStackTrace();
	// }
	// return pd;
	// }

	/**
	 * 把包括分隔符的字符串转换成ArrayList
	 * 
	 * @param strlist
	 *            含有分隔符号的字符串
	 * @param ken
	 *            分隔符号
	 * @return
	 */
	public ArrayList parseStringToArrayList(String strlist, String ken) {

		StringTokenizer st = new StringTokenizer(strlist, ken);
		if (strlist == null || strlist.equals("") || st.countTokens() <= 0) {
			return new ArrayList();
		}
		int size = st.countTokens();
		ArrayList strv = new ArrayList();
		for (int i = 0; i < size; i++) {
			String nextstr = st.nextToken();
			if (!nextstr.equals("")) {
				strv.add(nextstr);
			}
		}
		return strv;
	}

	/**
	 * 得到平台的某人的信息发布的人员类型
	 * 
	 * @param personuuid
	 * @return
	 * @throws HandlerException
	 */
	public String getRO_PersonType(String person_uuid) throws HandlerException {

		// Connection conn = null;
		List list = null;
		String info_type = null;

		try {
			DAOFactory factory = new DAOFactory(conn);
			SysPersonDAO2 dao = new SysPersonDAO2();
			if (person_uuid == null)
				person_uuid = "1";
			dao.setPersonuuid(person_uuid);
			factory.setDAO(dao);

			list = factory.find(new SysPersonVO2());

			if (list != null && list.size() == 1) {
				info_type = ((SysPersonVO2) list.get(0)).getJob();
			} else {
				info_type = "普通人员";
			}
			return info_type;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("取得平台的人员的信息发布类型列表有误");

		}
	}

	/**
	 * 在电话簿中修改平台的人员类型
	 * 
	 * @param personuuid
	 * @param type
	 */
	public void update_person_type(String personuuid, String type) {
		Connection conn1 = null;

		System.out.println("personuuid   " + personuuid);
		System.out.println("type   " + type);

		if (personuuid == null || type == null || "".equals(personuuid)
				|| "".equals(type))
			return;

		try {
			conn1 = DBConnectionLocator.getInstance().getConnection(
					"jdbc/ROEEE");
			DAOFactory factory = new DAOFactory(conn1);
			RoPersonDAO dao = new RoPersonDAO();
			factory.setDAO(dao);
			dao.setConnection(conn1);
			dao.setPersonuuid(personuuid);

			dao = (RoPersonDAO) factory.findByPrimaryKey();
			dao.setJob(type);
			dao.update(true);

		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();

		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			if (conn1 != null) {
				try {
					conn1.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	/**
	 * 根据人员UUID,从人事视图中获取人员信息
	 * 
	 * @auth zhanggk
	 * @param personuuid
	 */
	public String getPersonJobCode(String personuuid) {
		String jobcode = "";
		Statement stat = null;
		ResultSet rs = null;
		try {
			System.out.println("person uuid is " + personuuid);
			DAOFactory factory = new DAOFactory(conn);
			PhoneInfoDAO pidao = new PhoneInfoDAO();
			// 根据人员uuid从电话表中获取人员信息
			pidao.setWhereClause(" 1=1 and useruuid = '" + personuuid + "' ");
			factory.setDAO(pidao);
			List pilist = factory.find(new PhoneInfoVO());

			if (pilist != null && pilist.size() > 0) {
				// 获取人员的人事id
				PhoneInfoVO pivo = (PhoneInfoVO) pilist.get(0);
				String hrid = pivo.getHrid() == null ? "" : pivo.getHrid();
				System.out.println("hr id is " + hrid);
				// 根据人事id,从人事系统中获取该人员的职级信息
				if (!"".equals(hrid)) {
					String jobsel = "select jobcode from hrperson where hrid='"
							+ hrid + "'";
					System.out.println("=========job sel sql " + jobsel);
					stat = conn.createStatement();
					rs = stat.executeQuery(jobsel);

					if (rs.next()) {
						jobcode = rs.getString("jobcode");
					}
					return jobcode;
				}

			} else {
				return "";
			}
			//

		} catch (Exception e1) {
			e1.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return jobcode;
	}

	/**
	 * 根据职级获取该人员的电话簿查看权限
	 * 
	 * @auth zhanggk
	 * @param jobcode
	 */
	public String getSearchPrivJobCode(String jobcode) {
		String searchpriv = "";
		try {

			DAOFactory factory = new DAOFactory(conn);
			PhonePrivDAO ppdao = new PhonePrivDAO();
			// 根据人员uuid从电话表中获取人员信息
			// 条件不严谨,应从定义改起--------------------
			ppdao.setWhereClause(" 1=1 and pp_type='0' and pp_level like '%"
					+ jobcode + "%' ");
			factory.setDAO(ppdao);
			List pplist = factory.find(new PhonePrivVO());

			if (pplist != null && pplist.size() > 0) {
				for (int i = 0; i < pplist.size(); i++) {
					// 获取人员的人事id
					PhonePrivVO ppvo = (PhonePrivVO) pplist.get(0);
					searchpriv += "," + ppvo.getPp_searchlevel();
				}
			} else {
				return "";
			}
			//

		} catch (Exception e1) {
			e1.printStackTrace();

		}
		return searchpriv;
	}

	/**
	 * 根据人员uuid获取其特殊的电话簿查看权限
	 * 
	 * @auth zhanggk
	 * @param jobcode
	 */
	public String getSpecialSearchPriv(String personuuid) {
		String searchpriv = "";
		String result = "";
		Statement stat = null;
		ResultSet rs = null;
		try {
			// 获取特殊查看权限的范围
			String sqlsel = "select p.pp_scope pp_scope from phone_priviledge p,phone_privperson pp where p.pp_id=pp.pp_id and pp.personuuid='"
					+ personuuid + "'";
			System.out.println("+++++++++++special priv " + sqlsel);
			stat = conn.createStatement();
			rs = stat.executeQuery(sqlsel);

			while (rs.next()) {
				searchpriv += rs.getString("pp_scope") + ",";
			}
			if (searchpriv.length() > 0) {
				// System.out.println("==========search priv is before substring "+searchpriv);
				searchpriv = searchpriv.substring(0, searchpriv.length() - 1);
				// System.out.println("==========search priv is "+searchpriv);
				String[] searchprivs = searchpriv.split(",");
				// System.out.println("++++++++++person special priv is "+searchprivs.length+"  "+searchprivs.toString());
				// 获取特殊查看权限对应的部门code
				if (searchprivs != null) {
					for (int j = 0; j < searchprivs.length; j++) {
						String temp = searchprivs[j];
						// System.out.println("---------person org is "+temp);
						if ("0".equals(temp)) {
							// 可以查看全部
							return "0";
						} else if ("-1".equals(temp)) {
							// 可以查看本部门,根据uuid获取所在局级部门信息
							// System.out.println("------------in get orgcode ");
							EntityManager em = EntityManager.getInstance();
							List orglist = em.findOrgsByPersonUuid(personuuid);

							// System.out.println("orglist size is "+orglist.size());
							if (orglist != null) {
								for (int i = 0; i < orglist.size(); i++) {
									Organization org = (Organization) orglist
											.get(i);
									// if(org.getOrglevel().intValue()==3){
									result += org.getOrgcode() + ",";
									// System.out.println("result is "+result);
									// }
								}
							}

						} else {
							// 根据部门uuid,获取部门编码
							EntityManager em = EntityManager.getInstance();
							Organization org = em.findOrganizationByUuid(temp);
							result += org.getOrgcode() + ",";
							System.out.println("************result is "
									+ result);
						}
					}
				}
			} else {
				return "";
			}

			return result;

		} catch (Exception e1) {
			e1.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 根据部门UUID,获取所在的局级部门代码
	 * 
	 * @auth zhanggk
	 * @param jobcode
	 */
	public String getOrgCodeByUuid(String uuid) {
		String searchpriv = "";
		String result = "";
		Statement stat = null;
		ResultSet rs = null;
		try {
			// 获取特殊查看权限的范围
			String sqlsel = "select ";
			stat = conn.createStatement();
			rs = stat.executeQuery(sqlsel);

			if (rs.next()) {
				searchpriv += rs.getString("pp_scope") + ",";
			}
			if (searchpriv.length() > 0) {
				searchpriv = searchpriv.substring(0, searchpriv.length() - 1);
				String[] searchprivs = searchpriv.split(",");

				// 获取特殊查看权限对应的部门code
				if (searchprivs != null) {
					for (int j = 0; j < searchprivs.length; j++) {
						String temp = searchprivs[j];
						if ("0".equals(temp)) {
							// 可以查看全部
							return "0";
						} else if ("-1".equals(temp)) {
							// 可以查看本部门,根据uuid获取所在局级部门信息

						} else {
							// 根据部门uuid,获取部门编码

							result += temp + ",";
						}
					}
				}
			} else {
				return "";
			}

			return searchpriv;

		} catch (Exception e1) {
			e1.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return searchpriv;
	}

}

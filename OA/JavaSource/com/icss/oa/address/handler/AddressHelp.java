/*
 * Created on 2004-4-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.address.vo.*;
import com.icss.oa.address.dao.AddressGroupinfoDAO;
import com.icss.oa.config.AddressConfig;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;
import com.icss.resourceone.sdk.framework.Person;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressHelp {
	/**
	 * Comment for <code>conn</code>
	 */
	private Connection conn;
	/**
	 * Comment for <code>closeconn</code>
	 */
	private String closeconn = "";
	public AddressHelp() {
	}
	/**
	 * @param connection
	 */
	public AddressHelp(Connection connection) {
		this.conn = connection;
		this.closeconn = "1";
	}

	/**
	 * @param addressjndi
	 * @deprecated	此方法不推荐使用yy
	 */
	public AddressHelp(String addressjndi) {
		try {
			this.conn = DBConnectionLocator.getInstance().getConnection(addressjndi);
			ConnLog.open("AddressHelp");
		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭Connection
	 * @deprecated	此方法不推荐使用yy
	 */
	public void closeConnection() {
		if (this.conn != null) {
			try {
				this.conn.close();
				ConnLog.close("AddressHelp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param orgpersonlist
	 * @param request
	 * @param sessionname
	 * @return
	 * @throws HandlerException
	 */
	public List getperson(List orgpersonlist, HttpServletRequest request, String sessionname) throws HandlerException {
		int tempflag = 1;
		Group group = new Group(conn);
		List resultList = new ArrayList();
		Map resultMap = new HashMap(1000); // hash table
		resultMap = new TreeMap(); // sorted map
		if (orgpersonlist != null && orgpersonlist.size() > 0) {
			Iterator iterator = orgpersonlist.iterator();
			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);

			try {
				while (iterator.hasNext()) {
					SelectOrgpersonVO vo = (SelectOrgpersonVO) iterator.next();
					if (isPerson(vo)) {
						resultMap.put(vo.getUseruuid(), vo);
						//如果是人员，直接添加到personList;
					} else {
						String grouptype = getGroupType(vo);
						if ("2".equals(grouptype)) { //如果是个人分组
							//						System.out.println("得到个人分组开始开始1！");
							List addressgroupinfolist = group.personInGroup(Integer.valueOf(vo.getUserid()), grouptype);
							//						System.out.println("addressgroupinfolist.size()=   "+addressgroupinfolist.size());
							//						System.out.println("得到个人分组开始开始2！");
							Iterator addressgroupinfoiterator = addressgroupinfolist.iterator();
							//						System.out.println("得到个人分组开始开始3！");
							addGroupPersonIntoResultList(resultMap, handler, addressgroupinfoiterator);
							//						System.out.println("得到个人分组开始开始4！");
							//						System.out.println("resultMap.size()   "+resultMap.size());
						} else { //如果是公共分组
							List grouplist = group.getGroupByParentGroupOrGroup(new Integer(vo.getUserid()));
							Iterator groupiterator = grouplist.iterator();
							while (groupiterator.hasNext()) {
								AddressCommongroupVO addresscommongroupvo = (AddressCommongroupVO) groupiterator.next();

								List addressgroupinfolist = group.personInGroup(addresscommongroupvo.getId(), grouptype);
								Iterator addressgroupinfoiterator = addressgroupinfolist.iterator();

								addGroupPersonIntoResultList(resultMap, handler, addressgroupinfoiterator);
							} //while		
						} //else
					} //else
				} //while
				//清除session
				HttpSession tempsession = request.getSession();
				tempsession.removeAttribute(sessionname);

				Iterator it = resultMap.values().iterator();
				while (it.hasNext()) {
					// Get value
					SelectOrgpersonVO tempvo = (SelectOrgpersonVO) it.next();
					resultList.add(tempvo);
					System.out.println("name = " + tempvo.getName());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if ((!this.closeconn.equals("1")) && (conn != null)){
						conn.close();
						ConnLog.close("AddressHelp.getperson");
					}
						
				} catch (Exception e) {

				}
				//
			}
		}
		return resultList;
	}

	/**
	 * @param orgpersonlist
	 * @param request
	 * @param sessionname
	 * @return
	 * @throws HandlerException
	 */
	public List getentrust(List orgpersonlist, HttpServletRequest request, String sessionname) throws HandlerException {
		int tempflag = 1;
		Group group = new Group(conn);
		List resultList = new ArrayList();
		Map resultMap = new HashMap(); // hash table
		resultMap = new TreeMap(); // sorted map
		if (orgpersonlist.size() > 0) {
			Iterator iterator = orgpersonlist.iterator();
			SysOrgpersonHandler handler = new SysOrgpersonHandler(conn);

			try {
				while (iterator.hasNext()) {
					SelectOrgpersonVO vo = (SelectOrgpersonVO) iterator.next();
					if (isPerson(vo)) {
						vo = getentrustvo(vo);
						resultMap.put(vo.getUseruuid(), vo);
						//如果是人员，直接添加到personList;
					} else {
						String grouptype = getGroupType(vo);
						if ("2".equals(grouptype)) { //如果是个人分组
							List addressgroupinfolist = group.personInGroup(Integer.valueOf(vo.getUserid()), grouptype);
							Iterator addressgroupinfoiterator = addressgroupinfolist.iterator();

							addGroupPersonIntoResultList(resultMap, handler, addressgroupinfoiterator);
						} else { //如果是公共分组
							List grouplist = group.getGroupByParentGroupOrGroup(new Integer(vo.getUserid()));
							Iterator groupiterator = grouplist.iterator();
							while (groupiterator.hasNext()) {
								AddressCommongroupVO addresscommongroupvo = (AddressCommongroupVO) groupiterator.next();

								List addressgroupinfolist = group.personInGroup(addresscommongroupvo.getId(), grouptype);
								Iterator addressgroupinfoiterator = addressgroupinfolist.iterator();

								addEntrustPersonIntoResultList(resultMap, handler, addressgroupinfoiterator);
							} //while		
						} //else
					} //else
				} //while
				//清除session
				HttpSession tempsession = request.getSession();
				tempsession.removeAttribute(sessionname);

				Iterator it = resultMap.values().iterator();
				while (it.hasNext()) {
					// Get value
					SelectOrgpersonVO tempvo = (SelectOrgpersonVO) it.next();
					resultList.add(tempvo);
					System.out.println("entrust name = " + tempvo.getName());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if ((!this.closeconn.equals("1")) && (conn != null))
						conn.close();
				} catch (Exception e) {

				}
				//
			}
		}
		return resultList;
	}

	/**
	 * @param resultMap
	 * @param handler
	 * @param addressgroupinfoiterator
	 * @throws HandlerException
	 */
	private void addGroupPersonIntoResultList(Map resultMap, SysOrgpersonHandler handler, Iterator addressgroupinfoiterator) throws HandlerException {
		//		System.out.println("input tht function");
		int i = 0;
		while (addressgroupinfoiterator.hasNext()) {
			AddressGroupinfoVO addressgroupinfovo = (AddressGroupinfoVO) addressgroupinfoiterator.next();

			SelectOrgpersonVO selectorgpersonvo = makeupSelectedVO(handler, addressgroupinfovo);

			//			System.out.println("ResultList = "+ selectorgpersonvo.getName());

			if (selectorgpersonvo.getUseruuid() != null && !"".equals(selectorgpersonvo.getUseruuid())) {
				try {
					resultMap.put(selectorgpersonvo.getUseruuid(), selectorgpersonvo);
					//					System.out.println("ResultList       = "+selectorgpersonvo.getName()+"successfully!");
					//					System.out.println("resultMap.size() = " + resultMap.size());
					//					System.out.println("count            = " + i);
					i++;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			} else {
				//				System.out.println("yaoming  selectorgpersonvo.getName()'s uuid = null "+selectorgpersonvo.getName());
			}
		}
		//		System.out.println("All_resultMap.size() "+resultMap.size());
	}

	private void addEntrustPersonIntoResultList(Map resultMap, SysOrgpersonHandler handler, Iterator addressgroupinfoiterator) throws HandlerException {
		while (addressgroupinfoiterator.hasNext()) {
			AddressGroupinfoVO addressgroupinfovo = (AddressGroupinfoVO) addressgroupinfoiterator.next();

			SelectOrgpersonVO selectorgpersonvo = makeupSelectedVO(handler, addressgroupinfovo);
			selectorgpersonvo = getentrustvo(selectorgpersonvo);
			resultMap.put(selectorgpersonvo.getUseruuid(), selectorgpersonvo);
		}
	}

	private boolean isPerson(SelectOrgpersonVO vo) {
		return vo.getIsperson().equals("1");
	}

	/**
	 * 建立vo
	 * @param handler
	 * @param addressgroupinfovo
	 * @return
	 * @throws HandlerException
	 */
	private SelectOrgpersonVO makeupSelectedVO(SysOrgpersonHandler handler, AddressGroupinfoVO addressgroupinfovo) throws HandlerException {

		SelectOrgpersonVO selectorgpersonvo = new SelectOrgpersonVO();
		selectorgpersonvo.setUseruuid(addressgroupinfovo.getUserid());
		//根据userid查找出user信息
		List userList = new ArrayList();
		try {
			userList = handler.getbyuseruuid(addressgroupinfovo.getUserid());

			Iterator result = userList.iterator();

			SysOrgpersonVO sysorgpersonvo = new SysOrgpersonVO();
			if (result.hasNext()) {
				sysorgpersonvo = (SysOrgpersonVO) result.next();
			}
			//向vo写入name信息
			selectorgpersonvo.setName(sysorgpersonvo.getCnname());
			//向vo写入useruuid信息
			selectorgpersonvo.setUserid(sysorgpersonvo.getUserid());
			selectorgpersonvo.setIsperson("1");

		} catch (HandlerException e) {
			e.printStackTrace();
		}

		return selectorgpersonvo;
	}

	/**
	 * 取得分组类型
	 * @param vo
	 * @return
	 */
	private String getGroupType(SelectOrgpersonVO vo) {
		String grouptype;
		if (vo.getIsperson().equals("00")) {
			grouptype = AddressConfig.GROUPTYPE_COMMOM;
		} else {
			grouptype = AddressConfig.GROUPTYPE_PRIVATE;
		}
		return grouptype;
	}

	/**
	 * 确保结果集唯一判断
	 * @param resultList
	 * @param addressgroupinfovo
	 * @return
	 */
	private boolean isInResultList(List resultList, AddressGroupinfoVO addressgroupinfovo) {
		for (int index = 0; index < resultList.size(); index++) {
			SelectOrgpersonVO vo2 = (SelectOrgpersonVO) resultList.get(index);
			if (addressgroupinfovo.getUserid().equals(vo2.getUserid())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据vo得到代理人员的vo
	 * @param vo
	 * @return
	 */
	public SelectOrgpersonVO getentrustvo(SelectOrgpersonVO vo) {
		EntrustHandler ehandler = new EntrustHandler(conn);
		SelectOrgpersonVO selectorgpersonvo = new SelectOrgpersonVO();
		List list = new ArrayList();
		try {
			list = ehandler.getByEntrustUid(vo.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list.isEmpty()) {
			return vo;
		} else {
			Iterator iterator = list.iterator();
			EntrustVO entrustvo = null;
			if (iterator.hasNext()) {
				entrustvo = (EntrustVO) iterator.next();
			}
			selectorgpersonvo.setUserid(entrustvo.getSubstituteUid());
			selectorgpersonvo.setUseruuid(getUseruuid(entrustvo.getSubstituteUid()));
			selectorgpersonvo.setName(getUserName(entrustvo.getSubstituteUid()));
			selectorgpersonvo.setIsperson("1");

			return selectorgpersonvo;
		}
	}

	/*
	* 根据userid得到person对象
	*/
	public Person getPerson(java.lang.String userid) {
		Person person = null;
		try {
			EntityManager entitymanger = EntityManager.getInstance();
			person = entitymanger.findPersonByUid(userid);
		} catch (EntityException e) {
			e.printStackTrace();
		}
		return person;
	}

	/*
	* 根据userid得到username
	*/
	public String getUserName(java.lang.String userid) {
		String username = "";
		try {
			username = getPerson(userid).getFullName();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		return username;
	}

	/*
	* 根据userid得到username
	*/
	public String getUseruuid(java.lang.String userid) {
		String useruuid = "";
		try {
			useruuid = getPerson(userid).getUuid();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		return useruuid;
	}

	/**
	 * 根据分组id得到该分组下的人员uuid串，利用“，”分开
	 * 
	 * @param id
	 * @return
	 */
	public String getPersonUUID_StringByGruopId(Integer id, String grouptype) {

		Connection conn = null;
		StringBuffer str = new StringBuffer();

		try {
			try {
				conn = DBConnectionLocator.getInstance().getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
				ConnLog.open("AddressHelp.getPersonUUID_StringByGruopId");

			} catch (DBConnectionLocatorException e1) {
				e1.printStackTrace();
			}
			DAOFactory factory = new DAOFactory(conn);
			AddressGroupinfoDAO addressGroupinfoDAO = new AddressGroupinfoDAO();
			addressGroupinfoDAO.setGroupid(id);
			addressGroupinfoDAO.setGrouptype(grouptype);
			factory.setDAO(addressGroupinfoDAO);
			List list = null;
			Iterator it = null;

			list = factory.find(new AddressGroupinfoVO());
			if (list != null)
				it = list.iterator();
			if (it != null) {
				while (it.hasNext()) {
					AddressGroupinfoVO vo = (AddressGroupinfoVO) it.next();
					str.append(vo.getUserid());
					str.append(",");
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new RuntimeException("查找分组内人员错误！");
		} finally {
			if (conn != null) {
				try {
					conn.close();
					ConnLog.close("AddressHelp.getPersonUUID_StringByGruopId");
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		String reStr = str.toString();
		if (reStr.indexOf(",")==-1){
			return reStr;
		}else{
			return reStr.substring(0, reStr.length() - 1);
		}
	}

}

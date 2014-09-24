package com.icss.oa.bbs.handler;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.oa.address.vo.AddressGroupinfoVO;
import com.icss.oa.bbs.dao.*;
import com.icss.oa.bbs.vo.*;
import com.icss.oa.hr.dao.HRSysPersonDAO;
import com.icss.oa.hr.handler.HRGroupHandler;
import com.icss.oa.hr.vo.HRSysPersonVO;
import com.icss.oa.sms.vo.HRSMSMobileVO;

import java.sql.*;
import java.util.*;

public class BBSBoardHandler {
	private Connection conn;

	public BBSBoardHandler(Connection conn) {
		this.conn = conn;
	}

	public boolean getIsAudit(Integer boardid) {
		boolean isAudit = false;
		List list = new ArrayList();
		BbsBoardDAO dao = new BbsBoardDAO();
		dao.setBoardid(boardid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			list = factory.find(new BbsBoardVO());

			if (list != null && !list.isEmpty()) {
				BbsBoardVO vo = (BbsBoardVO) list.get(0);
				if ("1".endsWith(vo.getIsaudit())) {
					isAudit = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAudit;

	}

	/**
	 * 根据userid 取得管理的板块
	 * 
	 * @param userid
	 * @return
	 * @throws DAOException
	 */

	public List getMlist(String userid) throws DAOException {
		List list = new ArrayList();
		BbsManagerDAO dao = new BbsManagerDAO();
		dao.setUserid(userid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		list = factory.find(new BbsManagerVO());
		return list;
	}

	/**
	 * get unaudit list
	 * 
	 * @param vo
	 * @return
	 */
	public List getUnauditList(String userid) {
		ArticleUserinfoSearchDAO dao = new ArticleUserinfoSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql
				.append("BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_ARTICLE,BBS_USERINFO ");
		sql
				.append(" where BBS_ARTICLE.USERID = BBS_USERINFO.USERID and BBS_ARTICLE.ISVIEW = '"
						+ 0
						+ "' and BBS_ARTICLE.BOARDID in (select boardid from bbs_manager where userid='"
						+ userid + "')");
		sql.append(" order by BBS_ARTICLE.TOP desc,BBS_ARTICLE.EMITTIME desc ");

		dao.setSearchSQL(sql.toString());
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new ArticleUserinfoVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据ID取得名字
	 * 
	 * @param boardid
	 * @return
	 * @throws DAOException
	 */

	public String getBoardName(Integer boardid) throws DAOException {
		String name = "未知版块";
		List list = new ArrayList();
		BbsBoardDAO dao = new BbsBoardDAO();
		dao.setBoardid(boardid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			list = factory.find(new BbsBoardVO());
			if (list != null && !list.isEmpty()) {
				BbsBoardVO vo = (BbsBoardVO) list.get(0);
				name = vo.getBoardname();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * 取得所有禁止名单
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List getOutList() throws DAOException {
		BbsOutDAO dao = new BbsOutDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.addOrderBy("cnname", true);
		factory.setDAO(dao);
		return factory.find(new BbsOutVO());
	}

	/**
	 * 取得选择人的所有uuid
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @param s4
	 * @return
	 */
	public List<String> getSendtoUUID(String s1, String s2, String s3, String s4) {

		List<String> uuidList = new ArrayList<String>();
		// 分别得到个人＆个人＆公共分组的字符串
		StringTokenizer addresslist = new StringTokenizer(s1, ",");
		// System.out.println("s1 =" + s1);
		StringTokenizer addresslist2 = new StringTokenizer(s2, ",");
		// System.out.println("s2 =" + s2);
		StringTokenizer addresslist3 = new StringTokenizer(s3, ",");

		StringTokenizer addresslist4 = new StringTokenizer(s4, ",");

		// System.out.println("公共分组。。。。s3 =" + s3);

		// 构造取得分组的handler
		Group group = new Group(conn);
		// 公共分组的handler
		HRGroupHandler handler = new HRGroupHandler(conn);

		// 对取得的个人信息进行解析
		while (addresslist.hasMoreTokens()) {
			String uuid = addresslist.nextToken();
			if (!uuidList.contains(uuid)) {
				uuidList.add(uuid);
			}
			// System.out.println("uuid=" + uuid);
		}

		// 对取得个人分组信息进行解析
		while (addresslist2.hasMoreTokens()) {

			List addressgroupinfolist = group.personInGroup(Integer
					.valueOf(addresslist2.nextToken()), "2");
			// System.out.println("addressgroupinfolist.size() ="+
			// addressgroupinfolist.size());
			Iterator addressgroupinfoiterator = addressgroupinfolist.iterator();
			// System.out.println("addressgroupinfolist.size()1111 ="+
			// addressgroupinfolist.size());
			while (addressgroupinfoiterator.hasNext()) {

				AddressGroupinfoVO addressgroupinfovo = (AddressGroupinfoVO) addressgroupinfoiterator
						.next();

				String uuid = addressgroupinfovo.getUserid();

				if (!uuidList.contains(uuid)) {
					uuidList.add(uuid);
				}
				// System.out.println("uuid =" + uuid);
			}
		}

		// 对取得人事分组的信息进行解析

		while (addresslist3.hasMoreTokens()) {
			// List grouplist = group.getGroupByParentGroupOrGroup(new Integer(
			// addresslist3.nextToken()));
			List grouplist = handler.getAllUuidByGroupid(addresslist3
					.nextToken());
			Iterator groupiterator = grouplist.iterator();

			while (groupiterator.hasNext()) {

				HRSysPersonVO vo = (HRSysPersonVO) groupiterator.next();

				String uuid = vo.getPersonuuid();

				if (!uuidList.contains(uuid)) {
					uuidList.add(uuid);
				}
			}
		}

		// 公共分组
		while (addresslist4.hasMoreTokens()) {

			List grouplist = group.getGroupByParentGroupOrGroup(new Integer(
					addresslist4.nextToken()));
			Iterator groupiterator = grouplist.iterator();

			while (groupiterator.hasNext()) {

				AddressCommongroupVO addresscommongroupvo = (AddressCommongroupVO) groupiterator
						.next();

				List addressgroupinfolist = group.personInGroup(
						addresscommongroupvo.getId(), "1");
				Iterator addressgroupinfoiterator = addressgroupinfolist
						.iterator();

				while (addressgroupinfoiterator.hasNext()) {
					AddressGroupinfoVO addressgroupinfovo = (AddressGroupinfoVO) addressgroupinfoiterator
							.next();
					String uuid = addressgroupinfovo.getUserid();
					if (!uuidList.contains(uuid)) {
						uuidList.add(uuid);
					}
				}
			}
		}
		return uuidList;
	}

	/**
	 * 根据UUID取得姓名
	 * 
	 * @param personuuid
	 * @return
	 */
	public String getPersonName(String personuuid) {
		String name = "";
		HRSysPersonDAO dao = new HRSysPersonDAO();
		dao.setPersonuuid(personuuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find(new HRSysPersonVO());
			if (list != null) {
				HRSysPersonVO personVO = (HRSysPersonVO) list.get(0);
				name = personVO.getCnname();

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return name;
	}

	public void insertOutVO(BbsOutVO vo) throws DAOException {
		BbsOutDAO dao = new BbsOutDAO();
		dao.setValueObject(vo);
		dao.setConnection(conn);
		dao.create();
		// TODO Auto-generated method stub

	}

	public void delUserOUT(String _personuuid) throws DAOException {
		BbsOutDAO dao = new BbsOutDAO();
		dao.setPersonuuid(_personuuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		factory.batchDelete();
	}

	public void uptUserOUT(String _personuuid, Timestamp _time) throws DAOException, SQLException {
		
//		BbsOutDAO dao = new BbsOutDAO();
//		dao.setCnname("name");
//		dao.setPersonuuid(_personuuid);
//		dao.setTime(_time);
//		dao.setWhereClause("PERSONUUID= " + _personuuid);
//     	dao.setConnection(conn);
//     	dao.update();
//		DAOFactory factory = new DAOFactory(conn);
//		factory.setDAO(dao);
//		factory.batchUpdate();
		PreparedStatement pst = conn.prepareStatement("update bbs_out set time=? where PERSONUUID=?");
		pst.setTimestamp(1, _time);
		pst.setString(2, _personuuid);
		pst.executeUpdate();
		pst.close();
	}

}
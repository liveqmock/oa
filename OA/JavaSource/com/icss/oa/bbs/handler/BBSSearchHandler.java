/*
 * Created on 2004-2-25
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.handler;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import com.icss.oa.bbs.dao.*;
import com.icss.oa.bbs.vo.*;

import com.icss.j2ee.dao.DAOFactory;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BBSSearchHandler {
	private Connection conn;
	public BBSSearchHandler(Connection conn) {
		this.conn = conn;
	}

	public List getTopicList(
		Integer boardId,
		String articleMsg,
		String order,
		int time,
		String primeFlag) {
		ArticleUserinfoSearchDAO dao = new ArticleUserinfoSearchDAO();
		DAOFactory factory = new DAOFactory(conn);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(
			"BBS_ARTICLE.ARTICLEID,BBS_ARTICLE.BOARDID,BBS_ARTICLE.ARTICLENAME,BBS_ARTICLE.EMITTIME,BBS_ARTICLE.ARTICLESIZE,BBS_ARTICLE.IP,BBS_ARTICLE.PRIME,BBS_ARTICLE.TOP,BBS_ARTICLE.TOPIC,BBS_ARTICLE.ARTICLECONTENT,BBS_ARTICLE.ARTICLELOCK,BBS_ARTICLE.REID,BBS_ARTICLE.REUSERID,BBS_ARTICLE.RETIME,BBS_ARTICLE.RENUM,BBS_ARTICLE.HITNUM,BBS_ARTICLE.EDITTIME,BBS_ARTICLE.ACCTYPE,BBS_ARTICLE.FACE,BBS_ARTICLE.ISVIEW,BBS_ARTICLE.REUSERNAME,BBS_ARTICLE.ACCID,BBS_USERINFO.USERID,BBS_USERINFO.ONLINEID,BBS_USERINFO.BM_ID,BBS_USERINFO.BBA_ID,BBS_USERINFO.OICQ,BBS_USERINFO.USERPIC,BBS_USERINFO.UNDERWRITE,BBS_USERINFO.PUBNUM,BBS_USERINFO.ACCESSNUM,BBS_USERINFO.USERLEVEL,BBS_USERINFO.REGDATE,BBS_USERINFO.ON_LINE,BBS_USERINFO.USERNAME,BBS_USERINFO.HOMEPAGE,BBS_USERINFO.MAIL,BBS_USERINFO.EXP,BBS_USERINFO.ISLEADER,BBS_USERINFO.TRUENAME ");
		sql.append("FROM ");
		sql.append("BBS_ARTICLE,BBS_USERINFO ");
		sql.append(
			" where BBS_ARTICLE.USERID = BBS_USERINFO.USERID and BBS_ARTICLE.boardid = "
				+ boardId
				+ " and BBS_ARTICLE.topic = '1' and BBS_ARTICLE.ISVIEW='1' ");
		if (primeFlag.equals("1"))
			sql.append(" and BBS_ARTICLE.prime = '1' ");

		if (time != 0) {
			Long toTime =
				new Long(
					System.currentTimeMillis() - time * 24 * 60 * 60 * 1000);
			sql.append(" and BBS_ARTICLE.emittime >'" + toTime + "' ");
		}

		sql.append(" order by BBS_ARTICLE.top desc,");

		sql.append(articleMsg + " " + order);
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

	//用来找到主题的tointend（标识是否写入待办工作）
	public String[] getTointend(Integer articleId) {

		DAOFactory factory1 = new DAOFactory(conn);
		BbsArticleDAO dao1 = new BbsArticleDAO();
		factory1.setDAO(dao1);
		List list1 = null;
		String tointend = new String();
		String userid = new String();
		String username = new String();

		dao1.setConnection(conn);
		dao1.setArticleid(articleId);

		try {
			list1 = factory1.find(new BbsArticleVO());

			Iterator Iterator = list1.iterator();
			BbsArticleVO vo = null;
			while (Iterator.hasNext()) {
				vo = (BbsArticleVO) Iterator.next(); //只得到一条记录
			}

			tointend = vo.getTointend();
			userid = vo.getUserid();

			//得到楼主用户名
			DAOFactory factory2 = new DAOFactory(conn);
			SysPersonDAO dao2 = new SysPersonDAO();
			factory2.setDAO(dao2);
			List list2 = null;

			dao2.setConnection(conn);
			dao2.setPersonuuid(userid);
			try {
				list2 = factory2.find(new SysPersonVO());

				Iterator Iterator2 = list2.iterator();
				SysPersonVO vo2 = null;
				while (Iterator2.hasNext()) {
					vo2 = (SysPersonVO) Iterator2.next(); //只得到一条记录
				}
				username = vo2.getUserid();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] arr = { tointend, username };

		return arr;
	}

}

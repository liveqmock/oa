package com.icss.oa.bbs.handler;

import com.icss.j2ee.dao.*;
import com.icss.oa.bbs.dao.BbsArticleDAO;
import com.icss.oa.bbs.dao.BbsBoardDAO;
import com.icss.oa.bbs.vo.*;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.icss.oa.bbs.handler:
//            BBSHandler, UserMsgHandler

public class BoardArticleUpdate {

	public BoardArticleUpdate(Connection conn) {
		this.conn = conn;
	}

	public void updateBoardByArticle(Integer boardId, String lastId,
			Integer articleId, String lastArticleName, String lastUserName) {
		DAOFactory factory = new DAOFactory(conn);
		BbsBoardDAO dao = new BbsBoardDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setBoardid(boardId);
		try {
			dao = (BbsBoardDAO) factory.findByPrimaryKey();
			dao.setLastid(lastId);
			dao.setLasttime(new Long(System.currentTimeMillis()));
			dao.setLastarticleid(articleId);
			dao.setLastarticlename(lastArticleName);
			dao.setLastusername(lastUserName);
			Integer articleNum = dao.getArticlenum();
			Integer topicNum = dao.getTopicnum();
			int anum = articleNum.intValue();
			anum++;
			dao.setArticlenum(new Integer(anum));
			int tnum = topicNum.intValue();
			tnum++;
			dao.setTopicnum(new Integer(tnum));
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void updateBoardLastArticle(Integer boardId, String lastId,
			Integer articleId, String lastArticleName, String lastUserName) {
		DAOFactory factory = new DAOFactory(conn);
		BbsBoardDAO dao = new BbsBoardDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setBoardid(boardId);
		try {
			dao = (BbsBoardDAO) factory.findByPrimaryKey();
			dao.setLastid(lastId);
			dao.setLasttime(new Long(System.currentTimeMillis()));
			dao.setLastarticleid(articleId);
			dao.setLastarticlename(lastArticleName);
			dao.setLastusername(lastUserName);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void updateBoardLimited(Integer boardId) {
		DAOFactory factory = new DAOFactory(conn);
		BbsBoardDAO dao = new BbsBoardDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setBoardid(boardId);
		try {
			dao = (BbsBoardDAO) factory.findByPrimaryKey();
			dao.setIslimited("1");
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改了更新方法，tNum,aNum 这两个参数已经没有用了
	 * @param boardId
	 * @param tNum
	 * @param aNum
	 */
	public void updateBoard(Integer boardId, int tNum, int aNum) {
		DAOFactory factory = new DAOFactory(conn);
		BbsBoardDAO dao = new BbsBoardDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setBoardid(boardId);
		try {
			dao = (BbsBoardDAO) factory.findByPrimaryKey();
			
			BbsArticleDAO adao = new BbsArticleDAO();
			adao.setBoardid(boardId);
			adao.setIsview("1");
			factory.setDAO(adao);
			List atlist = factory.find();
			adao.setWhereClause("TOPIC='1' and boardid="+boardId + " and isview =1");
			factory.setDAO(adao);
			List tolist = factory.find();

//
//			Integer articleNum = dao.getArticlenum();
//			Integer topicNum = dao.getTopicnum();
//			int anum = articleNum.intValue();
//			anum += aNum;
//			int tnum = topicNum.intValue();
//			tnum += tNum;
			dao.setTopicnum(tolist!=null?tolist.size():0);
			dao.setArticlenum(atlist!=null?atlist.size():0);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void updateArticle(Integer articleId) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			Integer reNum = dao.getRenum();
			int renum = reNum.intValue();
			renum++;
			dao.setRenum(new Integer(renum));
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void updateArticleByHit(Integer articleId) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			Integer hitNum = dao.getHitnum();
			int hnum = hitNum.intValue();
			hnum++;
			dao.setHitnum(new Integer(hnum));
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void updateArticleByArticle(String reUserId, Integer articleId,
			String reuserName) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			//更新回复算法
			BbsArticleDAO dao1 = new BbsArticleDAO();
			dao1.setReid(articleId);
			factory.setDAO(dao1);
			List list = factory.find(new BbsArticleVO());
			
			dao.setReuserid(reUserId);
			dao.setReusername(reuserName);
			dao.setRetime(new Long(System.currentTimeMillis()));
//			Integer reNum = dao.getRenum();
//			int anum = reNum.intValue();
//			anum++;
			
			dao.setRenum(list==null?0:list.size());
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void updateLastArticle(String reUserId, Integer articleId,
			String reuserName) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			dao.setReuserid(reUserId);
			dao.setReusername(reuserName);
			dao.setRetime(new Long(System.currentTimeMillis()));
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void updateLastArticleByDel(String reUserId, Integer articleId,
			String reuserName, Long reTime) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			dao.setReuserid(reUserId);
			dao.setReusername(reuserName);
			dao.setRetime(reTime);
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void updateArticleByDel(Integer articleId, int num) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setArticleid(articleId);
		try {
			dao = (BbsArticleDAO) factory.findByPrimaryKey();
			Integer reNum = dao.getRenum();
			int anum = reNum.intValue();
			anum += num;
			dao.setRenum(new Integer(anum));
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public List getLastTopic(Integer boardId) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setConnection(conn);
		dao
				.setWhereClause(" BOARDID="
						+ boardId
						+ " AND ISVIEW=1 AND EMITTIME=(SELECT MAX(EMITTIME) FROM BBS_ARTICLE WHERE BOARDID="
						+ boardId + " AND TOPIC='1' AND ISVIEW=1) AND TOPIC='1' ");
		factory.setDAO(dao);
		List topicList = null;
		try {
			topicList = factory.find(new BbsArticleVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return topicList;
	}

	public List getLastArticle(Integer articleId) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setConnection(conn);
		dao
				.setWhereClause(" REID="
						+ articleId
						+ " AND ISVIEW=1 AND EMITTIME=(SELECT MAX(EMITTIME) FROM BBS_ARTICLE WHERE REID="
						+ articleId + " AND TOPIC <> '1' AND ISVIEW=1) AND TOPIC <> '1' ");
		factory.setDAO(dao);
		List articleList = null;
		try {
			articleList = factory.find(new BbsArticleVO());

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return articleList;
	}

	public void adjustBoard(Integer boardId) {
		BbsBoardVO boardVO = null;
		BBSHandler handler = new BBSHandler(conn);
		UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
		boardVO = handler.getBoardVO(boardId);
		List topicList = getLastTopic(boardId);
		if (boardVO != null && topicList != null) {
			Iterator it = topicList.iterator();
			BbsArticleVO topicVO = null;
			if (it.hasNext())
				topicVO = (BbsArticleVO) it.next();
			if (topicVO != null) {
				if (boardVO.getLastarticleid() == null
						|| !boardVO.getLastarticleid().equals(
								topicVO.getArticleid())) {
					List userList = userMsgHandler.getUserListById(topicVO
							.getUserid());
					String userName = "";
					if (userList != null) {
						Iterator uit = userList.iterator();
						if (uit.hasNext()) {
							BbsUserinfoVO userVO = (BbsUserinfoVO) uit.next();
							if (userVO != null)
								userName = userVO.getUsername();
						}
					}
					updateBoardLastArticle(topicVO.getBoardid(), topicVO
							.getUserid(), topicVO.getArticleid(), topicVO
							.getArticlename(), userName);
				}
			} else {
				updateBoardLastArticle(boardId, null, null, null, null);
			}
		}
	}

	/**
	 * 更新最后发帖
	 * 
	 * @param articleId
	 */
	public void adjustArticle(Integer articleId) {
		BbsArticleVO articleVO = null;
		BbsArticleVO reArticleVO = null;
		BBSHandler handler = new BBSHandler(conn);
		UserMsgHandler userMsgHandler = new UserMsgHandler(conn);
		articleVO = handler.getArticleVO(articleId);
		List artilceList = getLastArticle(articleId);
		if (artilceList.size() == 0) {
			List list = userMsgHandler.getUserListById(articleVO
					.getUserid());
			String userName = "";
			if (list != null) {
				Iterator uit = list.iterator();
				if (uit.hasNext()) {
					BbsUserinfoVO userVO = (BbsUserinfoVO) uit.next();
					if (userVO != null)
						userName = userVO.getUsername();
				}
			}

			updateLastArticleByDel(articleVO.getUserid(), articleId, userName, articleVO.getEmittime());
		} else if (articleVO != null && artilceList != null
				&& artilceList.size() > 0) {
			reArticleVO = (BbsArticleVO) artilceList.get(0);

			if (reArticleVO != null) {

				if (articleVO.getRetime() != null
						|| !articleVO.getRetime().equals(
								reArticleVO.getEmittime())) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!"
							+ reArticleVO.getUserid());
					List userList = userMsgHandler.getUserListById(reArticleVO
							.getUserid());
					String userName = "";
					if (userList != null) {
						Iterator uit = userList.iterator();
						if (uit.hasNext()) {
							BbsUserinfoVO userVO = (BbsUserinfoVO) uit.next();
							if (userVO != null)
								userName = userVO.getUsername();
						}
					}
					System.out.println("!!!!!!!!!!!+" + userName);
					updateLastArticleByDel(reArticleVO.getUserid(), articleId,
							userName, reArticleVO.getEmittime());
				}
			} else {
				updateLastArticleByDel(null, articleId, null, null);
			}
		}
	}

	public List getArticleOutDelete(Integer boardId, long startTime,
			long endTime) {
		DAOFactory factory = new DAOFactory(conn);
		BbsArticleDAO dao = new BbsArticleDAO();
		dao.setConnection(conn);
		dao.setWhereClause(" (RETIME BETWEEN " + startTime + " AND " + endTime
				+ ") AND EMITTIME < " + startTime
				+ " AND TOPIC='1' AND BOARDID = " + boardId + " ");
		factory.setDAO(dao);
		List articleList = null;
		try {
			articleList = factory.find(new BbsArticleVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return articleList;
	}

	private Connection conn;
}
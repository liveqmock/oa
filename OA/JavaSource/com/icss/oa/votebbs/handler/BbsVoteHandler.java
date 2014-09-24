/*
 * 创建日期 2007-3-6
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.votebbs.handler;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.log.dao.LogMsgDAO;
import com.icss.oa.log.dao.LogSysDAO;
import com.icss.oa.log.vo.LogMsgVO;
import com.icss.oa.votebbs.dao.BbsMainarticleDAO;
import com.icss.oa.votebbs.dao.BbsOptionsDAO;
import com.icss.oa.votebbs.dao.BbsReplyDAO;
import com.icss.oa.votebbs.dao.BbsVoteDAO;
import com.icss.oa.votebbs.dao.BbsVoteuserDAO;
import com.icss.oa.votebbs.vo.BbsMainarticleVO;
import com.icss.oa.votebbs.vo.BbsOptionsVO;
import com.icss.oa.votebbs.vo.BbsReplyVO;
import com.icss.oa.votebbs.vo.BbsStatOptionsVO;
import com.icss.oa.votebbs.vo.BbsVoteVO;
import com.icss.oa.votebbs.vo.BbsVoteuserVO;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class BbsVoteHandler {
	Connection conn = null;
	/**
	 * 
	 * 构造函数
	 * @param _conn
	 */
	public BbsVoteHandler(Connection _conn) {
		this.conn = _conn;
	}
	
	
/**
 * 在管理页面中取得文章列表
 * @return
 */
	public List getArticalManagerList() {
		BbsMainarticleDAO dao = new BbsMainarticleDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.addOrderBy("logTime",false);
		factory.setDAO(dao);
		
		List list = null;
		try {
			list = factory.find(new BbsMainarticleVO());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
/**
 * 增加文章
 * @param articalvo
 */
public void addArtical(BbsMainarticleVO vo) {
	BbsMainarticleDAO dao = new BbsMainarticleDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}
/**
 * 在管理页面中取得选项的列表
 * @param mainid
 * @return
 */
public List getarticalOptionList(Integer mainid) {
	BbsOptionsDAO dao = new BbsOptionsDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setMainid(mainid);
	dao.addOrderBy("opOrder",true);
	factory.setDAO(dao);
	
	List list = null;
	try {
		list = factory.find(new BbsOptionsVO());
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}
/**
 * 增加文章选项
 * @param vo
 */
public void addOption(BbsOptionsVO vo) {
	BbsOptionsDAO dao = new BbsOptionsDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}
/**
 * 根据选项ID删除选项
 * @param optionid
 */
public void deleteArticalOption(String optionid) {
	BbsOptionsDAO dao = new BbsOptionsDAO();
	dao.setOpId(Integer.valueOf(optionid));
	dao.setConnection(conn);
	try {
		dao.delete();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}
/**
 * 根据文章ID删除文章记录
 * @param mainid
 */
public void deleteArtical(String mainid) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  bbs_mainarticle WHERE MAINID = "
			+ mainid;
	System.out.println("deleteArtical     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	} catch (SQLException e) {
		e.printStackTrace();
		
	} finally {
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
	


}
	

/**
 * 根据optionid取得选项的具体信息
 * @param optionid
 * @return
 */
public List getOptionDetail(Integer optionid) {
	BbsOptionsDAO dao = new BbsOptionsDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setOpId(optionid);
	factory.setDAO(dao);
	
	List list = null;
	try {
		list = factory.find(new BbsOptionsVO());
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}
/**
 * 根据文章ID判断是否拥有选项信息
 * @param mainid
 * @return
 */
public boolean hasOptions(String mainid) {
//	System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " SELECT count(OP_ID) AS NUM FROM  bbs_options WHERE MAINID = "
			+ mainid;
//	System.out.println("eeee22222222222222222222222222     "+sql); 

	Statement stmt = null;
	ResultSet rs = null;
	boolean hasoption=false;
	int num=0;
	
	try {
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
//		System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
		if (rs != null) {
			if (rs.next()) {
				num = rs.getInt("num");
				if(num>0){
					hasoption=true;
				}
//				System.out.println("eeee3333333     "+blob.length()); 
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
	
	return hasoption;

}

/**
 * 根据文章ID删除相关选项记录
 * @param mainid
 */
public void deleteOptions(String mainid) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  bbs_options WHERE MAINID = "
			+ mainid;
	System.out.println("deleteOptions     "+sql); 

	Statement stmt = null;

	
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	} catch (SQLException e) {
		e.printStackTrace();
		
	} finally {
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
	


}
/**
 * 修改文章记录信息
 * @param articalvo
 */
public void updateArtical(BbsMainarticleVO articalvo) {
	BbsMainarticleDAO dao = new BbsMainarticleDAO();
	dao.setValueObject(articalvo);
	dao.setConnection(conn);
	try {
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}
/**
 * 修改选项记录信息
 * @param vo
 */
public void updateOption(BbsOptionsVO vo) {
	BbsOptionsDAO dao = new BbsOptionsDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
//		System.out.println("++++++++++++++修改记录bbsvotehandler title="+vo.getOpTitle()+"and opid="+vo.getOpId());
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}
/**
 * 根据optionid取得optionVO
 * @param optionid
 * @return
 */
public BbsOptionsVO getOptionVO(String optionid) {
	BbsOptionsVO vo=new BbsOptionsVO();
	BbsOptionsDAO dao = new BbsOptionsDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setOpId(Integer.valueOf(optionid));
	factory.setDAO(dao);
	
	try {
		vo=(BbsOptionsVO) factory.findByPrimaryKey(new BbsOptionsVO());
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return vo;
}


/**
 * 根据mainid取得管理员回贴列表
 * @param mainid
 * @return
 */
public List getReplyManagerList(Integer mainid) {

	List result = new ArrayList();
	DAOFactory factory = new DAOFactory(conn);
	BbsReplyDAO replydao = new BbsReplyDAO();
	factory.setDAO(replydao);
	replydao.setMainid(mainid);

	replydao.addOrderBy("reId",true);
	try {

		result = factory.find(new BbsReplyVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
/**
 * 在管理员页面根据回复ID删除回贴
 * @param replyid
 */

public void deleteMangerReply(String replyid) {
	BbsReplyDAO dao = new BbsReplyDAO();
	dao.setReId(Integer.valueOf(replyid));
	dao.setConnection(conn);
	try {
		dao.delete();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}
/**
 * 根据replyid取得管理员页面的replyVO
 * @param optionid
 * @return
 */
public BbsReplyVO getMangerReplyVO(String replyid) {
	BbsReplyVO vo=new BbsReplyVO();
	BbsReplyDAO dao = new BbsReplyDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setReId(Integer.valueOf(replyid));
	factory.setDAO(dao);
	
	try {
		vo=(BbsReplyVO) factory.findByPrimaryKey(new BbsReplyVO());
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return vo;
}

/**
 * 在管理员页面进行回贴内容的修改操作
 * @param vo
 */
public void updateMangagerReply(BbsReplyVO vo) {
	BbsReplyDAO dao = new BbsReplyDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
//		System.out.println("++++++++++++++修改记录bbsvotehandler title="+vo.getOpTitle()+"and opid="+vo.getOpId());
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * 根据mainid取得文章记录的详细信息
 * @param integer
 * @return
 */
public List getMainArticalOptionDetailList(Integer optionid) {
	List result = new ArrayList();
	DAOFactory factory = new DAOFactory(conn);
	BbsOptionsDAO dao = new BbsOptionsDAO();
	factory.setDAO(dao);
	dao.setOpId(optionid);

	try {

		result = factory.find(new BbsOptionsVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}	
//以下是王苏的handler	
/**
 * 
 * @param whereClause
 * @return
 */
public List getReplyListByClause(String whereClause) {

	List result = new ArrayList();

	try {
		DAOFactory factory = new DAOFactory(conn);
		BbsReplyDAO replydao = new BbsReplyDAO();
		factory.setDAO(replydao);
		replydao.setWhereClause(whereClause);
		replydao.addOrderBy("reTime",false);
		result = factory.find(new BbsReplyVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
/**
 * 
 * @param MainID
 * @return
 */
public BbsMainarticleVO findBbsMainarticleByID(String MainID) {
	try {
		DAOFactory factory = new DAOFactory(conn);

		BbsMainarticleDAO maindao = new BbsMainarticleDAO();
		maindao.setMainid(new Integer(MainID));
		factory.setDAO(maindao);
		
		BbsMainarticleVO mainvo = (BbsMainarticleVO) factory.findByPrimaryKey(new BbsMainarticleVO());
		return mainvo;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
/**
 * 
 * @param whereClause
 * @return
 */
public List getOptionsListByClause(String whereClause) {

	List result = new ArrayList();

	try {
		DAOFactory factory = new DAOFactory(conn);
		BbsOptionsDAO optdao = new BbsOptionsDAO();
		optdao.setWhereClause(whereClause);
		optdao.addOrderBy("opOrder",true);
		factory.setDAO(optdao);			
		result = factory.find(new BbsOptionsVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
public List getUserListByName(String name,String	mainId) {

	List result = new ArrayList();

	try {
		DAOFactory factory = new DAOFactory(conn);
		BbsVoteuserDAO userdao = new BbsVoteuserDAO();
		userdao.setVuLoginname(name);
		userdao.setVuMainid(mainId);
		factory.setDAO(userdao);			
		result = factory.find(new BbsVoteuserVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}	


/**
 * 
 * @param vo
 */
public void addReply(BbsReplyVO vo){
	BbsReplyDAO dao = new BbsReplyDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
	} catch (DAOException e) {
		e.printStackTrace();
	}
}
/**
 * 
 * @param vo
 * @return Integer
 */
public Integer addUser(BbsVoteuserVO vo){
	BbsVoteuserDAO dao = new BbsVoteuserDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return dao.getVuId();
}
/**
 * 
 * @param VUID
 * @return
 */
public BbsVoteuserVO findBbsVoteuserByID(String VUID) {
	try {
		DAOFactory factory = new DAOFactory(conn);

		BbsVoteuserDAO uvdao = new BbsVoteuserDAO();
		uvdao.setVuId(new Integer(VUID));
		factory.setDAO(uvdao);

		BbsVoteuserVO uvvo = (BbsVoteuserVO) factory.findByPrimaryKey(new BbsVoteuserVO());
		return uvvo;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

/**
 * 
 * @param vo
 */
public void addVote(BbsVoteVO vo){
	BbsVoteDAO dao = new BbsVoteDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
	} catch (DAOException e) {
		e.printStackTrace();
	}
}
/**
 * 
 * @param ipAddress
 * @return
 */
public List getVoteVOList(String ipAddress,String mainId) {
	BbsVoteDAO dao = new BbsVoteDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setVtVoterip(ipAddress);
	dao.setMainid(new Integer(mainId));
	dao.addOrderBy("vtVotetime",false);
	factory.setDAO(dao);

	List list = null;
	try {
		list = factory.find(new BbsVoteVO());
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

/**
 * 
 * @param repid
 * @return
 */
public BbsReplyVO getReplyVO(String repid) {
	BbsReplyVO vo=new BbsReplyVO();
	BbsReplyDAO dao = new BbsReplyDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setReId(new Integer(repid));
	factory.setDAO(dao);

	try {
		vo=(BbsReplyVO) factory.findByPrimaryKey(new BbsReplyVO());
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return vo;
}	
/**
 * 
 * @param vo
 */
public void updateReply(BbsReplyVO vo) {
	BbsReplyDAO dao = new BbsReplyDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
//		System.out.println("++++++++++++++修改记录bbsvotehandler title="+vo.getOpTitle()+"and opid="+vo.getOpId());
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}

}
/**
 * 
 * @param whereClause
 * @return
 */
public List getMainListByClause(String whereClause) {

	List result = new ArrayList();

	try {
		DAOFactory factory = new DAOFactory(conn);
		BbsMainarticleDAO maindao = new BbsMainarticleDAO();
		factory.setDAO(maindao);
		maindao.setWhereClause(whereClause);
		maindao.addOrderBy("mainid",true);
		result = factory.find(new BbsMainarticleVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
/**
 * 
 * @param whereClause
 * @return
 */
public List getMainListOnPop(String whereClause) {

	List result = new ArrayList();
	DAOFactory factory = new DAOFactory(conn);
	BbsMainarticleDAO maindao = new BbsMainarticleDAO();
	
	factory.setDAO(maindao);
	maindao.setStatus("开放");
	//maindao.setMOrder("4");
	//maindao.setStatus("4");

	//maindao.setStatus("开放");
	//maindao.setStatus("4");
	//maindao.setWhereClause(whereClause);
	maindao.setWhereClause("  'M_ORDER'  not like '4'");
	maindao.addOrderBy("mainid",true);
	
	try {
		
		result = factory.find(new BbsMainarticleVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

/**
 * 根据optionid进行多选方式的按性别统计的数值
 * @param opId
 * @return
 */
public String[][] getMultiStatBySexVO(Integer opId) {
	String[][] statnum=new String[4][4];
	for (int i=0 ;i<4;i++){
		for (int j=0;j<4;j++){
			statnum[i][j]="0";
		}
	}
	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = "select vt_multinum,vu_sex, count(vt_multinum) as statnum  from bbs_vote "+
	
				"left join bbs_options  on bbs_options.op_id=bbs_vote.vt_opid "+
				"left join bbs_voteuser on bbs_voteuser.vu_id=vt_vuid where bbs_vote.vt_opid="+opId+
				" group by vt_multinum,vu_sex  ";
			
	System.out.println("BbsStatOptionsVO   sql========================  "+sql); 

	Statement stmt = null;

	ResultSet rs=null;
	try {
		stmt = conn.createStatement();
		rs =stmt.executeQuery(sql);
		while(rs.next()){
			System.out.println("BbsStatOptionsVO   sql2============vt_multinum===========  "+rs.getString("vt_multinum")); 

			if("1".equals(rs.getString("vt_multinum").toString())){
				System.out.println("BbsStatOptionsVO   sql11111============vu_sex===========  "+rs.getString("vu_sex"));
				if("男".equals(rs.getString("vu_sex"))){
					statnum[1][1]=rs.getString("statnum");
					System.out.println("+++statnum[1][1]=++++"+rs.getString("statnum"));
				}else if ("女".equals(rs.getString("vu_sex"))){
					statnum[1][2]=rs.getString("statnum");
				}else{
					statnum[1][3]=rs.getString("statnum");
				}
			}else if("2".equals(rs.getString("vt_multinum").toString())){
				System.out.println("BbsStatOptionsVO   sql22222============vu_sex===========  "+rs.getString("vu_sex"));
				if("男".equals(rs.getString("vu_sex"))){
					statnum[2][1]=rs.getString("statnum");
				}else if ("女".equals(rs.getString("vu_sex"))){
					statnum[2][2]=rs.getString("statnum");
				}else{
					statnum[2][3]=rs.getString("statnum");
				}
			}
			else if("3".equals(rs.getString("vt_multinum").toString())){
				System.out.println("BbsStatOptionsVO   sql33333============vu_sex===========  "+rs.getString("vu_sex"));
				if("男".equals(rs.getString("vu_sex"))){
					statnum[3][1]=rs.getString("statnum");
				}else if ("女".equals(rs.getString("vu_sex"))){
					statnum[3][2]=rs.getString("statnum");
				}else{
					statnum[3][3]=rs.getString("statnum");
				}
			}
		}
		
		return statnum;
	} catch (SQLException e) {
		e.printStackTrace();
		
	} finally {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
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
	return statnum;
}
/**
 * 根据optionid进行多选方式的按性别统计的数值
 * @param opId
 * @return
 */
public String[][] getSingleStatBySexVO(Integer opId) {
	String[][] statnum=new String[4][4];
	for (int i=0 ;i<4;i++){
		for (int j=0;j<4;j++){
			statnum[i][j]="0";
		}
	}
	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = "select vu_sex, count(vt_singlenum) as statnum  from bbs_vote "+
	
				"left join bbs_options  on bbs_options.op_id=bbs_vote.vt_opid "+
				"left join bbs_voteuser on bbs_voteuser.vu_id=vt_vuid where bbs_vote.vt_opid="+opId+
				" group by vu_sex  ";
			
	System.out.println("getSingleStatBySexVO   sql========================  "+sql); 

	Statement stmt = null;

	ResultSet rs=null;
	try {
		stmt = conn.createStatement();
		rs =stmt.executeQuery(sql);
		while(rs.next()){
			System.out.println("getSingleStatBySexVO   ============vu_sex===========  "+rs.getString("vu_sex"));
			if("男".equals(rs.getString("vu_sex"))){
					statnum[0][1]=rs.getString("statnum");
					
			}else if ("女".equals(rs.getString("vu_sex"))){
					statnum[0][2]=rs.getString("statnum");
			}else{
					statnum[0][3]=rs.getString("statnum");
			}
			
		}
		
		return statnum;
	} catch (SQLException e) {
		e.printStackTrace();
		
	} finally {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
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
	return statnum;
}

/**
 * 根据optionid进行多选方式的按性别统计的数值
 * @param opId
 * @return
 */
public String[][] getMultiStatByPostVO(Integer opId) {
	String[][] statnum=new String[4][8];
	for (int i=0 ;i<4;i++){
		for (int j=0;j<8;j++){
			statnum[i][j]="0";
		}
	}
	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = "select vt_multinum,vu_post, count(vt_multinum) as statnum  from bbs_vote "+
	
				"left join bbs_options  on bbs_options.op_id=bbs_vote.vt_opid "+
				"left join bbs_voteuser on bbs_voteuser.vu_id=vt_vuid where bbs_vote.vt_opid="+opId+
				" group by vt_multinum,vu_post  ";
			
	System.out.println("getMultiStatByPostVO   sql========================  "+sql); 

	Statement stmt = null;

	ResultSet rs=null;
	try {
		stmt = conn.createStatement();
		rs =stmt.executeQuery(sql);
		while(rs.next()){
			System.out.println("getMultiStatByPostVO   sql2============vt_multinum===========  "+rs.getString("vt_multinum")); 

			if("1".equals(rs.getString("vt_multinum").toString())){
				System.out.println("getMultiStatByPostVO   sql11111============vu_post===========  "+rs.getString("vu_post"));
				if("管理人员".equals(rs.getString("vu_post"))){
					statnum[1][1]=rs.getString("statnum");
					
				}else if ("营销人员".equals(rs.getString("vu_post"))){
					statnum[1][2]=rs.getString("statnum");
				}else if ("技术人员".equals(rs.getString("vu_post"))){
					statnum[1][3]=rs.getString("statnum");
				}else if ("记者".equals(rs.getString("vu_post"))){
					statnum[1][4]=rs.getString("statnum");
				}else if ("编辑".equals(rs.getString("vu_post"))){
					statnum[1][5]=rs.getString("statnum");
				}else if ("后勤服务人员".equals(rs.getString("vu_post"))){
					statnum[1][6]=rs.getString("statnum");
				
				}else{
					statnum[1][7]=rs.getString("statnum");
				}
			}else if("2".equals(rs.getString("vt_multinum").toString())){
				System.out.println("getMultiStatByPostVO   sql222222============vu_post===========  "+rs.getString("vu_post"));
				if("管理人员".equals(rs.getString("vu_post"))){
					statnum[2][1]=rs.getString("statnum");
					
				}else if ("营销人员".equals(rs.getString("vu_post"))){
					statnum[2][2]=rs.getString("statnum");
				}else if ("技术人员".equals(rs.getString("vu_post"))){
					statnum[2][3]=rs.getString("statnum");
				}else if ("记者".equals(rs.getString("vu_post"))){
					statnum[2][4]=rs.getString("statnum");
				}else if ("编辑".equals(rs.getString("vu_post"))){
					statnum[2][5]=rs.getString("statnum");
				}else if ("后勤服务人员".equals(rs.getString("vu_post"))){
					statnum[2][6]=rs.getString("statnum");
				
				}else{
					statnum[2][7]=rs.getString("statnum");
				}
			}
			else if("3".equals(rs.getString("vt_multinum").toString())){
				System.out.println("getMultiStatByPostVO   sql3333333============vu_post===========  "+rs.getString("vu_post"));
				if("管理人员".equals(rs.getString("vu_post"))){
					statnum[3][1]=rs.getString("statnum");
					
				}else if ("营销人员".equals(rs.getString("vu_post"))){
					statnum[3][2]=rs.getString("statnum");
				}else if ("技术人员".equals(rs.getString("vu_post"))){
					statnum[3][3]=rs.getString("statnum");
				}else if ("记者".equals(rs.getString("vu_post"))){
					statnum[3][4]=rs.getString("statnum");
				}else if ("编辑".equals(rs.getString("vu_post"))){
					statnum[3][5]=rs.getString("statnum");
				}else if ("其它".equals(rs.getString("vu_post"))){
					statnum[3][6]=rs.getString("statnum");
				
				}else{
					statnum[3][7]=rs.getString("statnum");
				}
			}
		}
		
		return statnum;
	} catch (SQLException e) {
		e.printStackTrace();
		
	} finally {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
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
	return statnum;
}
/**
 * 根据optionid进行多选方式的按性别统计的数值
 * @param opId
 * @return
 */
public String[][] getSingleStatByPostVO(Integer opId) {
	String[][] statnum=new String[4][8];
	for (int i=0 ;i<4;i++){
		for (int j=0;j<8;j++){
			statnum[i][j]="0";
		}
	}
	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = "select vu_post, count(vt_singlenum) as statnum  from bbs_vote "+
	
				"left join bbs_options  on bbs_options.op_id=bbs_vote.vt_opid "+
				"left join bbs_voteuser on bbs_voteuser.vu_id=vt_vuid where bbs_vote.vt_opid="+opId+
				" group by vu_post  ";
			
	System.out.println("getSingleStatByPostVO   sql========================  "+sql); 

	Statement stmt = null;

	ResultSet rs=null;
	try {
		stmt = conn.createStatement();
		rs =stmt.executeQuery(sql);
		while(rs.next()){
			System.out.println("getSingleStatBySexVO   ============vu_post===========  "+rs.getString("vu_post"));
			if("管理人员".equals(rs.getString("vu_post"))){
				statnum[0][1]=rs.getString("statnum");
				
			}else if ("营销人员".equals(rs.getString("vu_post"))){
				statnum[0][2]=rs.getString("statnum");
			}else if ("技术人员".equals(rs.getString("vu_post"))){
				statnum[0][3]=rs.getString("statnum");
			}else if ("记者".equals(rs.getString("vu_post"))){
				statnum[0][4]=rs.getString("statnum");
			}else if ("编辑".equals(rs.getString("vu_post"))){
				statnum[0][5]=rs.getString("statnum");
			}else if ("其它".equals(rs.getString("vu_post"))){
				statnum[0][6]=rs.getString("statnum");
			
			}else{
				statnum[0][7]=rs.getString("statnum");
			}
			
		}
		
		return statnum;
	} catch (SQLException e) {
		e.printStackTrace();
		
	} finally {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
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
	return statnum;
}

/**
 * 根据ＩＰ取得此用户的List
 * @param remoteAddr
 * @return
 */
public List getIpUserVo(String remoteAddr,String	mainid) {

	List result = new ArrayList();

	try {
		DAOFactory factory = new DAOFactory(conn);
		BbsVoteuserDAO dao = new BbsVoteuserDAO();
		factory.setDAO(dao);
		dao.setWhereClause(" VU_IP='"+remoteAddr+"' and VU_MAINID ='"+mainid+"'");
		
		result = factory.find(new BbsVoteuserVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

/**
 * 判断用户表中是否拥有此ＩＰ的用户
 * @param remoteAddr
 * @return
 */
public boolean hasIpUser(String remoteAddr,String	 mainid) {
	List result = new ArrayList();

	try {
		DAOFactory factory = new DAOFactory(conn);
		BbsVoteuserDAO userdao = new BbsVoteuserDAO();
		userdao.setVuIp(remoteAddr);
		userdao.setVuMainid(mainid.toString());
		factory.setDAO(userdao);			
		result = factory.find(new BbsVoteuserVO());
		if(result.size()>0){
			return true;
		}		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}

/**
 * 根据用户ID及主题ID查看是否投过票
 * @param userId
 * @param string
 * @return
 */
public List getUserIdVoteVOList(Integer userId, String mainId) {
	BbsVoteDAO dao = new BbsVoteDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setVtVuid(userId.toString());
	dao.setMainid(new Integer(mainId));
	dao.addOrderBy("vtVotetime",false);
	factory.setDAO(dao);

	List list = null;
	try {
		list = factory.find(new BbsVoteVO());
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

/**
 * 根据用户UUID及主题ID取得投票列表
 * @param uuid
 * @param mainId
 * @return
 */
public List getUUIDUserVoteList(String uuid, String mainId) {
	BbsVoteDAO dao = new BbsVoteDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setVtVoteruuid(uuid);
	dao.setMainid(new Integer(mainId));
	dao.addOrderBy("vtVotetime",false);
	factory.setDAO(dao);

	List list = null;
	try {
		list = factory.find(new BbsVoteVO());
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}
/**
 * 根据用户UUID及主题ID取得是否有此用户信息
 * @param remoteAddr
 * @param mainId
 * @return
 */

public boolean hasUUIDUser(String uuid, String mainId) {
	List result = new ArrayList();

	try {
		DAOFactory factory = new DAOFactory(conn);
		BbsVoteuserDAO userdao = new BbsVoteuserDAO();
		userdao.setVuUuid(uuid);
		userdao.setVuMainid(mainId.toString());
		factory.setDAO(userdao);			
		result = factory.find(new BbsVoteuserVO());
		if(result.size()>0){
			return true;
		}		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}
/**
 * 根据UUID及主题ID取得此UUID的用户信息
 * @param uuid
 * @param mainId
 * @return
 */

public List getUUIDUserList(String uuid, String mainId) {

	List result = new ArrayList();

	try {
		DAOFactory factory = new DAOFactory(conn);
		BbsVoteuserDAO dao = new BbsVoteuserDAO();
		factory.setDAO(dao);
		dao.setWhereClause(" VU_UUID='"+uuid+"' and VU_MAINID ='"+mainId+"'");
		
		result = factory.find(new BbsVoteuserVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

/**
 * 得到结果显示列表
 * @param optionid
 * @return
 */
public List getVoteOptionScoreList(Integer optionid) {

	List result = new ArrayList();

	try {
		DAOFactory factory = new DAOFactory(conn);
		BbsVoteDAO dao = new BbsVoteDAO();
		dao.setVtOpid(optionid);
		factory.setDAO(dao);
		//dao.setWhereClause(" VU_OPID='"+optionid+"' ");
		
		result = factory.find(new BbsVoteVO());
		return result;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

}
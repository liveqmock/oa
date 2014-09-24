/*
 * 创建日期 2007-6-22
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.fo.handler;

import java.io.FileInputStream;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;



import com.icss.oa.fo.admin.DbTrans;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.fo.dao.FoPlanDAO;
import com.icss.oa.fo.dao.FoVotepersonDAO;
import com.icss.oa.fo.vo.*;
import com.icss.oa.fo.dao.FoArticalDAO;
import com.icss.j2ee.util.Globals;



/**
 * @author liupei
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class FoLiuHandler {
	
	DbTrans myDbTrans;
	
	Connection conn = null;
	public Sheet sheetimp = null;
	public String datafile = ""; //数据文件名称
	public Workbook rwb = null;
	public int intRowAccount = 0; //待导入的数据条数
	public String erromessage = "";
	public boolean booInsertFlag = true;
	public boolean booEndFlag = false;
	
	/**
	 * 
	 * 构造函数
	 * @param _conn
	 */
	public FoLiuHandler(Connection _conn) {
		this.conn = _conn;
	}
	
	
/**
 * 在管理页面中取得计划列表
 * @return
 */
	public List getPlanManagerList() {
		FoPlanDAO dao = new FoPlanDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.addOrderBy("planPlanid",true);
		factory.setDAO(dao);
		
		List list = null;
		try {
			list = factory.find(new FoPlanVO());
			System.out.println("handler已经取得list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 根据计划ID判断是否拥有文章选项信息
	 * @param planPlanid
	 * @return
	 */
	public boolean hasArticleOptions(Integer planPlanid) {
//		System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT count(ART_ID) AS NUM FROM  FO_ARTICAL WHERE PLAN_PLANID = "
				+ planPlanid.toString();
//		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
		boolean hasarticleoption=false;
		int num=0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
			if (rs != null) {
				if (rs.next()) {
					num = rs.getInt("num");//得到文章的个数
					if(num>0){
						hasarticleoption=true;
					}
//					System.out.println("eeee3333333     "+blob.length()); 
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
		
		return hasarticleoption;

	}
	
	
/**
 * 增加文章
 * @param FoPlanVo
 */
public void addPlan(FoPlanVO vo) {
	FoPlanDAO dao = new FoPlanDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * 根据计划ID删除相关文章选项记录
 * @param planPlanid
 */
public void deleteArtical(Integer planPlanid) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  FO_ARTICAL WHERE PLAN_PLANID = "
			+ planPlanid.toString();
	System.out.println("deleteArticleOptions     "+sql); 

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
 * 根据计划ID删除计划
 * @param planPlanid
 */
public void deletePlan(Integer planPlanid) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  FO_PLAN WHERE PLAN_PLANID = "
			+ planPlanid.toString();
	System.out.println("deletePlan     "+sql); 

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
 * 修改计划记录信息
 * @param foplanvo
 */
public void updatePlan(FoPlanVO foplanvo) {
	FoPlanDAO dao = new FoPlanDAO();
	dao.setValueObject(foplanvo);
	dao.setConnection(conn);
	try {
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * 根据planPlanid取得FoPlanVO
 * @param planPlanid
 * @return
 */
public FoPlanVO getPlanVo(Integer planPlanid) {
	// TODO Auto-generated method stub
	FoPlanVO vo=new FoPlanVO();
	FoPlanDAO dao = new FoPlanDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setPlanPlanid(planPlanid);
	factory.setDAO(dao);
	try {
		vo=(FoPlanVO) factory.findByPrimaryKey(new FoPlanVO());
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return vo;
}

/**
 * 在管理页面中取得文章选项的列表
 * @param planPlanid
 * @return
 */
public List getarticalOptionList(Integer planPlanid) {
	FoArticalDAO dao = new FoArticalDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setPlanPlanid(planPlanid);
	dao.addOrderBy("artId",true);//按照文章的ID号排序
	factory.setDAO(dao);
	
	List list = null;
	try {
		list = factory.find(new FoArticalVO());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

/**
 * 根据artId取得FoArtialVO
 * @param artId
 * @return
 */
public FoArticalVO getArticleVo(Integer artId) {
	// TODO Auto-generated method stub
	FoArticalVO vo=new FoArticalVO();
	FoArticalDAO dao = new FoArticalDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setArtId(artId);
	factory.setDAO(dao);
	try {
		vo=(FoArticalVO) factory.findByPrimaryKey(new FoArticalVO());
	} catch (DAOException e) {
		e.printStackTrace();
	}
	return vo;
}

/**
 * 修改文章记录信息
 * @param foplanvo
 */
public void updateArticle(FoArticalVO foartvo) {
	FoArticalDAO dao = new FoArticalDAO();
	dao.setValueObject(foartvo);
	dao.setConnection(conn);
	try {
		dao.update();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * 增加文章
 * @param FoArticalVO
 */
public void addArticle(FoArticalVO vo) {
	FoArticalDAO dao = new FoArticalDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
		System.out.println("添加完毕");
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * 根据文章ID删除文章
 * @param planPlanid
 */
public void deleteArticleByArtid(Integer artId) {

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(
				com.icss.j2ee.util.Globals.DATASOURCEJNDI);
	} catch (DBConnectionLocatorException e1) {
		e1.printStackTrace();
	}
	
	String sql = " Delete FROM  FO_ARTICAL WHERE ART_ID = "
			+ artId.toString();
	System.out.println("deleteArticle     "+sql); 

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
 * 设置文件
 * @param filename
 */
private void setDataFile(String filename) {
	//datafile = filename + ".xls";
	datafile=filename;
	System.out.println("datafile" + datafile);
	
}

/**
 * 创建workbook对象
 * 
 */
public String initDataFile() {
	try {
		//构建Workbook对象, 只读Workbook对象
		//直接从本地文件创建Workbook
		//从输入流创建Workbook
		InputStream is = new FileInputStream(datafile);
		System.out.println("读取文件");
		rwb = Workbook.getWorkbook(is);
		sheetimp = rwb.getSheet(0);//sheetimp对象用来保存excel文件中的内容
		this.intRowAccount = sheetimp.getRows();
		System.out.println("数据文件 intRowAccount=" + intRowAccount);
	} catch (Exception e) {
		erromessage += "初始化ｅｘｃｌｅ文件错:" + e.getMessage();
		e.printStackTrace();
	}
	return this.erromessage;
}





/**
 * 把excel文件中的文章数据导入数据库中
 * @param file_name,planid
 */
public FoArticalVO[] dataImport2(String file_name, Integer planPlanid)
throws SQLException {
	

	System.out.println("进入dataimport2方法,planid="+planPlanid.toString());
	setDataFile(file_name);

	initDataFile();
	System.out.println("初始化数据文件完毕");
	int intBegin = 1;
	
	this.booInsertFlag = false;
	
	//创建一个FoArticalVo,excel文件的每一行对应一个FoArticalVO
	FoArticalVO[] foartvoarray = new FoArticalVO [intRowAccount-1];
	System.out.println("VO的个数为"+foartvoarray.length);
	
try{//解析文件
	System.out.println("开始解析文件");
	for (int i = intBegin; i < intRowAccount; i++) {
		if (booEndFlag) {
			break;
		} else {
			
			System.out.println("第"+i+"行");
			//利用getCell方法从sheetimp中取数据，第一个参数为列的index，第二个参数为行的index
			String artTotalnum=sheetimp.getCell(0, i).getContents()==null?"-1":sheetimp.getCell(0, i).getContents();
			String artTypeid=sheetimp.getCell(1, i).getContents()==null?"-1":sheetimp.getCell(1, i).getContents();
			String artTypename=sheetimp.getCell(2, i).getContents()==null?"-1":sheetimp.getCell(2, i).getContents();
			String artTitle=sheetimp.getCell(3, i).getContents()==null?"-1":sheetimp.getCell(3, i).getContents();
			String artOutvotenum=sheetimp.getCell(4, i).getContents()==null?"-1":sheetimp.getCell(4, i).getContents();
			String artWordnum=sheetimp.getCell(5, i).getContents()==null?"-1":sheetimp.getCell(5, i).getContents();
			String artPubtime=sheetimp.getCell(6, i).getContents()==null?"-1":sheetimp.getCell(6, i).getContents();
			String artIstoolong=sheetimp.getCell(7, i).getContents()==null?"-1":sheetimp.getCell(7, i).getContents();
			String artIsconsult=sheetimp.getCell(8, i).getContents()==null?"-1":sheetimp.getCell(8, i).getContents();
			String artUsenum=sheetimp.getCell(9, i).getContents()==null?"-1":sheetimp.getCell(9, i).getContents();
			String artStatnum=sheetimp.getCell(10, i).getContents()==null?"-1":sheetimp.getCell(10, i).getContents();
			String artWriter=sheetimp.getCell(11, i).getContents()==null?"-1":sheetimp.getCell(11, i).getContents();
			String artContent=sheetimp.getCell(12, i).getContents()==null?"-1":sheetimp.getCell(12, i).getContents();
			
	
			
			System.out.println("已经从表单中获得文章信息");
			System.out.println("artTitle="+artTitle);
			System.out.println("artTotalnum"+artTotalnum);
			System.out.println("artTypename"+artTypename);

			//if(sheetimp.getCell(0, i+1).getContents()==null||sheetimp.getCell(0, i+1).getContents().equals(""))
			//this.booEndFlag=true;

			//myDbTrans.executeUpdate(sqlInsert);
			
			//向VO里赋值
			System.out.println("向VO里赋值");
			foartvoarray[i-1]=new FoArticalVO();
			System.out.println("对VO进行初始化完毕");	
			
			foartvoarray[i-1].setPlanPlanid(planPlanid);
			System.out.println("planid="+foartvoarray[i-1].getPlanPlanid());
			
			foartvoarray[i-1].setArtTotalnum(artTotalnum);
			foartvoarray[i-1].setArtTypeid(artTypeid);
			foartvoarray[i-1].setArtTypename(artTypename);
			foartvoarray[i-1].setArtTitle(artTitle);
			foartvoarray[i-1].setArtOutvotenum(artOutvotenum);
			foartvoarray[i-1].setArtWordnum(artWordnum);
			foartvoarray[i-1].setArtPubtime(artPubtime);
			foartvoarray[i-1].setArtIstoolong(artIstoolong);
			foartvoarray[i-1].setArtIsconsult(artIsconsult);
			foartvoarray[i-1].setArtUsenum(artUsenum);
			foartvoarray[i-1].setArtStatnum(artStatnum);
			foartvoarray[i-1].setArtWriter(artWriter);
			foartvoarray[i-1].setArtContent(artContent);
			
			
			System.out.println("title"+(i-1)+"="+foartvoarray[i-1].getArtTitle());
			System.out.println("content"+(i-1)+"="+foartvoarray[i-1].getArtContent());
			System.out.println("此时i="+i);
			
		}
	}
 return foartvoarray;	
}catch(Exception e){
	e.printStackTrace();return null;
}

}

/**
 * 取得用户列表
 * @return
 */
	public List getUserManagerList(String sql) {
		FoVotepersonDAO dao = new FoVotepersonDAO();
		DAOFactory factory = new DAOFactory(conn);
		
		dao.setWhereClause(sql.toString());
		dao.addOrderBy("name", true);
		//dao.addOrderBy("logTime",false);
		factory.setDAO(dao);
		
		List list = null;
		try {
			list = factory.find(new FoVotepersonVO());
			System.out.println("handler已经取得personlist");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据personId取得FoVotepersonVO
	 * @param artId
	 * @return
	 */
	public FoVotepersonVO getVotepersonVo(Integer personId) {
		// TODO Auto-generated method stub
		FoVotepersonVO vo=new FoVotepersonVO();
		FoVotepersonDAO dao = new FoVotepersonDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setPersonId(personId);		
		factory.setDAO(dao);
		try {
			vo=(FoVotepersonVO) factory.findByPrimaryKey(new FoVotepersonVO());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * 修改用户记录信息
	 * @param fopersonvo
	 */
	public void updatePerson(FoVotepersonVO fopersonvo) {
		FoVotepersonDAO dao = new FoVotepersonDAO();
		dao.setValueObject(fopersonvo);
		dao.setConnection(conn);
		try {
			dao.update();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 根据用户ID和计划ID更新用户是否投过票
	 * @param personId,planId
	 */
	public void updateUserVote(Integer personId,Integer planId,String flag) {
		String sql=null;
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		if("1".equals(flag))//如果该用户以前已投票，则更改为未投票
		{ sql = " update fo_voteperson set flag='0' where plan_planid="
				+ planId.toString()+" and person_id="+personId.toString();
		}else{//如果该用户以前未投票，则更改为已投票
			sql = " update fo_voteperson set flag='1' where plan_planid="
				+ planId.toString()+" and person_id="+personId.toString();
		}
		
		System.out.println("updatePerson: "+sql); 

		Statement stmt = null;

		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("完成更新");
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
	
}
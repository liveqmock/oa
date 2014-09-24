/*
 * �������� 2007-6-22
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class FoLiuHandler {
	
	DbTrans myDbTrans;
	
	Connection conn = null;
	public Sheet sheetimp = null;
	public String datafile = ""; //�����ļ�����
	public Workbook rwb = null;
	public int intRowAccount = 0; //���������������
	public String erromessage = "";
	public boolean booInsertFlag = true;
	public boolean booEndFlag = false;
	
	/**
	 * 
	 * ���캯��
	 * @param _conn
	 */
	public FoLiuHandler(Connection _conn) {
		this.conn = _conn;
	}
	
	
/**
 * �ڹ���ҳ����ȡ�üƻ��б�
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
			System.out.println("handler�Ѿ�ȡ��list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * ���ݼƻ�ID�ж��Ƿ�ӵ������ѡ����Ϣ
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
					num = rs.getInt("num");//�õ����µĸ���
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
 * ��������
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
 * ���ݼƻ�IDɾ���������ѡ���¼
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
 * ���ݼƻ�IDɾ���ƻ�
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
 * �޸ļƻ���¼��Ϣ
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
 * ����planPlanidȡ��FoPlanVO
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
 * �ڹ���ҳ����ȡ������ѡ����б�
 * @param planPlanid
 * @return
 */
public List getarticalOptionList(Integer planPlanid) {
	FoArticalDAO dao = new FoArticalDAO();
	DAOFactory factory = new DAOFactory(conn);
	dao.setPlanPlanid(planPlanid);
	dao.addOrderBy("artId",true);//�������µ�ID������
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
 * ����artIdȡ��FoArtialVO
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
 * �޸����¼�¼��Ϣ
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
 * ��������
 * @param FoArticalVO
 */
public void addArticle(FoArticalVO vo) {
	FoArticalDAO dao = new FoArticalDAO();
	dao.setValueObject(vo);
	dao.setConnection(conn);
	try {
		dao.create();
		System.out.println("������");
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
}

/**
 * ��������IDɾ������
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
 * �����ļ�
 * @param filename
 */
private void setDataFile(String filename) {
	//datafile = filename + ".xls";
	datafile=filename;
	System.out.println("datafile" + datafile);
	
}

/**
 * ����workbook����
 * 
 */
public String initDataFile() {
	try {
		//����Workbook����, ֻ��Workbook����
		//ֱ�Ӵӱ����ļ�����Workbook
		//������������Workbook
		InputStream is = new FileInputStream(datafile);
		System.out.println("��ȡ�ļ�");
		rwb = Workbook.getWorkbook(is);
		sheetimp = rwb.getSheet(0);//sheetimp������������excel�ļ��е�����
		this.intRowAccount = sheetimp.getRows();
		System.out.println("�����ļ� intRowAccount=" + intRowAccount);
	} catch (Exception e) {
		erromessage += "��ʼ����������ļ���:" + e.getMessage();
		e.printStackTrace();
	}
	return this.erromessage;
}





/**
 * ��excel�ļ��е��������ݵ������ݿ���
 * @param file_name,planid
 */
public FoArticalVO[] dataImport2(String file_name, Integer planPlanid)
throws SQLException {
	

	System.out.println("����dataimport2����,planid="+planPlanid.toString());
	setDataFile(file_name);

	initDataFile();
	System.out.println("��ʼ�������ļ����");
	int intBegin = 1;
	
	this.booInsertFlag = false;
	
	//����һ��FoArticalVo,excel�ļ���ÿһ�ж�Ӧһ��FoArticalVO
	FoArticalVO[] foartvoarray = new FoArticalVO [intRowAccount-1];
	System.out.println("VO�ĸ���Ϊ"+foartvoarray.length);
	
try{//�����ļ�
	System.out.println("��ʼ�����ļ�");
	for (int i = intBegin; i < intRowAccount; i++) {
		if (booEndFlag) {
			break;
		} else {
			
			System.out.println("��"+i+"��");
			//����getCell������sheetimp��ȡ���ݣ���һ������Ϊ�е�index���ڶ�������Ϊ�е�index
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
			
	
			
			System.out.println("�Ѿ��ӱ��л��������Ϣ");
			System.out.println("artTitle="+artTitle);
			System.out.println("artTotalnum"+artTotalnum);
			System.out.println("artTypename"+artTypename);

			//if(sheetimp.getCell(0, i+1).getContents()==null||sheetimp.getCell(0, i+1).getContents().equals(""))
			//this.booEndFlag=true;

			//myDbTrans.executeUpdate(sqlInsert);
			
			//��VO�︳ֵ
			System.out.println("��VO�︳ֵ");
			foartvoarray[i-1]=new FoArticalVO();
			System.out.println("��VO���г�ʼ�����");	
			
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
			System.out.println("��ʱi="+i);
			
		}
	}
 return foartvoarray;	
}catch(Exception e){
	e.printStackTrace();return null;
}

}

/**
 * ȡ���û��б�
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
			System.out.println("handler�Ѿ�ȡ��personlist");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ����personIdȡ��FoVotepersonVO
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
	 * �޸��û���¼��Ϣ
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
	 * �����û�ID�ͼƻ�ID�����û��Ƿ�Ͷ��Ʊ
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
		
		if("1".equals(flag))//������û���ǰ��ͶƱ�������ΪδͶƱ
		{ sql = " update fo_voteperson set flag='0' where plan_planid="
				+ planId.toString()+" and person_id="+personId.toString();
		}else{//������û���ǰδͶƱ�������Ϊ��ͶƱ
			sql = " update fo_voteperson set flag='1' where plan_planid="
				+ planId.toString()+" and person_id="+personId.toString();
		}
		
		System.out.println("updatePerson: "+sql); 

		Statement stmt = null;

		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("��ɸ���");
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
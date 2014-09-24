package com.icss.oa.useraddress.handler;      

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.oa.useraddress.dao.OaaddListDAO;
import com.icss.oa.useraddress.dao.OauserInfoDAO;
import com.icss.oa.useraddress.vo.*;

public class UserAddressHandler
{

	private  Connection conn;
	public String filepath =null; 

	public Workbook rwb = null;
	public Sheet sheetimp = null;
	public boolean booInsertFlag = true;
	public boolean booEndFlag = false;
	public String erromessage = "";
	public int intRowAccount = 0; //���������������
	public int intRowIn = 0; //�ɹ����������
	public int intRowFalse = 0; //��������

	
	public String datafile = ""; //�����ļ�����

	public UserAddressHandler(Connection conn)
	{
		this.conn = conn;
	}

	
	/**�����û���Ϣ���ϱ�
	 * @return
	 */
	public  List getUserInfoList() {
		OaaddListDAO dao = new OaaddListDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.addOrderBy("odDept",true);
		
		dao.addOrderBy("odUsrname",true);
		List list = null;
		try {
			list = factory.find(new OaaddListVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**���ظ������������û���Ϣ���ϱ�
	 * @param address
	 * @return
	 */
	public List getUserInfoList1(String ip) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		OaaddListDAO dao = new OaaddListDAO();
		dao.setOdIp(ip);
		factory.setDAO(dao);
		try {
			List list= factory.find(new OaaddListVO());
			return list;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}


	/**
	 * @param ComString
	 * @param sbt
	 * @return
	 */
	public List getUserSearchList(String ComString, StringBuffer sbt) {
		List list = null;
		DAOFactory factory = new DAOFactory(conn);
		OaaddListDAO jDao = new OaaddListDAO();
		if(ComString!=null&&!ComString.equals("")){
			jDao.setWhereClause(ComString);
		}
		jDao.addOrderBy("odDept",true);
		jDao.addOrderBy("odUsrname",true);
		factory.setDAO(jDao);
		try {
			sbt.append(ComString);
			sbt.append("huang=").append(System.currentTimeMillis()).append("��������");
			list=factory.find(new OaaddListVO());
			System.out.println("++++++++++++++++++getusersearchlist");
			sbt.append("huang=").append(System.currentTimeMillis()).append("��������");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**������
	 * @param vo
	 */
	public void add(OaaddListVO vo) throws HandlerException {
		OaaddListDAO dao = new OaaddListDAO();
		dao.setConnection(conn);
		dao.setValueObject(vo);
		try {
			dao.create();
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
		
	}


	/**�޸�
	 * @param vo
	 */
	public void update(OaaddListVO vo) {
		DAOFactory factory = new DAOFactory(conn);
		OaaddListDAO dao = new OaaddListDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setOdId (vo.getOdId());
		try{
			dao = (OaaddListDAO)factory.findByPrimaryKey();
			dao.setValueObject(vo);
			dao.update();
		}
		catch(DAOException e){
			e.printStackTrace();
		}
	 }


	/**ɾ��
	 * @param odid
	 */
	public void delete(Integer odid) {
		DAOFactory factory = new DAOFactory(conn);
		OaaddListDAO dao = new OaaddListDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setOdId(odid);
		try{
			dao.delete();
		}
		catch(DAOException e){
			e.printStackTrace();
		}
	}


	/**
	 * @return
	 */
	public List getUserwordList() throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		OauserInfoDAO dao = new OauserInfoDAO();
		dao.addOrderBy("odWord",true);
		factory.setDAO(dao);
		try {
			List list = factory.find(new OauserInfoVO());
			return list;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * ����Ŀ¼
	 * @param filepath
	 */
	public void CreatDir(String filepath){
		
		// this.filepath = toFilePathManager.getString("toFile_path");
		this.filepath = filepath;
		System.out.println("toFile_path is : "+filepath);
		File file=new File(filepath);
		 if(!file.exists()){
		 	 file.mkdirs();
	  	 }
	 }

	/**
	 * �õ��û����ŵ�list
	 * 
	 * @param personuuid
	 * @return
	 * @throws HandlerException
	 */
	public List getUserDeptList()  {
		List deptlist=new ArrayList();
//		System.out.println("eeee11111111111111111111111     "+personuuid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT distinct OD_DEPT  from OAADD_LIST where OD_DEPT not like '%����%'"  ;
		

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
//			System.out.println("eeee1111111111     "+sql); 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222     "+sql); 
			if (rs != null) {
				while (rs.next()) {
					String cnname = "";
//					System.out.println("eeee3333333     "+cnname); 
					cnname = rs.getString("OD_DEPT");
					System.out.println("eeee444444     "+cnname); 
					if(!"".equals(cnname)){
						deptlist.add(cnname);
					}
					
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
		
		return deptlist;

	}

	/**
	 * �õ��û����ŵ�list
	 * 
	 * @param personuuid
	 * @return
	 * @throws HandlerException
	 */
	public List getUserRegionList()  {
		List deptlist=new ArrayList();
//		System.out.println("eeee11111111111111111111111     "+personuuid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT distinct OD_Region  from OAADD_LIST " ;
		

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
//			System.out.println("eeee1111111111     "+sql); 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222     "+sql); 
			if (rs != null) {
				while (rs.next()) {
					String cnname = "";
//					System.out.println("eeee3333333     "+cnname); 
					cnname = rs.getString("OD_Region");
					if(!"null".equals(cnname)&&!"".equals(cnname)&&cnname!=null){
						deptlist.add(cnname);
					}
					System.out.println("ȡ��¥��     "+cnname); 
					
					
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
		
		return deptlist;

	}

	public void dataImport(String file_name)throws HandlerException {
//		System.out.println("��ʼ�����ļ�");
		setDataFile(file_name);
//		System.out.println("��ʼ��ʼ���ļ���Ϣ");
		initDataFile();
//		System.out.println("��ʼ�����ļ���Ϣ");
		int intBegin = 1;
		this.booInsertFlag = false;

		try {
			for (int i = intBegin; i < intRowAccount; i++) {
				if (booEndFlag) {
					break;
				} else {
//					System.out.println("��ʼװ�ص����ļ���sql���");
					OaaddListVO vo=new OaaddListVO();
					vo.setOdDept(sheetimp.getCell(0, i).getContents());

					vo.setOdOffice(sheetimp.getCell(1, i).getContents());
					vo.setOdUsrname(sheetimp.getCell(2, i).getContents());
					vo.setOdBuss(sheetimp.getCell(3, i).getContents());
					vo.setOdRegion(sheetimp.getCell(4, i).getContents());
					vo.setOdRoomnum(sheetimp.getCell(5, i).getContents());
					vo.setOdRoomnod(sheetimp.getCell(6, i).getContents());
					vo.setOdTel(sheetimp.getCell(7, i).getContents());
					vo.setOdMachid(sheetimp.getCell(8, i).getContents());
					vo.setOdSys(sheetimp.getCell(9, i).getContents());
					vo.setOdMachcircs(sheetimp.getCell(10, i).getContents());
//					vo.setOdWord(sheetimp.getCell(2, i).getContents());
					vo.setOdIp(sheetimp.getCell(11, i).getContents());

					vo.setOdMacnum(sheetimp.getCell(12, i).getContents());
					vo.setOdMachname(sheetimp.getCell(13, i).getContents());
					
					
					vo.setOdOpesys(sheetimp.getCell(14, i).getContents());
					
					vo.setOdMemo(sheetimp.getCell(15, i).getContents());

//					vo.setOdOnflag(sheetimp.getCell(2, i).getContents());
					
//					vo.setOdVirus(sheetimp.getCell(2, i).getContents());
//					vo.setOdOpentime(sheetimp.getCell(2, i).getContents());
					
//					vo.setOdNetcircs(sheetimp.getCell(2, i).getContents());
					
//					vo.setOdNettel(sheetimp.getCell(2, i).getContents());

					this.add(vo);
					
//					System.out.println("��ɸ���Ŀ�ĵ��룡");
				}
			}

			//rs.close();
			//this.close();
			//return rslist;
		}catch (Exception e) {
				throw new HandlerException(e);
			}
		
		
	}
	//�����ļ�
	private void setDataFile(String filename) {
		datafile = filename + ".xls";
		System.out.println("datafile" + datafile);
	}
	//	����workbook����
	public String initDataFile() {
		try {
			//����Workbook����, ֻ��Workbook����
			//ֱ�Ӵӱ����ļ�����Workbook
			//������������Workbook
//			System.out.println("��ʼ��ȡ�ļ�");
			InputStream is = new FileInputStream(datafile);
//			System.out.println("��ȡ�ļ�");
			rwb = Workbook.getWorkbook(is);
			sheetimp = rwb.getSheet(0);
			this.intRowAccount = sheetimp.getRows();
			System.out.println("�����ļ� intRowAccount=" + intRowAccount);
		} catch (Exception e) {
			erromessage += "��ʼ����������ļ���:" + e.getMessage();
			e.printStackTrace();
		}
		return this.erromessage;
	}

}


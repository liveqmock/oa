package com.icss.oa.hr.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.util.Globals;
import com.icss.oa.hr.dao.HRPersonTempDAO;
import com.icss.oa.hr.dao.PhoneInfoDAO;
import com.icss.oa.hr.vo.HRPersonTempVO;
import com.icss.oa.sync.dao.PersonDAO;
import com.icss.oa.sync.vo.PersonVO;
import com.icss.resourceone.common.login.dao.OrgPersonDAO;
import com.icss.resourceone.common.login.model.OrgPersonVO;
import com.icss.resourceone.org.dao.OrgDAO;
import com.icss.resourceone.org.model.OrgVO;
import com.icss.resourceone.common.logininfo.UserInfo;

public class HRPersonHandler {

	private Connection conn;

	public HRPersonHandler() {
	}

	public HRPersonHandler(Connection conn) {
		this.conn = conn;
	}

	public List getAllTempPerson() {
		// TODO Auto-generated method stub
		HRPersonTempDAO dao = new HRPersonTempDAO();
		dao.setFlag(new Integer(0));
		
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		List list = new ArrayList();
		try {

			list = factory.find(new HRPersonTempVO());

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public List getOAPerson() {
		// TODO Auto-generated method stub
		PersonDAO dao = new PersonDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		List list = new ArrayList();
		try {
			list = factory.find(new PersonVO());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public PersonVO initHrid(PersonVO vo) {
		Connection oaconn=null;
		DAOFactory factory = new DAOFactory(conn);

		String deptcode = "";
		String hrid = null;
		String orguuid = "";
		//取得DEPTCODE
		OrgPersonDAO dao1 = new OrgPersonDAO();
		dao1.setPersonuuid(vo.getPersonuuid());
		factory.setDAO(dao1);

		try {
			/*
			List list = factory.find(new OrgPersonVO());
			
			if (!list.isEmpty()) {
				OrgPersonVO vo1 = (OrgPersonVO) list.get(0);	
				orguuid = vo1.getOrguuid();
				
				System.out.println("@@@@@"+orguuid);
				OrgDAO orgdao = new OrgDAO();
				orgdao.setOrguuid(orguuid);
				factory.setDAO(orgdao);
				OrgVO orgvo = (OrgVO)factory.find(new OrgVO()).get(0);
				deptcode = orgvo.getOrgcode();
				System.out.println("@@@@@"+deptcode);
			}
			*/

			//取得HRID
			DAOFactory factory1 = new DAOFactory(conn);

			HRPersonTempDAO dao = new HRPersonTempDAO();
			dao.setUsername(vo.getCnname());
			//dao.setDeptcode(deptcode);
			//System.out.println("#####"+deptcode);
			//System.out.println("#####"+vo.getCnname());

			factory1.setDAO(dao);
			
			//按人员姓名进行检索
			List hrlist = factory1.find(new HRPersonTempVO());
			System.out.println("@@@@@@"+hrlist.toString());
			
			//人员列表不为空,并且人员列表中有且仅有一条记录.
			if(hrlist!=null && hrlist.size()==1){
				HRPersonTempVO tvo = (HRPersonTempVO)hrlist.get(0);

				hrid = tvo.getHrid();

				if (hrid != null) {
					PersonDAO pdao = new PersonDAO();
					pdao.setPersonuuid(vo.getPersonuuid());
					pdao.setHrid(hrid);
					pdao.setConnection(conn);
	
					pdao.update(true);
	
					oaconn = DBConnectionLocator.getInstance()
							.getConnection(Globals.DATASOURCEJNDI);
					
					/*
					 * 该人员所在组织不明,因此无法设置电话簿信息,待更新人员组织时补充
					PhoneInfoDAO phdao = new PhoneInfoDAO();
					phdao.setConnection(oaconn);
					phdao.setOrguuid(orguuid);
					phdao.setUseruuid(vo.getPersonuuid());
					phdao.setUsername(vo.getCnname());
					phdao.setNoteorder(new Integer(999));
					phdao.setHrid(hrid);
	
					phdao.create();
					*/
					
					HRPersonTempDAO hpdao = new HRPersonTempDAO();
					hpdao.setConnection(conn);
					hpdao.setWhereClause(" hrid = '"+hrid+"' ");
					hpdao.setFlag(new Integer(1));
					hpdao.update(false);
					
				}else{
					return vo;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return vo;
		}finally{
			if (oaconn!=null){
				try {
					oaconn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
package com.icss.oa.user.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.hr.dao.HRSysPersonDAO;
import com.icss.oa.hr.vo.HRSysPersonVO;
import com.icss.oa.sync.dao.PersonDAO;
import com.icss.oa.sync.vo.PersonVO;
import com.icss.oa.user.dao.UserInfoSearchDAO;
import com.icss.oa.user.vo.UserInfoSearchVO;

public class hrUserInfoHandler {

	private Connection conn;

	public hrUserInfoHandler() {
	}

	public hrUserInfoHandler(Connection conn) {
		this.conn = conn;
	}

	public UserInfoSearchVO getUserInfo(String uuid) {
			UserInfoSearchVO vo = new UserInfoSearchVO();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT ");
			sb.append("HR_PHONE_INFO.PINOTEID,HR_PHONE_INFO.PIORGUUID,HR_PHONE_INFO.PIUSERUUID,HR_PHONE_INFO.PIUSERNAME,HR_PHONE_INFO.PIOFFICEPHONE,HR_PHONE_INFO.PIHOMEPHONE,HR_PHONE_INFO.PINETPHONE,HR_PHONE_INFO.PIMOBILEPHONE,HR_PHONE_INFO.PIFAXPHONE,HR_PHONE_INFO.PIOFFICEADDRESS,HR_PHONE_INFO.PIFUNCTION,HR_PHONE_INFO.PIISPERMISSION,HR_PHONE_INFO.PIMAINTANPERSON,HR_PHONE_INFO.PILASTMAINTANTIME,HR_PHONE_INFO.PINOTEORDER,HR_PHONE_INFO.PINAMEIDS,HR_PHONE_INFO.PIISPARTTIME,HR_PHONE_INFO.PIORGNAME,HR_PHONE_INFO.PIDEPTNAME,");
			sb.append("HRPERSON.HRID,HRPERSON.USERNAME,HRPERSON.SEX,HRPERSON.OFFICEPHONE,HRPERSON.HOMEPHONE,HRPERSON.NETPHONE,HRPERSON.MOBILEPHONE,HRPERSON.FAXPHONE,HRPERSON.ORGNAME,HRPERSON.ORGCODE,HRPERSON.DEPTNAME,HRPERSON.HEADSHIP,HRPERSON.JOBCODE,HRPERSON.JOB,HRPERSON.HOMEADDRESS,HRPERSON.EMAIL,HRPERSON.IMAGE,HRPERSON.VPN  ");
			sb.append(" FROM HR_PHONE_INFO,HRPERSON");
			sb.append("	WHERE HR_PHONE_INFO.PIHRID=HRPERSON.HRID ");
			sb.append(" AND HR_PHONE_INFO.piuseruuid='").append(uuid).append("'");
			//System.out.println("##############getuserinfo+"+sb.toString());
            UserInfoSearchDAO dao =new UserInfoSearchDAO();
			dao.setSearchSQL(sb.toString());
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);
			try {
				List list = factory.find(new UserInfoSearchVO());
				if(!list.isEmpty()){
					vo=(UserInfoSearchVO) list.get(0);
				}
			}
			catch (Exception ex) {
		       ex.printStackTrace();
			}
		
			return vo;
		}
	
	
	public String getUUID(String userid){
		
		HRSysPersonDAO dao = new HRSysPersonDAO();
		dao.setUserid(userid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		String uuid = null;
		try {
			HRSysPersonVO vo = (HRSysPersonVO) factory.find(new HRSysPersonVO()).get(0);
			uuid = vo.getPersonuuid();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uuid;

	}
	
	public List getAllPerson(){
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
	
	public void creatPic(InputStream in,String filepath) throws IOException{
		
		File picfile = new File(filepath);
		if (!picfile.exists()) {
			picfile.createNewFile();
		}
		System.out.println(picfile.getAbsolutePath());
		FileOutputStream fo = new FileOutputStream(picfile.getPath(), false);
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
			fo.write(buf, 0, len);
		}
		fo.close();
	}
}
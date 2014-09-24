package com.icss.oa.ftp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.orgtree.handler.HandlerException;
import com.icss.orgtree.handler.OrgHandler;
import com.icss.orgtree.vo.SysOrgVO;
import com.icss.resourceone.sdk.framework.Organization;

public class FtpRightHandler {
	Connection conn = null;
	public FtpRightHandler(Connection conn){
		this.conn = conn;
	}

	/**
	 * 返回一个用户所能读的本部门所有文件夹列表
	 * @param uuid
	 * @return
	 */
	public List getAllRead(String uuid) throws HandlerException,DAOException{
		List readlist = new ArrayList();
		OrgHandler handler = new OrgHandler(conn);
		DAOFactory factory = new DAOFactory(conn);
		
		//取得该用户所在的3级部门信息
		Organization contextorg = handler.getContextUserOrg(uuid);
		SysOrgVO topOrgVO = handler.getOrgByCurrentUser(contextorg, 3);
		String orguuid = topOrgVO.getOrguuid();
		
		//取得该部门的共享目录id
		String folderid = "";
		FtpAdminDAO fadao = new FtpAdminDAO(conn);
		factory.setDAO(fadao);
		fadao.setWhereClause("ORG='"+ orguuid +"'");
		List orgl = factory.find(new FtpAdminVO());
		if(orgl!=null && orgl.size()>0){
			folderid = (((FtpAdminVO)orgl.get(0)).getId()).toString();
		}else{
			//没有共享目录
			return null;
		}
		
		//判断该共享目录下的所有子目录的全局共享权限，将不为0的加入列表
		FtpFolderDAO ffdao = new FtpFolderDAO(conn);
		factory.setDAO(ffdao);
		ffdao.setWhereClause("PID = '"+ folderid +"'");
		List folderl = factory.find(new FtpFolderVO());
		if(folderl!=null && folderl.size()>0){
			int num = folderl.size();
			for(int i=0;i<num;i++){
				FtpFolderVO tempvo = (FtpFolderVO)folderl.get(i);
				if(!"0".equals(tempvo.getRight())){
					readlist.add(parsePath(tempvo).trim());
				}
			}
		}else{
			//该共享目录下没有子目录
			return null;
		}
		
		//将该用户所有分配权限的文件夹加入列表
		FtpUserRightDAO furdao = new FtpUserRightDAO(conn);
		factory.setDAO(furdao);
		furdao.setWhereClause("UNAME = '"+ uuid +"'");
		List urightl = factory.find(new FtpUserRightVO());
		if(urightl !=null && urightl.size()>0){
			int num = urightl.size();
			for(int i=0;i<num;i++){
				FtpUserRightVO tempvo = (FtpUserRightVO)urightl.get(i);
				String path = (tempvo.getFolder()).substring(1, (tempvo.getFolder()).length());
				if(!readlist.contains(path)){
					readlist.add(path);
				}
			}
		}	
		return readlist;
	}
	
	
	
	public String parsePath(FtpFolderVO vo){
		String result = "";
		result = (vo.getFather()).substring(1,(vo.getFather()).length())+vo.getName();
		return result;
	}
}



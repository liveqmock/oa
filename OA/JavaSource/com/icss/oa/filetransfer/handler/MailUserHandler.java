/**
 * 
 */
package com.icss.oa.filetransfer.handler;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.user.usermanage;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.config.Config;
import com.icss.oa.filetransfer.dao.FiletransferReadrecordDAO;
import com.icss.oa.filetransfer.dao.FiletransferSetDAO;
import com.icss.oa.filetransfer.dao.FiletransferTimeDAO;
import com.icss.oa.filetransfer.dao.FiletransferWanttorecordDAO;
import com.icss.oa.filetransfer.vo.FiletransferSetVO;
import com.icss.oa.filetransfer.vo.FiletransferWanttorecordVO;
import com.icss.oa.util.PropertyManager;
/**
 * ���ڹ����ʼ��ʺţ��������ʼ��������й���������ݿ��й���
 * @author lxy
 * @version 1.0
 *
 */
public class MailUserHandler {

	private Connection conn;
	private String initpwd = "1";
	/**
	 * 
	 */
	public MailUserHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * �����û�
	 * @param username
	 * @param password
	 * @param domain
	 * @param filesize
	 */
	public void createUser(String userid, String uuid,String orgname){
		FiletransferSetHandler handler = new FiletransferSetHandler(conn);
		FiletransferSetVO fvo = new FiletransferSetVO();
		usermanage user=new usermanage();
		int filesize = Config.MAIL_SPACE;
		String ip = PropertyManager.getProperty("archivesip");
		String backupip = PropertyManager.getProperty("backupip");
		String domain = PropertyManager.getProperty("archivesdomain");
		fvo.setDeleted(false);
		fvo.setFsSize(new Integer(filesize));
		fvo.setFsRule("1");
		fvo.setFsPer(new Integer(80));
		fvo.setFsUid(userid);
		fvo.setFsUuid(uuid);
		fvo.setFsDeltag("0");
		try {
			handler.add(fvo);
			user.connect(ip, 389, "cn=root,o=redflag.com,c=CH", "simple",
					"redflag.com");
			user.setRebunderServer(backupip);
			user.adduser(ip,userid,initpwd,domain,orgname,filesize*1024*1024);
		} catch (LdapException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * ͨ��uuidɾ�����û����е��ʼ��������
	 * @param uuid
	 */
	public void deleteUser(String uuid){
		usermanage user=new usermanage();
		String ip = PropertyManager.getProperty("archivesip");
		String backupip = PropertyManager.getProperty("backupip");
		String domain = PropertyManager.getProperty("archivesdomain");
		
		try {
			DAOFactory factory = new DAOFactory(conn);
			//ɾ��FiletransferSet��¼
			FiletransferSetDAO dao = new FiletransferSetDAO(conn);
			factory.setDAO(dao);
			dao.setWhereClause("FS_UUID='"+ uuid +"'");
			List l = factory.find(new FiletransferSetVO());
			factory.batchDelete();
			//ȡ��userid
			FiletransferSetVO fvo = null;
			if(l!=null && l.size()!=0){
				fvo = (FiletransferSetVO)l.get(0);
			}
			String userid = fvo.getFsUid();
			//ɾ��FiletransferTime��¼
			FiletransferTimeDAO ftdao = new FiletransferTimeDAO(conn);
			factory.setDAO(ftdao);
			ftdao.setWhereClause("SENDUSERID='"+ uuid +"'");
			factory.batchDelete();
			//ɾ��FILETRANSFER_WANTTORECORD,ȡ���ʼ�ID����
			FiletransferWanttorecordDAO fwdao = new FiletransferWanttorecordDAO(conn);
			factory.setDAO(fwdao);
			fwdao.setWhereClause("RECORDID LIKE '"+ userid +",%'");
			List wl = factory.find(new FiletransferWanttorecordVO());
			factory.batchDelete();
			//ɾ��FILETRANSFER_READRECORD
			for(int i=0;i<wl.size();i++){
				FiletransferWanttorecordVO fwvo = (FiletransferWanttorecordVO)wl.get(i);
				FiletransferReadrecordDAO frdao = new FiletransferReadrecordDAO(conn);
				factory.setDAO(frdao);
				frdao.setWhereClause("MAILID = '"+ fwvo.getMailid().toString() +"'");
				factory.batchDelete();
			}
			//ɾ���ʼ�����������Ա
			user.connect(ip, 389, "cn=root,o=redflag.com,c=CH", "simple",
			"redflag.com");
			user.setRebunderServer(backupip);
			user.deleteuser(ip, userid, domain);
		} catch (LdapException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//		usermanage user=new usermanage();
//		user.connect("10.102.1.33", 389, "cn=root,o=redflag.com,c=CH", "simple",
//		"redflag.com");
//		user.setRebunderServer("10.102.1.34");
//		user.deleteuser("10.102.1.33","7n7","oa.xinhua.net");
//		} catch (LdapException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
	}
}

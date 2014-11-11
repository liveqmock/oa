package com.icss.oa.sync.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.core.db.DBConnectionProvider;
import com.icss.core.db.DataBaseExecutor;
import com.icss.core.db.Record;
import com.icss.core.db.RecordSet;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.util.UUID;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.vo.SysOrgpersonVO;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.MailUserHandler;
import com.icss.oa.filetransfer.util.FindMailRun;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.filetransfer.util.ThreadsPool;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.hr.admin.HRGroupWebservice;
import com.icss.oa.shortmessage.powerassign.dao.SysOrgpersonDAO;
import com.icss.oa.sync.dao.PersonDAO;
import com.icss.oa.sync.dao.PersonSyncDAO;
import com.icss.oa.sync.dao.PersonSyncSearchDAO;
import com.icss.oa.sync.dao.PersonTempDAO;
import com.icss.oa.sync.dao.SysOrgDAO;
import com.icss.oa.sync.vo.PersonSyncSearchVO;
import com.icss.oa.sync.vo.PersonSyncVO;
import com.icss.oa.sync.vo.PersonTempVO;
import com.icss.oa.sync.vo.PersonVO;
import com.icss.oa.sync.vo.SysOrgVO;
import com.icss.oa.tq.Webservice.TQUser;
import com.icss.oa.user.handler.hrUserInfoHandler;
import com.icss.oa.user.vo.UserInfoSearchVO;
import com.icss.oa.util.CommUtil;
import com.icss.resourceone.common.login.dao.OrgPersonDAO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.org.dao.OrgDAO;
import com.icss.resourceone.org.model.OrgVO;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.user.dao.PersonAccountDAO;
import com.icss.resourceone.user.dao.PersonOrgDAO;
import com.icss.resourceone.user.model.PersonAccountVO;
import com.icss.starflow.designer.db.dao.SysPersonDAO;

public class UserSyncHandler {

	private Connection conn;

	public UserSyncHandler() {
	}

	public UserSyncHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * ͬ���������½�
	 * 
	 * @param personTempVo
	 * @return
	 * @throws Exception
	 */

	public boolean NewSyncUser(PersonTempVO personTempVo) throws Exception {
		boolean result = false;

		Connection oaconn = null;
		try {

			System.out.println("##########################create user!");
			String orguuid = "";
			if (personTempVo.getDeptid() != null
					&& personTempVo.getDeptid().length() > 0) {
				orguuid = this.getOrguuidByDeptid(personTempVo.getDeptid());
			} else {
				orguuid = this.getOrguuidByDeptid(personTempVo.getOrgid());
			}

			String userid = personTempVo.getUserid().trim(); // �û���

			//�������Ƿ�������
			Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
			java.util.regex.Matcher matcher = pattern.matcher(userid);
			if(matcher.find()){
				throw new HandlerException("�û���" + userid + "��������,���޸ĺ����½�������");
			}
			
			//�ж������Ƿ��ظ�
			if (this.UserIsExist(userid)) {
				throw new HandlerException("�û���" + userid + "�Ѿ�����,���޸ĺ����½�������");
			}
			String pname = personTempVo.getPersonname();
			String hrid = personTempVo.getHrid();
			String uuid = (new UUID()).toString();

			PersonAccountDAO personaccountdao = new PersonAccountDAO();
			personaccountdao.setConnection(conn);
			PersonDAO persondao = new PersonDAO();
			persondao.setConnection(conn);

			personaccountdao.setUserid(userid);
			personaccountdao.setPersonuuid(uuid);
			personaccountdao.setFlag(new Integer(2));
			personaccountdao.setPassword("ROdyR8mRC9Jfc");
			personaccountdao.setAccountstat(new Integer(0));
			personaccountdao.setLoginfailnum(new Integer(0));
			personaccountdao.setLastloginip("0.0.0.0");
			personaccountdao.setLastlogindate(new Timestamp(System
					.currentTimeMillis()));
			personaccountdao.setTtlflag(new Integer(0));
			personaccountdao.setAccountttl(new Integer(0));
			personaccountdao.setCreatetime(new Timestamp(System
					.currentTimeMillis()));
			personaccountdao.setPassquestion("password");
			personaccountdao.setPassanswer("pass");
			personaccountdao.setDeltag("0");

			persondao.setPersonuuid(uuid);
			persondao.setEnname(userid);
			persondao.setCnname(pname);
			persondao.setHrid(hrid);
			persondao.setSex(personTempVo.getTotq());

			OrgPersonDAO orgpersondao = new OrgPersonDAO();
			orgpersondao.setConnection(conn);
			orgpersondao.setPersonuuid(uuid);
			orgpersondao.setOrguuid(orguuid);
			orgpersondao.setIsbelong("1");

			OrgSyncHandler handler1 = new OrgSyncHandler(conn);
			String orgname = handler1.getOrgName(personTempVo.getDeptid());
			String parameters = orgname + "| | | | | | | ";
			String tqid = "";
			System.out.println("----------TQ user!--------");
			// ����TQ�ʺ�
			try {
				TQUser tq = new TQUser();
				tqid = tq.oneUserRegister(userid, "ROdyR8mRC9Jfc", personTempVo
						.getTotq().toString(), pname, "", "", "2", parameters);
				if (tqid.length() < 7) {
					System.out
							.println("------------���� TQ �ʺ� ʧ�� ������-------------");
					throw new HandlerException("���ڴ����»�ͨ �ʺ�ʧ�ܣ�" + pname
							+ "δ����������");
				} else {
					persondao.setTqid(new Integer(tqid));

				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new HandlerException("���ڴ����»�ͨ �ʺ�ʧ�ܣ�" + pname + "δ����������");
			}

			oaconn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			// �����绰��
			// Ŀǰ�绰�����ô�����ͬ������ɾ����Щ����
			// try {
			// System.out
			// .println("-------------------���� �绰�� �ʺ� !--------------");
			// oaconn = DBConnectionLocator.getInstance().getConnection(
			// Globals.DATASOURCEJNDI);
			// PersonSyncHandler phonehandler = new PersonSyncHandler(oaconn);
			// phonehandler.newPersonPhoneInfo(orguuid, uuid, pname, hrid);
			//
			// System.out
			// .println("-------------------���� �绰�� �ʺŽ��� !----------");
			// } catch (Exception e) {
			// e.printStackTrace();
			// throw new HandlerException("���ڴ��� �绰�� �ʺ�ʧ�ܣ�" + pname + "δ����������");
			// }
			System.out
					.println("-----------------------���� �����ʺ�!---------------");

			// ���������ʺ�
			try {
				MailUserHandler mailhandler = new MailUserHandler(oaconn);
				mailhandler.createUser(userid, uuid, orguuid);

				System.out
						.println("-------------------���� �����ʺŽ��� !---------------");
			} catch (Exception e) {
				e.printStackTrace();
				throw new HandlerException("���ڴ���  �����ʺ� ʧ�ܣ�" + pname + "δ����������");

			}

			//���������ļ���
			FolderHandler fhandler = new FolderHandler(conn);
			FolderManagementVO vo = new FolderManagementVO();
			vo.setFmPersonid(uuid);
			vo.setFmStoretype("1");
			vo.setFmCreatedate(new Long(System.currentTimeMillis()));
			vo.setFmModifydate(new Long(System.currentTimeMillis()));
			fhandler.addUser(vo);
			
			// //����ͷ��
			hrUserInfoHandler handler = new hrUserInfoHandler(oaconn);
			UserInfoSearchVO uservo = handler.getUserInfo(uuid);

			String path = HRGroupWebservice.readValue("TQPICPATH");

			if (uservo.getImage() != null) {
				handler.creatPic(uservo.getImage(), path + "/" + tqid + ".jpg");
			}

			personaccountdao.create();
			persondao.create();
			orgpersondao.create();
			result = true;

			System.out.println("-----------------�����ʼ�--------------");
			// ��OA����Ա�������û��İ칫�������˷�һ���ʼ�֪ͨ
			try {
				dirmanage mailhandler = null;
				Context ctx = Context.getInstance();
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(
						oaconn);
				UserInfo user = ctx.getCurrentLoginInfo();
				String chinaName = user.getCnName();

				// ��ȡ������
				String mail_userid = ftsHandler.getUserid(user.getPersonUuid());

				mailhandler = MailhandlerFactory.getHandler(mail_userid);
				String subject = "��Ա���֪ͨ";
				StringBuffer texttemp = new StringBuffer();

				texttemp
						.append(this.GetOrgName(personTempVo.getOrgid()) + "--");
				texttemp.append(this.GetOrgName(personTempVo.getDeptid()));
				texttemp.append("��");
				texttemp.append(pname);
				texttemp.append("ͬ־��OA�˺��ѿ�ͨ����¼�˺�Ϊ��");
				texttemp.append(userid);
				texttemp.append("����¼����Ϊ:1��");
				String text = "";
				String[] to = null;
				List<String> userlist = new ArrayList<String>();

				OrgVO orgvo = this.getOrgCode(orguuid);
				// �õ���һ���µİ칫�ҳ�Ա
				List officeperson = this.getPersonByOrg(orgvo
						.getParentorguuid());
				if (officeperson != null && officeperson.size() > 0) {
					userlist.add("dev");
					String officeuserid = null;
					PersonAccountVO paccountvo = new PersonAccountVO();
					String officeuseruuid = null;

					for (int i = 0; i < officeperson.size(); i++) {
						officeuseruuid = ((PersonOrgDAO) officeperson.get(i))
								.getPersonuuid();
						paccountvo = this.getPersonAccount(officeuseruuid);
						if ("0".equals(paccountvo.getDeltag())) {
							officeuserid = ftsHandler.getUserid(officeuseruuid);
							userlist.add(officeuserid);
						}
					}
					to = new String[userlist.size()];
					for (int j = 0; j < userlist.size(); j++) {
						to[j] = userlist.get(j) + ("@oa.xinhua.net");
					}
				} else {
					userlist.add("dev");
					to = new String[1];
					to[0] = "dev@oa.xinhua.net";
				}
				System.out.println(to.length);
				for (String te : to) {
					System.out.println(te);
				}
				String[] cc = new String[0];
				String[] bcc = new String[0];
				long nowtime = System.currentTimeMillis();
				// ���͵ı���Ϊ������Ϸ��͵�ʱ�䴮��Ϊɾ�����������
				subject = subject.concat(",").concat(String.valueOf(nowtime));
				text = CommUtil.formathtm(texttemp.toString());
				System.out.println("=+++++++++++=");

				String[][] failuser = mailhandler.transfermail(subject, text,
						to, cc, bcc, null, 1);
				// end��OA����Ա�������û��İ칫�������˷�һ���ʼ�֪ͨ
				System.out.println("------����");

				// �����ʼ������¼

				for (String uid : userlist) {
					System.out.println("==============" + uid);

					// ���뵽����
					String intendtopic = "���ʼ�������" + chinaName + "��"
							+ CommUtil.getTime(System.currentTimeMillis())
							+ "��";
					String tointendId = user.getUserID()+"," + subject + "," + uid;
					// handler.addToIntend(request.getContextPath(),yesUserID,intendtopic,tointendId);
					Thread t = new Thread(new FindMailRun(user.getUserID(),
							subject, nowtime, intendtopic, tointendId, uid,
							"/oabase"));
					ThreadsPool.getInstance().putTask(t);
				}

			} catch (Exception e) {
				e.printStackTrace();
				//throw new HandlerException("��������ʧ��!");
			}
			System.out.println("-----�����ʼ�����-----");
			System.out.println("-----��������-----");

			// ��������ϵͳ
			String xml = this.getHrXML("new", hrid, userid, uuid, "", "");
			System.out.println(xml);
			HRGroupWebservice hw = new HRGroupWebservice();
			hw.BackToHR(xml);
			System.out.println("-----�������½���-----");

		} catch (Exception e) {
			throw e;
		} finally {
			if (oaconn != null) {
				try {
					oaconn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	
	
	
	
	
	
	/** 
	 * ͬ���������½�
	 * 
	 * author:zhouyi
	 * @param personTempVo
	 * @return
	 * @throws Exception
	 */

	public String NewSyncUser_New(PersonSyncVO personSyncVo) throws Exception {
		String result = "true";
		Connection oaconn = null;
		try {
			System.out.println("##########################create user!");
			
			String userid = personSyncVo.getUsername().trim();// �û���
			
			String xinhuaid = personSyncVo.getXinhuaid();//xinhuaid
			
			String orguuids = "";
			if(personSyncVo.getGroupid()!=null &&personSyncVo.getGroupid().length() > 0) {
				for (String orgId : personSyncVo.getGroupid().split(";"))
				{
					SysOrgVO temp_sysOrgVo = getOrgByOrgCode(orgId);
					if(temp_sysOrgVo!=null&&temp_sysOrgVo.getOrguuid()!=null&&!temp_sysOrgVo.getOrguuid().equals("")){
						orguuids += temp_sysOrgVo.getOrguuid() + ";";
					}else{
						result="����OA�в�������֯����Ϊ"+orgId+"��������λ,�û���Ϊ"+userid+"���û�����ʧ��!";
						return result;
					}
				}
				
				if (orguuids.endsWith(";")) orguuids = orguuids.substring(0, orguuids.length() - 1);
			}
			
			
			
			if(xinhuaid==null){
				xinhuaid = "";
			}
			
			if(xinhuaid.equals("")){
				result="����ͬ����Ϣ��xinhuaidΪ��,"+"�û���Ϊ"+userid+"���û�����ʧ��!";
				return result;
			}
			
			
			
			
			//�ж������Ƿ��ظ�
			if(this.UserIsExist(userid)) {
				//throw new HandlerException("�û���" + userid + "�Ѿ�����,���޸ĺ����½�������");
				result = "�����û���"+userid+"�Ѿ�����,�û���Ϊ"+userid+"���û�����ʧ��";
				return result;
			}
			
			
			String pname = personSyncVo.getTruename();
			String hrid = personSyncVo.getHrid();
			String mobile = personSyncVo.getMobilephone();
			String uuid = (new UUID()).toString();
			String userpwd = personSyncVo.getUserpwd();
			
			PersonAccountDAO personaccountdao = new PersonAccountDAO();
			personaccountdao.setConnection(conn);
			PersonDAO persondao = new PersonDAO();
			persondao.setConnection(conn);

			personaccountdao.setUserid(userid);
			personaccountdao.setPersonuuid(uuid);
			personaccountdao.setFlag(new Integer(2));  
			 
			//personaccountdao.setPassword("ROkMLeoiP.H7I");
			personaccountdao.setPassword("MD"+userpwd);
			
			//personaccountdao.setPassanswer(personSyncVo.getUserpwd());
			
			
			personaccountdao.setAccountstat(new Integer(0));
			personaccountdao.setLoginfailnum(new Integer(0));
			personaccountdao.setLastloginip("0.0.0.0");
			personaccountdao.setLastlogindate(new Timestamp(System.currentTimeMillis()));
			personaccountdao.setTtlflag(new Integer(0));
			personaccountdao.setAccountttl(new Integer(0));
			personaccountdao.setCreatetime(new Timestamp(System.currentTimeMillis()));
			personaccountdao.setPassquestion("password");
			personaccountdao.setPassanswer("pass");
			personaccountdao.setDeltag("0");

			persondao.setPersonuuid(uuid);
			persondao.setEnname(userid);
			persondao.setXinhuaid(xinhuaid);
			persondao.setCnname(pname);
			persondao.setMobile(mobile);
			persondao.setHrid(hrid);
			
			
			//R1��ֵ��0���ܡ�1�С�2Ů
			//IDS��ֵ:1�У�2Ů��3����
			
			String sex = personSyncVo.getGender();
			if(sex.equals("3")){
				sex = "0";
			}
			
			Integer sex_Integer = new Integer(0);
			if(sex!=null&&!sex.equals("")){
				sex_Integer = new Integer(sex);
			}
			
			persondao.setSex(sex_Integer);
			OrgSyncHandler handler1 = new OrgSyncHandler(conn);
			 
			String orgname = handler1.getOrgName(personSyncVo.getGroupid().split(";")[0]);
			String parameters = orgname + "| | | | | | | ";
			String tqid = "";
			System.out.println("----------TQ user!--------");
			
			
			
			//����TQ�ʺ�
			try{ 
				TQUser tq = new TQUser();
				
				//��ʱ����TQ����TQ�˺ţ����Ƶ���ʽ������ʱ�����±������е�ע��ȥ��
				String ro_pwd = personSyncVo.getRo_pwd();
				tqid = tq.oneUserRegister(userid, ro_pwd , sex, pname, "", "", "2", parameters);
				
				
				if(tqid.length() < 7){ 
					//��ʱ����TQ����TQ�˺ţ����Ƶ���ʽ������ʱ�����±���3�е�ע��ȥ��
					System.out.println("------------���� TQ �ʺ� ʧ�� ������-------------");
					result = "���ڴ����»�ͨ�ʺ�ʧ��,"+"�û���Ϊ"+userid+"���û�δ����!";
					return result;
				}else{ 
					persondao.setTqid(new Integer(tqid));
				} 
			}catch(Exception e){
				//e.printStackTrace();
				result = "���ڴ����»�ͨ�ʺ�ʧ��,"+"�û���Ϊ"+userid+"���û�δ����!";
				return result;
				//throw new HandlerException("���ڴ����»�ͨ �ʺ�ʧ�ܣ�" + pname + "δ����������");
			}
			
			
			
			
			
			
			oaconn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			// �����绰��
			// Ŀǰ�绰�����ô�����ͬ������ɾ����Щ����
			// try {
			// System.out
			// .println("-------------------���� �绰�� �ʺ� !--------------");
			// oaconn = DBConnectionLocator.getInstance().getConnection(
			// Globals.DATASOURCEJNDI);
			// PersonSyncHandler phonehandler = new PersonSyncHandler(oaconn);
			// phonehandler.newPersonPhoneInfo(orguuid, uuid, pname, hrid);
			//
			// System.out
			// .println("-------------------���� �绰�� �ʺŽ��� !----------");
			// } catch (Exception e) {
			// e.printStackTrace();
			// throw new HandlerException("���ڴ��� �绰�� �ʺ�ʧ�ܣ�" + pname + "δ����������");
			// }
			System.out.println("-----------------------���� �����ʺ�!---------------");

			// ���������ʺ�
			try{
				MailUserHandler mailhandler = new MailUserHandler(oaconn);
				
				
				//��ʱ���������˺ţ���Ϊ���Ի�����������ʽ�����������������Ƶ���ʽ���������±����е�ע��ȥ����
				System.out.println("���ڴ��������˺� userid:"+userid+" personuuid:"+uuid+" orguuid:"+orguuids);
				mailhandler.createUser(userid, uuid, orguuids);
				
				
				System.out.println("-------------------���� �����ʺŽ��� !---------------");
			} catch (Exception e) {
				//e.printStackTrace();
				result = "���ڴ��������ʺ�ʧ��,"+"�û���Ϊ"+userid+"���û�δ����!";
				return result;
				//throw new HandlerException("���ڴ���  �����ʺ� ʧ�ܣ�" + pname + "δ����������");
			}
			
			//���������ļ��� 
			FolderHandler fhandler = new FolderHandler(oaconn);
			FolderManagementVO vo = new FolderManagementVO();
			vo.setFmPersonid(uuid);
			vo.setFmStoretype("1");
			vo.setFmCreatedate(new Long(System.currentTimeMillis()));
			vo.setFmModifydate(new Long(System.currentTimeMillis()));
			fhandler.addUser(vo);
			
			//����ͷ��
			hrUserInfoHandler handler = new hrUserInfoHandler(oaconn);
			UserInfoSearchVO uservo = handler.getUserInfo(uuid);
			
			String path = HRGroupWebservice.readValue("TQPICPATH");
			
			if(uservo.getImage() != null){
				//��ʱ������ͷ���ļ��У��Ƶ���ʽ����ʱ��������һ��ע�͵�
				handler.creatPic(uservo.getImage(), path + "/" + tqid + ".jpg");
			}
			
			personaccountdao.create();
			persondao.create();
			
			OrgPersonDAO orgpersondao = new OrgPersonDAO();
			orgpersondao.setConnection(conn);
			int index = 0;
			for (String orgId : orguuids.split(";"))
			{
				orgpersondao.setPersonuuid(uuid);
				orgpersondao.setOrguuid(orgId);
				orgpersondao.setIsbelong(index++ == 0? "1" : "0");
				orgpersondao.create();
			}
			
			result = "true";
			
			System.out.println("-----------------�����ʼ�--------------");
			//��OA����Ա�������û��İ칫�������˷�һ���ʼ�֪ͨ
			try {
				dirmanage mailhandler = null;
				Context ctx = Context.getInstance();
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(oaconn);
				UserInfo user = ctx.getCurrentLoginInfo();
				String chinaName = user.getCnName();

				//��ȡ������
				String mail_userid = ftsHandler.getUserid(user.getPersonUuid());

				mailhandler = MailhandlerFactory.getHandler(mail_userid);
				String subject = "��Ա���֪ͨ";
				StringBuffer texttemp = new StringBuffer();

				texttemp.append(this.getFullOrgName(personSyncVo.getGroupid()) + "--");
				texttemp.append("��");
				texttemp.append(pname);
				texttemp.append("ͬ־��OA�˺��ѿ�ͨ����¼�˺�Ϊ��");
				texttemp.append(userid);
				texttemp.append("����¼����Ϊ:1��");
				String text = "";
				String[] to = null;
				List<String> userlist = new ArrayList<String>();

				
				
				OrgVO orgvo = this.getOrgCode(orguuids);
				
				
				//�õ���һ���µİ칫�ҳ�Ա
				List officeperson = this.getPersonByOrg(orgvo.getParentorguuid());
				
				
				//����˵����Ҫ��������Ա���ڰ칫�ҵ��˷��ʼ�����˼���������һ�䣬�����칫����Ա�������Ҫ������������һ��ע�͵������ˡ�
				officeperson = null;
				
				
				if(officeperson != null && officeperson.size() > 0) {
					userlist.add("dev");
					String officeuserid = null;
					PersonAccountVO paccountvo = new PersonAccountVO();
					String officeuseruuid = null;

					for(int i = 0; i < officeperson.size(); i++) {
						officeuseruuid = ((PersonOrgDAO) officeperson.get(i)).getPersonuuid();
						paccountvo = this.getPersonAccount(officeuseruuid);
						if("0".equals(paccountvo.getDeltag())) {
							officeuserid = ftsHandler.getUserid(officeuseruuid);
							userlist.add(officeuserid);
						}
					}
					to = new String[userlist.size()];
					for(int j = 0; j < userlist.size(); j++){
						to[j] = userlist.get(j) + ("@oa.xinhua.net");
					}
				}else{
					userlist.add("dev");
					to = new String[1];
					to[0] = "dev@oa.xinhua.net";
				}
				
				
				
				String[] cc = new String[0];
				String[] bcc = new String[0];
				long nowtime = System.currentTimeMillis();
				
				
				
				//���͵ı���Ϊ������Ϸ��͵�ʱ�䴮��Ϊɾ�����������
				subject = subject.concat(",").concat(String.valueOf(nowtime));
				text = CommUtil.formathtm(texttemp.toString());
				String[][] failuser = mailhandler.transfermail(subject, text,to, cc, bcc, null, 1);
				
				
				
				//�������ʼ�����Ϣ���뵽�����б�
				for(String uid:userlist) {
					//���뵽����
					String intendtopic = "���ʼ�������" + chinaName + "��"+ CommUtil.getTime(System.currentTimeMillis())+ "��";
					String tointendId = user.getUserID()+"," + subject + "," + uid;
					// handler.addToIntend(request.getContextPath(),yesUserID,intendtopic,tointendId);
					Thread t = new Thread(new FindMailRun(user.getUserID(),subject, nowtime, intendtopic, tointendId, uid,"/oabase"));
					ThreadsPool.getInstance().putTask(t); 
				} 
				
			}catch (Exception e){
				//e.printStackTrace();
				//throw new HandlerException("��������ʧ��!");
			}
			System.out.println("-----�����ʼ�����-----");
			
			 
			
			//��ʱ�������£��Ƶ���ʽ����ʱ�������ע��ȥ����
			
			
			System.out.println("-----��������-----");
			// ��������ϵͳ
			String xml = this.getHrXML("new", hrid, userid, uuid, "", "");
			System.out.println(xml);
			
			HRGroupWebservice hw = new HRGroupWebservice();
			if(xml!=null&&!xml.equals("")){
				hw.BackToHR(xml); 
			}
			System.out.println("-----�������½���-----");
			
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (oaconn != null) {
				try {
					oaconn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	
	
	
	
	
	
	
	
	
	

	/**
	 * ͬ�������ĸ���
	 * 
	 * @param personTempVo
	 * @return
	 */
	public boolean UpdSyncUser(PersonTempVO personTempVo) throws Exception {

		boolean result = false;
		Connection oaconn = null;
		PreparedStatement pst = null;
		try {
			String field = personTempVo.getAlterfield(); // �����һ���ֶ�
			System.out.println("@@@@@@@@@@@:" + field);
			String hrid = personTempVo.getHrid();
			String purpose = personTempVo.getPurpose(); // �����
			String origin = personTempVo.getOrigin(); // ���ǰ
			PersonDAO persondao = new PersonDAO();
			persondao.setHrid(hrid);

			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(persondao);

			List list = factory.find(new PersonVO());
			if (list.isEmpty()) {
				throw new HandlerException("�Ҳ���HridΪ" + hrid + "����");
			}

			PersonVO vo = (PersonVO) list.get(0);
			String personuuid = vo.getPersonuuid(); // �õ�person��uuid

			if (field.equalsIgnoreCase("PNAME")) {
				PersonDAO pdao = new PersonDAO();
				pdao.setConnection(conn);
				pdao.setPersonuuid(personuuid);

				pdao.setCnname(purpose);
				pdao.update(true);

				PersonVO vo2 = this.getPersonByuuid(personuuid);
				PersonAccountVO vo1 = this.getPersonAccount(personuuid);
				if (vo1 != null) {
					TQUser web = new TQUser();
					String rs = web.updateUser(vo2.getTqid().toString(), vo1
							.getUserid(), vo1.getPassword(), vo2.getCnname(),
							"2", " | | | | | | | | | | | ");
					System.out.println(rs);

				}
				result = true;

			}
			/*
			 * else if(field.equals("ORGID")){ //������ΪORGID������DEPTID���
			 * 
			 * 
			 * 
			 * result=true; }
			 */
			else if (field.equalsIgnoreCase("DEPTID")
					|| field.equalsIgnoreCase("ORGID")) {
				/*
				 * ���¾ּ���λ�͸��µ�λ�Ĵ���һ�£���Ϊ�������xml�е�һ��upd�����м�
				 * ��ORGID����DEPTID����ֻȡDEPTID������ʱ��Ҳ����˵һ���û����Ƕ�Ӧ һ����֯
				 * 
				 * �ȵõ����Ӧ��person��uuid���ٵõ�ԭ����org��uuid��Ҫ���ĵ�orguuid,
				 * �ٵ�OrgPerson����ȥ����
				 * 
				 * fandy
				 */

				OrgDAO orgdao = new OrgDAO();
				System.out.println("+++++++++++++++++purpose" + purpose);
				orgdao.setOrgcode(purpose);
				factory.setDAO(orgdao);

				list = factory.find();
				if (list.isEmpty()) {
					throw new HandlerException("�Ҳ���Ŀ����֯");
					// return false;
				}
				orgdao = (OrgDAO) list.get(0);
				String orguuid = orgdao.getOrguuid(); // �õ�Ŀ����֯��uuid
				System.out.println("+++++++++++++++++" + orguuid);

				// ֻȡһ�����ж�ԭ����֯
				if ((purpose.toUpperCase().lastIndexOf("ZC") == purpose
						.length() - 2)
						|| (origin.indexOf("03") == 0 && purpose.indexOf("03") != 0)
						|| (origin.toUpperCase().lastIndexOf("ZC") == origin
								.length() - 2)) {
					OrgDAO orgdao1 = new OrgDAO();
					orgdao1.setOrgcode(origin);
					factory.setDAO(orgdao1);
					list = factory.find();
					if (list.isEmpty()) {
						throw new HandlerException("�Ҳ���ԭ��֯" + origin);
						// return false;
					}
					orgdao1 = (OrgDAO) list.get(0);
					String originorg = orgdao1.getOrguuid(); // �õ�ԭ����֯��uuid
					System.out.println("--------------" + originorg);
				}
				// ����Ŀ�����֯

				// ����פ�����
				if (purpose.indexOf("03") == 0) {
					// ԭ����֯��Ϊ����֯
					pst = conn
							.prepareStatement("update ro_orgperson set isbelong=? where personuuid=? ");
					pst.setString(1, "0");
					pst.setString(2, personuuid);
					pst.executeUpdate();

					// �����µ���֯
					OrgPersonDAO dao2 = new OrgPersonDAO();
					dao2.setPersonuuid(personuuid);
					dao2.setOrguuid(orguuid);
					dao2.setIsbelong("1");
					dao2.setConnection(conn);
					dao2.create();
				} else if (purpose.toUpperCase().lastIndexOf("ZC") == purpose
						.length() - 2
						&& origin.indexOf("03") != 0) {
					// ���������ŵ�פ��
					pst = conn
							.prepareStatement("update ro_orgperson set orguuid=? where personuuid=? and orguuid=(select orguuid from ro_org where orgcode=?)");
					pst.setString(1, orguuid);
					pst.setString(2, personuuid);
					pst.setString(3, origin);
					pst.executeUpdate();

				} else if (origin.indexOf("03") == 0
						&& purpose.indexOf("03") != 0) {
					// פ��������
					System.out.println("פ�������أ�");
					pst = conn
							.prepareStatement("delete ro_orgperson where personuuid=? and orguuid=(select orguuid from ro_org where orgcode=?)");
					pst.setString(1, personuuid);
					pst.setString(2, origin);

					pst.executeUpdate();
					System.out.println("פ�������ؽ�����");
				} else if (origin.toUpperCase().lastIndexOf("ZC") == origin
						.length() - 2) {
					// ������פ�����
					pst = conn
							.prepareStatement("update ro_orgperson set isbelong='0' where personuuid=? ");
					pst.setString(1, personuuid);
					pst.executeUpdate();

					pst = conn
							.prepareStatement("update ro_orgperson set orguuid=? ,isbelong='1' where personuuid=? and orguuid=(select orguuid from ro_org where orgcode=?)");
					pst.setString(1, orguuid);
					pst.setString(2, personuuid);
					pst.setString(3, origin);
					pst.executeUpdate();

				} else {
					pst = conn
							.prepareStatement("delete ro_orgperson where personuuid=? and isbelong='1'");
					pst.setString(1, personuuid);
					pst.executeUpdate();
					pst = conn
							.prepareStatement("insert into ro_orgperson(orguuid,personuuid,isbelong) values(?,?,'1')");
					pst.setString(1, orguuid);
					pst.setString(2, personuuid);
					pst.executeUpdate();
					// OrgPersonDAO dao1 = new OrgPersonDAO();
					// dao1.setConnection(conn);
					// dao1.setWhereClause("personuuid = '" + personuuid
					// + "'");
					// dao1.setOrguuid(orguuid);
					// dao1.update(false);
				}

				// oaconn = DBConnectionLocator.getInstance().getConnection(
				// Globals.DATASOURCEJNDI);
				// �޸ĵ绰������֯
				// oaconn = DBConnectionLocator.getInstance().getConnection(
				// Globals.DATASOURCEJNDI);
				// PhoneInfoDAO pdao = new PhoneInfoDAO();
				// pdao.setConnection(oaconn);
				// pdao.setWhereClause("useruuid = '" + personuuid
				// + "'");
				// pdao.setOrguuid(orguuid);
				// pdao.update(false);

				// �޸�TQ����֯
				try {
					OrgSyncHandler handler1 = new OrgSyncHandler(conn);
					String orgname = handler1.getOrgName(personTempVo
							.getDeptid());
					String parameters = orgname + "| | | | | | | ";

					PersonVO vo2 = this.getPersonByuuid(personuuid);
					PersonAccountVO vo1 = this.getPersonAccount(personuuid);
					if (vo1 != null) {
						TQUser web = new TQUser();
						String rs = web.updateUser(vo2.getTqid().toString(),
								vo1.getUserid(), vo1.getPassword(), vo2
										.getCnname(), "2", parameters);
						System.out.println(rs);

					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new HandlerException(vo.getCnname() + "�޸� TQ ��֯ʧ�ܣ�����");
				}

				result = true;
			}

			// �޸��ֻ�Ϊ�˹���������������
			else if (field.equalsIgnoreCase("MOBILEPHONE")) {
				result = true;
			} else if (field.equalsIgnoreCase("serialindex")) {
				PersonDAO pdao = new PersonDAO();
				pdao.setConnection(conn);
				pdao.setPersonuuid(personuuid);
				pdao.setSequenceno(new Integer(purpose));
				pdao.update(true);
				result = true;
			}else if("SEX".equalsIgnoreCase(field)){
				PersonDAO pdao = new PersonDAO();
				pdao.setConnection(conn);
				pdao.setPersonuuid(personuuid);
				pdao.setSex(new Integer(purpose));
				pdao.update(true);
				result = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (pst != null) {
				pst.close();
			}
			if (oaconn != null) {
				try {
					oaconn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	
	
	
	
	/**
	 * ͬ�������ĸ���
	 * 
	 * @param personTempVo
	 * @return
	 */
	public String UpdSyncUser_New(PersonSyncVO personSyncVO) throws Exception {

		String result = ""; 
		Connection oaconn = null;
		PreparedStatement pst = null;
		
		String userid = personSyncVO.getUsername().trim(); 
		
		try {
			String xinhuaid = personSyncVO.getXinhuaid();
			
			if(xinhuaid==null){
				xinhuaid = "";
			}
			
			if(xinhuaid.equals("")){
				result="����ͬ����Ϣ��xinhuaidΪ��,"+"�޸��û���Ϊ"+userid+"���û���Ϣʧ��!";
				return result;
			}
			
			PersonDAO persondao = new PersonDAO();
			persondao.setXinhuaid(xinhuaid);
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(persondao);

			List list = factory.find(new PersonVO());
			if(list.isEmpty()){
				result="�����Ҳ���xinhuaidΪ"+xinhuaid+"����,"+"�޸��û���Ϊ"+userid+"���û���Ϣʧ��!";
				return result;
				//throw new HandlerException("�Ҳ���xhinhuaidΪ" + xinhuaid + "����");
			}
			
			
			
			
			//ͣ�û�������Ա
			//����Ƿ�ͣ����Ϣ
			String jsonString = personSyncVO.getJsonstring();
			String actived = "";
		    if(!jsonString.equals("")){
		        JSONObject jsonObj = new JSONObject(jsonString);
		   	    JSONObject syncObj = new JSONObject();
				String command = jsonObj.getString("Command");
				syncObj = (JSONObject)jsonObj.get("User");
				
				if(syncObj.has("actived")){
			    	actived = ""+syncObj.getString("actived");
			    }
		    }
			DataBaseExecutor m_executor = DataBaseExecutor.getExecutor(conn);
            if(actived.equals("FALSE")){//idsҪ��ͣ��,�Ͱ����xinhuaid��Ӧ���˶�ͣ��������ro_personaccount�����ж�������Ϣ
		    	StringBuffer sb=new StringBuffer(""); 
		    	sb.append("update ro_personaccount t set t.deltag='1' where t.personuuid in(");
		    	sb.append("select t.personuuid from ro_person t, ro_personaccount s");
		    	sb.append(" where t.personuuid=s.personuuid and s.deltag='0' and t.xinhuaid='");
		    	sb.append(xinhuaid).append("')");
		    	m_executor.execute(sb.toString());
		    }else{//idsҪ������
		    	String sqlStr = "SELECT T.XINHUAID,T.PERSONUUID,S.USERID,S.DELTAG FROM RO_PERSON T, RO_PERSONACCOUNT S WHERE T.PERSONUUID=S.PERSONUUID  AND T.XINHUAID='"+xinhuaid+"'";
		    	RecordSet rcdSet = m_executor.find(sqlStr);
		    	if(rcdSet!=null&&rcdSet.size()==1){//ֻ�е�����ֻ��1����¼ʱ����״̬Ϊ��ɾ�����û�ʱ������������˻�
		    		Record record = rcdSet.get(0);
		    		String puuid = record.getString("PERSONUUID","");
		    		String deltag = record.getString("DELTAG","");
		    		if(deltag.equals("1")){
		    			sqlStr = "UPDATE RO_PERSONACCOUNT T SET T.DELTAG='0' WHERE T.PERSONUUID='"+puuid+"'";
		    			m_executor.execute(sqlStr);
		    		}
		    	}
		    }
			
			
			
			
			
			PersonVO personVO = (PersonVO) list.get(0);
			String personuuid = personVO.getPersonuuid(); // �õ�person��uuid
			
			
			//�����û���Ϣ
			PersonDAO pdao = new PersonDAO();
			pdao.setConnection(conn);
			pdao.setPersonuuid(personuuid);
			
			//Ŀǰֻ��IDS�Ǳ߸����� �û��������ֻ��š��Ա���֯
			pdao.setCnname(personSyncVO.getTruename());
			pdao.setMobile(personSyncVO.getMobilephone());
			
			//R1��ֵ��0���ܡ�1�С�2Ů
			//IDS��ֵ:1�У�2Ů��3����
			String sex = personSyncVO.getGender();
			if(sex.equals("3")){
				sex = "0";
			}
			Integer sex_Integer = new Integer(0);
			if(sex!=null&&!sex.equals("")){
				sex_Integer = new Integer(sex);
			}
			pdao.setSex(sex_Integer);
			
			pdao.update(true);
			
			
			
			
			
			
			
			
			//����ǵ�����Ա������֯����
			String toOrgCode = personSyncVO.getGroupid();
			if(toOrgCode!=null&&!toOrgCode.equals("")&&(personSyncVO.getJsonstring().indexOf("moveToGroup")!=-1||personSyncVO.getJsonstring().indexOf("removeFromGroup")!=-1)){
				boolean already = false;
				int index = 0;
				for (String code : toOrgCode.split(";"))
				{
					SysOrgVO orgvo = this.getOrgByOrgCode(code);
					if(orgvo!=null&&orgvo.getOrguuid()!=null&&!orgvo.getOrguuid().equals("")){
						String orguuid = orgvo.getOrguuid();
						if (!already)
						{
							pst = conn.prepareStatement("delete ro_orgperson where personuuid=?");
							pst.setString(1, personuuid);
							pst.executeUpdate();
							already = true;
						}
						pst = conn.prepareStatement("insert into ro_orgperson(orguuid,personuuid,isbelong) values(?,?,?)");
						pst.setString(1, orguuid);
						pst.setString(2, personuuid);
						pst.setString(3, index++ == 0? "1" : "0");
						pst.executeUpdate();
					}
				}
			}
			
			 
			//����TQ�Ǳߵ��û���������֯����ʱ�����£��Ƶ���ʽ����ʱ�ſ������ע�ͼȿɡ�
			
			PersonVO vo2 = this.getPersonByuuid(personuuid);
			PersonAccountVO vo1 = this.getPersonAccount(personuuid);
			try {
				//����TQ���û�����
				TQUser web1 = new TQUser(); 
				if(vo1!=null&&vo2!=null&&vo2.getTqid()!=null&&!vo2.getTqid().equals("")&&vo1.getUserid()!=null&&!vo1.getUserid().equals("")&&personSyncVO.getRo_pwd()!=null&&!personSyncVO.getRo_pwd().equals("")&&personSyncVO.getTruename()!=null&&!personSyncVO.getTruename().equals("")){
					String rs = web1.updateUser(vo2.getTqid().toString(), vo1.getUserid(), personSyncVO.getRo_pwd(), personSyncVO.getTruename(),"2", " | | | | | | | | | | | ");
					System.out.println(rs);
				}
				//�޸�TQ����֯ 
				OrgSyncHandler handler1 = new OrgSyncHandler(conn); 
				String orgname = handler1.getOrgName((personSyncVO.getGroupid().split(";"))[0]);
				String parameters = orgname + "| | | | | | | ";
				
				if(vo1!=null&&vo2!=null&&vo2.getTqid()!=null&&!vo2.getTqid().equals("")&&vo1.getUserid()!=null&&!vo1.getUserid().equals("")&&personSyncVO.getRo_pwd()!=null&&!personSyncVO.getRo_pwd().equals("")&&vo2.getCnname()!=null&&!vo2.getCnname().equals("")){
					TQUser web2 = new TQUser();
					String rs2 = web2.updateUser(vo2.getTqid().toString(),vo1.getUserid(), personSyncVO.getRo_pwd(), vo2.getCnname(), "2", parameters);
					System.out.println(rs2);
				}
			}catch(Exception e){
				return "�޸�TQ�����Ϣʧ�ܣ�";
			}
			
			//�޸�TQ�Ǳߵ���Ϣ����
			
			
			
			
			
			
			
			result="true";
		}catch(Exception e) {
			result="����ĳЩ�����ֶ�ֵΪ��,"+"�޸��û���Ϊ"+userid+"���û���Ϣʧ��";
			return result;
			//throw e;
		} finally {
			if (pst != null) {
				pst.close();
			}
			if (oaconn != null) {
				try {
					oaconn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	
	
	

	/**
	 * ɾ��
	 * 
	 * @param personVo
	 * @return
	 * @throws Exception
	 */
	public boolean DelSyncUser(PersonTempVO personVo) throws Exception {
		boolean result = false;
		Connection oaconn = null;
		try {
			String hrid = personVo.getHrid();

			DAOFactory factory = new DAOFactory(conn);

			PersonDAO persondao = new PersonDAO();
			persondao.setHrid(hrid);

			factory.setDAO(persondao);
			List list = null;
			list = factory.find(new PersonVO());
			if (list.isEmpty()) {
				throw new HandlerException("�Ҳ������� hrid =" + hrid);
			}
			PersonVO vo = (PersonVO) list.get(0);

			String personuuid = vo.getPersonuuid();
			// ����uuid�ҵ�PersonAccount

			PersonAccountDAO dao = new PersonAccountDAO();
			dao.setPersonuuid(personuuid);
			factory.setDAO(dao);
			dao = (PersonAccountDAO) factory.findByPrimaryKey();
			String userid = dao.getUserid();
			//��ɾ����Ա 2009.9.28
			//dao.delete();
			 dao.setDeltag("1");
			 dao.update(true);

//			// ɾ����֯��ϵ
//			OrgPersonDAO orgpersondao = new OrgPersonDAO();
//			orgpersondao.setPersonuuid(personuuid);
//			DAOFactory factory1 = new DAOFactory(conn);
//			factory1.setDAO(orgpersondao);
//			factory1.batchDelete();
//
//			// ɾ��TQ��
//
//			TQUser tu = new TQUser();
//
//			String rm = tu.deleteUser(vo.getTqid().toString(), userid);
//
//			System.out.println("ɾ��TQ����------>" + rm);
//
//			// ɾ���ʼ��ʺ�
//			oaconn = DBConnectionLocator.getInstance().getConnection(
//					Globals.DATASOURCEJNDI);
//			try {
//				MailUserHandler mailhandler = new MailUserHandler(oaconn);
//				mailhandler.deleteUser(personuuid);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new HandlerException(personVo.getUserid() + "ɾ������ʧ�ܣ�����");
//			}
//
//			// ɾ���绰���ʺ�
//			// PhoneInfoDAO pdao = new PhoneInfoDAO();
//			// //pdao.setWhereClause(" useruuid = " + personuuid);
//			// pdao.setUseruuid(personuuid);
//			// //pdao.setConnection(oaconn);
//			// DAOFactory factory1 = new DAOFactory(oaconn);
//			// factory1.setDAO(pdao);
//			// try {
//			// factory1.batchDelete();
//			// } catch (DAOException e) {
//			// e.printStackTrace();
//			// throw new HandlerException(personVo.getUserid()+"ɾ���绰��ʧ�ܣ�����");
//			// }

			result = true;
		} catch (Exception e) {
			throw e;
		} finally {
			if (oaconn != null) {
				try {
					oaconn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;

	}
	
	
	
	
	/**
	 * ɾ��
	 * 
	 * @param personVo
	 * @return
	 * @throws Exception
	 */
	public String DelSyncUser_New(PersonSyncVO personSyncVo) throws Exception {
		String result = "";
		Connection oaconn = null;
		String userid2 = personSyncVo.getUsername().trim();
		
		try {
			String xinhuaid = personSyncVo.getXinhuaid();
			
			
			
			if(xinhuaid==null){
				xinhuaid = "";
			}
			if(xinhuaid.equals("")){
				result="����ͬ����Ϣ��xinhuaidΪ��,"+"ɾ���û���Ϊ"+userid2+"���û���Ϣʧ��!";
				return result;
			}
			
			
			
			DAOFactory factory = new DAOFactory(conn);
			
			PersonDAO persondao = new PersonDAO();
			persondao.setXinhuaid(xinhuaid);
			
			factory.setDAO(persondao);
			List list = null;
			list = factory.find(new PersonVO());
			if(list.isEmpty()) {
				result = "�����Ҳ���xinhuaidΪ"+xinhuaid+"����,"+"ɾ���û���Ϊ"+userid2+"���û���Ϣʧ��";
				return result;
			}
			PersonVO vo = (PersonVO) list.get(0);

			String personuuid = vo.getPersonuuid();
			// ����uuid�ҵ�PersonAccount
			
			PersonAccountDAO dao = new PersonAccountDAO();
			dao.setPersonuuid(personuuid);
			factory.setDAO(dao);
			dao = (PersonAccountDAO) factory.findByPrimaryKey();
			String userid = dao.getUserid();
			//��ɾ����Ա 2009.9.28
			//dao.delete();
			 dao.setDeltag("1");
			 //dao.update(true);
			 dao.delete();
			 
//			// ɾ����֯��ϵ
//			OrgPersonDAO orgpersondao = new OrgPersonDAO();
//			orgpersondao.setPersonuuid(personuuid);
//			DAOFactory factory1 = new DAOFactory(conn);
//			factory1.setDAO(orgpersondao);
//			factory1.batchDelete();
//
//			// ɾ��TQ��
//
//			TQUser tu = new TQUser();
//
//			String rm = tu.deleteUser(vo.getTqid().toString(), userid);
//
//			System.out.println("ɾ��TQ����------>" + rm);
//
//			// ɾ���ʼ��ʺ�
//			oaconn = DBConnectionLocator.getInstance().getConnection(
//					Globals.DATASOURCEJNDI);
//			try {
//				MailUserHandler mailhandler = new MailUserHandler(oaconn);
//				mailhandler.deleteUser(personuuid);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new HandlerException(personVo.getUserid() + "ɾ������ʧ�ܣ�����");
//			}
//
//			// ɾ���绰���ʺ�
//			// PhoneInfoDAO pdao = new PhoneInfoDAO();
//			// //pdao.setWhereClause(" useruuid = " + personuuid);
//			// pdao.setUseruuid(personuuid);
//			// //pdao.setConnection(oaconn);
//			// DAOFactory factory1 = new DAOFactory(oaconn);
//			// factory1.setDAO(pdao);
//			// try {
//			// factory1.batchDelete();
//			// } catch (DAOException e) {
//			// e.printStackTrace();
//			// throw new HandlerException(personVo.getUserid()+"ɾ���绰��ʧ�ܣ�����");
//			// }

			result = "true";
		} catch (Exception e) {
			throw e;
		} finally {
			if (oaconn != null) {
				try {
					oaconn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;

	}
	
	
	
	
	
	
	

	/**
	 * ���ݲ�ͬ�Ĳ������͵�����غ������д���
	 * 
	 * @param personVo
	 * @return
	 */
	public boolean OperateSyncUser(PersonTempVO personVo) throws Exception {
		String operateType = personVo.getOperatetype();
		boolean result = false;
		try {
			if (("new").equals(operateType)) {
				result = NewSyncUser(personVo);

			} else if (("upd").equals(operateType)) {
				result = UpdSyncUser(personVo);

			} else if (("transfer").equals(operateType)) {
				result = UpdSyncUser(personVo);

			} else if (("del").equals(operateType)) {
				result = DelSyncUser(personVo);
			} else {
				return result;
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * ���ݲ�ͬ�Ĳ������͵�����غ������д���
	 * 
	 * @param personVo
	 * @return
	 */
	public String OperateSyncUser_New(PersonSyncVO personSyncVo) throws Exception {
		//operateType addUser���½��û� updUser���޸��û� delUser��ɾ���û� 
		String operateType = personSyncVo.getOperatetype();
		String result = "";
		try{
			if(("addUser").equals(operateType)){
				result = NewSyncUser_New(personSyncVo);
			}else if(("updUser").equals(operateType)){
				result = UpdSyncUser_New(personSyncVo);
			}else if(("delUser").equals(operateType)){
				result = DelSyncUser_New(personSyncVo);
			}else{
				return result;
			}
		}catch(Exception e) {
			throw e;
		}
		return result;
	}
	
	
	

	/**
	 * ����orguuid�õ�����֯�°칫����������Ա
	 * 
	 * @param orguuid
	 * @return
	 */
	public List getPersonByOrg(String orguuid) throws Exception {
		DAOFactory factory = new DAOFactory(conn);
		OrgDAO orgdao = new OrgDAO();
		orgdao.setCnname("�칫��");
		orgdao.setParentorguuid(orguuid);
		factory.setDAO(orgdao);
		OrgVO orgvo = new OrgVO();
		try {
			List orglist = factory.find(orgvo);
			if (orglist != null && orglist.size() > 0) {
				orgvo = (OrgVO) orglist.get(0);
			} else {
				return null;
			}

		} catch (DAOException e) {
			throw e;
		}

		PersonOrgDAO personlistsearchdao = new PersonOrgDAO();
		personlistsearchdao.setOrguuid(orgvo.getOrguuid());
		personlistsearchdao.setIsBelong("1");
		factory.setDAO(personlistsearchdao);
		return factory.find();

	}

	/**
	 * ��������ʱ���е�userid
	 * 
	 * @param operateid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean AlertUserId(String operateid, String userid)
			throws Exception {
		boolean result = false;
		PersonTempDAO dao = new PersonTempDAO();
		// dao.setOperateid(operateid);
		dao.setUserid(userid);
		dao.setIsright(new Integer(1));
		dao.setIsuniq(new Integer(0));
		dao.setWhereClause(" operateid = '" + operateid+"'");
		dao.setConnection(conn);
		try {

			dao.update(false);
			result = true;

		} catch (DAOException e) {
			// System.out.println(e+"fandy");
			throw e;
			// throw new HandlerException(e);
		}

		return result;
	}

	/**
	 * �õ���ʱ�������м�¼
	 * 
	 * @return
	 * @throws HandlerException
	 */
	public List GetAllPerson() throws HandlerException {

		PersonTempDAO dao = new PersonTempDAO();

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			return factory.find(new PersonTempVO());
		} catch (DAOException e) {
			throw new HandlerException(e);
		}

	}

	/**
	 * ���������õ�ͬ���û�����Ϣ�б�
	 * 
	 * @param name
	 * @param userid
	 * @param operatetype
	 * @param audittype
	 * @return
	 * @throws HandlerException
	 */
	public List GetSearchPerson(String name, String userid, String operatetype,
			String audittype) throws HandlerException {
		// List searchList = new ArrayList();
		
		//ԭ���Ĵ���
		 
		/*
		StringBuffer searchsql = new StringBuffer();
		
		StringBuffer sql = new StringBuffer();
		searchsql.append(" SELECT ");
		searchsql.append("OPERATEID,DESCRIBE,ORIGIN,PURPOSE,HRID,PERSONNAME,ORGID,DEPTID,ALTERFIELD,userid,operatetype,isuniq,isright,approved,tohr,totq,tomail,mobilephone,serialindex,id,time   ");
		searchsql.append(" FROM ");
		searchsql.append("PERSON_TEMP");
		searchsql.append(" WHERE HRID IS NOT NULL ");

		// name
		if (!"".equals(name)) {
			searchsql.append(" AND PERSONNAME LIKE '%" + name + "%' ");
		}
		if (!"".equals(userid)) {
			searchsql.append(" AND USERID LIKE '%" + userid + "%' ");
		}
		if (!operatetype.equals("all")) {
			searchsql.append(" AND OPERATETYPE LIKE'%" + operatetype + "%' ");

		}
		if (audittype != 10) {
			searchsql.append(" AND APPROVED='" + audittype + "'");
		}

		searchsql.append(" order by id  ");

		PersonTempSearchDAO dao = new PersonTempSearchDAO();
		DAOFactory factory = new DAOFactory(conn);
		System.out.println(searchsql.toString() + "fandy");
		factory.setDAO(dao);
		dao.setSearchSQL(searchsql.toString());

		try {

			return factory.find(new PersonTempVO());
		} catch (DAOException e) {

			throw new HandlerException(e);

		}
		*/
		
		
		
		 
		StringBuffer sb = new StringBuffer();
		
		StringBuffer sql = new StringBuffer();
		sb.append(" SELECT PERSON_SYNC.id,PERSON_SYNC.operatetype,PERSON_SYNC.xinhuaid,PERSON_SYNC.hrid,PERSON_SYNC.username,PERSON_SYNC.userpwd,PERSON_SYNC.groupid,PERSON_SYNC.gender,PERSON_SYNC.mobilephone,PERSON_SYNC.renyuanxh,PERSON_SYNC.truename,PERSON_SYNC.position,PERSON_SYNC.version,PERSON_SYNC.opratetime,PERSON_SYNC.jsonstring,PERSON_SYNC.approved  ");
		
		sb.append(",PERSON_SYNC.OLD_TRUENAME,PERSON_SYNC.OLD_MOBILEPHONE,PERSON_SYNC.OLD_GENDER,PERSON_SYNC.OLD_GROUPID,PERSON_SYNC.OLD_RENYUANXH,PERSON_SYNC.OLD_POSITION,PERSON_SYNC.OLD_EMAIL ");
		
		sb.append(" ,SYS_PERSON.MOBILE,SYS_PERSON.CNNAME,SYS_PERSON.SEX,SYS_PERSON.ORGCODE ");
		sb.append(" FROM PERSON_SYNC LEFT JOIN (");
		sb.append(" SELECT S.XINHUAID,S.MOBILE,S.CNNAME,S.SEX,U.ORGCODE FROM SYS_PERSON S,SYS_ORGPERSON T,RO_ORG U ");
		sb.append(" WHERE S.DELTAG = '0' AND S.PERSONUUID = T.PERSONUUID(+) AND T.ORGUUID = U.ORGUUID(+) ");
		sb.append(" ) SYS_PERSON ON PERSON_SYNC.XINHUAID = SYS_PERSON.XINHUAID");
		
		
        
		
		
		
		
		sb.append(" WHERE 1 = 1 ");
		
		if(name!=null&&!name.equals("")) {
			sb.append(" AND PERSON_SYNC.TRUENAME LIKE '%" + name + "%' ");
		}
		if(userid!=null&&!userid.equals("")) {
			sb.append(" AND PERSON_SYNC.USERNAME LIKE '%" + userid + "%' ");
		}
		if(operatetype!=null&&!operatetype.equals("")) {
			sb.append(" AND PERSON_SYNC.OPERATETYPE ='" + operatetype + "' ");
		}
		if(audittype != null&&!audittype.equals("")&&!audittype.equals("all")) {
			sb.append(" AND PERSON_SYNC.APPROVED='" + audittype + "'");
		}

		sb.append(" order by PERSON_SYNC.OPRATETIME  ");

		PersonSyncSearchDAO dao = new PersonSyncSearchDAO();
		DAOFactory factory = new DAOFactory(conn); 
		System.out.println(sb.toString() + "fandy");
		factory.setDAO(dao);
		dao.setSearchSQL(sb.toString());

		try {

			return factory.find(new PersonSyncSearchVO());
		} catch (DAOException e) {
			System.out.println("cccccccccccccc:"+e);
			throw new HandlerException(e);

		}
		
		
		

	}

	/**
	 * 
	 * @param orgcode
	 * @return ��֯������
	 */
	public String GetOrgName(String orgcode) throws HandlerException {

		OrgDAO dao = new OrgDAO();
		List list = new ArrayList();
		try {

			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);
			dao.setOrgcode(orgcode);
			OrgVO orgvo = null;
			list = factory.find(new OrgVO());
			if (list == null || list.isEmpty())
				return "NOORG";
			orgvo = (OrgVO) list.get(0);
			return orgvo.getCnname();
		} catch (Exception e) {
			throw new HandlerException(e);
		}

	}
	
	/**
	 * @author ����
	 * @param orgcode
	 * @return ��֯������
	 */
	public String getFullOrgName(String orgCode) throws HandlerException {
		String fullName = "";
		
		SysOrgVO vo = getOrgByOrgCode(orgCode);
		
		if(vo!=null){
			String p_id = vo.getParentorguuid();
			fullName=vo.getCnname();
			while(!p_id.equals("0000000000000000000000000000000000000000000000000000000000000000")){
				vo = getOrgByOrgUuid(p_id);
				p_id = vo.getParentorguuid();
				fullName = vo.getCnname()+"->"+fullName;
			}
		}
		
		return fullName;
	}
	
	
	
	
	
	
	public SysOrgVO getOrgByOrgCode(String orgcode) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO(conn);
		factory.setDAO(dao);
		dao.setOrgcode(orgcode);
		List orgList = null;
		try {
			orgList = factory.find(new SysOrgVO());
			if (orgList != null && orgList.size() == 1) {
				return (SysOrgVO) orgList.get(0);
			} else {
				return null;
			}
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
	
	
	
	public SysOrgVO getOrgByOrgUuid(String orguuid) throws HandlerException {
		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO(conn);
		factory.setDAO(dao);
		dao.setOrguuid(orguuid);
		List orgList = null;
		try {
			orgList = factory.find(new SysOrgVO());
			if (orgList != null && orgList.size() == 1) {
				return (SysOrgVO) orgList.get(0);
			} else {
				return null;
			}
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}
	
	
	
	
	
	

	/**
	 * �ж��û��Ƿ��Ѿ�����
	 * 
	 * @param userid
	 * @return
	 * @throws DAOException
	 */
	public boolean UserIsExist(String userid) throws Exception {
		Connection connection = null;
		boolean result = false;
		try {

			PersonAccountDAO account = new PersonAccountDAO();
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(account);
			account.setUserid(userid);
			List list = factory.find(new PersonAccountVO());
			if (!list.isEmpty()) {
				result = true;
			}
		} catch (Exception e) {
			throw new HandlerException(e);
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (Exception e) {
			}

		}
		return result;

	}

	/**
	 * ����id ȡ��PersonTempVO
	 * 
	 * @param operateid
	 * @return
	 */
	public PersonTempVO getPersonTempVOById(Integer id) {
		PersonTempDAO dao = new PersonTempDAO();
		dao.setId(id);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		PersonTempVO vo = new PersonTempVO();

		try {

			vo = (PersonTempVO) factory.findByPrimaryKey(new PersonTempVO());

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	
	
	/**
	 * ����id ȡ��PersonTempVO
	 * 
	 * @param operateid
	 * @return
	 */
	public PersonSyncVO getPersonSyncVOById(String personUuid) {
		PersonSyncDAO dao = new PersonSyncDAO();
		dao.setId(personUuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		
		
		PersonSyncVO vo = new PersonSyncVO();

		try {

			vo = (PersonSyncVO) factory.findByPrimaryKey(new PersonSyncVO());

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	
	
	/**
	 * �ı������ͨ��״̬
	 * 
	 * @param operateid
	 */
	public void updateFlag(Integer id, Integer i) {

		PersonTempDAO dao = new PersonTempDAO();
		dao.setId(id);
		dao.setApproved(i);
		dao.setConnection(conn);
		try {
			dao.update(true);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * �ı���Աͬ����Ϣ������״̬ APPROVED 0:����� 1:���ͨ�� 2:��˲�ͨ�� 
	 * 
	 * @param operateid
	 */
	public void updPerSyncApprovedStatus(String uuid, String approved) {
		
		PersonSyncDAO dao = new PersonSyncDAO();
		dao.setId(uuid);
		dao.setApproved(approved);
		dao.setConnection(conn);
		try { 
			dao.update(true);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	/**
	 * ����Hridȡ�� Person
	 * 
	 * @param hrid
	 * @return
	 */
	public PersonVO getPersonByHrid(String hrid) {

		PersonDAO dao = new PersonDAO();
		dao.setHrid(hrid);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		try {
			List list = factory.find(new PersonVO());
			if (list != null && list.size() > 0) {
				PersonVO vo = (PersonVO) list.get(0);
				return vo;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * ����uuidȡ�� Person
	 * 
	 * @param hrid
	 * @return
	 */
	public PersonVO getPersonByuuid(String uuid) {

		PersonDAO dao = new PersonDAO();
		dao.setPersonuuid(uuid);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		try {
			List list = factory.find(new PersonVO());
			if (list != null && list.size() > 0) {
				PersonVO vo = (PersonVO) list.get(0);
				return vo;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * ����Deptidȡ��Orguuid
	 * 
	 * @param orgoode
	 * @return
	 * @throws HandlerException
	 */
	public String getOrguuidByDeptid(String orgcode) throws HandlerException {
		String orguuid = "";
		DAOFactory factory = new DAOFactory(conn);

		OrgDAO orgdao = new OrgDAO();
		orgdao.setOrgcode(orgcode);
		orgdao.setDeltag("0");

		factory.setDAO(orgdao);
		List list;
		try {
			list = factory.find(new OrgVO());
			if (list.isEmpty()) {
				throw new HandlerException("�Ҳ�����֯����Ϊ" + orgcode + "����֯");
			} else {
				OrgVO vo = (OrgVO) list.get(0);
				orguuid = vo.getOrguuid();
			}

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orguuid;
	}

	/**
	 * �������µ�XML
	 * 
	 * @param hrid
	 * @param username
	 * @return
	 */
	public String getHrXML(String type, String hrid, String username,
			String uuid, String orgid, String deptid) {

		Document doc = null;
		doc = DocumentHelper.createDocument();
		Element root = doc.addElement("USERS");
		Element user = root.addElement("USER");
		user.addElement("TYPE").setText(type);
		user.addElement("SORT").setText("personal");
		if (hrid != null) user.addElement("HRID").setText(hrid);
		user.addElement("USERNAME").setText(username);
		user.addElement("ORGID").setText(orgid);
		user.addElement("DEPTID").setText(deptid);
		user.addElement("UUID").setText(uuid);

		return doc.asXML();
	}

	/**
	 * �������µ�XML
	 * 
	 * @param hrid
	 * @param username
	 * @return
	 */
	public String getHrXML(String type, String username, String orgid,
			String deptid, String uuid) {

		Document doc = null;
		doc = DocumentHelper.createDocument();
		Element root = doc.addElement("USERS");
		Element user = root.addElement("USER");
		user.addElement("TYPE").setText(type);
		user.addElement("SORT").setText("common");
		user.addElement("HRID").setText("");
		user.addElement("USERNAME").setText(username);
		user.addElement("ORGID").setText(orgid);
		user.addElement("DEPTID").setText(deptid);
		user.addElement("UUID").setText(uuid);

		return doc.asXML();
	}

	/**
	 * ����ORGUUID ȡ��ORGVO
	 * 
	 * @param orguuid
	 * @return
	 */

	public OrgVO getOrgCode(String orguuid) {
		OrgDAO dao = new OrgDAO();
		dao.setOrguuid(orguuid);
		dao.setDeltag("0");

		DAOFactory factory = new DAOFactory(conn);

		factory.setDAO(dao);
		OrgVO vo = null;
		try {
			List list = factory.find(new OrgVO());
			if (!list.isEmpty()) {
				vo = (OrgVO) list.get(0);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * 
	 * @return
	 * @throws HandlerException
	 */

	public PersonAccountVO getPersonAccount(String personuuid) {

		PersonAccountDAO dao = new PersonAccountDAO();
		dao.setPersonuuid(personuuid);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		try {
			List list = factory.find(new PersonAccountVO());
			if (list != null && list.size() > 0) {
				PersonAccountVO vo = (PersonAccountVO) list.get(0);
				return vo;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	
	
	//����personuuid���Ҹ���Ա������λ��orgCode
	public String getOrgCodeBypersonuuid(String id) {
		
		String orgCode = null;
		DAOFactory factory = new DAOFactory(conn);
		SysOrgpersonDAO opDao = new SysOrgpersonDAO();
		opDao.setPersonuuid(id);
		factory.setDAO(opDao);
		SysOrgpersonVO opVO = new SysOrgpersonVO();
		List list = new ArrayList();
		String orguuid = null;
		try {
			list = (List) factory.find(new SysOrgpersonVO());
			if (list.size() != 0) {
					Iterator iter = list.iterator();
					while (iter.hasNext()) {
						opVO = (SysOrgpersonVO) iter.next();
						if (opVO.getIsbelong().equals("1")) {
							orguuid = opVO.getOrguuid();
							break;
						}
					}

			}
		
			if(orguuid!=null){ 
					factory = new DAOFactory(conn);
					SysOrgDAO dao = new SysOrgDAO();
					factory.setDAO(dao);
					dao.setOrguuid(orguuid);
	
					list = factory.find(new SysOrgVO());
					if (!list.isEmpty()) {
	
						SysOrgVO vo = (SysOrgVO) list.get(0);
						orgCode = vo.getOrgcode();
					}
					return orgCode;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orguuid;
	}
	
	
}

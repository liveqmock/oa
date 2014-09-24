package com.icss.oa.sync.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.util.UUID;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.filetransfer.dao.SysOrgDAO;
import com.icss.oa.filetransfer.vo.SysOrgVO;
import com.icss.oa.sync.dao.OrgSyncDAO;
import com.icss.oa.sync.dao.OrgSyncSearchDAO;
import com.icss.oa.sync.dao.OrgTempDAO;
import com.icss.oa.sync.dao.PersonSyncDAO;
import com.icss.oa.sync.dao.PersonSyncSearchDAO;
import com.icss.oa.sync.dao.SysOrgShortcutDAO;
import com.icss.oa.sync.vo.OrgSyncSearchVO;
import com.icss.oa.sync.vo.OrgSyncVO;
import com.icss.oa.sync.vo.OrgTempVO;
import com.icss.oa.sync.vo.PersonSyncSearchVO;
import com.icss.oa.sync.vo.PersonSyncVO;
import com.icss.resourceone.common.login.dao.OrgPersonDAO;
import com.icss.resourceone.org.dao.OrgDAO;
import com.icss.resourceone.org.model.OrgVO;

public class OrgSyncHandler {
	private Connection conn;

	public OrgSyncHandler() {
	}

	public OrgSyncHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * ȡ��ͬ����֯�б�
	 * 
	 * @param type
	 * @param pass
	 * 
	 * @return
	 */
	public List getSyncOrg(String orgname,String orgcode,String Otype,String Atype) throws HandlerException {
		
		/*
		List list = new ArrayList();
		OrgTempDAO dao = new OrgTempDAO();
		if (pass != 2) {
			dao.setPass(pass);
		}
		if (!type.equalsIgnoreCase("all")) {
			dao.setType(type);
		}
		dao.addOrderBy("time", true);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);


		try {
			list = factory.find(new OrgTempVO());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;*/
		
		
		//�޸ĺ�Ĵ���
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ORG_SYNC.ID,ORG_SYNC.OPERATETYPE,ORG_SYNC.GROUPCODE,");
		sb.append("ORG_SYNC.PARENTGROUPCODE,ORG_SYNC.GROUPNAME,ORG_SYNC.CONTACT,ORG_SYNC.SERIALINDEX,");
		sb.append("ORG_SYNC.MEMO,ORG_SYNC.OLD_GROUPCODE,ORG_SYNC.OLD_PARENTGROUPCODE,ORG_SYNC.OLD_GROUPNAME,");
		sb.append("ORG_SYNC.OLD_CONTACT,ORG_SYNC.OLD_SERIALINDEX,ORG_SYNC.OLD_MEMO,ORG_SYNC.JSONSTRING,ORG_SYNC.OPRATETIME,ORG_SYNC.APPROVED, ");
		sb.append("SYS_ORG.ORGCODE2,SYS_ORG.CNNAME2,SYS_ORG.CONTACT2,SYS_ORG.SERIALINDEX2,SYS_ORG.MEMO2 ");
		
		
		sb.append("FROM ORG_SYNC LEFT JOIN (SELECT S.ORGCODE ORGCODE2,S.CNNAME CNNAME2,S.CONTACT CONTACT2,S.SERIALINDEX SERIALINDEX2,S.MEMO MEMO2 FROM SYS_ORG S WHERE S.DELTAG='0') SYS_ORG ON ORG_SYNC.GROUPCODE = SYS_ORG.ORGCODE2 ");
		sb.append(" WHERE 1 = 1 ");
		
		
		if(orgname!=null&&!orgname.equals("")) {
			sb.append(" AND ORG_SYNC.GROUPNAME LIKE '%" + orgname + "%' ");
		}
		if(orgcode!=null&&!orgcode.equals("")) {
			sb.append(" AND ORG_SYNC.GROUPCODE LIKE '%" + orgcode + "%' ");
		}
		if(Otype!=null&&!Otype.equals("")) {
			sb.append(" AND ORG_SYNC.OPERATETYPE ='" + Otype + "' ");
		}
		if(Atype != null&&!Atype.equals("")&&!Atype.equals("all")) {
			sb.append(" AND ORG_SYNC.APPROVED='" + Atype + "'");
		}
		
		
		sb.append(" ORDER BY ORG_SYNC.OPRATETIME  ");

		OrgSyncSearchDAO dao = new OrgSyncSearchDAO();
		DAOFactory factory = new DAOFactory(conn); 
		System.out.println(sb.toString() + "fandy");
		factory.setDAO(dao);
		dao.setSearchSQL(sb.toString());
		
		try {
			return factory.find(new OrgSyncSearchVO());
		} catch (DAOException e) {
			System.out.println("cccccccccccccc:"+e);
			throw new HandlerException(e);
		}
	}

	/**
	 * ȡ����֯��
	 * 
	 * @param orgcode
	 * @return
	 */

	public String getOrgName(String orgcode) {
		Integer orglevel = new Integer(3);
		String position = null;

		try {
			OrgDAO dao = new OrgDAO();
			dao.setOrgcode(orgcode);
			dao.setDeltag("0");
			dao.setStatus(0);

			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(dao);

			List list = factory.find(new OrgVO());
			if (!list.isEmpty()) {
				OrgVO vo = (OrgVO) list.get(0);
				orglevel = vo.getOrglevel();

				if (orglevel.intValue() == 3 || orglevel.intValue() == 0
						|| orglevel.intValue() == 1 || orglevel.intValue() == 2) {
					position = vo.getCnname();
				}

				if (orglevel.intValue() > 3) {
					String parentOrgName = this.getOrgNameByuuid(vo
							.getParentorguuid());
					position = parentOrgName + "->" + vo.getCnname();
				}
			}

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			position = "δ֪��λ";
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return position;

	}

	/**
	 * ���ͨ�� ORG
	 * 
	 * @param id
	 */
	public boolean passOrg(String id) {
		boolean flag = false;

		OrgTempDAO dao = new OrgTempDAO();
		dao.setId(id);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		try {
			List list = factory.find(new OrgTempVO());
			OrgTempVO vo = (OrgTempVO) list.get(0);

			OrgDAO roorg = new OrgDAO();
			roorg.setConnection(conn);

			String type = vo.getType();
			// �½�
			if (type.equals("new")) {

				String uuid = (new UUID()).toString();
				System.out.println("####�½����� uuid =" + uuid);

				OrgVO orgvo = this.getOrgVO(vo.getParentorgid());
				roorg.setOrguuid(uuid);
				roorg.setCnname(vo.getOrgname());
				roorg.setEnname(vo.getOrgcode());
				roorg.setOrgcode(vo.getOrgcode());
				//����ͬ
				String s = vo.getSerialindex();
				roorg.setSerialindex(new Integer(vo.getSerialindex()));
				roorg.setSequenceno(new Integer(vo.getSerialindex()));
				roorg.setStatus(new Integer(0));
				roorg.setDeltag("0");
				roorg.setParentorguuid(orgvo.getOrguuid());
				roorg.setOrglevel(orgvo.getOrglevel() + 1);
				roorg.setOrglevelcode(this.getOrgLevelCode(orgvo.getOrguuid(),
						orgvo.getOrglevelcode()));

				SysOrgShortcutDAO dao1 = new SysOrgShortcutDAO();
				dao1.setConnection(conn);
				dao1.setSysid(23);
				dao1.setOrguuid(uuid);
				dao1.create();

				roorg.create();
				flag = true;
			}

			// ����
			else if (type.equals("upd")) {
				if (vo.getUpdcontent().equalsIgnoreCase("ORGNAME")) {
					roorg.setOrguuid(this.getUuid(vo.getOrgcode()));
					roorg.setCnname(vo.getNewmsg());
//					roorg.setOrgcode(vo.getOrgcode());
//					roorg.setParentorguuid(this.getUuid(vo.getParentorgid()));
//					roorg.setSerialindex(new Integer(vo.getSerialindex()));
					roorg.update(true);
					flag = true;
				} else if (vo.getUpdcontent().equalsIgnoreCase("serialindex")) {
					roorg.setOrguuid(this.getUuid(vo.getOrgcode()));
					roorg.setSerialindex(new Integer(vo.getNewmsg()));
					roorg.setSequenceno(new Integer(vo.getNewmsg()));
					roorg.update(true);
					flag = true;
				} else if (vo.getUpdcontent().equalsIgnoreCase("ORGCODE")) {
					roorg.setOrguuid(this.getUuid(vo.getOrgcode()));
					roorg.setOrgcode(vo.getNewmsg());
//					roorg.setSerialindex(new Integer(vo.getSerialindex()));
					roorg.update(true);
					flag = true;
				}  
				else {
					flag = false;
				}
			}
			// ɾ��
			else if (type.equals("del")) {
				roorg.setOrguuid(this.getUuid(vo.getOrgcode()));
				roorg.setStatus(2);
				roorg.setDeltag("1");
				roorg.update(true);
				flag = true;

			}
			// �ϲ�
			else if (type.equals("unite")) {

				String olduuid = this.getUuid(vo.getOldmsg());
				String newuuid = this.getUuid(vo.getNewmsg());

				// ת����Ա��ϵ
				OrgPersonDAO dao1 = new OrgPersonDAO();
				dao1.setConnection(conn);
				dao1.setOrguuid(newuuid);
				dao1.setWhereClause(" orguuid = '" + olduuid + "'");
				dao1.update(false);

				// ɾ��ԭ����֯
				roorg.setOrguuid(olduuid);
				roorg.setStatus(2);
				roorg.setDeltag("1");
				roorg.update(true);
				flag = true;

			}
			// ��ת
			else if (type.equals("transit")) {

				roorg.setOrguuid(this.getUuid(vo.getOldmsg()));
				roorg.setOrgcode(vo.getNewmsg());
				roorg.setEnname(vo.getNewmsg());
				roorg.update(true);
				flag = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	/**
	 * author:zhouyi
	 * �µ���֯����ͨ������
	 * 
	 * @param id 
	 */
	public String passOrg_New(OrgSyncVO syncvo) {
		String flag = "";
		OrgDAO roorg = new OrgDAO(); 
		roorg.setConnection(conn);
		  
		//��������
		String type = syncvo.getOperatetype();
		try{
			//�½�����
			if(type.equals("addGroup")){
					String uuid = (new UUID()).toString();
					System.out.println("####�½����� uuid =" + uuid);
					
					//�ж�ͬ����Ϣ��groupCode�Ƿ�Ϊ��
					String t_groupCode = "";
					t_groupCode = syncvo.getGroupcode();
					if(t_groupCode==null){
						t_groupCode = "";
					}
					if(t_groupCode.equals("")){
						flag = "����ͬ����Ϣ����֯����Ϊ��,������֯ʧ��!";
						return flag;
					}
					
					
					//�ж�ͬ����Ϣ��getGroupname�Ƿ�Ϊ��
					String t_cnname = syncvo.getGroupname();
					if(t_cnname==null){
						t_cnname = "";
					}
					if(t_cnname.equals("")){
						flag = "����ͬ����Ϣ����֯����Ϊ��,������֯ʧ��!";
						return flag;
					}
					
					//�����֯�����Ƿ��Ѵ���
					String orgName = new IdsSyncHandler().getOrgName(t_groupCode);
					if (orgName != null && !"".equals(orgName)) 
					return "������֯����Ϊ" + t_groupCode + "����֯�Ѵ��ڣ�������֯ʧ�ܣ�";
					
					//�õ���
					OrgVO parentOrgVo = this.getOrgVO(syncvo.getParentgroupcode());
					if(parentOrgVo!=null&&parentOrgVo.getOrguuid()!=null&&!parentOrgVo.getOrguuid().equals("")){
						roorg.setOrguuid(uuid);
						roorg.setCnname(syncvo.getGroupname());
						roorg.setEnname(syncvo.getGroupname());
						roorg.setOrgcode(syncvo.getGroupcode());
						roorg.setContact(syncvo.getContact());
						roorg.setMemo(syncvo.getMemo());
						
						//����ͬ
						String s = syncvo.getSerialindex();
						
						roorg.setSerialindex(new Integer(syncvo.getSerialindex()));
						roorg.setSequenceno(new Integer(syncvo.getSerialindex()));
						roorg.setStatus(new Integer(0));
						roorg.setDeltag("0");
						roorg.setParentorguuid(parentOrgVo.getOrguuid());
						roorg.setOrglevel(parentOrgVo.getOrglevel() + 1);
						roorg.setOrglevelcode(this.getOrgLevelCode(parentOrgVo.getOrguuid(),parentOrgVo.getOrglevelcode()));
			
						SysOrgShortcutDAO dao1 = new SysOrgShortcutDAO();
						dao1.setConnection(conn);
						dao1.setSysid(23);
						dao1.setOrguuid(uuid);
						dao1.create();
						roorg.create();
						flag = "true";
					}else{
						flag="��OA���Ҳ��� "+syncvo.getGroupname()+" ���ϲ���֯,������֯ʧ��!";
						return flag;
					}
				
			}else if(type.equals("updGroup")){// ����
				//�ж�ͬ����Ϣ��groupCode�Ƿ�Ϊ��
				String t_groupCode = "";
				t_groupCode = syncvo.getGroupcode();
				if(t_groupCode==null){
					t_groupCode = "";
				}
				if(t_groupCode.equals("")){
					flag = "����ͬ����Ϣ����֯����Ϊ��,�޸���֯��Ϣʧ��!";
					return flag;
				}
				 
				
				//�ж�ͬ����Ϣ��getGroupname�Ƿ�Ϊ��
				String t_cnname = syncvo.getGroupname();
				if(t_cnname==null){
					t_cnname = "";
				}
				if(t_cnname.equals("")){
					flag = "����ͬ����Ϣ����֯����Ϊ��,�޸���֯��Ϣʧ��!";
					return flag;
				}
				
				//���ȸ���orgCode�ҵ���ʽ���еļ�¼
				OrgVO orgvo = getOrgVO(syncvo.getGroupcode());
				
				//�������ʽ�����ҵ���ƥ��ļ�¼
				if(orgvo!=null&&orgvo.getOrguuid()!=null&&!orgvo.getOrguuid().equals("")){
					roorg.setOrguuid(orgvo.getOrguuid());
					roorg.setCnname(syncvo.getGroupname());
					roorg.setOrgcode(syncvo.getGroupcode());
					roorg.setContact(syncvo.getContact());
					roorg.setSerialindex(new Integer(syncvo.getSerialindex()).intValue());
					roorg.setSequenceno(new Integer(syncvo.getSerialindex()).intValue());
					roorg.setMemo(syncvo.getMemo());
					roorg.update(true);
					flag = "true";
				}else{
					flag = "��OA���Ҳ�����֯����Ϊ"+syncvo.getGroupcode()+"����֯,�޸���֯��Ϣʧ��!";
					return flag;
				}
					
			}else if(type.equals("delGroup")){//ɾ��
				String orgCode = syncvo.getGroupcode();
				if(orgCode!=null&&!orgCode.equals("")){
					//���ȸ���orgCode�ҵ���ʽ���еļ�¼
					OrgVO orgvo = getOrgVO(orgCode);
					
					//�������ʽ�����ҵ���ƥ��ļ�¼
					if(orgvo!=null&&orgvo.getOrguuid()!=null&&!orgvo.getOrguuid().equals("")){
						roorg.setOrguuid(orgvo.getOrguuid());
						roorg.setStatus(2);
						roorg.setDeltag("1");
						roorg.update(true);
						flag = "true";
					}else{
						flag = "��OA���Ҳ�����֯����Ϊ"+orgCode+"����֯,ɾ����֯��Ϣʧ��!";
						return flag;
					}
				}else{
					flag = "����ͬ����Ϣ����֯����Ϊ��,ɾ����֯��Ϣʧ��!";
					return flag;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//flag="ͬ��ʧ��!";
			//return flag;
		}
		return flag;
	}
	
	
	 
	
	
	

	/**
	 * ����ORGCODEΪorgcode����֯��Uuid����û�и���֯���򷵻�"NOTHISORG"
	 */
	public String getUuid(String orgcode) throws Exception {
		String result = "NOTHISORG";
		OrgDAO org = new OrgDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(org);

		org.setOrgcode(orgcode);
		org.setDeltag("0");
		org.setStatus(0);

		List list = factory.find(new OrgVO());
		if (!list.isEmpty()) {
			OrgVO orgvo = (OrgVO) list.get(0);
			return orgvo.getOrguuid();

		}
		return result;

	}

	public String getOrgNameByuuid(String uuid) throws HandlerException {

		DAOFactory factory = new DAOFactory(conn);
		SysOrgDAO dao = new SysOrgDAO();
		factory.setDAO(dao);
		dao.setOrguuid(uuid);
		String name = "";

		try {
			List list = factory.find(new SysOrgVO());
			if (!list.isEmpty()) {

				SysOrgVO vo = (SysOrgVO) list.get(0);
				name = vo.getCnname();
			}
			return name;
		} catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * ȡ�� OrgVO
	 * 
	 * @param orgcode
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public OrgVO getOrgVO(String orgcode) throws Exception {
		OrgVO orgvo = new OrgVO();

		OrgDAO dao = new OrgDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		dao.setOrgcode(orgcode);
		List list = factory.find(new OrgVO());
		if (!list.isEmpty()) {
			return (OrgVO) list.get(0);

		}

		return orgvo;

	}

	/**
	 * ���LevelCode
	 * 
	 * @param parentorguuid
	 * @param parentlevelcode
	 * @return
	 * @throws DAOException
	 */
	private String getOrgLevelCode(String parentorguuid, String parentlevelcode)
			throws DAOException {

		DAOFactory daofactory = new DAOFactory(conn);

		OrgDAO dao = new OrgDAO();
		dao.setParentorguuid(parentorguuid);
		dao.setDeltag("0");
		daofactory.setDAO(dao);
		List list = daofactory.find();

		int i = 0;
		if (list == null) {
			return (parentlevelcode + getCode(1));
		} else {
			for (int j = 0; j < list.size(); j++) {
				OrgDAO orgdao2 = (OrgDAO) list.get(j);
				String s2 = orgdao2.getOrglevelcode();
				if (s2 == null || s2.length() < 4)
					continue;
				int k = (new Integer(s2.substring(s2.length() - 4))).intValue();
				if (k > i)
					i = k;
			}

			return (parentlevelcode + getCode(i + 1));
		}

	}

	private String getCode(int i) {
		String s = "";
		if (i < 10000 && i >= 1000)
			s = String.valueOf(i);
		else if (i >= 100)
			s = "0" + String.valueOf(i);
		else if (i >= 10)
			s = "00" + String.valueOf(i);
		else
			s = "000" + String.valueOf(i);
		return s;
	}

	/**
	 * ͬ���ɹ�
	 * 
	 * @param id
	 * @throws DAOException
	 */

	public void updflag(String id,Integer i) throws DAOException {

		OrgTempDAO dao = new OrgTempDAO();
		dao.setConnection(conn);
		dao.setId(id);
		dao.setPass(i);
		dao.update(true);
	}
	
	
	
	
	/**
	 * author:zhouyi
	 * �ı���֯ͬ����Ϣ������״̬ APPROVED 0:����� 1:���ͨ�� 2:��˲�ͨ�� 
	 * 
	 * @param operateid
	 */
	public void updOrgSyncApprovedStatus(String uuid, String approved) {
		
		OrgSyncDAO dao = new OrgSyncDAO();
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
	 * ����ORG_SYNC���uuid�ֶλ����֯ͬ�����һ����¼
	 * author:zhouyi
	 * @param orgcode
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public OrgSyncVO getOrgSyncVOById(String uuid) throws Exception {
		

		OrgSyncDAO dao = new OrgSyncDAO();
		dao.setId(uuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		
		 
		OrgSyncVO vo = new OrgSyncVO();

		try {

			vo = (OrgSyncVO) factory.findByPrimaryKey(new OrgSyncVO());

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	
	
	
}
package com.icss.oa.sync.servlet;
 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;


import com.icss.common.log.ConnLog;
import com.icss.core.base.ServletBase;
import com.icss.core.db.DBConnectionProvider;
import com.icss.core.db.DataBaseExecutor;
import com.icss.core.db.PagingInfo;
import com.icss.core.db.Record;
import com.icss.core.db.RecordSet;
import com.icss.core.web.RequestKit;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.MailUserHandler;
import com.icss.oa.sync.dao.OrgSyncDAO;
import com.icss.oa.sync.dao.PersonDAO;
import com.icss.oa.sync.dao.PersonSyncDAO;
import com.icss.oa.sync.handler.IdsSyncHandler; 
import com.icss.oa.sync.handler.OrgSyncHandler;
import com.icss.oa.sync.handler.UserSyncHandler;
import com.icss.oa.sync.ids.syncHandler;
import com.icss.oa.sync.vo.OrgSyncVO;
import com.icss.oa.sync.vo.PersonSyncVO;
import com.icss.oa.sync.vo.PersonVO;
import com.icss.resourceone.org.model.OrgVO;


public class IdsSyncServlet extends ServletBase
{
	/**
	 * ��Աͬ���б�
	 * @author ����
	 * @param orgcode
	 * @return 
	 */
	public void syncPersonList(HttpServletRequest request,HttpServletResponse response)
	{
		RequestKit kit = (RequestKit) request.getAttribute("RequestKit");
		String pageSize = kit.getParameter("pageSize", "10");
		PagingInfo pagingInfo = new PagingInfo(request, new Integer(pageSize).intValue(),"^(?!.*(result)|(LTPAToken)).*$");
		Record seaRecord = kit.getRecordWithPrefix("SEA_");
        try {
        	IdsSyncHandler handler = new IdsSyncHandler();
    		RecordSet syncPersonList = new RecordSet();
    		syncPersonList.add(handler.syncPersonList(kit, pagingInfo));
    		System.out.println("vvvvvvvvvv     ----- syncPersonList.size():"+syncPersonList.size());
    		
    		request.setAttribute("pagingInfo", pagingInfo);
    		request.setAttribute("seaRecord", seaRecord);
    		request.setAttribute("syncPersonList", syncPersonList);
			forward("/syncperson/idspersonsync.jsp");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * ��֯ͬ���б�
	 * @author ����
	 * @param orgcode
	 * @return 
	 */
	public void syncOrgList(HttpServletRequest request,HttpServletResponse response)
	{
		RequestKit kit = (RequestKit) request.getAttribute("RequestKit");
		
		String pageSize = kit.getParameter("pageSize", "10");
		PagingInfo pagingInfo = new PagingInfo(request, new Integer(pageSize).intValue(),"^(?!.*(result)|(LTPAToken)).*$");
		
		Record seaRecord = kit.getRecordWithPrefix("SEA_");
        try {
        	IdsSyncHandler handler = new IdsSyncHandler();
    		RecordSet syncOrgList = new RecordSet();
    		syncOrgList.add(handler.syncOrgList(kit, pagingInfo));
    		
    		request.setAttribute("pagingInfo", pagingInfo);
    		request.setAttribute("seaRecord", seaRecord);
    		request.setAttribute("syncOrgList", syncOrgList);
			forward("/syncperson/idsorgsync.jsp");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * ��Աͬ������
	 * @author ����
	 * @param orgcode
	 * @return 
	 */
	public void personSyncAudit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		//PrintWriter out = response.getWriter();
		String JNDI = "jdbc/ROEEE"; 
		int i;
		String type = request.getParameter("type");
		
		String returnStr = "";
		
		try{
			conn = DBConnectionProvider.getConnection(JNDI);
			ConnLog.open("AudiServlet");
            
			UserSyncHandler handler = new UserSyncHandler(conn);
			String[] syncuser = request.getParameterValues("syncuser");
			
			
			//�����������ͨ��
			if(type != null && "no".equals(type)){
				for (int j = 0; j < syncuser.length; j++) {
					String uuid = syncuser[j];
					PersonSyncVO personSyncVo = handler.getPersonSyncVOById(uuid);
					String operateType = personSyncVo.getOperatetype();
					//������޸��û����ͣ���Ŀǰ��ʽ������Ա�������Ϣ��������(���浽person_sync����)���Ա��Ժ���ĸ��ļ�¼
					if(operateType!=null&&("updUser").equals(operateType)){
							//����xinhuaid�ҳ���ʽ���еĸ���Ա
							PersonVO personVO = null; 
							DAOFactory factory = new DAOFactory(conn);
							PersonDAO dao = new PersonDAO(conn);
							factory.setDAO(dao);
							
							String t_xinhuaid = personSyncVo.getXinhuaid();
							if(t_xinhuaid==null){
								t_xinhuaid = "";
							}
							
							if(!t_xinhuaid.equals("")){
								dao.setXinhuaid(t_xinhuaid);//xinhuaid 
								List personList = null;
								personList = factory.find(new PersonVO());
								if(personList != null && personList.size() == 1) {
									personVO = (PersonVO) personList.get(0);
								}
								
								//�ȶ���ʽ���еļ�¼,��Ŀǰ��ʽ������Ա�������Ϣ��������(���浽person_sync����)���Ա��Ժ���ĸ��ļ�¼
								if(personVO!=null&&personVO.getPersonuuid()!=null&&!(personVO.getPersonuuid()).equals("")){//�����ʽ�����ҵ��˸���Ա
									PersonSyncDAO personSyncDAO = new PersonSyncDAO();
									personSyncDAO.setConnection(conn); 
									personSyncDAO.setValueObject(personSyncVo);
									personSyncDAO.setApproved("2");//2Ϊ��ͨ��
									
									personSyncDAO.setOld_truename(personVO.getCnname());
									personSyncDAO.setOld_mobilephone(personVO.getMobile());
									personSyncDAO.setOld_gender(new Integer(personVO.getSex()).toString());
									String old_orgcode = handler.getOrgCodeBypersonuuid(personVO.getPersonuuid());
									personSyncDAO.setOld_groupid(old_orgcode);
									personSyncDAO.setOld_email(personVO.getEmail1());
									
									personSyncDAO.update();
								}else{//�����ʽ�����Ҳ���ƥ�����Ա��ֱ��������ͨ��
									handler.updPerSyncApprovedStatus(uuid,"2");
								}
							}else{//�����ʽ�����Ҳ���ƥ�����Ա��ֱ��������ͨ��
								handler.updPerSyncApprovedStatus(uuid,"2");
							}
					}else{//��������ӻ�ɾ���û�����û��Ҫ��¼��ʷ��ֵ��ֱ�ӱ�ǳ�δͨ��
						handler.updPerSyncApprovedStatus(uuid,"2");
					}
				}
			}else{//���������ͨ������
					for(i=0;i<syncuser.length;i++){
							String personUuid = syncuser[i]; 
							PersonSyncVO personSyncVo = handler.getPersonSyncVOById(personUuid);
							String flag = "false";
							
							
							//�����������Ϊ����,�ȼ���Ŀǰ��ʽ���и��û���ֵ
							//����xinhuaid�����ʽ���е�PersonVO
							PersonVO personVO = null; 
							String operateType = personSyncVo.getOperatetype();
							String old_orgcode="";
							if(operateType!=null&&("updUser").equals(operateType)){
								DAOFactory factory = new DAOFactory(conn);
								PersonDAO dao = new PersonDAO(conn);
								factory.setDAO(dao);
								
								String t_xinhuaid = personSyncVo.getXinhuaid();
								if(t_xinhuaid==null){
									t_xinhuaid = "";
								}
								//ȷ��xinhuaid��Ϊ��
								if(!t_xinhuaid.equals("")){
									dao.setXinhuaid(personSyncVo.getXinhuaid());//xinhuaid
									List personList = null;
									personList = factory.find(new PersonVO());
									if(personList != null && personList.size() == 1) {
										personVO = (PersonVO) personList.get(0);
									}
									if(personVO!=null&&personVO.getPersonuuid()!=null&&!(personVO.getPersonuuid()).equals("")){//����ҵ�ƥ�����Ա
										old_orgcode = handler.getOrgCodeBypersonuuid(personVO.getPersonuuid());
									}
								}
							}
							
							//ִ��ͬ������
							flag = handler.OperateSyncUser_New(personSyncVo);
							
							if(!flag.equals("true")){
								returnStr = returnStr+flag+"\n";
							}
							
							if(flag.equals("true")){
								
								//������޸��û����ͣ���Ŀǰ��ʽ������Ա�������Ϣ��������(���浽person_sync����)���Ա��Ժ���ĸ��ļ�¼
								if(operateType!=null&&("updUser").equals(operateType)){
									//�ȶ���ʽ���еļ�¼,
									if(personVO!=null&&personVO.getPersonuuid()!=null&&!(personVO.getPersonuuid()).equals("")){//����ҵ�ƥ�����Ա
										PersonSyncDAO personSyncDAO = new PersonSyncDAO(); 
										personSyncDAO.setConnection(conn); 
										personSyncDAO.setValueObject(personSyncVo);
										personSyncDAO.setApproved("1");//1Ϊͨ��
										
										personSyncDAO.setOld_truename(personVO.getCnname());
										personSyncDAO.setOld_mobilephone(personVO.getMobile());
										personSyncDAO.setOld_gender(new Integer(personVO.getSex()).toString());
										personSyncDAO.setOld_groupid(old_orgcode);
										personSyncDAO.setOld_email(personVO.getEmail1());
										
										
										personSyncDAO.update();
									}else{//����Ҳ���ƥ�����Ա��ֱ������Ϊͨ��״̬
										handler.updPerSyncApprovedStatus(personUuid,"1");
									}
								}else{//��������ӻ�ɾ���û�����û��Ҫ��¼��ʷ��ֵ��ֱ�ӱ�ǳ���ͨ��
									handler.updPerSyncApprovedStatus(personUuid,"1");
								}
						  }
					}
			}
			
			if(returnStr.equals("")){
				returnStr = "�����ɹ�!";
			}
			
			appendString(returnStr);
			//response.sendRedirect("/oabase/servlet/IdsSyncServlet?method=syncPersonList");
		}catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println(e);
		}finally{
			if(conn != null)
			{
				try{
					conn.close();
					ConnLog.close("AudiServlet");
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	
	/**
	 * ��Աͬ������
	 * @author ����
	 * @param orgcode
	 * @return 
	 */ 
	public void createMail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	
		String userid = "sunxiaoli"; 
		String uuid = "38bb38bb-11b34193b50-1a426a6cdb8e100f49f6a31924b1b927";
		String orguuid = "13561356-126b5c9de81-837f84975ee09664d890917866b9261b";
		 
		 
		
		Connection oaconn;
		try { 
			oaconn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			MailUserHandler mailhandler = new MailUserHandler(oaconn); 
			   
			mailhandler.createUser(userid, uuid, orguuid); 
		} catch (DBConnectionLocatorException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	
	}
	
	
	
	
	
	
	/**
	 * ��Աͬ��У��
	 * @author ����
	 * @param orgcode
	 * @return 
	 */
	public void personSyncPriorCheck(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String JNDI = "jdbc/ROEEE"; 
		String persons[] = request.getParameterValues("userInfos");
		
		String personNums[] = new String[persons.length];
		String ids[] = new String[persons.length];
		String xinhuaids[] = new String[persons.length];
		String personNames[] = new String[persons.length];
		String personOrgs[] = new String[persons.length];
		String orgCodes[] = new String[persons.length];
		String optTimes[] = new String[persons.length];
		
		
		for(int i=0;i<persons.length;i++){
			String perStr = persons[i];
			perStr = new String(perStr.getBytes("GBK"),"UTF-8");
			String perObj[] = perStr.split("@");
			personNums[i] = perObj[0];
			ids[i] = perObj[1];
			xinhuaids[i] = perObj[2];
			personNames[i] = perObj[3];
			personOrgs[i] = perObj[4];
			orgCodes[i] = perObj[6];
			optTimes[i] = perObj[7];
		}
		
		
		try{
			
			conn = DBConnectionLocator.getInstance().getConnection(JNDI);
			
			JSONArray array = new JSONArray();
			
			
			//�����ͬһ�����ж�����Ĳ�����Ӧ�ð�ʱ����Ⱥ�˳������ͨ����
			for(int i=0;i<xinhuaids.length;i++){
				String xinhuaid = xinhuaids[i];
				String optTime = optTimes[i];
				String personNum = personNums[i];
				String orgCode = orgCodes[i];
				String orgName = personOrgs[i];
				
				PersonSyncDAO personSyncDAO = new PersonSyncDAO();
				StringBuffer sql = new StringBuffer();
				sql.append(" XINHUAID ='").append(xinhuaid).append("' ");
				sql.append(" AND APPROVED = '0' AND OPRATETIME<'").append(optTime).append("' ");
				
				for(int t=0;t<ids.length;t++){
					if(t==0){
						sql.append(" AND ID NOT IN (");
					}
					sql.append("'").append(ids[t]).append("'");
					
					if(t!=ids.length-1){
						sql.append(",");
					}
					
					if(t==ids.length-1){
						sql.append(")");
					}
				}
				
				
				personSyncDAO.setWhereClause(sql.toString());
				DAOFactory factory = new DAOFactory(conn);
				factory.setDAO(personSyncDAO);
				List personList = factory.find(new PersonSyncVO());
				
				if(personList!=null&&personList.size()>0){
					HashMap<String, String> map1 = new HashMap<String, String>(); 
					map1.put("num", personNum);//���
					map1.put("id", xinhuaids[i]);
					map1.put("msg", "����Ա���ڶ���ͬ����Ϣ���밴ʱ����Ⱥ�˳��������Աͬ����Ϣ��");
					array.put(map1);
				}
				
				
				
				
				OrgSyncDAO orgSyncDAO = new OrgSyncDAO();
				sql = new StringBuffer();
				sql.append(" GROUPCODE ='").append(orgCode).append("' ");
				sql.append(" AND OPERATETYPE='addGroup' AND APPROVED = '0' AND OPRATETIME<'").append(optTime).append("' ");
				
				
				orgSyncDAO.setWhereClause(sql.toString());
				factory = new DAOFactory(conn);
				factory.setDAO(orgSyncDAO);
				List orgList = factory.find(new OrgSyncVO());
				
				if(orgList!=null&&orgList.size()>0){
					HashMap<String, String> map1 = new HashMap<String, String>(); 
					map1.put("num", personNum);//���
					map1.put("msg", "����������֯ͬ����������Ϣ��\""+orgName+"\"��֯�Ĵ�����֯ͬ����Ϣ��");
					array.put(map1);
				}
			}
			
			appendString(array.toString());
		}catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println(e);
		}finally{
			if(conn != null)
			{
				try{
					conn.close();
					ConnLog.close("priorCheck");
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	
	
	/**
	 * ��֯ͬ�����ȼ��ж�
	 * @author ����
	 * @param orgcode
	 * @return ������֯code�����֯����
	 */
	public void orgSyncPriorCheck(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String JNDI = "jdbc/ROEEE"; 
		String orgs[] = request.getParameterValues("orgInfos");
		
		
		String orgNums[] = new String[orgs.length];
		String ids[] = new String[orgs.length];
		String orgCodes[] = new String[orgs.length];
		String orgNames[] = new String[orgs.length];
		String optTypes[] = new String[orgs.length];
		String optTimes[] = new String[orgs.length];
		
		
		
		for(int i=0;i<orgs.length;i++){
			String orgStr = orgs[i];
			orgStr = new String(orgStr.getBytes("GBK"),"UTF-8");
			String orgObj[] = orgStr.split("@");
			
			orgNums[i] = orgObj[0];
			ids[i] = orgObj[1];
			orgCodes[i] = orgObj[2];
			orgNames[i] = orgObj[3];
			optTypes[i] = orgObj[4];
			optTimes[i] = orgObj[5];
		}
		
		
		try{
			conn = DBConnectionProvider.getConnection(JNDI);
			JSONArray array = new JSONArray();
			
			
			//�����ͬһ�����ж�����Ĳ�����Ӧ�ð�ʱ����Ⱥ�˳������ͨ����
			for(int i=0;i<orgCodes.length;i++){
				
				String orgNum = orgNums[i];
				String id = ids[i];
				String orgCode = orgCodes[i];
				String orgName = orgNames[i];
				String optType = optTypes[i];
				String optTime = optTimes[i];
				
				
				//����֯���ڶ���ͬ����Ϣ���밴ʱ����Ⱥ�˳��������֯ͬ����Ϣ��
				OrgSyncDAO orgSyncDAO = new OrgSyncDAO();
				StringBuffer sql = new StringBuffer();
				sql.append(" GROUPCODE ='").append(orgCode).append("' ");
				sql.append(" AND APPROVED = '0' AND OPRATETIME<'").append(optTime).append("' ");
				
				for(int t=0;t<ids.length;t++){
					if(t==0){
						sql.append(" AND ID NOT IN (");
					}
					sql.append("'").append(ids[t]).append("'");
					
					if(t!=ids.length-1){
						sql.append(",");
					}
					
					if(t==ids.length-1){
						sql.append(")");
					}
				}
				
				
				orgSyncDAO.setWhereClause(sql.toString());
				DAOFactory factory = new DAOFactory(conn);
				factory.setDAO(orgSyncDAO);
				List orgList = factory.find(new OrgSyncVO());
				
				if(orgList!=null&&orgList.size()>0){
					HashMap<String, String> map1 = new HashMap<String, String>(); 
					map1.put("num", orgNum);//���
					map1.put("orgcode", orgCodes[i]);
					map1.put("msg", "����֯���ڶ���ͬ����Ϣ���밴ʱ����Ⱥ�˳��������֯ͬ����Ϣ��");
					array.put(map1);
				}
				
				
				//����֯���ڶ���ͬ����Ϣ���밴ʱ����Ⱥ�˳��������֯ͬ����Ϣ��
				if(optType!=null&&optType.equals("delGroup")){
					PersonSyncDAO personSyncDAO = new PersonSyncDAO();
					sql = new StringBuffer();
					sql.append(" GROUPID ='").append(orgCode).append("' ");
					sql.append(" AND APPROVED = '0' AND OPRATETIME<'").append(optTime).append("' ");
					
					
					personSyncDAO.setWhereClause(sql.toString());
					factory = new DAOFactory(conn);
					factory.setDAO(personSyncDAO);
					List personList = factory.find(new PersonSyncVO());
					
					if(personList!=null&&personList.size()>0){
						HashMap<String, String> map1 = new HashMap<String, String>(); 
						map1.put("num", orgNum);//���
						map1.put("msg", "ɾ������֯ǰ��������������֯����Ա����Աͬ����Ϣ��");
						array.put(map1);
					}
				}
				
				
				
				//��OA���ң�����֯���Ƿ����ˣ���������ˣ��Ͳ���ִ��ɾ������
				if(optType!=null&&optType.equals("delGroup")){
					StringBuffer sb = new StringBuffer("");
					sb.append(" SELECT T.ORGUUID, T.CNNAME, T.ORGCODE, S.PERSONUUID ");
					sb.append(" FROM SYS_ORG T, SYS_ORGPERSON S WHERE T.ORGUUID = S.ORGUUID  ");
					sb.append(" AND S.ISBELONG='1' ");
					sb.append(" and t.ORGCODE = '").append(orgCode).append("'");
					
					DataBaseExecutor m_executor = DataBaseExecutor.getExecutor(conn);
					RecordSet rset = m_executor.find(sb.toString());
					if(rset!=null&&rset.size()>0){
						HashMap<String, String> map2 = new HashMap<String, String>(); 
						map2.put("num", orgNum);//���
						map2.put("msg", "��OA��,�������ڸ���֯����Ա��ɾ������֯ǰ,����ȷ����Աͬ�����Ƿ��и���֯����Ա��ɾ����ת����Ϣδ����!��");
						array.put(map2);
					}
				}
				
				
				
			}
			
			appendString(array.toString());
		}catch (Exception e) {
			PrintWriter out = response.getWriter();
			System.out.println(e);
		}finally{
			if(conn != null)
			{
				try{
					conn.close();
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * ��֯ͬ������
	 * @author ����
	 * @param orgcode
	 * @return ������֯code�����֯����
	 */
	public void orgSyncAudit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String JNDI = "jdbc/ROEEE";
		String type = request.getParameter("type");
		String returnStr = "";
		
		try {
			conn = DBConnectionProvider.getConnection(JNDI);
			OrgSyncHandler handler = new OrgSyncHandler(conn);
			String[] syncorg = request.getParameterValues("syncorg");
			
			
			//������ͨ��
			if(type != null && "no".equals(type)){
				for(int i = 0; i < syncorg.length; i++) {
					// ��ͨ�������ļ�¼ȡ����������handler�Ĵ��������д���
					String id = syncorg[i];
					OrgSyncVO syncvo = handler.getOrgSyncVOById(id);
					
					String operateType = syncvo.getOperatetype();
					
					//�����������Ϊ�޸���֯����������ͨ��֮ǰҪ��¼�˿���ʽ���е�ֵ
					if(operateType!=null&&operateType.equals("updGroup")){
							//����orgcode�����ʽ���е�OrgVO
							String orgCode = syncvo.getGroupcode();
							if(orgCode!=null&&!orgCode.equals("")){
								OrgVO orgvo = handler.getOrgVO(orgCode);
								
								//������֯��ʽ��֯��������ֶε�ֵ��¼����
								//�������״̬��Ϊ��δͨ����
								if(orgvo!=null&&orgvo.getOrguuid()!=null&&!orgvo.getOrguuid().equals("")){//�����ʽ�����ҵ��˸���֯
									OrgSyncDAO orgSyncDAO = new OrgSyncDAO();
									orgSyncDAO.setConnection(conn); 
									orgSyncDAO.setValueObject(syncvo);
									orgSyncDAO.setApproved("2");//2Ϊ��ͨ��
									
									orgSyncDAO.setOld_groupcode(orgvo.getOrgcode());
									orgSyncDAO.setOld_groupname(orgvo.getCnname());
									orgSyncDAO.setOld_contact(orgvo.getContact());
									orgSyncDAO.setOld_serialindex(new Integer(orgvo.getSerialindex()).toString());
									orgSyncDAO.setOld_memo(orgvo.getMemo());
									
									orgSyncDAO.update();
								}else{//�����ʽ�����Ҳ���ƥ�����֯��ֱ������Ϊ������ͨ��״̬
									handler.updOrgSyncApprovedStatus(id,"2");
								}
							}
					}else{//��������ӻ�ɾ��������ֱ������Ϊ��ͨ��״̬
						handler.updOrgSyncApprovedStatus(id,"2");
					}
				}
			}else{//����ͨ��
				for(int i = 0; i < syncorg.length; i++) {
					// ��ͨ�������ļ�¼ȡ����������handler�Ĵ��������д���
					String id = syncorg[i];
					OrgSyncVO syncvo = handler.getOrgSyncVOById(id);
					
					
					//�����������Ϊ����,�ȼ���Ŀǰ��ʽ������֯��ֵ
					//����orgcode�����ʽ���е�OrgVO
					String orgCode = syncvo.getGroupcode();
					OrgVO orgvo = new OrgVO();
					String operateType = syncvo.getOperatetype();
					if(operateType!=null&&operateType.equals("updGroup")){
						if(orgCode!=null&&!orgCode.equals("")){
							orgvo = handler.getOrgVO(orgCode);
						}
					}
					
					//ִ����֯ͬ��
					String flag = handler.passOrg_New(syncvo);
					
					if(!flag.equals("true")){
						returnStr = returnStr+flag+"\n";
					}
					
					if(flag.equals("true")){
						//�����������Ϊ�޸���֯��������ͨ��֮ǰҪ��¼�˿���ʽ���е����ֵ
						if(operateType!=null&&operateType.equals("updGroup")){
									//������֯��ʽ��֯��������ֶε�ֵ��¼����
									//�������״̬��Ϊ��ͨ����
									if(orgvo!=null&&orgvo.getOrguuid()!=null&&!orgvo.getOrguuid().equals("")){//�����ʽ�����ҵ��˸���֯
										OrgSyncDAO orgSyncDAO = new OrgSyncDAO();
										orgSyncDAO.setConnection(conn); 
										orgSyncDAO.setValueObject(syncvo);
										orgSyncDAO.setApproved("1");//1Ϊͨ��
										
										orgSyncDAO.setOld_groupcode(orgvo.getOrgcode());
										orgSyncDAO.setOld_groupname(orgvo.getCnname());
										orgSyncDAO.setOld_contact(orgvo.getContact());
										orgSyncDAO.setOld_serialindex(new Integer(orgvo.getSerialindex()).toString());
										orgSyncDAO.setOld_memo(orgvo.getMemo());
										
										orgSyncDAO.update();
									}else{//�����ʽ�����Ҳ���ƥ�����֯��ֱ������Ϊ����ͨ��״̬
										handler.updOrgSyncApprovedStatus(id,"1");
									}
						}else{//��������ӻ�ɾ��������ֱ������Ϊͨ��״̬
							handler.updOrgSyncApprovedStatus(id,"1");
						}
					}
				}
			}
			
			if(returnStr.equals("")){
				returnStr="�����ɹ�!";
			}
			appendString(returnStr);
			//response.sendRedirect("/oabase/servlet/IdsSyncServlet?method=syncOrgList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn != null)
			{
				try{
					conn.close();
					ConnLog.close("OrgPassServlet");
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	
}
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
	 * 人员同步列表
	 * @author 周义
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
	 * 组织同步列表
	 * @author 周义
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
	 * 人员同步审批
	 * @author 周义
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
			
			
			//如果是审批不通过
			if(type != null && "no".equals(type)){
				for (int j = 0; j < syncuser.length; j++) {
					String uuid = syncuser[j];
					PersonSyncVO personSyncVo = handler.getPersonSyncVOById(uuid);
					String operateType = personSyncVo.getOperatetype();
					//如果是修改用户类型，将目前正式库中人员的相关信息保存起来(保存到person_sync表中)，以便以后查阅更改记录
					if(operateType!=null&&("updUser").equals(operateType)){
							//根据xinhuaid找出正式库中的该人员
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
								
								//比对正式库中的记录,将目前正式库中人员的相关信息保存起来(保存到person_sync表中)，以便以后查阅更改记录
								if(personVO!=null&&personVO.getPersonuuid()!=null&&!(personVO.getPersonuuid()).equals("")){//如果正式库中找到了该人员
									PersonSyncDAO personSyncDAO = new PersonSyncDAO();
									personSyncDAO.setConnection(conn); 
									personSyncDAO.setValueObject(personSyncVo);
									personSyncDAO.setApproved("2");//2为不通过
									
									personSyncDAO.setOld_truename(personVO.getCnname());
									personSyncDAO.setOld_mobilephone(personVO.getMobile());
									personSyncDAO.setOld_gender(new Integer(personVO.getSex()).toString());
									String old_orgcode = handler.getOrgCodeBypersonuuid(personVO.getPersonuuid());
									personSyncDAO.setOld_groupid(old_orgcode);
									personSyncDAO.setOld_email(personVO.getEmail1());
									
									personSyncDAO.update();
								}else{//如果正式库中找不到匹配的人员，直接审批不通过
									handler.updPerSyncApprovedStatus(uuid,"2");
								}
							}else{//如果正式库中找不到匹配的人员，直接审批不通过
								handler.updPerSyncApprovedStatus(uuid,"2");
							}
					}else{//如果是增加或删除用户，则没必要记录历史的值，直接标记成未通过
						handler.updPerSyncApprovedStatus(uuid,"2");
					}
				}
			}else{//如果是审批通过操作
					for(i=0;i<syncuser.length;i++){
							String personUuid = syncuser[i]; 
							PersonSyncVO personSyncVo = handler.getPersonSyncVOById(personUuid);
							String flag = "false";
							
							
							//如果操作类型为更改,先记下目前正式库中该用户的值
							//根据xinhuaid获得正式库中的PersonVO
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
								//确保xinhuaid不为空
								if(!t_xinhuaid.equals("")){
									dao.setXinhuaid(personSyncVo.getXinhuaid());//xinhuaid
									List personList = null;
									personList = factory.find(new PersonVO());
									if(personList != null && personList.size() == 1) {
										personVO = (PersonVO) personList.get(0);
									}
									if(personVO!=null&&personVO.getPersonuuid()!=null&&!(personVO.getPersonuuid()).equals("")){//如果找到匹配的人员
										old_orgcode = handler.getOrgCodeBypersonuuid(personVO.getPersonuuid());
									}
								}
							}
							
							//执行同步操作
							flag = handler.OperateSyncUser_New(personSyncVo);
							
							if(!flag.equals("true")){
								returnStr = returnStr+flag+"\n";
							}
							
							if(flag.equals("true")){
								
								//如果是修改用户类型，将目前正式库中人员的相关信息保存起来(保存到person_sync表中)，以便以后查阅更改记录
								if(operateType!=null&&("updUser").equals(operateType)){
									//比对正式库中的记录,
									if(personVO!=null&&personVO.getPersonuuid()!=null&&!(personVO.getPersonuuid()).equals("")){//如果找到匹配的人员
										PersonSyncDAO personSyncDAO = new PersonSyncDAO(); 
										personSyncDAO.setConnection(conn); 
										personSyncDAO.setValueObject(personSyncVo);
										personSyncDAO.setApproved("1");//1为通过
										
										personSyncDAO.setOld_truename(personVO.getCnname());
										personSyncDAO.setOld_mobilephone(personVO.getMobile());
										personSyncDAO.setOld_gender(new Integer(personVO.getSex()).toString());
										personSyncDAO.setOld_groupid(old_orgcode);
										personSyncDAO.setOld_email(personVO.getEmail1());
										
										
										personSyncDAO.update();
									}else{//如果找不到匹配的人员，直接设置为通过状态
										handler.updPerSyncApprovedStatus(personUuid,"1");
									}
								}else{//如果是增加或删除用户，则没必要记录历史的值，直接标记成已通过
									handler.updPerSyncApprovedStatus(personUuid,"1");
								}
						  }
					}
			}
			
			if(returnStr.equals("")){
				returnStr = "操作成功!";
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
	 * 人员同步审批
	 * @author 周义
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
	 * 人员同步校验
	 * @author 周义
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
			
			
			//如果对同一个人有多个更改操作，应该按时间的先后顺序审批通过。
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
					map1.put("num", personNum);//序号
					map1.put("id", xinhuaids[i]);
					map1.put("msg", "该人员存在多条同步信息，请按时间的先后顺序审批人员同步信息。");
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
					map1.put("num", personNum);//序号
					map1.put("msg", "请先审批组织同步待审批信息中\""+orgName+"\"组织的创建组织同步信息。");
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
	 * 组织同步优先级判断
	 * @author 周义
	 * @param orgcode
	 * @return 根据组织code获得组织名称
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
			
			
			//如果对同一个人有多个更改操作，应该按时间的先后顺序审批通过。
			for(int i=0;i<orgCodes.length;i++){
				
				String orgNum = orgNums[i];
				String id = ids[i];
				String orgCode = orgCodes[i];
				String orgName = orgNames[i];
				String optType = optTypes[i];
				String optTime = optTimes[i];
				
				
				//该组织存在多条同步信息，请按时间的先后顺序审批组织同步信息。
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
					map1.put("num", orgNum);//序号
					map1.put("orgcode", orgCodes[i]);
					map1.put("msg", "该组织存在多条同步信息，请按时间的先后顺序审批组织同步信息。");
					array.put(map1);
				}
				
				
				//该组织存在多条同步信息，请按时间的先后顺序审批组织同步信息。
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
						map1.put("num", orgNum);//序号
						map1.put("msg", "删除该组织前，请先审批该组织下人员的人员同步信息。");
						array.put(map1);
					}
				}
				
				
				
				//在OA中找，该组织下是否还有人，如果还有人，就不能执行删除操作
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
						map2.put("num", orgNum);//序号
						map2.put("msg", "在OA中,仍有属于该组织的人员，删除该组织前,请先确认人员同步中是否有该组织下人员的删除或转移信息未处理!。");
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
	 * 组织同步审批
	 * @author 周义
	 * @param orgcode
	 * @return 根据组织code获得组织名称
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
			
			
			//审批不通过
			if(type != null && "no".equals(type)){
				for(int i = 0; i < syncorg.length; i++) {
					// 将通过审批的记录取出来，调用handler的处理函数进行处理
					String id = syncorg[i];
					OrgSyncVO syncvo = handler.getOrgSyncVOById(id);
					
					String operateType = syncvo.getOperatetype();
					
					//如果操作类型为修改组织，则审批不通过之前要记录此刻正式库中的值
					if(operateType!=null&&operateType.equals("updGroup")){
							//根据orgcode获得正式库中的OrgVO
							String orgCode = syncvo.getGroupcode();
							if(orgCode!=null&&!orgCode.equals("")){
								OrgVO orgvo = handler.getOrgVO(orgCode);
								
								//将该组织正式组织库中相关字段的值记录下来
								//并将审核状态设为“未通过”
								if(orgvo!=null&&orgvo.getOrguuid()!=null&&!orgvo.getOrguuid().equals("")){//如果正式库中找到了该组织
									OrgSyncDAO orgSyncDAO = new OrgSyncDAO();
									orgSyncDAO.setConnection(conn); 
									orgSyncDAO.setValueObject(syncvo);
									orgSyncDAO.setApproved("2");//2为不通过
									
									orgSyncDAO.setOld_groupcode(orgvo.getOrgcode());
									orgSyncDAO.setOld_groupname(orgvo.getCnname());
									orgSyncDAO.setOld_contact(orgvo.getContact());
									orgSyncDAO.setOld_serialindex(new Integer(orgvo.getSerialindex()).toString());
									orgSyncDAO.setOld_memo(orgvo.getMemo());
									
									orgSyncDAO.update();
								}else{//如果正式库中找不到匹配的组织，直接设置为审批不通过状态
									handler.updOrgSyncApprovedStatus(id,"2");
								}
							}
					}else{//如果是增加或删除操作，直接设置为不通过状态
						handler.updOrgSyncApprovedStatus(id,"2");
					}
				}
			}else{//审批通过
				for(int i = 0; i < syncorg.length; i++) {
					// 将通过审批的记录取出来，调用handler的处理函数进行处理
					String id = syncorg[i];
					OrgSyncVO syncvo = handler.getOrgSyncVOById(id);
					
					
					//如果操作类型为更改,先记下目前正式库中组织的值
					//根据orgcode获得正式库中的OrgVO
					String orgCode = syncvo.getGroupcode();
					OrgVO orgvo = new OrgVO();
					String operateType = syncvo.getOperatetype();
					if(operateType!=null&&operateType.equals("updGroup")){
						if(orgCode!=null&&!orgCode.equals("")){
							orgvo = handler.getOrgVO(orgCode);
						}
					}
					
					//执行组织同步
					String flag = handler.passOrg_New(syncvo);
					
					if(!flag.equals("true")){
						returnStr = returnStr+flag+"\n";
					}
					
					if(flag.equals("true")){
						//如果操作类型为修改组织，则审批通过之前要记录此刻正式库中的相关值
						if(operateType!=null&&operateType.equals("updGroup")){
									//将该组织正式组织库中相关字段的值记录下来
									//并将审核状态设为“通过”
									if(orgvo!=null&&orgvo.getOrguuid()!=null&&!orgvo.getOrguuid().equals("")){//如果正式库中找到了该组织
										OrgSyncDAO orgSyncDAO = new OrgSyncDAO();
										orgSyncDAO.setConnection(conn); 
										orgSyncDAO.setValueObject(syncvo);
										orgSyncDAO.setApproved("1");//1为通过
										
										orgSyncDAO.setOld_groupcode(orgvo.getOrgcode());
										orgSyncDAO.setOld_groupname(orgvo.getCnname());
										orgSyncDAO.setOld_contact(orgvo.getContact());
										orgSyncDAO.setOld_serialindex(new Integer(orgvo.getSerialindex()).toString());
										orgSyncDAO.setOld_memo(orgvo.getMemo());
										
										orgSyncDAO.update();
									}else{//如果正式库中找不到匹配的组织，直接设置为审批通过状态
										handler.updOrgSyncApprovedStatus(id,"1");
									}
						}else{//如果是增加或删除操作，直接设置为通过状态
							handler.updOrgSyncApprovedStatus(id,"1");
						}
					}
				}
			}
			
			if(returnStr.equals("")){
				returnStr="操作成功!";
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
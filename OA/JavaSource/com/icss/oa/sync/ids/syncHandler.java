package com.icss.oa.sync.ids;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.sync.dao.OrgSyncDAO;
import com.icss.oa.sync.dao.PersonDAO;
import com.icss.oa.sync.dao.PersonSyncDAO;
import com.icss.oa.sync.vo.PersonVO;
import com.icss.oa.tq.Webservice.TQUser;
import com.icss.resourceone.user.dao.PersonAccountDAO;
import com.icss.resourceone.user.model.PersonAccountVO;
import com.icss.core.util.UUIDGenerator; 
 

public class syncHandler
{

	private Connection conn;

	public syncHandler()
	{
	}
	
	
	//ͬ����Ա����֯����
	public void syncData(String jsonString)
	{
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(jsonString);
			
			if(jsonObj!=null){
				String command = jsonObj.getString("Command");
				
				if(command!=null){
					if(command.equals("insertUser")||command.equals("updateUser")||command.equals("deleteUser")||command.equals("moveToGroup")||command.equals("removeFromGroup")){
						System.out.println("11111111111:"+command);
						syncUser(jsonString);
					}else if(command.equals("insertGroup")||command.equals("updateGroup")||command.equals("deleteGroup")){
						System.out.println("22222222222222:"+command);
						syncOrg(jsonString);
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void syncUser(String jsonString)
	{
		try
		{
				//conn = DBConnectionLocator.getInstance().getConnection("jdbc/ROEEE");
				conn = getConnection("jdbc/ROEEE");
				PersonSyncDAO personSyncDAO = new PersonSyncDAO();
				personSyncDAO.setConnection(conn);
				
				JSONObject jsonObj = new JSONObject(jsonString);
				
				String operatetype = "";
				String xinhuaid="";
				String hrid="";
				String username="";
				String userpwd="";
				String groupid="";
				String gender="";
				String mobile="";
				String email="";
				String renyuanxh="";
				String truename="";
				String position="";
				String version="";
				String opratetime="";
				String ro_pwd = "";
				
				JSONObject syncObj = new JSONObject();
				String command = jsonObj.getString("Command");
				syncObj = (JSONObject)jsonObj.get("User");
				
				if(command.equals("insertUser")){
					operatetype = "addUser";
				}else if(command.equals("updateUser")){
					operatetype = "updUser";
					
					String personUuid = "";
					String t_xinhuaid = "";
					String old_pwd = "";//R1���������
					String new_pwd = "";
					
					PersonVO personVO = new PersonVO();
					PersonAccountVO accountVo =new PersonAccountVO();
					
					if(syncObj.has("IDSEXT_XINHUAID")){
						t_xinhuaid= syncObj.getString("IDSEXT_XINHUAID");
						if(t_xinhuaid==null)t_xinhuaid="";
				    }
					
					if(syncObj.has("encryptedUserPwd")){
						new_pwd = syncObj.getString("encryptedUserPwd");
						if(new_pwd==null)new_pwd="";
				    }
					
					if(syncObj.has("IDSEXT_R0PassWord")){
						ro_pwd = syncObj.getString("IDSEXT_R0PassWord");
						if(ro_pwd==null){
							ro_pwd="";
						}else{
							if(ro_pwd.length()>4){
								ro_pwd = "RO"+ro_pwd.substring(4);
							}
						}
				    }
					
					DAOFactory factory = new DAOFactory(conn);
					
					//������Ա
					PersonDAO dao = new PersonDAO(conn);
					dao.setXinhuaid(t_xinhuaid);
					factory.setDAO(dao);
					List personList = null;
					personList = factory.find(new PersonVO());
					
					if(personList != null && personList.size() == 1) {
						personVO = (PersonVO) personList.get(0);
					}
					
					if(personVO!=null&&personVO.getPersonuuid()!=null&&!personVO.getPersonuuid().equals("")){
						personUuid = personVO.getPersonuuid();
					}
					
					if(personUuid!=null&&!personUuid.equals("")){
						//������Աpersonuuid���password
						PersonAccountDAO accountDao = new PersonAccountDAO();
						accountDao.setPersonuuid(personUuid);
						factory.setDAO(accountDao);
						
					
						List list = factory.find(new PersonAccountVO());
						if(list != null && list.size() > 0) {
							accountVo = (PersonAccountVO) list.get(0);
							old_pwd = accountVo.getPassword();
						}
					}
					
					
					//���ͬ����������������е����벻һ��,����˵������Ϣ��ͬ��������Ϣ,ֱ��ͬ�����벢����
					//��IDS������������ǰ�߲��� MDǰ׺
					String newPassword = "";//R1����Ҫ��������
					if(new_pwd.indexOf("[RO]")!=-1){
						newPassword = "RO"+new_pwd.substring(4, new_pwd.length());
					}else{
						newPassword = "MD"+new_pwd;
					}
					
					if(!old_pwd.equals("")&&!newPassword.equals("")&&!old_pwd.equals(newPassword)){
						
						//���ȸ��¿��е�����
						PersonAccountDAO personAccountDAO = new PersonAccountDAO();
						personAccountDAO.setValueObject(accountVo);
	                    personAccountDAO.setConnection(conn); 
	                    personAccountDAO.setPassword(newPassword); 
	                    personAccountDAO.update();
	                     
	                    TQUser tquser = new TQUser();
	                    //֪ͨTQ�����뻻��unix�㷨������ �ڲ��Ի����ϲ�Ҫ����ִ����һ��,����û���½ʱ����ʽ�����ϵ�TQ���������
	                    if(ro_pwd!=null&&!ro_pwd.equals("")){
	                    	String rs = tquser.updateUser(personVO.getTqid().toString(), personAccountDAO.getUserid(), ro_pwd, personVO.getCnname(), "2", " | | | | | | | | | | | ");
	                    	System.out.println("ͬ���»�ͨ��������"+rs);
	                    }
			            return;//ֱ�ӷ���,����Ҫִ����PERSON_SYNC��Ĳ�����
					}
					
				}else if(command.equals("deleteUser")){
					operatetype = "delUser";
				}else if(command.equals("moveToGroup")){
					operatetype = "updUser";
				}else if(command.equals("removeFromGroup")){
					operatetype = "updUser";
				}
				
				
			    if(syncObj.has("IDSEXT_XINHUAID")){
			    	 xinhuaid= syncObj.getString("IDSEXT_XINHUAID");
			    }
			    if(syncObj.has("IDSEXT_HRID")){
			    	hrid= syncObj.getString("IDSEXT_HRID");
			    }
			    if(syncObj.has("userName")){
			    	username= syncObj.getString("userName");
			    }
			    if(syncObj.has("encryptedUserPwd")){
			    	userpwd = syncObj.getString("encryptedUserPwd");
			    }
			    if(syncObj.has("allGroupFullPaths")){
			    	groupid= getOrgIds(syncObj.getString("allGroupFullPaths"));
			    }
			    if(syncObj.has("gender")){
			    	gender= syncObj.getString("gender");
			    }
			    if(syncObj.has("mobile")){
			    	mobile= syncObj.getString("mobile");
			    }
			    if(syncObj.has("email")){
			    	email = syncObj.getString("email");
			    }
			    
			    if(syncObj.has("renyuanxh")){
			    	renyuanxh= syncObj.getString("IDSEXT_DISPLAYORDE");
			    }
			    
			    if(syncObj.has("IDSEXT_R0PassWord")){
					ro_pwd = syncObj.getString("IDSEXT_R0PassWord");
					if(ro_pwd==null){
						ro_pwd="";
					}else{
						if(ro_pwd.length()>4){
							ro_pwd = "RO"+ro_pwd.substring(4);
						}
					}
			    }
			    
			    
			    //if(syncObj.has("trueName")){
			    	//truename= syncObj.getString("trueName");
			    //}
			    
			    //TRS���Ǿ�����firstName��Ϊ�û���ȫ��,ԭ������trueName�ֶ�
//			    if(syncObj.has("firstName")){
//		    	  truename= syncObj.getString("firstName");
//		        }
			    
			    if(syncObj.has("IDSEXT_XIANSHIXM")){
			    	truename= syncObj.getString("IDSEXT_XIANSHIXM");
			    }
			    
			    
			    if(syncObj.has("jobPosition")){
			    	position= syncObj.getString("jobPosition");
			    }
			    if(syncObj.has("version")){
			    	version= syncObj.getString("version");
			    }
			    if(syncObj.has("timestamp")){
			    	opratetime= syncObj.getString("timestamp");
			    }
				
			    
			    personSyncDAO.setId(UUIDGenerator.getUUID());
			    personSyncDAO.setOperatetype(operatetype);
				personSyncDAO.setXinhuaid(xinhuaid);
				personSyncDAO.setHrid(hrid);
				personSyncDAO.setUsername(username);
				personSyncDAO.setUserpwd(userpwd);
				personSyncDAO.setGroupid(groupid);
				personSyncDAO.setGender(gender);
				personSyncDAO.setMobilephone(mobile); 
				personSyncDAO.setEmail(email);
				personSyncDAO.setRenyuanxh(renyuanxh); 
				personSyncDAO.setTruename(truename);
				personSyncDAO.setPosition(position);
				personSyncDAO.setVersion(version);
				personSyncDAO.setOpratetime(DateUtils.getCurrentStringDateYMDHMS());
				personSyncDAO.setJsonstring(jsonString);
				personSyncDAO.setRo_pwd(ro_pwd);
				personSyncDAO.setApproved("0");//��Ϊ�����״̬
				
				System.out.println("yyyyyyyyyyy:set attribute:"+personSyncDAO.toString());
				System.out.println("hhhhhhh:����Աͬ����ʱ����Ӽ�¼:"+personSyncDAO);
				personSyncDAO.create();
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("����Աͬ����ʱ����Ӽ�¼ʧ��!"+e);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String getOrgIds(String path) 
	{
		if (path == null || "".equals(path)) return null;
		
		String result = "";
		for (String orgPath : path.split(";"))
		{
			String[] orgs = orgPath.split("/");
			result += orgs[orgs.length - 1].split("#")[0] + ";";
		}
		
		return result.endsWith(";")? result.substring(0, result.length() - 1) : result;
	}


	//ͬ����֯��Ϣ
	public void syncOrg(String jsonString)
	{
		try
		{
				//conn = DBConnectionLocator.getInstance().getConnection("jdbc/ROEEE");
				conn = getConnection("jdbc/ROEEE");
				OrgSyncDAO OrgSyncDAO = new OrgSyncDAO();
				OrgSyncDAO.setConnection(conn);
				
				String id = "";
				String operatetype = "";
				String groupcode = "";
				String parentgroupcode = "";
				String groupname = "";
				String contact = "";
				String serialindex = "";
				String memo = "";
				
				JSONObject jsonObj = new JSONObject(jsonString);
				JSONObject orgObj = new JSONObject();
				String command = jsonObj.getString("Command");
				orgObj = (JSONObject)jsonObj.get("Group");
				
				System.out.println("3333333333:"+orgObj);
				
				if(command.equals("insertGroup")){
					operatetype = "addGroup";
				}else if(command.equals("updateGroup")){
					operatetype = "updGroup";
				}else if(command.equals("deleteGroup")){
					operatetype = "delGroup";
				}
			    
				
			    
			    if(orgObj.has("groupId")){
			    	groupcode= orgObj.getString("groupId");
			    }
			    
			    if(orgObj.has("parentId")){
			    	parentgroupcode= orgObj.getString("parentId");
			    }
			    
			    if(orgObj.has("groupDisplayName")){
			    	groupname= orgObj.getString("groupDisplayName");
			    }
			    
			    if(orgObj.has("groupTel")){
			    	contact= orgObj.getString("groupTel");
			    }
			    
			    if(orgObj.has("displayOrder")){
			    	serialindex = orgObj.getString("displayOrder");
			    }
			    
			    
			    if(orgObj.has("groupDesc")){
			    	memo = orgObj.getString("groupDesc");
			    }
			    
			    
			    
			    OrgSyncDAO.setId(UUIDGenerator.getUUID());
			    OrgSyncDAO.setOperatetype(operatetype);
			    OrgSyncDAO.setGroupcode(groupcode);
			    OrgSyncDAO.setParentgroupcode(parentgroupcode);
			    OrgSyncDAO.setGroupname(groupname);
			    OrgSyncDAO.setContact(contact);
			    OrgSyncDAO.setSerialindex(serialindex);
			    OrgSyncDAO.setMemo(memo);
			    OrgSyncDAO.setJsonstring(jsonString);
			    OrgSyncDAO.setOpratetime(DateUtils.getCurrentStringDateYMDHMS());
			    OrgSyncDAO.setApproved("0");//��Ϊ�����״̬
			    OrgSyncDAO.create();
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("����֯ͬ����ʱ����Ӽ�¼ʧ��!"+e);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	
	 
	
	
	
	
	
	
	protected Connection getConnection(String jndiName) throws NamingException, SQLException {
		Connection conn = null;
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(jndiName);
			conn = ds.getConnection();

		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return conn;
	}
	
	
	
	
}
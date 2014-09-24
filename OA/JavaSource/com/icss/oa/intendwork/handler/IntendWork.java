// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   IntendWork.java

package com.icss.oa.intendwork.handler;


import com.icss.j2ee.dao.*;
import com.icss.oa.intendwork.dao.*;
import com.icss.oa.intendwork.vo.InfoStopVO;
import com.icss.oa.intendwork.vo.OfficePendingVO;
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.util.StringUtility;
import java.io.*;
import java.net.URLEncoder;
import java.sql.*;
import java.util.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class IntendWork
{

	private Connection conn;
	private static Properties properties;

	public IntendWork(Connection conn1)
	{
		conn = conn1;
	}

	public List showWorkList(String personid, String flag, String type)
	{
		DAOFactory daoFactory = new DAOFactory(conn);
		OfficePendingDAO dao = new OfficePendingDAO();
		dao.setPersonid(personid);
		dao.setOpType(type);
		if(!flag.equals("0"))
			dao.setOpFlag(flag);
		dao.addOrderBy("opDate", false);
		List list = null;
		try
		{
			list = daoFactory.find(new OfficePendingVO());
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("取得待办工作错误!");
		}
		return list;
	}

	public List showWorkList(String personid, String flag)
	{
		DAOFactory daoFactory = new DAOFactory(conn);
		OfficePendingDAO dao = new OfficePendingDAO();
		dao.setPersonid(personid);
		if(!flag.equals("0"))
			dao.setOpFlag(flag);
		dao.addOrderBy("opDate", false);
		List list = null;
		try
		{
			list = daoFactory.find(new OfficePendingVO());
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("取得待办工作错误!");
		}
		return list;
	}

	public void addWork(String topic, String source, String url, String navigate, String personid, String code, String id)
	{
		long defaultLongTime = System.currentTimeMillis();
		OfficePendingDAO officePendingDAO = new OfficePendingDAO(conn);
		officePendingDAO.setOpTopic(topic);
		officePendingDAO.setOpDate(new Long(defaultLongTime));
		officePendingDAO.setOpFlag("2");
		officePendingDAO.setOpNavigate(navigate);
		officePendingDAO.setOpSource(source);
		officePendingDAO.setOpType("1");
		officePendingDAO.setPersonid(personid);
		officePendingDAO.setOpModify(new Long(0L));
		officePendingDAO.setOpUrl(url);
		officePendingDAO.setOpCode(initCode(code, id));
		try
		{
			officePendingDAO.create();
			
			//向IDS推送待办消息
			//http://10.102.1.102:9080/cms/cms/manage/info/attachfile?infoId=440077
			String secretKey="";
			String opId = "";
			if(officePendingDAO.getOpId()!=null){
				opId = officePendingDAO.getOpId().toString();
			}
			
			String uri=URLEncoder.encode(getDoWordUrl(opId,url));
			
			String title="["+source+"] "+topic;
			String icon="";
			String ids_type=source;
			String username=personid;
			String createdTime=new Long(defaultLongTime).toString();
			String overdueTime="";
			String content="";
			addWordToIds(secretKey,uri,title,icon,ids_type,username,createdTime,overdueTime,content);
			 
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("增加待办工作错误!");
		}
	}

	public void addWork(String topic, String source, String url, String type, String navigate, String personid, String code, 
			String id)
	{
		long defaultLongTime = System.currentTimeMillis();
		OfficePendingDAO officePendingDAO = new OfficePendingDAO(conn);
		officePendingDAO.setOpTopic(topic);
		officePendingDAO.setOpDate(new Long(defaultLongTime));
		officePendingDAO.setOpFlag("2");
		officePendingDAO.setOpNavigate(navigate);
		officePendingDAO.setOpSource(source);
		officePendingDAO.setOpType(type);
		officePendingDAO.setPersonid(personid);
		officePendingDAO.setOpModify(new Long(0L));
		officePendingDAO.setOpUrl(url);
		officePendingDAO.setOpCode(initCode(code, id));
		try
		{
			officePendingDAO.create();
			
			//向IDS推送待办消息
			String secretKey="";
			
			String opId = "";
			if(officePendingDAO.getOpId()!=null){
				opId = officePendingDAO.getOpId().toString();
			}
			
			//String uri=URLEncoder.encode(getDoWordUrl(opId,url));
			String uri=getDoWordUrl(opId,url);
			//String title="["+source+"] "+topic;
			String title=topic;
			String icon=""; 
			String ids_type=source;
			String username=personid;
			String createdTime=new Long(defaultLongTime).toString();
			String overdueTime="";
			String content="";
			addWordToIds(secretKey,uri,title,icon,ids_type,username,createdTime,overdueTime,content);
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("增加待办工作错误!");
		}
	}
	
	/**
	 * 得到待办url
	 * @author 周义
	 * @throws Exception 
	 */
	public String getDoWordUrl(String opId,String opUrl)
	{
		opUrl = Base64Util.encode(opUrl);
		String ipandport = readValue("oaipandport");
		return "http://"+ipandport+"/oabase"+"/servlet/DoWorkServlet?workid="+opId+"&fromIds=true"+"&url=" + opUrl;
	}
	
	private String readValue(String key) { 
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream("/Mq.properties");
			props.load(in);

			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			System.out.println("############## 取得 Mq.properties 出错");
			e.printStackTrace();
			return null;
		}finally{
				try {
					if(in!=null)
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
	/**向IDS推送待办消息
	 * 得到待办url
	 * secretKey     [String，必需] 密钥     注：由IDS颁发的应用密钥
	 * 待办事项标题     title   String 【可选】注：长度需小于255个字符
	 * 待办事项图标      icon   String 【可选】注：长度需小于255个字符
	 * 待办事项类型      type   String 【可选】注：长度需小于255个字符
	 * 待办事项所属用户名  username  String 【必填】 此用户必须在认证中心存在：长度为3~15位；不允许使用 ' " - \ / : ; * ? < > | % & # + 空格等特殊字符。
	 * 待办事项创建时间   createdTime   long 【可选】注：若不填则为当前系统时间
	 * 待办事项逾期时间   overdueTime   long 【可选】注：若不填则为创建时间后一天
	 * 待办事项内容       content       String 【可选】注：长度需小于255个字符
	 * 
	 * @author 周义
	 * @param personname
	 * @param id
	 * @throws Exception 
	 */
	public void addWordToIds(String secretKey,String uri,String title,String icon,String type,String username,String createdTime,String overdueTime,String content)
	{
		//String str_reminderInfos = "uri=http://www.baidu4.com&title=tstststs&type=ceshi&userName=dev&content=33333&type=32323&createdTime=23423&overdueTime=234234243";
		//uri=**&title=**&icon=**&type=**&username=**&createdTime=**&overdueTime=**&content=**& ”
		
		String str_reminderInfos = "uri~"+uri+"|title~"+title+"|icon~"+icon+"|type~"+type+"|userName~"+username+"|createdTime~"+createdTime+"|overdueTime~"+overdueTime+"|content~"+content;
		//String base64_str_reminderInfos =  new String(encodeBase64(str_reminderInfos.getBytes()));
		String base64_str_reminderInfos="";
		try {
			base64_str_reminderInfos = Base64Util.encode(str_reminderInfos.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace(); 
		}
		String t_url=readValue("IdsToDoServiceUrl");
		
		//String idsServiceType = "v5httpAPI";
		String idsServiceType = "remoteapi"; 
		String method = "addReminder";
		String appName = "oa";
		String reminderInfos = base64_str_reminderInfos;
		
		
		
		
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams cmp = client.getHttpConnectionManager().getParams();
		cmp.setConnectionTimeout(5000);//http连接超时
		cmp.setSoTimeout(5000);//读数据超时
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");//编码
		
		
		PostMethod postMethod = new PostMethod(t_url);
		NameValuePair idsServiceType$ = new NameValuePair("idsServiceType",idsServiceType);
		NameValuePair method$ = new NameValuePair("method",method);
		NameValuePair appName$ = new NameValuePair("appName",appName);
		NameValuePair reminderInfos$ = new NameValuePair("reminderInfos",reminderInfos);
		
		
		NameValuePair[] paramsPair = {idsServiceType$,method$,appName$,reminderInfos$};
		postMethod.addParameters(paramsPair);
		
		try {
			int stateCode = client.executeMethod(postMethod);
			byte[] responseBody = postMethod.getResponseBody();
		    //处理内容
		    System.out.println("向IDS推送待办消息返回:"+new String(responseBody));
		    
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}
	}
	
	

	public void deleteWork(String code, String id)
		throws SQLException
	{
		Statement stmt = null;
		try
		{
			stmt = conn.createStatement();
			String sql = "delete from OFFICE_PENDING where OP_CODE='" + initCode(code, id) + "'";
			System.out.println("删除代办 sql = "+sql);
			stmt.execute(sql);
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
		}
		finally
		{
			if(stmt != null)
				try
				{
					stmt.close();
				}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
		}
		return;
	}

	public void deleteWorkByUser(String username)
		throws SQLException
	{
		System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwww 1111111111111111111111");
		Statement stmt = null;
		try
		{
			stmt = conn.createStatement();
			String sql = "delete from OFFICE_PENDING where PERSONID='" + username + "'";
			System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwww 22222222222222222222222222 " + sql);
			stmt.execute(sql);
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}
		finally
		{
			if(stmt != null)
				try
				{
					stmt.close();
				}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
		}
		return;
	}

	public void deleteWorkbyMainFrame(String code, String id, String userid)
	{
		DAOFactory factory = new DAOFactory(conn);
		OfficePendingDAO dao = new OfficePendingDAO();
		OfficePendingDAO dao1 = new OfficePendingDAO();
		OfficePendingVO vo = null;
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao1.setConnection(conn);
		List list = getWorkListbyID1(code, id, userid);
		if(list != null && list.size() == 1)
		{
			for(Iterator it = list.iterator(); it.hasNext();)
			{
				vo = (OfficePendingVO)it.next();
				try
				{
					dao.setOpId(vo.getOpId());
					dao1 = (OfficePendingDAO)factory.findByPrimaryKey();
					dao1.delete();
				}
				catch(DAOException e)
				{
					e.printStackTrace();
				}
			}

		}
	}

	public OfficePendingVO getIntendWork(Integer opid)
	{
		DAOFactory daoFactory = new DAOFactory(conn);
		OfficePendingDAO dao = new OfficePendingDAO();
		dao.setOpId(opid);
		OfficePendingVO officePendingVO = null;
		try
		{
			officePendingVO = (OfficePendingVO)daoFactory.findByPrimaryKey(new OfficePendingVO());
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("取得待办工作错误!");
		}
		return officePendingVO;
	}

	public void doWork(Integer opid)
	{
		OfficePendingDAO dao = new OfficePendingDAO(conn);
		OfficePendingVO officePendingVO = getIntendWork(opid);
		officePendingVO.setOpFlag("1");
		try
		{
			dao.update();
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("更改待办工作标记错误!");
		}
	}

	public void deleteWork(Integer opid)
	{
		OfficePendingDAO dao = new OfficePendingDAO(conn);
		dao.setOpId(opid);
		try
		{
			dao.delete();
		}
		catch(DAOException e)
		{
			throw new RuntimeException("删除待办工作!");
		}
	}

	public List searchWork(String searchSql)
	{
		DAOFactory daoFactory = new DAOFactory(conn);
		OfficePendingDAO dao = new OfficePendingDAO();
		dao.setWhereClause(searchSql);
		daoFactory.setDAO(dao);
		dao.addOrderBy("opDate", false);
		List list = null;
		try
		{
			list = daoFactory.find(new OfficePendingVO());
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("根据条件搜索待办工作错误!" + e.getMessage());
		}
		return list;
	}

	public String getSearchSql(String personid, String flag, String type, long startTime, long endTime, 
			String topic)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("PERSONID='" + personid + "' ");
		if(flag != null && !flag.equals("0"))
			sql.append(" and op_Flag='" + flag + "' ");
		if(type != null)
			sql.append(" and OP_TYPE in ( " + type + " )");
		if(topic != null)
			sql.append(" and OP_TOPIC like '%" + topic + "%' ");
		sql.append(" and OP_MODIFY < " + System.currentTimeMillis());
		sql.append(" and OP_Date between " + startTime + " and " + endTime);
		return sql.toString();
	}

	public void doIntendWork(Integer workid)
	{
		DAOFactory daoFactory = new DAOFactory(conn);
		OfficePendingDAO dao = new OfficePendingDAO();
		daoFactory.setDAO(dao);
		dao.setOpId(workid);
		try
		{
			dao = (OfficePendingDAO)daoFactory.findByPrimaryKey();
			dao.setOpFlag("1");
			dao.setOpModify(new Long(System.currentTimeMillis()));
			dao.update();
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("设置待办工作标记错误!");
		}
	}

	public List getTotalList(String loginname)
	{
		List list = null;
		DAOFactory daoFactory = new DAOFactory(conn);
		OfficePendingDAO dao = new OfficePendingDAO();
		dao.setConnection(conn);
		dao.setPersonid(loginname);
		dao.setOpFlag("2");
		dao.addOrderBy("opDate", false);
		daoFactory.setDAO(dao);
		try
		{
			list = daoFactory.find(new OfficePendingVO());
		}
		catch(DAOException e)
		{
			e.printStackTrace();
		}
		return list;
	}

	public List getTotalListbySort(String loginname, String condition)
	{
		OfficePendingSearchDAO dao = new OfficePendingSearchDAO();
		dao.setConnection(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("OFFICE_PENDING.OP_ID,OFFICE_PENDING.OP_TOPIC,OFFICE_PENDING.OP_SOURCE,OFFICE_PENDING.OP_URL,OFFICE_PENDING.OP_TYPE,OFFICE_PENDING.OP_DATE,OFFICE_PENDING.OP_FLAG,OFFICE_PENDING.PERSONID,OFFICE_PENDING.OP_MODIFY,OFFICE_PENDING.OP_NAVIGATE,OFFICE_PENDING.OP_CODE ");
		sql.append(" FROM ");
		sql.append("OFFICE_PENDING ");
		sql.append(" WHERE ");
		sql.append("OFFICE_PENDING.OP_FLAG='2' AND OFFICE_PENDING.PERSONID='" + loginname + "'");
		sql.append(" MINUS ");
		sql.append("SELECT ");
		sql.append("OFFICE_PENDING.OP_ID,OFFICE_PENDING.OP_TOPIC,OFFICE_PENDING.OP_SOURCE,OFFICE_PENDING.OP_URL,OFFICE_PENDING.OP_TYPE,OFFICE_PENDING.OP_DATE,OFFICE_PENDING.OP_FLAG,OFFICE_PENDING.PERSONID,OFFICE_PENDING.OP_MODIFY,OFFICE_PENDING.OP_NAVIGATE,OFFICE_PENDING.OP_CODE ");
		sql.append(" FROM ");
		sql.append("OFFICE_PENDING ");
		sql.append(" WHERE ");
		sql.append("OFFICE_PENDING.OP_FLAG='2' AND OFFICE_PENDING.PERSONID='" + loginname + "'");
		sql.append(" and SUBSTR(OFFICE_PENDING.OP_CODE ,INSTR(OFFICE_PENDING.OP_CODE ,'#'),length(OFFICE_PENDING.OP_CODE )) in (" + condition + ")");
		dao.setSearchSQL(sql.toString());
		List list = null;
		try
		{
			list = factory.find(new OfficePendingVO());
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List getImportantList(String loginname)
	{
		List list = null;
		DAOFactory daoFactory = new DAOFactory(conn);
		OfficePendingDAO dao = new OfficePendingDAO();
		dao.setConnection(conn);
		dao.setWhereClause(" PERSONID='" + loginname + "' and (OP_TYPE=" + "2" + " OR OP_TYPE=" + "12" + ") AND OP_FLAG='2' ");
		daoFactory.setDAO(dao);
		try
		{
			list = daoFactory.find(new OfficePendingVO());
		}
		catch(DAOException e)
		{
			e.printStackTrace();
		}
		return list;
	}

	public static void InitProperties(String rootPath)
	{
		InputStream in = null;
		try
		{
			String de = System.getProperty("file.separator");
			String filepath = rootPath + de + "WEB-INF" + de + "intendwork.properties";
			in = new FileInputStream(filepath);
			properties = new Properties();
			properties.load(in);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Exception exception = e;
		}
		finally
		{
			if(in != null)
				try
				{
					in.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
		}
		return;
	}

	public static String getCodeValue(String codeName)
	{
		return properties.getProperty(codeName);
	}

	private static String initCode(String code, String id)
	{
		String reStr = "";
		int i = code.indexOf("|");
		if(i == -1)
		{
			reStr = code;
		} else
		{
			int j = code.lastIndexOf("|");
			int k = code.length();
			String a = code.substring(0, i);
			String b = code.substring(j + 1, k);
			reStr = a + b;
		}
		return reStr + "_" + id;
	}

	public String getItembyUserid(String userid)
		throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		InfoStopDAO dao = new InfoStopDAO();
		factory.setDAO(dao);
		dao.setPersonuuid(userid);
		String str = null;
		InfoStopVO vo = new InfoStopVO();
		try
		{
			List list = factory.find(new InfoStopVO());
			Iterator it = null;
			if(list != null)
				it = list.iterator();
			if(it != null)
				while(it.hasNext()) 
				{
					vo = (InfoStopVO)it.next();
					str = vo.getStopbuffer();
				}
			return str;
		}
		catch(DAOException e)
		{
			e.printStackTrace();
			throw new HandlerException(e);
		}
	}

	public String[] getSetUpItemArray(String str)
	{
		return StringUtility.split(str, "#");
	}

	public String getCondition(String setup_item1[])
	{
		StringBuffer str = new StringBuffer();
		for(int j = 0; j < setup_item1.length; j++)
			if(str.toString().equals(""))
				str.append("'#" + setup_item1[j] + "'");
			else
				str.append(",'#" + setup_item1[j] + "'");

		return str.toString();
	}

	public List getWorkListbyID(String code, String id)
	{
		OfficePendingDAO officePendingDAO = new OfficePendingDAO(conn);
		officePendingDAO.setOpCode(initCode(code, id));
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(officePendingDAO);
		List list = null;
		try
		{
			list = factory.find(new OfficePendingVO());
			return list;
		}
		catch(DAOException e)
		{
			e.printStackTrace();
		}
		throw new RuntimeException("根据code+id得到工作列表出错!");
	}

	public List getWorkListbyID1(String code, String id, String userid)
	{
		OfficePendingDAO officePendingDAO = new OfficePendingDAO(conn);
		officePendingDAO.setOpCode(initCode(code, id));
		officePendingDAO.setPersonid(userid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(officePendingDAO);
		List list = null;
		try
		{
			list = factory.find(new OfficePendingVO());
			return list;
		}
		catch(DAOException e)
		{
			e.printStackTrace();
		}
		throw new RuntimeException("根据code+id得到工作列表出错!");
	}

	public void updateTopicWork(String Topic, String code, String id)
	{
		DAOFactory factory = new DAOFactory(conn);
		OfficePendingDAO dao = new OfficePendingDAO();
		OfficePendingVO vo = null;
		factory.setDAO(dao);
		dao.setConnection(conn);
		List list = getWorkListbyID(code, id);
		for(Iterator it = list.iterator(); it.hasNext();)
		{
			vo = (OfficePendingVO)it.next();
			try
			{
				dao.setOpId(vo.getOpId());
				dao = (OfficePendingDAO)factory.findByPrimaryKey();
				dao.setOpTopic(Topic);
				dao.update(true);
			}
			catch(DAOException e)
			{
				e.printStackTrace();
			}
		}

	}
}

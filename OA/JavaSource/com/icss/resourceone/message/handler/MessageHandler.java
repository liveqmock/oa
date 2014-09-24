/*
 * 创建日期 2005-8-22
 */
package com.icss.resourceone.message.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.regexp.RE;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.resourceone.message.dao.RoMessageDAO;
import com.icss.resourceone.message.vo.RoMessageVO;

/**
 * @author YANGYANG
 * 2005-8-22 14:23:16
 */
public class MessageHandler {
	
	private Connection conn;
	
	public MessageHandler(){
		
	}
	
	public MessageHandler(Connection conn){
		this.conn = conn;
	}
	
	
	/**
	 * AppTop.jsp页面调用这个方法
	 * 
	 * MessageHandler handler = new MessageHandler();
	 * List list = handler.getMessages(channel,app,ipAddress,username,lasttime);
	 * 
	 * String line = "";
	 * for(int i = 0; i< list.size(); i++){
	 * 	
	 *     com.icss.resourceone.message.vo.RoMessageVO vo = (com.icss.resourceone.message.vo.RoMessageVO)list.get(i);
	 * 	   String content = vo.getMessagecontent();
	 * 	   
	 * 	   line += content;
	 * 
	 * }
	 * 
	 * <%=line%>
	 * 
	 * 取得消息集合
	 * @return
	 */
	public List getMessages(String channel, String app, String ipAddress, String username, String lasttime ) throws DAOException{
		
		List list = new ArrayList();
		Connection _conn = null;
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("jdbc/OABASE");
			_conn = ds.getConnection();	
			
			DAOFactory factory = new DAOFactory(_conn);
			RoMessageDAO dao = new RoMessageDAO();
			dao.setIspublish("1");
			dao.addOrderBy("serialindex",true);
			factory.setDAO(dao);
			list = factory.find(new RoMessageVO());	
			
			for (int i = 0; i < list.size(); i++) {
				
				RoMessageVO vo = (RoMessageVO)list.get(i);
				String content = vo.getMessagecontent();
				
				//对消息进行替换	
				//@CHANNEL@ ——> channel
				//@APP@ ——> app
				//@IPADDRESS@ ——> ipAddress
				//@USERNAME@ ——> username
				//@LASTTIME@ ——> lasttime
					
				RE channelRE=new RE("@CHANNEL@");
				//1.把content字符串中的"@CHANNEL@"部分用channel替换
				content = channelRE.subst(content,channel);	
				RE appRE=new RE("@APP@");
				//2.把content字符串中的"@APP@"部分用app替换
				content = appRE.subst(content,app);
				RE ipAddressRE=new RE("@IPADDRESS@");
				//3.把content字符串中的"@IPADDRESS@"部分用ipAddress替换
				content = ipAddressRE.subst(content,ipAddress);
				RE usernameRE=new RE("@USERNAME@");
				//4.把content字符串中的"@USERNAME@"部分用username替换
				content = usernameRE.subst(content,username);
				RE lasttimeRE=new RE("@LASTTIME@");
				//5.把content字符串中的"@LASTTIME@"部分用lasttime替换
				content = lasttimeRE.subst(content,lasttime);
				//将数据存放在vo中
				vo.setMessagecontent(content);
				
				
				
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(_conn!=null){
				try {
					_conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	
		return list;
	}	
	
	/**
	 * 取得消息集合
	 * @return
	 */
	public List getMessages() throws DAOException{
		DAOFactory factory = new DAOFactory(conn);
		RoMessageDAO dao = new RoMessageDAO();
		dao.addOrderBy("serialindex",true);
		factory.setDAO(dao);
		return factory.find(new RoMessageVO());			
	}
	
	/** 判断添加时序号是否已经存在
	 * @param serialindex
	 * @return
	 */
	public boolean serialindexExist(Integer serialindex) {
		boolean exist = false;
		Statement stmt = null;
		ResultSet rst = null;
		String sql = "SELECT SERIALINDEX FROM RO_MESSAGE WHERE SERIALINDEX=" + serialindex;
		System.out.println("SQL" + sql);
		try {
			stmt = conn.createStatement();
			System.out.println("SQL" + sql);
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				exist = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rst != null) {
				try {
					rst.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return exist;
	}
	
	/**
	 * 添加消息
	 * @param vo
	 * @return
	 */
	public Integer addMessage( RoMessageVO vo ) throws DAOException{
		RoMessageDAO dao = new RoMessageDAO();
		dao.setConnection(conn);
		dao.setValueObject(vo);
		dao.create();
		return dao.getMessageid();
	}
	
	/** 判断修改时序号是否已经存在
	 * @param serialindex
	 * @param messageId
	 * @return
	 */
	public boolean serialindexExist(Integer serialindex,Integer messageId) {
		boolean exist = false;
		Statement stmt = null;
		ResultSet rst = null;
		String sql = "SELECT SERIALINDEX FROM RO_MESSAGE WHERE SERIALINDEX=" + serialindex + " AND MESSAGEID<>"+messageId;
		System.out.println("SQL=" + sql);
		try {
			stmt = conn.createStatement();
			System.out.println("SQL" + sql);
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				exist = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rst != null) {
				try {
					rst.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return exist;
	}
	
	/**
	 * 更新消息
	 * @param vo
	 */
	public void updateMessage( RoMessageVO vo ) throws DAOException{
		RoMessageDAO dao = new RoMessageDAO();
		dao.setConnection(conn);
		dao.setValueObject(vo);
		dao.update(true);
	}
	
	/**
	 * 删除消息
	 * @param messageid
	 */
	public void deleteMessage( Integer messageid ) throws SQLException {
		PreparedStatement pstmt = null;
		
		String sql = " DELETE FROM RO_MESSAGE WHERE MESSAGEID=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,messageid.intValue());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		RE re=new RE(" AND ");

		boolean match1 = re.match("SELECT COLUMN1 FROM TABLE1 WHERE 1=1 AND 2=2");

		boolean match2 = re.match("SELECT COLUMN1 FROM TABLE1 WHERE 1=1AND2=2");
		
		boolean match3 = re.match("SELECT ANDY FROM TABLE1 WHERE 1=1 AND 2=2");
		
		boolean match4 = re.match("SELECT COL1 FROM TABLE1 WHERE 1=1");
		
//		System.out.println("match1 = " + match1);
//		System.out.println("match2 = " + match2);
//		System.out.println("match3 = " + match3);
//		System.out.println("match4 = " + match4);
		
//		RE re1 = new RE("AAA AND BBB AND CCC ");
//		String[] sp1 = re1.split(" AND ");
//		for (int i = 0; i < sp1.length; i++) {
//			System.out.println("sp1["+i+"] = " + sp1[i]);
//		}
		
//		RE re2 = new RE(" OR ");
//		String[] sp2 = re2.split("AAAND AND BBB AND CCC ");
//		for (int i = 0; i < sp2.length; i++) {
//			System.out.println("sp2["+i+"] = " + sp2[i]);
//		}
//		
//		
//		String l1 = " A";
//		System.out.println(l1.startsWith(" "));
//		System.out.println(l1.endsWith(" "));
//		
//		String l2 = " A ";
//		System.out.println(l2.startsWith(" "));
//		System.out.println(l2.endsWith(" "));
		
		String m1 = "AND B ";
		System.out.println(m1.startsWith("AND "));
		
		System.out.println(m1.indexOf("AND "));
		
		
		String line0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String line1 = "ABCDE\"FGHI\"JKLMNOPQRSTUVWXYZ";
		org.apache.regexp.RE re1 = new org.apache.regexp.RE("\"");
		
		if(re1.match(line0)){
			System.out.println("re1.match(line0) true");
		}else{
			System.out.println("re1.match(line0) false");
		}		
		
		String line2 = "";
		String line3 = "";
		if(re1.match(line1)){
			System.out.println("re1.match(line1) true");
			line2 = re1.subst(line1,"\\\"");
			line3 = re1.subst(line1,"\\\"");
		}else{
			System.out.println("re1.match(line1) false");
		}

		
		System.out.println("line0 = " + line0);
		System.out.println("line1 = " + line1);
		System.out.println("line2 = " + line2);
		System.out.println("line3 = " + line3);
	}
	
	
	
}




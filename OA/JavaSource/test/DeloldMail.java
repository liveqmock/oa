/*
 * Created on 2004-12-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.mail.MessagingException;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.MessageHandler;
import com.icss.oa.util.CommUtil;

/**
 * @author firecoral
 * 删除所有用户2004-12-00以前的所有邮件
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeloldMail {
	
	static dirmanage mailhandler = null;
	static MessageHandler mhandler = new MessageHandler();
	static FileTransferHandler ftshandler = new FileTransferHandler();
	static String mailhead = "";
	static long recetime = 0;
	static long gotime = 0;
	static Connection conn = null;
	static int x = 0;

	public static void main(String[] args) {
		
		getConnection();
		
		List userList = getAllMailUser();
		System.out.println("there is "+userList.size()+" user want to do!!");
		if(userList.size()>0){
			Iterator Itr = userList.iterator();
			String userid = "";
			String domain = "xinhua.com";
			String ip = "192.9.100.25";
			try {
				gotime = CommUtil.getLongByTime("2004-12-00");
				int i = 0;
				while(Itr.hasNext()){
					userid = Itr.next().toString();	
					System.out.println("the userid is:"+userid);
					mailhandler = new dirmanage(ip, userid, domain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
					//系统下的邮件
					try{
						String[][] str = mailhandler.fileheadList("");
						delMail("",str);
						
						//得到用户自定义的文件夹
						String[] uFolder = mailhandler.dirList("");
						//得到用户所有自定义文件夹中的邮件列表(一个文件夹是一个LIST，一起放在一个大LIST中)
						if(uFolder.length>0){
							String userFolderName = "";
							//用户文件夹名称
							for(int j=0;j<uFolder.length;j++){
								userFolderName = uFolder[j];
								String[][] userStr = mailhandler.fileheadList(userFolderName);
								delMail(userFolderName,userStr);
							}
						}
						System.out.println("has done "+userid+"'s mail");
					}catch(Exception ex){
						System.out.println(userid +"is not a real user!!!");
					}
					
					mailhandler.disconnect();
					i++;
				}//while
				
				System.out.println("did all user compeletly!!! and user'num is:"+i+"!!!and delete mail'num is:"+x);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(mailhandler!=null){
					try {
						mailhandler.disconnect();
					} catch (LdapException e1) {
						System.out.println("disconnect mailhandler failed!!!");
						e1.printStackTrace();
					}					
				}
				
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e1) {
						System.out.println("close conn failed!");
						e1.printStackTrace();
					}
				}
			}
		}//if
	}
	
	
	public static List getAllMailUser(){
		
		List list = new ArrayList();
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT FS_UID FROM FILETRANSFER_SET WHERE FS_DELTAG='0'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString("FS_UID"));
			}

			//System.out.println("get list successfully!!!");

		}  catch (SQLException e) {
			//System.out.println("create statement failed!!!");
			e.printStackTrace();
			
		} finally {
			
			if(rs!=null){
				try {
					rs.next();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		return list;
		
	}
	
	
	public static void delMail(String folder,String[][] str){

		try {
			for(int i=0;i<str[0].length;i++){
				mailhead = str[1][i];
				recetime = mhandler.getReceiveDate(mailhead).getTime();
			    if(recetime<gotime){
			    	String mailpath = str[2][i];
			    	if(folder!="")  mailpath = folder+"/"+mailpath;
			    	mailhandler.deletedir(mailpath,1);
			    	x++;
			    	System.out.println("successfully delete a mail and the recetime is:"+ CommUtil.getTime(recetime));
			    }
			}
		} catch (MessagingException e) {
			System.out.println("get recetime failed!!!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("delete mail failed!!!");
		} catch (Exception e) {
			System.out.println("delete mail failed!!!");
			e.printStackTrace();
		}

	}
	
	public static void getConnection(){
    	try {
			//加载oracle的驱动程序
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			// 主机名和端口 
			String url = "jdbc:oracle:oci:@orcl_192.9.100.25";
			//建立连接
			conn = DriverManager.getConnection(url,"oabase","oabase");
			System.out.println("get connection successfully!!!");
		} catch (InstantiationException e) {
			System.out.println("get connection instance failed!!!");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("get connection instance failed!!!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("get connection instance failed!!!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("connection failed!!!");
			e.printStackTrace();
		}
	}
}

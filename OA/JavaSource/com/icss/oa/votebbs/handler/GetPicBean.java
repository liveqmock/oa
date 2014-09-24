/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.votebbs.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.resourceone.sdk.framework.Context;



//import java.io.UnsupportedEncodingException;
//import javax.mail.internet.MimeUtility;

/**
 * @author Administrator
 *
 * ��¼�ϴ��ĸ�������Ϣ
 */
public class GetPicBean {
	Blob picblob;
	public GetPicBean() {
		
	}
	public void loadPic(String picpath ,Integer	optionid){
		InputStream in;
		try{
			
//			System.out.println("********��ʼ�����ļ����ɹ���************************");
			picblob=this.getPicBlob(optionid);
//			System.out.println("********ȡ��blob************************");
			if(picblob!=null){
//				System.out.println("********blob�ǿղ������ֽ�ת��************************");
				in = picblob.getBinaryStream();
//				System.out.println("********�ֽ�ת���ɹ�************************");
				File picfile = new File(picpath.toString());
				if (!picfile.exists()) {
					picfile.createNewFile();
				}
//				System.out.println("********����ͼƬ�ļ�*1************************");
				FileOutputStream fo = new FileOutputStream(picfile.getPath(),false);
//				System.out.println("*********����ͼƬ�ļ�2************************");
				
				byte[] buf = new byte[1024];
				int len = 0;
				
				while ((len = in.read(buf)) != -1) {
//					System.out.println("*********3-1************************");
					fo.write(buf, 0, len);
//					System.out.println("*********3-2************************");
				}
				System.out.println("*********����ͼƬ�ļ�4************************");
				fo.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	public Blob	getPicBlob(Integer	optionid){
//		System.out.println("GepPicBean   eeee11111111111111111111111     "+optionid);
		Connection conn = null;
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					com.icss.j2ee.util.Globals.DATASOURCEJNDI);
		} catch (DBConnectionLocatorException e1) {
			e1.printStackTrace();
		}
		
		String sql = " SELECT OP_PICBLOB AS picblob FROM  bbs_options WHERE OP_ID = "
				+ optionid;
//		System.out.println("eeee22222222222222222222222222     "+sql); 

		Statement stmt = null;
		ResultSet rs = null;
//		InputStream inputstream=null;
		Blob blob=null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			System.out.println("eeee22222222222222222222222222111111111111     "+sql); 
			if (rs != null) {
				if (rs.next()) {
					blob =(Blob) rs.getBlob("picblob");
//					System.out.println("eeee3333333     "+blob.length()); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		return blob;

	}
	
	
	
}

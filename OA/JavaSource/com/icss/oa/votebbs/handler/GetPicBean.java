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
 * 记录上传的附件的信息
 */
public class GetPicBean {
	Blob picblob;
	public GetPicBean() {
		
	}
	public void loadPic(String picpath ,Integer	optionid){
		InputStream in;
		try{
			
//			System.out.println("********开始进行文件生成工作************************");
			picblob=this.getPicBlob(optionid);
//			System.out.println("********取得blob************************");
			if(picblob!=null){
//				System.out.println("********blob非空并进行字节转换************************");
				in = picblob.getBinaryStream();
//				System.out.println("********字节转换成功************************");
				File picfile = new File(picpath.toString());
				if (!picfile.exists()) {
					picfile.createNewFile();
				}
//				System.out.println("********生成图片文件*1************************");
				FileOutputStream fo = new FileOutputStream(picfile.getPath(),false);
//				System.out.println("*********生成图片文件2************************");
				
				byte[] buf = new byte[1024];
				int len = 0;
				
				while ((len = in.read(buf)) != -1) {
//					System.out.println("*********3-1************************");
					fo.write(buf, 0, len);
//					System.out.println("*********3-2************************");
				}
				System.out.println("*********生成图片文件4************************");
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

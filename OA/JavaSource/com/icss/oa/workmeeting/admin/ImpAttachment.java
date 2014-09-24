/*
 * Created on 2004-8-7
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.workmeeting.admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.icss.j2ee.dao.DAOException;
import com.icss.oa.workmeeting.dao.Attachment1DAO;

//import com.icss.wangimp.vo.InfoMsgVO;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ImpAttachment {

	private static String datePath = "D:\\wangimp\\date\\attachfile\\gongzuowj";
	//附件文件路径

	public static void main(String[] args) {

		Connection sqlConn = GetConn.getSqlServerConn();
		Connection orclConn = GetConn.getOrclConn();

		PreparedStatement pstate = null;
		ResultSet argRes = null;
		try {

			pstate = sqlConn.prepareStatement("SELECT * FROM attachment1 ");

			argRes = pstate.executeQuery();

			int i = 0;
			String fullfilename = "";
			String docno = "";
			String fileextension = "";

			while (argRes.next()) {
				i++;
				fullfilename = "";
				docno = "";
				fileextension = "";
				Attachment1DAO attachdao = new Attachment1DAO();
				attachdao.setConnection(orclConn);
				attachdao.setAttachmentid(
					new Integer(argRes.getInt("ATTACHMENTID")));
				attachdao.setFilename(argRes.getString("FILENAME"));
				fileextension = argRes.getString("FILEEXTENSION");
				attachdao.setFileextension(fileextension);
				fullfilename = argRes.getString("FULLFILENAME");
				attachdao.setFullfilename(fullfilename);
				attachdao.setFilesize(new Integer(argRes.getInt("FILESIZE")));
				attachdao.setDoctype(argRes.getString("DOCTYPE"));
				docno = argRes.getString("DOCNO");
				attachdao.setDocno(docno);
				attachdao.setAttachmentstate(
					argRes.getString("ATTACHMENTSTATE"));
				attachdao.setIsinfolder(argRes.getString("ISINFOLDER"));
				attachdao.setUploadtime(
					new Long(argRes.getTimestamp("UPLOADTIME").getTime()));
				attachdao.setUploadusername(argRes.getString("UPLOADUSERNAME"));
				attachdao.setConverted(argRes.getString("CONVERTED"));
				attachdao.setGzwjid(new Integer(argRes.getInt("GZWJID")));

				try {
					InputStream fileIO =
						new FileInputStream(
							datePath + docno + "." + fileextension);
					attachdao.setAttach(fileIO);
					attachdao.create();
					
					try {
						fileIO.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				} catch (DAOException e3) {
					e3.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			
			if(argRes!=null){
				try {
					argRes.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if(pstate!=null){
				try {
					pstate.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if (sqlConn != null) {
				try {
					sqlConn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			if (orclConn != null) {
				try {
					orclConn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}
//		System.out.println("完成");

	}

}

package com.icss.oa.filetransfer.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.filetransfer.dao.FiletransferReadrecordBakDAO;
import com.icss.oa.filetransfer.dao.FiletransferReadrecordDAO;
import com.icss.oa.filetransfer.dao.FiletransferWanttorecordDAO;
import com.icss.oa.filetransfer.vo.FiletransferWanttorecordVO;
import com.icss.oa.util.PropertyManager;
import com.icss.oa.util.TimeUtil;

public class ReadRecordBackup extends TimerTask{
	
	private String days = PropertyManager.getProperty("readrecordbackup");

	private String time = PropertyManager.getProperty("backuptime");
	
	private Timer timer = new Timer();
	
	
	public ReadRecordBackup(){

	}
	
	
	private synchronized Connection getConn() throws SQLException{
		DataSource ds = null;
		Context ctx = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("jdbc/OABASE");
		} catch (NamingException e) {
			e.printStackTrace();
		} finally{
			if(ctx!=null){
				try {
					ctx.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}
		return ds.getConnection();
	}
	/**
	 * 将阅读记录转移到被分表
	 * @param conn
	 */
	private synchronized void backup(Connection conn) throws IOException{
		FiletransferWanttorecordDAO fwdao = new FiletransferWanttorecordDAO(conn);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(fwdao);

		try {
			List all = factory.findAll(new FiletransferWanttorecordVO());
			if(all!=null && all.size()!=0){
				for(int i=0;i<all.size();i++){
					FiletransferWanttorecordVO fwtemp = (FiletransferWanttorecordVO)all.get(i);
					String mailtime = fwtemp.getRecordid();
					mailtime = mailtime.substring(mailtime.lastIndexOf(",")+1,mailtime.length());
					long daydiff = TimeUtil.dayBetween(Long.parseLong(mailtime),(new Date()).getTime());
					if(daydiff >= Long.parseLong(days)){
						FiletransferReadrecordDAO frdao = new FiletransferReadrecordDAO(conn);
						factory.setDAO(frdao);
						frdao.setWhereClause("MAILID='"+fwtemp.getMailid()+"'");
						List result = factory.find();
						FiletransferReadrecordBakDAO frbdao = new FiletransferReadrecordBakDAO(conn);
						if(result!=null && result.size()!=0){
							for(int j=0;j<result.size();j++){
								FiletransferReadrecordDAO frtemp = (FiletransferReadrecordDAO)result.get(j);
								frbdao.setMailid(frtemp.getMailid());
								frbdao.setReadpersonuuid(frtemp.getReadpersonuuid());
								frbdao.setIsread(frtemp.getIsread());
								frbdao.create();
								frtemp.delete();
							}
						}
					}
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				//add by lintl
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		

		try {
			Connection conn = getConn();
			backup(conn);	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start(){
		timer.scheduleAtFixedRate(this, new Date(), Long.parseLong(days)*24*60*60*1000);
	}
	
}

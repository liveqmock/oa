package com.icss.oa.filetransfer.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeUtility;

import org.redflaglinux.services.dir.dirmanage;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FileTransferHandler;

public class FindMailRun implements Runnable {

	private dirmanage mailhandler = null;
	private long nowtime = 0;
	private int delay = 60;
	private String intendtopic = "";
	private String tointendId = "";
	private FileTransferHandler handler = null;
	private String userid = "";
	private String basepath = "";
	private Connection conn = null;
	private String subject = "";
	private String from = "";
	private boolean flag = false;

	public FindMailRun(String from, String subject, long nowtime,
			String intendtopic, String tointendId, String userid,
			String basepath) throws IOException, SQLException {
		this.mailhandler = MailhandlerFactory.getHandler(userid);
		this.nowtime = nowtime;
		this.intendtopic = intendtopic;
		this.tointendId = tointendId;
		// this.conn = getConnection();
		// this.handler = new FileTransferHandler(conn);
		this.userid = userid;
		this.basepath = basepath;
		this.subject = subject;
		this.from = from;

	}

	// private synchronized Connection getConnection() throws SQLException{
	// DataSource ds = null;
	// Context ctx = null;
	// try {
	// ctx = new InitialContext();
	// ds = (DataSource)ctx.lookup("jdbc/OABASE");
	// } catch (NamingException e) {
	// e.printStackTrace();
	// } finally{
	// if(ctx!=null){
	// try {
	// ctx.close();
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// return ds.getConnection();
	// }

	public void run() {
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			handler = new FileTransferHandler(conn);
			// int count = 1;
			// int findflag = 0;
			// MessageHandler messageHandler = new MessageHandler();
			List mailList = new ArrayList();
			// System.out.println("=========测试邮件！");

			// String serrchresult = "";
			// System.out.println("测试邮件！+"+mailList.size());
			// System.out.println("测试邮件！+"+subject);
			// System.out.println("测试邮件！+"+from);

			// while (!flag) {

			Thread.sleep(3000);
			
			mailList = mailhandler.searchmail("", subject, from
					+ "@oa.xinhua.net", true,
					new Date(nowtime - 60 * 60 * 1000), new Date(
							nowtime + 60 * 60 * 1000), "n");

			for (int i = 0; i < mailList.size(); i++) {
				String[] tmp = (String[]) mailList.get(i);
				// System.out.println("^^^^^^tmp[2]" + tmp[2]);
				// System.out.println("^^^^^^" + nowtime);

				if ((MimeUtility.decodeText(tmp[2])).indexOf(String
						.valueOf(nowtime)) > -1
						&& (tmp[0].indexOf("i") > -1)) {
					// System.out.println("^^^^^^^^^^^待办开始！");
					handler.addToIntend(basepath, userid, intendtopic,
							tointendId, tmp[0]);
					flag = true;
					// // System.out.println("^^^^^^^^^^^待办结束！");
					break;
				}
			}
			
			//如果未找到邮件，则直接插入到收件箱的待办
			if(!flag){
				handler.addToIntend(basepath,userid,intendtopic,tointendId);
			}

			// Thread.sleep(1000);
			// count++;
			// if (count >= delay) {
			// flag = true;
			// break;
			// }

			// }
			// while(true){
			// //如果找到了，或者超时了，退出
			// if(count >= delay || findflag == 1){
			// break;
			// }
			// String[][] heads = mailhandler.fileheadList("");
			//				
			// int mailcount = heads[0].length;
			// mailList= mailhandler.searchmail("inbox", subject, from, true,
			// new Date(nowtime-60*60*1000), new Date(nowtime+60*60*1000), "n");
			// //String serrchresult = "";
			// System.out.println("测试邮件！");
			// System.out.println("测试邮件！+"+mailList.size());
			// System.out.println("测试邮件！+"+subject);
			// System.out.println("测试邮件！+"+from);
			// System.out.println("测试邮件！+"+from);
			//
			// for(int i=0;i<mailList.size();i++){
			// String [] tmp = (String[]) mailList.get(i);
			// for(int j=0;j<tmp.length;j++)
			// {
			// System.out.println(tmp[j]);
			// log.debug("测试搜索邮件开始！");
			// log.debug(tmp[j]);
			// log.debug("测试搜索邮件结束！");
			//
			// }
			// // String tempname = heads[2][i];
			// //
			// // byte[] mailContent = mailhandler.viewmail(tempname);
			// // MimeMessage fileMessage =
			// messageHandler.getContentMessage(mailContent);
			// // String subject = fileMessage.getSubject();
			// //
			// handler.addToIntend(basepath,userid,intendtopic,tointendId,heads[2][i]);
			//
			// //
			// if(Long.toString(nowtime).equals(subject.substring(subject.length()-13,subject.length()))){
			// // //serrchresult = heads[2][i];
			// //
			// handler.addToIntend(basepath,userid,intendtopic,tointendId,heads[2][i]);
			// // findflag = 1;
			// // break;
			// // }
			//					
			// }
			// Thread.sleep(1000);
			// count = count + 1;
			// }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					System.out.println("邮件代办数据库连接关闭");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}

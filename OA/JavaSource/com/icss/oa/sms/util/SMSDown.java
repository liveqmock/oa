package com.icss.oa.sms.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.TimerTask;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.util.Globals;
import com.icss.oa.sms.handler.SMSHandler;
import com.icss.oa.sms.vo.SMSReceiveVO;

public class SMSDown extends TimerTask {

	private void transfer() {

		// String sendAccount = SMSProperties.readValue("sendAccount");
		String server = SMSProperties.ip;
		int port = new Integer(SMSProperties.port).intValue();
		String user = SMSProperties.username;
		String password = SMSProperties.password;
		String contentpath = SMSProperties.upcontent;
		String flagpath = SMSProperties.upflag;
		String path = "";

		Connection conn = null;
		FtpUtil1 ftp = null;
		InputStream in = null;
		try {
			//System.out.println("smsdown---------here!");

			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			SMSHandler handler = new SMSHandler(conn);
			// ���ӷ�����
			ftp = new FtpUtil1();
			// ftp.setFileType(FtpUtil1.ASCII_FILE_TYPE);
			ftp.connectServer(server, port, user, password, path);
			// �г��ļ�
			List<String> list = ftp.getFileList(flagpath);
			//System.out.println("�ļ�="+list.toString());
			// �����ļ�
			ftp.changeDirectory(flagpath);

			for (String name : list) {

				try {
					ftp.changeDirectory("../" + contentpath);

					// System.out.println("---SMS�����ļ�---");
					SMSReceiveVO vo = new SMSReceiveVO();
					SAXReader saxReader = new SAXReader();

					
					in = ftp.downFile(name);
					Document document = saxReader.read(in);
					
					if (ftp.completeDown()) {

						Element root = document.getRootElement();
						Element info = root.element("MESSAGE_INFO");

						// String id = info.elementText("ID");
						// String fromNo = info.elementText("FromNo");
						String toNo = info.elementText("ToNo");

						String extendNo = info.elementText("ExtendNo");
						// String TextContent = info.elementText("TextContent");
						// String ReceiveTime = info.elementText("ReceiveTime");

						String no = toNo.substring(toNo.indexOf(extendNo) + 4,
								toNo.length());

						if ("".equals(no) || no == null) {
							no = "001";
							vo.setCotent(info.elementText("TextContent")
									+ "(�Ҳ���������!**" + toNo + "**)");
						} else {
							vo.setCotent(info.elementText("TextContent"));
						}
						String uuid = handler.getUuidBySMSID(no);
						vo.setFromNo(info.elementText("FromNo"));
						vo.setToNo(no);
						vo.setReceiver(uuid);
						vo.setTime(info.elementText("ReceiveTime"));

						// �������ݿ�
						Integer id = handler.saveReSMS(vo);
						// �������
						if ("".equals(uuid) || uuid == null) {

						} else {
							vo.setId(id);
							handler.toDb(vo);
						}
					}
					// System.out.println("---�����ļ�����---");

					ftp.changeDirectory("../" + flagpath);

					ftp.deleteFile(name);
					// System.out.println("---ɾ���ļ�---" + name);

				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					// �������e�r�����ļ��h������Ȼ��һֱ����
					System.err.println("�ļ�" + name + "����!�Ѿ�ɾ��");
					ftp.changeDirectory("../" + flagpath);
					ftp.deleteFile(name);
				}
			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ftp.closeServer();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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

	public void run() {
		transfer();
	}
}
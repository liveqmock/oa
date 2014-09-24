// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   CreateUserServelt.java

package com.icss.oa.filetransfer.admin;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.address.handler.PersonOrderList;
import com.icss.oa.filetransfer.dao.SyspersonSearchDAO;
import com.icss.oa.filetransfer.handler.*;
import com.icss.oa.folder.dao.FolderManagementDAO;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.util.PropertyManager;
import com.icss.resourceone.sdk.framework.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.redflaglinux.services.dir.dirmanage;

public class CreateUserServelt extends ServletBase {

	Connection conn = null;
	dirmanage mailhandler = null;

	public CreateUserServelt() {
		mailhandler = null;
	}

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					"java:comp/env/ResourceOne/DataSource");
			SyspersonSearchDAO spdao = new SyspersonSearchDAO();
			spdao.setConnection(conn);
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(spdao);
			List s = factory.findAll();
			createFolderManager(conn, s);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public void createFolderManager(Connection conn, List user)
			throws DAOException, FileNotFoundException, IOException {
		FolderHandler handler = new FolderHandler(conn);
		DAOFactory factory = new DAOFactory(conn);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("/zxc.txt")));
		int bnum = 0;
		for (int i = 0; i < user.size(); i++) {
			SyspersonSearchDAO temp = (SyspersonSearchDAO) user.get(i);
			FolderManagementDAO fadao = new FolderManagementDAO(conn);
			factory.setDAO(fadao);
			fadao
					.setWhereClause("FM_PERSONID = '" + temp.getPersonuuid()
							+ "'");
			List havea = factory.find();
			if (havea == null || havea.size() <= 0) {
				bnum++;
				FolderManagementVO vo = new FolderManagementVO();
				vo.setFmPersonid(temp.getPersonuuid());
				vo.setFmStoretype("1");
				vo.setFmCreatedate(new Long(System.currentTimeMillis()));
				vo.setFmModifydate(new Long(System.currentTimeMillis()));
				handler.addUser(vo);
			}
		}

		bw.write("shuzi:" + bnum);
		bw.flush();
		bw.close();
	}

	public void createuser(Connection conn, List s) throws DAOException {
		MailUserHandler muhandler = new MailUserHandler(conn);
		for (int i = 0; i < s.size(); i++) {
			SyspersonSearchDAO temp = (SyspersonSearchDAO) s.get(i);
			muhandler.createUser(temp.getUserid(), temp.getPersonuuid(),
					"xinhua");
		}

	}

	public void fileattach(Connection conn) throws EntityException, Exception {

		int flag = 0;
		EntityManager entitymanger = EntityManager.getInstance();
		List personlist = PersonOrderList
				.personList(entitymanger
						.findPersonsRecursivelyByOrgUuid("4161cca6-fc3558bd9f-97fe05e58eef24f3370b74f3cc7c23fc"));
		String domain = PropertyManager.getProperty("archivesdomain");
		String ip = PropertyManager.getProperty("archivesip");
		FileOutputStream fos = new FileOutputStream("/4.txt");
		FileOutputStream fs1 = new FileOutputStream("/out.txt");
		FileOutputStream err = new FileOutputStream("/err.txt");
		PrintStream pw = new PrintStream(fs1);
		PrintStream pw2 = new PrintStream(err);
		System.setOut(pw);
		System.setErr(pw2);
		FileTransferHandler ft = new FileTransferHandler();
		MessageHandler messageHandler = new MessageHandler();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		try {
			for (int i = 0; i < personlist.size(); i++) {
				flag = 0;
				String username = ((Person) personlist.get(i)).getLoginName();
				System.out.println("username" + i + ":" + username);
				mailhandler = new dirmanage(ip, username, domain, ip, 389,
						"cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
				String str[][] = mailhandler.fileheadList("");
				for (int j = 0; j < str[0].length; j++) {
					String tempname = str[2][j];
					System.out.println("tempname(" + i + ")" + j + ":"
							+ tempname);
					byte mailContent[] = mailhandler.viewmail(tempname);
					System.out.println("byte:" + mailContent.length);
					MimeMessage fileMessage = messageHandler
							.getContentMessage(mailContent);
					if ((fileMessage.getContent() instanceof Multipart)
							&& "i".equals(tempname.substring(0, 1))) {
						Multipart multipart = (Multipart) fileMessage
								.getContent();
						System.out.println("num" + j + ":"
								+ multipart.getCount());
						for (int k = 0; k < multipart.getCount(); k++) {
							Part part = multipart.getBodyPart(k);
							if (!ft.isAttachPart(part))
								continue;
							System.out.println("attachname("
									+ i
									+ ")("
									+ j
									+ ")"
									+ k
									+ ":"
									+ MimeUtility
											.decodeText(part.getFileName()));
							if (!"xperf_x64.msi".equals(MimeUtility
									.decodeText(part.getFileName())))
								continue;
							bw.write(username + "," + tempname + "\n");
							bw.flush();
							flag = 1;
							break;
						}

					}
					if (flag == 1)
						break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//by lintl
			fos.close();
			fs1.close();
			err.close();
			bw.close();
			pw2.close();
		}
	}
}
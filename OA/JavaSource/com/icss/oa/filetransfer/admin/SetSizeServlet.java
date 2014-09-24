/*
 * Created on 2004-5-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.user.usermanage;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.filetransfer.vo.FiletransferSetVO;
import com.icss.oa.util.PropertyManager;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SetSizeServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		usermanage usermanage = null;

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);

			//		�޸������С
			if (request.getParameter("id") != null) {
				Integer id = Integer.valueOf(request.getParameter("id"));
				//String name = request.getParameter("name");
				Integer size = Integer.valueOf(request.getParameter("size"));

				FiletransferSetHandler handler =
					new FiletransferSetHandler(conn);
				List filesetlist = handler.getByid(id);
				Iterator result = filesetlist.iterator();
				FiletransferSetVO filetransetvo = null;
				if (result.hasNext()) {
					filetransetvo = (FiletransferSetVO) result.next();
				}
				FiletransferSetVO vo = new FiletransferSetVO();
				vo.setId(id);
				vo.setFsSize(size);
				vo.setFsPer(filetransetvo.getFsPer());
				vo.setFsRule(filetransetvo.getFsRule());
				vo.setFsUid(filetransetvo.getFsUid());
				vo.setFsUuid(filetransetvo.getFsUuid());
				vo.setFsDeltag(filetransetvo.getFsDeltag());
				handler.alter(vo);

				//�����ʼ�������������
				String ip = PropertyManager.getProperty("archivesip");
				String backupip = PropertyManager.getProperty("backupip");
				String archivesdomain = PropertyManager.getProperty("archivesdomain");
				usermanage = new usermanage();
				usermanage.connect(ip,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");

				//usermanage = MailhandlerFactory.getUserManager();
				String tempsize = request.getParameter("size");
				Integer integersize = new Integer(tempsize);
				int intsize = integersize.intValue() * 1024 * 1024;usermanage.changemailsize(filetransetvo.getFsUid(),
						archivesdomain, intsize);
				//�ô˷����ı䱸�÷������Ĵ�С���ʼ�����������ͬ����add by lintl
				usermanage.connect(backupip,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
				usermanage.changemailsize(filetransetvo.getFsUid(),archivesdomain, intsize);
			}

			String orgid = request.getParameter("orgid");
			FiletransferSetHandler filetranset =
				new FiletransferSetHandler(conn);
			List list = filetranset.getByOrgid(orgid);

			//******************���ڷ�ҳ***************************************
			List relist = new ArrayList();
			//һҳ��������¼
			int rowcount =
				Integer.parseInt(this.getInitParameter("_usercount_per_page"));
			//��ǰҳ��
			int curPageNum = 0;
			String currentNum = request.getParameter("curPageNum");
			if (currentNum != null) {
				curPageNum =
					Integer.parseInt(request.getParameter("curPageNum"));
			} else {
				curPageNum = 1; //��һ�ε�JSPΪ��һҳ
			}
			//���ж���ҳ
			int pagecount = 0;
			if (list.size() % rowcount == 0)
				pagecount = list.size() / rowcount;
			else
				pagecount = list.size() / rowcount + 1;
			//���ж�������¼
			int totalcount = list.size();

			///////////////////////////////////////////////////////////
			int startNum = (curPageNum - 1) * rowcount; //��ʼ��������LIST�е���ţ�
			int endcount = startNum + rowcount;
			if (endcount > totalcount) {
				endcount = totalcount;
			}
			for (int k = startNum; k < endcount; k++) {
				relist.add(list.get(k));
			}



			///////////////////////////���ڷ�ҳ/////////////////////
			request.setAttribute("curPageNum", new Integer(curPageNum));
			request.setAttribute("pagecount", new Integer(pagecount));
			request.setAttribute("totalcount", new Integer(totalcount));
			//*****************��ҳ�������************************************

			
			request.setAttribute("list", relist);
			request.setAttribute("orgid", orgid);

			this.forward(request, response, "/mail/setsize.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally {
			try {
				usermanage.disConnect();
			} catch (LdapException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				throw new RuntimeException("���ݿ����ӹرմ���");
			}
		}
	}
	
	/*
	* �õ�Ldap��ַ�����ʼ���������ַ
	*/
	public String getLdapAdd() {
		return PropertyManager.getProperty("archivesip");
	}
	
}





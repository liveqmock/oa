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

			//		修改邮箱大小
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

				//更改邮件服务器的设置
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
				//用此方法改变备用服务器的大小，邮件服务器不能同步，add by lintl
				usermanage.connect(backupip,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
				usermanage.changemailsize(filetransetvo.getFsUid(),archivesdomain, intsize);
			}

			String orgid = request.getParameter("orgid");
			FiletransferSetHandler filetranset =
				new FiletransferSetHandler(conn);
			List list = filetranset.getByOrgid(orgid);

			//******************用于分页***************************************
			List relist = new ArrayList();
			//一页多少条记录
			int rowcount =
				Integer.parseInt(this.getInitParameter("_usercount_per_page"));
			//当前页数
			int curPageNum = 0;
			String currentNum = request.getParameter("curPageNum");
			if (currentNum != null) {
				curPageNum =
					Integer.parseInt(request.getParameter("curPageNum"));
			} else {
				curPageNum = 1; //第一次到JSP为第一页
			}
			//共有多少页
			int pagecount = 0;
			if (list.size() % rowcount == 0)
				pagecount = list.size() / rowcount;
			else
				pagecount = list.size() / rowcount + 1;
			//共有多少条记录
			int totalcount = list.size();

			///////////////////////////////////////////////////////////
			int startNum = (curPageNum - 1) * rowcount; //开始条数（在LIST中的序号）
			int endcount = startNum + rowcount;
			if (endcount > totalcount) {
				endcount = totalcount;
			}
			for (int k = startNum; k < endcount; k++) {
				relist.add(list.get(k));
			}



			///////////////////////////用于分页/////////////////////
			request.setAttribute("curPageNum", new Integer(curPageNum));
			request.setAttribute("pagecount", new Integer(pagecount));
			request.setAttribute("totalcount", new Integer(totalcount));
			//*****************分页处理结束************************************

			
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
				throw new RuntimeException("数据库连接关闭错误");
			}
		}
	}
	
	/*
	* 得到Ldap地址，即邮件服务器地址
	*/
	public String getLdapAdd() {
		return PropertyManager.getProperty("archivesip");
	}
	
}





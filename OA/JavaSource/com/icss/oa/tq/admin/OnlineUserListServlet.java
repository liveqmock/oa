package com.icss.oa.tq.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.tq.handler.TQHandler;
import com.icss.oa.tq.vo.TqOnlineVO;
import com.icss.resourceone.sdk.framework.Context;

public class OnlineUserListServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn =null;
		try {
				Context ctx = Context.getInstance();
				String uuid =ctx.getCurrentPersonUuid();
				
				
				conn =getConnection(Globals.DATASOURCEJNDI);
				TQHandler handler = new TQHandler(conn );
				
				Integer tqid = handler.getTqid(uuid);
				
				int pagesize = 20;
				int cpage = request.getParameter("cp")==null?1:Integer.parseInt(request.getParameter("cp"));
				String key = request.getParameter("key");
				
				int sumRecords = handler.querySumPages(key);
				
				int last = 1;
				if (sumRecords != 0) {
					last = sumRecords / pagesize;
					if ((sumRecords % pagesize) > 0)
						last = last + 1;
				}
				List list = handler.queryPage(key, cpage, pagesize);
				Iterator it =list.iterator();
				
				StringBuffer s = new StringBuffer();

				try {
					PrintWriter out = response.getWriter();
					s.append("<TABLE width=300 border=0 align=center cellpadding=0 cellspacing=1 class=table_bgcolor>");
					s.append("<TR bgcolor=#E0EDF8>");
					s.append("<TD height=22 align=center nowrap class=block_title><b>姓名</b></td>");
					s.append("<TD width=64% align=center class=block_title nowrap><b>部门</b></td>");
					s.append("</tr>");
					
					while(it.hasNext()){
						TqOnlineVO vo = (TqOnlineVO) it.next();   
						s.append("<tr>");
						s.append("<td width=30% nowrap align=center bgcolor=#FFFFFF class=blue3-12><a href=javascript:tq_talk(2,")
						.append(vo.getTqid()).append(",").append(tqid).append("); >");
						s.append(vo.getCnname()).append("</td>");
						s.append("<TD align=center nowrap bgcolor=#FFFFFF class=blue3-12>"
								+ vo.getOrgname() + "</td>");
						s.append("</tr>");
					}
					int up = (cpage - 1 > 1) ? cpage - 1 : 1;
					int next = (cpage + 1 < last) ? cpage + 1 : last;
					s.append("<tr>");
					if (cpage == 1) {
						s.append("<td bgcolor=#ffffff class=message_title align=center colspan=2>首页&nbsp;&nbsp;");
						s.append("上一页&nbsp;&nbsp;");
					} else {
						s.append("<td bgcolor=#ffffff class=message_title align=center colspan=2><a href=javascript:changepage(1)>首页</a>&nbsp;&nbsp;");
						s.append("<a href=javascript:changepage(" + up
								+ ") class=message_title>上一页</a>&nbsp;&nbsp;");
					}
					if (cpage == last) {
						s.append("下一页 &nbsp;&nbsp;");
						s.append("尾页</td>");
					} else {
						s.append("<a href=javascript:changepage(" + next
								+ ") class=message_title>下一页</a> &nbsp;&nbsp;");
						s.append("<a href=javascript:changepage(" + last
								+ ") class=message_title>尾页</a></td>");
					}
					s.append("</tr>");
					s.append("<tr ><td align=center colspan=2><b class=red2-12-b>当前共有").append(sumRecords).append("人在线</b></td></tr>");
					s.append("</table>");
					out.print(s.toString());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
					
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
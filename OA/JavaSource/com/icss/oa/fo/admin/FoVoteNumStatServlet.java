/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.fo.admin;

import java.util.List;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoPlanVO;


public class FoVoteNumStatServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Integer planid = request.getParameter("planid")==null?new Integer(-1):Integer.valueOf(request.getParameter("planid"));
    	String[] voteartids =request.getParameterValues("artids") == null? new String[] { "-1" }: request.getParameterValues("artids");
    	Connection conn = null;
      
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			FoHandler handler = new FoHandler(conn);
			
			if (!new String[] { "-1" }.equals(voteartids)) {
				
				PrintWriter out = response.getWriter();
				printHead(request, response, out,planid.toString());
				handler.outHtml(
						out,
						"任务开始...(根据数据量不同，需要1-5分钟，请耐心等待。)",
						request,
						"info");
				String	commonmsg="";
				//统计投票人数
				String	votepersonnum=handler.getVotePersonNum(planid.toString());
				int	inputvoteperson=handler.getinPutVotePersonnum(planid);
				int newvoteperson=new Integer(votepersonnum).intValue()+inputvoteperson;
				
				
				
				FoPlanVO planvo=handler.getPlanVo(planid.toString());
				planvo.setPlanPersonnum(new Integer(newvoteperson));
				handler.updatePlan(planvo);
				commonmsg="统计投票人数并更新参数表中的投票人数信息,投票人数="+newvoteperson;
				handler.outHtml(
						out,
						commonmsg,
						request,
						"info");
				
				for (int i = 0; i < voteartids.length; i++) {
					
					
					
					//取得此文章的票数合计
					int votenumstat=handler.getArtVoteNum(planid,voteartids[i]);
					int invotenumstat=handler.getInputvalueStat(planid,voteartids[i]);
					//更新文章表中的票数
					handler.updateArticalVotenum(voteartids[i],new Integer(votenumstat+invotenumstat));
					//System.out.println("统计票数，votenumstat="+votenumstat);
					commonmsg="现在统计文章ID为"+voteartids[i]+"的票数："+votenumstat+invotenumstat+"其中投票数为"+votenumstat+"，输入投票数为"+invotenumstat;
					handler.outHtml(
							out,
							commonmsg,
							request,
							"info");
				}
				handler.outHtml(
						out,
						"统计完成，请查看结果!",
						request,
						"info");
				printEnd(request, response, out);
				
			}else{
				String dist = "/fo/alert.jsp?errormsg=请选择统计ID"
					
				;
				forward(request,response,dist);
			}

			
			
			
			
		}catch(Exception e){
			handleError(e) ;
		}finally{
			try {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e) {
				handleError(e);
			}
        }
    }
    
    
    private void printHead(HttpServletRequest request, HttpServletResponse response, PrintWriter out,String	planid) {
		
		
		
		//out.println("<%@ page contentType=\"text/html; charset=gb2312\"%>");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html; charset=GBK");
	//PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">");
		out.println("<title>投票统计</title>");
		out.println("<link href=\"" + request.getContextPath() + "/include/style.css\" rel=\"stylesheet\" type=\"text/css\">");
		out.println("<script language=\"javascript\">");
		out.println(" function showWaitingDiv(){");
		out.println("  window.document.all.Layer1.style.display = \"\";");
		out.println(" }");
		out.println(" function hideWaitingDiv(){");
		out.println("  window.document.all.Layer1.style.display = \"none\";");
		out.println(" }");
		out.println("function _goback(){document.location=\"" + request.getContextPath() + "/servlet/FoStatListServlet?planid=" + planid+ "\";}");
		out.println("</script>");
		out.println("</head>");
		out.println("<body background=\"" + request.getContextPath() + "/images/grid.gif\" >");
		out.println("<div id=\"Layer1\" style=\"position:absolute; left:414px; top:83px; width:220px; height:40px; z-index:1; display:visibility\">");
		out.println("<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#333333\">");
		out.println("  <tr>");
		out.println("     <td bgcolor=\"#FFFFFF\">");
		out.println("     <table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr>");
		out.println("       <td width=\"40\" height=\"40\"><img src=\"" + request.getContextPath() + "/images/appInstall_animated.gif\" width=\"40\" height=\"40\"></td>");
		out.println("       <td>&nbsp;<span class=\"style6\">总分计算中 请稍候......</span></td>");
		out.println("     </tr></table>");
		out.println("     </td>");
		out.println("  </tr>");
		out.println("</table>");
		out.println("</div>");
		out.println("<TABLE cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0>");
		out.println("  <TR>");
		out.println("    <TD width=\"2%\" background=\"" + request.getContextPath() + "/images/bg-12.gif\"><IMG height=22 src=\"" + request.getContextPath() + "/images/bg-10.gif\" width=14></TD>");
		out.println("    <TD class=title-05 background=\"" + request.getContextPath() + "/images/bg-12.gif\">[执行票数计算...]</TD>");
		out.println("    <TD width=\"1%\" align=\"right\"><IMG height=22 src=\"" + request.getContextPath() + "/images/bg-11.gif\" width=20></TD>");
		out.println("  </TR>");
		out.println("</TABLE>");
		out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		out.println("  <TR height=24 class=title-04 bgcolor=\"#FFFBEF\">");
		out.println(
			"    <td width=\"1\" background=\"" + request.getContextPath() + "/images/bg-21.gif\"><img src=\"" + request.getContextPath() + "/images/bg-21.gif\" width=\"1\" height=\"100%\"></td>");
		out.println("    <td width=\"100%\" class=\"dot\" align=center>状态</td>");
		out.println("  </TR>");
		out.println("</TABLE>");
		out.flush();
		
	}
    private void printEnd(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		out.println("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		out.println("  <tr>");
		out.println("    <TD width=\"1%\" background=\"" + request.getContextPath() + "/images/bg-23.jpg\"><IMG height=21 src=\"" + request.getContextPath() + "/images/bg-21.jpg\" width=10></TD>");
		out.println("    <TD class=text-01 width=\"97%\" background=\"" + request.getContextPath() + "/images/bg-23.jpg\"></TD>");
		out.println(
			"    <TD width=\"2%\" background=\""
				+ request.getContextPath()
				+ "/images/bg-23.jpg\" align=\"right\"><IMG height=21 src=\""
				+ request.getContextPath()
				+ "/images/bg-22.jpg\" width=11></TD>");
		out.println("  </tr>");
		out.println("</table>");
		out.println("<script language=\"javascript\">");
		out.println("  hideWaitingDiv();");
		out.println("</script>");
		out.println("<table width=\"100%\"  border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
		out.println(" <tr>");
		out.println("	<td height=\"50\" align=\"center\">");
		out.println("	  <table width=\"100\" height=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
		out.println("		<tr align=\"center\" valign=\"middle\">");
		out.println("		  <td width=\"33%\"><input type=\"button\" class=\"word4\"  value=\"查看汇总结果\" onClick=\"_goback()\"></td>");
		out.println("		</tr>");
		out.println("	</table>");
		out.println("	</td>");
		out.println("  </tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
	}
    
}
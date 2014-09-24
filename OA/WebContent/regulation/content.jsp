<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.regulation.handler.RegulationHandler" %>
<%@ page import="com.icss.regulation.vo.RegulationVO" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.regulation.handler.RegulationHandler" %>


<%
	Integer id = request.getParameter("id") == null ? new Integer(-1)
			: new Integer(request.getParameter("id"));

	Connection conn = null;
	RegulationVO vo = new RegulationVO();
	String org ="";
	if (id.intValue() > -1) {
		try {
			conn = DBConnectionLocator.getInstance().getConnection(
					"jdbc/OABASE");
			RegulationHandler handler = new RegulationHandler(conn);
			vo = handler.getRegulation(id);
			org = handler.getROrgName(vo.getOrg());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception sqle) {
				sqle.printStackTrace();
			}
		}
	}
%>
<HTML>
	<HEAD>
		<TITLE>content</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GBK">
		<STYLE>
		BODY {
			font-family: 宋体;
			font-size: 14px;
		}

		.left{
		color: #444444;
		display: block;
		float:left;
		padding:1px 0px 0px 7px;
		min-height:0px;
		}
		.clear{
		float:none;
		clear:both;
		height:0px;
		}
		</STYLE>

		<script>
	function printdiv(printpage)
		{
		var newstr = document.getElementById(printpage).innerHTML;
		document.getElementById("daying").innerHTML="<h3 align='center'>"+document.getElementById("title").innerHTML+"</h3>"+newstr;
		//document.getElementById("ptitle").innerHTML= document.getElementById("title").innerHTML;
		allhtml.style.display="none";
		daying.style.display="block";
		//var oldstr = document.body.innerHTML;
		//document.body.innerHTML = newstr;
		window.print(); 
		daying.style.display="none";
		allhtml.style.display="block";
		return false;
		} 
		</script>
	</HEAD>
	<BODY topMargin=3 marginheight="1">
		<div id ="daying" style ="display:none" >
			<h3 id="ptitle" align="center"></h3>
		</div>
		<div id ="allhtml">
		<div>
		<div>
		<div class='left'>标题：</div>
		<div class='left'>
		<textarea id="title" cols="60" rows="2" ><%=StringUtil.escapeNull(vo.getTitle())%></textarea></div>
		<div class="clear"></div>
		</div>
		<div>
		<div class='left'>部门：</div>
		<div class='left'><input id="org" type="text" class="text" size='10' value=<%=StringUtil.escapeNull(org)%> ></div>
		<div class='left'>制定时间：</div>
		<div class='left'><input id="creattime" type="text" class="text" size='10' value=<%=vo.getCreateTime()==null?"":vo.getCreateTime().toString()%> ></div>
		<div class='left'>修改时间：</div>
		<div class='left'><input id="edittime" type="text" class="text" size='10' value=<%=vo.getEditTime()==null?"":vo.getEditTime().toString()%> ></div>
		<div class='left'>有效:</div>
		<div class='left'><input id="ck" type="checkbox" class="text" <%if(vo.getFlag()!=null && 0==vo.getFlag().intValue()){ %> checked <%} %>></div>
		<div></div>
		<div class="clear"></div>
		</div>
		<div>
		<div class='left'>发文号：</div>
		<div class='left'>
		<input id="rcNO" type="text" class="text" size='30'  value ="<%=StringUtil.escapeNull(vo.getRecordNo())%>" /></div>
		<div class="clear"></div>
		</div>
		<div>
		<div class='left'>备注：</div>
		<div class='left'>
		<textarea id="memo" cols="60" rows="2"><%=StringUtil.escapeNull(vo.getMemo())%></textarea>
		<input type="button" value="打印" onclick="printdiv('content')" />
		</div>
		<div class="clear"></div>
		</div>
		<hr/>
		<div id="content">
		<%=StringUtil.escapeNull(vo.getContent()) %>
		</div>
		</div>
	</BODY>
</HTML>


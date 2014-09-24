<%@ page contentType="text/html; charset=GBK" %>


<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.bbs.handler.BBSHandler" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%

List applylist = (List)request.getAttribute("applylist");
List applypersonNamelist = (List)request.getAttribute("applypersonNamelist");
String curUUid = (String)request.getAttribute("curUUid");
Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("applyBoardlist.jsp");

	BBSHandler handler = new BBSHandler(conn);
%>
<HTML><HEAD><TITLE>新华社论坛</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">
</head>

<body background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-08.gif">
<DIV align=center>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-10.gif" width="14" height="22"></td>
      <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-12.gif"  class="title-05">待审批板块列表</td>
      <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-11.gif" width="20" height="22"></div></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.gif" width="1" height="4"></td>
      <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-09.jpg">
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr  bgcolor="#FFFBEF">
                <td width="20%" height="22"><div align="center"><span class="title-04">申请板块名称</span></div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="20%" ><div align="center" class="title-04">申请所属专区</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="15%" ><div align="center" class="title-04">申请人</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="25%" ><div align="center" class="title-04">申请时间</div></td>
                <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-24.gif" width="2" height="2"></td>
                <td width="20%" ><div align="center" class="title-04">操作</div></td>
              </tr>
              <tr>
                <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	            <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
                <td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
           	 	<td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
	       	 	<td height="2"  background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
             </tr>              
		<%
		if(applylist != null&&applylist.size()>0){
			int showFlag = 1;
			Iterator Iterator = applylist.iterator();
			Iterator applyNameItr = applypersonNamelist.iterator();
			String cnname = "";
			String areaName = "";
			while(Iterator.hasNext()){
				BbsBoardVO vo = (BbsBoardVO)Iterator.next();
				cnname = applyNameItr.next().toString();
				areaName = handler.getAreaNameById(vo.getAreaid());
		%>
		<% showFlag ++;%>
        <%if(showFlag%2==1){%>
             <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
		<%}else{%>
			 <tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" >
		<%}%>
                <td height="22" class="text-01">
                	<div align="center">
                	<a title=查看此板块设置 href="#" class="text-01" onClick="javascript:_openBoardMsg('<%=request.getContextPath()%>/servlet/ShowSingleBoardServlet?boardId=<%=vo.getBoardid().toString()%>')"><%=vo.getBoardname()%></a>
                	</div>
                </td>
                <td class="text-01"><div align="center"><%=areaName%></div></td>
                <td class="text-01">
                	<div align="center">
					<A title=查看申请人“<%=cnname%>”的资料 href="#" onClick="javascript:_openUserMsg('<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=vo.getApplypersonuuid()%>&currUserId=<%=curUUid%>')"  class="text-01"><%=cnname%></a>
					</div>
				</td>
                <td  class="text-01"><div align="center"><%=CommUtil.getTime(vo.getCreattime().longValue())%></div></td>
                <td  class="text-01"><div align="center"><a href="#" onClick="javascript:_auditBoard('<%=request.getContextPath()%>/servlet/AuditApplyBoardServlet?boardId=<%=vo.getBoardid()%>')">通过审批</a></div></td>
              </tr>
<%
		}//while		
  	}else{     //if
%>
              <tr bgColor='#D8EAF8'>
                <td colspan="9" height="35" class="text-01">
                	<div align="center">没有待审批的板块</div>
                </td>
             </tr>			  
<%	}%>
              <tr>		
                 <td height="2" colspan="9" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-13.gif"></td>
             </tr>            
		</table></td>  
          </tr>
      </table></td>
      <td width="1" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.gif" width="1" height="4"></td>
    </tr>
  </table>
  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1%"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
      <td width="80%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01">
	     <%@ include file= "/include/defaultPageScrollBar.jsp" %>
	  </td>
      <td width="17%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"><div align="right"> </div></td>
      <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
    </tr>
  </table>
  
  <p>
	<a href="javascript:_goback('<%=request.getContextPath()%>')"><img src="<%=request.getContextPath()%>/images/botton-return.gif" width="70" height="22" hspace="10"  border=0></a>
  </p>
</div>
</body>

</html>
		<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					 ConnLog.close("applyBoardlist.jsp");
					}
			} catch (Exception e) {
		}
	}
%>

<script language="JavaScript">
function _auditBoard(url){
	if(confirm("确认要批准这个申请吗？")){
		document.location.href = url;
	}else{
		return false;
	}
}

function _goback(url){
  document.location.href = url+"/servlet/AreaManageServlet";
}

function _openBoardMsg(url){
	window.open(url,"","width=550,height=300,left=170,top=110,scrollbars=yes");
}

function _openUserMsg(url){
    window.open(url,"","");
}

</script>
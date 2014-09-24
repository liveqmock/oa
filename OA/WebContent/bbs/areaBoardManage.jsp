<%@ page contentType="text/html; charset=GBK" %>


<%response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%Collection bcollection = (Collection) request.getAttribute("boardList");
Collection scollection = (Collection) request.getAttribute("subareaList");
Long time = (Long) request.getAttribute("serverTime");
List managerList = (List) request.getAttribute("managerList");
String SuperAdminIds = (String)request.getAttribute("SuperAdminIds");
String cUserId = (String)request.getAttribute("cUserId");
Map areaManagerMap = (Map) request.getAttribute("areaManagerMap");
boolean haveRight = false;
%>
<html>
<head>
<title>新华社论坛</title>

<link href="<%=request.getContextPath()%>/Style/css.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />    
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<script language="JavaScript" type="text/JavaScript">

</script>
<SCRIPT src="<%=request.getContextPath()%>/include/move.js"></SCRIPT>
</head>
<body bgcolor="#ffffff">

<FORM name=form1 action="" method=post>
<jsp:include page= "/include/top.jsp" />
<table width="983" border="0" align="center" cellpadding="0"
	cellspacing="0">
    <tr><td height="5"></td></tr>
</table>
<table width="983" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td bgcolor="#FFFFFF">&nbsp;</td>
		<td colspan="5" valign="top"></td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">&nbsp;</td>
		<td colspan="5" valign="top">
		<table width="983" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
			<tr>
				<td width="38%" class="block_title" align="center">
					讨论区
				</td>
				<td width="10%" align="center" class="block_title">主题数</td>
				<td width="10%" align="center" class="block_title">总贴数</td>
				<td width="13%" align="center" class="block_title">版主</td>
				<td width="25%" class="block_title" align="center">删除/修改</td>
			</tr>
			<%Iterator AreaIterator = scollection.iterator();
			
while (AreaIterator.hasNext()) {
	
	BbsAreaVO areaVO = (BbsAreaVO) AreaIterator.next();
	if(SuperAdminIds.indexOf(cUserId)>=0){
		haveRight = true;
	}
	
	String managerIds = (String)areaManagerMap.get(areaVO.getAreaid());
	
	if(managerIds.indexOf(cUserId)>=0){
		haveRight = true;
	}
	if(haveRight){
%>
			<tr>
				<td height="24" colspan="4">
				<div align="center" class="blue3-12-b">
				<div align="left">&nbsp;&nbsp;≡ <%=areaVO.getAreaname()%> ≡</div>
				</div>
				</td>
				<td height="24">
				<table width="98%" align=center height="22">
					<tbody>
						<tr>
							<td width="49%">
							</td>
							<td width="51%"></td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
			<%Iterator BoardIterator = bcollection.iterator();
while (BoardIterator.hasNext()) {
	BbsBoardVO vo = (BbsBoardVO) BoardIterator.next();
	if (vo.getAreaid().toString().equals(areaVO.getAreaid().toString())) {
%>
			<tr bgcolor="FFFFFF">
				<td width="40%" height="50">
				<table width="98%" align=center>
					<tbody>
						<tr>
							<td class=blue3-12 rowspan=2></td>
							<td class=blue3-12 align=left><a> <%=vo.getBoardname()%></a></td>
							<td class=blue3-12 align=right width="20%">&nbsp;</td>
						</tr>
						<tr>
							<td class="blue3-12" align=left width="100%" colspan=2><%if (vo.getBoarddes() != null) {%>
							<%=vo.getBoarddes()%> <%}%></td>
						</tr>
					</tbody>
				</table>
				<td width="10%" height="50" class="blue3-12">
				<div align="center"><%=vo.getTopicnum()%></div>
				</td>
				<td width="10%" height="50" class="blue3-12">
				<div align="center"><%=vo.getArticlenum()%></div>
				</td>
				<td width="10%" height="50">
				<div align="center" class="blue3-12">
				<div align="center"><a class="blue3-12"> <%Iterator managerIterator = managerList.iterator();

ManagerUserinfoVO managerVO = null;
while (managerIterator.hasNext()) {
	managerVO = (ManagerUserinfoVO) managerIterator.next();
	if (managerVO.getBoardid().equals(vo.getBoardid())) {%> <%=managerVO.getTruename()%></a><br>
				<%} //if
} //while%></div>
				</div>
				</td>
				<td width="30%" height="50" nowrap>
				<div align="center">
				<table width="98%" align=center height="22">
					<tbody>
						<tr>
							<td width="49%" class="blue3-12"><a style="text-decoration: none"
								href="#" class="blue3-12"
								onClick="javascript:_delBoard('<%=request.getContextPath()%>','<%=vo.getBoardid()%>')">
							<div align="center">删除</div>
							</a></td>
							<td width="51%" class="blue3-12"><a style="text-decoration: none"
								href="#" class="blue3-12"
								onClick="javascript:_openBoard('<%=request.getContextPath()%>/servlet/ShowSingleBoardServlet?boardId=<%=vo.getBoardid().toString()%>')">
							<div align="center">编辑</div>
							</a></td>
						</tr>
					</tbody>
				</table>
				</div>
				</td>
			</tr>
			<%} //if boardid.equals(areaid)
} //while board
} //while right
haveRight = false;
} //while are%>
			<tr>
				<td bgcolor="#FFFFFF" colspan=5 align="left" class="blue2-12">
				<div align="center" class="blue2-12">
								
					<image
						src="<%=request.getContextPath()%>/images/bbs/button-addboard.gif"
						style="cursor:hand"
						onclick="javascript:_openBoard('<%=request.getContextPath()%>/bbs/newBoard.jsp')">
					
				</td>
			</tr>
		</table>
		<div align="center">
		<TABLE width="98%" border=0 cellPadding=0 cellSpacing=0
			class="text-01">
			<TBODY>
				<TR>
					<TD width="100%" height=23>
					<div align="center">
					  <!--<image src="<%=request.getContextPath()%>/images/bbs/button-boardaudit.gif" style="cursor:hand"  onclick="javascript:_auditBoard('<%=request.getContextPath()%>/servlet/ShowApplyBoardServlet')">-->
					</div>				  </TD>
				</TR>
			</TBODY>
		</TABLE>
		</div>


		</td>
	</tr>
</table>
</form>

</body>
</html>

<script language="JavaScript">
function _auditBoard(url){
	document.location.href = url;
}


function _mergeBoard(url){
	window.open(url,"","width=500,height=210,left=170,top=110,scrollbars=yes");
}

function _openBoard(url){
	window.open(url,"","width=550,height=350,left=170,top=110,scrollbars=yes");
}


function _delBoard(url,boardId){
	if(confirm("你确认要删除此板块吗？板块里的所有贴子都将被删除！")){
		document.form1.action=url+"/servlet/DelBoardServlet?boardId="+boardId;
    	document.form1.submit();
    	return true;
    }
}

</script>
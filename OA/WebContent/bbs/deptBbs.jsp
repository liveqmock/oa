<%@ page contentType="text/html; charset=GBK" %>
<%response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>

<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.bbs.handler.BBSHandler" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%

List list = (List) request.getAttribute("topicList");
int curPageNum1 = com.icss.j2ee.util.PageScrollUtil.getPageNum();
int pageCount1 = com.icss.j2ee.util.PageScrollUtil.getPageCount();
int tiao1  = com.icss.j2ee.util.PageScrollUtil.getRowCount();
	
String firstLogin = (String) request.getAttribute("firstLogin");
BbsUserinfoVO userVO = (BbsUserinfoVO) request.getAttribute("userVO");

Collection bcollection = (Collection) request.getAttribute("boardList");
Collection sbcollection = (Collection) request.getAttribute("subareaList");
//System.out.println("----------area size "+sbcollection.size());
List wholerightList = (List) request.getAttribute("rightList");
List wholemanagerList = (List) request.getAttribute("managerList");

List areaRightList = (List) request.getAttribute("areaRightList");
Connection conn = null;
try {
	conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("privalIndex.jsp");

	BBSHandler handler = new BBSHandler(conn);
%>

<HTML>
<HEAD>
<TITLE>新华社论坛</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>

<link href="/Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />

</head>

<body>
<form name="form1" method="post" action="">
<%@ include file= "/include/top.jsp" %>

<table width="983" border="0" cellspacing="0" cellpadding="0"
	align="center">

	<tr>
		
		<td colspan="5" valign="top">&nbsp;</td>
	</tr>
	<tr>
		
		<td colspan="5" valign="top">
		<table width="983" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
	 		<tr>
				<td width="4%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">&nbsp;</td>
				<td width="38%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">
				<div align="center" class="white2-12-b">讨论区</div>
				</div>
				</td>
			  <td width="6%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">主题</td>
			  <td width="7%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">总贴数</td>
			  <td width="13%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">最后发表</td>
			  <td width="32%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">版主</div>
				</td>
		  </tr>
			<%
String area_name = "";
Iterator AreaIterator = sbcollection.iterator();
while (AreaIterator.hasNext()) {
	BbsAreaVO areaVO = (BbsAreaVO) AreaIterator.next();
	String areaRightString ="";
	if("1".equals(areaVO.getArearight())){
		for(int n=0;n<areaRightList.size();n++){
			BbsAreaccVO areaccvo = (BbsAreaccVO)areaRightList.get(n);
			areaRightString += areaccvo.getUserid()+"||";
		}		
	}
	//System.out.println("areaRightString=:"+areaRightString);
	//System.out.println("userVO.getUserid().toString()=:"+userVO.getUserid().toString());
	if(!"1".equals(areaVO.getArearight())||areaRightString.indexOf(userVO.getUserid().toString())>=0){
	%>
			<tr>
				<td colspan="6" class="message_title_bold" height="20">&nbsp;&nbsp;&nbsp;<%=areaVO.getAreaname()%></td>
		  </tr>
			<%Iterator BoardIterator = bcollection.iterator();
while (BoardIterator.hasNext()) {
	BbsBoardVO vo = (BbsBoardVO) BoardIterator.next();
	String rightflag = "";
	if (vo.getAreaid().toString().equals(areaVO.getAreaid().toString()))
		//此专区的讨论区
		{
		if (wholerightList.size() == 0) { //当前登录的人或分组未受限制
			if (vo.getPermit().equals("1")) {
				rightflag = "0"; //当前登录的人或分组不可进
			} //if
			else {
				rightflag = "1"; //当前登录的人或分组可进
			} //else
		} else {
			if (vo.getIslimited().equals("0")) { //无权限限制（未选择人或分组）
				if (vo.getPermit().equals("1")) {
					rightflag = "0"; //当前登录的人或分组不可进
				} //if
				else {
					rightflag = "1"; //当前登录的人或分组可进
				} //else
			} else { //选择了人或分组&&当前登录的人或分组受限制
				for (int i = 0;
					i < wholerightList.size();
					i++) { //rightList 存放当前登录用户在BoardAcc中相关的记录
					BbsBoardaccVO bvo = (BbsBoardaccVO) wholerightList.get(i);
					if (vo.getBoardid().equals(bvo.getBoardid())) {
						//当前用户的受限板块ID:bvo.getBoardid()
						if (vo.getPermit().equals("1")) {
							rightflag = "1"; //当前登录者可以进入
						} //if
						else {
							rightflag = "0";
						} //else
						break; //一个板块对一个用户只能有一次限制，所以找到相等的后就break
					} //if
					else {
						if (vo.getPermit().equals("1")) {
							rightflag = "0"; //当前登录者可以进入
						} //if
						else {
							rightflag = "1";
						} //else
					}
				} //for
			} //else
		} //else
%>
			<tr>
				<td width="40" height="20" align="center" nowrap bgcolor="#FFFFFF"><%if (rightflag.equals("1")) {%>
				<img src="<%=request.getContextPath()%>/images/bbs/forumnew.gif"
					border=0> <%} else {%> <a href="#"><img title="您没有进入该板块的权限"
					src="<%=request.getContextPath()%>/images/bbs/forumlock.gif"
					border=0></a> <%}%></td>
				<td bgcolor="#FFFFFF" align="center" height="20">
				<table width="98%">
					<tr>
						<td bgcolor="#FFFFFF" class="message_title_bold"><%if (rightflag.equals("1")) {%>
						<A class="message_title_bold" style="text-decoration: none"
							title="进入<%=vo.getBoardname()%>板块" href="#"
							onClick="javascript:_topic('<%=request.getContextPath()%>','<%=vo.getBoardid().toString()%>')"><%=vo.getBoardname()%></A>
						<%} else {%> <A title="您没有进入该板块的权限,请与管理员联系" href="#"><font
							color="#999999"><%=vo.getBoardname()%></font></A> <%} //else%> <!--<a href="#" class="STYLE4" style="text-decoration: none">项目信息公告区</a>--></td>
						<%if (rightflag.equals("1")) {%>
						<td align="right" bgcolor="#FFFFFF" class="message_title"><A
							title="在『<%=vo.getBoardname()%>』论坛内发新主题 "
							href="<%=request.getContextPath()%>/bbs/newArticle.jsp?boardId=<%=vo.getBoardid().toString()%>&boardName=<%=vo.getBoardname()%>&auditFlag=0"><IMG
							src="<%=request.getContextPath()%>/images/bbs/quickpost.gif"
							border=0></A><A href="#" style="text-decoration: none"
							onClick="javascript:_primeTopic('<%=request.getContextPath()%>','<%=vo.getBoardid().toString()%>')">
						<IMG alt="查看『<%=vo.getBoardname()%>』论坛精华帖"
							src="<%=request.getContextPath()%>/images/bbs/quickjh.gif"
							border=0></A></td>
						<%} else {%>
						<TD align="right" bgcolor="#FFFFFF" class="message_title"><A
							style="text-decoration: none"
							title="您没有在『<%=vo.getBoardname()%>』论坛内发新主题的权限"><IMG
							src="<%=request.getContextPath()%>/images/bbs/quickpost.gif"
							border=0></A><A style="text-decoration: none"><IMG
							alt="您没有查看『<%=vo.getBoardname()%>』论坛精华帖的权限"
							src="<%=request.getContextPath()%>/images/bbs/quickjh.gif"
							border=0></A></TD>
						<%}%>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" class="message_title"> <%if (vo.getBoarddes() != null) {%>
						<%=vo.getBoarddes()%> <%}%></td>
					</tr>
				</table>
				</td>
				<td bgcolor="#FFFFFF" class="message_title" align="center"><span
					class="STYLE3"><%=vo.getTopicnum()%></span></td>
				<td bgcolor="#FFFFFF" class="message_title" align="center"><span
					class="STYLE3"><%=vo.getArticlenum()%></span></td>
				<%
					String lastname = vo.getLastusername();					
					//String lasttime = CommUtil.getTime(vo.getLasttime().longValue());
					if(lastname==null){
					%>
						<td align="center" bgcolor="#FFFFFF" class="message_title">最后发帖不存在<br>或已被删除</td>
					<%
					}
					else{%>
				<td align="center" bgcolor="#FFFFFF" class="message_title"><span
					class="STYLE3"><%=CommUtil.getTime(vo.getLasttime().longValue())%><BR>
				by 
					<span  class="message_title">
					<%=vo.getLastusername()%>
				</span></span></td><%}%>
				<td align="center" bgcolor="#FFFFFF" class="message_title"><%Iterator managerIterator = wholemanagerList.iterator();

ManagerUserinfoVO managerVO = null;
String ManagerIds = "";
while (managerIterator.hasNext()) {
	managerVO = (ManagerUserinfoVO) managerIterator.next();
	if (managerVO.getBoardid().equals(vo.getBoardid())) {
		ManagerIds += managerVO.getUserid()+"||";
	%> 
					<span class="message_title">
					<%=managerVO.getTruename()%></span> <%if (managerVO.getUserid().equals(userVO.getUserid())) {%>
				 <%}%> <%}
}%>
<%if(ManagerIds.indexOf(userVO.getUserid())>=0){%>
	<div align="center" class="message_title_bold" >
	<span >
		<a style="text-decoration: none" title=帖子管理 href="#"
		onClick="javascript:window.location.href='<%=request.getContextPath()%>/servlet/ShowTopicServlet?boardId=<%=vo.getBoardid().toString()%>&primeFlag=0&manageFlag=1'">
				帖子管理
		</a>
	</span>
	<span>
	<a style="text-decoration: none" title=查看此板块设置  href="#"
		onClick="javascript:_openBoard('<%=request.getContextPath()%>/servlet/ShowSingleBoardServlet?boardId=<%=vo.getBoardid().toString()%>')">
				板块设置
		</a>
	</span>
	</div>
				<%}%>
</td>
			</tr>
			<%}
}}
}


%>
		</table>
	  </td>
		
	</tr>
	<tr>
		<!--td width="11" bgcolor="#FFFFFF"><img src="../images/kongbai.jpg"
			width="11" height="11" /></td-->
		<td colspan="5" valign="top">
		<div align="left"></div>
		</td>
		<!--td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td-->
	</tr>
<%@ include file= "/include/defaultPageScrollBar2.jsp" %>
		<!--td width="175" align="right" valign="bottom"><<a href="Page.htm"><img
			src="../images/newtopic.gif" border="0"></a><img
			src="../images/newspecial.gif">--></td>
		<!-- td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td-->
	</tr>
	

	<tr>
		<!--td width="11" bgcolor="#FFFFFF"><img src="../images/kongbai.jpg"
			width="11" height="11" /></td-->
		<td colspan="5" valign="top">
		<table width="983" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
	  <tr>
				<td width="5%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title" nowrap>状态</td>
			  <td width="5%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title" nowrap>图标</td>
			  <td width="39%" height="24" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center" class="white2-12-b">主题</div>
				</td>
		    <!--<td width="5%" background="/OA/images/2-title-05.jpg" bgcolor="#E0EDF8">&nbsp;</td>-->
				<td width="12%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">
				<div align="center" class="white2-12-b">话题发起人</div>
				</div>
				</td>
			  <td width="9%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">点击</div>
				</td>
			  <td width="9%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">回复</div>
				</td>
			  <td width="21%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">最后发表</div>
				</td>
		  </tr>
			<%
			if(list != null&&list.size()>0){
				int showFlag = 1;
				Iterator Iterator = list.iterator();
				while(Iterator.hasNext()){
					ArticleUserinfoVO vo = (ArticleUserinfoVO)Iterator.next();

				if (showFlag % 2 == 1) {%>
					<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';" onMouseOver="this.bgColor='#E0EDF8';">
				<%} else {%>
					<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';" onMouseOver="this.bgColor='#E0EDF8';">
				<%}%>
				<td height="22" class="message_title">
				<div align="center"><%if (vo.getTop().equals("1")) {%><IMG alt=固顶贴
					src="<%=request.getContextPath()%>/images/bbs/locktop.gif"> <%} else if (vo.getArticlelock().equals("1")) {%><IMG
					alt=主题已关闭 src="<%=request.getContextPath()%>/images/bbs/lock.gif">
				<%} else if (vo.getHitnum().intValue() <= 30) {%> <IMG alt=普通贴
					src="<%=request.getContextPath()%>/images/bbs/closed.gif"> <%} else if (vo.getHitnum().intValue() > 30) {%><IMG
					alt=热帖 src="<%=request.getContextPath()%>/images/bbs/hotclosed.gif"><%}%></div>
				</td>
				<td class="message_title">
				<div align="center"><A href=# style="text-decoration: none"><IMG
					src="<%=request.getContextPath()%>/images/bbs/reArticle/<%=vo.getFace()%>"
					border=0></A></div>
				</td>
				<td class="message_title">
				<div align="left"><%if (vo.getPrime().equals("1")) {%> <img alt=精华
					src="<%=request.getContextPath()%>/images/bbs/jh.gif"> <%}%> <A
					class="message_title" style="text-decoration: none"
					title="主题：<%=vo.getArticlename()%>！&#13;&#10;观看：<%=vo.getHitnum()%>次&#13;&#10;回复：<%=vo.getRenum()%>篇&#13;&#10;发起人：&#13;&#10;发起时间：<%=CommUtil.getTime(vo.getEmittime().longValue())%>&#13;&#10;最后回复：<%if (vo.getReuserid() != null) {%><%=vo.getReusername()%><%}%>	&#13;&#10;最后回复时间：<%if (vo.getRetime() != null) {
	out.print(CommUtil.getTime(vo.getRetime().longValue()));
}%> &#13;&#10;主题内容大小：122字节"
					href="#"
					onClick="javascript:_article('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=vo.getBoardid().toString()%>')"><%=vo.getArticlename()%>
				</A></div>
				</td>
				<td class="message_title">
				<div align="center">
					<span class="message_title">
					<%=handler.getUserName(vo.getUserid())%></span>
				</div>
				</td>
				<td class="message_title">
				<div align="center"><%=vo.getHitnum()%></div>
				</td>
				<td class="message_title">
				<div align="center"><%=vo.getRenum()%></div>
				</td>
				<td height="22">
				<div align="center" class="message_title"><%if (vo.getReuserid() != null) {%>
				
				<span class="message_title">
					<%=vo.getReusername()%></span>|<%}%>
				<%if (vo.getRetime() != null) {
	%><%=CommUtil.getTime(vo.getRetime().longValue())%><%
}%></div>
				</td>
			</tr>

			<%}
//while		
} //if
%>
		</table>
	  </td>
		
	</tr>
    
<%@ include file= "/include/defaultPageScrollBar2.jsp" %>
		<td width="175" align="right" valign="bottom"><!--<a href="Page.htm"><img
			src="../images/newtopic.gif" border="0"></a><img
			src="../images/newspecial.gif">--></td>
		
	</tr>
</table>
</body>

</html>
<%} catch (DBConnectionLocatorException e) {
	e.printStackTrace();

} finally {
	try {
		if (conn != null) {
			conn.close();
			ConnLog.close("privalIndex.jsp");
		}
	} catch (Exception e) {
	}
}

%>

<script language="JavaScript">
function _gohome(url){
  document.location.href = url+"/servlet/ShowIndexServlet";
}

function _openUserMsg(url){
window.open(url,"","");
}
 function _openBoard(url){
    window.open(url,"","width=550,height=350,left=170,top=110,scrollbars=yes");
 }
function _showTopic(url){

	boardId = boardTopic.value;
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();
 }
function _topic(url,boardId){
	document.form1.action=url+"/servlet/ShowDeptTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();	
}

function _article(url,topicId,boardId){
	document.form1.action=url+"/servlet/ShowDeptArticleServlet?topicId="+topicId+"&boardId="+boardId+"&hitFlag=1";
    document.form1.submit();
 }
 
 function _applyBoard(url){
 	//加一个参数表示为申请板块
 	window.open(url+"/bbs/newBoard.jsp?isApply=1","applyboard","width=550,height=350,left=170,top=110,scrollbars=yes");
 
 }
  function _primeTopic(url,boardId){
    window.location.href = url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=1";
 }
</script>
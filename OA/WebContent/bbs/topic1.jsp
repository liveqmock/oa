<%@ page contentType="text/html; charset=GBK" %>


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
//用于下端刷新后下拉列表中的选择不变
String articleMsg = request.getParameter("articleMsg");
String order = request.getParameter("order");
String _time = request.getParameter("_time");

Collection scollection = (Collection) request.getAttribute("topicList");
System.out.println("topic list size is ........... "+scollection.size());

Collection bcollection = (Collection) request.getAttribute("boardList");
Collection sbcollection = (Collection) request.getAttribute("subareaList");

BbsBoardVO boardVO = (BbsBoardVO) request.getAttribute("boardVO");
String boardId = boardVO.getBoardid().toString();
BbsUserinfoVO userVO = (BbsUserinfoVO) request.getAttribute("userVO");

Connection conn = null;
try {
	conn =
		DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("topic.jsp");
	
%>
<html>
<head>
<title>新华社论坛</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<!--<link rel="stylesheet" href="../include/style.css">-->
<link href="/Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<script type="text/javascript" src="../include/judge.js"></script>
<SCRIPT src="../include/js/common.js"></SCRIPT>
<script language="JavaScript" src="../include/formVerify.js"></script>
<script language="JavaScript" src="../include/runFormVerify.js"></script>
<script language="JavaScript" src="../include/extendString.js"></script>
<script language="javascript">
function _topic(url,boardId){
	document.form1.action=url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=0";
    document.form1.submit();	
}
function _article(url,topicId,boardId){
	document.form1.action=url+"/servlet/ShowArticleServlet?topicId="+topicId+"&boardId="+boardId+"&hitFlag=1";
    document.form1.submit();
 }
function _newArticle(url){
	if(document.form2.articleName.value==""){
		alert("请填写主题！");
		return false;
	}else{
		//alert('<%=boardId%>');
		document.form2.action=url+"/servlet/NewArticleServlet";
    	document.form2.submit();
    	return true;
    }
 }
 function _openBoard(url){
    window.open(url,"","width=550,height=350,left=170,top=110,scrollbars=yes");
 }
 function _openUserMsg(url){
window.open(url,"","");
}
 function _primeTopic(url,boardId){
    window.location.href = url+"/servlet/ShowTopicServlet?boardId="+boardId+"&primeFlag=1";
 }
 
 function max(message,max)
	{
	//var max=1500;
	document.getElementById('zishu').innerHTML='您已经输入了<font color=red>'+message.value.length+'</font>字'
	if (message.value.length > max) {
	message.value = message.value.substring(0,max);
	alert("内容不能超过 "+max+" 个字!");
	 }
	}
</script>

</head>
<BODY text="#000000" leftMargin="0" topMargin="10">
<form name="form1" action="" method=post target="_self">


<%
	String area_name = "";
	if(request.getAttribute("areaname")!=null){
		area_name = (String)request.getAttribute("areaname");
	}
	
%>

<table width="1003" border="0" cellspacing="0" cellpadding="0"
	align="center">

  <tr>
  <td cosplan='10'>
  <table><tr>
		<td height="5" colspan="5" valign="top"></td>
	<%@ include file= "/include/defaultPageScrollBar2.jsp" %>
	<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
	</tr>
	</table>
	</td>
	</tr>
	<tr>
	  <td colspan="5" valign="top">
		<div align="left">
		<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#FFFFFF
			border=0>
			
			
			
			
		</TABLE>
		</div>		</td>
		<td width="21"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
	</tr>

	<tr>
	  <td colspan="5" valign="top">
		<table width="983" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
	  <tr>
				<td width="5%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title" nowrap>状态</td>
			  <td width="5%" align="center" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title" nowrap>图标</td>
			  <td width="39%" height="24" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center" class="white2-12-b">主题</div>				</td>
		    <!--<td width="5%" background="/OA/images/2-title-05.jpg" bgcolor="#E0EDF8">&nbsp;</td>-->
				<td width="12%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">
				<div align="center" class="white2-12-b">话题发起人</div>
				</div>				</td>
			  <td width="9%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">点击</div>				</td>
			  <td width="9%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">回复</div>				</td>
			  <td width="21%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" class="block_title">
				<div align="center">最后发表</div>				</td>
		  </tr>
			<%if (scollection != null) {
	int showFlag = 1;
	Iterator Iterator = scollection.iterator();
	while (Iterator.hasNext()) {
		ArticleUserinfoVO vo = (ArticleUserinfoVO) Iterator.next();

		if (showFlag % 2 == 1) {%>
			<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';"
				onMouseOver="this.bgColor='#E0EDF8';">
				<%} else {%>
			<tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';"
				onMouseOver="this.bgColor='#E0EDF8';">
	      <%}%>
				<td height="26" class="blue3-12">
				<div align="center"><%if (vo.getTop().equals("1")) {%><IMG alt=固顶贴
					src="<%=request.getContextPath()%>/images/bbs/locktop.gif"> <%} else if (vo.getArticlelock().equals("1")) {%><IMG
					alt=主题已关闭 src="<%=request.getContextPath()%>/images/bbs/lock.gif">
			  <%} else if (vo.getHitnum().intValue() <= 30) {%> <IMG alt=普通贴
					src="<%=request.getContextPath()%>/images/bbs/closed.gif"> <%} else if (vo.getHitnum().intValue() > 30) {%><IMG
					alt=热帖 src="<%=request.getContextPath()%>/images/bbs/hotclosed.gif"><%}%></div>				</td>
				<td class="blue3-12">
				<div align="center"><A href=# style="text-decoration: none"><IMG
					src="<%=request.getContextPath()%>/images/bbs/reArticle/<%=vo.getFace()%>"
					border=0></A></div>				</td>
				<td class="blue3-12">
				<div align="left" class="message_title">
				  <%if (vo.getPrime().equals("1")) {%> <img alt=精华
					src="<%=request.getContextPath()%>/images/bbs/jh.gif"> <%}%> 
                    <A class="blue3-12" style="text-decoration: none"
					title="主题：<%=vo.getArticlename()%>！&#13;&#10;观看：<%=vo.getHitnum()%>次&#13;&#10;回复：<%=vo.getRenum()%>篇&#13;&#10;发起人：<%=vo.getTurename()%>&#13;&#10;发起时间：<%=CommUtil.getTime(vo.getEmittime().longValue())%>&#13;&#10;最后回复：<%if (vo.getReuserid() != null) {%><%=vo.getReusername()%><%}%>	&#13;&#10;最后回复时间：<%if (vo.getRetime() != null) {
	out.print(CommUtil.getTime(vo.getRetime().longValue()));
}%> &#13;&#10;主题内容大小：122字节"
					href="#"
					onClick="javascript:_article('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=boardId%>')" target="_self"><%=vo.getArticlename()%>
				</A></div>			  </td>
				<td class="blue3-12">
				<div align="center" class="message_title">
                    <span onClick="window.open('<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getUserid()%>','个人信息','width=620,height=240,status=no,toolbar=no,menubar=no,scrollbars=no,location=no,status=no')" style="cursor:hand;" class="blue3-12">
					<%=vo.getTurename()%></span>				</div>			  </td>
				<td class="blue3-12">
				<div align="center" class="message_title"><%=vo.getHitnum()%></div>			  </td>
				<td class="blue3-12">
				<div align="center" class="message_title"><%=vo.getRenum()%></div>			  </td>
				<td height="26">
				<div align="center" class="message_title">
				  <%if (vo.getReuserid() != null) {%>
				
					<span onClick="window.open('<%=request.getContextPath()%>/servlet/UserInfoServlet?personuuid=<%=vo.getReuserid()%>','个人信息','width=620,height=240,status=no,toolbar=no,menubar=no,scrollbars=no,location=no,status=no')" style="cursor:hand;" class="blue3-12">
					<%=vo.getReusername()%></span>|<%}%>
			  <%if (vo.getRetime() != null) {
	out.print(CommUtil.getTime(vo.getRetime().longValue()));
}%></div>			  </td>
			</tr>

			<%}
//while		
} //if
%>
		</table>	  </td>
		<td width="21"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
	</tr>
	
	 <tr>
	<td cosplan='10'>
	<table><tr>
		<td height="5" colspan="5" valign="top"></td>
	<%@ include file= "/include/defaultPageScrollBar2.jsp" %>
	<td width="11"><img src="../images/kongbai.jpg" width="11" height="11" /></td>
	</tr>
	</table>
	</td>
	</tr>
</table>
</form>
<form name="form2" action="" enctype="multipart/form-data" method=post>

</form>
</body>
</html>
<%} catch (DBConnectionLocatorException e) {
	e.printStackTrace();

} finally {
	try {
		if (conn != null) {
			conn.close();
			ConnLog.close("topic.jsp");
		}
	} catch (Exception e) {
	}
}
%>

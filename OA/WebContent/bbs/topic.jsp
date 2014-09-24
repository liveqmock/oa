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

<%@ include file= "/include/top.jsp" %>

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
			<tr>
				<td width="75%" bgColor=#FFFFFF class="message_title_bold"><IMG title=到论坛首页
					style="CURSOR: hand"
					onClick="javascript:_gohome('<%=request.getContextPath()%>');"
					src="../images/bbshome.gif"><a style="text-decoration: none"
					href="<%=request.getContextPath()%>/servlet/ShowIndexServlet"
					title="到论坛首页">论坛首页</a> ==&gt; <%=area_name%> ==&gt; <a href="#"
					class="message_title_bold" style="text-decoration: none"><%=boardVO.getBoardname()%></a></td>
				<td width="25%" align="right" nowrap bgColor=#f7f7f7><!--<a href="#"
					class="blue3-12" style="cursor: hand; text-decoration: none">全部</a>
				<a href="#" class="blue3-12"
					style="cursor: hand; text-decoration: none">精华</a>&nbsp;&nbsp;--></td>
			</tr>
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
<table width="1003" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="2" valign="top">
		<table width="983" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
	  <tr>
		<td width="100%" height="23" colspan=3 class="block_title" align="center">快速发帖			  </td>
		  </tr>
			<tr>
				<td width="15%" height="26" class="message_title_bold" bgcolor="#FFFFFF">
				<div align="right">主题：</div>				</td>
			  <td bgcolor="#FFFFFF" class="message_title" colspan=2><input
					name="articleName" maxlength=30 class="blue2-12" size=61>（不得超过30个汉字）</td>
		  </tr>
			<tr>
				<td height="44" class="message_title_bold" bgcolor="#FFFFFF">
				<div align="right">当前心情&#65306;</div>				</td>
			  <td bgcolor="#FFFFFF" class="blue2-12" colspan=2>
				<table height="44" border="0" cellpadding="0" cellspacing="0"
					bgcolor="#FFFFFF">
					<tr>
						<td>
						<div align="center"><input type="radio" name="face" value="0.gif"
							CHECKED></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/0.gif" width="21"
							height="21"></div>						</td>
						<td>
						<div align="center"><input type="radio" name="face" value="1.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/1.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="10.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/10.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="12.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/12.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="13.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/13.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="15.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/15.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="16.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/16.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="17.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/17.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="5.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/5.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="8.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/8.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="18.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/18.gif" width="21"
							height="21"></div>						</td>
					</tr>
					<tr>
						<td>
						<div align="center"><input type="radio" name="face" value="20.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/20.gif" width="21"
							height="21"></div>
						<div align="center"></div>						</td>
						<td>
						<div align="center"><input type="radio" name="face" value="21.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/21.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="23.gif"></div>						</td>
						<td class="blue2-12"><img src="../images/23.gif" width="21"
							height="21">
						<div align="center"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="24.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/24.gif" width="21"
							height="21"></div>
						<div align="center"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="26.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/26.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="27.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/27.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="29.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/29.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="3.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/3.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="6.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/6.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="9.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/9.gif" width="21"
							height="21"></div>						</td>
						<td class="blue2-12">
						<div align="center"><input type="radio" name="face" value="4.gif"></div>						</td>
						<td class="blue2-12">
						<div align="center"><img src="../images/4.gif" width="21"
							height="21"></div>						</td>
					</tr>
				</table>				</td>
			</tr>
			<tr>
				<td height="26" bgcolor="#FFFFFF" class="message_title_bold">
				<div align="right" class="blue2-12">内容&#65306;</div>				</td>
			  <td class="message_title" bgcolor="#FFFFFF" colspan=2><textarea
					name="content" cols="80" rows="8" class="blue2-12" onKeyDown='max(this,1500)' onKeyUP='max(this,1500)'></textarea>
							<div class="message_title" id='zishu' ></div>
					<!-- <input type="checkbox" name="checkbox3" value="checked">是否加入待办事宜 --></td>
		  </tr>
			<tr>
				<td height="26" bgcolor="#FFFFFF" class="message_title_bold">
				<div align="right" class="blue2-12">上传文件&#65306;</div>				</td>
			  <TD bgColor="#FFFFFF" class="blue2-12" colspan=2><input type="file"
					name="acc" size=50></TD>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF" colspan=3 align="left" class="message_title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
					src="../images/button_ok.gif" width="59" height="19" hspace="5"
					onclick="javascript:_newArticle('<%=request.getContextPath()%>')">帖子长度：&nbsp;最大为：1500字，最小为0字&nbsp;</td>
		  </tr>
			<INPUT TYPE="hidden" name="boardId" value="<%=boardId%>">
			<INPUT TYPE="hidden" name="auditFlag" value="0">
		</table>	  </td>
</table>
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

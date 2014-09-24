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
List arealist = (List)request.getAttribute("subareaList");
List boardlist = (List)request.getAttribute("boardList");
List topiclist = (List)request.getAttribute("topicList");
Map boardmap = (Map)request.getAttribute("boardmap");
BbsUserinfoVO userVO = (BbsUserinfoVO) request.getAttribute("userVO");
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
	window.open(url+"/servlet/ShowArticleServlet?topicId="+topicId+"&boardId="+boardId+"&hitFlag=1");
    //document.form1.submit();
 }
function _newArticle(url){
	if(document.form2.articleName.value==""){
		alert("请填写主题！");
		return false;
	}else{
		
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

</script>

<SCRIPT LANGUAGE="JavaScript">
function changeStyle(id){//切换样式
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	//切换样式时设置COOKIE
	var cookies = document.cookie;
	var setcookies = "";
	var lastcookies = "";
	var Days = 30;
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	
	var deltime = new Date();
	daltime = exp.setTime (exp.getTime() - 1);    

	while(cookies.indexOf(";")>=0){
		var tempcookie = cookies.substring(0,cookies.indexOf(";"));
		cookies = cookies.substring(cookies.indexOf(";")+1);
		
		if(tempcookie.indexOf(name)>=0){
			//删除原来的COOKIE
			document.cookie = name+"="+value+";expires="+deltime.toGMTString();
		}
	}
	//设置新的样式
	document.cookie = name+"="+value+";expires="+exp.toGMTString()+";path=/;domain=10.102.1.40";
}

function getCookie(name){
	var cook =document.cookie;
	//alert(cook);
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(";"));
		var value = "grey";
		if(cook1.indexOf(";")>0){ 
			value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";"));
		}else{
			value = cook1.substring(cook1.indexOf("=")+1);
		}
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+style+".css";
}

initstyle();

</SCRIPT>
</head>
<BODY text="#000000" leftMargin="0" topMargin="10">
<form name="form1" action="" method=post>

<!--jsp:include page= "/include/top.jsp" /-->

<%
if(arealist!=null){
%>
	<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
    	<tr>
<%
	for(int i=0;i<arealist.size();i++){
		BbsAreaVO vo = (BbsAreaVO)arealist.get(i);
%>
		<td class="message_title_bold"><span onClick="window.open('/oabase/servlet/ShowDeptBBSServlet')" style="cursor:hand;"><%=vo.getAreaname()%></span></td><td width="15"></td>
<%
	}
%>
		</tr>
	</table>
<%
}
%>


<%

//帖子展示
if(topiclist!=null){
%>

<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
<tr><td>
<%@ include file= "/include/defaultPageScrollBar2.jsp" %>
</td><td width="175"></td></tr>
<tr><td height="5"></td></tr>
</table>


	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_bgcolor">
   			<tr>
				<td width="5%" align="center" class="block_title" nowrap>状态</td>
			  	<td width="5%" align="center" class="block_title" nowrap>图标</td>
			  	<td width="30%" height="24" align="center" class="block_title">主题</td>
				<td width="12%" align="center" class="block_title">话题发起人</td>
			  	<td width="9%" align="center" class="block_title">点击</td>
			  	<td width="9%" align="center" class="block_title">回复</td>
			  	<td width="30%" align="center" class="block_title">最后发表</td>
		  	</tr>
<%
	int showFlag = 1;
	for(int m=0;m<topiclist.size();m++){
		ArticleUserinfoVO vo = (ArticleUserinfoVO)topiclist.get(m);
		showFlag++;
%>
		
			
      <tr bgcolor="#FFFFFF" onMouseOut="this.bgColor='#FFFFFF';"
				onMouseOver="this.bgColor='#E0EDF8';">
       
        <td height="26" class="blue3-12"><div align="center">
          <%if (vo.getTop().equals("1")) {%>
          <img alt=固顶贴
					src="<%=request.getContextPath()%>/images/bbs/locktop.gif">
          <%} else if (vo.getArticlelock().equals("1")) {%>
          <img
					alt=主题已关闭 src="<%=request.getContextPath()%>/images/bbs/lock.gif">
          <%} else if (vo.getHitnum().intValue() <= 30) {%>
          <img alt=普通贴
					src="<%=request.getContextPath()%>/images/bbs/closed.gif">
          <%} else if (vo.getHitnum().intValue() > 30) {%>
          <img
					alt=热帖 src="<%=request.getContextPath()%>/images/bbs/hotclosed.gif">
          <%}%>
        </div></td>
        <td class="blue3-12"><div align="center"><a href=# style="text-decoration: none"><img
					src="<%=request.getContextPath()%>/images/bbs/reArticle/<%=vo.getFace()%>"
					border=0></a></div></td>
        <td class="blue3-12"><div align="left" class="message_title">
          <%if (vo.getPrime().equals("1")) {%>
          <img alt=精华
					src="<%=request.getContextPath()%>/images/bbs/jh.gif">
          <%}%>
        <a class="blue3-12" style="text-decoration: none"
					title="主题：<%=vo.getArticlename()%>！&#13;&#10;观看：<%=vo.getHitnum()%>次&#13;&#10;回复：<%=vo.getRenum()%>篇&#13;&#10;发起人：<%=vo.getTurename()%>&#13;&#10;发起时间：<%=CommUtil.getTime(vo.getEmittime().longValue())%>&#13;&#10;最后回复：<%if (vo.getReuserid() != null) {%><%=vo.getReusername()%><%}%>	&#13;&#10;最后回复时间：<%if (vo.getRetime() != null) {
	out.print(CommUtil.getTime(vo.getRetime().longValue()));
}%> &#13;&#10;主题内容大小：122字节"
					href="#"
					onClick="javascript:_article('<%=request.getContextPath()%>','<%=vo.getArticleid().toString()%>','<%=vo.getBoardid()%>')"><%=vo.getArticlename()%> </a></div></td>
        <td class="blue3-12"><div align="center" class="message_title"><a class="blue3-12" href="#"
					style="text-decoration: none"
					onClick="javascript:_openUserMsg('<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=vo.getUserid()%>&currUserId=<%=userVO.getUserid()%>')"><%=vo.getTurename()%></a> </div></td>
        <td class="blue3-12"><div align="center" class="message_title"><%=vo.getHitnum()%></div></td>
        <td class="blue3-12"><div align="center" class="message_title"><%=vo.getRenum()%></div></td>
        <td height="26"><div align="center" class="message_title">
          <%if (vo.getReuserid() != null) {%>
          <a class="blue3-12" href="#" style="text-decoration: none"
					onClick="javascript:_openUserMsg('<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=vo.getReuserid()%>&currUserId=<%=userVO.getUserid()%>')"><%=vo.getReusername()%></a>|
          <%}%>
          <%if (vo.getRetime() != null) {
	out.print(CommUtil.getTime(vo.getRetime().longValue()));
}%>
        </div></td>
      </tr>
<%
	}
%>
  </table>
<%
}
%>
		

</form>
</body>
</html>

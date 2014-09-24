<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfomainVO" %>
<%@ page import="com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfoVO" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>

<%
	TbYjsWorkinfomainVO vo = (TbYjsWorkinfomainVO)request.getAttribute("vo");
	List list = (List) request.getAttribute("list");
	HashMap  map = (HashMap) request.getAttribute("map");
	
	String keyword = (String) request.getAttribute("keyword");
	String title = "";
	Timestamp time = (Timestamp) vo.getWitDate();
	//System.err.println("Timestamp=" + time);
	String timestr = "";
	if (time != null) {
		timestr = time.toString().substring(0, 10);
	}
	String leader = vo.getWitLeader();
	String secret = vo.getWitSecret();
	title = timestr + " 工作日志 " + leader + "、" + secret;
%>
<html>
<head>
<title>工作日志信息查询结果查看</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">

<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />


<script language="javascript">
function _HightLight(nWord)
{
var oRange = document.body.createTextRange();
if(nWord!=""){
while(oRange.findText(nWord))
{
oRange.pasteHTML("<span style='background-color:yellow;color:red;'>" + oRange.text + "</span>");
oRange.moveStart('character',1);
}
}
}
</script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
</head>

<BODY onLoad="javascript:_HightLight('<%=keyword%>')">
<form action="" name="form1">
<jsp:include page= "/include/top.jsp" />
<br>
<br>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td width="100%"><font color =#0080FF><b><%=title%></b></font></td></tr>
<tr><td width="100%">&nbsp;</td></tr>
<%for(int i=0;i<list.size();i++){
	TbYjsWorkinfoVO infovo = (TbYjsWorkinfoVO)list.get(i);
	
%>
	<tr><td width="100%" style="font-size: 14pt;color =red"><b> ■<%=map.get(infovo.getWitId())%></b><br><br></td></tr>
	<tr><td width="100%"><%=StringUtil.escapeNull(infovo.getWitContent())%><br><br></td></tr>
<%}%>
</table>
</form>
</BODY></HTML>
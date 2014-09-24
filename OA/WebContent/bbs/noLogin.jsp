<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%
Integer a =(Integer)request.getAttribute("day");
%>
<HTML><HEAD><TITLE>新华社论坛</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id="homepagestyle"  rel="stylesheet" type="text/css" />

</head>
<body bgcolor='#E0EDF8'>
<jsp:include page= "/include/top.jsp" />
	<br/>
	<br/>
	<br/>
	<br/>
	<div id=show align='center' style="font-size:14pt;font-weight: bold;color:#006699;font-family:verdana;">
	您已经被管理员禁用论坛，剩余时间为<%=a%>天！
	</div>
	<br/>
	<br/>
	<div align='center' class="message_title"><a href="/cms/cms/website/index.html">如果无法跳转，请您直接点击链接进入办公信息系统首页</div>
	<br/>
	<br/><br/>
	<br/><br/>
	<br/><br/>
	<br/><br/>
	<br/><br/>
	<br/><br/>
	<br/>
<table width="983" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="983" height="54"><div align="center" class="foot_message"><a href="http://10.103.8.88" target="_blank" class="foot_message" style="text-decoration:none">多媒体数据库系统</a>│<a href="http://10.103.8.52" target="_blank" class="foot_message" style="text-decoration:none">多媒体待编稿库系统</a>│<a href="http://10.106.1.13" target="_blank" class="foot_message" style="text-decoration:none">eNews系统</a>│<a href="http://10.101.101.201:8081" target="_blank" class="foot_message" style="text-decoration:none">总社新闻编辑系统</a>│<a href="http://10.100.3.40:8081" target="_blank" class="foot_message" style="text-decoration:none">国内分社多媒体编辑系统</a>│<a href="http://10.101.106.88/" target="_blank" class="foot_message" style="text-decoration:none">多媒体eNews系统</a>│<a href="http://10.102.2.12" target="_blank" class="foot_message" style="text-decoration:none">稿件采用查询系统</a>│<a href="http://10.103.9.80" target="_blank" class="foot_message" style="text-decoration:none">信息部多媒体采编系统</a><br />
    <a href="http://10.102.1.206:8080/ggjs/" target="_blank" class="foot_message" style="text-decoration:none">图书/数字图书系统</a>│<a href="http://10.99.100.50" target="_blank" class="foot_message" style="text-decoration:none">图片/图表编辑系统</a>│<a href="http://10.102.2.11" target="_blank" class="foot_message" style="text-decoration:none">营销管理系统</a>│<a href="http://10.102.2.221" target="_blank" class="foot_message" style="text-decoration:none">防病毒管理系统</a></div></td>
  </tr>
  <tr>
    <td height="52" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 版权所有　　<br />
      制作单位：新华社技术局　（浏览本网主页，建议将电脑显示屏的分辨率调为1024*768）<br />
    </div></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript">

var n = 4;

function foward(){
	document.getElementById("show").innerHTML="您已经被管理员禁用论坛，剩余时间为<%=a%>天!("+n+")";
	if(n==1 || n==0)
	document.getElementById("show").innerHTML="正在准备跳转...("+n+")";
	//if(n==0)
	//document.getElementById("show").innerHTML="转到办公信息系统首页...";
	if(!n--)
	this.location="/cms/cms/website/index.html";
	setTimeout("foward();",1000);
}
foward();
</script>

<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%
Integer a =(Integer)request.getAttribute("day");
%>
<HTML><HEAD><TITLE>�»�����̳</TITLE>
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
	���Ѿ�������Ա������̳��ʣ��ʱ��Ϊ<%=a%>�죡
	</div>
	<br/>
	<br/>
	<div align='center' class="message_title"><a href="/cms/cms/website/index.html">����޷���ת������ֱ�ӵ�����ӽ���칫��Ϣϵͳ��ҳ</div>
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
    <td width="983" height="54"><div align="center" class="foot_message"><a href="http://10.103.8.88" target="_blank" class="foot_message" style="text-decoration:none">��ý�����ݿ�ϵͳ</a>��<a href="http://10.103.8.52" target="_blank" class="foot_message" style="text-decoration:none">��ý�������ϵͳ</a>��<a href="http://10.106.1.13" target="_blank" class="foot_message" style="text-decoration:none">eNewsϵͳ</a>��<a href="http://10.101.101.201:8081" target="_blank" class="foot_message" style="text-decoration:none">�������ű༭ϵͳ</a>��<a href="http://10.100.3.40:8081" target="_blank" class="foot_message" style="text-decoration:none">���ڷ����ý��༭ϵͳ</a>��<a href="http://10.101.106.88/" target="_blank" class="foot_message" style="text-decoration:none">��ý��eNewsϵͳ</a>��<a href="http://10.102.2.12" target="_blank" class="foot_message" style="text-decoration:none">������ò�ѯϵͳ</a>��<a href="http://10.103.9.80" target="_blank" class="foot_message" style="text-decoration:none">��Ϣ����ý��ɱ�ϵͳ</a><br />
    <a href="http://10.102.1.206:8080/ggjs/" target="_blank" class="foot_message" style="text-decoration:none">ͼ��/����ͼ��ϵͳ</a>��<a href="http://10.99.100.50" target="_blank" class="foot_message" style="text-decoration:none">ͼƬ/ͼ��༭ϵͳ</a>��<a href="http://10.102.2.11" target="_blank" class="foot_message" style="text-decoration:none">Ӫ������ϵͳ</a>��<a href="http://10.102.2.221" target="_blank" class="foot_message" style="text-decoration:none">����������ϵͳ</a></div></td>
  </tr>
  <tr>
    <td height="52" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 ��Ȩ���С���<br />
      ������λ���»��缼���֡������������ҳ�����齫������ʾ���ķֱ��ʵ�Ϊ1024*768��<br />
    </div></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript">

var n = 4;

function foward(){
	document.getElementById("show").innerHTML="���Ѿ�������Ա������̳��ʣ��ʱ��Ϊ<%=a%>��!("+n+")";
	if(n==1 || n==0)
	document.getElementById("show").innerHTML="����׼����ת...("+n+")";
	//if(n==0)
	//document.getElementById("show").innerHTML="ת���칫��Ϣϵͳ��ҳ...";
	if(!n--)
	this.location="/cms/cms/website/index.html";
	setTimeout("foward();",1000);
}
foward();
</script>

<%@ page pageEncoding="GBK" %>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.ftp.SearchResultVO;" %>



<%
	String contextPath = request.getContextPath();
	List infoList = new ArrayList();
	if(request.getAttribute("result")!=null)
	{
		infoList.addAll((List)request.getAttribute("result"));
	}
	String total = (String)request.getAttribute("total");
	String keyword = (String)request.getAttribute("keyword");
	String firstrecord = (String)request.getAttribute("firstrecord");
	String lastrecord = (String)request.getAttribute("lastrecord");

	
 %>
<html>
<HEAD>
<SCRIPT LANGUAGE="JavaScript">
function query()
{
	var sForm = document.frm;
	sForm.action = "<%=contextPath%>/servlet/FtpTrsServlet"
	sForm.submit();
}

function goto(type)
{
	var sForm = document.frm;
	sForm.action = "<%=contextPath%>/servlet/FtpTrsServlet"
	sForm.keyword.value = "<%=keyword%>";
	sForm.type.value = type;
	sForm.lastrecord.value = "<%=lastrecord%>";
	sForm.firstrecord.value = "<%=firstrecord%>";
	sForm.submit();
}


function _download(path){
	window.location.href = "<%=contextPath%>/servlet/FtpSearchDownloadServlet?path="+path;
}

function _inbox(path){
	
	var path = path.substring(0,path.lastIndexOf("/")+1);
	window.opener.intobox("/"+path);
}
</SCRIPT>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<TITLE>����ҳ��</TITLE>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 14px;
	color: #000000;
}
-->
</style>

<style type="text/css">
<!--
.TOPSTYLE {
	color: #FFFFFF;
	font-size: 12px;
}
-->
</style>
</HEAD>
<BODY>
<form method="post" name="frm">
<input type="hidden" name="lastrecord" value="">  
	<input type="hidden" name="firstrecord" value="">  
	<input type="hidden" name="type" value="">  
    
	<table width="1003" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="10">&nbsp;</td>
    <td width="983">
    <table border="0" cellpadding="0" cellspacing="0" class="top_back">
    <tr>
    <td width="536" class="top_logo" height="84"></td>
    <td class="top_back" height="84" width="137">&nbsp;</td>
    <td width="310" class="top_back" height="84">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
              <td width="135" height="30">&nbsp;</td>
        	  <td width="15" align="right" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><img src="/cms/images/top/top_home.png" border="0"/></td>
        	  <td width="40" align="center" valign="middle"><a href="/cms/cms/website/index.html" style="text-decoration:none"><span class="TOPSTYLE">��ҳ</span></a></td>
        	  <td width="15" align="right" valign="middle"><img src="/cms/images/top/top_help.png" /></td>
        	  <td width="40" align="center" valign="middle" class="TOPSTYLE">����</td>
        	  <td width="15" align="right" valign="middle"><a href="/resourceone/common/Logout.jsp" style="text-decoration:none"><img src="/cms/images/top/top_out.png" border="0" /></a></td>
        	  <td width="40" align="center" valign="middle" class="TOPSTYLE"><a href="/resourceone/common/Logout.jsp" style="text-decoration:none" class="TOPSTYLE">�˳�</a></td>
        	  </tr>
            <tr><td colspan="7">&nbsp;</td></tr>
            <tr><td colspan="7" height="30">&nbsp;</td></tr>
        </table>
    </td>
    </tr>
    </table>
    </td>
    <td width="10" >&nbsp;</td>
  </tr>
</table>
	
	<table border="0" cellpadding="0" cellspacing="0" width="983" align="center">
    	<tr><td height="5"></td></tr>
	</table>
    
    <table border="0" cellpadding="0" cellspacing="0" width="983" align="center">
	<tr>
	<td align="center" colspan="2" class="message_title">
	�ļ����Ϲ���ȫ�ļ��� <input name="keyword" type="text" value="<%=keyword%>" size="40">
    <input name="queryButton" type="submit" class="inputbutton" onClick="query();" value=" �� �� ">&nbsp;</td>
	</tr>
    <tr><td width="10"></td><td height="5"></td></tr>
    <tr>
    	<td height="1" bgcolor="#999999" colspan="2">		</td>
    </tr>
    <%if(infoList.size()>0 ){%>
    <tr>
      <td width="10" class="block_title"></td>
	  <td align="right" class="block_title">���� <%=total%> ���ѯ���������Ȩ�����⣬�������޷��鿴ȫ�������Ϣ���ݡ�</td>
	</tr>
    <%}else{%>
    <tr>
       <td width="10" class="block_title"></td>
	  <td align="right" class="block_title"><font color="red">���޼�¼</font>&nbsp;&nbsp;���������������ؼ��֣������������ť���м�����</td>
	</tr>
    <%}%>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" width="750" align="center">
	<%for(int i=0;i<infoList.size();i++) 
	 {
	 	SearchResultVO vo = (SearchResultVO)infoList.get(i);
	%>
	
	<tr>
    <td width="10"></td>
	<td height="30" class="message_title">
	 	<b><a href="#" onclick="javascript:_download('<%=vo.getDownload()%>')"><%=vo.getFilename()%></a></b><br>
		�ϴ�ʱ��:<%=vo.getCreatetime()%>
        </td>
	</tr>
	<tr>
    	<td width="10"></td>
        <td  height="25">
         <span class="message_title" >���ݸ�Ҫ:</span><span class="foot_message"><%=vo.getContent()%></span></td>
	</tr>
	<tr>
    	<td width="10"></td>
        <td class="message_title" height="25">
         �洢·��:  <%=vo.getDownloadhtml()%>&nbsp;&nbsp;&nbsp;<a href="javascript:_inbox('<%=vo.getDownload()%>')">����Ŀ¼</a>
        </td>
	</tr>
    <tr><td width="10"></td><td height="10"></td></tr>
	<%} %>
    </table>

	<%if(infoList.size()>0 ){%>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
	<td width="10"></td><td align="center">

	<input type="button" class="inputbutton" onClick="goto(0);" value="��һҳ" <%if("0".equals(firstrecord)){%> disabled<%} %>>
	<input type="button" class="inputbutton" onClick="goto(1);" value="��һҳ"  <%if(lastrecord.equals(total)){%> disabled<%} %>>
	
	</td>
	</tr>
    </table>
	<%} %>


 <table width="983" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td width="983" height="10"></td>
  </tr>
  <tr>
    <td height="52" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 ��Ȩ���С���<br />
      ������λ���»��缼���֡������������ҳ�����齫������ʾ���ķֱ��ʵ�Ϊ1024*768��<br />
    </div></td>
  </tr>
</table>
</form>
</BODY>
</html>
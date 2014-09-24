<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.oa.ftp.FtpAdminVO"%>
<%@ page import="com.icss.oa.address.vo.SysOrgVO"%>
<%@ page import="java.util.StringTokenizer"%>

<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.sms.handler.SMSHandler" %>
<%@ page import="com.icss.common.log.ConnLog" %>

<%
	String path = request.getContextPath();
	List dirlist = (List)request.getAttribute("dirlist");
	List orglist = (List)request.getAttribute("orglist");
	int num = dirlist.size();

	Connection conn = null;
	try {
		conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
		ConnLog.open("ftpadmin.jsp");

		SMSHandler handler = new SMSHandler(conn);
%>
<html>
<head>

<title>文件资料共享</title>
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
<link href="<%=path%>/Style/css_ftp.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE2 {
	font-family: "隶书";
	font-size: 36px;
	color: #FFFFFF;
	font-weight: bold;
	font-style: italic;
}
.STYLE6 {font-size: 12px}
.STYLE7 {
	color: #FFFFFF
}
.STYLE9 {font-size: 12px; color: #999999; }
.STYLE12 {
	font-size: 12px;
	color: #FF9900;
}
.STYLE13 {font-size: 14px}
-->
</style>
</head>

<script  language="javascript">
function changeStyle(id){//切换样式
	//alert("dddd");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	document.cookie=name+"="+value+",";
}
function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(",")); 
		var value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(","));
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+style+".css";
}

//initstyle();
</script>

<script>

function _new(){
	if(_check()){
		document.frm.action = "<%=path%>/servlet/NewFtpDirServlet";
		document.frm.submit();
	}
}

function _check(){
	if(document.frm.name.value == ""){
		alert("请输入目录名称！");
		return false;
	}
	if(document.frm.msgAccess.value == ""){
		alert("请选择管理员！");
		return false;
	}
	var obj = document.getElementById("tlist");
	var n = obj.rows.length;
	for(var i=1;i<n;i++){
		if(obj.rows[i].cells[1].innerHTML == document.frm.name.value){
			alert("有同名目录存在！");
			return false;
		}
	}
	var opt = document.frm.org.options;
	var sstr = "";
	for(var i=0;i<opt.length;i++){
		if(opt[i].selected){
			sstr = opt[i].innerHTML;
		}
	}
	for(var i=1;i<n;i++){
		if(obj.rows[i].cells[2].innerHTML == sstr){
			alert("该部门已经存在共享目录！");
			return false;
		}
	}
	return true;
}

function _checkmodify(){
	var cols = document.getElementsByName("col");  
	var x = "-1";
	var len  = cols.length;   
	for(var i = 0;i<len;i++){   
		if(cols[i].checked   ==   true){   
           x = cols[i].index;   
        }   
    }
	if(x == -1){
		alert("请选择一个要修改的对象!");
		return false;
	}

	if(document.frm.name.value == ""){
		alert("请输入目录名称！");
		return false;
	}
	if(document.frm.msgAccess.value == "" || frm.uuid.value==""){
		alert("请选择管理员！");
		return false;
	}
	return true;
}

function _checkdel(){
	var cols = document.getElementsByName("col");  
	var x = "-1";
	var len  = cols.length;   
	for(var i = 0;i<len;i++){   
		if(cols[i].checked   ==   true){   
           x = cols[i].index;   
        }   
    }
	if(x == -1){
		alert("请选择一个要修改的对象!");
		return false;
	}
	if(confirm("文件夹将被删除,你确认删除吗?")){
		return true;
	}else{
		return false;
	}
}

function  _modify(){
	if(_checkmodify()){
		document.frm.action = "<%=path%>/servlet/ModifyFtpDirServlet";
		document.frm.submit();
	}
}

function  _del(){
	if(_checkdel()){
		document.frm.action = "<%=path%>/servlet/DelFtpDirServlet";
		document.frm.submit();
	}
}

</script>

<body >

<form name="frm" method="post">

<input type="hidden" name="uuid" />

<jsp:include page="/include/top.jsp" />
<table border="0" cellpadding="0" cellspacing="0">
	<tr><td height="5"></td></tr>
</table>

<table id="tlist" border="0" cellpadding="0" cellspacing="1" width="983" align="center" bgcolor="#FFFFFF" class="table_bgcolor">
	<tr><td class="block_title" align="center" height="30">选择</td>
    <td class="block_title" align="center">目录名</td>
    <td class="block_title" align="center">所属组织</td>
    <td class="block_title" align="center">路径</td>
    <td class="block_title" align="center">管理员</td></tr>

 <%
	for(int i=0;i<dirlist.size();i++){
		FtpAdminVO fvo = (FtpAdminVO)dirlist.get(i);

		String a = fvo.getOwener();
		String adminnames = "";
		if(a!=null){
				StringTokenizer stz = new StringTokenizer(a,",");
				 while(stz.hasMoreElements()){
				 String tempid = (String)stz.nextElement();
				 adminnames+=handler.getPersonName(tempid);
				 adminnames+=",";
			}
		}
 %>
	<tr id="tr<%=i%>">
    <td bgcolor="#FFFFFF" align="center" height="30"><input type="radio" name="col" value="<%=fvo.getId()%>" index="<%=i%>" onclick="_curshow()" /></td>
	<td height="5" bgcolor="#FFFFFF" class="message_title"><%=fvo.getFolder_name()%></td>
	<td height="5" bgcolor="#FFFFFF" class="message_title"><%=fvo.getOrg()%></td>
	<td height="5" bgcolor="#FFFFFF" class="message_title"><%=fvo.getFolder_path()%></td>
	<td height="5" bgcolor="#FFFFFF" class="message_title"><%=adminnames%></td>
	<input type="hidden" id="<%=i%>uuid" value=<%=a%> />
	</tr>
<%
	}
%>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td height="15"></td></tr>
</table>

 	<table  border="0" cellpadding="0" cellspacing="0" width="983">
		<tr><td>&nbsp;&nbsp;</td><td height="30" class="message_title_bold">目录名</td><td><input type="text" name="name" value=""/></td></tr>
		<tr><td>&nbsp;&nbsp;</td><td height="30" class="message_title_bold">所属组织</td>
		<td><select name="org" onChange="_clearpeople()">
		<%
			for(int i=0;i<orglist.size();i++){
				SysOrgVO ovo = (SysOrgVO)orglist.get(i);
		%>
			<option value="<%=ovo.getOrguuid()%>"><%=ovo.getCnname()%></option>
		<%
			}
		%>
		</select></td></tr>
		<tr><td>&nbsp;&nbsp;</td><td class="message_title_bold" height="30">管理员</td></td><td><textarea name="msgAccess" cols="50" rows="1" readonly></textarea><input type="button" value="选择人员" onClick="javascript:_selectpeople()"/></td></tr>
		<tr></tr>
	</table>

	<table  border="0" cellpadding="0" cellspacing="0" width="1003">
		<tr><td colspan="2" align="center" height="30">
		<input type="button" id="new" value="新建" onClick="javascript:_new()">
		<input type="button" id="new" value="修改" onClick="javascript:_modify()">
		<input type="button" id="del" value="删除" onClick="javascript:_del()"></td></tr>
	</table>
</form>

<table width="1003" border="0" cellspacing="0" cellpadding="0" align="center">
  
  <tr>
  	<td width="10" bgcolor="#FFFFFF"></td>
    <td height="48" bgcolor="#EFEFEF"><div align="center" class="content">Tel:010-63072715 Copyright (C) 2008 版权所有　　<br />
制作单位：新华社技术局　（浏览本网主页，建议将电脑显示屏的分辨率调为1024*768）<br />
    </div></td>
  	<td width="10" bgcolor="#FFFFFF"></td>
  </tr>
</table>

</body>
</html>

<link href="<%=path%>/include/FTP/Span.css"  rel="stylesheet" type="text/css" />


<%
	} catch (DBConnectionLocatorException e) {
		e.printStackTrace();
		
	} finally {
			try {
				if (conn != null){
					conn.close();
					 ConnLog.close("ftpadmin.jsp");
					}
			} catch (Exception e) {
		}
	}
%>


<script language="javascript">
function _selectpeople(){
	var org = document.getElementById("org");
	var URL = '<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame2.jsp?doFunction=_addUserftp&sessionname=orgAccess&orguuid='+org.value;
	 window.open(URL,'','width=700,height=550,scrollbars=yes,resizable=yes');
}

function _addUserftp(msgAccess){
	frm.msgAccess.value=msgAccess;
}

function _addUserftp1(uuid){
	frm.uuid.value=uuid;
}
function _curshow(){
	var cols = document.getElementsByName("col");  
	var x ;
	var len  = cols.length;   
	for(var i = 0;i<len;i++){   
		if(cols[i].checked   ==   true){   
           x = cols[i].index;   
        }   
    }
	var obj = document.getElementById("tr"+x);
	var name = obj.childNodes(1);
	var admin = obj.childNodes(4);
	var orgname = obj.childNodes(2);
	document.frm.name.value= name.innerHTML;
	document.frm.msgAccess.value= admin.innerHTML;
	var orgobj = document.getElementsByName("org")[0];  
	for(var i=0;i<orgobj.options.length;i++){
		if(orgobj.options[i].text == orgname.innerHTML){
			orgobj.options.selectedIndex = i;
		}
	}
	
	frm.uuid.value=document.getElementById(x+"uuid").value;
	
}



function _clearpeople(){
	var obj = eval("document.frm.msgAccess");
	obj.value = "";
}


function changemessage(id){
	//document.getElementById("message").className="title_mainmessage"+id;
	for(i=1;i<3;i++){
		document.getElementById("message"+i).style.display="none";
	}
	document.getElementById("message"+id).style.display="block";
}

</script>
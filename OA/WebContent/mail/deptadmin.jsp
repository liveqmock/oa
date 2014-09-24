<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();		
	String ftpip = (String)request.getAttribute("ftpip");
	String ftpuser = (String)request.getAttribute("ftpuser");
	String ftppass = (String)request.getAttribute("ftppass");
	String ftpdepadmin = (String)request.getAttribute("ftpdepadmin");
	String basepath = (String)request.getAttribute("basepath");
	String fid = (String)request.getAttribute("fid");
	String curpath = (String)request.getAttribute("curpath");
	List undelete = (List)request.getAttribute("undelete");
%>
<script>
	var ftpip = <%=ftpip%>;
	var ftpuser = <%=ftpuser%>;
	var ftppass = <%=ftppass%>;
	var ftpdepadmin = '<%=ftpdepadmin%>';
	if("false" == ftpdepadmin){
		alert("您没有FTP管理权限！");
		history.go(-1);
	}

	var curpath = '<%=curpath%>';
	<%if(undelete!=null && undelete.size()!=0){
		String str = "";
		for(int i=0;i<undelete.size();i++){
			str =str + "," + undelete.get(i);
		}%>
		alert("文件夹\""+'<%=str%>'+"\"不为空，不能删除！");
	<%}%>
</script>
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

<body onLoad="javascript:loadftp()" onUnload="javascript:unloadftp()">
<form name="frm" target="_blank">

<%@ include file= "/include/top.jsp" %>

<table border="0" cellpadding="0" cellspacing="0" width="1003">
	<tr><td height="5"></td></tr>
</table>
<input type="hidden" id="fid" value="<%=fid%>"/>
<table border="0" cellpadding="0" cellspacing="0" width="993" align="left">
  <tr>
  	<td width="18" height="30"></td>
    <td width="86" height="30" align="center" valign="bottom" background="<%=path%>/images/ftp_title.jpg" class="table_title" style="background-repeat:no-repeat">
    文件服务器    </td>
    <td width="762" >
    	<table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td width="10">&nbsp;</td>
            	<td align="center" valign="middle"><img src="<%=path%>/images/ftp_back01.jpg" width="23" height="23" id="remote_last" onClick="javascript:laststep('remotearea')" style="cursor:hand"></td>
           	  <td align="center" valign="middle" nowrap class="foot_message">&nbsp;后退&nbsp;&nbsp;</td>
              <td align="center" valign="middle"><img src="<%=path%>/images/ftp_next01.jpg" width="23" height="23" id="remote_next" onClick="javascript:nextstep('remotearea')" style="cursor:hand"></td>
           	  <td align="center" valign="middle" nowrap class="foot_message" >&nbsp;前进&nbsp;&nbsp;</td>
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_up.jpg" width="23" height="23" id="remote_up" onClick="javascript:upfolder('remotearea')" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;向上&nbsp;&nbsp;</td>
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_home.jpg" width="23" height="23" onClick="javascript:rootpath('remotearea')" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;根目录&nbsp;&nbsp;</td>
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_newfolder.jpg" width="30" height="21" onClick="javascript:newfolder()" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;新建&nbsp;&nbsp;</td>
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_delete.jpg" width="22" height="21"    onclick="javascript:deletesel()" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;删除&nbsp;&nbsp;</td>
				<!--
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_copyto.jpg" width="23" height="21"  onclick="javascript:filetransfer('save')" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;复制到&nbsp;&nbsp;</td>
				-->
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_moveto.jpg" width="23" height="21"  onclick="javascript:filetransfer('move')" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;移动到&nbsp;&nbsp;</td>
        	</tr>
        </table>    </td>
    <td width="116"></td>
    <td width="11" height="30"></td>
  </tr>
  <tr>
  	<td width="18" height="10"></td>
    <td height="10" colspan="2">
        <div style="width:762px;height:400px;scroll:auto;border:groove;border-color:#9999FF;border-width:1;overflow:scroll;" valign="top" > 
        <table border="0" cellpadding="0" cellspacing="0" width="100%" valign="top" >
            <tr>
                <td height="20"><img src="<%=path%>/images/ftp_addr.jpg" width="47" height="20"></td>
                <td height="20"><input type="text" size="96" id="cur_remote_dir" disabled = true></td>
            </tr>
        </table>
    
        <table id="remote_list">
            <tr><td colspan="2" height="3"></td></tr>
        </table>
        </div>    
    </td>
    <td width="116" valign="middle">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr><td align="center" width="100%">&nbsp;</td></tr>
            <tr><td height="10"><applet alt="传输控件" code="FtpApplet.class" name="FtpApplet" archive="<%=path%>/ftp/FTP_update2.jar" height="1" width="1"></applet></td></tr>
            <tr><td align="center" width="100%">&nbsp;</td></tr>
      </table>    </td>
    <td width="11" height="10"></td>
  </tr>
</table>
</form>
</body>
</html>

<link href="<%=path%>/include/FTP/Span.css"  rel="stylesheet" type="text/css" />

<script language="JavaScript" src="<%=path%>/include/FTPADMIN/LocalDir.js"></script>
<script language="JavaScript" src="<%=path%>/include/FTPADMIN/ShowFolders.js"></script>
<script language="JavaScript" src="<%=path%>/include/FTPADMIN/StringUtil.js"></script>
<script language="JavaScript" src="<%=path%>/include/FTPADMIN/RemoteCall.js"></script>
<script language="javascript">
function loadftp(){
	ftpbase = "<%=basepath%>/";
	var status = document.FtpApplet.connectServer(ftpip,21,ftpuser,ftppass);
	if(status == "01" || status == "001"){
		drawdiskproxy("remotearea","连接文件服务器失败，请与管理员联系！");
	}else{	
		if(curpath == 'null'){
			drawelement(ftpbase,"remotearea");
		}else{
			drawelement(curpath,"remotearea");
		}
	}
}


function changemessage(id){
	//document.getElementById("message").className="title_mainmessage"+id;
	for(i=1;i<3;i++){
		document.getElementById("message"+i).style.display="none";
	}
	document.getElementById("message"+id).style.display="block";
}

</script>
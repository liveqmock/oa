<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();		
	String ftpip = (String)request.getAttribute("ftpip");
	String ftpuser = (String)request.getAttribute("ftpuser");
	String ftppass = (String)request.getAttribute("ftppass");
	String readstr = (String)request.getAttribute("readstr");
	String writestr = (String)request.getAttribute("writestr");
	String basepath = (String)request.getAttribute("basepath");
	String orguuid = (String)request.getAttribute("orguuid");
	String serverip = (String)request.getAttribute("serverip");
%>
<script>
	var ftpip = <%=ftpip%>;
	var ftpuser = <%=ftpuser%>;
	var ftppass = <%=ftppass%>;
	var readstr = '<%=readstr%>';
	var writestr = '<%=writestr%>';
	var orguuid = '<%=orguuid%>';
	var serverip = '<%=serverip%>';
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


</script>

<body onLoad="javascript:loadftp()" onUnload="javascript:unloadftp()">
<jsp:include page= "/include/top.jsp" />

<div id="sendbar" style="display:none;position:absolute;top:100px;left:100px;z-index:5;border:solid 1px #f00;">正在发送....</div>
<div id="receivebar" style="display:none;position:absolute;top:100px;left:100px;z-index:5;border:solid 1px #f00;">正在下载....</div>

<table border="0" cellpadding="0" cellspacing="0" width="1003">
	<tr><td height="5"></td></tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="1003" align="center">
  <tr>
  	<td width="10" height="10"></td>
    <td width="86" height="30" align="center" valign="bottom" background="<%=path%>/images/ftp_title.jpg" class="table_title" style="background-repeat:no-repeat">
    本地目录    
    </td>
    <td width="374" >
    	<table border="0" cellpadding="0" cellspacing="0">
        	<tr>
            	<td width="10">&nbsp;</td>
            	<td align="center" valign="middle"><img src="<%=path%>/images/ftp_back01.jpg" width="23" height="23" id="local_last" onClick="javascript:laststep('localarea')" style="cursor:hand"></td>
           	  <td align="center" valign="middle" nowrap class="foot_message">&nbsp;后退&nbsp;&nbsp;</td>
              <td align="center" valign="middle"><img src="<%=path%>/images/ftp_next01.jpg" width="23" height="23" id="local_next" onClick="javascript:nextstep('localarea')" style="cursor:hand"></td>
           	  <td align="center" valign="middle" nowrap class="foot_message" >&nbsp;前进&nbsp;&nbsp;</td>
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_up.jpg" width="23" height="23" id="local_up" onClick="javascript:upfolder('localarea')" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;向上&nbsp;&nbsp;</td>
                <td align="center" valign="middle"><img src="<%=path%>/images/ftp_home.jpg" width="23" height="23" onClick="javascript:rootpath('localarea')" style="cursor:hand"></td>
                <td align="center" valign="middle" nowrap class="foot_message">&nbsp;根目录&nbsp;&nbsp;</td>
        	</tr>
        </table>
    </td>
    <td width="63"></td>
    <td width="86" height="30" align="center" valign="bottom" background="<%=path%>/images/ftp_title.jpg" class="table_title" style="background-repeat:no-repeat">
    服务器目录    </td>
    <td width="374" ><table border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="10" valign="middle">&nbsp;</td>
        <td valign="middle"><img src="<%=path%>/images/ftp_back01.jpg" width="23" height="23" id="remote_last" onClick="javascript:laststep('remotearea')" style="cursor:hand"></td>
        <td valign="middle" nowrap class="foot_message">&nbsp;后退&nbsp;&nbsp;</td>
        <td align="center" valign="middle"><img src="<%=path%>/images/ftp_next.jpg" width="23" height="23" id="remote_next" onClick="javascript:nextstep('remotearea')" style="cursor:hand"></td>
        <td align="center" valign="middle" nowrap class="foot_message">&nbsp;前进&nbsp;&nbsp;</td>
        <td valign="middle"><img src="<%=path%>/images/ftp_up.jpg" width="23" height="23"  id="remote_up"      onclick="javascript:upfolder('remotearea')" style="cursor:hand"></td>
        <td valign="middle" nowrap class="foot_message">&nbsp;向上&nbsp;&nbsp;</td>
        <td valign="middle"><img src="<%=path%>/images/ftp_home.jpg" width="23" height="23" onClick="javascript:rootpath('remotearea')" style="cursor:hand"></td>
        <td valign="middle" nowrap class="foot_message">&nbsp;根目录&nbsp;&nbsp;</td>
      </tr>
    </table></td>
    <td width="10" height="10"></td>
  </tr>
  <tr>
  	<td width="10" height="10"></td>
    <td width="460" height="10" colspan="2">


    <div style="width:460px;height:400px;scroll:auto;border:groove;border-color:#9999FF;border-width:1;overflow:scroll;" valign="top" > 
    <table border="0" cellpadding="0" cellspacing="0" width="100%" valign="top" >
    	<tr>
        	<td height="20" rowspan="2"><img src="<%=path%>/images/ftp_addr.jpg" width="47" height="20"></td>
            <td height="20" rowspan="2"><input type="text" size="40" id="cur_local_dir" disabled = true class="biankuang"></td>
			<td height="20" rowspan="2"><input type="button" onclick="javascript:logindefault()" value="设置成默认"/></td>
        </tr>
    </table>

	<table id="showlist">
    	<tr><td colspan="2" height="3"></td></tr>
    </table>
	</div>


    </td>
    <td width="63" valign="middle">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr><td align="center" width="100%"><img src="<%=path%>/images/ftp_toserver.jpg" id="upload" style="cursor:hand" title="上传到服务器" onClick="javascript:connecttoftp('localarea')"></td></tr>
            <tr><td height="10"><!--"CONVERTED_APPLET"-->
<!-- HTML CONVERTER -->
<OBJECT 
    classid = "clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
    WIDTH = "1" HEIGHT = "1" NAME = "FtpApplet" ALT = "传输控件" >
    <PARAM NAME = CODE VALUE = "FtpApplet.class" >
    <PARAM NAME = ARCHIVE VALUE = "<%=path%>/ftp/FTP_update2.jar" >
    <PARAM NAME = NAME VALUE = "FtpApplet" >
    <PARAM NAME = "type" VALUE = "application/x-java-applet;version=1.4">
    <PARAM NAME = "scriptable" VALUE = "false">

    <COMMENT>
	<EMBED 
            type = "application/x-java-applet;version=1.4" \
            CODE = "FtpApplet.class" \
            ARCHIVE = "<%=path%>/ftp/FTP_update2.jar" \
            ALT = "传输控件" \
            NAME = "FtpApplet" \
            WIDTH = "1" \
            HEIGHT = "1" \
	    scriptable = false \
	    pluginspage = "http://java.sun.com/products/plugin/index.html#download">
	    <NOEMBED>
            
            </NOEMBED>
	</EMBED>
    </COMMENT>
</OBJECT>

<!--
<APPLET CODE = "FtpApplet.class" ARCHIVE = "<%=path%>/ftp/tFTP.jar" WIDTH = "1" HEIGHT = "1" NAME = "FtpApplet" ALT = "传输控件">


</APPLET>
-->


<!--"END_CONVERTED_APPLET"-->
</td></tr>
            <tr><td align="center" width="100%"><img src="<%=path%>/images/ftp_tolocal.jpg" id="download" style="cursor:hand" title="下载到本地" onClick="javascript:connecttoftp('remotearea')"></td></tr>
      </table>
    </td>
    <td colspan="2">
	<form name="sendfrm" target="_blank">
    <div style="width:460px;height:400px;scroll:auto;border:groove;border-color:#9999FF;border-width:1;overflow:scroll;">
    <table border="0" cellpadding="0" cellspacing="0" width="100%" valign="top" >
    	<tr>
        	<td height="20"><img src="<%=path%>/images/ftp_addr.jpg" width="47" height="20"></td>
            <td height="20"><input type="text" size="54" id="cur_remote_dir" disabled = true class="biankuang"></td>
        </tr>    	
		<tr>
			<td height="20" align=center><input type="button" value="检索" onclick="javascript:_search()"/></td>
            <td height="20"><input type="text" size="26" value="请输入检索关键字" class="biankuang" 
			onmousedown="javascript:_clearinfo();" name="keyword"/></td>
        </tr>
    </table>

    <script language="javascript">
		function _clearinfo(){
			if(sendfrm.keyword.value=="请输入检索关键字"){
				sendfrm.keyword.value="";
			}
		}

		function _search(){
			sendfrm.action="<%=request.getContextPath()%>/servlet/FtpTrsServlet"
			sendfrm.submit();
		}
	</script>
    
	<table id="remote_list">
    	<tr><td colspan="2" height="3"></td></tr>
    </table>
    
    
    </div>    
	</form>
	</td>


    <td width="10" height="10"></td>
  </tr>
</table>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td height="5"></td></tr>
</table>


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

<script language="JavaScript" src="<%=path%>/include/FTP/LocalDir.js"></script>
<script language="JavaScript" src="<%=path%>/include/FTP/ShowFolders.js"></script>
<script language="JavaScript" src="<%=path%>/include/FTP/StringUtil.js"></script>
<script language="JavaScript" src="<%=path%>/include/FTP/RemoteCall.js"></script>
<script language="javascript">

function intobox(path){
	curremotecurdir = path;
	drawelement(path,"remotearea");
}


function loadftp(){
	var deflogin = getdeflogin();
	ftpbase = '<%=basepath%>/';
	drawelement(deflogin,"localarea");
	var status = document.FtpApplet.connectServer(ftpip,21,ftpuser,ftppass);
	document.FtpApplet.setServer(serverip);
	if(status == "01" || status == "001"){
		drawdiskproxy("remotearea","连接文件服务器失败，请与管理员联系！");
		bupload.onclick = function(){
			alert("连接文件服务器失败，请与管理员联系！");
		}
		bdownload.onclick = function(){
			alert("连接文件服务器失败，请与管理员联系！");
		}
	}else{	
		drawelement(ftpbase,"remotearea");
	}
	//setInterval("drawelement(curremotecurdir,\"remotearea\")",30000);
}


function changemessage(id){
	//document.getElementById("message").className="title_mainmessage"+id;
	for(i=1;i<3;i++){
		document.getElementById("message"+i).style.display="none";
	}
	document.getElementById("message"+id).style.display="block";
}

</script>
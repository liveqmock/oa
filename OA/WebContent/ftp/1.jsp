<%@ page contentType="text/html; charset=gb2312" pageEncoding="gb2312"%>
<%
	String path = request.getContextPath();
%>

<html>
<head>
<title>apptest</title>
<meta name="GENERATOR" content="Rational Software Architect">
</head>
<input type="text" id="filehide" value=""/>
<input type="text" id="folderhide" value=""/>
<body>
	<table>
		<tr><td>≤‚ ‘:</td></tr>
		<tr><td>
			<applet alt="≤‚ ‘" code="FtpApplet.class" name="FtpApplet" width="200" height="100" archive="cFTP.jar"></applet>	
			<input type="button" onclick="javascript:c()" value="¡¨Ω”"/>
			<input type="button" onclick="javascript:f()" value="¥Ú”°"/>
		</td></tr>
	</table>
</body>
<script>
 function c(){
	 document.FtpApplet.connectServer("172.16.143.70",21,"sms","sms");
	 var s = document.FtpApplet.getFiles(".");
	 alert(s);
	 var s1 = document.FtpApplet.getFolders(".");
	 alert(s1);
 }


 function f(){
	alert("start");
	alert(document.FtpApplet.filestr);
	alert(document.FtpApplet.folderstr);
	alert(document.FtpApplet.state);
 }
</script>
</html>

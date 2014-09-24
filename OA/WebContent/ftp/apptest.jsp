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
<body >
	<table>
		<tr><td>≤‚ ‘:</td></tr>
		<tr><td>
			<applet alt="≤‚ ‘" code="FtpApplet.class" name="FtpApplet" width="200" height="100" archive="jFTP.jar"></applet>	
			<input type="button" onclick="javascript:f()"/>
		</td></tr>
	</table>
</body>
<script>
 function f(){
	alert("start");
	setTimeout("alert(document.FtpApplet.filestr)",1000);
	setTimeout("alert(document.FtpApplet.folderstr)",2000);
	setTimeout("alert(document.FtpApplet.state)",3000);
 }
</script>
</html>

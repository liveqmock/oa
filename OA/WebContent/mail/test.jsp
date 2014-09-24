 <%@ page contentType="text/html; charset=GBK" %>
 <%@ page pageEncoding="GBK" %>
 <%
	String path = request.getContextPath();
 %>
<html>
<head>
<title>测试上传</title>
<link href="<%=path%>/mail/css/Attach.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" id="homepagestyle" type="text/css" />
<script type="text/javascript" src="<%=path%>/mail/js/Attach.js"></script>
<script type="text/javascript">
	function sub(){
		document.attachform.filenum.value = fileNumber;
		alert(document.attachform.filenum.value);
		document.attachform.action = "<%=path%>/servlet/SendFileServlet";
		document.attachform.submit();
	}
</script>
</head>
<body>
<form method="post" name="attachform" ENCTYPE="multipart/form-data">
<INPUT TYPE="hidden" NAME="filenum">
<div>
<a id="container1" class="addfile">
<input id="File1" name="file_0" type="file" class="addfile" onchange="createnew();"  />
</a>
</div>
<div id="container2" style="position:relative; float:left; ">
</div>
<INPUT TYPE="button" VALUE="提交" ONCLICK="javascript:sub()">
</form>
</body>
</html>
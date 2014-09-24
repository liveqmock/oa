<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>

<html>
<head>
<title>top.gif</title>   
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script type="text/javascript" src="<%=request.getContextPath()%>/mail/js/xtreeleft.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mail/js/xmlextras.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/mail/js/xloadtree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/mail/css/xtree.css" />
<style type="text/css">    
body {
	margin-left: 0px;
	margin-top: 0px;  
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">

</head>
<body text=#eeeeee bgColor=#d8eaf8 align=center  width=100% leftMargin=0 topMargin=0 marginheight="0" 
marginwidth="0">
                 
<br>
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
    <td width="13"><img src="<%=request.getContextPath()%>/images/left-01.gif" width="13" height="36"></td>
    <td width="153" background="<%=request.getContextPath()%>/images/left-02.gif">&nbsp;</td>
    <td width="12"><img src="<%=request.getContextPath()%>/images/left-03.gif" width="12" height="36"></td>
  </tr>
  <tr>
    <td width="13" background="<%=request.getContextPath()%>/images/left-04.gif">&nbsp;</td>
    <td bgcolor="#FFFFFF">
<script type="text/javascript">
/// XP Look
webFXTreeConfig.rootIcon		= "<%=request.getContextPath()%>/mail/images/root.gif";
webFXTreeConfig.openRootIcon	= "<%=request.getContextPath()%>/mail/images/root.gif";
webFXTreeConfig.folderIcon		= "<%=request.getContextPath()%>/mail/images/close.gif";
webFXTreeConfig.openFolderIcon	= "<%=request.getContextPath()%>/mail/images/open.gif";
webFXTreeConfig.fileIcon		= "<%=request.getContextPath()%>/mail/images/close.gif";
webFXTreeConfig.lMinusIcon		= "<%=request.getContextPath()%>/mail/images/Lminus.png";
webFXTreeConfig.lPlusIcon		= "<%=request.getContextPath()%>/mail/images/Lplus.png";
webFXTreeConfig.tMinusIcon		= "<%=request.getContextPath()%>/mail/images/Tminus.png";
webFXTreeConfig.tPlusIcon		= "<%=request.getContextPath()%>/mail/images/Tplus.png";
webFXTreeConfig.iIcon			= "<%=request.getContextPath()%>/mail/images/I.png";
webFXTreeConfig.lIcon			= "<%=request.getContextPath()%>/mail/images/L.png";
webFXTreeConfig.tIcon			= "<%=request.getContextPath()%>/mail/images/T.png";

var tree = new WebFXTree("网络文件夹");
<%
	String hasFolder=(String)request.getAttribute("hasFolder");
	String hasShare=(String)request.getAttribute("hasShare");
	if(hasFolder.equals("no")){
%>
	tree.add(new WebFXTreeItem("个人文件夹","<%=request.getContextPath()%>/servlet/ShowRootFolderServlet","","<%=request.getContextPath()%>/mail/images/personfolder.gif","<%=request.getContextPath()%>/mail/images/personfolder.gif"));
<%
	}
	else{
%>	
	tree.add(new WebFXLoadTreeItem("个人文件夹","../servlet/LoadTreeXmlServlet.xml?folderId=1","<%=request.getContextPath()%>/servlet/ShowRootFolderServlet","","<%=request.getContextPath()%>/mail/images/personfolder.gif","<%=request.getContextPath()%>/mail/images/personfolder.gif"));
<%}if(hasShare.equals("no")){%>

	tree.add(new WebFXTreeItem("共享文件夹","","","<%=request.getContextPath()%>/mail/images/sharefolder.gif","<%=request.getContextPath()%>/mail/images/sharefolder.gif"));
<%
	}
	else{
%>
	tree.add(new WebFXLoadTreeItem("共享文件夹","../servlet/LoadShareRootXmlServlet.xml","../include/errorString.jsp?errorS=请在左面的共享树中,点击要选择的共享文件夹!","","<%=request.getContextPath()%>/mail/images/sharefolder.gif","<%=request.getContextPath()%>/mail/images/sharefolder.gif"));
<%}%>
document.write(tree);
</script>
</td>
    <td width="12" background="<%=request.getContextPath()%>/images/left-06.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left-07.gif" width="13" height="16"></td>
    <td background="<%=request.getContextPath()%>/images/left-08.gif"><img src="<%=request.getContextPath()%>/images/left-08.gif" width="3" height="16"></td>
    <td><img src="<%=request.getContextPath()%>/images/left-09.gif" width="12" height="16"></td>
  </tr>
 <br>
</body>
</html>


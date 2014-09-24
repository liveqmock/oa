<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.bbs.vo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>组织树</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script type="text/javascript" src="<%=request.getContextPath()%>/orgtree/js/xtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/orgtree/js/xmlextras.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/orgtree/js/xloadtree.js"></script>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/orgtree/css/xtree.css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
.style {color: #03598F}
</style>
</head>
<body text="#eeeeee" bgColor="#d8eaf8" align="center" width="100%" leftMargin="0" topMargin="0" marginheight="0" marginwidth="0">
<br>
<%@ include file= "/include/intendWork.jsp" %>
<br>
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="13"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-01.gif" width="13" height="36"></td>
    <td width="153" background="<%=request.getContextPath()%>/images/bbs/tree/top.gif">&nbsp;</td>
    <td width="12"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-03.gif" width="12" height="36"></td>
  </tr>
  <tr>
    <td width="13" background="<%=request.getContextPath()%>/orgtree/imagesOut/left-04.gif">&nbsp;</td>
    <td bgcolor="#FFFFFF">
    <script type="text/javascript">
/// XP Look
webFXTreeConfig.rootIcon		= "<%=request.getContextPath()%>/images/bbs/tree/root.gif";
webFXTreeConfig.openRootIcon	= "<%=request.getContextPath()%>/images/bbs/tree/root.gif";
webFXTreeConfig.folderIcon		= "<%=request.getContextPath()%>/orgtree/images/close.gif";
webFXTreeConfig.openFolderIcon	= "<%=request.getContextPath()%>/orgtree/images/open.gif";
webFXTreeConfig.fileIcon		= "<%=request.getContextPath()%>/orgtree/images/open.gif";
webFXTreeConfig.lMinusIcon		= "<%=request.getContextPath()%>/orgtree/images/Lminus.png";
webFXTreeConfig.lPlusIcon		= "<%=request.getContextPath()%>/orgtree/images/Lplus.png";
webFXTreeConfig.tMinusIcon		= "<%=request.getContextPath()%>/orgtree/images/Tminus.png";
webFXTreeConfig.tPlusIcon		= "<%=request.getContextPath()%>/orgtree/images/Tplus.png";
webFXTreeConfig.iIcon			= "<%=request.getContextPath()%>/orgtree/images/I.png";
webFXTreeConfig.lIcon			= "<%=request.getContextPath()%>/orgtree/images/L.png";
webFXTreeConfig.tIcon			= "<%=request.getContextPath()%>/orgtree/images/T.png";
webFXTreeConfig.blankIcon	    = "<%=request.getContextPath()%>/orgtree/images/blank.png";
webFXTreeConfig.defaultTarget	= "mainFrame";
<%
    String nodeUrl = request.getContextPath()+"/servlet/ShowTopicServlet";
%>
var tree = new WebFXTree("新华社论坛","<%=request.getContextPath()%>/servlet/ShowAreaServlet");
<%
	List list = (List)request.getAttribute("list");
	if(list!=null){
		Iterator it = list.iterator();
		while(it.hasNext()){
			BBSLeftTreeVO vo = (BBSLeftTreeVO)it.next();
			if(!vo.getHasChild()){
%>
					tree.add(new WebFXTreeItem("<%=vo.getVO().getAreaname()%>","<%=request.getContextPath()%>/servlet/ShowOneAreaServlet?areaId=<%=vo.getVO().getAreaid()%>&areaName=<%=vo.getVO().getAreaname()%>","","<%=request.getContextPath()%>/images/bbs/tree/area.gif","<%=request.getContextPath()%>/images/bbs/tree/area.gif"));
<%
			}
			else{
%>
					tree.add(new WebFXLoadTreeItem("<%=vo.getVO().getAreaname()%>",
							"<%=request.getContextPath()%>/servlet/ShowTreeOfAreaServlet.xml?areaId=<%=vo.getVO().getAreaid()%>&nodeUrl=<%=nodeUrl%>&personuuid=<%=(String)request.getAttribute("personuuid")%>","<%=request.getContextPath()%>/servlet/ShowOneAreaServlet?areaId=<%=vo.getVO().getAreaid()%>&areaName=<%=vo.getVO().getAreaname()%>","","<%=request.getContextPath()%>/images/bbs/tree/area.gif","<%=request.getContextPath()%>/images/bbs/tree/area.gif"));
<%
			}
		}
	}
%>
document.write(tree);
</script>
    </td>
    <td width="12" background="<%=request.getContextPath()%>/orgtree/imagesOut/left-06.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-07.gif" width="13" height="16"></td>
    <td background="<%=request.getContextPath()%>/orgtree/imagesOut/left-08.gif"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-08.gif" width="3" height="16"></td>
    <td><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-09.gif" width="12" height="16"></td>
  </tr>
</table>
<br>
<br>

</body>
</html>


<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.orgtree.vo.*"%>
<%@ page import="com.icss.orgtree.handler.*"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.j2ee.util.PageScrollUtil" %>

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
<SCRIPT language=JavaScript>
function _search(){
	if(document.searchform.cnname.value==""){
    	alert("请填写姓名！");
    	document.searchform.cnname.focus;
    	return false;
    }
	document.searchform.submit();
}
function _reset(){
	document.searchform.cnname.value="";
}
</SCRIPT>
<body text=#eeeeee bgColor=#d8eaf8 align=center  width=100% leftMargin=0 topMargin=0 marginheight="0" 
marginwidth="0">
<br>
<form name="searchform" action="<%=request.getContextPath()%>/servlet/FindPersonInOrgServlet" target="mainFrame">
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="13"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-01.gif" width="13" height="36"></td>
    <td width="153" background="<%=request.getContextPath()%>/orgtree/imagesOut/left_img/left-title-11.gif">&nbsp;</td>
    <td width="12"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-03.gif" width="12" height="36"></td>
  </tr>
  <tr>
    <td width="13" background="<%=request.getContextPath()%>/orgtree/imagesOut/left-04.gif">&nbsp;</td>
    <td bgcolor="#FFFFFF">
    <script type="text/javascript">
/// XP Look
webFXTreeConfig.rootIcon		= "<%=request.getContextPath()%>/orgtree/images/root.gif";
webFXTreeConfig.openRootIcon	= "<%=request.getContextPath()%>/orgtree/images/root.gif";
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
	SysOrgVO topOrgVO=(SysOrgVO)request.getAttribute("topOrgVO");
	//String nodeUrl=request.getParameter("nodeUrl");
    String sessionname = request.getParameter("sessionname");
    String doFunction = request.getParameter("doFunction");
    String nodeUrl = request.getContextPath() + "/servlet/SelectPersonServlet?doFunction="+doFunction+"?sessionname="+sessionname;
	String mStr=null;
	if(nodeUrl.indexOf("?",0)==-1){
		mStr="?";
	}
	else{
		mStr="&amp;";
	}
%>
var tree = new WebFXTree("<%=topOrgVO.getCnname()%>","<%=OrgHandler.realUrl(nodeUrl,topOrgVO.getOrguuid(),topOrgVO.getCnname())%>");
<%
	List list = (List)request.getAttribute("list");
	String flag=request.getParameter("flag");
	if(list!=null){
		Iterator it = list.iterator();
		while(it.hasNext()){
			SysOrgTreeVO vo = (SysOrgTreeVO)it.next();
			if(!vo.getHasChild()){
				if(true){
%>
					tree.add(new WebFXTreeItem("<%=vo.getVO().getCnname()%>","<%=OrgHandler.realUrl(nodeUrl,vo.getVO().getOrguuid(),vo.getVO().getCnname())%>","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
<%
				}
				else{
%>
					tree.add(new WebFXTreeItem("<%=vo.getVO().getCnname()%>","","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
<%
				}
			}
			else{
				if(true){
%>		
					tree.add(new WebFXLoadTreeItem("<%=vo.getVO().getCnname()%>",
							"../servlet/OrgTreeXmlServlet.xml?orgId=<%=vo.getVO().getOrguuid()%>&nodeUrl=<%=nodeUrl%>","<%=OrgHandler.realUrl(nodeUrl,vo.getVO().getOrguuid(),vo.getVO().getCnname())%>","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
<%
				}
				else{
%>
					tree.add(new WebFXLoadTreeItem("<%=vo.getVO().getCnname()%>",
							"../servlet/OrgTreeXmlServlet.xml?orgId=<%=vo.getVO().getOrguuid()%>&nodeUrl=<%=nodeUrl%>","","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
<%
				}
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
  
  <!--tr>
  	<td></td>
    <td align="center"><a href="<%=request.getContextPath()%>/servlet/SelectCommonGroupServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>" target="mainFrame">
<img src="<%=request.getContextPath()%>/images/selectfromcommon.gif" border=0 style="cursor:hand" ></a></td>
  	<td></td>
  </tr>
  <tr>
  	<td></td>
  	<td>&nbsp;</td>
  	<td></td>
  </tr>
  <tr>
  	<td></td>
    <td align="center"><a href="<%=request.getContextPath()%>/servlet/SelectIndiGroupServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>" target="mainFrame">
    <img src="<%=request.getContextPath()%>/images/selectfromindi.gif" border=0 style="cursor:hand" ></a></td>
  	<td></td>
  </tr-->
</table>

<br>
<br>

<tr>
  	<td></td>
    <td height="20" colspan="2" class="left"><div align="center"  class="style"><strong>人员搜索</strong></div></td>
  	<td></td>
</tr>
<br>
<tr>
    <td></td>
    <td height="22" class="left"><div align="center"  class="style">姓名：<input name="cnname" type="text" size="14"></div></td>
  	<td></td>
</tr>
<tr>
    <td colspan="2"><div align="center"><img src="<%=request.getContextPath()%>/images/leftbutton.gif" width="51" height="19" vspace="10" style="cursor:hand;"  onclick="javascript:_search()">
          								<img src="<%=request.getContextPath()%>/images/leftbutton_rewrite.gif" width="51" height="19" vspace="10" style="cursor:hand;"  onclick="javascript:_reset()"></div></td>
</tr>







<input type=hidden name="doFunction" value="<%=doFunction%>">
<input type=hidden name="sessionname" value="<%=sessionname%>">
<input type=hidden name="orguuid" value="<%=topOrgVO.getOrguuid()%>">
</form>
 <br>
</body>
</html>


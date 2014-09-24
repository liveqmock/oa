<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.popuporgtree.vo.*"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.j2ee.util.PageScrollUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>组织树</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script type="text/javascript" src="<%=request.getContextPath()%>/popuporgtree/js/xtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/popuporgtree/js/xmlextras.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/popuporgtree/js/xloadtree.js"></script>
<script language="javascript">
function getOrg(orgid,orgname){
	document.Form_CheckOrg.orgid.value=orgid;
	document.Form_CheckOrg.orgname.value=orgname;
}
function _checkOk(){
  	window.close();
	var func="window.opener.top.mainFrame.<%=request.getParameter("nodeFunc")%>(document.Form_CheckOrg.orgid.value,document.Form_CheckOrg.orgname.value)";
	eval(func);
}
function _checkCancel(){
	window.close();
}
</script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/popuporgtree/css/xtree.css" />
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
<div class="title-04">&nbsp;请选择组织：</div>
<br>
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="13"><img src="<%=request.getContextPath()%>/popuporgtree/imagesOut/left-01.gif" width="13" height="36"></td>
    <td width="153" background="<%=request.getContextPath()%>/popuporgtree/imagesOut/left_img/left-title-11.gif">&nbsp;</td>
    <td width="12"><img src="<%=request.getContextPath()%>/popuporgtree/imagesOut/left-03.gif" width="12" height="36"></td>
  </tr>
  <tr>
    <td width="13" background="<%=request.getContextPath()%>/popuporgtree/imagesOut/left-04.gif">&nbsp;</td>
    <td bgcolor="#FFFFFF">
    <script type="text/javascript">
/// XP Look
webFXTreeConfig.rootIcon		= "<%=request.getContextPath()%>/popuporgtree/images/root.gif";
webFXTreeConfig.openRootIcon	= "<%=request.getContextPath()%>/popuporgtree/images/root.gif";
webFXTreeConfig.folderIcon		= "<%=request.getContextPath()%>/popuporgtree/images/close.gif";
webFXTreeConfig.openFolderIcon	= "<%=request.getContextPath()%>/popuporgtree/images/open.gif";
webFXTreeConfig.fileIcon		= "<%=request.getContextPath()%>/popuporgtree/images/open.gif";
webFXTreeConfig.lMinusIcon		= "<%=request.getContextPath()%>/popuporgtree/images/Lminus.png";
webFXTreeConfig.lPlusIcon		= "<%=request.getContextPath()%>/popuporgtree/images/Lplus.png";
webFXTreeConfig.tMinusIcon		= "<%=request.getContextPath()%>/popuporgtree/images/Tminus.png";
webFXTreeConfig.tPlusIcon		= "<%=request.getContextPath()%>/popuporgtree/images/Tplus.png";
webFXTreeConfig.iIcon			= "<%=request.getContextPath()%>/popuporgtree/images/I.png";
webFXTreeConfig.lIcon			= "<%=request.getContextPath()%>/popuporgtree/images/L.png";
webFXTreeConfig.tIcon			= "<%=request.getContextPath()%>/popuporgtree/images/T.png";
webFXTreeConfig.blankIcon	    = "<%=request.getContextPath()%>/popuporgtree/images/blank.png";
<%
	SysOrgVO topOrgVO=(SysOrgVO)request.getAttribute("topOrgVO");
	String nodeUrl=request.getParameter("nodeUrl");
%>
var tree = new WebFXTree("<%=topOrgVO.getCnname()%>","javascript:getOrg('<%=topOrgVO.getOrguuid()%>','<%=topOrgVO.getCnname()%>')");
<%
	List list = (List)request.getAttribute("list");
	String flag=request.getParameter("flag");
	if(list!=null){
		Iterator it = list.iterator();
		while(it.hasNext()){
			SysOrgTreeVO vo = (SysOrgTreeVO)it.next();
			if(!vo.getHasChild()){
				if("1".equals(flag)){
%>
					tree.add(new WebFXTreeItem("<%=vo.getVO().getCnname()%>","javascript:getOrg('<%=vo.getVO().getOrguuid()%>','<%=vo.getVO().getCnname()%>')","","<%=request.getContextPath()%>/popuporgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
<%
				}
				else{
%>
					tree.add(new WebFXTreeItem("<%=vo.getVO().getCnname()%>","javascript:getOrg('','')","","<%=request.getContextPath()%>/popuporgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
<%
				}
			}
			else{
				if("1".equals(flag)){
%>		
					tree.add(new WebFXLoadTreeItem("<%=vo.getVO().getCnname()%>",
							"../servlet/PopupOrgTreeXmlServlet.xml?orgId=<%=vo.getVO().getOrguuid()%>","javascript:getOrg('<%=vo.getVO().getOrguuid()%>','<%=vo.getVO().getCnname()%>')","","<%=request.getContextPath()%>/popuporgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
<%
				}
				else{
%>
					tree.add(new WebFXLoadTreeItem("<%=vo.getVO().getCnname()%>",
							"../servlet/PopupOrgTreeXmlServlet.xml?orgId=<%=vo.getVO().getOrguuid()%>","javascript:getOrg('','')","","<%=request.getContextPath()%>/popuporgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));
<%
				}
			}
		}
	}
%>
document.write(tree);
</script>
    
</td>
    <td width="12" background="<%=request.getContextPath()%>/popuporgtree/imagesOut/left-06.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/popuporgtree/imagesOut/left-07.gif" width="13" height="16"></td>
    <td background="<%=request.getContextPath()%>/popuporgtree/imagesOut/left-08.gif"><img src="<%=request.getContextPath()%>/popuporgtree/imagesOut/left-08.gif" width="3" height="16"></td>
    <td><img src="<%=request.getContextPath()%>/popuporgtree/imagesOut/left-09.gif" width="12" height="16"></td>
  </tr>
</table>
	<table align="center" width="100%">
	 
  <form name="Form_CheckOrg">
  <input type="hidden" name="orgid">
	<tr><td align="left" class="title-04">&nbsp;所选组织：</td></tr>
	<tr><td align="center" class="title-04"><input type="text" readonly name="orgname" size="25"></td></tr>
	</table>
	</form>	
    <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
       <tr>
         <td align="center" class="text-01"><img src="<%=request.getContextPath()%>/popuporgtree/imagesOut/button_ok.gif" hspace="10" style="cursor:hand" onClick="_checkOk()"><img src="<%=request.getContextPath()%>/popuporgtree/imagesOut/button_cancel.gif" hspace="10" style="cursor:hand" onClick="_checkCancel()"></td>
       </tr>
    </table>
</body>
</html>


<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.orgtree.vo.*"%>
<%@ page import="com.icss.orgtree.handler.*"%>
<%@ page import="com.icss.oa.address.vo.AddressGroupVO"%>
<%@ page import="com.icss.oa.hr.vo.HRGroupVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
Collection indiCollection = (Collection)request.getAttribute("indiGroup");
Collection commomCollection = (Collection)request.getAttribute("toplist");
Iterator commonGroupIterator = commomCollection.iterator();
Iterator indiGroupIterator = indiCollection.iterator();
%>
<html>
<head>
<title>组织树</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<script type="text/javascript" src="<%=request.getContextPath()%>/orgtree/js/xtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/orgtree/js/xmlextras.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/orgtree/js/xloadtree.js"></script>
<link href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/orgtree/css/xtree.css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/include/treestyle.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=request.getContextPath()%>/include/treeview_pro.js" type="text/JavaScript"></script>

<style type="text/css">
.style {color: #03598F}
</style>

<script language="javascript"> 
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
</script>

</head>
<body text=#eeeeee bgColor=#d8eaf8 align=center  width=100% leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<form name="searchform" action="<%=request.getContextPath()%>/servlet/SendFileFindPersonServlet" target="mainFrame">
<br/>

<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">

<tr>
	<td></td>  
	<td>
		<table width="156"  border="0" align="left" cellpadding="0" cellspacing="0">
          <tr>
		    <td height="20" colspan="2" class="left"><div align="center"  class="style"><strong>人名模糊搜索</strong></div></td>
		  </tr>
		  
		  <tr>
		    <td height="22" class="center"><div align="left"  class="style">姓名：<input name="cnname" type="text" size="14" onmouseover="this.select()"></div></td>
		  </tr>
		
		   <tr>
		    <td colspan="2"><div align="center">
		    <img src="<%=request.getContextPath()%>/images/leftbutton.gif" width="51" height="19" vspace="10" style="cursor:hand;"  onclick="javascript:_search()" />
		    <img src="<%=request.getContextPath()%>/images/leftbutton_rewrite.gif" width="51" height="19" vspace="10" style="cursor:hand;"  onclick="javascript:_reset()" />
		          								</div></td>
		   </tr>
    </table>
	</td>
	<td></td>
</tr>

  <tr style="display:none" id="selectOften">
  	<td></td>
    <td align="left"><a href="<%=request.getContextPath()%>/servlet/SendFileSelectOftenPersonServlet" target="mainFrame"><img src="<%=request.getContextPath()%>/images/selectfromdep3.gif" border=0 style="cursor:hand" ></a></td>
  	<td></td>
  </tr>  
  <tr>
  	<td></td>
    <td align="left">&nbsp;</td>
  	<td></td>
  </tr>
  <tr>
  	<td></td>
    <td align="left"><a href="<%=request.getContextPath()%>/servlet/SendFileOrgServlet" target="mainFrame">
	<img src="<%=request.getContextPath()%>/images/selectfromdep2.gif" border=0 style="cursor:hand" /></a></td>
  	<td></td>
  </tr>
  <tr>
  	<td></td>
  	<td>&nbsp;</td>
  	<td></td>
  </tr>
  <tr>
    <td width="13"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-01.gif" width="13" height="36" /></td>
    <td width="153" background="<%=request.getContextPath()%>/images/selectfromcommon3.gif">&nbsp;</td>
    <td width="12"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-03.gif" width="12" height="36" /></td>
  </tr>
  <tr>
  	<td width="13" background="<%=request.getContextPath()%>/orgtree/imagesOut/left-04.gif">&nbsp;</td>
    <td bgcolor="#FFFFFF">
    <table width="156"  border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td height="6" colspan="2"></td>
        </tr>
      <tr><td colspan="2" class="left" >
		 <script>
			  //document.write(commtree);
			  //commtree.collapseAll('10');
		 </script>
		 
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
				
				var tree2= new WebFXTree("公共地址簿列表","<%=request.getContextPath()%>/servlet/SendFileSelectCommonTwoLevelGroupServlet");
				<%  
					while(commonGroupIterator.hasNext()){
						HRGroupVO vo = (HRGroupVO)commonGroupIterator.next();
				%>
					tree2.add(new WebFXLoadTreeItem("<%=vo.getOrgname()%>",
							"../SendFileTwoGuoupXmlServlet.xml?ParentID=<%= vo.getGroupid()%>","<%=request.getContextPath()%>/servlet/SendFileSelectCommonTwoLevelGroupServlet?groupid=<%=vo.getGroupid()%>","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));

				<%
				   }
				%>

				document.write(tree2);
				
		</script>
		 
         </td>
        </tr>
		
    </table>
	</td>
  	<td width="12" background="<%=request.getContextPath()%>/orgtree/imagesOut/left-06.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-07.gif" width="13" height="16" /></td>
    <td background="<%=request.getContextPath()%>/orgtree/imagesOut/left-08.gif"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-08.gif" width="3" height="16" /></td>
    <td><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-09.gif" width="12" height="16" /></td>
  </tr>
  <tr>
  	<td></td>
  	<td>&nbsp;</td>
  	<td></td>
  </tr>
  <tr>
    <td width="13"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-01.gif" width="13" height="36" /></td>
    <td width="153" background="<%=request.getContextPath()%>/images/selectfromindi3.gif">&nbsp;</td>
    <td width="12"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-03.gif" width="12" height="36"/></td>
  </tr>
  <tr>
  	<td width="13" background="<%=request.getContextPath()%>/orgtree/imagesOut/left-04.gif">&nbsp;</td>
    <td bgcolor="#FFFFFF">
    <table width="156"  border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td height="6" colspan="2"></td>
        </tr>
      <tr>
        <td colspan="2" class="left" >
		<script>
			 // document.write(inditree);
			 // inditree.collapseAll('0');
		 </script>
		 
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
				
				var tree1 = new WebFXTree("个人地址簿列表","<%=request.getContextPath()%>/servlet/SendFileSelectIndiGroupServlet");
				<%  
					while(indiGroupIterator.hasNext()){
						AddressGroupVO addressGroupVO = (AddressGroupVO)indiGroupIterator.next();
				%>
				tree1.add(new WebFXTreeItem("<%=addressGroupVO.getGroupname()%>","<%=request.getContextPath()%>/servlet/SendFileSelectIndiPersonServlet?groupid=<%=addressGroupVO.getId()%>","","<%=request.getContextPath()%>/orgtree/images/closep.gif","<%=request.getContextPath()%>/orgtree/images/openp.gif"));

				<%
				   }
				%>

				document.write(tree1);
				
		</script>
         </td>
        </tr>
    </table>
	</td>
  	<td width="12" background="<%=request.getContextPath()%>/orgtree/imagesOut/left-06.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-07.gif" width="13" height="16" /></td>
    <td background="<%=request.getContextPath()%>/orgtree/imagesOut/left-08.gif"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-08.gif" width="3" height="16" /></td>
    <td><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-09.gif" width="12" height="16" /></td>
  </tr>
  <tr>
  	<td></td>
  	<td>&nbsp;</td>
  	<td></td>
  </tr>
  <tr>
    <td width="13"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-01.gif" width="13" height="36" /></td>
    <td width="153" background="<%=request.getContextPath()%>/orgtree/imagesOut/left_img/left-title-11.gif">&nbsp;</td>
    <td width="12"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-03.gif" width="12" height="36" /></td>
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
	String nodeUrl = request.getContextPath() + "/servlet/SendFileSelectPersonServlet?";
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
				//if("1".equals(flag)){
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
				//if("1".equals(flag)){
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
    <td><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-07.gif" width="13" height="16" /></td>
    <td background="<%=request.getContextPath()%>/orgtree/imagesOut/left-08.gif"><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-08.gif" width="3" height="16" /></td>
    <td><img src="<%=request.getContextPath()%>/orgtree/imagesOut/left-09.gif" width="12" height="16" /></td>
  </tr>
</table>
</form>
 <br/>
</body>
</html>


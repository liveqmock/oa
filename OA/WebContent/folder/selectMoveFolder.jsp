<%@ page contentType="text/html; charset=gb2312" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
 
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.folder.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%

List folderList = (List)request.getAttribute("folderList");
String folderId = (String)request.getAttribute("folderId");

%>


<script language="JavaScript" src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></script>
<link href="<%=request.getContextPath()%>/include/treestyle.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">



<HTML><HEAD><TITLE>网络文件夹</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">

<META content="MSHTML 6.00.2800.1170" name=GENERATOR></HEAD>
<script language="JavaScript">
function getId(tfId,fId,fName){
	document.Form_MovetoSort.targetFolderId.value=tfId;
	document.Form_MovetoSort.folderId.value=fId;
	document.Form_MovetoSort.folderName.value=fName;
}
function _moveOk(){
  	if(document.Form_MovetoSort.targetFolderId.value==""||
  	   document.Form_MovetoSort.folderId.value==""){
  	   	alert("请选择移动位置！");
  	   	return;
  	 }
  	 	document.Form_MovetoSort.action="<%=request.getContextPath()%>/servlet/MoveFolderServlet";
  	 	document.Form_MovetoSort.submit();
}
function _moveCancel(){
	window.close();
}
</script>
<BODY text=#000000 leftMargin=0 bgColor=#d8eaf8>
<br>
<div class="title-04">&nbsp;请选择移动位置：</div><br>
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="13"><img src="<%=request.getContextPath()%>/images/left-01.gif" width="13" height="36"></td>
    <td width="153" background="<%=request.getContextPath()%>/images/left-02.gif">&nbsp;</td>
    <td width="12"><img src="<%=request.getContextPath()%>/images/left-03.gif" width="12" height="36"></td>
  </tr>
  <tr>
    <td width="13" background="<%=request.getContextPath()%>/images/left-04.gif">&nbsp;</td>
    <td bgcolor="#FFFFFF">
<SCRIPT language="JavaScript" >
var a=new TreeView('a','_parent')
a.add(-1,0,'网络文件夹','#')					  
a.add(1,-1,'个人文件夹','javascript:getId("1","<%=folderId%>","个人文件夹")','','')
</script>
<%
Iterator Itr = folderList.iterator();
  ManagementPackageVO vo = null;
  while(Itr.hasNext()){
	  vo = (ManagementPackageVO)Itr.next();
%>
 <script>a.add(<%=vo.getFpId()%>,<%=vo.getFolFpId()%>,'<%=vo.getFpName()%>','javascript:getId("<%=vo.getFpId()%>","<%=folderId%>","<%=vo.getFpName()%>")','','');</script>   

	<%}%>
 <script>
	 document.write(a);
 </script>

</td>
    <td width="12" background="<%=request.getContextPath()%>/images/left-06.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left-07.gif" width="13" height="16"></td>
    <td background="<%=request.getContextPath()%>/images/left-08.gif"><img src="<%=request.getContextPath()%>/images/left-08.gif" width="3" height="16"></td>
    <td><img src="<%=request.getContextPath()%>/images/left-09.gif" width="12" height="16"></td>
  </tr>
</table>
<table align="center" width="100%">
  <form name="Form_MovetoSort">
  <input type="hidden" name="targetFolderId">
  <input type="hidden" name="folderId">
	<tr><td align="left" class="title-04">&nbsp;所选位置：</td></tr>
	<tr><td align="center" class="title-04"><input type="text" readonly name="folderName" size="25"></td></tr>
	</table>
	</form>	
    <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
       <tr>
         <td align="center" class="text-01"><img src="<%=request.getContextPath()%>/images/botton-ok.gif" hspace="10" style="cursor:hand" onClick="_moveOk()"><img src="<%=request.getContextPath()%>/images/botton-cancel.gif" hspace="10" style="cursor:hand" onClick="_moveCancel()"></td>
       </tr>
    </table>
</BODY></HTML>
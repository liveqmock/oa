<%@ page contentType="text/html; charset=GBK" %>
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
//String strParentId = (String)request.getParameter("parentId");
//out.print(strParentId);
List shareFolderList = (List)(List)request.getAttribute("shareFolderList");

%>
<html>
<head>
<title>top.gif</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script language="JavaScript" src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></script>
<link href="<%=request.getContextPath()%>/include/treestyle.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
</head>
<body text=#eeeeee bgColor=#d8eaf8 align=center  width=100% leftMargin=0 topMargin=0 marginheight="0" 
marginwidth="0">
<br>
<table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/01.gif" width="178" height="1"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/05.gif" width="178" height="8"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/title-02.gif" width="178" height="28"></td>
  </tr>
  <tr>
    <td background="<%=request.getContextPath()%>/images/07.gif">   
		<table width="156"  border="0" align="center" cellpadding="0" cellspacing="0">
      		<tr><td>
<script type="text/javascript">

var a=new TreeView('a','_parent')
a.add(-1,0,'网络文件夹','#')					  
a.add(1,-1,'我的文件夹','<%=request.getContextPath()%>/servlet/ShowRootFolderServlet','','mainFrame')	
a.add(-2,-1,'共享目录','#')
</script>
<%
Iterator Itr = folderList.iterator();
  ManagementPackageVO vo = new ManagementPackageVO();
  while(Itr.hasNext()){
	  vo = (ManagementPackageVO)Itr.next();
%>
 <script>a.add(<%=vo.getFpId()%>,<%=vo.getFolFpId()%>,'<%=vo.getFpName()%>','<%=request.getContextPath()%>/servlet/ShowFileListServlet?parentId=<%=vo.getFpId()%>','','mainFrame');</script> 

	<%}%>
<%
if(shareFolderList != null){
Iterator shareIt = shareFolderList.iterator();
  List shareList = null;
  while(shareIt.hasNext()){
	  ShareListVO shareListVO= (ShareListVO)shareIt.next();
	  String accessFlag = shareListVO.getShareAccess();
	 //   out.print(shareList);
	 shareList = shareListVO.getList();
		if(shareList != null){
		Iterator shareItr = shareList.iterator();
		 FolderPackageVO shareVO = null;
		while(shareItr.hasNext()){
			shareVO = (FolderPackageVO)shareItr.next();
		//	out.print("shareVO"+shareVO);
%>
 <script>a.add(<%=shareVO.getFpId()%>,<%=shareVO.getFolFpId()%>,'<%=shareVO.getFpName()%>','<%=request.getContextPath()%>/servlet/ShowFileListServlet?parentId=<%=shareVO.getFpId()%>&shareFlag=1&accessFlag=<%=accessFlag%>','','mainFrame');</script> 

	<%}
	}
  }
}%>
 <script>
			 	
			  document.write(a);
</script>
</td></tr></table>
	</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/08.gif" width="178" height="7"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/05.gif" width="178" height="8"></td>
  </tr>
  <tr>
    <td bgcolor="#D8EAF8">&nbsp;</td>
  </tr>
</table>
 
</body>
</html>


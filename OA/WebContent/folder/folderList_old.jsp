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
//String strParentId = (String)request.getParameter("parentId");
//out.print(strParentId);
List shareFolderList = (List)(List)request.getAttribute("shareFolderList");

%>
<script language="JavaScript" src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></script>
<link href="<%=request.getContextPath()%>/include/treestyle.css" rel="stylesheet" type="text/css">



<HTML><HEAD><TITLE>网络文件夹</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">

<META content="MSHTML 6.00.2800.1170" name=GENERATOR></HEAD>
<BODY text=#000000 leftMargin=0 background="<%=request.getContextPath()%>/images/folder/bg-08.gif"
topMargin=10>

<SCRIPT language="JavaScript" >
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
</BODY></HTML>
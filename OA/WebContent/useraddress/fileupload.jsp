<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312" %>
<%

response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@	page import="org.apache.commons.fileupload.*"%>
<%

String path_separa=System.getProperty("file.separator") ;    //系统文件分隔符
String msg ="";
 String path = pageContext.getServletContext().getRealPath("/useraddress");
 System.out.println("+++++++++in fileupload.jsp"+path.toString());
 DiskFileUpload fu = new DiskFileUpload();
 // 设置允许用户上传文件大小,单位:字节
 fu.setSizeMax(10000000);
 // maximum size that will be stored in memory?
 // 设置最多只允许在内存中存储的数据,单位:字节
 fu.setSizeThreshold(4096);
 // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
 fu.setRepositoryPath(path+path_separa+"uploads");
 //开始读取上传信息
 List fileItems = fu.parseRequest(request);
// if(fileItems==null) out.println("");
%>


<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>文件上载</title>
<SCRIPT language=JavaScript src="../include/common.js"></SCRIPT>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script type="text/javascript" src="../include/judge.js"></script>
<style type="text/css">
<!--
. bjc_button1 {
	BACKGROUND: #007ED2;
	font-size: 12px;
	font-style: normal;
	line-height: normal;
	HEIGHT: 17;
	cursor: hand;
	color: #FFFFFF;
	border: 1px solid #666666;
}

.dotted2 {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: dotted;
	border-left-style: none;
	border-top-color: #666666;
	border-right-color: #666666;
	border-bottom-color: #666666;
	border-left-color: #666666;
}

.style2 {
	color: #0055A2
}
-->
</style>
<script language="JavaScript">
 function loadmainpage1(url){

	//newURL=url;
	window.top.mainFrame.location=url;
	//"<%=request.getContextPath()%>/webapp/artical/addArtical.jsp";
	//alert("hhhh");
 }
  function LoadNextPage(url){
		document.form.action=url;
		document.form.submit();				
	}
	

 </script>



</head>

<body bgcolor="#ffffff">
 <FORM name=form action=""  method=post>
 <input type=hidden name="planid" >
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
        <TR>
          <TD width="2%" background='<%=request.getContextPath()%>/images/bg-12.gif'><IMG height=22 src="<%=request.getContextPath()%>/webapp/images/bg-10.gif" width=14></TD>
          <TD class=title-05 background='<%=request.getContextPath()%>/images/bg-12.gif'>[文件导入] </TD>
          <TD width="1%" align="right"><IMG height=22 src="<%=request.getContextPath()%>/images/bg-11.gif" width=20></TD>
        </TR>
      </TABLE>
	</td>
  </tr>
   <tr>
    <td class="dot-border">
      <table width="100%"  border="0" cellpadding="0" cellspacing="0">
        <%
					 // 依次处理每个上传的文件
 						Iterator iter = fileItems.iterator();
						String file_name="";
						while (iter.hasNext()) {
						  FileItem item = (FileItem) iter.next();
						  //忽略其他不是文件域的所有表单信息
						  if (!item.isFormField()) {
							   String name = item.getName();
							   long size = item.getSize();
							   if((name==null||name.equals("")) && size==0)
							   continue;
				%>
		<tr bgcolor="#fffbef"class="title-04" >
		
          <td width="17%" height="35" align="center" class="dot">文件名</td>
          <td width="33%" align="left" class="dot"><%=item.getName()%></td>
		  <td width="17%" class="dot">文件大小 </td>
		  <td width="33%" class="dot"> <%=item.getSize()%></td>
        </tr>
		
							<%
							   //保存上传的文件到指定的目录   
							file_name=name.substring(name.lastIndexOf("\\")+1);
							int fileL=name.indexOf(".");
							   if(fileL==-1){
							      file_name=name.substring(name.lastIndexOf("\\")+1);
							   }
							item.write(new File(path+path_separa+"uploads"+path_separa+ file_name));
							  }
							 }
							 file_name=file_name.substring(0,file_name.lastIndexOf("."));
							// out.println(file_name);
							file_name=file_name;
							%>
       </table>
	</td>
  </tr>
  <tr>
    <td>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
        <TR>
          <TD width="1%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><IMG height=21 src="<%=request.getContextPath()%>/images/bg-21.jpg" width=10></TD>
          <TD class=text-01 width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg">
          </TD>
          <TD width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg" align="right"><IMG height=21 src="<%=request.getContextPath()%>/images/bg-22.jpg" width=11></TD>
        </TR>
      </TABLE>
	</td>
  </tr>
  <br>
    <br>
  <tr>
    <td align="center">
     	 <div align="center"><a href="<%=request.getContextPath()%>/servlet/UseraddressImportServlet?file_name=<%=path+path_separa+ "uploads"+path_separa+ file_name%>"><img src="<%=request.getContextPath()%>/images/botton-ok.gif" width="88" height="21" border="0"></a></div>&nbsp;
            
	</td>
  </tr>
  </table>
	  
</form>
</body>
</html>

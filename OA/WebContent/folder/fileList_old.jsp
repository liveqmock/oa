<%@ page contentType="text/html; charset=GBK" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.filemanager.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.filemanager.control.FileType" %>88
<%

List fileList = (List)request.getAttribute("fileList");
String parentId = (String)request.getAttribute("parentId");
%>

<HTML><HEAD><TITLE>案例列表</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/style.css">

<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/include/calendar.js"></SCRIPT>

<SCRIPT language=JavaScript>
writeDIV();
</SCRIPT>

<META content="MSHTML 6.00.2800.1170" name=GENERATOR></HEAD>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/grid.gif 
topMargin=10>
<form name="form" method="post" action=""> 
<TABLE width="720" border=0 
      align=center cellPadding=2 cellSpacing=1 borderColorLight=#003366 borderColorDark=#dfdfff >
            <TBODY>
              <TR> 
                
       <td width="684" height="28" colspan="5" bgcolor="#a6d0f2">
<div align="center"> 网络文件夹</div></td>
              </TR>
              <TR> 
                <TD> <TABLE cellSpacing=1 borderColorDark=#dfdfff cellPadding=2 
            width="100%" align=center bgColor=#b3c4db borderColorLight=#003366 
            border=0>
          <TBODY>
            <TR bgColor=#eef4ff> 
              <TD width="4%" height=22><div align="center"></div></TD>
              <TD width="9%" align=middle><div align="center">文件名称</div></TD>
              <TD width="26%" align=middle bgcolor="#eef4ff"><div align="center">文件描述</div></TD>
              <TD width="27%" align=middle bgcolor="#eef4ff"><div align="center">文件类型</div></TD>
              <TD width="18%" align=middle><div align="center">创建日期</div></TD>
            </TR>
         
          <%
 FileType fileType = FileType.getInstance();	
 FileTypeVO typeVO = new FileTypeVO();
 Iterator Itr = fileList.iterator();
 FilemanagerVO vo = new FilemanagerVO();
 while(Itr.hasNext()){
	  vo = (FilemanagerVO)Itr.next();
	  if(vo.getMimeType() != null){
	  typeVO = fileType.getXMLData(vo.getMimeType());
	  }

%>
          <TR onmouseover="this.bgColor='#EBEBEB';" 
              onmouseout="this.bgColor='#EEF4FF';" bgColor=#eef4ff> 
            <TD align=center> <INPUT type="checkbox" name="folderId" value="<%=vo.getFolderId()%>"> 
            </TD>
            <TD> 
              <%if(vo.getFolderType().equals("1")){%>
              <img src="<%=request.getContextPath()%>/images/fileManager/close.gif" width="15" height="15" align="middle"   style="border=0">
              <%}else if((vo.getFolderType().equals("0"))&&(vo.getMimeType()!=null)){%>
				 <img src="<%=request.getContextPath()%>/images/fileType/<%=typeVO.getFilePic()%>" width="15" height="15" align="middle" style="border=0">
				 <%}else {%>
				  <img src="<%=request.getContextPath()%>/images/fileType/file.gif" width="15" height="15" align="middle"   style="border=0">
					  <%}%>
               <%if(vo.getFolderType().equals("0")){%>
				  <A  href="<%=request.getContextPath()%>/servlet/ShowFileServlet?folderId=<%=vo.getFolderId()%>"><%=vo.getFolderName()%></a>
					<%} else{%>
					<%=vo.getFolderName()%>
					<%}%>
              </TD>
            <TD> 
              <%if(vo.getFolderDesc() != null){%>
              <div align="center"><%=vo.getFolderDesc()%> 
                <%}%>
              </div></TD>
			    <TD><div align="center"><%if((vo.getFolderType().equals("0"))&&(vo.getMimeType() != null)){%>
					<%=typeVO.getFileType()%>
					<%}else if((vo.getFolderType().equals("0"))&&(vo.getMimeType() == null)){%>文件<%}%></div></TD>
				<td align=center> 
				<%//=CommUtil.getTime(vo.getCreateTime())%>
				</td>
          </TR>
          <%
			}	//while
			
%></TBODY>
        </TABLE>
			   <TR> 
      <TD> 
            <div align="center"> </div></TD>
    </TR>
		     </TR> 
            </TBODY>
			  </TABLE>
			 
  <table width=720 border="0" cellspacing="1" cellpadding="4" bgcolor="#426199" align=center>
    <tr bgcolor="EEF4FF"> 
      <td width="187" height="25" align="center"> <div align="center"><a href="#" onclick="javascript:_addFolder('<%=request.getContextPath()%>/jsp/filemanager/addFolder.jsp?parentId=<%=parentId%>')">新建目录</a></div></td>
      <td width="150" align="center"><a href="#" onclick="javascript:_addFolder('<%=request.getContextPath()%>/jsp/filemanager/upload.jsp?parentId=<%=parentId%>')">上传文件</a></td>
      <td width="195" align="center"><a href="#" onclick="javascript:_delFile('<%=request.getContextPath()%>','<%=parentId%>')">删除文件</a></td>
      <td width="151" align="center">共享文件</td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p><br>	  
		  </TD>
  </p><div align="center"></div>
  </TD>
		  </form>
</BODY></HTML>

<script language="JavaScript">

function _addFolder(url){
window.open(url,"","width=550,height=280,scrollbars=yes");
}


 function _delFile(url,parentId){

	
	document.form.action=url+"/servlet/DelFileServlet?parentId="+parentId;
    document.form.submit();
	
	
 }

</script>
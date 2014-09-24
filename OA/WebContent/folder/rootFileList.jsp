<%@ page contentType="text/html; charset=gb2312" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.folder.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.folder.control.FileType" %>
<%

List fileList = (List)request.getAttribute("fileList");
String parentId = (String)request.getAttribute("parentId");
String path = (String)request.getAttribute("path");
String accessFlag = (String)request.getAttribute("accessFlag");
String shareFlag = (String)request.getAttribute("shareFlag");
String userPercnet  = (String)request.getAttribute("userPercnet");
String userSpace = (String)request.getAttribute("userSpace");

String shareName1[]=new String[100];
shareName1=(String [])request.getAttribute("shareName1");
//out.print("shareFlag="+shareFlag+" accessFlag="+accessFlag);
%>
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312"><LINK 
href="index_files/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet" href="<%=request.getContextPath()%>/include/style.css">

<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>

<SCRIPT language=javascript src="index_files/jcommon.js"></SCRIPT>

<SCRIPT src="index_files/calendar.js"></SCRIPT>

<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></SCRIPT>

</HEAD>
<BODY topMargin=0 background="<%=request.getContextPath()%>/images/folder/bg-08.gif">
    <form name="form" method="post" action="">          
<table width="100%"  cellSpacing=0 cellPadding=0 align=center border=0>
	<tr>
		<td vAlign=top>
      <TABLE  width="90%"  cellSpacing=0 cellPadding=0 align=center border=0>
        <TR>
          <TD align=left >
            <table width=100% border=0>
                <tr valign=bottom height=21>
                  <td ><div align="center"><font color=#fb4303>当前路径：<%=path%></font> 
                    </div></td>
                    </tr>
					<tr>
                  <td height=10  width=100% > <div align="center" >用户使用空间(<%=userSpace%>M,<%=userPercnet%>%)
                      <table width="20%" height=10 border="0" cellpadding="1" cellspacing="1"   bgcolor="#CCCCCC">
                          <td  height=10 bgcolor="#FFFFFF"> 
                            <div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%=userPercnet%>%"; height=10></div></td>
                        </table></div></td><tr>
                    </table>
                   <td>     
				    </tr>
                        <tr>
                            <td height=8></td>
                          </tr>
                      </table>
         
                      <!----------文件和目录列表显示start------------------------>
      <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-12.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/folder/bg-12.gif" class="title-05">文件夹信息</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-11.gif" width="20" height="22"></div></td>
        </tr>
      </table>

            <table width="100%" border="0" align=center cellpadding="0" cellspacing="0">
              <tbody>
                
                <tr bgColor=#FFFBEF class="title-04"> 
                  <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2 ></td>
                  <td width="10%" colspan=2 height=25><div align="center">序号</div> </td>
                  <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                  <td width="22%"><div align="center">名称</div></td>
                  <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                  <td width="22%"><div align="center">描述</div></td>
                  <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                  <td width="11%"><div align="center">大小</div></td>
                  <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                  <td width="13%"><div align="center">类别</div></td>
                  <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                  <td width="22%"><div align="center">创建日期</div></td>
                  <td width=0 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=0></td>
                </tr>
                <tr> 
                  <td height="0" background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                </tr>
<%
						  int i=0; int shareNameOpe=0;
 FileType fileType = FileType.getInstance();	
 FileTypeVO typeVO = new FileTypeVO();
 Iterator Itr = fileList.iterator();
 FolderPackageVO vo = null;
 while(Itr.hasNext()){
	  vo = (FolderPackageVO)Itr.next();
	  if(vo.getFpMimeType() != null){
	  typeVO = fileType.getXMLData(vo.getFpMimeType());
	  }
	  i++;
	  if(vo.getFpIsfile().equals("0")){

%>
	<%if(i%2==0){%>
                <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
<%}else{%>
<tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" >
	<%}%>
                  <td width="5%" height=25><input type=checkbox value="<%=vo.getFpId()%>"
                        name=folderId></td>
                  <td align=middle width="5%"><%=i%></td>
                  <td title=ccc>
				 <A  href="<%=request.getContextPath()%>/servlet/ShowFileServlet?folderId=<%=vo.getFpId()%>">
				 <% if(vo.getFpMimeType()!=null){%>
				 <img src="<%=request.getContextPath()%>/images/fileType/<%=typeVO.getFilePic()%>" width="15" height="15" align="middle" style="border=0">
				 <%}else {%>
				  <img src="<%=request.getContextPath()%>/images/fileType/file.gif" width="15" height="15" align="middle"   style="border=0">
					  <%}%>
               
				  <%= shareName1[shareNameOpe++] %></a>
					</td>
                  <td title="" > <%if(vo.getFpDesc() != null){%>
              <div align="center"><%=vo.getFpDesc()%> 
                <%}%>
              </div> </td>
                  <td><%if(vo.getFpSize() != null){%><div align="center" ><%=CommUtil.getDivision(vo.getFpSize().longValue(),1048576,3)%>M</div><%}%></td>
                  <td><div align="center"><%if(vo.getFpMimeType() != null){%>
					<%=typeVO.getFileType()%>
					<%}else {%>文件<%}%>
					</div></td>
                  <td><div align="center"><%=CommUtil.getTime(vo.getFpCreatedate().longValue())%></div></td>
                </tr>
               
                <%}else{%>
<%if(i%2==0){%>
                <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
<%}else{%>
<tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" >
	<%}%>
                  <td width="5%" height=25><input type=checkbox value="<%=vo.getFpId()%>"
                        name=folderId></td>
                  <td align=middle width="5%"><%=i%></td>
                  <td title=ccc>
				  <A  href="<%=request.getContextPath()%>/servlet/ShowFileListServlet?parentId=<%=vo.getFpId()%>&entrance=<%=request.getParameter("entrance")%>">
				  <img src="<%=request.getContextPath()%>/images/folder/close.gif" width="15" height="15" align="middle"   style="border=0">
	 
				<%= shareName1[shareNameOpe++] %></a>
					</td>
                  <td title="" > <%if(vo.getFpDesc() != null){%>
              <div align="center"><%=vo.getFpDesc()%> 
                <%}%>
              </div> </td>
					  <td></td>
                  <td> <div align="center">文件夹
                  </div></td>
                  <td><div align="center"><%=CommUtil.getTime(vo.getFpCreatedate().longValue())%></div></td>
                </tr>
               <%}%>
                <tr> 
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                  <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                </tr>
              
				<%
				 
				}%>
              </tbody>
            </table>
                        <!-- 翻页 begin -->
					  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
						  <td width="1%"><img src="<%=request.getContextPath()%>/images/folder/bg-21.jpg" width="10" height="21"></td>
						  <td width="55%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"> </td>
						  <td width="30%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"><div align="right"></div></td>
						  <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-22.jpg" width="11" height="21"></div></td>
						</tr>
					  </table>      
                          <!-- 翻页 end -->
                      <br>
                      <table width=800 border=0>
                        <tbody>
                          <tr>
                            <td><div align=center>
                              
							   <%
							     if((accessFlag == null) || (accessFlag != null && accessFlag.equals("1"))){%>
							     <img src="<%=request.getContextPath()%>/images/folder/botton-rename.gif"  onclick="javascript:_rename('<%=request.getContextPath()%>','<%=parentId%>')">
							  <img src="<%=request.getContextPath()%>/images/folder/botton-delete.gif"  onclick="javascript:_delFile('<%=request.getContextPath()%>','<%=parentId%>')">
							  <%}if(shareFlag == null){%>
							   <img src="<%=request.getContextPath()%>/images/folder/botton-share.gif"  onclick="javascript:_selectPerson('<%=parentId%>')"> 
							   <img src="<%=request.getContextPath()%>/images/folder/botton-move.gif"  onclick="javascript:_showSelect('<%=request.getContextPath()%>/servlet/ShowSelectFolderServlet?parentId=<%=parentId%>&selectFlag=0&folderId=')"> 
								   <%}%>
							   <img src="<%=request.getContextPath()%>/images/folder/botton-save.gif"  onclick="javascript:_showSelect('<%=request.getContextPath()%>/servlet/ShowSelectFolderServlet?parentId=<%=parentId%>&selectFlag=1&folderId=')">
							   <img src="<%=request.getContextPath()%>/images/botton-return.gif" onclick="location.href='javascript:history.go(-1)'" style="cursor:hand">
							   </div>
							  </td>
                          </tr>
                        </tbody>
                      </table>
				
            <!-- Tail begin --></TD>
        </TR></TABLE>	  
		</td>
	</tr>
</table>
</form>
</BODY></HTML>

<script language="JavaScript">

function _addFolder(url,parentId){

	
	document.form.action=url+"/folder/addFolder.jsp?parentId="+parentId;
    document.form.submit();
	
	
 }

function _selectPerson(parentId){
	url="<%=request.getContextPath()%>/folder/share.jsp?parentId="+parentId+"&folderId=";
	//if(_check("请选择要共享的文件夹或文件")){
		for (var i=0;i<document.form.elements.length;i++){
     		var e = document.form.elements[i];
	  		if (e.name == 'folderId' && e.checked){
	  			url+=",";
		 		url+=e.value;
			}
  		}
		document.form.action=url;
		 document.form.submit();
	//}
}


 function _uploadFile(url,parentId){

	
	document.form.action=url+"/folder/upload.jsp?parentId="+parentId;
    document.form.submit();
	
	
 }


 function _delFile(url,parentId){

	
	document.form.action=url+"/servlet/DelFileServlet?parentId="+parentId;
    document.form.submit();
	window.top.leftFrame.location.reload();
	
 }

 function _rename(url,parentId){

	
	document.form.action=url+"/servlet/ShowFolderServlet?parentId="+parentId;
    document.form.submit();
	
	
 }
 function _showSelect(url){
	for (var i=0;i<document.form.elements.length;i++){
     		var e = document.form.elements[i];
	  		if (e.name == 'folderId' && e.checked){
	  			url+=",";
		 		url+=e.value;
			}
  		}	
	window.open(url,"","width=320,height=320,scrollbars=yes");
 }

 
</script>
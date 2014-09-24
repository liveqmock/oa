<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.folder.vo.*" %>
<%@ page import="com.icss.oa.folder.control.FileType" %>  
<%@ page import="com.icss.oa.folder.control.*" %>    

<%
List fileList = (List)request.getAttribute("fileList");
String parentId = (String)request.getAttribute("parentId");
String path = (String)request.getAttribute("path");
String accessFlag = (String)request.getAttribute("accessFlag");
String shareFlag = (String)request.getAttribute("shareFlag");
String userPercnet  = (String)request.getAttribute("userPercnet");
String userSpace = (String)request.getAttribute("userSpace");
//out.print("shareFlag="+shareFlag+" accessFlag="+accessFlag);
%>
<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>    
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module17");   
		String ip=request.getRemoteAddr();		  
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<LINK href="index_files/style.css" type=text/css rel=stylesheet>

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
      <td vAlign=top> <div align="center"> 
          <TABLE  width="90%"  cellSpacing=0 cellPadding=0 align=center border=0>
            <TR> 
              <TD align=left > <table width=100% border=0>
                  <tr valign=bottom height=21> 
                    <td ><div align="center"><font color=#fb4303>当前路径：<%=path%></font> 
                      </div></td>
                  </tr>
                  <tr> 
                    <td height=10  width=100% > <div align="center" >用户使用空间(<%=userSpace%>M,<%=userPercnet%>%) 
                        <table width="20%" height=10 border="0" cellpadding="1" cellspacing="1" bgcolor="#CCCCCC">
                          <td  height=10 bgcolor="#FFFFFF"> <div align="left"><img src="<%=request.getContextPath()%>/images/bbs/bar1.gif" 
                            width="<%=userPercnet%>%"; height=10></div></td>
                        </table>
                      </div></td>
                  <tr> </table>
              <td> </tr>
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
                <td height=25><div align="center">序号</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td><div align="center">名称</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td><div align="center">大小</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td><div align="center">类别</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=2></td>
                <td><div align="center">创建日期</div></td>
                <td width=2 rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/folder/bg-24.gif"><img src="<%=request.getContextPath()%>/images/folder/bg-24.gif" width=0></td>
              </tr>
              <tr>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
                <td background="<%=request.getContextPath()%>/images/folder/bg-13.gif"></td>
              </tr>
<%
 int i=0;
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
                <td align="center" height=25>
                <input type=checkbox value="<%=vo.getFpId()%>" name=folderId>
                </td>
                <td>&nbsp; 
                  <%    
                         boolean flag = false; 
                         isSHARE dd1=new isSHARE(vo.getFpId());
                      	 flag = dd1.jj;   
                      	 
                      	if(vo.getFpMimeType()!=null){
                      		
                      		if(flag){%>
                  <font color="red">[已共享]</font>
                                   <%}%>

                  <img alt="<%=vo.getFpMimeType()%>文件" src="<%=request.getContextPath()%>/images/fileType/<%=typeVO.getFilePic()%>" width="15" height="15" align="middle" style="border=0"> 
                  <%}else {
                  	if(flag){
                  %>
                  <font color="red">[已共享]</font><%}%>
                  <img alt="文件" src="<%=request.getContextPath()%>/images/fileType/file.gif" width="15" height="15" align="middle"   style="border=0"> 
                  <%}%>
                  <A  href="<%=request.getContextPath()%>/servlet/ShowFileServlet?folderId=<%=vo.getFpId()%>&entrance=<%=request.getParameter("entrance")%>">
                  <%=vo.getFpName()%></a> 
                </td>

                <td> 
                  <%if(vo.getFpSize() != null){%>
                  <div align="center" >
                  <% 
                  		double onefilesize = CommUtil.getDivision(vo.getFpSize().longValue(),1024,2);
                  		if(onefilesize<1024) out.print(onefilesize+"K");
                  		else out.print(CommUtil.getDivision(new Double(onefilesize).longValue(),1024,3)+"M");
                  %>
                  </div>
                  <%}%>
                </td>
                <td><div align="center"> 
                    <%if(vo.getFpMimeType() != null){%>
                    <%=typeVO.getFileType()%> 
                    <%}else {%>
                    文件 
                    <%}%>
                  </div></td>
                <td><div align="center"><%=CommUtil.getTime(vo.getFpCreatedate().longValue())%></div></td>
              </tr>
              <%}else{%>
              <%if(i%2==0){%>
              <tr bgcolor="#F2F9FF" onMouseOut="this.bgColor='#F2F9FF';" onMouseOver="this.bgColor='#8CC0E8';" > 
                <%}else{%>
              <tr bgcolor="#D8EAF8" onMouseOut="this.bgColor='#D8EAF8';" onMouseOver="this.bgColor='#8CC0E8';" > 
                <%}%>
                <td align="center" height=25><input type=checkbox value="<%=vo.getFpId()%>" name=folderId></td>
                <td>&nbsp
				       
				<%   isSHARE dd=new isSHARE(vo.getFpId()); %>
				<% if(!dd.jj){ %>
                  <img alt="文件夹" src="<%=request.getContextPath()%>/images/folder/close.gif" width="15" height="15" align="middle" border="0"> 
				  <% }else{ %><font color="red">[已共享]</font>
				  <img alt="共享的文件夹" src="<%=request.getContextPath()%>/folder/tree/images/shareClose.gif" width="15" height="15" align="middle" border="0">
				  <% } %>
                  <A href="<%=request.getContextPath()%>/servlet/ShowFileListServlet?parentId=<%=vo.getFpId()%>&entrance=<%=request.getParameter("entrance")%>"><%=vo.getFpName()%></a> 
                 </td>
                <td>&nbsp;</td>
                <td> <div align="center">文件夹 </div></td>
                <td><div align="center"><%=CommUtil.getTime(vo.getFpCreatedate().longValue())%></div></td>
              </tr>
              <%}%>
              <tr> 
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
              <td width="55%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"> 
                <%@ include file= "/include/defaultPageScrollBar.jsp" %>
              </td>
              <td width="30%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg" class="text-01"><div align="right"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/folder/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/folder/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
          <!-- 翻页 end -->
          <br>
          <table width=800 border=0>
            <tbody>
              <tr> 
                <td> <div align=center>
                    <%
                    if(!"kk".equals(request.getParameter("entrance"))){ 
                    if((accessFlag == null)||(accessFlag != null && accessFlag.equals("1"))){%>
                    <img src="<%=request.getContextPath()%>/images/folder/botton-new.gif"  onclick="javascript:_addFolder('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                    <img src="<%=request.getContextPath()%>/images/folder/botton-upload.gif"  onclick="javascript:_uploadFile('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                    <img src="<%=request.getContextPath()%>/images/folder/botton-rename.gif"  onclick="javascript:_rename('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                    <img src="<%=request.getContextPath()%>/images/folder/botton-delete.gif"  onclick="javascript:_delFile('<%=request.getContextPath()%>','<%=parentId%>')" style="cursor:hand"> 
                    <%}if(shareFlag == null||(shareFlag != null && shareFlag.equals("0"))){%>
                    <img src="<%=request.getContextPath()%>/images/folder/botton-share.gif"  onclick="javascript:_selectPerson('<%=parentId%>')" style="cursor:hand"> 
                    <img src="<%=request.getContextPath()%>/images/folder/botton-canshare.gif"  onclick="javascript:_delshare1('<%=parentId%>')" style="cursor:hand">
					<img src="<%=request.getContextPath()%>/images/folder/botton-move.gif"  onclick="javascript:_showSelect('<%=request.getContextPath()%>/servlet/ShowSelectFolderServlet?parentId=<%=parentId%>&selectFlag=0&folderId=','move')" style="cursor:hand"> 
                    <%}%>
                    <img src="<%=request.getContextPath()%>/images/folder/botton-save.gif"  onclick="javascript:_showSelect('<%=request.getContextPath()%>/servlet/ShowSelectFolderServlet?parentId=<%=parentId%>&selectFlag=1&folderId=','save')" style="cursor:hand">
                   <%}else{%>
                    <img src="<%=request.getContextPath()%>/images/folder/botton-save.gif"  onclick="javascript:_showSelect('<%=request.getContextPath()%>/servlet/ShowSelectFolderServlet?parentId=<%=parentId%>&selectFlag=1&folderId=','save')" style="cursor:hand">
                  <%}%>
                  </div>
                  </td>
			  </tr>
            </tbody>
          </table>
          <!-- Tail begin -->
        </div></TD>
    </TR>
  </TABLE>	  
		</td>
	</tr>
</table>
</form>
</BODY></HTML>

<script language="JavaScript">
function _checkOnly(outString,outString2){
	var i;
	var n;
	if(form.folderId == undefined){
		alert(outString);
		return false;
	}
	if(form.folderId.length == undefined){
		if(form.folderId.checked){
			return true;
		}
	}
	else{
		n=0;
		for(i=0;i<form.folderId.length&&n<2;i++){
			if(form.folderId[i].checked){
				++n;
			}
		}
		if(n>1){
			alert(outString2);
			return false;
		}
		else if(n==0){
			alert(outString);
			return false;
		}
		else if(n==1){
			return true;
		}
	}
	alert(outString);
	return false;
}

function _check(outString){
	var i;
	
	if(form.folderId == undefined){
		alert(outString);
		return false;
	}
	
	if(form.folderId.length == undefined){
		if(form.folderId.checked){
			return true;
		}
	}
	else{
		for(i=0;i<form.folderId.length;i++){
			if(form.folderId[i].checked){
				return true;
			}
		}
	}
	alert(outString);
	return false;
}

function _addFolder(url,parentId){

	
	form.action=url+"/folder/addFolder.jsp?parentId="+parentId;
	
    form.submit();
	
 }

function _selectPerson(parentId){
	url="<%=request.getContextPath()%>/servlet/GetShareServlet?parentId="+parentId+"&folderId=";
	if(_checkOnly("请选择要共享的文件夹或文件","您只能同时选择一个文件夹或文件进行共享操作！")){
		for (var i=0;i<form.elements.length;i++){
     		var e = form.elements[i];
	  		if (e.name == 'folderId' && e.checked){
		 		url+=e.value;
		 		break;
			}
  		}
		form.action=url;
		form.submit();
	}
}

function _delshare1(parentId){
	url="<%=request.getContextPath()%>/servlet/DelShareServlet?parentId="+parentId+"&folderId=";
	if(_checkOnly("请选择要删除共享的文件夹或文件","您只能同时选择一个文件夹或文件进行删除共享操作！")){
		for (var i=0;i<form.elements.length;i++){
     		var e = form.elements[i];
	  		if (e.name == 'folderId' && e.checked){
		 		url+=e.value;
		 		break;
			}
  		}
		form.action=url;
		form.submit();
		window.top.leftFrame.location.reload();
	}
}

 function _delshare(parentId){

if(_check("请选择要删除共享的文件夹或文件")){	
    if(confirm("请确认是否有取消要共享的文件夹！"))
    {
		form.action="<%=request.getContextPath()%>/servlet/DelShareServlet?parentId="+parentId;
	    form.submit();
		window.top.leftFrame.location.reload();
	}
}
	
 }


 function _uploadFile(url,parentId){

	form.action=url+"/folder/upload.jsp?parentId="+parentId;
    form.submit();
	
 }


 function _delFile(url,parentId){
	if(_check("请选择要删除的文件夹或文件")){	
	    
	    if(confirm("请确认是否要删除所选择的文件或文件夹！"))
	    {
			form.action=url+"/servlet/DelFileServlet?parentId="+parentId;
		    form.submit();
			window.top.leftFrame.location.reload();
		}
	}
 }

 function _rename(url,parentId){

if(_checkOnly("请选择要重命名的文件夹或文件","您只能同时选择一个文件夹或文件进行重命名操作！")){
	form.action=url+"/servlet/ShowFolderServlet?parentId="+parentId;
    form.submit();
}	
 }
 
 function _showSelect(url,flag){
    var out;
    if(flag=="save"){
    	out="请选择要保存的文件夹或文件"
    }
    else if(flag=="move"){
    	out="请选择要移动的文件夹或文件"
    }
    else return;
	
if(_check(out)){
	for (var i=0;i<form.elements.length;i++){
     		var e = form.elements[i];
	  		if (e.name == 'folderId' && e.checked){
	  			url+=",";
		 		url+=e.value;
			}
  		}	
	window.open(url,"","width=230,height=400,scrollbars=yes,resizable=yes,left=300,top=110");
}
 }
 
function Test(){
	window.open("<%=request.getContextPath()%>/servlet/SaveAccessServlet","","width=230,height=400,scrollbars=yes,resizable=yes,left=300,top=110");
}

 
</script>
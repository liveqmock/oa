<%@ page contentType="text/html; charset=GBK" %>
<%@ page pageEncoding="GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
String path = request.getContextPath();
%>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.folder.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="com.icss.oa.folder.control.FileType" %>
<%

List fileList = (List)request.getAttribute("fileList");
String parentId = (String)request.getAttribute("parentId");
String accessFlag = (String)request.getAttribute("accessFlag");
String shareFlag = (String)request.getAttribute("shareFlag");
String userPercnet  = (String)request.getAttribute("userPercnet");
String userSpace = (String)request.getAttribute("userSpace");
String totalsize = (String)request.getAttribute("totalsize");
String shareName1[]=new String[100];
shareName1=(String [])request.getAttribute("shareName1");

%>


<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<SCRIPT language=javascript src="index_files/jcommon.js"></SCRIPT>
<SCRIPT src="index_files/calendar.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/include/treeview.js" type="text/JavaScript"></SCRIPT>

<html>
<head>

<title>网络文件夹</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="<%=path%>/Style/css_grey.css" rel="stylesheet" id="homepagestyle" type="text/css" />
<script language="javascript">
function changeStyle(id){//切换样式
	//alert("dddd");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	document.cookie=name+"="+value+",";
}
function getCookie(name){
	var cook =document.cookie;
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(",")); 
		var value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(","));
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=path%>/Style/css_"+style+".css";
}


</script>
</head>
<%

 String page_num=(String)request.getAttribute("page_num");
 if(page_num == null || page_num.equals("null"))
    page_num="1";

%>
<body>
<form name="form" method="post" action="">  

<input type="hidden" name="_page_num" value="<%=page_num%>">
<!--主要内容去区-->
<table width="750" border="0" cellspacing="0" cellpadding="0">


    <tr>
    	<td colspan="6" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
    <!--
    <tr>
      <td>&nbsp;</td>

      <td height="30" valign="middle" class="foot_message">
        
        <table>
          <tr>
            <td height="26" valign="top" class="foot_message">总容量<%=totalsize%>M：已用<%=userSpace%>M（<%=userPercnet%>%），剩余<%=CommUtil.getLeftSpace(Float.parseFloat(totalsize), Float.parseFloat(userSpace))%>M&nbsp;</td>
          <td valign="middle">
            <div style="border-color:#B9DAF9;border-style:solid;border-width:1px;width:150px;height:10px" align="left">
              <div style="width:<%=userPercnet%>%;background-color:#ee0000;height:10px"></div>
          </div>        </td>
          </tr>
        </table>
      <div align="center"></div></td>
    </tr>
    -->
    <tr>
    	<td width="8">&nbsp;</td>
        
      <td valign="top">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
        <tr>
          <td nowrap="nowrap" width="8%" height="26" class="block_title">
              <input type="checkbox" name="all" value="checkbox" onclick="javascript:allcheck()"/>
            序号</td>
          <td colspan="4" class="block_title"><div align="center">名称</div></td>
		  <td class="block_title"><div align="center">描述</div></td>
          <td width="12%" class="block_title"><div align="center">大小</div></td>
          <td width="16%" class="block_title"><div align="center">类型</div></td>
          <td width="17%" class="block_title"><div align="center">创建日期</div></td>
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

        <tr>
          <td height="26" bgcolor="#FFFFFF"><div align="center">
              <input type="checkbox" name="folderId" value="<%=vo.getFpId()%>" />
          </div></td>
          <td colspan="4" bgcolor="#FFFFFF"  ><div  class="message_title_bold">
		  <a href="<%=request.getContextPath()%>/servlet/ShowFileServlet?folderId=<%=vo.getFpId()%>" class="message_title_bold" style="text-decoration:none">
		  <% if(vo.getFpMimeType()!=null){%>
		<img alt="<%=vo.getFpMimeType()%>文件" src="<%=request.getContextPath()%>/images/fileType/<%=typeVO.getFilePic()%>" width="15" height="15" align="middle" style="border=0"> 
		<%}else {%>
<img alt="文件" src="<%=request.getContextPath()%>/images/fileType/file.gif" width="15" height="15" align="middle" style="border=0"> 
		<%}%>
		   <%= shareName1[shareNameOpe++] %></a></div></td>
          <td height="26" bgcolor="#FFFFFF" ><%if(vo.getFpDesc() != null){%><div align="center" class="message_title_bold">
              <%=vo.getFpDesc()%> 
          </div>
		  <%}%>
		  </td>
          <td bgcolor="#FFFFFF" class="message_title_bold">
		  <%if(vo.getFpSize() != null){%>
                  <div align="center" >
					<%=CommUtil.getDivision(vo.getFpSize().longValue(),1048576,3)%>M
                  </div>
          <%}%>
		  </td>
          <td bgcolor="#FFFFFF" class="message_title_bold">
		 <div align="center"> 
                    <%if(vo.getFpMimeType() != null){%>
                    <%=typeVO.getFileType()%> 
                    <%}else {%>
                    文件 
                    <%}%>
          </div> 
		 </td>
          <td bgcolor="#FFFFFF" class="message_title_bold"><div align="center"><%=CommUtil.getTime(vo.getFpCreatedate().longValue())%></div></td>
        </tr>

<%}else{%>


    <tr>
          <td height="26" bgcolor="#FFFFFF"><div align="center">
              <input type="checkbox" name="folderId" value="<%=vo.getFpId()%>" />
          </div></td>
          <td colspan="4" bgcolor="#FFFFFF" ><div class="message_title_bold">
			<a href="<%=request.getContextPath()%>/servlet/ShowFileListServlet?parentId=<%=vo.getFpId()%>&entrance=<%=request.getParameter("entrance")%>" class="message_title_bold" style="text-decoration:none">
			  <img alt="文件夹" src="<%=request.getContextPath()%>/images/folder/close.gif" width="15" height="15" align="middle" border="0"> 

		   <%= shareName1[shareNameOpe++] %></a></div></td>
		 <td height="26" bgcolor="#FFFFFF"><%if(vo.getFpDesc() != null){%><div align="center" class="message_title_bold">
              <%=vo.getFpDesc()%> 
          </div><%}%></td>
          <td bgcolor="#FFFFFF" class="message_title_bold"> </td>
          <td bgcolor="#FFFFFF" class="message_title_bold"><div align="center"> <div align="center">文件夹 </div></td>
                <td class="message_title_bold" bgcolor="#FFFFFF"><div align="center"><%=CommUtil.getTime(vo.getFpCreatedate().longValue())%></div></td>
        </tr>
	    <%}}%>

                      <tr>
          <td height="26" colspan="9" bgcolor="#FFFFFF"><div align="center">
		  <% if((accessFlag == null) || (accessFlag != null && accessFlag.equals("1"))){%>
		  <span onClick="javascript:_rename('<%=request.getContextPath()%>','<%=parentId%>')" style="text-decoration:none;cursor:hand" class="foot_message">重命名</span> 
		  <span onClick="javascript:_delFile('<%=request.getContextPath()%>','<%=parentId%>')" style="text-decoration:none;cursor:hand" class="foot_message">删除</span> 
		   <%}if(shareFlag == null){%>
		  <span onClick="javascript:_selectPerson('<%=parentId%>')" style="text-decoration:none;cursor:hand" class="foot_message">共享</span> 
		  <span onClick="javascript:_showSelect('<%=request.getContextPath()%>/servlet/ShowSelectFolderServlet?parentId=<%=parentId%>&selectFlag=0&folderId=','move')" style="text-decoration:none;cursor:hand" class="foot_message">移动</span>  
		   <%}%>
		  <span onClick="javascript:_showSelect('<%=request.getContextPath()%>/servlet/ShowSelectFolderServlet?parentId=<%=parentId%>&selectFlag=1&folderId=','save')" style="text-decoration:none;cursor:hand" class="foot_message">保存</span> 
		   <span onClick="location.href='javascript:history.go(-1)'" style="text-decoration:none;cursor:hand" class="foot_message">返回</span>  </div></td>
              </tr>
      </table></td>
    </tr>
      </table>      
</td>
    </tr>
    
    
    <tr>
    	<td colspan="11" height="11"><img src="<%=path%>/images/kongbai.jpg" width="11" height="11" /></td>
    </tr>
</table> 
	  
	  

</form>
</body>
</html>

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

	
	form.action=url+"/mail/AddFolder.jsp?parentId="+parentId;
	
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
	}
}

function allcheck(){
	var all = document.form.all;
	var check = document.getElementsByName("folderId");
	var num = check.length;
	for(var i=0;i<num;i++){
		check[i].checked = all.checked;
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

	form.action=url+"/mail/Upload.jsp?parentId="+parentId;
    form.submit();
	
 }


 function _delFile(url,parentId){
	if(_check("请选择要删除的文件夹或文件")){	
	    
	    if(confirm("请确认是否要删除所选择的文件或文件夹！"))
	    {
			form.action=url+"/servlet/DelFileServlet?parentId="+parentId;
		    form.submit();
			window.location.reload();
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
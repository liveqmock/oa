<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%@ page import="com.icss.j2ee.util.PageScrollUtil"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfotypeVO" %>
<%
List mainDutyTypeList = (List) request.getAttribute("mainDutyTypeList");
String msgid = (String) request.getAttribute("msgid");
%>
<html>
<head>
<title>[值班分类列表]</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/zbs/include/style.css">-->
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>

<style type="text/css">
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/zbs/include/judge.js"></script>
<SCRIPT src="<%=request.getContextPath()%>/zbs/include/js/common.js"></SCRIPT>
<script language="JavaScript" src="<%=request.getContextPath()%>/zbs/include/formVerify.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/zbs/include/runFormVerify.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/zbs/include/extendString.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/zbs/include/js/calendar.js"></script>
<script language="javascript">
   function _toPage(pageno){
    
    if(pageno!='0'){
	    var str = '<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>';
	    var a1=str.lastIndexOf("=");
	    var a3=str.substr(0,a1);
	    var a4=a3+'='+pageno;
		window.location.href = a4;}
}
   
   
   
   function _delete(){
	if (IsRadioChecked(document.myForm.witid,"请选择要删除的值班分类！")){
		if(confirm("您确认要删除所选的值班分类?包含值班记录的分类无法删除!")){	
   		document.myForm.action="<%=request.getContextPath()%>/servlet/YjsDutyTypeDeleteServlet";
   		document.myForm.submit();  
   		}
   	}
   }
   function _modify(id,name,desc){
   		document.myForm.modifydutyid.value = id;
   		document.myForm.type_name.value = name;
   		document.myForm.type_desc.value = desc;
   }
   function _save(a){
   if(_check(document.myForm)){   
   		document.myForm.action="<%=request.getContextPath()%>/servlet/YjsDutyTypeSaveServlet?add="+a;
   		document.myForm.submit();
   }}

   function fPopUpCalendarDlg(ctrlobj){
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog("<%=request.getContextPath()%>/zbs/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}
  }
  	function _onload(){
  		if('1'=='<%=msgid%>'){
  			alert("无法删除!请确认此分类下无值班记录!");
  		}
  	}
</script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
</head>
<BODY text="#000000" leftMargin="0" topMargin="10" onLoad="_onload()">
<form name="myForm" method="post">
<jsp:include page= "/include/top.jsp" />
<input type="hidden" name="modifydutyid">
<table width="983" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td height="5"></td></tr>
</table>
<table width="983" border="0" align="center" cellpadding="0"
	cellspacing="0" class="text">
	<tr>
		<td bgcolor="#FFFFFF">&nbsp;</td>
		<td valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
			<tr>
				<td width="5%" height="24" class="block_title">
				<div align="center"></div>
				</td>
				
				<td width="10%" class="block_title">
				<div align="center">序号</div>
				</td>
				<td width="35%" class="block_title">
				<div align="center">值班信息分类</div>
				</td>
				<td width="45%" class="block_title">
				<div align="center">说明</div>
				</td>
			</tr>
			<%for(int i=0;i<mainDutyTypeList.size();i++){
				TbYjsWorkinfotypeVO vo = (TbYjsWorkinfotypeVO)mainDutyTypeList.get(i);
				String name = vo.getWitName();
				String memo = vo.getWitMemo();
			%>
			<tr>
				<td height="26" bgcolor="#FFFFFF">
				<div align="center"><input type="radio" name="witid"
					value="<%=vo.getWitId()%>" onClick="javascript:_modify('<%=vo.getWitId()%>','<%=name%>','<%=memo%>')"/></div>
				</td>
				<td bgcolor="#FFFFFF" class="blue3-12" align="center"><%=i+1%></td>
				
				<td bgcolor="#FFFFFF" class="blue3-12">
				<div align="center"><%=name%></div>
				</td>
				<td bgcolor="#FFFFFF">
				<table border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td class="blue3-12"><%=memo%></td>
						<td><!--<img src="<%=request.getContextPath()%>/images/icon_attachment.gif" width="16" height="16" hspace="5" />--></td>
					</tr>
				</table>
				</td>
			</tr>
			<%}%>
			
          
          
          
          
       <tr><td width="50%" align="left"> 

<%
try{
	int curPageNum = com.icss.j2ee.util.PageScrollUtil.getPageNum();
	int pageCount = com.icss.j2ee.util.PageScrollUtil.getPageCount();
	int tiao  = com.icss.j2ee.util.PageScrollUtil.getRowCount();
%>
<%
	if(curPageNum != 1 && pageCount > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getFirstPageUrl() %>">首页</a>&nbsp;&#9474;
<%
	}else{
%>
首 页&nbsp;&#9474;
<%
}
if(curPageNum > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getPriorPageUrl() %>">上一页</a>&nbsp;&#9474;
<%
}else{
%>
上一页&nbsp;&#9474;
<%
}
if(curPageNum < pageCount){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getNextPageUrl() %>">下一页</a>&nbsp;&#9474;
<%
}else{
%>
下一页&nbsp;&#9474;
<%
}
if(curPageNum != pageCount && pageCount > 1){
%>
<a href="<%= com.icss.j2ee.util.PageScrollUtil.getLastPageUrl() %>">尾 页</a>
<%
}else{ 
%>
尾 页
<%
}
%>

</td>
<td align="right" width="50%">
<select name ='ee' onChange="javaScript:_toPage(this.value)" style="width:50px;font-size:9.5px">
    <option value='0'>页 数</option>
<% for(int _fanyeNo=1;_fanyeNo<=pageCount;++_fanyeNo){%>
	<option value='<%= _fanyeNo%>'><%= _fanyeNo%></option>
<%}%>
</select>
第&nbsp;<%=curPageNum%>&nbsp;页&nbsp;共&nbsp;<%=pageCount%>&nbsp;页&nbsp;&nbsp;共有<%=tiao%>条记录
<%
}catch(Exception e){
%>
	An error occured, please check you web.xml file.
<%
}	
%>
</td></tr>
          
          
          
          
          
          
          
          
          
          
          
			
		</table>
		</td>
	</tr>
</table>




				


<table width="100%" height="50" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td height="50" align="center">
		<table width="400" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr align="center">
				<!--<td width="33%"><input name="Submit" type="button" class="word6"
					value="新增分类" onClick=""></td>-->
				<!--<td width="50%"><input name="modify" type="button" class="word4"
					value="修改" onClick="javascript:_modify();"></td>-->
				<td width="50%"><input name="delete" type="button" class="word4"
					value="删除" onClick="javascript:_delete();"></td>
			
			<!-- <td width="50%"><input name="delete" type="button" class="word4"
					value="删除" >
					<a href="<%=request.getContextPath()%>/servlet/YjsDutyTypeDeleteServlet"></a>
			</td>-->
			</tr>
		</table>
		</td>
	</tr>
</table>

<table width="983" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
  	<td height="10" colspan="2">&nbsp;</td>
  </tr>
  <tr>
  	<td bgcolor="#FFFFFF">&nbsp;</td>
    <td valign="top">
      	<TABLE  cellpadding="2" cellspacing="1" width="983" align=center border=0 class="table_bgcolor">
          	<TR>
            	<td height="24" colspan="2" class="block_title">
				<div align="center">
            		值班信息分类详细信息
            	</div>
				</td>          
          	</TR>
			<TR>
				<td width="15%" height="20" class="message_title_bold" bgcolor="#FFFFFF">
					<div align="left">值班信息分类名称</div>
				</td>
				<td width="85%" bgcolor="#FFFFFF" class="blue3-12-b">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><div align="right" class="blue3-12-b">
									<div align="right">
											<!--2008年1月6日 值班日志
											<select name="dutytype">
												<option>白班</option>
												<option>夜班</option>
											</select>-->
									</div>
								</div>
							</td>
							<td>
								<div align="right" class="blue3-12-b" >
									<div align="right">
										<input name="type_name" type="text" size="30" notnull="不能为空" title="值班信息分类名称">
									</div>
								</div>							
							</td>
						</tr>
					</table>
				</td>
        	</TR>
        	<TR>
				<td width="10%" height="20"  class="blue3-12-b" bgcolor="#FFFFFF">
					<div align="left">说明</div>
				</td>
				<td width="90%" bgcolor="#FFFFFF" class="blue3-12-b">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><div align="right" class="blue3-12-b">
									<div align="right">
											<!--2008年1月6日 值班日志
											<select name="dutytype">
												<option>白班</option>
												<option>夜班</option>
											</select>-->
									</div>
								</div>
							</td>
							<td>
								<input name="type_desc" type="text" size="60" notnull="不能为空" title="值班信息分类描述">
							</td>
						</tr>
					</table>
				</td>
        	</TR>	
			</table>
		</td>
	</tr>
</table>
<table width="100%" height="20" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td height="20" align="center">
		<table width="400" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr align="center">
			<td width="50%"><input name="newtype" type="button" class="word6"
					value="新增" onClick="javascript:_save(1);"></td>
				<td width="50%"><input name="Submit" type="button" class="word6"
					value="保存" onClick="javascript:_save(2);"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</form>
</body>
</html>

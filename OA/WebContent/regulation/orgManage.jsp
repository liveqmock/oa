<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.regulation.handler.RegulationHandler"%>
<%@ page import="com.icss.regulation.vo.RegulationOrgVO"%>
<%@ page import="com.icss.oa.address.vo.*"%>

<%
List orglist = (List) request.getAttribute("orglist");
List eorglist = RegulationHandler.getOrg(3);
String msgid = (String) request.getAttribute("msgid");
%>
<html>
<head>
<title>[部门列表]</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/zbs/include/judge.js"></script>
<SCRIPT src="<%=request.getContextPath()%>/zbs/include/js/common.js"></SCRIPT>
<script language="JavaScript" src="<%=request.getContextPath()%>/zbs/include/formVerify.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/zbs/include/runFormVerify.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/zbs/include/extendString.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/zbs/include/js/calendar.js"></script>
<script language="javascript">
   
   function _delete(){
	if (IsRadioChecked(document.myForm.did,"请选择要删除的组织！")){
		if(confirm("您确认要删除所选的组织?该组织下所有的管理制度将被删除!")){	
   		document.myForm.action="<%=request.getContextPath()%>/servlet/RegulationOrgManageServlet?type=del";
   		document.myForm.submit();  
   		}
   	}
   }
   function _modify(id,name,desc){
   		document.myForm.modifyid.value = id;
   		document.myForm.o_name.value = name;
   		document.myForm.o_sequence.value = desc;
   }
   
   function _edit(){
   		 if(_check(document.myForm)){
   		 	document.myForm.action="<%=request.getContextPath()%>/servlet/RegulationOrgManageServlet?type=edit";
   			document.myForm.submit();
   		 }
   }
   function _new(){
   if(_check(document.myForm)){   
   		document.myForm.action="<%=request.getContextPath()%>/servlet/RegulationOrgManageServlet?type=new";
   		document.myForm.submit();
   }}

	function _enew(){
	if(true){
	   	document.myForm.action="<%=request.getContextPath()%>/servlet/RegulationOrgManageServlet?type=enew";
   		document.myForm.submit();
	}
	}
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

<input type="hidden" name="modifyid">

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
				<div align="center">部门名称</div>
				</td>
				<td width="35%" class="block_title">
				<div align="center">排序</div>
				</td>
			</tr>
			<%for(int i=0;i<orglist.size();i++){
				RegulationOrgVO vo = (RegulationOrgVO)orglist.get(i);
				String orgname = vo.getOrgname();
				Integer sequence = vo.getSequence();
			%>
			<tr>
				<td height="26" bgcolor="#FFFFFF">
				<div align="center"><input type="radio" name="did"
					value="<%=vo.getOrguuid()%>" onClick="javascript:_modify('<%=vo.getOrguuid()%>','<%=orgname%>','<%=sequence%>')"/></div>
				</td>
				<td bgcolor="#FFFFFF" class="blue3-12" align="center"><%=i+1%></td>
				
				<td bgcolor="#FFFFFF" class="blue3-12">
				<div align="center"><%=orgname%></div>
				</td>
				<td bgcolor="#FFFFFF">
				<table border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td class="blue3-12"><%=sequence%></td>
						<td><!--<img src="<%=request.getContextPath()%>/images/icon_attachment.gif" width="16" height="16" hspace="5" />--></td>
					</tr>
				</table>
				</td>
			</tr>
			<%}%>
			<tr>
				<td height="26" colspan="6" bgcolor="#E0EDF8">
					<%@ include file= "/include/defaultPageScrollBar.jsp" %>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table width="100%" height="30" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" align="center">
		<table width="400" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr align="center">
				<td width="50%"><input name="delete" type="button" class="word4"
					value="删除" onClick="javascript:_delete();"></td>
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
            	<td height="24" colspan="9" class="block_title">
				<div align="center">
            		组织详细信息
            	</div>
				</td>          
          	</TR>
			<TR bgcolor="#FFFFFF">
				<td width="15%" height="20" class="message_title_bold">
					<div align="left">部门名称</div>
				</td>
				<td width="10%">
					<input name="o_name" type="text" size="20" notnull="不能为空" title="组织名称">
				</td>
				<td width="10%" height="20" class="message_title_bold" >
					<div align="left">序号</div>
				</td>
				<td width="10%">
				<input name="o_sequence" type="text" size="20" notnull="不能为空" title="序号">
				</td>
				<td>
					<input name="newtype" type="button" class="word6" value="新增" onClick="javascript:_new();" />
					<input name="Submit" type="button" class="word6" value="修改" onClick="javascript:_edit();" />
				</td>
        	</TR>
        	
        	<TR bgcolor="#FFFFFF">
				<td width="10%" height="20" class="message_title_bold">
					<div align="left">部门名称</div>
				</td>
				<td width="10%">
					<select name="e_org" id="e_org" class="AdvInput">
							<%
								if (eorglist != null) {
										Iterator iter = eorglist.iterator();
										int i = 0;
										while (iter.hasNext()) {
											++i;
											SysOrgVO svo = (SysOrgVO) iter.next();
								%>
									<option value="<%=svo.getOrguuid()+"@"+svo.getCnname()%>"><%=svo.getCnname()%></option>
								<%
									}
								}
								%>
							</select>
				</td>
				<td width="10%" height="20" class="message_title_bold" >
					<div align="left">序号</div>
				</td>
				<td width="10%">
				<input name="e_sequence" type="text" size="20" title="序号">
				</td>
				<td>
				<input name="newtype" type="button" class="word6" value="从已有部门新增" onClick="javascript:_enew();" />
				</td>
        	</TR>

        </table>
		</td>
	</tr>
	        <tr height="20px" cosplan="10">
        		<td>&nbsp;</td>
        	</tr>
</table>

</form>
</body>
</html>

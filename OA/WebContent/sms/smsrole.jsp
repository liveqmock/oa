<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.sms.vo.SMSRoleVO"%>
<%
	Collection roleCollection = (Collection) request
			.getAttribute("roleList");
	Iterator roleIterator = roleCollection.iterator();
	
	String exist = (String) request.getAttribute("roleNameExist");
%>
<html>
	<head>
		<title>短信权限管理</title>
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
		<LINK href="<%=request.getContextPath()%>/include/style.css"
			rel=stylesheet>
		<link href="<%=request.getContextPath()%>/Style/css.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />

		<style type="text/css">
<!--
.STYLE1 {
	color: #006699;
	font-size: 12px;
}
-->
</style>

		<script language="JavaScript">
function UpdateApp(id,rolename,isout,ishistory,sendnumber,roledes)
{		
	 document.roleForm.id.value = id;
	 document.roleForm.rolename.value    = rolename;
	 document.roleForm.sendnumber.value  = sendnumber;
	 document.roleForm.roledes.value     = roledes;
	 document.roleForm.isout[isout].checked = true;
	 document.roleForm.ishistory[ishistory].checked = true;
	 
}
function _CheckForm(){
  if(document.roleForm.rolename.value==""){
    alert("请填写权限名称！");
    document.roleForm.rolename.focus;
    return false
    }
    if(document.roleForm.sendnumber.value==""){
    alert("请填写群发人数！");
    document.roleForm.sendnumber.focus;
    return false
    }
  if(document.roleForm.roledes.value==""){
    alert("请填写权限描述！");
    document.roleForm.roledes.focus;
    return false
    }
   return true
 }

function _Add(){

  if(!_CheckForm()){
    return false
  }
  document.roleForm.action="<%=request.getContextPath()%>/servlet/SMSRoleAddServlet";
  document.roleForm.submit();
  
 }
 
 function _Update()
 {
     if(!roleForm.id){
		alert("没有选择分组！");
      return false;
    }
  if(!_CheckForm()){
    return false
   }
   document.roleForm.action="<%=request.getContextPath()%>/servlet/SMSRoleAlterServlet";
   document.roleForm.submit();
}
   function _Delete()
  {
     if(!roleForm.id){
		alert("没有选择分组！");
      return false
    }
    if(!_CheckForm()){
    return false
   }
   ok=confirm("此操作将删除该权限组下的所有关联信息,您确定要进行吗?");
    if(ok){
     document.roleForm.action="SMSRoleDelServlet";
     document.roleForm.submit()
	}
    else{
      return false
    }
  }
  
  function isNumber(e) {   
       if ( ((event.keyCode > 47) && (event.keyCode < 58))||(event.keyCode == 8) ) {   
            return true;   
        } else {   
            return false;   
        }   
    
}   


</script>
	</head>
	<body>
		<jsp:include page= "/include/top.jsp" />

			<br>
			<form name="roleForm" method="post" action="">
				<input type="hidden" name="id" id="id">
				<input type="hidden" name="sendType">


				<table width="983" align="center" border="0" cellspacing="0" cellpadding="0">

					<tr>
						
						<td valign="top">
							<%
					if(exist != null && exist.equals("true")){
						
						out.print("<font color=red >权限组："+(String)request.getAttribute("roleName")+" 已存在，请重新添加！</font><br>");
					}
					%>
							<table width="100%" border="0" cellpadding="2" cellspacing="1"
								bgcolor="#B9DAF9">
								<tr>
									<td width="5%" height="24" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white-12-b">
										编号
									</td>
									<td width="17%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										角色
									</td>
									<td width="9%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										人员
									</td>
									<td width="12%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										群发人数
									</td>
									<td width="12%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										系统外用户
									</td>
									<td width="12%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										历史纪录查看
									</td>
									<td width="35%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										说明
									</td>
								</tr>
								<%
									if (!roleIterator.hasNext()) {
								%>
								<tr bgColor='#D8EAF8'>
									<td height="52" class="text-01" colspan=9>
										<div align="center">
											<br>
											<br>
											还没有权限信息！
										</div>
									</td>

								</tr>

								<tr>
									<td colspan=9 height="2"
										background="<%=request.getContextPath()%>/images/bg-13.gif"
										class="text-01"></td>

								</tr>
								<%
									}
									int index = 0;
									while (roleIterator.hasNext()) {
										SMSRoleVO roleVO = (SMSRoleVO) roleIterator.next();
										index++;
										String color = "#F2F9FF";
										if (index % 2 == 1)
											color = "#D8EAF8";
								%>

								<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
									onMouseOver="this.bgColor='#8CC0E8';">
									<td height="26" align="center" class="blue3-12"><%=index%></td>
									<td align="center" class="blue3-12" >
										<a href="javascript:UpdateApp('<%=roleVO.getId()%>','<%=roleVO.getRolename()%>','<%=roleVO.getIsout()%>','<%=roleVO.getIshistory()%>','<%=roleVO.getSendnumber()%>','<%=roleVO.getRoledes()%>')" ><%=roleVO.getRolename()%></a></td>
									<td align="center" class="blue3-12"><a href="ListRoleInfoServlet?id=<%=roleVO.getId()%>&name=<%=roleVO.getRolename()%>">查看</a>
									</td>
									<td align="center" class="blue3-12"><%=roleVO.getSendnumber()%>人</td>
									<td align="center" class="blue3-12"><%if (roleVO.getIsout().intValue()==1) {%>可以 <%}else{ %>不可以<%} %></td>
									<td align="center" class="blue3-12"><%if (roleVO.getIshistory().intValue()==1) {%>全部 <%}else{ %>本人<%} %></td>
									<td class="blue3-12"><%=roleVO.getRoledes()%></td>
								</tr>

								<%
									}
								%>
							<tr>
        						<td height="26" colspan="8" bgcolor="#E0EDF8">
        						<%@ include file="../include/defaultPageScrollBar.jsp"%>
        						</td>
        						</tr>
							</table>

						</td>
						
					</tr>
					<tr>
						
						<td valign="top">
							<div align="left"></div>
						</td>
						
					</tr>
				</table>
			

				<table width="983" align="center" border="0" cellspacing="0" cellpadding="0">
					<tr>
						
						<td valign="top">
							<table width="100%" border="0" cellpadding="2" cellspacing="1"
								bgcolor="#B9DAF9">
								<tr>

									<td width="26%"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8">
										<div align="center">
											<div align="center" class="white2-12-b">
												功能
											</div>
										</div>
									</td>
									<td width="62%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										分配
									</td>
								</tr>
								<tr>

									<td align="center" bgcolor="#FFFFFF">
										角色名
									</td>
									<td align="center" bgcolor="#FFFFFF" class="blue3-12">

										<input type="text" name="rolename" id="rolename">
									</td>
								</tr>
								<tr>

									<td align="center" bgcolor="#FFFFFF" class="blue3-12">
										群发人数
									</td>
									<td align="center" bgcolor="#FFFFFF" class="blue3-12">
										&nbsp;
										<input name="sendnumber" type="text" id="sendnumber" size="5"
											onkeypress="javascript:return isNumber(event);" />
										人
									</td>
								</tr>
								<tr>

									<td align="center" bgcolor="#FFFFFF" class="blue3-12">
										系统外用户
									</td>
									<td align="center" bgcolor="#FFFFFF" class="blue3-12">
										<INPUT type=radio name="isout" value=0>
										不可以&nbsp;
										<INPUT type=radio name="isout" value=1>
										可以&nbsp;&nbsp;

									</td>
								</tr>
								<tr>

									<td align="center" bgcolor="#FFFFFF" class="blue3-12">
										历史纪录查看
									</td>
									<td align="center" bgcolor="#FFFFFF" class="blue3-12">
										<INPUT type=radio name="ishistory" value=0>
										本人 &nbsp;&nbsp;
										<INPUT type=radio name="ishistory" value=1>
										全部&nbsp;&nbsp;
									</td>
								</tr>
								<tr>

									<td align="center" bgcolor="#FFFFFF" class="blue3-12">
										说明
									</td>
									<td align="center" bgcolor="#FFFFFF" class="blue3-12">
										<INPUT type=text name=roledes size=60%>
									</td>
								</tr>
								<tr>
									<td height="26" colspan="2" bgcolor="#E0EDF8">
										<br>
									</td>
								</tr>
							</table>
						</td>
						
					</tr>
					<tr>
						
						<td valign="top">
							<div align="left"></div>
						</td>
						
					</tr>
				</table>
				<table align="center" style="width:983px; margin:0 auto;">
				<tr><td height="40">
				<img src="<%=request.getContextPath()%>/images/bt_new.gif" border=0
					style="cursor: hand" onclick="javascript:_Add()" />
				&nbsp;&nbsp;
				<img src="<%=request.getContextPath()%>/images/bt_update.gif"
					border=0 style="cursor: hand" onclick="javascript:_Update()" />
				&nbsp;&nbsp;
				<img src="<%=request.getContextPath()%>/images/bt_del.gif" border=0
					style="cursor: hand" onclick="javascript:_Delete()" />
				</td></tr></table>
			</form>

	</body>
</html>

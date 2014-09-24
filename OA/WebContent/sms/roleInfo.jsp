<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="com.icss.resourceone.sdk.framework.Person"%>
<%@ page import="com.icss.oa.sms.vo.*"%>
<%

Collection roleinfoCollection = (Collection)request.getAttribute("roleinfoList");

Iterator roleinfoIterator = roleinfoCollection.iterator();

String rolename = (String)request.getAttribute("rolename");
Integer id =(Integer)request.getAttribute("id");
%>
<jsp:useBean id="handler"
	class="com.icss.oa.filetransfer.handler.personInfoHandler" />
<html>

	<head>
		<title>人员列表</title>
		<LINK href="<%=request.getContextPath()%>/include/style.css"
			rel=stylesheet>
		<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />

		<SCRIPT LANGUAGE="JavaScript">
function _selectPerson(path){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_addPerson&sessionname=selectorgperson','addressbook','width=700,height=550,scrollbars=yes,resizable=yes');
}

function _addPerson(usernamestring){
	window.top.location = "<%=request.getContextPath()%>/servlet/AddRoleInfoServlet?id=<%=id%>";
	return true;
}

function check(){
	for (var i=0;i<document.groupForm.elements.length;i++)
   {
     var e = document.groupForm.elements[i];
	  if (e.name == 'personid' && e.checked){
		 return true;
		}
   }
   return false;
}

function _deletePeople(){
	if(check()){
		document.groupForm.action="<%=request.getContextPath()%>/servlet/DelRolePersonServlet?id=<%=id%>";
		document.groupForm.submit();
	}
	else{
		alert("请选择要删除的人员！");
	}
}

function CheckAll()
 {
   for (var i=0;i<document.groupForm.elements.length;i++)
   {
     var e = document.groupForm.elements[i];
	  if (e.name == 'personid')
		 e.checked = document.groupForm.allbox.checked;
   }
 }
 
function _back(){

     document.groupForm.action="<%=request.getContextPath()%>/servlet/SMSRoleServlet";
	 document.groupForm.submit();
}
</script>
	</head>
	<body>
			<jsp:include page= "/include/top.jsp" />

		<div align="center">
			<br>
			<form name="groupForm" method="post" action="">

				<table width="100%" border="0" cellspacing="0" cellpadding="0">

					<tr>
						<td bgcolor="#FFFFFF">
							&nbsp;
						</td>
						<td valign="top">
							<table width="100%" border="0" cellpadding="2" cellspacing="1"
								bgcolor="#B9DAF9">
								<tr>
									<td width="100%" height="24" colspan="10" align="left"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white-12-b">
										<b><%=rolename%> 分组人员列表</b>
								</tr>
								<tr>
									<td width="2%" height="24" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white-12-b">

									</td>
									<td width="4%" height="24" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white-12-b">
										序号
									</td>
									<td width="6%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										姓名
									</td>
									<td width="10%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										性别
									</td>
									<td width="10%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										职务
									</td>
									<td width="15%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										电话
									</td>
									<td width="15%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										部门
									</td>
								</tr>
								<%
									if (!roleinfoIterator.hasNext()) {
								%>
								<tr bgColor='#D8EAF8'>
									<td height="52" class="text-01" colspan=9>
										<div align="center">
											<br>
											<br>
											还没有添加分组人员！
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
EntityManager entityManager = EntityManager.getInstance();
while(roleinfoIterator.hasNext()){
	SMSPersonRoleSysPersonSearchVO roleInfoVO = (SMSPersonRoleSysPersonSearchVO)roleinfoIterator.next();
	
	Person person = null;	
	
	Integer k11 = null;
	
    try{
		person = entityManager.findPersonByUuid(roleInfoVO.getUserid());
		k11 = person.getSex();
	}
	catch(EntityException e){
		e.printStackTrace() ;
	}
	if(k11!=null){
	
	index ++;
	String color = "#F2F9FF";
	if(index % 2 == 1)
		color = "#D8EAF8";
								%>

								<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
									onMouseOver="this.bgColor='#8CC0E8';">
									<td height="26" align="center" class="blue3-12">
										<div align="center">
											<input type="checkbox" name="personid"
												value="<%=roleInfoVO.getUserid()%>">
											<%
            String sex ="未知";
            if (person.getSex().equals(new Integer(0)))
				sex = "保密";
			if (person.getSex().equals(new Integer(1)))
				sex = "男";
			if (person.getSex().equals(new Integer(2)))
				sex = "女";
			if (person.getSex().equals(new Integer(3)))
				sex = "未知";

%>
										</div>
									</td>
									<td align="center" class="blue3-12"><%=index%></td>
									<td align="center" class="blue3-12"><%=person.getFullName()%></td>
									<td align="center" class="blue3-12"><%= sex %></td>
									<td align="center" class="blue3-12"><%= person.getJob()== null?"":person.getJob() %></td>
									<td align="center" class="blue3-12">
										<%= person.getOfficetel()%></td>
									<td class="blue3-12">
										<%= handler.getPersonJUPositionInJsp(roleInfoVO.getUserid())%>
									</td>

								</tr>

								<%
									}
									}
								%>
								<tr>
									<td height="26" colspan="10" bgcolor="#E0EDF8">
										<%@ include file="../include/defaultPageScrollBar.jsp"%>
									</td>
								</tr>

							</table>
							<p align="center">
								<input type="checkbox" name="allbox" value="Check All"
									onClick="CheckAll();">
								<label for="allbox">
									<a href="javascript:CheckAll();"
										onClick="allbox.checked=!allbox.checked;">全选</a>
								</label>
								<img src="<%=request.getContextPath()%>/images/bt_sp.gif"
									style="cursor: hand"
									onclick="_selectPerson('<%=request.getContextPath()%>')">

								<img src="<%=request.getContextPath()%>/images/bt_del.gif"
									style="cursor: hand" onclick="_deletePeople()">
								<img src="<%=request.getContextPath()%>/images/bt_back.gif"
									onclick="_back()" style="cursor: hand">
							</p>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>

					<tr>
						<td width="11" bgcolor="#FFFFFF">
							<img src="<%=request.getContextPath()%>/images/kongbai.gif"
								width="11" height="11" />
						</td>
						<td valign="top">
							<div align="left"></div>
						</td>
						<td width="11">
							<img src="<%=request.getContextPath()%>/images/kongbai.gif"
								width="11" height="11" />
						</td>
					</tr>
				</table>

			</form>
		</div>
	</body>

</html>

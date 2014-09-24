<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.sms.vo.SMSReceiveVO"%>
<%@ page import="com.icss.oa.sms.handler.SMSHandler"%>

<%
	Collection historyCollection = (Collection) request.getAttribute("recieveList");
	Iterator historyIterator = historyCollection.iterator();
	
%>
<html>
	<head>
		<title>短信记录</title>
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
<script language="JavaScript" type="text/JavaScript">
  function _openNew(url)
    {
        window.open(url,"","toolbar=no,scrollbars=yes,resizable=yes,width=500,height=350,left="+(screen.width-500)/2+",top="+(screen.height-350)/2);
    }
    
    function checkAll(e, itemName)
	{
  		var aa = document.getElementsByName(itemName);
  		for (var i=0; i<aa.length; i++)
   		aa[i].checked = e.checked;
	}
  function _delete()
  {
  	if(check()){
		document.historyForm.action="<%=request.getContextPath()%>/servlet/DeleteRecieveServlet";
		document.historyForm.submit();
	}
	else{
		alert("请选择要删除的记录！");
	}
  }
  
  function check(){
	for (var i=0;i<document.historyForm.elements.length;i++)
   {
     var e = document.historyForm.elements[i];
	  if (e.name == 'historyid' && e.checked){
		 return true;
		}
   }
   return false;
}
</script >
	</head>
	<body>
	<jsp:include page= "/include/top.jsp" />
		<div align="center">
			<br>
			<form name="historyForm" method="post" action="">

				<table width="100%" border="0" cellspacing="0" cellpadding="0" >

					<tr>
						<td bgcolor="#FFFFFF">
							&nbsp;
						</td>
						<td valign="top">
							<table width="100%" border="0" cellpadding="2" cellspacing="1"
								class="table_bgcolor">
								<tr class="block_title">
								<td width="100%" height="24"  colspan="6" align="center">
								短信纪录
								</tr>
								<tr class="table_bgcolor">
									<td width="2%" height="24" align="center" class="block_title">
									<input type="checkbox" name="all" onclick="checkAll(this,'historyid')" /> 
									</td>
									<td width="4%" height="24" align="center" class="block_title">
										序号
									</td>
									<td width="10%" align="center" class="block_title">
										发送人
									</td>
									<td width="15%" align="center" class="block_title">
										时间
									</td>
									<td width="80%" align="center" class="block_title">
										短信内容
									</td>

								</tr>
								<%
									if (!historyIterator.hasNext()) {
								%>
								<tr bgColor='#D8EAF8'>
									<td height="52" class="text-01" colspan=9>
										<div align="center">
											<br>
											<br>
											还没有纪录！
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
									while (historyIterator.hasNext()) {
										SMSReceiveVO vo = (SMSReceiveVO) historyIterator.next();
										index++;
										String color = "#eff5e7";
										if (index % 2 == 1)
											color = "#FFFFFF";
										String name = SMSHandler.getUserByPhone(vo.getFromNo());
								%>

								<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
									onMouseOver="this.bgColor='#e2f2cd';">
									<td height="26" align="center" class="message_title">
									<input type="checkbox" name="historyid" value=<%=vo.getId()%>></td>
									<td align="center" class="message_title"><%=index%></td>
									<td align="center" class="message_title"><%=name%></td>
									<td align="center" class="message_title"><%=vo.getTime() %>
									<td class="message_title">
									<a href="#" style="text-decoration:underline;" onclick='javascript:_openNew("<%=request.getContextPath()%>/sms/smsReply.jsp?id=<%=vo.getId()%>&fromNo=<%=vo.getFromNo()%>&name=<%=name %>&content=<%=vo.getCotent()%>")'> 
									<%=vo.getCotent()%></a></td>
								</tr>

								<%
									}
								%>
								<tr>
        						<td height="26" colspan="6" bgcolor="#E0EDF8">
								<%@ include file="../include/defaultPageScrollBar.jsp"%>
								</td>
        						</tr>
        						<tr>
        						<td height="26" colspan="6" bgcolor="#E0EDF8" align="center">
        						<img src="<%=request.getContextPath()%>/images/bt_del.gif" style="cursor:hand" onclick="_delete()">
        						</td>
        						</tr>

							</table>
							
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

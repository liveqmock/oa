<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.sms.vo.SMSHistoryVO"%>
<%
	Collection historyCollection = (Collection) request.getAttribute("historylist");
	Iterator historyIterator = historyCollection.iterator();
	
%>
<html>
	<head>
		<title>短信历史纪录</title>
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
<script language="JavaScript" type="text/JavaScript">
  function _openNew(url)
    {
        window.open(url,"","toolbar=no,width=500,height=400,left="+(screen.width-500)/2+",top="+(screen.height-400)/2);
    }
    
  function _delete()
  {
  	if(check()){
		document.historyForm.action="<%=request.getContextPath()%>/servlet/DelSMSHistoryServlet";
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

function CheckAll()
 {
   for (var i=0;i<document.historyForm.elements.length;i++)
   {
     var e = document.historyForm.elements[i];
	  if (e.name == 'historyid')
		 e.checked = document.historyForm.allbox.checked;
   }
 }
</script >
	</head>
	<body>
	<jsp:include page= "/include/top.jsp" />

			<br>
			<form name="historyForm" method="post" action="">

				<table width="983" align="center" border="0" cellspacing="0" cellpadding="0">

					<tr>
						
						<td valign="top">
							<table width="100%" border="0" cellpadding="2" cellspacing="1"
								bgcolor="#B9DAF9">
								<tr>
								<td width="100%" height="24"  colspan="6" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white-12-b">
										短信历史纪录
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
										发送人
									</td>
									<td width="10%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										时间
									</td>
									<td width="10%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
										接收人
									</td>
									<td width="15%" align="center"
										background="<%=request.getContextPath()%>/images/2-title-05.gif"
										bgcolor="#E0EDF8" class="white2-12-b">
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
											还没有历史纪录！
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
										SMSHistoryVO historyVO = (SMSHistoryVO) historyIterator.next();
										index++;
										String color = "#F2F9FF";
										if (index % 2 == 1)
											color = "#D8EAF8";
								%>

								<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';"
									onMouseOver="this.bgColor='#8CC0E8';">
									<td height="26" align="center" class="blue3-12"><input type="checkbox" name="historyid" value=<%=historyVO.getId()%>></td>
									<td align="center" class="blue3-12"><%=index%></td>
									<td align="center" class="blue3-12"><%=historyVO.getSendername()%></td>
									<td align="center" class="blue3-12"><%=historyVO.getTime() %>
									<td align="center" class="blue3-12">
									<%if (historyVO.getReceivername()!=null&&historyVO.getReceivername().length()>10){%> <%=historyVO.getReceivername().substring(0,10)%>... <% }else{ %> <%=historyVO.getReceivername()%> <%} %></td>
									
									<td class="blue3-12">
									<a href="#" onclick='javascript:_openNew("<%=request.getContextPath()%>/servlet/SMSHistoryDetailServlet?id=<%=historyVO.getId() %>")'> 
									<%if (historyVO.getContent().length()>20){%> <%=historyVO.getContent().substring(0,20)%>... <% }else{ %> <%=historyVO.getContent()%> <%} %></a></td>
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
								
        						<td height="26" colspan="6" bgcolor="#E0EDF8" align=center>
								<input type="checkbox" name="allbox" value="Check All"
									onClick="CheckAll();">
								<label for="allbox">
									<a href="javascript:CheckAll();"
										onClick="allbox.checked=!allbox.checked;">全选</a>
								</label>
        						<img src="<%=request.getContextPath()%>/images/bt_del.gif" style="cursor:hand" onclick="_delete()">
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

			</form>
		</div>
	</body>
</html>

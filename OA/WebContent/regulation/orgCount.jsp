<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.regulation.vo.RegulationCountVO"%>
<%@ page import="com.icss.regulation.handler.RegulationHandler" %>

<%
	List Rlist = (List) request.getAttribute("countList") == null ? new ArrayList()
			: (List) request.getAttribute("countList");
%>
<html>
	<head>
		<title>管理制度系统</title>
		<link href="<%=request.getContextPath()%>/Style/css.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/Style/css_grey.css"
			id="homepagestyle" rel="stylesheet" type="text/css" />
		<style type="text/css">
</style>

<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/regulation/include/date/WdatePicker.js"></SCRIPT>

	
	<script language="javascript">
   function _new(){
   		document.form1.action="<%=request.getContextPath()%>/regulation/edit.jsp";
		document.form1.submit();
   }
   function _search(){
   		document.form1.action="<%=request.getContextPath()%>/servlet/MainDutyListServlet?SearchFlag=1";
		document.form1.submit();	
   }
   function _edit(id){
   		document.form1.action="<%=request.getContextPath()%>/regulation/edit.jsp?id="+id;
		document.form1.submit();   		
   }
 
   function _count(){
   		var f = document.getElementById("timef");
   		var e = document.getElementById("timee");
  		if(!f.value){
  			alert("请选择开始时间！");
  			return false;
  		}
  		if(!e.value){
  			alert("请选择开始时间！");
  			return false;
  		}
   		document.form1.action="<%=request.getContextPath()%>/servlet/RegulationCountServlet";
		document.form1.submit();  
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
	<BODY text="#000000" leftMargin="0" topMargin="10">
		<form name=form1 method="post">
			<jsp:include page="/include/top.jsp" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td  height='40px' bgcolor="#FFFFFF">
						&nbsp;
					</td>
					<td align='right' class="block_title">
							<span >从
								<input type="text" class="Wdate" id="timef" name="timef" size='15'
									onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'timee\',{d:-1});}'})" /> 到
								<input type="text" class="Wdate" id="timee" name="timee" size='15'
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'timef\',{d:1});}'})" />
							</span>
							<input type="button"  value="统 计" onclick='_count()'/>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF">
						&nbsp;
					</td>
					<td valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							class="table_bgcolor">
							<tr>
								<td width="25%" class="block_title" align="center">
									部门
								</td>
								<td width="25%"  class="block_title" align="center">
									总共
								</td>
								<td width="25%" class="block_title" align="center">
									有效
								</td>
								<td width="25%" class="block_title" align="center">
									无效
								</td>
							</tr>
							<%
								for (int i = 0; i < Rlist.size(); i++) {
										RegulationCountVO vo = (RegulationCountVO) Rlist.get(i);
							%>
							<tr>
								<td height="26" bgcolor="#FFFFFF" align="center" class="blue3-12">
									<%=vo.getOrgname() %>
								</td>
								<td bgcolor="#FFFFFF" class="blue3-12" align="center">
									<%=vo.getTotal()%>条
								</td>
								<td bgcolor="#FFFFFF" class="blue3-12" align="center">
									<%=vo.getTotal().intValue()-vo.getUnvalid().intValue() %>条
								</td>
								<td bgcolor="#FFFFFF" class="blue3-12" align="center">
									<%=vo.getUnvalid() %>条
								</td>
							</tr>
							<%
								}
							%>

						</table>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width="11" bgcolor="#FFFFFF">
						<img src="<%=request.getContextPath()%>/images/kongbai.jpg"
							width="11" height="11" />
					</td>
					<td valign="top">
						<div align="left"></div>
					</td>
					<td width="11">
						<img src="<%=request.getContextPath()%>/images/kongbai.jpg"
							width="11" height="11" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>


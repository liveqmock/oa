<%@ page contentType="text/html; charset=GBK"%>

<%@ page import="java.util.*"%>
<%@ page import="com.icss.regulation.vo.RegulationVO"%>
<%@ page import="com.icss.regulation.handler.RegulationHandler" %>
<%@ page import="com.icss.regulation.vo.RegulationOrgVO"%>


<%
	List Rlist = (List) request.getAttribute("Rlist") == null ? new ArrayList()
			: (List) request.getAttribute("Rlist");
			
	boolean noAdmin = request.getAttribute("noAdmin")==null?false:((Boolean)request.getAttribute("noAdmin")).booleanValue();
	String orguuid =(String) request.getAttribute("orguuid")==null?"no":(String)request.getAttribute("orguuid");
	
	List orglist = (List) request.getAttribute("orglist") == null ? new ArrayList():(List) request.getAttribute("orglist");
	
	HashMap orgMap =RegulationHandler.getOrgMap(); 

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
   function search(){
   		document.form1.action="<%=request.getContextPath()%>/servlet/AllRegulationServlet?SearchFlag=1";
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
			<input type=hidden name='orguuid' id='orguuid' value=<%=orguuid%> />
		
			<jsp:include page="/include/top.jsp" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<td  height='40px' bgcolor="#FFFFFF">
						&nbsp;
					</td>
					<td align='right' >
						<input type="button" value="新 建" onclick='_new()'> 
							&nbsp;&nbsp;
						<input type="button" value="删 除" onclick='_del()'> 
							&nbsp;&nbsp;
						<%if(!noAdmin){ %>
							<input type="button" onclick="window.location.href='<%=request.getContextPath()%>/servlet/RegulationOrgListServlet'" value="组织管理"/>
							&nbsp;&nbsp;
							<span >从
								<input type="text" class="Wdate" id="timef" name="timef" size='15'
									onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'timee\',{d:-1});}'})" /> 到
								<input type="text" class="Wdate" id="timee" name="timee" size='15'
									onFocus="WdatePicker({minDate:'#F{$dp.$D(\'timef\',{d:1});}'})" />
							</span>
							<input type="button"  value="统 计" onclick='_count()'/>
						<%} %>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td  height='40px'>
						&nbsp;
					</td>
					<td valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
					<tr class="text-align:left;bgcolor:#FFFFFF">
						<td>关键字：</td>
						<td><input id='key' name="key" size="15"/></td>
						<%if(!noAdmin){ %>
						<td>部门：</td>
						<td>
						<select name='org' id='org' >
							<option value='allorg'>全部</option>
							<%
							if (orglist != null) {
								Iterator iter = orglist.iterator();
									while (iter.hasNext()) {
										RegulationOrgVO svo = (RegulationOrgVO) iter.next();
							%>
								<option value="<%=svo.getOrguuid()%>"><%=svo.getOrgname()%></option>
							<%
							}}
							%>
						</select>
						</td>
						<%} %>
						<td>范围：</td>
						<td>					
						<select id='fanwei'  name="fanwei">
							<option value='all'>不限</option>
							<option value='title'>标题</option>
							<option value='content'>正文</option>
							<option value='rcNO'>发文号</option>
						</select>
						</td>
						<td>有效性：</td>
						<td>
						<select id='youxiao'  name="youxiao">
							<option value='all'>不限</option>
							<option value='yes'>有效</option>
							<option value='no'>作废</option>
						</select>
						</td>
						<td>
						时间段：
						</td>
						<td>
						从
						<input type="text" class="Wdate" id="ftime" name="ftime" size='13'
							onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'etime\',{d:-1});}'})" /> 到
						<input type="text" class="Wdate" id="etime" name="etime" size='13'
								onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ftime\',{d:1});}'})" />
						</td>
						<td>
						<input type="button"  value="查询" onclick='search()'/>
						</td>
					</tr>
					
						</table>
							
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
							<td width="3%" height="24" class="block_title" nowrap align="center">
								<input type="checkbox" name="checkall" id="checkall"/>全选
							</td>
								<td width="5%" height="24" class="block_title">
									<div align="center">
										序号
									</div>
								</td>
								<td width="45%" class="block_title" align="center">
									标题
								</td>
								<td width="20%" class="block_title" align="center">
									日期
								</td>
								<td width="15%" class="block_title" align="center">
									部门
								</td>
								<td width="10%" class="block_title" align="center">
									详细
								</td>
							</tr>
							<%
								for (int i = 0; i < Rlist.size(); i++) {
										RegulationVO vo = (RegulationVO) Rlist.get(i);
										String title = "";
										String timestr = "";
										if (vo.getCreateTime() != null) {
											timestr = vo.getCreateTime().toString().substring(0, 10);
										}
									
										title = vo.getTitle();
										String org = (String)orgMap.get(vo.getOrg());
										
							%>
							<tr>
							<td height="26" bgcolor="#FFFFFF">
							<input type="checkbox" id="rid" name="rid" value=<%=vo.getId()%>>
							</td>
								<td height="26" bgcolor="#FFFFFF">
									<div align="center" class="blue3-12"><%=i + 1%></div>
								</td>
								<td bgcolor="#FFFFFF" class="blue3-12">
									<a href="#"
										onclick="javascrpit:_edit('<%=vo.getId()%>')"
										style="text-decoration: none"><%=title%></a>
								</td>
								<td bgcolor="#FFFFFF" class="blue3-12" align="center">
									<%=timestr%>
								</td>
								<td bgcolor="#FFFFFF" class="blue3-12" align="center">
									<%=org%>
								</td>
								<td bgcolor="#FFFFFF" class="blue3-12" align="center">
									<a href="#"
										onclick="javascrpit:_edit('<%=vo.getId()%>')"
										style="text-decoration: none">编辑</a>
								</td>
							</tr>
							<%
								}
							%>

						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							bgcolor="#FFFFFF">
							<tr>
								<td height="30" colspan="6">
									&nbsp;<%@ include file="/include/defaultPageScrollBar.jsp"%>&nbsp;
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
	<script type="text/javascript">
	document.getElementById("checkall").onclick = function(){
		var objs = document.getElementsByName("rid");
			for(var i=0;i<objs.length;i++){
				objs[i].checked = this.checked;	
			}
	}

	function _check(){
		var objs = document.getElementsByName("rid");
		for(var i=0;i<objs.length;i++){
			if(objs[i].checked == true){				
				return true;
			}
		}
		alert("请选择记录!");
		return false;
	}

	function _del(){
		if(_check()){
			document.form1.action = "<%=request.getContextPath()%>/regulation/delRegulation.jsp"
			document.form1.submit();
		}

	}
	
	</script>
</html>


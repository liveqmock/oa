<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.zbs.rhduty.vo.TbRhWorkinfomainVO" %>
<%@ page import="com.icss.oa.zbs.rhduty.vo.TbRhWorkinfoVO" %>
<%@ page import="com.icss.oa.phonebook.handler.PhoneHandler" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.oa.zbs.duty.handler.StringUtility" %>
<%
List mainDutyList = (List) request.getAttribute("mainDutyList");
Map map = (Map)request.getAttribute("map");
String keyword =(String)request.getAttribute("keyword")==null?"":(String)request.getAttribute("keyword");
Connection conn = null;
try {
	conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("dutySearch.jsp");
	PhoneHandler handler = new PhoneHandler(conn);
%>
<html>
<head>
<title>日志信息检索</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/zbs/include/formverify/extendString.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/zbs/include/formverify/formVerify.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/zbs/include/formverify/runFormVerify.js"></SCRIPT>
<SCRIPT language=JavaScript src="<%=request.getContextPath()%>/zbs/include/js/common.js"></SCRIPT>
<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/zbs/include/style.css">-->
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />
<script language="javascript">
function fPopUpCalendarDlg(ctrlobj){
	showx = event.screenX - event.offsetX +4 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog("<%=request.getContextPath()%>/zbs/include/date.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
	if( retval != null ){
		ctrlobj.value = retval;
	}
  }
function _searchgo(){
	document.form1.action="<%=request.getContextPath()%>/servlet/RhDutySearchServlet";
	document.form1.submit();
}  
function _HightLight(nWord)
{
	if(nWord!=""){
var oRange = document.body.createTextRange();
while(oRange.findText(nWord))
{
oRange.pasteHTML("<span style='background-color:yellow;color:red;font-weight: bold;'>" + oRange.text + "</span>");
oRange.moveStart('character',1);
}

}
}
function _go(id){
	window.open("<%=request.getContextPath()%>/servlet/RhDutySearchViewServlet?id="+id+"&keyword=<%=keyword%>");	
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

<BODY onLoad="javascript:_HightLight('<%=keyword%>')">
<form action="" name="form1">
<input name="SearchFlag" type="hidden" value="1">
<jsp:include page= "/include/top.jsp" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="11" bgcolor="#FFFFFF"></td>
		<td valign="top">
		<table width="90%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
				<td width="37">
				<div align="right"></div>
			  </td>
				<td width="737" class="black-12">
				<table border="0" align="left" cellpadding="0" cellspacing="3">
					<tr>
						<td class="black-12">
						<div align="right" class="grap2-12">值班时间</div>
						</td>
						<td colspan="5">
						<table border="0" cellspacing="2" cellpadding="0">
							<tr>
								<td class="grap2-12">从</td>
								<td><input name="fromdate" type="text" class="biankuang-blue"
									value="" onClick="fPopUpCalendarDlg(fromdate)" size="12"
									readonly /></td>
								<td><img src="<%=request.getContextPath()%>/images/calendar_view_day.gif" width="16"
									height="16" alt="点击弹出日历" onClick="fPopUpCalendarDlg(fromdate)" /></td>
								<td class="grap2-12">&nbsp;到</td>
								<td><input name="todate" type="text" class="biankuang-blue"
									value="" onClick="fPopUpCalendarDlg(todate)" size="12" readonly /></td>
								<td><img src="<%=request.getContextPath()%>/images/calendar_view_day.gif" width="16"
									height="16" alt="点击弹出日历" onClick="fPopUpCalendarDlg(todate)" /></td>
							</tr>

							
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td class="black-12">
				<table border="0" align="left" cellpadding="0" cellspacing="3">
					<tr>
						
						<td class="grap2-12">
						<div align="right">值班领导</div>
						</td>
						<td><input name="dutyName" type="text" class="biankuang-blue"
							size="22" /></td>
						<td>
						<div align="right" class="grap2-12">&nbsp;&nbsp;内容关键字</div>
						</td>
						<td>&nbsp;&nbsp;<input name="dutykeyword" type="text"
							class="biankuang-blue" size="42" value="<%=keyword%>"/></td>
						<td><img src="<%=request.getContextPath()%>/images/search.jpg" width="59" height="19"
							hspace="10" onClick="javascript:_searchgo()" /></td>
					</tr>

					
				</table>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="4">
				<hr />
				</td>
			</tr>
	<%for(int i=0;i<mainDutyList.size();i++){
	TbRhWorkinfomainVO vo = (TbRhWorkinfomainVO) mainDutyList.get(i);
	boolean isManager = false;
	String title = "";
	Timestamp time = (Timestamp) vo.getWitDate();
	System.err.println("Timestamp=" + time);
	String timestr = "";
	if (time != null) {
		timestr = time.toString().substring(0, 10);
	}
	String leader = vo.getWitLeader();
	String secret = vo.getWitSecret();
	title = timestr + " 值班日志 " + leader + "、" + secret;
	String creator = vo.getWitCreater();

	String type = vo.getWitClass();
	String type1 = "";
	if ("1".equals(type)) {
		type1 = "白班";
	} else if ("2".equals(type)) {
		type1 = "夜班";
	}			
	List list = (List)map.get(vo.getWimId());
	TbRhWorkinfoVO infovo = new TbRhWorkinfoVO();
	if(list!=null){
		infovo = (TbRhWorkinfoVO)list.get(0);
	}
			%>			
			<tr>
				<td align="center" class="black-12" colspan="4">
				<div align="left"><b><a href="#" class="blue3-12-b" onClick="javascript:_go('<%=vo.getWimId()%>')"><%=title%></a></b></div>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="left">
				<div align="left" class="black-12">...<br>
				<%if("".equals(keyword)){%>
				<%=StringUtility.searchStringCut(infovo.getWitContent(),"¤яюй",100,"")%><%}else{
					for(int a=0;a<list.size();a++){
						infovo = (TbRhWorkinfoVO)list.get(a);
				%>
					<%= StringUtility.searchStringCut(infovo.getWitContent(),keyword,100,"red")%>
				<%}}%>
				...<br>
				</div>
				</td>
			</tr>
			<tr><td width="11"><img src="<%=request.getContextPath()%>/images/kongbai.jpg" width="11" height="11" /></td></tr>
			<%}%>
			<tr>
				<td colspan="2" class="blue3-12-b" height="30">
				<div align="center"><%@ include file= "/include/defaultPageScrollBar.jsp" %></div>
				</td>
			</tr>
		</table>
		</td>
		<td width="11"><img src="<%=request.getContextPath()%>/images/kongbai.jpg" width="11" height="11" /></td>
	</tr>
</table>
</form>
</BODY>
</HTML>
<%} catch (DBConnectionLocatorException e) {
	e.printStackTrace();

} finally {
	try {
		if (conn != null) {
			conn.close();
			ConnLog.close("dutySearch.jsp");
		}
	} catch (Exception e) {
	}
}
%>
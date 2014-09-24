<%@ page contentType="text/html; charset=GBK"  %>
<%response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfomainVO" %>
<%@ page import="com.icss.oa.phonebook.handler.PhoneHandler" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%List mainDutyList = (List) request.getAttribute("mainDutyList");
Connection conn = null;
try {
	conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("dutyManageList.jsp");
	PhoneHandler handler = new PhoneHandler(conn);
%>
<html>
<head>
<title>[工作日志列表]</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/zbs/include/style.css">-->
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/zbs/include/judge.js"></script>
<SCRIPT src="<%=request.getContextPath()%>/zbs/include/js/common.js"></SCRIPT>
<SCRIPT src="<%=request.getContextPath()%>/zbs/include/js/page.js"></SCRIPT>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/zbs/include/formVerify.js"></script>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/zbs/include/runFormVerify.js"></script>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/zbs/include/extendString.js"></script>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/zbs/include/js/calendar.js"></script>
<script language="javascript">
  
   function _dutysearch(){
   		document.form1.action="<%=request.getContextPath()%>/servlet/YjsMainDutyManageListServlet?SearchFlag=1";
		document.form1.submit();
   }
   function _search2(){
   		document.form1.action="<%=request.getContextPath()%>/servlet/YjsDutySearchServlet";
		document.form1.submit();
	}
   function _delete(){
   //alert("1");
	if (IsRadioChecked(document.form1.wimid,"请选择要删除的工作日志！")){
	   //alert("2");
		if(confirm("您确认要删除所选的工作日志?")){	
    	document.form1.action="<%=request.getContextPath()%>/servlet/YjsDutyInfoDeleteServlet";
		document.form1.submit();  	
   }}}
   function _viewDuty(id){
   		document.form1.action="<%=request.getContextPath()%>/servlet/YjsMainDutyViewServlet?wimid="+id+"&manager=true";
		document.form1.submit();   		
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
</script>
</head>
<BODY text="#000000" leftMargin="0" topMargin="10">
<form name="form1" method="post" action="">
<jsp:include page= "/include/top.jsp" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tr>
		<td bgcolor="#FFFFFF">&nbsp;</td>
		<td valign="top">
		<table border="0" align="left" cellpadding="0" cellspacing="0">

			<tr>
				<td width="37">
				<div align="right"></div>
			  </td>
				<td width="737" class="black-12">
				<table border="0" align="left" cellpadding="0" cellspacing="3">
					<tr>
						<td class="black-12">
						<div align="right" class="grap2-12">研究室值班时间(可不填)</div>
						</td>
						<td colspan="5">
						<table border="0" cellspacing="2" cellpadding="0">
							<tr>
								<td class="grap2-12">从</td>
								<td><input name="fromdate" type="text" class="biankuang-blue"
									value="" onClick="fPopUpCalendarDlg(fromdate)" size="12"
									readonly /></td>
								<td><img
									src="<%=request.getContextPath()%>/images/calendar_view_day.gif"
									width="16" height="16" alt="点击弹出日历"
									onClick="fPopUpCalendarDlg(fromdate)" /></td>
								<td class="grap2-12">&nbsp;到</td>
								<td><input name="todate" type="text" class="biankuang-blue"
									value="" onClick="fPopUpCalendarDlg(todate)" size="12" readonly /></td>
								<td><img
									src="<%=request.getContextPath()%>/images/calendar_view_day.gif"
									width="16" height="16" alt="点击弹出日历"
									onClick="fPopUpCalendarDlg(todate)" /></td>
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
						<div align="right" class="grap2-12">&nbsp;&nbsp;</div>
						</td>
						<td>&nbsp;&nbsp;</td>
						<td><img src="<%=request.getContextPath()%>/images/search.jpg"
							width="59" height="19" hspace="10" onClick="javascript:_dutysearch()" /><a
							href="#" onClick="_search2()" style="text-decoration: none"><span
							class="green-12-b">&gt;&gt;&nbsp;高级检索</span></a></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
		<td>&nbsp;</td>
	</tr>

	
	<tr>
		<td bgcolor="#FFFFFF">&nbsp;</td>
		<td valign="top">
		<table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_bgcolor">
			<tr>
				<td width="3%" height="24" class="block_title" nowrap>
				<div align="center"><input type="checkbox" name="checkbox10" onClick="changeSelect(this,'wimid');"/>全选</div>
				</td>
				<td width="4%" height="24" class="block_title">
				<div align="center">序号</div>
				</td>
				<td width="20%" class="block_title">
				<div align="center">日期</div>
				</td>
				<td width="45%" class="block_title">
				<div align="center">当日值班领导</div>
				</td>			
				<td width="15%" class="block_title">
				<div align="center">值班登记人</div>
				</td>
				<td width="10%" class="block_title">
				<div align="center">详细</div>
				</td>
			</tr>
			<%for (int i = 0; i < mainDutyList.size(); i++) {
	TbYjsWorkinfomainVO vo = (TbYjsWorkinfomainVO) mainDutyList.get(i);
	String title = "";
	Timestamp time = (Timestamp) vo.getWitDate();
	//System.err.println("Timestamp="+time);
	String timestr = "";
	if (time != null) {
		timestr = time.toString().substring(0, 10);
	}
	String leader = vo.getWitLeader();
	String secret = vo.getWitSecret();
	
	String creator = vo.getWitCreater();
	String type = vo.getWitClass();
	String type1 = "日志";
	%>
			<tr>
				<td height="26" bgcolor="#FFFFFF">
				<div align="center" class="blue3-12"><input type="checkbox" id="wimid" 
					name="wimid" value=<%=vo.getWimId()%>></div>
				</td>
				<td height="26" bgcolor="#FFFFFF">
				<div align="center" class="blue3-12"><%=i + 1%></div>
				</td>
				<td bgcolor="#FFFFFF" class="blue3-12">
				<div align="center"><%=timestr%>&nbsp; <%=type1%></div>
				</td>
				<!--<td bgcolor="#FFFFFF"><div align="center"><img src="<%=request.getContextPath()%>/images/email.gif" width="16" height="16" hspace="3" vspace="3" /></div></td>-->
				<td bgcolor="#FFFFFF" class="blue3-12"><a href="#"
					onclick="javascrpit:_viewDuty('<%=vo.getWimId()%>')"
					style="text-decoration: none">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;白班：<%=leader%><%if(leader.length()==2){ %>&nbsp;&nbsp;<%}%>&nbsp;&nbsp;&nbsp;&nbsp;夜班：<%=secret %></a></td>
				
				<td bgcolor="#FFFFFF" class="blue3-12">
				<div align="center"><%=handler.getUserName(creator)%></div>
				</td>
				<td bgcolor="#FFFFFF">
				<table border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td class="blue3-12"><a href="#"
							onclick="javascrpit:_viewDuty('<%=vo.getWimId()%>')"
							style="text-decoration: none">编辑</a></td>
						<td><!--<img src="<%=request.getContextPath()%>/images/icon_attachment.gif" width="16" height="16" hspace="5" />--></td>
					</tr>
				</table>
				</td>
			</tr>
			<%}%>
			
		</table>
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
				<td height="26" colspan="6" bgcolor="#FFFFFF"><%@ include file= "/include/defaultPageScrollBar.jsp" %>
				</td>
			</tr>
        </table>
        <table width="100%" border="0" cellpadding="3" cellspacing="0">
			<tr>
				<td align="center">
                <input type="button" value="删除所选记录" onClick="javascript:_delete()">
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td width="11" bgcolor="#FFFFFF"><img
			src="<%=request.getContextPath()%>/images/kongbai.jpg" width="11"
			height="11" /></td>
		<td valign="top">
		<div align="left"></div>
		</td>
		<td width="11"><img
			src="<%=request.getContextPath()%>/images/kongbai.jpg" width="11"
			height="11" /></td>
	</tr>
</table>
</form>
</body>
</html>
<%} catch (DBConnectionLocatorException e) {
	e.printStackTrace();

} finally {
	try {
		if (conn != null) {
			conn.close();
			ConnLog.close("dutyManageList.jsp");
		}
	} catch (Exception e) {
	}
}
%>

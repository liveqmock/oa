<%@ page contentType="text/html; charset=GBK" %>


<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.icss.oa.zbs.rhduty.vo.TbRhWorkinfomainVO" %>
<%@ page import="com.icss.oa.phonebook.handler.PhoneHandler" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>

<%

List mainDutyList = (List) request.getAttribute("mainDutyList") == null ? new ArrayList() : (List) request.getAttribute("mainDutyList");
System.err.println("mainDutyList=" + mainDutyList.size());
String userId = (String) request.getAttribute("userId") == null ? "" : (String) request.getAttribute("userId");
Connection conn = null;
try {
	conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("dutyList.jsp");
	PhoneHandler handler = new PhoneHandler(conn);
%>
<html>
<head>
<title>������־��¼</title>
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/Style/css_grey.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/zbs/include/judge.js"></script>
<SCRIPT src="<%=request.getContextPath()%>/zbs/include/js/common.js"></SCRIPT>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/zbs/include/formVerify.js"></script>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/zbs/include/runFormVerify.js"></script>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/zbs/include/extendString.js"></script>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/zbs/include/js/calendar.js"></script>
<script src="<%=request.getContextPath()%>/include/DatePicker/WdatePicker.js"></script>
<script language="javascript">
   function newDuty(){
   		document.form1.action="<%=request.getContextPath()%>/servlet/RhMainDutyAddServlet";
		document.form1.submit();
   }
   function _dutysearch(){
   		document.form1.action="<%=request.getContextPath()%>/servlet/RhMainDutyListServlet?SearchFlag=1";
		document.form1.submit();	
   }
   function _viewDuty(id){
   		document.form1.action="<%=request.getContextPath()%>/servlet/RhMainDutyViewServlet?wimid="+id;
		document.form1.submit();   		
   }
   function _search2(){
   		document.form1.action="<%=request.getContextPath()%>/servlet/RhDutySearchServlet";
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
<jsp:include page= "/include/top.jsp" />
<table width="983" style="margin:0 auto;" align="center" border="0" cellspacing="0" cellpadding="0">

	<tr>
		
		<td valign="top">
		<table border="0" align="left" cellpadding="0" cellspacing="0">

			<tr>
				<td width="37">
				<div align="right"></div>
			 	</td>
				<td width="750" class="black-12">
					<table border="0" align="left" cellpadding="0" cellspacing="3">
						<tr>
							<td class="black-12">
							<div align="right" class="grap2-12">ֵ��ʱ��(�ɲ���)</div>
							</td>
							<td colspan="5">
								<table border="0" cellspacing="2" cellpadding="0">
									<tr>
										<td class="grap2-12">��</td>
										<td><input name="fromdate" type="text" class="biankuang-blue Wdate"  onFocus="WdatePicker()"
											value="" size="12"
											readonly /></td>
										<td class="grap2-12">&nbsp;��</td>
										<td><input name="todate" type="text" class="biankuang-blue Wdate"  onFocus="WdatePicker()"
											value="" size="12" readonly /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td class="black-12">
					<table border="0" align="left" cellpadding="0" cellspacing="3">
						<tr>
							
							<td class="grap2-12">
							<div align="right">ֵ���쵼</div>
							</td>
							<td><input name="dutyName" type="text" class="biankuang-blue"
								size="22" /></td>
							<td>
							<div align="right" class="grap2-12">&nbsp;&nbsp;</div>
							</td>
							<td>&nbsp;<img
								src="<%=request.getContextPath()%>/images/search.jpg" width="59"
								height="19" hspace="10" onClick="javascript:_dutysearch()" />&nbsp;</td>
							<td><a href="#" onClick="_search2()" style="text-decoration: none"><span
								class="green-12-b">&gt;&gt;&nbsp;�߼�����</span></a></td>
						</tr>
					</table>
				</td>
				<td align="center">
						<img src='<%=request.getContextPath()%>/images/newduty.gif' alt="�½�ֵ���¼" onClick="javascrpit:newDuty()" style="cursor:hand"/>
				</td>
			</tr>
		</table>
		</td>
		
	</tr>
	<tr>
		
		<td valign="top">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table_bgcolor">
			<tr>
				<td width="5%" height="24" class="block_title"><div align="center">���</div>
				</td>
				<td width="20%" class="block_title">
				<div align="center">����</div>
				</td>
				<td width="45%" class="block_title">
				<div align="center">����ֵ���쵼</div>
				</td>				
				<td width="15%" class="block_title">
				<div align="center">ֵ��Ǽ���</div>
				</td>
				<td width="10%" class="block_title">
				<div align="center">��ϸ</div>
				</td>
			</tr>
			<%for (int i = 0; i < mainDutyList.size(); i++) {
	TbRhWorkinfomainVO vo = (TbRhWorkinfomainVO) mainDutyList.get(i);
	boolean isManager = false;
	
	Timestamp time = (Timestamp) vo.getWitDate();
	System.err.println("Timestamp=" + time);
	String timestr = "";
	if (time != null) {
		timestr = time.toString().substring(0, 10);
	}
	String leader = vo.getWitLeader();
	
	String secret = vo.getWitSecret();
	
	String creator = vo.getWitCreater();
	if(creator.equals(userId)){
		isManager =true;
	}
	String type = vo.getWitClass();
	String type1 = "��־";
	%>
			<tr>
				<!-- ��� -->
				<td height="26" bgcolor="#FFFFFF">
				<div align="center" class="blue3-12"><%=i + 1%></div>
				</td>
				<!--<td bgcolor="#FFFFFF"><div align="center"><img src="<%=request.getContextPath()%>/images/email.gif" width="16" height="16" hspace="3" vspace="3" /></div></td>-->
				
				<!-- ���� -->
				<td bgcolor="#FFFFFF" class="blue3-12">
				<div align="center"><%=timestr%>&nbsp; <%=type1%></div>
				</td>
								
				<!-- ����ֵ���쵼 -->
				<td bgcolor="#FFFFFF" class="blue3-12"><a href="#"
					onclick="javascrpit:_viewDuty('<%=vo.getWimId()%>')"  style="text-decoration: none">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�װࣺ<%=leader%><%if(leader.length()==2){ %>&nbsp;&nbsp;<%}%>&nbsp;&nbsp;&nbsp;&nbsp;ҹ�ࣺ<%=secret %></a>
				</td>
											
				<!-- ֵ��Ǽ��� -->
				<td bgcolor="#FFFFFF" class="blue3-12">
				<div align="center"><%=handler.getUserName(creator)%></div>
				</td>
				
				<td bgcolor="#FFFFFF">
				<table border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td class="blue3-12"><a href="#"
							onclick="javascrpit:_viewDuty('<%=vo.getWimId()%>')"
							style="text-decoration: none"><%if(isManager){%>�༭<%}else{%>�鿴<%}%></a></td>
						<td><!--<img src="<%=request.getContextPath()%>/images/icon_attachment.gif" width="16" height="16" hspace="5" />--></td>
					</tr>
				</table>
				</td>
			</tr>
			<%}%>
			
		</table>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#FFFFFF">
        	<tr>
				<td height="30" colspan="6">&nbsp;<%@ include file= "/include/defaultPageScrollBar.jsp" %>&nbsp;
				</td>
			</tr>
        </table>
   
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		
		<td valign="top">
		<div align="left"></div>
		</td>
		
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
			ConnLog.close("dutyList.jsp");
		}
	} catch (Exception e) {
	}
}
%>
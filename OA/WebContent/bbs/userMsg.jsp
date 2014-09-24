<%@ page contentType="text/html; charset=gb2312" %>


<%response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.bbs.vo.*" %>
<%@ page import="com.icss.oa.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocator" %>
<%@ page import="com.icss.j2ee.services.DBConnectionLocatorException" %>
<%@ page import="com.icss.j2ee.util.Globals" %>
<%@ page import="com.icss.common.log.ConnLog" %>
<%@ page import="com.icss.oa.filetransfer.handler.personInfoHandler" %>
<%@ page import="com.icss.oa.bbs.handler.BBSAreaHandler" %>
<%Collection collection = (Collection) request.getAttribute("userList");
Iterator Iterator = collection.iterator();
BbsUserinfoVO usermsgVO = null;
if (Iterator.hasNext()) {
	usermsgVO = (BbsUserinfoVO) Iterator.next();
}
String currUserId = (String) request.getAttribute("currUserId");
//out.print(currUserId);
String uuid = usermsgVO.getUserid();
Connection conn = null;
try {
	conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
	ConnLog.open("userMsg.jsp");
	personInfoHandler _personInfoHandler = new personInfoHandler(conn);		
	BBSAreaHandler ahandler = new BBSAreaHandler(conn);	
	String orguuid = _personInfoHandler.getOrgUUID(uuid);
	System.err.println("orguuid="+orguuid);
	String orgname = _personInfoHandler.getOrgName(orguuid);
	System.err.println("orgname="+orgname);	
	String _orguuid = _personInfoHandler.getParentUUID(orguuid);
	System.err.println("_orguuid="+_orguuid);
	String _orgname = _personInfoHandler.getOrgName(_orguuid);
	System.err.println("_orgname="+_orgname);
	
	String tel = ahandler.getPersonTel(uuid);
	if(tel==null||tel==""){
		tel = "���޵绰����";
	}
	String job = ahandler.getPersonJob(uuid);
	if(job==null||job==""){
		job = "";
	}
	System.err.println("tel="+tel);
%>
<html>
<head>
<title>�鿴������Ϣ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2132">
<!--<link href="<%=request.getContextPath()%>/include/bbscss.css" rel="stylesheet" type="text/css">-->
<link href="<%=request.getContextPath()%>/Style/css.css" rel="stylesheet" type="text/css" />

</head>
<body bgcolor="#ffffff">
<table width="100%" height="523" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td valign="top">
		<div align="center"><br>
		</div>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="100%" background="../images/2-title-05.jpg"
					bgcolor="#E0EDF8" colspan=3 height="23">
				<div align="center" class="white2-12-b">�û� <%=usermsgVO.getTruename()%>
				������</div>
				</td>
			</tr>
		</table>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="1"></td>
				<td width="100%">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
						<table width="100%" border="0" cellspacing="2" cellpadding="1"
							bgcolor="#B9DAF9">
							<tr>
								<td height="22" colspan="2" bgcolor="FBFBEE">
								<div align="center" class="blue2-12">��������</div>
								</td>
							</tr>
							<tr>
								<td height="22" bgcolor="FFFFFF"><span class="blue2-12">&nbsp;<B>�û����ƣ�</B><%=usermsgVO.getTruename()%></span></td>

								<td class="blue2-12" bgcolor="FFFFFF">&nbsp;<B>�ܹ�������</B>&nbsp;<%=usermsgVO.getPubnum()%></td>
							</tr>
							<tr>
								<td height="22" bgcolor="FFFFFF" class="blue2-12">&nbsp;<B>�û��绰��</B><%=tel%></td>
								
								<td class="blue2-12" bgcolor="FFFFFF">&nbsp;<B>����½��</B> <%if (usermsgVO.getLasttime() != null) {%>
								<%=CommUtil.getTime(usermsgVO.getLasttime().longValue())%> <%}%>
								</td>
							</tr>
							<tr>
								<td height="22" class="blue2-12" bgcolor="FFFFFF">&nbsp;<B>�û����ڣ�</B><%=_orgname%>-<%=orgname%>&nbsp;
								
								<td width="50%" class="blue2-12" bgcolor="FFFFFF">&nbsp;<B>��¼�ɣУ�</B> <%if (usermsgVO.getLastip() != null) {%>
								<%=usermsgVO.getLastip()%> <%}%></td>
							</tr>
							<tr>
								<td height="22" colspan="2" bgcolor="FBFBEE">
								<div align="center"><span class="blue2-12">��ϵ����</span></div>
								</td>
							</tr>
							<tr>
								<td class="blue2-12" bgcolor="FFFFFF"><B>�û�ͷ��</B><BR>
								<IMG height=100
									src="<%=request.getContextPath()%>/images/bbs/face/<%=usermsgVO.getUserpic()%>"
									width=83></td>
								
								<td class="blue2-12" bgcolor="FFFFFF"><B>�û�ǩ��: <%if (usermsgVO.getUnderwrite() != null) {%>
								<%=usermsgVO.getUnderwrite()%> <%}%> </B></td>
							</tr>							
							<%if (usermsgVO.getUserid().equals(currUserId)) {%>
							<tr>
								<td bgcolor="#FFFFFF" colspan=3 align="left" class="blue2-12">
								<div align="center" class="blue2-12"><A
									href="<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=usermsgVO.getUserid()%>&editFlag=1&currUserId=<%=currUserId%>"
									class="blue2-12">�޸��û���Ϣ</a> 
								</td>
							</tr>
							<%}%>
						</table>
						</td>
					</tr>
				</table>
				</td>

			</tr>
		</table>


		<!--<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-21.jpg" width="10" height="21"></td>
          <td background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg" class="text-01"> 
            <%if (usermsgVO.getUserid().equals(currUserId)) {%>
            <div align="center"><A href="<%=request.getContextPath()%>/servlet/ShowUserMsgServlet?userId=<%=usermsgVO.getUserid()%>&editFlag=1&currUserId=<%=currUserId%>" class="blue2-12">�޸��û���Ϣ</a> 
              <%}%>
            </div></td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bbs/newbbs/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bbs/newbbs/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
      </table>      --></td>
	</tr>
</table>
</body>
</html>
<%} catch (DBConnectionLocatorException e) {
	e.printStackTrace();

} finally {
	try {
		if (conn != null) {
			conn.close();
			ConnLog.close("userMsg.jsp");
		}
	} catch (Exception e) {
	}
}
%>
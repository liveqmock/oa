<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/j2ee-html.tld" prefix="html"%>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.sms.vo.SMSHistoryVO"%>
<%
	List historylist = (List) request.getAttribute("historylist");
	SMSHistoryVO historyVO =(SMSHistoryVO)historylist.get(0);
%>
<html>
<head>
<title>��ʷ��ϸ��Ϣ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/style.css">
</head>

<body>
<form name=form1 action="<%=request.getContextPath()%>/servlet/LogDBDetailServlet" method=post>

<center>
<p>&nbsp;</p><p>&nbsp;</p>
		  <table width="71%" border="1" cellpadding="1" cellspacing="0"  bgcolor="#EEF4FF">
            <tr bgcolor="#A6D0F2">
          <td colspan="2">
            <div align="center">��ʷ��ϸ��Ϣ</div>
          </td>
        </tr>
				<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td width="22%">��¼ID:</td>
            <td width="78%"><%=historyVO.getId() %></td>
          </tr>
				
				<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td>������:</td>
            <td><%=historyVO.getSendername() %></td>
          </tr>
          		<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td>ʱ��:</td>
            <td><%=historyVO.getTime() %></td>
          </tr>
				<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td>������:</td>
            <td><%=historyVO.getReceivername()%></td>
          </tr>
				<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td>��������:</td>
            <td><%=historyVO.getContent() %></td>
          </tr>
				
				<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
          <td colspan="2">
            <div align="center">
              <img src="<%=request.getContextPath()%>/images/bt_back.gif" border="0" style="cursor:hand" onClick=window.close()>
            </div>
          </td>
        </tr>
      </table>
      <div align="center"> </div>
</center>
</form>
</body>
</html>

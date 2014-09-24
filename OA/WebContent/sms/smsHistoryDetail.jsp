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
<title>历史详细信息</title>
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
            <div align="center">历史详细信息</div>
          </td>
        </tr>
				<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td width="22%">记录ID:</td>
            <td width="78%"><%=historyVO.getId() %></td>
          </tr>
				
				<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td>发送人:</td>
            <td><%=historyVO.getSendername() %></td>
          </tr>
          		<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td>时间:</td>
            <td><%=historyVO.getTime() %></td>
          </tr>
				<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td>接收人:</td>
            <td><%=historyVO.getReceivername()%></td>
          </tr>
				<tr bgcolor="EEF4FF" onmouseover="this.bgColor='#EBEBEB';" onmouseout="this.bgColor='#EEF4FF';">
            <td>短信内容:</td>
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

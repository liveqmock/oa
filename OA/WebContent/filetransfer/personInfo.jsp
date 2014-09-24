<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.Person"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);

String sendresult = (String)request.getAttribute("sendresult");
String title1 = (String)request.getAttribute("title1");
String title2 = (String)request.getAttribute("title2");
Person  person = (Person)request.getAttribute("person");
%>

<HTML><HEAD><TITLE></TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<script>

</SCRIPT>

</head>
<BODY text=#000000 leftMargin=0 background=<%=request.getContextPath()%>/images/grid.gif topMargin=10>
<form name="sendForm" method="post" action="">
<div align="center" >
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">查看人员详细信息</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>
          <%
            String sex ="未知";
            if (person.getSex().equals(new Integer(0)))
				sex = "保密";
			if (person.getSex().equals(new Integer(1)))
				sex = "男";
			if (person.getSex().equals(new Integer(2)))
				sex = "女";
			if (person.getSex().equals(new Integer(3)))
				sex = "未知";

%>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
                    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                    
                       <tr  bgcolor="#FFFBEF" height="20">
                          <td width="50%" ><div align="center" class="title-04">姓名：</div></td>
                          <td width="2"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                          <td width="50%" ><div align="center" class="title-04"><%= person.getFullName()%></div></td>

                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr  bgcolor="#FFFBEF" height="20">
                          <td width="50%" ><div align="center" class="title-04">所属组织：</div></td>
                          <td width="50%" ><div align="center" class="title-04"><%= request.getAttribute("dep")%></div></td>

                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr  bgcolor="#FFFBEF" height="20">
                          <td width="50%" ><div align="center" class="title-04">职务：</div></td>
                          <td width="50%" ><div align="center" class="title-04"><%= person.getJob()==null?"暂缺":person.getJob()%></div></td>
                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr  bgcolor="#FFFBEF" height="20">
                         <td width="50%" ><div align="center" class="title-04">办公电话：</div></td>
                          <td width="50%" ><div align="center" class="title-04"><%= person.getOfficetel()==null?"无":person.getOfficetel()%></div></td>

                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                        <tr  bgcolor="#FFFBEF" height="20">
                         <td width="50%" ><div align="center" class="title-04">性别：</div></td>
                          <td width="50%" ><div align="center" class="title-04"><%= sex%></div></td>
                        </tr>
                        <tr>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                          <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        </tr>
                    </table></td>
                  </tr>
              </table></td>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
            </tr>
          </table>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="48%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
              <td width="49%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><div align="right"></div></td>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr>
          </table>
          

</div>
</form>
</body>

</html>
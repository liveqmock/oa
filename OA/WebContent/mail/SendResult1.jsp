<%@ page contentType="text/html; charset=GBK" %>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);

String failResult = (String)request.getAttribute("failResult");
request.getSession().setAttribute("failResult",failResult);
String sendResult = (String)request.getAttribute("sendResult");
request.getSession().setAttribute("sendResult",sendResult);

String overFlowResult = (String)request.getAttribute("overFlowResult");
request.getSession().setAttribute("overFlowResult",overFlowResult);

String noPersonResult = (String)request.getAttribute("noPersonResult");
request.getSession().setAttribute("noPersonResult",noPersonResult);

String title1 = (String)request.getAttribute("title1");
request.getSession().setAttribute("title1",title1);
String title2 = (String)request.getAttribute("title2");
request.getSession().setAttribute("title2",title2);
String coreStr = (String)request.getAttribute("coreStr");
request.getSession().setAttribute("coreStr",coreStr);

%>
<SCRIPT>
 function _onload(){
window.location.href = "<%=request.getContextPath()%>/mail/SendResult.jsp";
 }  	 

</SCRIPT>
<HTML><HEAD><TITLE><%=title1%></TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<LINK href="<%=request.getContextPath()%>/Style/css_grey.css" rel=stylesheet>


</head>
<BODY leftmargin="10" onload="_onload()" >

</body>

</html>
<%@ page language="java" pageEncoding="GB2312" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>部门列表</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link rel="stylesheet" type="text/css" href="tree.css">
</head>

<BODY text=#eeeeee bgColor=#d8eaf8 leftMargin=0 topMargin=0 marginheight="0" 
marginwidth="0">

 <table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/01.gif"  height="1"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif" height="8"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/title-03.gif"  height="28"></td>
  </tr>
  <tr>
    <td background="<%=request.getContextPath()%>/images/left_img/07.gif">
    <table width="156"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="6" colspan="2"></td>
      </tr>
      <tr>
        <td width=20></td><td colspan="2" class="left" >

<%
String sessionname = request.getParameter("sessionname");
System.out.println("leftjsp_sessionname="+sessionname);
String doFunction = request.getParameter("doFunction");
/**在注册或者调用时添加nodeUrl参数*/
//String nodeUrl = request.getContextPath() + request.getParameter("nodeUrl");
String nodeUrl = request.getContextPath() + "/servlet/SelectPersonServlet?doFunction="+doFunction+"&sessionname="+sessionname;

String xmlPath = "/resourceone/tempxml/0.xml"; //xml文件路径
Integer displayFlag = new Integer("0"); 

%>



		       <% 
			       if(!xmlPath.equals("0")) {
			          if(displayFlag.intValue()==0){ 
			 %>
                          <div id="deeptree" class="deeptree" CfgXMLSrc="<%=xmlPath%>" NodeUrl="<%=nodeUrl%>" OrgUuid="<%//=displayOrgIds%>"></div>
                     <%} else {%>
                         <div id="deeptree" class="deeptree" CfgXMLSrc="<%=xmlPath%>" NodeUrl="<%=nodeUrl%>" OrgUuid=""></div>
                   <%}
			   } %>

         </td>
        </tr>
		
    </table>
	</td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/08.gif"  height="7"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif"  height="8"></td>
  </tr>
  
  <tr>
    <td align="center"><a href="<%=request.getContextPath()%>/servlet/SelectCommonGroupServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>" target="mainFrame">
<img src="<%=request.getContextPath()%>/images/selectfromcommon.gif" border=0 style="cursor:hand" ></a></td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center"><a href="<%=request.getContextPath()%>/servlet/SelectIndiGroupServlet?doFunction=<%=doFunction%>&sessionname=<%=sessionname%>" target="mainFrame">
    <img src="<%=request.getContextPath()%>/images/selectfromindi.gif" border=0 style="cursor:hand" ></a></td>
  </tr>
 
 
 
</table>

</body>
</html>
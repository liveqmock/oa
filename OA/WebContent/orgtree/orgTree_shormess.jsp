<%@ page language="java" pageEncoding="GB2312" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>部门列表</title>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/orgtree/tree.css">
</head>
<body text=#eeeeee bgColor=#d8eaf8 leftMargin=10 topMargin=0 marginheight="0" 
marginwidth="0">

<BODY text=#eeeeee bgColor=#d8eaf8 leftMargin=10 topMargin=0 marginheight="0" 
marginwidth="0">

 <table width="178" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/01.gif" width="178" height="1"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif" width="178" height="8"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/title-03.gif" width="178" height="28"></td>
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
/**在注册或者调用时添加nodeUrl参数*/
String nodeUrl = request.getContextPath() + request.getParameter("nodeUrl");

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
    <td><img src="<%=request.getContextPath()%>/images/left_img/08.gif" width="178" height="7"></td>
  </tr>
  <tr>
    <td><img src="<%=request.getContextPath()%>/images/left_img/05.gif" width="178" height="8"></td>
  </tr>
  
  
 
</table>

</body>
</html>
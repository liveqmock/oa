<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*" %>
<%@ page import="com.icss.resourceone.common.login.LoginHelper" %>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo" %>

<%
List userList = LoginHelper.getOnlineUser();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>谁与我同在</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resourceone/include/style.css">
 <script language="javascript">
 var _IsMousedown = 0;
   var _ClickLeft = 0;
   var _ClickTop = 0;
   function moveInit(divID,evt)
   {
    _IsMousedown = 1;
    if(getBrowserType() == "NSupport")
    {
     return;
    }
    var obj = getObjById(divID);
    if(getBrowserType() == "fox")
    {
     _ClickLeft = evt.pageX - parseInt(obj.style.left);
     _ClickTop = evt.pageY - parseInt(obj.style.top);
    }else{
     _ClickLeft = evt.x - parseInt(obj.style.left);
     _ClickTop = evt.y - parseInt(obj.style.top);
    }
   }
    function changeDiv(obj,url)
   {
		document.getElementById(obj).style.display="";
		document.getElementById(obj).innerHTML = "<iframe src='"+url+"'   width=100%   height=100% marginheight='0px' marginwidth='0px' frameborder='0'></iframe><input type=\"button\" onclick=\"this.parentElement.style.display='none'\" value=\"关闭\" />";
   }	
   function Move(divID,evt)
   {
    if(_IsMousedown == 0)
    {
     return;
    }
    var objDiv = getObjById(divID);
    if(getBrowserType() == "fox")
    {
     objDiv.style.left = evt.pageX - _ClickLeft;
     objDiv.style.top = evt.pageY - _ClickTop;
    }
    else{
     objDiv.style.left = evt.x - _ClickLeft;
     objDiv.style.top = evt.y - _ClickTop;
    }
    
   }
   function stopMove()
   {
    _IsMousedown = 0;
   }
   function getObjById(id)
   {
    return document.getElementById(id);
   }
   function getBrowserType()
   {
    var browser=navigator.appName
    var b_version=navigator.appVersion
    var version=parseFloat(b_version)
    //alert(browser);
    if ((browser=="Netscape"))
    {
     return "fox";
    }
    else if(browser=="Microsoft Internet Explorer")
    {
     if(version>=4)
     {
      return "ie4+";
     }
     else
     {
      return "ie4-";
     }
    }
    else
    {
     return "NSupport";
    }
   }
</script>
	</head>
	
	<body>
		<br>
		<table width="172" height="47" border="0" align="center" cellpadding="2" cellspacing="0" >
			<tr>
				<td align="center">谁与我同在(<font color=blue><%=userList.size()%></font>人在线)</td>
			</tr>
            <tr>
				<td align="center"><div>&nbsp;</div></td>
			</tr>
			
			<%
			   java.util.Iterator iuserid = userList.iterator();
			   
			   while(iuserid.hasNext()){
               UserInfo user = (UserInfo)iuserid.next();
               
			%>
			<tr>
				<td height="21" align="left">&nbsp;<font color=blue><span onclick="changeDiv('movediv','/oabase/servlet/UserInfoServlet?personuuid=<%=user.getPersonUuid()%>')" style="cursor:hand;"><%=user.getUserID()%>(<%=user.getCnName()%>)</span></font></td>
			</tr>
			<%
			}
			%>
		</table>
        <div id="movediv"  style="left:150px;top:20px;display:none" onmousedown="moveInit('movediv',event);" onmousemove="Move('movediv',event)" onmouseup="stopMove()" onmouseout="stopMove()"><ifream></div>
		<div align="center"><img src="../images/close.gif" width="69" height="25" style="cursor:hand" onClick='window.close()'>
		</div>
	</body>
</html>
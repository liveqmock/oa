<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.orgtree.vo.*"%>
<%@ page import="com.icss.orgtree.handler.*"%>


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.anylinkcss{ 
position:absolute; 
visibility: hidden; 
z-index: 100; 
} 
-->
</style>
<link href="../Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />
<script language="javascript">
function change_view(obj_name)
{
var aa=document.getElementById(obj_name);
if(aa.style.display=="")
  {
    aa.style.display="none";
    ch.innerHTML="<a href=# onClick=change_view('menu')>显示组织结构树</a>";
  }
  else
  {
    ch.innerHTML="";
    aa.style.display="";
   }
}
function setmenu(url)
{
    window.top.frames["menufrm"].location.href=url;
}
</script>
<html>
<head>
<title>电话簿查询</title>

<body>
<form name="frm" target="_blank">
<!--头-->
<%@ include file= "/include/top.jsp" %>
<!--头-->

        <table width="1003" height="100%" border="0" align="center">
           <tr height=400>
             <td width="200" id=menu valign="top" align="right"><iframe name="menufrm" src="/oabase/servlet/MyOrgTreeServlet" width="100%" height="480" scrolling="auto" frameborder="0"></iframe></td>
    
            <td width="800" id=main align=left>
	            <iframe name="mainFrame" id="mainFrame" scrolling="auto" src="/oabase/servlet/MySearchPhoneServlet" width="100%" height="100%" frameborder="0"></iframe>             </td>
           </tr>
        </table>
</form>

</body>
</html>







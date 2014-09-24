<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.store.vo.CatogaryVO"%>
<%@ page import="com.icss.store.vo.DeviceTypeVO"%>
<%@ page import="java.util.Map" %>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%
List listDeviceType = (List)request.getAttribute("listDeviceType");
Map mapDevicepic = (Map)request.getAttribute("mapDevicepic");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.TOPSTYLE {
	color: #FFFFFF;
	font-size: 12px;
}
-->
</style>
<link href="../style/css.css" id="homepagestyle" rel="stylesheet" type="text/css" />

<title>应急报道设备管理系统</title>
<style type="text/css">
<!--
.STYLE2 {
	font-size: 16px;
	font-weight: bold;
	color: #FF0000;
}
-->
</style>


</head>

<body>
<form name="frm" method="post">

<table border="0" cellpadding="0" cellspacing="0" width="570" align="center">
    	<tr>
       	  <td height="140">
            <table border="0" cellpadding="0" cellspacing="1" class="table_bgcolor" width="100%">
            <%
			if(listDeviceType!=null){
				for(int i=0;i<listDeviceType.size();i++){
					DeviceTypeVO tvo = (DeviceTypeVO)listDeviceType.get(i);
			%>
            	<tr>
                	<td width="37%" height="120" align="center">
                    	<img src="<%=request.getContextPath()%>/store/pic/<%=tvo.getPicname()%>" width="120" height="100" />
                    </td>
                    <td width="63%" align="left" class="black-13">
                    	<%=tvo.getDevicetype()%><br />
                        设备参数：
                        在库
                    </td>
            	</tr>
            <%
				}
			}
			%>
            </table>          
          </td>
        </tr>
</table> 

</form>
</body>
</html>

<script language="javascript">
function _changecontent(id){
	for(i=1;i<5;i++){
		document.getElementById("content"+i).style.display="none";
		document.getElementById("titlecontent"+i).className="headtitle_orange";
	}
	document.getElementById("content"+id).style.display="block";
	document.getElementById("titlecontent"+id).className="headtitle_red";
}

function _changecatory(count,id){
	for(i=1;i<=count;i++){
		document.getElementById("catory"+i).style.display="none";
		document.getElementById("catorytitle"+i).className="catorytitle_green";
	}
	document.getElementById("catory"+id).style.display="block";
	document.getElementById("catorytitle"+id).className="catorytitle_blue";
}

function _cancel(count){
	for(i=1;i<=count;i++){
		document.getElementById("catory"+i).style.display="none";
		document.getElementById("catorytitle"+i).className="catorytitle_green";
	}
}
</script>
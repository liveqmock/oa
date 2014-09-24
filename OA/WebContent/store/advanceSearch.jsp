<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.store.vo.CatogaryVO"%>
<%@ page import="com.icss.store.vo.DeviceTypeVO"%>
<%@ page import="com.icss.store.vo.BorrowOutVO"%>
<%@ page import="com.icss.store.vo.DeviceWhereVO"%>
<%@ page import="com.icss.store.vo.BorrowPersonVO"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%
List listCatogary = (List)request.getAttribute("listCatogary");
List listDeviceWhere = (List)request.getAttribute("listDeviceWhere");
List listBorrowPerson = (List)request.getAttribute("listBorrowPerson");
//Map mapDeviceType = (Map)request.getAttribute("mapDeviceType");
//Map mapDeviceIn = (Map)request.getAttribute("mapDeviceIn");
//Map mapDeviceTypeIn = (Map)request.getAttribute("mapDeviceTypeIn");
//Map mapDeviceTypeWhere = (Map)request.getAttribute("mapDeviceTypeWhere");
//List listBorrowOut = (List)request.getAttribute("listBorrowOut");
//List listAlertForIn = new ArrayList();
//List listAlertForDelay = new ArrayList();

int devicetypeincount = 0;
int devicetypeoutcount = 0;
int intdevicetypecount = 0;
String inwhere = "";
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
#movediv{
    width:600px;height:250px;position:absolute;border:1px solid #000;background:#EAEAEA;
    cursor:pointer;
    text-align:center;
    line-height:100px;
    left:100px;
    top:10px;
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

<script language="javascript">
function _clearinfo(){
	if(frm.keyword.value=="请输入设备关键字"){
		frm.keyword.value="";
	}
}
</script>
</head>

<body>
<form name="frm" method="post">
<table width="540" height="29" border="0" cellpadding="0" align="center" cellspacing="0">
	<tr><td height="5" colspan="2"></td></tr>
	
    <tr><td width="463" height="30" align="center" class="table_bgcolor">分类检索</td>
      <td width="77" align="right" class="table_bgcolor"><span class="black-12" align="right"><a href="<%=request.getContextPath()%>/servlet/StoreSearchServlet" class="black-12">条件检索</a></span></td>
    </tr>
    <tr>
    	<td colspan="2">
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
        	<tr>
           	  	<td width="33%" height="30" align="center" class="blue11-14-b">设备类别</td>
                <td width="33%" align="center" class="blue11-14-b">放置位置</td>
                <td width="33%" align="center" class="blue11-14-b">借出人</td>
            </tr>
            <tr>
            	<td height="30" align="center" class="blue11-12" valign="top">
                <%
				if(listCatogary!=null){
					for(int i=0;i<listCatogary.size();i++){
						CatogaryVO cvo = (CatogaryVO)listCatogary.get(i);
				%>
                        <span class="blue11-12"><%=cvo.getClassname()%>(<%=cvo.getDevicecount()%>)</span><br>
                <%
					}
				}
				%>                </td>
                <td align="center" class="blue11-12" valign="top">
                <%
				if(listDeviceWhere!=null){
					for(int j=0;j<listDeviceWhere.size();j++){
						DeviceWhereVO wvo = (DeviceWhereVO)listDeviceWhere.get(j);
				%>
                		<span class="blue11-12"><%=wvo.getDevicewhere()%>(<%=wvo.getDevicecount()%>)</span><br>
                <%
					}
				}
				%>                </td>
                <td align="center" class="blue11-12" valign="top">
                <%
				if(listBorrowPerson!=null){
					for(int m=0;m<listBorrowPerson.size();m++){
						BorrowPersonVO pvo = (BorrowPersonVO)listBorrowPerson.get(m);
				%>
                		<span class="blue11-12"><%=pvo.getPersonname()%>(<%=pvo.getDevicecount()%>)</span></br>
                <%
					}
				}
				%>                </td>
            </tr>
        </table>        </td>
    </tr>
</table>

</form>


<script language="javascript">
function _changecontent(id){
	for(i=1;i<4;i++){
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
function changeDiv(obj,url)
   {
		document.getElementById(obj).style.display="";
		document.getElementById(obj).innerHTML = "<iframe src='"+url+"'   width=100%   height=100% marginheight='0px' marginwidth='0px' frameborder='0'></iframe><input type=\"button\" onclick=\"this.parentElement.style.display='none'\" value=\"关闭\" />";
   }	
   
function _search(){
	//alert(url);
	//window.open(url);
	frm.action="<%=request.getContextPath()%>/servlet/StoreSearchServlet";
	frm.submit();
}
</script>

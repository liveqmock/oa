<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.store.vo.CatogaryVO"%>
<%@ page import="com.icss.store.vo.DeviceTypeVO"%>
<%@ page import="com.icss.store.vo.BorrowOutVO"%>
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
//Map mapDeviceType = (Map)request.getAttribute("mapDeviceType");
//Map mapDeviceIn = (Map)request.getAttribute("mapDeviceIn");
//Map mapDeviceTypeIn = (Map)request.getAttribute("mapDeviceTypeIn");
//Map mapDeviceTypeWhere = (Map)request.getAttribute("mapDeviceTypeWhere");
List listBorrowOut = (List)request.getAttribute("listBorrowOut");
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
	<tr><td height="5"></td></tr>
	<tr>
        <td>
        <table border="0" cellpadding="0" cellspacing="0" width="99%">
          <tr>
            <td width="23%" align="right"><select name="devicestatus">
                <option value="0" selected>状态</option>
                <option value="1">库存</option>
                <option value="2">借出</option>
              </select>            </td>
            <td width="22%" align="right"><select name="devicecat">
                <option value="" selected>请选择设备分类</option>
                <%
										if(listCatogary!=null){
											for(int i=0;i<listCatogary.size();i++){
												CatogaryVO cvo = (CatogaryVO)listCatogary.get(i);
										%>
                <option value="<%=cvo.getClassname()%>"><%=cvo.getClassname()%></option>
                <%
											}
										}
										%>
              </select>            </td>
            <td width="30%" align="center"><input type="text" name="keyword" value="请输入设备关键字" onMouseDown="_clearinfo();"/>            </td>
            <td width="12%"><input type="button" value="检索" onClick="_search();"/></td>
            <td width="13%" align="right"><a href="<%=request.getContextPath()%>/servlet/StoreSearchByTypeServlet" class="black-13">分类检索</a></td>
          </tr>
        </table>
      </td>
    </tr>
    <tr><td height="3"></td></tr>
    <!--检索结果-->
    <%
	List devicelist = (List)request.getAttribute("devicelist");
	if(devicelist!=null && devicelist.size()>0){		
	%>
            <tr id="content4" style="display:block">
                <td>
                <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#CCCCCC">
                	<tr>
                    	<td width="16%" height="30" align="center" bgcolor="#999999" class="white-13-b">分类</td>
                    	<td width="22%" align="center" bgcolor="#999999" class="white-13-b">型号</td>
                   	  	<td width="10%" align="center" bgcolor="#999999" class="white-13-b">状态</td>
                        <td width="20%" align="center" bgcolor="#999999" class="white-13-b">用途</td>
                   	  	<td width="16%" align="center" bgcolor="#999999" class="white-13-b">借出时间</td>
                      	<td width="16%" align="center" bgcolor="#999999" class="white-13-b">预计归还时间</td>
                	  	
                	</tr>
                    <%
					Date today = new Date();
					if(devicelist!=null){
						for(int b=0;b<devicelist.size();b++){
							BorrowOutVO bvo = (BorrowOutVO)devicelist.get(b);
							String devicewhere = bvo.getDevicewhere();
							Date borrowdate = new Date(108,4,12);
							if(bvo.getBorrowdate()!=null){
								borrowdate = bvo.getBorrowdate();
							}
							//if(borrowdate ==null) borrowdate = new Date(108,4,12);
							
							Date returndate = new Date();
							if(bvo.getReturndate()==null){
								returndate.setTime(borrowdate.getTime()+10*24*60*60*1000);
							}else{
								returndate = bvo.getReturndate();
							}
					%>
                   	<tr>
                    	<td width="16%" height="25" align="center" bgcolor="#FFFFFF" class="black-13"><%=bvo.getClassname()%></td>
                    	<td width="22%" align="center" bgcolor="#FFFFFF" class="black-13"><%=bvo.getDevicetype()%></td>
                        <td width="10%" align="center" bgcolor="#FFFFFF" class="black-13"><%="2".equals(devicewhere)?"借出":"在库"%></td>
                   	  	<td width="20%" align="center" bgcolor="#FFFFFF" class="black-13">
						<%if("2".equals(devicewhere)){%>
						<%=bvo.getPersonname()%>(<%=bvo.getPersonorg()%>)
                        <%}else{
						%>
                        <%=bvo.getBorrowto()==null?"":bvo.getBorrowto()%>
                        <%
						}%>
                        </td>
                        
                   	  	<td width="16%" align="center" bgcolor="#FFFFFF" class="black-13">
						<%
						if("2".equals(devicewhere)){
						%>
						<%=(borrowdate.getYear()+1900)+"-"+(borrowdate.getMonth()+1)+"-"+borrowdate.getDate()%>
                        <%
						}
						%>
                        </td>
                      	<td width="16%" align="center" bgcolor="#FFFFFF" class="black-13">
						<%
						if("2".equals(devicewhere)){
						%>
						<%=(returndate.getYear()+1900)+"-"+(returndate.getMonth()+1)+"-"+returndate.getDate()%></td>
                	  	<%
						}
						%>
                	</tr>
                    <%
						
						}
					}
					%>
                    
                </table>
                </td>
            </tr>
    <%
	}
	%>
            <!--检索结果-->
    
    
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

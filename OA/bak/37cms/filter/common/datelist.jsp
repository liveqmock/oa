<%@ page contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="cms.base.TimeUtil" %>
<%@ page import="cms.util.ObjectUtility" %>
<%@ taglib uri="/WEB-INF/cms-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/cms-info.tld" prefix="cms"%>
<%
	String time = TimeUtil.getCurrentDate("yyyy年MM月dd日 ")+TimeUtil.getCurrentWeek();
	String date = ObjectUtility.stringFormat(request.getParameter("date"));
%>
<script src="<%=request.getContextPath()%>/cms/include/DatePicker/WdatePicker.js"></script>
<html>
<head>
<title>历史信息速查</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0<p align="center"></p>px;
	margin-bottom: 0px;
}
-->
</style>
<link href="../Style/css_grey.css" rel="stylesheet" id="homepagestyle" type="text/css" />
<style type="text/css">
<!--
.STYLE1 {
	font-size: 20px;
	font-weight: bold;
	color: #004466;
}
.fixed
{
right:10px;
top:100px;
width:100px;
height:100px;
background:#FFFFFF;
border:#336699 1px dashed;
+position:absolute;
+top:expression(eval(document.body.scrollTop)+120);

-->
</style>
</head>
<script language="javascript">
WdatePicker({eCont:'datelist',maxDate:'%y-%M-%d',onpicked:function(dp){
window.location.href = '<%=request.getContextPath()%>/filter/common/datelist.jsp?date='+dp.cal.getDateStr()}})
function changeStyle(id){//切换样式
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+id+".css";
	setCookie("xhsstyle",id);
}
function setCookie(name,value){
	//切换样式时设置COOKIE
	var cookies = document.cookie;
	var setcookies = "";
	var lastcookies = "";
	var Days = 30;
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	
	var deltime = new Date();
	daltime = exp.setTime (exp.getTime() - 1);    

	while(cookies.indexOf(";")>=0){
		var tempcookie = cookies.substring(0,cookies.indexOf(";"));
		cookies = cookies.substring(cookies.indexOf(";")+1);
		
		if(tempcookie.indexOf(name)>=0){
			//删除原来的COOKIE
			document.cookie = name+"="+value+";expires="+deltime.toGMTString();
		}
	}
	//设置新的样式
	document.cookie = name+"="+value+";expires="+exp.toGMTString()+";path=/;domain=10.102.1.40";
}

function getCookie(name){
	var cook =document.cookie;
	//alert(cook);
	if(cook.indexOf("xhsstyle")>=0){
		var cook1 = cook.substring(cook.indexOf("xhsstyle"));
		//alert(cook1+" "+cook1.indexOf("=")+" "+cook1.indexOf(";"));
		var value = "grey";
		if(cook1.indexOf(";")>0){ 
			value = cook1.substring(cook1.indexOf("=")+1,cook1.indexOf(";"));
		}else{
			value = cook1.substring(cook1.indexOf("=")+1);
		}
		return value;
	}else{
		return "grey";
	}
}
function initstyle(){
	var style = getCookie("xhsstyle");
	document.getElementById("homepagestyle").href = "<%=request.getContextPath()%>/Style/css_"+style+".css";
}

initstyle();
</script>
<body>
<form name="frm" method="post">
<jsp:include page= "/include/top.jsp" />

<div id="datelist" class=fixed></div>
<table width="1003" border="0" cellspacing="0" cellpadding="0" align="center">
  
  <tr>
    <td width="10" bgcolor="#FFFFFF"></td>
    
    <td width="980" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      

        <tr><td colspan="11" height="5"></td></tr>
      <tr>
        <td height="60" colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="30" bgcolor="#FFFFFF" align="left" class="block_title">
			&nbsp;&nbsp; (<%=date%>) 信息
			</td>
            </tr>
            <TR>
            <TD background="<%=request.getContextPath()%>/images/mid_02.gif" height=3></TD>
			<TR>
        </table></td>
        </tr>
      
      <cms:infolist id="info1" statusId="cur" type="allsite" time="<%=date%>" infoType="both" perPageSize="50">
      <tr>
        <td  valign="top" height="26"><p class="message_title">
          &nbsp;&nbsp;·<span class="function_title">[<%=department%>>><%=channelName%>]</span><cms:info target="_blank" style="text-decoration:none"/></p>        </td>
        <td width="18%" valign="top"><div align="left" class="message_date">
           &nbsp;&nbsp;<a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info1" property="date"/></a>
		</div></td>
      </tr>
	  </cms:infolist>

      <tr>
        <td height="13" colspan="2" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="6" colspan="2"></td>
          </tr>
          <tr>
            <td height="1" colspan="2" background="../images/dot2.jpg"><img src="../images/dot2.jpg" width="1" height="1" /></td>
          </tr>
          <tr>
            <td width="41%" height="24" bgcolor="#FFFFFF" class="foot_message">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共<span class="red-12"> <cms:write name="cur" property="pageCount" /> </span>页│当前是第 <span class="red-12"><cms:write name="cur" property="curPage" /></span>页</td>
            <td width="59%" bgcolor="#FFFFFF" class="foot_message"><div align="right" class="blue-12"><input type="text" size="3" name="pagenum"><input type="button" value="go" onClick="<cms:write name='cur' property='goPageLink'/>">&nbsp;&nbsp;<a href="<cms:write name="cur" property="firstLink" />">首页</a>│<a href="<cms:write name="cur" property="previousLink" />">上页</a>│<a href="<cms:write name="cur" property="nextLink" />">下页</a>│<a href="<cms:write name="cur" property="lastLink" />">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
          </tr>
        </table></td>
        </tr>
    </table></td>
    <td width="15">&nbsp;</td>
  </tr>
</table>
<table width="980" border="0" cellspacing="0" cellpadding="0" align="center">
  
  <tr>
    <td height="48" bgcolor="#EFEFEF"><div align="center" class="message_date">Tel:010-63072715 Copyright (C) 2008 版权所有　　<br />
制作单位：新华社技术局　（浏览本网主页，建议将电脑显示屏的分辨率调为1024*768）<br />
    </div></td>
  </tr>
</table>
</form>
</body>
</html>

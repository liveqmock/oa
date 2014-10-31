<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page import="cms.base.TimeUtil" %>
<%@ taglib uri="/WEB-INF/cms-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/cms-info.tld" prefix="cms"%>
<%
	//无页面请求参数
	//获得输出数据
	//String time = TimeUtil.getCurrentDate("yyyy年MM月dd日")+" "+TimeUtil.getCurrentWeek();
    int messagecount = 0;
%>
<head>

<title>新华社办公信息化系统</title>
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
<link href="<%=request.getContextPath()%>/Style/css_red.css" id="homepagestyle" rel="stylesheet" type="text/css" />

<SCRIPT LANGUAGE="JavaScript">
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
</SCRIPT>

</head>
<html>
<body>
<form name="frm" method="post">
<table border="0" cellpadding="0" cellspacing="0" width="447" align="center">
    	<tr>
        	<td><table border="0" cellpadding="0" cellspacing="1" width="447" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
              <tr>
                <td bgcolor="#FFFFFF">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td height="26" colspan="2" class="block_title"><cms:channel channelId="4" styleClass="block_title" target="_blank" style="text-decoration:none"/></td>
                      <td width="68" align="right" valign="middle" class="block_title_blank">
					  <cms:channel channelId="4" link="true" displayName="<img src='/cms/Style/images/more.gif' width='29' height='5' border='0'>" styleClass="block_title" style="text-decoration:none" target="_blank"/>
					  </td>
                    </tr>
                    <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
				  <%
					  messagecount=0;
					  %>
                  <cms:infolist channelId="4" id="info1" type="channelTree" infoType="both" perPageSize="3" titleLength="52">
				  <%
					  messagecount++;
					  %>
                    <tr>
                      <td colspan="2" align="left" class="message_title" height="24" nowrap="nowrap">·<cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                      <td class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info1" property="date" /></a></td>
                    </tr>
                  </cms:infolist>                 
               <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24">&nbsp;</td></tr>
             <%
				}
			 }
			 %>
                    
                   
                </table></td>
              </tr>
            </table></td>
            </tr>
            <tr><td height="3"></td></tr>
            <tr>
            <td><table border="0" cellpadding="0" cellspacing="1" width="447" class="table_bgcolor" bgcolor="#FFFFFF" align="center">
              <tr>
                <td bgcolor="#FFFFFF"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                      <td height="26" colspan="2" class="block_title"><cms:channel channelId="15" styleClass="block_title" target="_blank" style="text-decoration:none"/></td>
                      <td width="16%" align="right" valign="middle" class="block_title_blank"><cms:channel channelId="15" link="true" displayName="<img src='/cms/Style/images/more.gif' width='29' height='5' border='0'>" styleClass="block_title" style="text-decoration:none" target="_blank"/></td>
                  </tr>
                    <TR>
                  	<TD background="<%=request.getContextPath()%>/Style/images/mid_02.gif" colspan="6"><IMG height=3 src="<%=request.getContextPath()%>/Style/images/mid_02.gif" width=3></TD>
                  </TR>
				  <%
					  messagecount=0;
					  %>
                  <cms:infolist channelId="15" id="info2" infoType="both" type="channelTree" perPageSize="3" titleLength="52">
				   <%
					  messagecount++;
					  %>
                    <tr>
                      <td colspan="2" align="left" class="message_title" height="24">·<cms:info target="_blank" styleClass="message_title" style="text-decoration:none" title="<%=infoTitle%>"/></td>
                      <td class="message_date"><a href="/cms/cms/jsp/info_message.jsp?infoId=<%=infoId%>" style="text-decoration:none" target="_blank" class="message_date"><cms:write name="info2" property="date" /></a></td>
                    </tr>
                  </cms:infolist>  
                     <%
			 if(messagecount<3){
			 	for(int m=messagecount;m<3;m++){
			 %>
             	<tr><td colspan="3" height="24" class="message_title"></td></tr>
             <%
				}
			 }
			 %>
                   
                </table></td>
              </tr>
            </table></td>
        </tr>
  </table>
    <!--值班简报和交接班日记-->
    
</form>
</body>
</html>
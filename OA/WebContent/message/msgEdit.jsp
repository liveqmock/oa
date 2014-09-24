<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.message.vo.*"%>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<html>
<head><%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module51");
		String ip=request.getRemoteAddr();		
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>
<title>短信发送</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
<SCRIPT language="JavaScript" src="<%=request.getContextPath()%>/include/common.js"></SCRIPT>
<script language="JavaScript">
var bSubmit=false;
function _send(){
	if(bSubmit) return;
	_emptyRes();
	if(Form_SendMsg.content.value==""){
	    alert("请填写短信内容！");
	}
	else if(getLength(Form_SendMsg.content.value)>140){
		alert("短信内容过长，最大长度为70个汉字或140个英文字母！");
	}
	else if(Form_SendMsg.msgAccess.value==""){
		alert("请选择接收人！");
	}
	else{
		Form_SendMsg.action="<%=request.getContextPath()%>/servlet/SendMsgServlet";
		bSubmit=true;
		MM_showHideLayers('alertbox','','show');
		Form_SendMsg.submit();
	}
}
function _selectPerson(){
	_emptyRes();
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_FinishAdd&sessionname=selectMsgPersonSession','addressbsook','width=700,height=550,scrollbars=yes,resizable=yes');
}
function _FinishAdd(userString){
	Form_SendMsg.msgAccess.value=userString;
}
function _alertRes(){
<% 
	String resStr=request.getParameter("resStr");
	if(resStr!=null){
		if("".equals(resStr)){
%>
			resStr.innerHTML="<font color=\"red\">短信发送成功！</font>";
<%
		}
		else{
%>
			resStr.innerHTML="<font color=\"red\"><%=resStr%></font>";
<%
		}
	}
%>
}
function _emptyRes(){
	resStr.innerHTML="";
}
function MM_findObj(n, d) { //v4.01
	var p,i,x;  
	if(!d) d=document; 
	if((p=n.indexOf("?"))>0&&parent.frames.length) {
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
	}
	if(!(x=d[n])&&d.all) x=d.all[n]; 
	for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	if(!x && d.getElementById) x=d.getElementById(n); 
	return x;
}
function MM_showHideLayers() { //v6.0
	var i,p,v,obj,args=MM_showHideLayers.arguments;
	for (i=0; i<(args.length-2); i+=3){
		if ((obj=MM_findObj(args[i]))!=null) { 
			v=args[i+2];
			if (obj.style) { 
				obj=obj.style; 
				v=(v=='show')?'visible':(v=='hide')?'hidden':v; 
			}
			obj.visibility=v; 
		}
	}
}
</script>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" text="#000000" leftMargin=0 rightMargin=0 bottomMargin=0 topMargin=0>

<form name="Form_SendMsg" method="post" action="" leftMargin=0 rightMargin=0 bottomMargin=0 topMargin=0>
<input name="orgid" type="hidden" value="<%=request.getParameter("orgid")%>">
<input name="orgname" type="hidden" value="<%=request.getParameter("orgname")%>">
   <br>
	<table  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td valign="top">
   <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">[<%=request.getParameter("orgname")%>]短信发送</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
	</table>
	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
      <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
               <td>
               <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td bgcolor="#D6E7F7" nowrap width="100" class="text-01"><div align="right">内&nbsp;&nbsp;&nbsp;&nbsp;容&#65306;</div></td>
                  <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-18.gif"><img src="<%=request.getContextPath()%>/images/bg-18.gif" width=2 height=2></td>
                  <td bgcolor="F2F9FF" class="text-01"><textarea name="content" cols="50"  rows="6"></textarea></td>
                </tr>
                <tr>
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"><img src="<%=request.getContextPath()%>/images/bg-13.gif" width="2" height="2"></td>
                  <td colspan="2"  background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                </tr>
                <tr>
                  <td bgcolor="#D6E7F7" nowrap height="22" class="text-01" align="right">接 收 人&#65306;</td>
                  <td bgcolor="F2F9FF" class="text-01"><textarea cols="50" rows="1" readonly name="msgAccess" class="text-01"></textarea>
                      <img src="<%=request.getContextPath()%>/images/botton-select_directory_inquiries.gif" onclick="javascript:_selectPerson()" style="cursor:hand" align="absmiddle"></td>
                </tr>
                <tr>
                  <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"><img src="<%=request.getContextPath()%>/images/bg-13.gif" width="2" height="2"></td>
                  <td colspan="2"  background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                </tr>
               </table>
              </td>
      <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
        </tr>
    </table>
	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td id="resStr" align="center" width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"></td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
</table>
</td></tr></table>
<script language="javascript">
_alertRes();
</script>
<table border="0" cellpadding="0" cellspacing="0" width="69%" id="AutoNumber2" height="40" align="center">
          <tr>
            <td align="center" class="text-01" nowrap valign="bottom"><img src="<%=request.getContextPath()%>/images/send.gif" hspace="10"  border="0" style="cursor:hand;" onclick="javascript:_send()"></td>
          </tr>
  </table>
   <div id="alertbox" style="position:absolute; width:196px; height:24px; z-index:1; left: 400; top: 60;visibility: hidden;">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#000000">
			<tr bgcolor="#EEFFF7">
				<td height="25" align=center>正在发送短信，请稍等...</td>
			</tr>
		</table>
	</div>
</form>
</body>
</html>


<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ page import="com.icss.oa.netoffice.onduty.vo.OfficeDutyRightSysPersonVO" %>

<html>
<head>
<title>排值班表权限列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
function _Add(){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_FinishAdd&sessionname=selectForDuty','addressbsook','width=700,height=550,scrollbars=yes');
}
function _FinishAdd(userString){
	document.form.action="<%=request.getContextPath()%>/servlet/AuthorDutyServlet";
	document.form.submit();
	return true;
}
function _Delete(){
	if(_check("请选择要删除的人员！")){
		document.form.action="<%=request.getContextPath()%>/servlet/AuthorlessDutyServlet";
		document.form.submit();
	}
}

function _check(){
	if(document.form.checkid == undefined){
		alert("没有可删除的记录！");
		return false;
	}else if( document.form.checkid.length == undefined){
        	if(document.form.checkid.checked){	
				return true;
			}else{
		    	alert("请选择删除项!");
		    	return false;
			}
	}else{
	    for(var j=0;j<document.form.checkid.length;j++){
	        if(document.form.checkid[j].checked){
		    	return true;
			}
	    }
	    alert("请选择删除项!");
	    return false;  
	}
    }
</script>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" text="#000000" leftMargin=0 rightMargin=0 bottomMargin=0 topMargin=0>
<form name="form" method="post" action="">
<input type="hidden" name="orgUUid" value="<%=request.getAttribute("orgUUid")%>">
<br>
	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr><td valign="top">
   <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">[<%=request.getAttribute("orgName")%>]有排值班表权限的人员列表</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg">
                	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td nowrap height="22" bgcolor="#FFFBEF">&nbsp;&nbsp;</td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td nowrap bgcolor="#FFFBEF" align="center" class="title-04">&nbsp;姓名&nbsp;</td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td nowrap bgcolor="#FFFBEF" align="center" class="title-04">&nbsp;登陆名&nbsp;</td>                      
                    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
<%
	List authorPersonList = (List)request.getAttribute("authorPersonList");
	if(authorPersonList!=null&&authorPersonList.size()>0){
		Iterator it = authorPersonList.iterator();
		int i=0;
		while(it.hasNext()){
			++i;
			OfficeDutyRightSysPersonVO vo = (OfficeDutyRightSysPersonVO)it.next();
			if(i%2==1){
%>
					<tr bgColor=#D6E7F7 onmouseover="this.bgColor='#8CC0E8';" onmouseout="this.bgColor='#D6E7F7';">
<%
			}
			else{
%>
                    <tr bgcolor="#F2F9FF"; onMouseOver="this.bgColor='#8CC0E8';" onMouseOut="this.bgColor='#F2F9FF';">
<%
			}
%>
                      <td nowrap height="22" class="text-01" align="center">&nbsp;<input type="checkbox" name="checkid" value="<%= vo.getPersonuuid()%>">&nbsp;</td>
                      <td nowrap class="text-01" align="center">&nbsp;<a href="#" onclick="javascript:window.open('<%=request.getContextPath()%>/servlet/PersonInfoServlet?personuuid=<%= vo.getPersonuuid()%>','','width=800,height=400,toolbar=0,directories=0,status=0,menu=0,scrollbars=yes,resizable=yes,copyhistory=no,left=50,top=20');"><%=vo.getCnname()%></a>&nbsp;</td>
                      <td nowrap align="center" class="text-01">&nbsp;<%= vo.getUserid()%>&nbsp;</td>
                    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                    </tr>
<%
		}//while
	}
%>	 
                </table></td>
              </tr>
          </table></td>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-22.gif"><img src="<%=request.getContextPath()%>/images/bg-22.gif" width="1" height="4"></td>
        </tr>
</table>
	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">&nbsp;</td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
</table>
</td></tr></table>
<table border="0" cellpadding="0" cellspacing="0" width="69%" height="40" align="center">
          <tr>
            <td valign="bottom" nowrap align="center" class="text-01">
            	<img src="<%=request.getContextPath()%>/images/botton-add.gif" hspace="10"  border="0" style="cursor:hand;" onclick='javascript:_Add()'>
            	<img src="<%=request.getContextPath()%>/images/botton-delete.gif" hspace="10"  border="0" style="cursor:hand;" onclick='javascript:_Delete()'>
            </td>
          </tr>
  </table>
</form>
</body>
</html>


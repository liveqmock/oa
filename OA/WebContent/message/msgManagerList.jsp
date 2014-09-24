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
		String modulename = StatPropertyManager.getString("stat_module50");
		String ip=request.getRemoteAddr();		
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>
<title>短信发送人员列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/include/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
function _checkPage(){
<%
		int curPageNum1 = com.icss.j2ee.util.PageScrollUtil.getPageNum();
		int pageCount1 = com.icss.j2ee.util.PageScrollUtil.getPageCount();
		if(curPageNum1>pageCount1){
			curPageNum1=pageCount1;
			String url=request.getContextPath()+"/servlet/ListMsgManagerServlet?_page_num="+curPageNum1
						+"&orgid="+request.getParameter("orgid");
%>
		this.location="<%=url%>";
<%
		}
%>
}
function _check(outString){
	var i;
	if(document.Form_managerList.check == undefined){
    	alert(outString);
		return false;
	}
	if(document.Form_managerList.check.length == undefined){
		if(document.Form_managerList.check.checked){
			return true;
		}
	}
	else{
		for(i=0;i<document.Form_managerList.check.length;i++){
			if(document.Form_managerList.check[i].checked){
				return true;
			}
		}
	}
	alert(outString);
	return false;
}
function _Add(){
	window.open('<%=request.getContextPath()%>/address/pubaddress/selectPersonFrame.jsp?doFunction=_FinishAdd&sessionname=selectPersonSession','addressbsook','width=700,height=550,scrollbars=yes,resizable=yes');
}
function _FinishAdd(userString){
	document.Form_managerList.action="<%=request.getContextPath()%>/servlet/AddMsgManagerServlet";
	document.Form_managerList.submit();
}
function _Delete(){
	if(_check("请选择要删除的管理员！")){
		document.Form_managerList.action="<%=request.getContextPath()%>/servlet/DeleteMsgManagerServlet";
		document.Form_managerList.submit();
	}
}
</script>
</head>

<body  background="<%=request.getContextPath()%>/images/bg-08.gif" text="#000000" leftMargin=0 rightMargin=0 bottomMargin=0 topMargin=0>

<script language="javascript">
_checkPage();
</script>

<form name="Form_managerList" method="post" action="" leftMargin=0 rightMargin=0 bottomMargin=0 topMargin=0>
<input type="hidden" name="_page_num" value="<%=request.getParameter("_page_num")==null?"1":request.getParameter("_page_num")%>">
<input name="orgid" type="hidden" value="<%=request.getParameter("orgid")%>">
<input name="orgname" type="hidden" value="<%=request.getParameter("orgname")%>">
   <br>
	<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td valign="top">
   <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
          <td background="<%=request.getContextPath()%>/images/bg-12.gif" class="title-05">[<%=request.getParameter("orgname")%>]短信发送人列表</td>
          <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
        </tr>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
          <td width="100%"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td background="<%=request.getContextPath()%>/images/bg-09.jpg"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td nowrap height="22" bgcolor="#FFFBEF">&nbsp;&nbsp;</td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td nowrap bgcolor="#FFFBEF" align="center" class="title-04">&nbsp;姓名&nbsp;</td>
                      <td width="2" rowspan="100" valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="2" height="2"></td>
                      <td nowrap height="22" bgcolor="#FFFBEF" align="center" class="title-04">&nbsp;职务&nbsp;</td>
                    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                    </tr>
<%
	List list = (List)request.getAttribute("list");
	if(list!=null){
	Iterator it = list.iterator();
	int i=0;
	while(it.hasNext()){
		++i;
		MsgManagerSysPersonVO vo = (MsgManagerSysPersonVO)it.next();
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
                      <td nowrap height="22" class="text-01" align="center">&nbsp;<input type="checkbox" name="check" value="<%= vo.getMmPersonuuid()%>">&nbsp;</td>
                      <td nowrap  class="text-01" align="center">&nbsp;<a href="#" onclick="javascript:window.open('<%=request.getContextPath()%>/servlet/PersonInfoServlet?personuuid=<%= vo.getMmPersonuuid()%>','','width=800,height=400,toolbar=0,directories=0,status=0,menu=0,scrollbars=yes,resizable=yes,copyhistory=no,left=50,top=20');"><%= vo.getCnname()%></a>&nbsp;</td>
                      <td nowrap align="center" class="text-01">&nbsp;<%= vo.getJob()%>&nbsp;</td>
                    </tr>
                    <tr>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      <td background="<%=request.getContextPath()%>/images/bg-13.gif" bgcolor="F2F9FF" class="text-01"></td>
                    </tr>
<%
	}
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
          <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01"><%@ include file="/include/defaultPageScrollBar.jsp" %></td>
          <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
        </tr>
</table>
</td></tr></table>
<table border="0" cellpadding="0" cellspacing="0" width="69%" id="AutoNumber2" height="40" align="center">
          <tr>
            <td align="center" class="text-01" nowrap valign="bottom"><img src="<%=request.getContextPath()%>/images/botton-add.gif" hspace="10"  border="0" style="cursor:hand;" onclick='javascript:_Add()'><img src="<%=request.getContextPath()%>/images/botton-delete.gif" hspace="10"  border="0" style="cursor:hand;" onclick='javascript:_Delete()'></td>
          </tr>
  </table>
</form>
</body>
</html>


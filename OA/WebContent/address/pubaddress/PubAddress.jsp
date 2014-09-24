<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="com.icss.resourceone.sdk.framework.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.j2ee.util.StringUtil"%>
<%@ page import="com.icss.oa.address.vo.*"%>


<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<LINK href="<%=request.getContextPath()%>/include/style.css" rel=stylesheet>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>

<BODY text=#eeeeee bgColor=#ffffff leftMargin=0 background="<%=request.getContextPath()%>/images/bg-08.gif" topMargin=0 marginheight="0" marginwidth="0">


<%--*******************以下代码是站点统计的扩展标记/Start****************************--%>
<%@ page import="com.icss.oa.util.*" %>
<%@ taglib uri="/WEB-INF/stat.tld" prefix="stat" %>
<%
		
		if(("1").equals(StatSiteControl.geSwitch())){
		String modulename = StatPropertyManager.getString("stat_module6");
		String ip=request.getRemoteAddr();		
%>
< stat:stat modulename="<%=modulename%>" ip="<%=ip%>" /><%}%>
<%--*******************站点统计的扩展标记到此结束/End****************************--%>
<div align="center">
<br>
<%
String _page_num=request.getParameter("_page_num");
if(_page_num==null)
    _page_num="1";

%>
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-12.gif"><img src="<%=request.getContextPath()%>/images/bg-10.gif" width="14" height="22"></td>
              <td background="<%=request.getContextPath()%>/images/bg-12.gif"  class="title-05">
<%
       if(!"null".equals(request.getParameter("orgName"))){
//       System.out.println("orgName = "+request.getParameter("orgName"));
%>
              <%=request.getParameter("orgName")%>
<%
}
%>
              人员信息列表</td>
              <td width="1%"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-11.gif" width="20" height="22"></div></td>
            </tr>
          </table>

		  <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1" background="<%=request.getContextPath()%>/images/bg-21.gif"><img src="<%=request.getContextPath()%>/images/bg-21.gif" width="1" height="4"></td>
              <td width="100%">
			  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td background="<%=request.getContextPath()%>/images/bg-09.jpg">	
<%
List personlist = (List)request.getAttribute("personlist");
if (!personlist.isEmpty()){
%>				
				  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr  bgcolor="#FFFBEF">
                        <td width="20%" ><div align="center" class="title-04">姓名</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="0" height="2"></td>
                        <td width="20%" ><div align="center" class="title-04">性别</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="0" height="2"></td>
                        <td width="40%" ><div align="center" class="title-04">公文地址</div></td>
                        <td width="0"  rowspan="100"  valign="top" background="<%=request.getContextPath()%>/images/bg-24.gif"><img src="<%=request.getContextPath()%>/images/bg-24.gif" width="0" height="2"></td>
                        <td width="20%" height="22"><div align="center" class="title-04">电话</div></td>

					</tr>
                      <tr>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%

        Iterator result=personlist.iterator();

int index = 0;
        while(result.hasNext()){
            SysOrgpersonVO sysorgpersonvo=(SysOrgpersonVO)result.next();
	index ++;
	String color = "#F2F9FF";
	if(index % 2 == 1)
		color = "#D8EAF8";
			String sex = "";
			if (sysorgpersonvo.getSex().equals(new Integer(0)))
				sex = "保密";
			if (sysorgpersonvo.getSex().equals(new Integer(1)))
				sex = "男";
			if (sysorgpersonvo.getSex().equals(new Integer(2)))
				sex = "女";

%>
					<tr bgcolor=<%=color%> onMouseOut="this.bgColor='<%=color%>';" onMouseOver="this.bgColor='#8CC0E8';">
						
                        <td height="22"   class="text-01"><div align="center"><%=StringUtil.escapeNull(sysorgpersonvo.getCnname())%></div></td>
                        <td  class="text-01"><div align="center"><%=sex%> </div></td>
                        <td  class="text-01"><div align="center"><%=StringUtil.escapeNull(sysorgpersonvo.getEnname())%>@oa.xinhua.org</div></td>
                        <td class="text-01"><div align="center"><%=StringUtil.escapeNull(sysorgpersonvo.getOfficetel())%></div></td>
					</tr>
                      <tr>
                      
                        <td height="2" background="<%=request.getContextPath()%>/images/bg-13.gif" class="text-01"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                        <td background="<%=request.getContextPath()%>/images/bg-13.gif"></td>
                      </tr>
<%
        }//while
}else{
	if((request.getParameter("orgName").equals("null"))||(request.getParameter("orgName") == null)){
%>
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr bgcolor="EEF4FF">
				<td height="22"  align="center" class="title-04">请从左边的导航栏选择</td>
			</tr>


<%		
	}else{
%>			
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr bgcolor="EEF4FF">
				<td height="22"  align="center" class="title-04"><%=request.getParameter("orgName")%>下没有人员</td>
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
          <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="1%"><img src="<%=request.getContextPath()%>/images/bg-21.jpg" width="10" height="21"></td>
              <td width="97%" background="<%=request.getContextPath()%>/images/bg-23.jpg" class="text-01">
              <%@ include file="../../include/defaultPageScrollBar.jsp"%>
              </td> 
              <td width="2%" background="<%=request.getContextPath()%>/images/bg-23.jpg"><div align="right"><img src="<%=request.getContextPath()%>/images/bg-22.jpg" width="11" height="21"></div></td>
            </tr> 
          </table>
	
<BR>
<BR>
<BR>
<BR>
<BR>
<!--input type="button" value="从地址簿挑选" OnClick="javascript:Open('')"-->
</div>
</BODY>
</HTML>
